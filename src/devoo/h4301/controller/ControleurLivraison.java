/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.views.VueListLivraison;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Tournee;
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
     
     public int supprLiv(Livraison liv) {
         //To do parcourir le tableau et reoutrner 0 on delete
         for (int  i = 0; i < this.vueListLivraison.getVueLivraison().size() ; i++){
             if (this.vueListLivraison.getVueLivraison().get(i).getLivraison().equals(liv))
             {
                 this.vueListLivraison.getVueLivraison().remove(liv);
                 return 0;
             }
         }
         return 1;
     }

    // Passer un tableau en paramètre ?
    
    public void setVueListLivraison(Tournee tournee) {
        this.vueListLivraison.setVueLivraison(tournee.getLivraisons());
    }
    
     
}
