/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Itineraire;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Noeud;
import devoo.h4301.model.Plan;
import devoo.h4301.model.Tournee;
import devoo.h4301.model.Troncon;
import devoo.h4301.views.FenetrePrincipale;
import devoo.h4301.views.VueNoeud;
import devoo.h4301.views.VuePlan;
import devoo.h4301.views.VueTroncon;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

/**
 *
 * @author chouard
 */
public class ControleurPlan {
    private ControleurPrincipal controleurPrincipal;
    
    private VuePlan vuePlan;

    public ControleurPlan(ControleurPrincipal controleurPrincipal) {
        this.vuePlan = new VuePlan(this);
        this.controleurPrincipal = controleurPrincipal;
    }
    
    //--------------------------------
    //  Public functions
    
    /**
     * Lie la vue plan avec le scroll pannel de la fenetre principale.
     */
    public void afficherPlan(JScrollPane panneauPlan) {
        panneauPlan.setViewportView(vuePlan);
        this.vuePlan.updateUI();
    }
    
    public void rafraichirVuePlan(Tournee tournee, JScrollPane panneauPlan) {
        this.vuePlan = new VuePlan(this);
        this.vuePlan.setTournee(tournee);
        Plan plan =  tournee.getPlan();
        
        this.scaleAutoVuePlan(panneauPlan);
        
        ArrayList<Noeud> noeuds = plan.getNoeuds();
        for (Noeud n : noeuds) {
            this.vuePlan.ajouterNoeud(n);
        }
        
//        ArrayList<Livraison> livs =  controleurPrincipal.getControleurGraph().getLivOrdered();
        LinkedList<Livraison> livs =  tournee.getLivraisons();
        int start = 0;
        for (Livraison l : livs) {
            //Si c'est l'entrepot
            if (start == 0) {
                this.vuePlan.ajouterEntrepot(l);
                start++;
            } else {
                this.vuePlan.ajouterLiv(l);
            }
        }
        
        ArrayList<Itineraire> itineraires = controleurPrincipal.getControleurGraph().getItineraires();
        for (Itineraire i : itineraires) {
            for (Troncon t : i.getEnsembleTroncons()) {
                this.vuePlan.ajouterItineraire(t);
            }
        }
        
        ArrayList<Troncon> troncons = plan.getTroncons();
        for (Troncon t : troncons) {
            this.vuePlan.ajouterTroncon(t);
        }
    }
    
    public void scaleAutoVuePlan(JScrollPane panneauPlan) {
        Plan p =  vuePlan.getTournee().getPlan();
        
        double planWidth = p.getMaxX() - p.getMinX() + 2*ControleurPrincipal.padding + ControleurPrincipal.diamNoeud;
        double planHeight = p.getMaxY() - p.getMinY() + 2*ControleurPrincipal.padding + ControleurPrincipal.diamNoeud;
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
    
    public void selectLivraison(Livraison liv) {
        this.controleurPrincipal.selectLivraison(liv);
    }
    
    public void createLiv(Noeud noeud) throws Exception {
        this.controleurPrincipal.createLiv(noeud);
    }
    
    //--------------------------------
    //  Geter - Seter

    public VuePlan getVuePlan() {
        return vuePlan;
    }

    public void setTournee(Tournee tournee) {
        this.vuePlan.setTournee(tournee);
        this.vuePlan.setBackground(ControleurPrincipal.grisMaps);
    }
}
