package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.ShowNeighbourDetailsEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.BUNDLE_EXTRA_NEIGHBOUR;

/* 3. Notre liste de voisins est matérialisée par un fragment qui contient une RecyclerView.
 * La classe d'un fragment hérite de Fragment afin de récupérer tout le comportement et les propriétés que peut avoir un fragment
 */
public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private static final String IS_FAVORITE = "falseORtrue";
    private boolean isFavoritesPage;


    /**
     * Create and return a new instance
     * Chaque instanciation de notre fragment (= 1 page différente) comporte comme paramètre dans le
     * Bundle un booléen qui indique si la page à afficher est celle des favoris ou non
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(Boolean isFav) {
        NeighbourFragment fragment = new NeighbourFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_FAVORITE, isFav);
        fragment.setArguments(args);
        return fragment;
    }

    /*  A la création du fragment, on crée une instance de l'API qui contient les méthodes pour gérer nos datas*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    /*A la constitution de l'affichage, on met en place la RecyclerView et on lui passe les datas de notre model dans initList()*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        //On récupère le nom de la page à afficher qui a été enreigstré en paramètre de notre instance de fragment
        isFavoritesPage = getArguments().getBoolean(IS_FAVORITE);

        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {

        if (isFavoritesPage) {
            mNeighbours = mApiService.getFavoriteNeighboursFromList();
        } else {
            mNeighbours = mApiService.getNeighbours();
        }
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));

    }

    //Le fragment s'enregistre en tant que receveur d'un event auprès de EventBus
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    //Le fragment doit se désinscrire en tant que receveur auprès de EventBus
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //On a déplacé initList dans onResume() de façon à regénérer (rafraîchir) nos listes en (re)venant sur la Main Activity
    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    //Ci-dessous nos méthodes appelées automatiquement depuis les classe (ou activités) où les events sont déclenchés avec EventBus
    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {

            mApiService.deleteNeighbour(event.neighbour);
            initList();
    }

    @Subscribe
    public void onNeighbourDetails(ShowNeighbourDetailsEvent event) {

        /*Comme notre ViewPager contient 2 instances du NeighbourFragment, on place le contenu de notre méthode uniquement dans un seul
         *fragment (par exemple celui qui contient la liste des voisins favoris) afin qu'il n'y ait pas 2 lancements simultanés
         *de la NeighbourDetailsActivity*/
        if (isFavoritesPage) {
            Intent neighbourDetailsIntent = new Intent(getContext(), NeighbourDetailsActivity.class);
            neighbourDetailsIntent.putExtra(BUNDLE_EXTRA_NEIGHBOUR, event.neighbour);
            startActivity(neighbourDetailsIntent);
        }
    }
}