package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.BUNDLE_EXTRA_NEIGHBOUR;

public class NeighbourDetailsActivity extends AppCompatActivity {

    @BindView(R.id.neighbour_details_firstname_txt) TextView mHeaderFristname;
    @BindView(R.id.contacts_cardview_firstname_txt) TextView mCardViewFristname;
    @BindView(R.id.neighbour_details_avatar_img) ImageView mHeaderAvatar;
    @BindView(R.id.neighbour_details_favorite_btn) FloatingActionButton mChangeFavoriteBtn;

    private Neighbour myNeighbour;
    private NeighbourApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        myNeighbour = (Neighbour) getIntent().getSerializableExtra(BUNDLE_EXTRA_NEIGHBOUR);

        mHeaderFristname.setText(myNeighbour.getName());
        mCardViewFristname.setText(myNeighbour.getName());
        Glide.with(this).load(myNeighbour.getAvatarUrl()).into(mHeaderAvatar);

        //Check for the star color to display either if the current neighbour is a favorite or not
        setStarColor();

        mChangeFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Met à jour l'attribut isFavorite de ce neighbour dans la liste des voisins du model (ApiServiceGenerator)
                mApiService.changeFavoriteStatus(myNeighbour);

                //Met à jour cet attribut dans l'objet de ce voisin manipulé dans l'activité actuel pour rafraîchir la vue
                myNeighbour.setFavorite(!myNeighbour.isFavorite());

                //Refresh the star color afer clicked
                setStarColor();

            }
        });

    }

    private void setStarColor (){
        if (myNeighbour.isFavorite()){
            mChangeFavoriteBtn.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            mChangeFavoriteBtn.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ListNeighbourActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DEBUG", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DEBUG", "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DEBUG", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DEBUG", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DEBUG", "onDestroy: ");
    }

}
