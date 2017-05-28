package com.example.enzo.guiaestudiobiologia;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ModifyGuiaBiologiaActivity extends AppCompatActivity {

    //REFERENCE to Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();

    TextInputEditText tvdescription, tvrelevant_fac;

    private static Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_guia_biologia);
        mcontext = this;

        tvdescription = (TextInputEditText)findViewById(R.id.mod_description) ;
        tvrelevant_fac = (TextInputEditText)findViewById(R.id.mod_relevant_fact) ;
        Button btn_update = (Button) findViewById(R.id.mod_btn_submit);

        String modDescription = getIntent().getStringExtra("description");
        String mRelevant = getIntent().getStringExtra("relevant");

        final String keyDB = getIntent().getStringExtra("keyDB");

        tvdescription.setText(modDescription);
        tvrelevant_fac.setText(mRelevant);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String des = tvdescription.getText().toString().trim();
                String rel = tvrelevant_fac.getText().toString().trim();

                String taskId = keyDB;

                mDatabaseReference
                        .child("users")
                        .child("40")
                        .child("guias").child(keyDB).child("description_guia").setValue(des);

                mDatabaseReference
                        .child("users")
                        .child("40")
                        .child("guias").child(keyDB).child("relevant_fact_1").setValue(rel);

                Intent intent = new Intent(mcontext, GuiaBiologiaMain.class);
                mcontext.startActivity(intent);
            }
        });

    }

}
