package com.example.meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    EditText email_log, password_log;
    Button login1;

    TextView register;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_log = findViewById(R.id.etEmailAddress);
        password_log = findViewById(R.id.etPassword);
        register = findViewById(R.id.tvRedirectSignUp);
        login1 = findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_log.getText().toString();
                String password = password_log.getText().toString();

                mAuth = FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                                    intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                                    startActivity(intent);

                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Invalid Id/Password", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }

            ;


        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);

            }


        });





    }
}