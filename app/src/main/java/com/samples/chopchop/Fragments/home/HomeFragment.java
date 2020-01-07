package com.samples.chopchop.Fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.samples.chopchop.Interface.ItemClickListerner;
import com.samples.chopchop.Model.Category;
import com.samples.chopchop.R;
import com.samples.chopchop.ViewHolder.MenuViewHolder;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference category;





    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    View view;
    private FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;


    public HomeFragment() {
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
        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(category,Category.class).build();
        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull Category model) {
                holder.txtMenuName.setText(model.getName());
                Picasso.with(getContext()).load(model.getImage()).into(holder.imageView);

                final Category clickedItem = model;
                holder.setItemClickListerner(new ItemClickListerner() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity(), ""+clickedItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @NonNull
            @Override
            public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
                return new MenuViewHolder(view);
            }
        };

       // GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        //recycler_menu.setLayoutManager(gridLayoutManager);

        adapter.startListening();

        adapter.notifyDataSetChanged();

        recycler_menu.setLayoutManager(layoutManager);

        recycler_menu.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}