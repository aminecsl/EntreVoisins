package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/* 4. Sert à faire le lien entre notre ViewPager et ses pages (matérialisée sous la forme d'un fragment NeighbourFragment
 * Cet adapter va garder en mémoire toutes les pages affichées (fragments) après leur création.
 * Cela permettra une plus grande fluidité lors du défilement et de l'affichage de ces derniers.
 */
public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    /* Le FragmentManager est utilisé pour gérer les fragments au sein de la classe mère FragmentPagerAdapter*/
    public ListNeighbourPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * Appelée dès qu'une page (fragment) demandera à être affichée.
     * @param position (commence à l'index 0)
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        //Si on veut afficher le 2ème onglet, alors on demande une instance de notre fragment qui portera comme nom de page "favorite neighbours"
        if (position == 1) {

            return NeighbourFragment.newInstance("favorite neighbours");
        } else {
            return NeighbourFragment.newInstance("all neighbours");
        }
    }

    /**
     * set the number of pages we want to display within the ViewPager
     * @return
     */
    @Override
    public int getCount() {

        return 2;
    }
}