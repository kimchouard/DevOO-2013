/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Noeud;
import devoo.h4301.model.Plan;
import devoo.h4301.model.Tournee;
import devoo.h4301.model.Troncon;
import devoo.h4301.views.FenetrePrincipale;
import devoo.h4301.views.VueNoeud;
import devoo.h4301.views.VuePlan;
import devoo.h4301.views.VueTroncon;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

/**
 *
 * @author chouard
 */
public class ControleurPlan {
    private VuePlan vuePlan;

    public ControleurPlan() {
        this.setVuePlan(new VuePlan());
        this.vuePlan.setBackground(Color.WHITE);
    }
    
    //--------------------------------
    //  Public functions
    
    /**
     * Affiche le plan à partir du singleton.
     */
    
    public void afficherPlan(JScrollPane panneauPlan) {
        panneauPlan.setViewportView(vuePlan);
    }
    
    public void rafraichirVuePlan(Plan plan) {
        this.vuePlan.reset();
        
        ArrayList<Noeud> noeuds = plan.getNoeuds();
        for (Noeud n : noeuds) {
            this.vuePlan.ajouterNoeud(n);
        }
        
        ArrayList<Troncon> troncons = plan.getTroncons();
        for (Troncon t : troncons) {
            this.vuePlan.ajouterTroncon(t);
        }
    }
    
    //--------------------------------
    //  Geter - Seter

    public VuePlan getVuePlan() {
        return vuePlan;
    }

    public void setVuePlan(VuePlan vuePlan) {
        this.vuePlan = vuePlan;
    }
}
