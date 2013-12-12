/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.views.VueListLivraison;
import devoo.h4301.model.Livraison;
import javax.swing.JScrollPane;

/**
 *
 * @author Mimi
 */
public class ControleurLivraison {
    
    private VueListLivraison vueListLivraison;
    
     public ControleurLivraison() {
        this.vueListLivraison = new VueListLivraison();
    }
     
     public void afficherListLivraison(JScrollPane listLiv) {
        listLiv.setViewportView(vueListLivraison);
        this.vueListLivraison.updateUI();
    }
     
     public VueListLivraison getVueListLisvraison() {
        return vueListLivraison;
    }

    // Passer un tableau en paramètre ?
     /*
    public void setVueListLivraison() {
        this.vueListLivraison.setListLivraison();
        this.vueListLivraison.setBackground(Color.WHITE);
    }*/
     
}
