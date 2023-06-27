package com.example.mhchatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class Account extends AppCompatActivity {
ImageView back;

TextView logout;

AppCompatButton savebtn;

CircleImageView profilePicture;

TextView changePicture;

    private static final int PICK_IMAGE = 1;


EditText emailEditText;
EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Objects.requireNonNull(getSupportActionBar()).hide();


        profilePicture=findViewById(R.id.profile_image);
        EditText editTextFullName = findViewById(R.id.fullnameEdit);
        EditText editTextMobileNo = findViewById(R.id.mobileno);
        EditText editTextAge = findViewById(R.id.Age);
        logout = findViewById(R.id.logout);
        savebtn=findViewById(R.id.SaveBtn);
        changePicture=findViewById(R.id.change_picture);

        profilePicture.setTag("profile_picture_url");


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
// Redirect the user to the login activity
                Toast.makeText(Account.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Account.this, Login.class);
                startActivity(intent);
                finish();

            }
        });


//
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Account.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        String userEmail = user.getEmail();
        String userId=user.getUid();




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference emailRef = database.getReference("users/" + userId + "/email");

        emailRef.setValue(userEmail);


        emailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userEmail = dataSnapshot.getValue(String.class);
                // Do something with the email value, like set it to an EditText in the Edit Profile section
                EditText editText = findViewById(R.id.email);
                editText.setText(userEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
            }
        });




        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("users");



                String ProfilePicture=""; // Retrieve from the appropriate view
                String fullName = ""; // Retrieve from the appropriate view
                String mobileNumber = ""; // Retrieve from the appropriate view
                int age = 0; // Retrieve from the appropriate view

                // Retrieve the values from the views
                ProfilePicture = profilePicture.getTag().toString();
                fullName = editTextFullName.getText().toString();
                mobileNumber = editTextMobileNo.getText().toString();
                age = Integer.parseInt(editTextAge.getText().toString());

                User user = new User(ProfilePicture, fullName, mobileNumber, age,userEmail);
                usersRef.child(userId).setValue(user);

                // Save the data to the database
                usersRef.setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Account.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Account.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Account.this, "Unable to save the data", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });




        changePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePicture();
            }
        });



    }

    private void changePicture() {
        // Create an Intent to open the Gallery or Camera app
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        // Start the Intent
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }


    @Override
    public void onBackPressed() {
       Intent intent=new Intent(Account.this, MainActivity.class);
       startActivity(intent);
       finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the Uri of the selected image
            Uri uri = data.getData();

            try {
                // Load the image into the CircleImageView using Glide library
                Glide.with(this).load(uri).into(profilePicture);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}