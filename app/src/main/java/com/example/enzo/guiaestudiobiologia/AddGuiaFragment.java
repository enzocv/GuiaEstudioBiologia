package com.example.enzo.guiaestudiobiologia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Asus on 27/05/2017.
 */

public class AddGuiaFragment extends Fragment implements View.OnClickListener{

    //PROPERTIES
    private DatabaseReference mDatabaseReference;
    private TextInputEditText guiaDescription;
    private TextInputEditText guiaRelevantFact1;
    private TextInputEditText guiaLogo;
    private Button bSubmit;
    //END PROPERTIES

    //START ONCREATEVIEW
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.guia_fragment,container,false);
        guiaDescription = (TextInputEditText) v.findViewById(R.id.guia_description);
        guiaRelevantFact1 = (TextInputEditText) v.findViewById(R.id.relevant_fact_1);
        guiaLogo = (TextInputEditText) v.findViewById(R.id.url_guia);
        bSubmit = (Button) v.findViewById(R.id.btn_submit);
        //initializing database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        bSubmit.setOnClickListener(this);
        return v;
    }
    //END ONCREATEVIEW

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_submit:
                if(!isEmpty(guiaDescription) && !isEmpty(guiaDescription)){
                    myNewGuia("40"
                            ,guiaDescription.getText().toString().trim()
                            ,guiaRelevantFact1.getText().toString().trim()
                            ,guiaLogo.getText().toString()
                    );
                }else{
                    if(isEmpty(guiaDescription)){
                        Toast.makeText(getContext(), "Ingrese un nombre para la Guía!", Toast.LENGTH_SHORT).show();
                    }else if(isEmpty(guiaLogo)){
                        Toast.makeText(getContext(), "Ingrese una URL para la imagen de la Guía", Toast.LENGTH_SHORT).show();
                    }
                }
                //to remove current fragment
                getActivity().onBackPressed();
                break;
        }
    }

    private void myNewGuia(String userId, String guiaDescription, String guiaRelevantFact1, String guiaLogo) {
        //Creating a movie object with user defined variables
        Guia guia = new Guia(guiaDescription, guiaRelevantFact1, guiaLogo);
        //referring to movies node and setting the values from movie object to that location
        mDatabaseReference.child("users").child(userId).child("guias").push().setValue(guia);

        String keyDB = mDatabaseReference.child("users").child(userId).child("guias").getKey();

        Toast.makeText(getContext(),keyDB, Toast.LENGTH_SHORT).show();
    }

    //check if edittext is empty
    private boolean isEmpty(TextInputEditText textInputEditText) {
        if (textInputEditText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

}
