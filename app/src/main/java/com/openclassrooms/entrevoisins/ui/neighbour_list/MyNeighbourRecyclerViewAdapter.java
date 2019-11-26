package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/* 2. Le RecyclerViewAdapter permet de faire la liaison entre la vue RecyclerView et et notre contrôleur NeighbourFragment*/
public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;

    /*Le constructor prenant en paramètres notre liste de voisins*/
    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    /* Crée un ViewHolder à partir du layout XML représentant chaque ligne de la RecyclerView.
     * Celle-ci est appelée pour les premières lignes visibles à l'écran de la RecyclerView.*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    /* Appelée pour chacune des lignes visibles affichées de notre RecyclerView.
     * C'est généralement ici que l'on met à jour leur apparence.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        //Permet d'afficher dans notre ImageView mNeighbourAvatar une image distante accessible via une URL
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });
    }

    /* Permet de retourner la taille de notre liste d'objet, et ainsi d'indiquer à l'Adapter le nombre
     * de lignes que peut contenir la RecyclerView
     */
    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    /* Modélise en un objet View view la vue XML (fragment_neighbour) de notre ligne représentant un voisin*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar) public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name) public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button) public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
