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

    TextInputEditText tvdescription, tvrelevant_fac;//, tvrelevant_fac2;
    TextInputEditText tvDbRel1, tvDbRel2;//, tvrelevant_fac2;

    private static Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_guia_biologia);
        mcontext = this;

        tvdescription = (TextInputEditText)findViewById(R.id.mod_description) ;
        tvrelevant_fac = (TextInputEditText)findViewById(R.id.mod_relevant_fact) ;
//        tvrelevant_fac2 = (TextInputEditText)findViewById(R.id.mod_relevant_fact2) ;
        Button btn_update = (Button) findViewById(R.id.mod_btn_submit);

        tvDbRel1 = (TextInputEditText)findViewById(R.id.db_mod_relevant_fact) ;
        tvDbRel2 = (TextInputEditText)findViewById(R.id.db_mod_relevant_fact2) ;


        String modDescription = getIntent().getStringExtra("description");
        final String mRelevant = getIntent().getStringExtra("relevant"); //1
        final String mRelevant2 = getIntent().getStringExtra("relevant2"); //2

        final String keyDB = getIntent().getStringExtra("keyDB");

        //load values
        tvdescription.setText(modDescription);
//        tvrelevant_fac.setText(mRelevant);

        tvDbRel1.setText(mRelevant);
        tvDbRel2.setText(mRelevant2);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get extras from guia main layout
                String rel_1 = getIntent().getStringExtra("relevant"); //1
                String rel_2 = getIntent().getStringExtra("relevant2"); //2

                //get values from modify guia layout
                String des = tvdescription.getText().toString().trim();
                String rel = tvrelevant_fac.getText().toString().trim(); // 1

                // changed values: relevant fact old -> new (max 2 relevant fact)
                String rel_1_help = rel_2;
                rel_2 = rel;
                rel_1 = rel_1_help;

                String taskId = keyDB;

                // update branch: description_guia; relevant_fact_1; relevant_fact_2
                mDatabaseReference
                        .child("users")
                        .child("40")
                        .child("guias").child(keyDB).child("description_guia").setValue(des);

                mDatabaseReference
                        .child("users")
                        .child("40")
                        .child("guias").child(keyDB).child("relevant_fact_1").setValue(rel_1);

                mDatabaseReference
                        .child("users")
                        .child("40")
                        .child("guias").child(keyDB).child("relevant_fact_2").setValue(rel_2);

                // go back main layout activity
                Intent intent = new Intent(mcontext, GuiaBiologiaMain.class);
                mcontext.startActivity(intent);
            }
        });

    }

}
