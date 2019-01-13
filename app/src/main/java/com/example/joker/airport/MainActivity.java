package com.example.joker.airport;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    EditText emailText,passText;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        emailText=findViewById(R.id.emailId);
        passText=findViewById(R.id.passId);
        login=findViewById(R.id.button);
        mUser=mAuth.getCurrentUser();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(emailText.getText().toString().trim()) || TextUtils.isEmpty(passText.getText().toString().trim())){
                    Toast.makeText(MainActivity.this, "Fields Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.signInWithEmailAndPassword(emailText.getText().toString().trim(),passText.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                            }
                            else
                                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
