package com.example.enzo.guiaestudiobiologia;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class GuiaBiologiaMain extends AppCompatActivity {

    //PROPERTIES
    private FloatingActionButton fab;
    ScaleAnimation shrinkAnim;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private TextView tvNoMovies;

    //REFERENCE to Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();

    private static Context mcontext;


    private static final String userId = "40";
    //end Properties


    //start onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia_biologia_main);

        mcontext = this;

        //Initializing our Recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_main);
        tvNoMovies = (TextView) findViewById(R.id.guia_vacia);
        //scale animation to shrink floating actionbar
        shrinkAnim = new ScaleAnimation(1.15f, 0f, 1.15f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (mRecyclerView != null) {
            //to enable optimization of recyclerview
            mRecyclerView.setHasFixedSize(true);
        }
        //using staggered grid pattern in recyclerview
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FirebaseRecyclerAdapter<Guia,GuiaViewHolder> adapter = new FirebaseRecyclerAdapter<Guia, GuiaViewHolder>(
                Guia.class,
                R.layout.guia_item,
                GuiaViewHolder.class,
                //referencing the node where we want the database to store the data from our Object
                mDatabaseReference.child("users").child(userId).child("guias").getRef()
        ) {
            @Override
            protected void populateViewHolder(final GuiaViewHolder viewHolder, Guia model, int position) {
                if(tvNoMovies.getVisibility()== View.VISIBLE){
                    tvNoMovies.setVisibility(View.GONE);
                }
                // load values from Firebase
                viewHolder.tvGuiaDescription.setText(model.getDescription_guia());
                viewHolder.tvRelevantFac1.setText(model.getRelevant_fact_1());
                viewHolder.tvRelevantFac2.setText(model.getRelevant_fact_2());
                Picasso.with(GuiaBiologiaMain.this).load(model.getImage_guia()).into(viewHolder.ivGuiaPoster);

                final String modKeyDB = getRef(position).getKey(); // get key branch
                final String modRelevant = model.getRelevant_fact_1(); // get value relevant_fact_1
                final String modRelevant2 = model.getRelevant_fact_2(); // get value relevant_fact_2


                viewHolder.viewVH.setOnClickListener(new View.OnClickListener() {
                    String relevant =  modRelevant
                            ,relevant2 =  modRelevant2 ;

                    @Override
                    public void onClick(View v) {
                        String descrip = viewHolder.tvGuiaDescription.getText().toString();

                        // submit values to Modify layout activity
                        Intent intent = new Intent(mcontext, ModifyGuiaBiologiaActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("description",descrip );
                        bundle.putString("relevant", relevant);
                        bundle.putString("relevant2", relevant2);
                        bundle.putString("keyDB", modKeyDB);
                        intent.putExtra("description",descrip);
                        intent.putExtra("relevant",relevant);
                        intent.putExtra("relevant2",relevant2);
                        intent.putExtra("keyDB",modKeyDB);

                        mcontext.startActivity(intent);

                    }
                });

            }
        };

        mRecyclerView.setAdapter(adapter);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new AddGuiaFragment())
                        .addToBackStack(null)
                        .commit();
                //animation being used to make floating actionbar disappear
                shrinkAnim.setDuration(400);
                fab.setAnimation(shrinkAnim);
                shrinkAnim.start();
                shrinkAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //changing floating actionbar visibility to gone on animation end
                        fab.setVisibility(View.GONE);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        });
    }
    //END onCreate


    //start onBackPressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fab.getVisibility() == View.GONE)
            fab.setVisibility(View.VISIBLE);
    }
    //end onBackPressed


    //ViewHolder for our Firebase UI
    public static class GuiaViewHolder extends RecyclerView.ViewHolder{
        TextView tvGuiaDescription
                ,tvRelevantFac1
                ,tvRelevantFac2;
        ImageView ivGuiaPoster;

        View viewVH;

        public GuiaViewHolder(View v) {
            super(v);
            tvGuiaDescription = (TextView) v.findViewById(R.id.guia_name);
            tvRelevantFac1 = (TextView) v.findViewById(R.id.guia_relevant1);
            tvRelevantFac2 = (TextView) v.findViewById(R.id.guia_relevant2);
            ivGuiaPoster = (ImageView) v.findViewById(R.id.iv_guia_poster);

            viewVH = v;

        }

    }
}
