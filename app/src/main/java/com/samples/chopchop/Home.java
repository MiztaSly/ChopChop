package com.samples.chopchop;


import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.samples.chopchop.Common.Common;
import com.samples.chopchop.Interface.ItemClickListerner;
import com.samples.chopchop.Model.Category;
import com.samples.chopchop.ViewHolder.MenuViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Home";

    private AppBarConfiguration mAppBarConfiguration;

    TextView txtFullName;

/**
    FirebaseDatabase database;
    DatabaseReference category;





    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
**/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //init firebase
       // database = FirebaseDatabase.getInstance();
       // category = database.getReference("Category");


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
               navController.getGraph())
                .setDrawerLayout(drawer)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //set Name for user
        View headerView = navigationView.getHeaderView(0);
        txtFullName=(TextView)headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currentUser.getName());
/**
        //load menu
        recycler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        
       // loadMenu();
 **/

    }
/**
    private void loadMenu() {
      FirebaseRecyclerOptions<Category>options = new FirebaseRecyclerOptions.Builder<Category>()
              .setQuery(category,Category.class).build();
      FirebaseRecyclerAdapter<Category,MenuViewHolder>adapter;
      adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(options) {
          @Override
          protected void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull Category model) {
              holder.txtMenuName.setText(model.getName());
              Picasso.with(getBaseContext()).load(model.getImage()).into(holder.imageView);

              final Category clickedItem = model;
              holder.setItemClickListerner(new ItemClickListerner() {
                  @Override
                  public void onClick(View view, int position, boolean isLongClick) {
                      Toast.makeText(Home.this, ""+clickedItem.getName(), Toast.LENGTH_SHORT).show();
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

      GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
      recycler_menu.setLayoutManager(gridLayoutManager);

        adapter.startListening();

        adapter.notifyDataSetChanged();

        //recycler_menu.setLayoutManager(layoutManager);

        recycler_menu.setAdapter(adapter);

    }

 **/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
