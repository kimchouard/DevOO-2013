/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Noeud;
import devoo.h4301.model.Plan;
import devoo.h4301.views.FenetrePrincipale;
import devoo.h4301.views.VueNoeud;
import devoo.h4301.views.VuePlan;
import java.util.ArrayList;
import javax.swing.JScrollPane;

/**
 *
 * @author chouard
 */
public class ControleurPlan {
    private JScrollPane scrollPanePlan;
    private FenetrePrincipale fenParent;
    
    private VuePlan vuePlan;
    private ArrayList<VueNoeud> vueNoeuds = new ArrayList();

    public ControleurPlan(JScrollPane scrollPanePlan, FenetrePrincipale fenParent) {
        this.setScrollPanePlan(scrollPanePlan);
        this.setFenParent(fenParent);
        
        this.setVuePlan(new VuePlan());
        this.scrollPanePlan.setViewportView(vuePlan);
    }
    
    //--------------------------------
    //  Public functions
    
    /**
     * Affiche le plan à partir du singleton.
     */
    public void afficherPlan() {
        Plan plan = Plan.getInstance();
        
        ArrayList<Noeud> noeuds = plan.getNoeuds();
        for (Noeud n : noeuds) {
            this.addNoeud(n);
        }
    }
    
    //--------------------------------
    //  Geter - Seter
    public JScrollPane getScrollPanePlan() {
        return scrollPanePlan;
    }

    public void setScrollPanePlan(JScrollPane scrollPanePlan) {
        this.scrollPanePlan = scrollPanePlan;
    }

    public FenetrePrincipale getFenParent() {
        return fenParent;
    }

    public void setFenParent(FenetrePrincipale fenParent) {
        this.fenParent = fenParent;
    }

    public VuePlan getVuePlan() {
        return vuePlan;
    }

    public void setVuePlan(VuePlan vuePlan) {
        this.vuePlan = vuePlan;
    }

    public ArrayList<VueNoeud> getVueNoeuds() {
        return vueNoeuds;
    }

    public void addNoeud(Noeud noeud) {
        VueNoeud v = new VueNoeud(noeud);
        this.vueNoeuds.add(v);
        this.vuePlan.addVueNoeud(v);
    }
    
    
}
