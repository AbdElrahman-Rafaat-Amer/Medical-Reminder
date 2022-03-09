package com.medication.medicalreminder;



import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medication.medicalreminder.databinding.ActivityMainBinding;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements PatientAdapterInterface, FriendsAdapterInterface {

    private static final String TAG = "TAG";

    //Main Activity Design
    //private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navigationHeaderView;

    //Current User
    private TextView profileNameCurrentUser, profileEmailCurrentUser;
    private CircleImageView circleImageViewCurrentUser;
    private ImageView changeModeImageView;

    // Patients
    private ConstraintLayout addNewPatientConstraintLayout;
    private ArrayList<Patient> patients = new ArrayList();
    private PatientAdapter patientAdapter;
    private ImageView show_list_arrow_patients;
    private LinearLayout linearLayoutVisibilityCheckerPatients;
    private CardView cardViewPatientsAnimation;
    private RecyclerView recyclerViewPatients;

    // Friends
    private ConstraintLayout addNewFriendConstraintLayout;
    private FriendsAdapter friendsAdapter;
    private RelativeLayout show_list_arrow_Friends;
    private LinearLayout linearLayoutVisibilityCheckerFriends;
    private CardView cardViewFiendsAnimation;
    private RecyclerView recyclerViewFriends;
    private ImageView showListArrowFriendsImage;
    ActivityMainBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //////
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Log.i("TAG", "onCreate: bottomNavigation.setOnItemSelectedListener go to home");
                    replaceFragment((new HomeFragment()));
                    break;
                case R.id.medicine:
                    Log.i("TAG", "onCreate: bottomNavigation.setOnItemSelectedListener go to medicine");
                    replaceFragment((new MedecineFragment()));
                    break;
            }
            return true;
        });



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Database").child("hager");
        reference.setValue("inshalaah");

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            Log.i("TAG", "Name: " + account.getDisplayName());
            Log.i("TAG", "Mail: " + account.getEmail());

        }

        patients.add(new Patient("ahmed@gmail.com", "passwordAhmed", "Ahmed", R.drawable.ic_capsule));
        patients.add(new Patient("abdo@gmail.com", "passwordabdo", "abdo", R.drawable.ic_facebook));
        patients.add(new Patient("anwer@gmail.com", "passwordanwer", "anwer", R.drawable.ic_twitter));
        patients.add(new Patient("hager@gmail.com", "passwordhager", "hager", R.drawable.ic_google));
        patients.add(new Patient("aya@gmail.com", "passwordaya", "aya", R.drawable.ic_capsule));
        patients.add(new Patient("ghada@gmail.com", "passwordghada", "ghada", R.drawable.ic_twitter));
        patients.add(new Patient("asmaa@gmail.com", "passwordasmaa", "asmaa", R.drawable.ic_google));

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
       // toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();
      //   getSupportActionBar().hide();
        navigationHeaderView = navigationView.getHeaderView(0);
        //Patient
        addNewPatientConstraintLayout = navigationHeaderView.findViewById(R.id.add_new_patient_ConstraintLayout);

        recyclerViewPatients = navigationHeaderView.findViewById(R.id.patients_recycler_view);
        cardViewPatientsAnimation = navigationHeaderView.findViewById(R.id.card_view_patients_recycler_view);
        show_list_arrow_patients = navigationHeaderView.findViewById(R.id.show_list_arrow_patients);
        linearLayoutVisibilityCheckerPatients = navigationHeaderView.findViewById(R.id.linear_layout_visibility_checker_patient);
        show_list_arrow_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutVisibilityCheckerPatients.getVisibility() == View.GONE) {
                    linearLayoutVisibilityCheckerPatients.setVisibility(View.VISIBLE);
                    patientAdapter = new PatientAdapter(MainActivity.this, patients);
                    LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerViewPatients.setLayoutManager(manager);
                    recyclerViewPatients.setAdapter(patientAdapter);
                    show_list_arrow_patients.setImageResource(R.drawable.ic_up_arrow);
                } else {
                    linearLayoutVisibilityCheckerPatients.setVisibility(View.GONE);
                    show_list_arrow_patients.setImageResource(R.drawable.ic_down_arrow);
                }
                TransitionManager.beginDelayedTransition(cardViewPatientsAnimation, new AutoTransition());
            }
        });
        addNewPatientConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });


        //Friends
        addNewFriendConstraintLayout = navigationHeaderView.findViewById(R.id.add_new_friends_ConstraintLayout);
        recyclerViewFriends = navigationHeaderView.findViewById(R.id.friends_recycler_view);
        cardViewFiendsAnimation = navigationHeaderView.findViewById(R.id.card_view_friends_recycler_view);
        show_list_arrow_Friends = navigationHeaderView.findViewById(R.id.show_list_arrow_friends);
        linearLayoutVisibilityCheckerFriends = navigationHeaderView.findViewById(R.id.linear_layout_visibility_checker_friends);
        showListArrowFriendsImage = navigationHeaderView.findViewById(R.id.show_list_arrow_friends_image);
        show_list_arrow_Friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutVisibilityCheckerFriends.getVisibility() == View.GONE) {
                    linearLayoutVisibilityCheckerFriends.setVisibility(View.VISIBLE);
                    friendsAdapter = new FriendsAdapter(MainActivity.this, patients);
                    LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerViewFriends.setLayoutManager(manager);
                    recyclerViewFriends.setAdapter(friendsAdapter);
                    showListArrowFriendsImage.setImageResource(R.drawable.ic_up_arrow);
                } else {
                    linearLayoutVisibilityCheckerFriends.setVisibility(View.GONE);
                    showListArrowFriendsImage.setImageResource(R.drawable.ic_down_arrow);
                }
                TransitionManager.beginDelayedTransition(cardViewFiendsAnimation, new AutoTransition());
            }
        });
        addNewFriendConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        //CurrentUser
        circleImageViewCurrentUser = navigationHeaderView.findViewById(R.id.circleImageView_current_user);
        profileNameCurrentUser = navigationHeaderView.findViewById(R.id.profile_name_current_user);
        profileEmailCurrentUser = navigationHeaderView.findViewById(R.id.profile_email_current_user);
        changeModeImageView = navigationHeaderView.findViewById(R.id.circleImageView_mode_application);

        changeModeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    // on below line we are changing the theme to light mode.
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //  changeModeImageView.setImageResource(R.drawable.ic_facebook);
                } else {
                    // on below line we are changing the theme to dark mode.
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    // changeModeImageView.setImageResource(R.drawable.ic_twitter);
                }
            }
        });


    /*    navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Patient
                addNewPatientConstraintLayout = v.findViewById(R.id.add_new_patient_ConstraintLayout);
                recyclerViewPatients = findViewById(R.id.patients_recycler_view);
                cardViewPatientsAnimation = v.findViewById(R.id.card_view_patients_recycler_view);
                show_list_arrow_patients = v.findViewById(R.id.show_list_arrow_patients);
                linearLayoutVisibilityCheckerPatients = v.findViewById(R.id.linear_layout_visibility_checker_patient);
                show_list_arrow_patients.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (linearLayoutVisibilityCheckerPatients.getVisibility() == View.GONE) {
                            linearLayoutVisibilityCheckerPatients.setVisibility(View.VISIBLE);
                            patientAdapter = new PatientAdapter(MainActivity.this, patients);
                            LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerViewPatients.setLayoutManager(manager);
                            recyclerViewPatients.setAdapter(patientAdapter);
                            show_list_arrow_patients.setImageResource(R.drawable.ic_up_arrow);
                        } else {
                            linearLayoutVisibilityCheckerPatients.setVisibility(View.GONE);
                            show_list_arrow_patients.setImageResource(R.drawable.ic_down_arrow);
                        }
                        TransitionManager.beginDelayedTransition(cardViewPatientsAnimation, new AutoTransition());
                    }
                });
                addNewPatientConstraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                });


                //Friends
                addNewFriendConstraintLayout = v.findViewById(R.id.add_new_friends_ConstraintLayout);
                recyclerViewFriends = findViewById(R.id.friends_recycler_view);
                cardViewFiendsAnimation = v.findViewById(R.id.card_view_friends_recycler_view);
                show_list_arrow_Friends = v.findViewById(R.id.show_list_arrow_friends);
                linearLayoutVisibilityCheckerFriends = v.findViewById(R.id.linear_layout_visibility_checker_friends);
                showListArrowFriendsImage = v.findViewById(R.id.show_list_arrow_friends_image);
                show_list_arrow_Friends.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (linearLayoutVisibilityCheckerFriends.getVisibility() == View.GONE) {
                            linearLayoutVisibilityCheckerFriends.setVisibility(View.VISIBLE);
                            friendsAdapter = new FriendsAdapter(MainActivity.this, patients);
                            LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerViewFriends.setLayoutManager(manager);
                            recyclerViewFriends.setAdapter(friendsAdapter);
                            showListArrowFriendsImage.setImageResource(R.drawable.ic_up_arrow);
                        } else {
                            linearLayoutVisibilityCheckerFriends.setVisibility(View.GONE);
                            showListArrowFriendsImage.setImageResource(R.drawable.ic_down_arrow);
                        }
                        TransitionManager.beginDelayedTransition(cardViewFiendsAnimation, new AutoTransition());
                    }
                });
                addNewFriendConstraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                    }
                });

                //CurrentUser
                circleImageViewCurrentUser = v.findViewById(R.id.circleImageView_current_user);
                profileNameCurrentUser = v.findViewById(R.id.profile_name_current_user);
                profileEmailCurrentUser = v.findViewById(R.id.profile_email_current_user);
                changeModeImageView = v.findViewById(R.id.circleImageView_mode_application);

                changeModeImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                            // on below line we are changing the theme to light mode.
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            //  changeModeImageView.setImageResource(R.drawable.ic_facebook);
                        } else {
                            // on below line we are changing the theme to dark mode.
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            // changeModeImageView.setImageResource(R.drawable.ic_twitter);
                        }
                    }
                });

            }
        });*/






    }


    private void replaceFragment( Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
      /*  if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            // on below line we are changing the theme to light mode.
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            changeModeImageView.setImageResource(R.drawable.ic_facebook);
        } else {
            // on below line we are changing the theme to dark mode.
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            changeModeImageView.setImageResource(R.drawable.ic_twitter);
        }*/
    }

    @Override
    public void changeUser(Patient patient) {
        Log.i(TAG, "changeUser: " + patient);

        Log.i(TAG, "changeUser: " + circleImageViewCurrentUser);
        Log.i(TAG, "changeUser: " + profileEmailCurrentUser);
        Log.i(TAG, "changeUser: " + profileNameCurrentUser);

        circleImageViewCurrentUser.setImageResource(patient.getProfileImageTest());
        profileEmailCurrentUser.setText(patient.getEmail());
        profileNameCurrentUser.setText(patient.getName());
    }


    @Override
    public void changeFriend(Patient patient) {
        Log.i(TAG, "changeUser: " + patient);

        Log.i(TAG, "changeUser: " + circleImageViewCurrentUser);
        Log.i(TAG, "changeUser: " + profileEmailCurrentUser);
        Log.i(TAG, "changeUser: " + profileNameCurrentUser);

        circleImageViewCurrentUser.setImageResource(patient.getProfileImageTest());
        profileEmailCurrentUser.setText(patient.getEmail());
        profileNameCurrentUser.setText(patient.getName());
    }
}