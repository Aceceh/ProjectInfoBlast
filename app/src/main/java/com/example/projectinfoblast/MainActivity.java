package com.example.projectinfoblast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView postList;
    private Toolbar mToolBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;
    private DataSnapshot dataSnapshot;
    private ImageButton AddNewPostButton;

    // ADAM WAS HERE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mToolBar = (Toolbar) findViewById(R.id.main_page_toolbar);
        AddNewPostButton =(ImageButton) findViewById(R.id.add_new_post_button);
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        setSupportActionBar(mToolBar); //add tool bar to main activity
        getSupportActionBar().setTitle("Home");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);



        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Admin Test","Testing Post User","updated  just now",R.drawable.makima,R.drawable.profile1 ));
        items.add(new Item("Cloak Dagger","Scenery on the beach","updated 1 hour ago",R.drawable.ocean,R.drawable.profile2 ));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));


        AddNewPostButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                SendUserToPostActivity();
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                UserMenuSelector(item);
                return false;
            }

        });
    }

    private void SendUserToPostActivity() {
        Intent addNewPostIntent = new Intent (MainActivity.this, PostActivity.class);
        startActivity(addNewPostIntent);
    }

    private void SendUserToProfileActivity() {
        Intent ProfileIntent = new Intent (MainActivity.this, ProfileActivity.class);
        startActivity(ProfileIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            SendUserToLoginActivity();
        } else {
            CheckUserExistence();
        }
    }

    private void CheckUserExistence() {
        final String current_user_id = mAuth.getCurrentUser().getUid();

        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!dataSnapshot.hasChild(current_user_id)) {
                    SendUserToSetupActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void SendUserToSetupActivity() {
        Intent setupIntent = new Intent(MainActivity.this, SetupActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void UserMenuSelector(MenuItem item) {

        if (item.getItemId() == R.id.nav_profile) {
            Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
            SendUserToProfileActivity();
        }
        if (item.getItemId() == R.id.nav_addpost) {
            Toast.makeText(MainActivity.this, "Add New Post", Toast.LENGTH_SHORT).show();
            SendUserToPostActivity();

        }
        if (item.getItemId() == R.id.nav_logout) {
            mAuth.signOut();
            SendUserToLoginActivity();
        }
    }


}



