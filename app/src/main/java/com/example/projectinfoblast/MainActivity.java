package com.example.projectinfoblast;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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

        //items.add(new Item("Izat MPP","Scenery on the beach","updated 45 minutes ago",R.drawable.izat_post,R.drawable.izat_profile));



        List<Item> items = new ArrayList<Item>();
        //post 1
        items.add(new Item("Muaz MPP","|| HEBAHAN PELAKSANAAN KAEDAH PdP SEMESTER II SESI 2024/2025 ||\n" +
                "\n" +
                "Assalamualaikum wbt., Salam Sejahtera dan Salam Nadi Untuk Semua.\n" +
                "\n" +
                "Berdasarkan Notis Akademik Bil. 1/2025, Majlis Perwakilan Pelajar UTHM Sesi 2024/2025 ingin memaklumkan bahawa pelaksanaan Kaedah Pengajaran dan Pembelajaran (PdP) bagi Semester II Sesi 2024/2025 adalah seperti berikut:\n" +
                "\n" +
                "PdP Secara Dalam Talian\n" +
                "Minggu 1 - Minggu 3: 17 Mac 2025 - 6 April 2025\n" +
                "Hari Raya Aidilfitri: 31 Mac & 1 April 2025\n" +
                "\n" +
                "PdP Secara Fizikal\n" +
                "Minggu 4 - Minggu 7: 7 April 2025 - 4 Mei 2025\n" +
                "\n" +
                "Cuti Pertengahan Semester Minggu 8: 5 - 11 Mei 2025\n" +
                "\n" +
                "Kami berharap semua pelajar dapat mengambil cakna tentang perkara ini dan membuat persediaan awal bagi memastikan proses pembelajaran berjalan dengan lancar sepanjang semester akan datang. Sebarang pertanyaan lanjut boleh dirujuk kepada fakulti/pusat masing-masing.\n" +
                "\n" +
                "...  \n" +
                "\n" +
                "|| ANNOUNCEMENT ON THE IMPLEMENTATION OF T&L METHODS FOR SEMESTER II SESSION 2024/2025 ||\n" +
                "\n" +
                "Assalamualaikum wbt., Greetings & Salam Nadi Untuk Semua.\n" +
                "\n" +
                "Based on Academic Notice No. 1/2025, the UTHM Student Representative Council for the 2024/2025 Session would like to inform student about the Teaching and Learning (T&L) Methods for Semester II Session 2024/2025 as follows:\n" +
                "\n" +
                "Online T&L\n" +
                "Week 1 - Week 3: March 17, 2025 - April 6, 2025 \n" +
                "Eid al-Fitr: March 31 & April 1, 2025\n" +
                "\n" +
                "Physical T&L\n" +
                "Week 4 - Week 7: April 7, 2025 - May 4, 2025\n" +
                "\n" +
                "Mid-Semester Break\n" +
                "Week 8: May 5 - 11, 2025\n" +
                "\n" +
                "We hope all students will take note of this matter and make early preparations to ensure that the learning process runs smoothly throughout the upcoming semester. For further inquiries, please refer to your respective faculty/center.\n" +
                "\n" +
                "Thank you.\n" +
                " \n" +
                "\"NADI UNTUK SEMUA\"\n" +
                "\n" +
                "Jabatan Akademik dan Pengantarabangsaan\n" +
                "Majlis Perwakilan Pelajar Sesi 2024/2025\n" +
                "Universiti Tun Hussein Onn Malaysia","updated just now",R.drawable.post_muaz,R.drawable.main_profile ));
        //post 2
        items.add(new Item("Izat MPP","#phepinfo \n" +
                "\n" +
                "PEMAKLUMAN PERUBAHAN JADUAL BAS KITARAN\n" +
                "BERMULA 3 JANUARI 2025 SEHINGGA 17 JANUARI 2025\n" +
                "\n" +
                "Merujuk kepada perkara di atas, sukacita dimaklumkan berikut adalah perubahan jadual operasi bas bagi laluan Kampus Cawangan Pagoh yang berkuatkuasa pada 3 Januari 2025 sehingga 17 Januari 2025. Bersama-sama ini dilampirkan jadual perjalanan untuk rujukan semua pelajar.\n" +
                "\n" +
                "Sekian, terima kasih.\n" +
                "\n" +
                "“DENGAN HIKMAH KITA MENEROKA”\n" +
                "“MALAYSIA MADANI”\n" +
                "\n" +
                "Pejabat Hal Ehwal Pelajar\n" +
                "Universiti Tun Hussein Onn Malaysia\n" +
                "\n" +
                "#phep\n" +
                "#hepa\n" +
                "#uthm\n" +
                "#movingforward\n" +
                "#uthmprihatin\n" +
                "#gtu2030","updated 45 minutes ago",R.drawable.izat_post,R.drawable.izat_profile));
        //post3
        items.add(new Item("Muhafiz","[ \uD835\uDC13\uD835\uDC08\uD835\uDC0D\uD835\uDC09\uD835\uDC00\uD835\uDC14\uD835\uDC00\uD835\uDC0D \uD835\uDC0F\uD835\uDC04\uD835\uDC0B\uD835\uDC00\uD835\uDC09\uD835\uDC00\uD835\uDC11 \uD835\uDC14\uD835\uDC13\uD835\uDC07\uD835\uDC0C \uD835\uDC18\uD835\uDC00\uD835\uDC0D\uD835\uDC06 \uD835\uDC13\uD835\uDC04\uD835\uDC11\uD835\uDC0A\uD835\uDC04\uD835\uDC12\uD835\uDC00\uD835\uDC0D \uD835\uDC01\uD835\uDC04\uD835\uDC0D\uD835\uDC02\uD835\uDC00\uD835\uDC0D\uD835\uDC00 \uD835\uDC01\uD835\uDC00\uD835\uDC0D\uD835\uDC09\uD835\uDC08\uD835\uDC11 \uD835\uDC0C\uD835\uDC14\uD835\uDC0B\uD835\uDC00\uD835\uDC08 \uD835\uDC09\uD835\uDC00\uD835\uDC0D\uD835\uDC14\uD835\uDC00\uD835\uDC11\uD835\uDC08 \uD835\uDFD0\uD835\uDFCE\uD835\uDFD0\uD835\uDFD3 ]\n" +
                "\n" +
                "Assalamualaikum wbt. dan salam sejahtera,\n" +
                "\n" +
                "Pelajar UTHM yang dikasihi sekalian.\n" +
                "Untuk makluman, Pusat Pembangunan Pelajar (PBP) sedang dalam usaha untuk mendapatkan maklumat pelajar UTHM dan keluarga yang terkesan dengan bencana banjir yang sedang melanda.\n" +
                "\n" +
                "Oleh itu, sekiranya ada pelajar UTHM dan keluarga yang terkesan dengan bencana banjir yang sedang melanda, mohon isikan maklumat melalui kod QR di dalam poster yang dilampirkan atau menerusi pautan di bawah:\n" +
                "\n" +
                "https://bit.ly/Banjir-Jan-2025\n" +
                "\n" +
                "Segala kerjasama dan maklumat yang diberikan didahului dengan ucapan terima kasih.\n" +
                "\n" +
                "#UTHMJohor\n" +
                "#tnchepauthm\n" +
                "#hepauthm\n" +
                "#pbputhm\n" +
                "#pheputhm\n" +
                "#nilaidankesukarelawanan","updated 2 hours ago",R.drawable.muha_post,R.drawable.muha_profile));
        //post4
        items.add(new Item("Amad","『\uD83C\uDF87HAPPY NEW YEAR✨』\n" +
                "-------------------------------\n" +
                "Assalamualaikum w.b.t and Salam Sejahtera,\n" +
                "\n" +
                "We, from the Persatuan Pelajar Diploma, wish you a New Year filled with endless joy, good health, and success. May you create cherished moments with your loved ones and embrace new opportunities that come your way in 2025.\n" +
                "\n" +
                "Wishing you boundless happiness and prosperity in the year ahead ! ❤\uFE0F\n" +
                "\n" +
                "Genuinely from,\n" +
                "Persatuan Pelajar Diploma, \n" +
                "Sesi 2024/2025✨\n" +
                "-------------------------------\n" +
                "#ppduthm\n" +
                "#ppdmoveforward\n" +
                "#unlimitedpotential\n" +
                "#withwisdomweexplore\n" +
                "#UTHMPilihanPertama\n" +
                "#cedsuthm\n" +
                "#universititunhusseinonnmalaysia","updated 5 days ago",R.drawable.adam_post,R.drawable.adam_profile));
        //post5
        items.add(new Item("Asyraf","\uD83D\uDCCDNOTIS PEMAKLUMAN PENGOSONGAN PARKIR KENDERAAN DI ANTARA BLOK A12 & A13\n" +
                "\n" +
                "Assalammualaikum dan Salam Sejahtera.\n" +
                "\n" +
                "1. Makluman kepada semua penghuni Kolej Kediaman Pelajar UTHM Kampus Cawangan Pagoh, pihak keselamatan memohon kerjasama para pemilik kenderaan untuk mengosongkan parkir di antara Blok A12 & A13  untuk memberi laluan kepada Program The Dreamscape Delight yang akan dijalankan seperti ketetapan berikut.\n" +
                "\n" +
                "Tarikh   : 27 Disember 2024 - 28 Disember 2024\n" +
                "Hari     :  Jumaat dan Sabtu\n" +
                "Masa    : 8 Pagi - 11 Malam\n" +
                "\n" +
                "2. Sehubungan dengan itu, para pemilik kenderaan diminta untuk mengosongkan parkir di antara Blok A12 & A13  selewat-lewatnya pada Khamis petang (26 Disember 2024).\n" +
                "\n" +
                "Sekian, Pemakluman.\n" +
                "\n" +
                "KERJASAMA ANDA AMAT DIHARGAI\n" +
                "\n" +
                "Bahagian Pengurusan Keselamatan\n" +
                "UTHM - Kampus Cawangan Pagoh","updated 1 week ago",R.drawable.acap_post,R.drawable.acap_profile));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

        // Exit Message App
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( MainActivity.this);

                alertDialogBuilder.setMessage("Do you want to Exit?");

                alertDialogBuilder.setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAndRemoveTask();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });



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

    private void SendUserToAboutActivity() {
        Intent ProfileIntent = new Intent (MainActivity.this, AboutActivity.class);
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
        if (item.getItemId() == R.id.nav_about) {
            Toast.makeText(MainActivity.this, "About App", Toast.LENGTH_SHORT).show();
            SendUserToAboutActivity();
        }
        if (item.getItemId() == R.id.nav_logout) {
            mAuth.signOut();
            SendUserToLoginActivity();
        }
    }
}