/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.ControleurPlan;
import devoo.h4301.controller.ControleurPrincipal;
import devoo.h4301.model.Livraison;
import java.awt.Color;

/**
 *
 * @author chouard
 */
public class VueLivraisonNoeud extends VueNoeud {
    private Livraison liv;

    /**
     * Creates new form VueLiv
     */
    public VueLivraisonNoeud() {
        initComponents();
    }
    
    /**
     * Constructeur VueNoeud avec création du noeud
     */
    public VueLivraisonNoeud(Livraison liv, VuePlan vuePlan ) {
        this.setLiv(liv);
        this.setVuePlan(vuePlan);
        this.initialize();
    }
    
    public void setSelected(boolean selected) {
        if (selected) {
            this.vuePlan.selectLiv(this.liv);
        }
        
        this.selected = selected;
        
        this.repaint();
    }

    public Livraison getLiv() {
        return liv;
    }

    public void setLiv(Livraison liv) {
        this.liv = liv;
    }
    
    public int getXNoeud() {
        return this.liv.getDestination().getX();
    }
    
    public int getYNoeud() {
        return this.liv.getDestination().getY();
    }
    
    public Color getColor() {
        if (this.selected || this.hovered) {
            return ControleurPrincipal.jauneMaps;
        } else {
            return ControleurPrincipal.rougeMaps;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
