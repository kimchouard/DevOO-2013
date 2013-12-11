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
        this.vuePlan = new VuePlan();
    }
    
    //--------------------------------
    //  Public functions
    
    /**
     * Affiche le plan à partir du singleton.
     */
    
    public void afficherPlan(JScrollPane panneauPlan) {
        panneauPlan.setViewportView(vuePlan);
        this.vuePlan.updateUI();
    }
    
    public void rafraichirVuePlan() {
        this.vuePlan.reset();
        Plan plan = this.vuePlan.getPlan();
        
        ArrayList<Noeud> noeuds = plan.getNoeuds();
        for (Noeud n : noeuds) {
            this.vuePlan.ajouterNoeud(n);
        }
        
        ArrayList<Troncon> troncons = plan.getTroncons();
        for (Troncon t : troncons) {
            this.vuePlan.ajouterTroncon(t);
        }
    }
    
    public void scaleAutoVuePlan(JScrollPane panneauPlan) {
        Plan p =  vuePlan.getPlan();
        
        double planWidth = p.getMaxX() - p.getMinX() + 2*VuePlan.padding + VuePlan.diamNoeud;
        double planHeight = p.getMaxY() - p.getMinY() + 2*VuePlan.padding + VuePlan.diamNoeud;
        double panneauWidth = panneauPlan.getWidth();
        double panneauHeight = panneauPlan.getHeight();
        double scaleX = panneauWidth / planWidth;
        double scaleY = panneauHeight / planHeight;
        
        if (scaleX < scaleY) {
            vuePlan.setZoomScale(scaleX);
        }
        else {
            vuePlan.setZoomScale(scaleY);
        }
    }
    
    //--------------------------------
    //  Geter - Seter

    public VuePlan getVuePlan() {
        return vuePlan;
    }

    public void setVuePlan(Plan plan) {
        this.vuePlan.setPlan(plan);
        this.vuePlan.setBackground(Color.WHITE);
    }
}
