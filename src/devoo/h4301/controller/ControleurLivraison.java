/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.views.VueListLivraison;
import devoo.h4301.model.Livraison;

/**
 *
 * @author Mimi
 */
public class ControleurLivraison {
    
    private VueListLivraison vueLivraison;
    
     public ControleurLivraison() {
        this.vueLivraison = new VueListLivraison();
    }
     
    public VueListLivraison getVueLivraison(){
        return vueLivraison;
    }
    
    public void setVueLivraison(Livraison liv){
        this.vueLivraison.setLivraison(liv);
    }
     
}
