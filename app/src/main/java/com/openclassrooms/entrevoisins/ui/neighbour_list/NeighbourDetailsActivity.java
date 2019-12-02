package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

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

    private Neighbour myNeighbour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);

        myNeighbour = (Neighbour) getIntent().getSerializableExtra(BUNDLE_EXTRA_NEIGHBOUR);

        mHeaderFristname.setText(myNeighbour.getName());
        mCardViewFristname.setText(myNeighbour.getName());
        Glide.with(this).load(myNeighbour.getAvatarUrl()).into(mHeaderAvatar);


    }
}
