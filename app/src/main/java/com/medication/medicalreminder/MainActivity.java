package com.medication.medicalreminder;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medication.medicalreminder.addhealthtaker.view.AddHealthTaker;
import com.medication.medicalreminder.databinding.ActivityMainBinding;
import com.medication.medicalreminder.displaymedicine.view.HomeFragment;
import com.medication.medicalreminder.displaymedicine.view.MedecineFragment;
import com.medication.medicalreminder.model.UserPojo;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements PatientAdapterInterface, FriendsAdapterInterface {

    private static final String TAG = "MainActivity";

    //Main Activity Design
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navigationHeaderView;
    private ImageView profileImage;
    private AlertDialog alertDialog;


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

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        profileImage = findViewById(R.id.profile_image_main_user);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You clicked me", Toast.LENGTH_SHORT).show();
            }
        });
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
                    replaceFragment((new UpdatesFragment()));
                    break;
            }
            return true;
        });

     DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //    Log.i(TAG, "onChildAdded: ");
                Log.i(TAG, "onChildAdded: previousChildName " + previousChildName);
                Log.i(TAG, "onChildAdded: snapshot " + snapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.i(TAG, "onChildChanged: previousChildName " + previousChildName);
                Log.i(TAG, "onChildChanged: snapshot.getValue() " + snapshot.getValue());
                Log.i(TAG, "onChildChanged: snapshot.getKey() " + snapshot.getKey());
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.i(TAG, "onChildRemoved: ");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.i(TAG, "onChildMoved: ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "onCancelled: ");
            }
        });


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


        navigationHeaderView = navigationView.getHeaderView(0);
        Log.i(TAG, "onCreate: navigationHeaderView " + navigationHeaderView);
        //Patient
        addNewPatientConstraintLayout = navigationHeaderView.findViewById(R.id.add_new_patient_ConstraintLayout);
        recyclerViewPatients = navigationHeaderView.findViewById(R.id.patients_recycler_view);
        cardViewPatientsAnimation = navigationHeaderView.findViewById(R.id.card_view_patients_recycler_view);
        show_list_arrow_patients = navigationHeaderView.findViewById(R.id.card_view_patients_recycler_view).findViewById(R.id.show_list_arrow_patients);
        linearLayoutVisibilityCheckerPatients = navigationHeaderView.findViewById(R.id.linear_layout_visibility_checker_patient);
        show_list_arrow_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onCreate: onClick before if ");
                if (linearLayoutVisibilityCheckerPatients.getVisibility() == View.GONE) {
                    Log.i(TAG, "onCreate: onClick in if ");
                    Log.i(TAG, "onCreate: linearLayoutVisibilityCheckerPatients in if " + linearLayoutVisibilityCheckerPatients);
                    linearLayoutVisibilityCheckerPatients.setVisibility(View.VISIBLE);
                    patientAdapter = new PatientAdapter(MainActivity.this, patients);
                    LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerViewPatients.setLayoutManager(manager);
                    recyclerViewPatients.setAdapter(patientAdapter);
                    show_list_arrow_patients.setImageResource(R.drawable.ic_up_arrow);
                } else {
                    Log.i(TAG, "onCreate: onClick in else ");
                    linearLayoutVisibilityCheckerPatients.setVisibility(View.GONE);
                    show_list_arrow_patients.setImageResource(R.drawable.ic_down_arrow);
                }
                TransitionManager.beginDelayedTransition(cardViewPatientsAnimation, new AutoTransition());
            }
        });
        addNewPatientConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onCreate: addNewPatientConstraintLayout in else ");
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
        profileNameCurrentUser.setText("Name Clicked");
        profileEmailCurrentUser.setText("Email Clicked");
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


    private void getAllData(DatabaseReference reference) {

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // name[0] = snapshot.getValue(UserPojo.class).getUserName();
                if (snapshot.exists()) {
                    UserPojo userPojo = snapshot.getValue(UserPojo.class);
                    String name = userPojo.getUserName();
                    showAlertDialog(name);
                    Log.i(TAG, "onDataChange: naem-----------------" + name);
                    Log.i(TAG, "getALLDATA: getALLDATA\n userName : " + userPojo.getUserName() + "\tEmail : " + userPojo.getEmail()
                            + "\tPassword : " + userPojo.getPassword() + "\tAccessUID : " + userPojo.getAccessUID());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showAlertDialog(String name) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(name + " Send you invitation to access his list of medicines and care him");
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Success, You can switch account now and see hos list", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You refused invitation of " + name + "To take care of him", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("accessUID").setValue("NULL");
                alertDialog.hide();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}