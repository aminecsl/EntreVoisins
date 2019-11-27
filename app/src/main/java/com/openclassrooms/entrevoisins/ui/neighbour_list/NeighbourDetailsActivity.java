package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.BUNDLE_EXTRA_NEIGHBOUR;

public class NeighbourDetailsActivity extends AppCompatActivity {

    public TextView mHeaderFristname;
    public TextView mCardViewFristname;

    private Neighbour myNeighbour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);

        myNeighbour = (Neighbour) getIntent().getSerializableExtra(BUNDLE_EXTRA_NEIGHBOUR);

        mHeaderFristname = (TextView)findViewById(R.id.neighbour_details_firstname_txt);
        mCardViewFristname = (TextView)findViewById(R.id.contacts_cardview_firstname_txt);

        mHeaderFristname.setText(myNeighbour.getName());
        mCardViewFristname.setText(myNeighbour.getName());
    }
}
