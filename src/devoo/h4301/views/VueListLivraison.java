/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.ControleurLivraison;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Tournee;
import static devoo.h4301.views.VuePlan.padding;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Mimi
 */
public class VueListLivraison extends javax.swing.JPanel {

    private LinkedList<VueLivraisonItem> vueLivraisons = new LinkedList();
    private ControleurLivraison controleurLivraison;
    /**
     * Creates new form VueLivraisonItem
     */
    public VueListLivraison(ControleurLivraison controleurLivraison) {
        this.setControleurLivraison(controleurLivraison); 
        initialize();
    }
    
    public void initialize() {
        initComponents();
        
        this.setOpaque(false);
        this.setVisible(true); 
    }
    
    public void reset() {
        this.removeAll();
        this.updateUI();
        this.vueLivraisons.clear();
        this.vueLivraisons.clear();
    }
    
    public void ajouterLivraison(Livraison livraison) {
        VueLivraisonItem v = new VueLivraisonItem(livraison, this.controleurLivraison);
        this.placerLivraison(v, this.vueLivraisons.size()-1);
        this.updateVueListLivraison();
        
        this.vueLivraisons.add(v);
        this.add(v);
        v.setVisible(true);
    }
    
    public void placerLivraison(VueLivraisonItem vl, int rank) {
        int yLocation = rank * 50;
        vl.setLocation(0, yLocation);
    }

    public LinkedList<VueLivraisonItem> getVueLivraison() {
        return vueLivraisons;
    }

    public ControleurLivraison getControleurLivraison() {
        return controleurLivraison;
    }

    public void setTournee(Tournee tournee) {
        
        for(int i = 0; i < tournee.getLivraisons().size(); i++)
        {
            this.ajouterLivraison(tournee.getLivraisons().get(i));
        }
    }

    public void setControleurLivraison(ControleurLivraison controleurLivraison) {
        this.controleurLivraison = controleurLivraison;
    }
    
    private void updateVueListLivraison() {
        int listHeight = 50 * this.getVueLivraison().size();
        
        Dimension dimension = new Dimension(this.getWidth(), listHeight);
        this.setPreferredSize(dimension);
    }
    
    // fonction highligth (livraison), 
    
    
    /*private void updateVueLivraison() {
        int planWidth = plan.getMaxX() - plan.getMinX();
        int planHeight = plan.getMaxY() - plan.getMinY();
        
        Dimension dimension = new Dimension(this.scaledSize(planWidth) + padding*2, this.scaledSize(planHeight) + padding*2);
        this.setPreferredSize(dimension);
    }*/
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(280, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
