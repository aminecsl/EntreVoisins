package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/* 1. Notre application se lance sur une première activity composée d'un TabLayout à deux onglets et d'un ViewPager afin d'avoir
 * une navigation horizontale sur deux pages.
 */
public class ListNeighbourActivity extends AppCompatActivity {

    /* Nous disons à ButterKnife de créer à notre place les différents findViewByID() et assignons ces différentes vues à des
     * variables
     */
    @BindView(R.id.tabs) TabLayout mTabLayout;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.container) ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);

        /*On crée une instance de ButterKnife afin d'activer ce processus de récupération des vues de notre layout
         * grâce à la méthode bind()
         */
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //On crée une instance de notre ViewPager Adapter
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        //On l'injecte dans notre ViewPager
        mViewPager.setAdapter(mPagerAdapter);
        //Met à jour l'onglet courant en glissant les pages avec son doigt
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //Affiche la page désirée en appuyant sur l'onglet correspondant
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
}