package com.example.enzo.guiaestudiobiologia;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class ModifyGuiaBiologiaActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;

    TextInputEditText description, relevant_fac;
    Button btn_modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_guia_biologia);

        description= (TextInputEditText)findViewById(R.id.mod_description) ;
        relevant_fac= (TextInputEditText)findViewById(R.id.mod_relevant_fact) ;

        String modDescription = getIntent().getStringExtra("description");
        String mRelevant = getIntent().getStringExtra("relevant");

        final String keyDB = getIntent().getStringExtra("keyDB");

        description.setText(modDescription);
        relevant_fac.setText(mRelevant);

        btn_modify = (Button) findViewById(R.id.mod_btn_submit);

        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descrip = description.getText().toString().trim();
                String relFac  = relevant_fac.getText().toString().trim();

                Toast.makeText(v.getContext(),descrip,Toast.LENGTH_LONG);
                Toast.makeText(v.getContext(),relFac,Toast.LENGTH_LONG);

//                mDatabaseReference.child("users")
//                        .child("40")
//                        .child("guias")
//                        .child(keyDB)
//                        .child("description_guia")
//                        .push().setValue(descrip);
//
//                mDatabaseReference.child("users")
//                        .child("40")
//                        .child("guias")
//                        .child(keyDB)
//                        .child("relevant_fact_1")
//                        .push().setValue(relFac);

            }
        });

    }

}
