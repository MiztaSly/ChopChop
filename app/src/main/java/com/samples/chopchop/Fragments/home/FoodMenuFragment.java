package com.samples.chopchop.Fragments.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.samples.chopchop.FoodList;
import com.samples.chopchop.Interface.ItemClickListerner;
import com.samples.chopchop.Model.Category;
import com.samples.chopchop.R;
import com.samples.chopchop.ViewHolder.MenuViewHolder;
import com.squareup.picasso.Picasso;

public class FoodMenuFragment extends Fragment {

    private static final String TAG = "FoodMenuFragment";

    OnFoodMenuSelectedListener mCallback;

    private FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;

    public void setmCallback(OnFoodMenuSelectedListener mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnFoodMenuSelectedListener {
        void onFoodMenuSelectedListener(int position);
    }

    FirebaseDatabase database;
    DatabaseReference category;



    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    View view;



    public FoodMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);


        //init firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");


        //load menu
        recycler_menu = (RecyclerView)view.findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recycler_menu.setLayoutManager(layoutManager);

        loadMenu();



        return view;



    }

    private void loadMenu() {
        Log.d(TAG, "loadMenu: called");
        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Category category, int i) {

                menuViewHolder.txtMenuName.setText(category.getName());
                Picasso.with(getContext())
                        .load(category.getImage())
                        .into(menuViewHolder.imageView);


                menuViewHolder.setItemClickListener(new ItemClickListerner() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Intent foodList = new Intent(getActivity(), FoodList.class);
                        foodList.putExtra("CategoryId", adapter.getRef(position).getKey());
                        startActivity(foodList);

                    }


                });




            }

        };
        recycler_menu.setAdapter(adapter);

        //adapter.notifyDataSetChanged();

        recycler_menu.setLayoutManager(layoutManager);
    }





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFoodMenuSelectedListener) {
            mCallback = (OnFoodMenuSelectedListener)context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFoodMenuSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}