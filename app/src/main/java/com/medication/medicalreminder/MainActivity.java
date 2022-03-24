package com.medication.medicalreminder;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medication.medicalreminder.displaymedicine.view.HomeFragment;
import com.medication.medicalreminder.displaymedicine.view.MedecineFragment;
import com.medication.medicalreminder.healthtaker.view.AddHealthTaker;
import com.medication.medicalreminder.databinding.ActivityMainBinding;
import com.medication.medicalreminder.healthtaker.view.EditHealthTakerRequest;
import com.medication.medicalreminder.healthtaker.displayMedicine.view.HealthTakerFragment;
import com.medication.medicalreminder.model.UserPojo;
import com.medication.medicalreminder.reminder.NotficationHealthTaker;

import androidx.work.OneTimeWorkRequest;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //Main Activity Design
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navigationHeaderView;
    private ImageView profileImage;
    private TextView textViewCurrentUser;


    //Current User
    private TextView profileNameCurrentUser, profileEmailCurrentUser;
    private CircleImageView circleImageViewCurrentUser;
    private ImageView changeModeImageView;
    private ArrayList<UserPojo> users = new ArrayList();
    private AccountAdapter patientAdapter;
    private RelativeLayout show_list_arrow_patients;
    private ImageView showListArrowUsersImage;
    private LinearLayout linearLayoutVisibilityCheckerPatients;
    private CardView cardViewPatientsAnimation;
    private RecyclerView recyclerViewPatients;

    // Friends
    private ConstraintLayout addNewFriendConstraintLayout;
    private RelativeLayout show_list_arrow_Friends;
    private LinearLayout linearLayoutVisibilityCheckerFriends;
    private CardView cardViewFiendsAnimation;
    private RecyclerView recyclerViewFriends;
    private ImageView showListArrowFriendsImage;
    private FriendsAdapter friendsAdapter;
    private ArrayList<UserPojo> friends = new ArrayList();
    private UserPojo currentUser = new UserPojo();
    ActivityMainBinding binding;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentUser = snapshot.getValue(UserPojo.class);
                textViewCurrentUser.setText(currentUser.getUserName());
                profileNameCurrentUser.setText(currentUser.getUserName());
                profileEmailCurrentUser.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                users.add(currentUser);
                if (!currentUser.getRequesterName().equals("NULL")) {
                    friends.add(currentUser);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                case R.id.updates:
                    replaceFragment((new HealthTakerFragment()));
                    break;
            }
            return true;
        });

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        navigationHeaderView = navigationView.getHeaderView(0);
        profileImage = findViewById(R.id.profile_pic);
        textViewCurrentUser = findViewById(R.id.profile_text);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.open();
            }
        });


        checkForHealthTaker();

        //Accounts
        recyclerViewPatients = navigationHeaderView.findViewById(R.id.patients_recycler_view);
        cardViewPatientsAnimation = navigationHeaderView.findViewById(R.id.card_view_patients_recycler_view);
        showListArrowUsersImage = navigationHeaderView.findViewById(R.id.show_list_arrow_users_image);
        linearLayoutVisibilityCheckerPatients = navigationHeaderView.findViewById(R.id.linear_layout_visibility_checker_patient);
        show_list_arrow_patients = navigationHeaderView.findViewById(R.id.show_list_arrow_patients);
        show_list_arrow_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutVisibilityCheckerPatients.getVisibility() == View.GONE) {
                    linearLayoutVisibilityCheckerPatients.setVisibility(View.VISIBLE);
                    patientAdapter = new AccountAdapter(MainActivity.this, users);
                    LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerViewPatients.setLayoutManager(manager);
                    recyclerViewPatients.setAdapter(patientAdapter);
                    showListArrowUsersImage.setImageResource(R.drawable.ic_up_arrow);
                } else {
                    linearLayoutVisibilityCheckerPatients.setVisibility(View.GONE);
                    showListArrowUsersImage.setImageResource(R.drawable.ic_down_arrow);
                }
                TransitionManager.beginDelayedTransition(cardViewPatientsAnimation, new AutoTransition());
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
                    friendsAdapter = new FriendsAdapter(MainActivity.this, friends);
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
                Log.i(TAG, "onClick: inviteFriends going to AddHealthTaker");
                startActivity(new Intent(MainActivity.this, AddHealthTaker.class));
            }
        });


        //CurrentUser
        circleImageViewCurrentUser = navigationHeaderView.findViewById(R.id.circleImageView_current_user);
        profileNameCurrentUser = navigationHeaderView.findViewById(R.id.profile_name_current_user);
        profileEmailCurrentUser = navigationHeaderView.findViewById(R.id.profile_email_current_user);

        circleImageViewCurrentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Cirecle Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        changeModeImageView = navigationHeaderView.findViewById(R.id.circleImageView_mode_application);

        changeModeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    // on below line we are changing the theme to light mode.
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    // on below line we are changing the theme to dark mode.
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });

    }

    private void checkForHealthTaker() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserPojo userPojo = snapshot.getValue(UserPojo.class);
                if (userPojo.getRequestReply().equals("False")) {
                    if (!userPojo.getAccessUID().equals("NULL")) {
                        EditHealthTakerRequest.senderName = userPojo.getRequesterName();
                        OneTimeWorkRequest reminderRequest = new OneTimeWorkRequest.Builder(NotficationHealthTaker.class)
                                .build();
                        androidx.work.WorkManager.getInstance(MainActivity.this).enqueue(reminderRequest);

                    } else {
                        Log.i(TAG, "onDataChange: userPojo.getAccessUID() " + userPojo.getAccessUID());
                    }
                } else {
                    Log.i(TAG, "onDataChange: userPojo.getRequestReply() " + userPojo.getRequestReply());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        users.clear();
        friends.clear();
    }
}