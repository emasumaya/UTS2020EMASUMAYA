package ac.id.atmaluhur.mahasiswa.uts_amub_ti7jm_1711500022_emasumaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOneActivity extends AppCompatActivity {
    Button bt_next ;
    EditText username, pass, email;

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);
        username = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        email = findViewById(R.id.email);

        bt_next = findViewById(R.id.bt_next);

            //pindah ke registwo
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("User").child(username.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(pass.getText().toString());
                        dataSnapshot.getRef().child("email").setValue(email.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent gotonextregister = new Intent(RegisterOneActivity.this, RegisterTwoActivity.class);
                startActivity(gotonextregister);
            }
        });

        //menyimpan data kepada Localstorage (handphone)
        SharedPreferences sharedPreferences =getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username_key,username.getText().toString()); //ngambil data imputan username disimpan ke variabel username_key
        editor.apply();


    }
}
