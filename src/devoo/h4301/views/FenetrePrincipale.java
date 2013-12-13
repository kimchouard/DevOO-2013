/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.*;
import javax.swing.JScrollPane;

/**
 *
 * @author chouard
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    public ControleurPrincipal controleurPrincipal;
    
    /**
     * Creates new form FenetrePrincipale
     */
    public FenetrePrincipale() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pGauche = new javax.swing.JScrollPane();
        chargerPlan = new javax.swing.JButton();
        chargerLiv = new javax.swing.JButton();
        redo = new javax.swing.JButton();
        undo = new javax.swing.JButton();
        pDroit = new javax.swing.JScrollPane();
        debug = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pGauche.setAutoscrolls(true);

        chargerPlan.setText("Charger Plan");
        chargerPlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickChargerPlan(evt);
            }
        });

        chargerLiv.setText("Charger Livraisons");
        chargerLiv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickChargerLivraisons(evt);
            }
        });

        redo.setText("Redo");

        undo.setText("Undo");

        debug.setText("DEBUG");
        debug.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickDebug(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pGauche, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pDroit, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(chargerPlan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chargerLiv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debug)
                .addGap(45, 45, 45)
                .addComponent(undo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redo)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chargerPlan)
                    .addComponent(chargerLiv)
                    .addComponent(redo)
                    .addComponent(undo)
                    .addComponent(debug))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pGauche, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addComponent(pDroit)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clickChargerPlan(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickChargerPlan
        // Lunch load of the map from the file URL asked
        this.controleurPrincipal.chargerPlan("");
    }//GEN-LAST:event_clickChargerPlan

    private void clickChargerLivraisons(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickChargerLivraisons
        this.controleurPrincipal.chargerLiv("");
    }//GEN-LAST:event_clickChargerLivraisons

    private void clickDebug(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickDebug
        // TODO add your handling code here:
        this.controleurPrincipal.chargerPlan("/Users/chouard/plan10x10.xml");
    }//GEN-LAST:event_clickDebug

    public JScrollPane getpDroit() {
        return pDroit;
    }

    public JScrollPane getpGauche() {
        return pGauche;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chargerLiv;
    private javax.swing.JButton chargerPlan;
    private javax.swing.JButton debug;
    private javax.swing.JScrollPane pDroit;
    private javax.swing.JScrollPane pGauche;
    private javax.swing.JButton redo;
    private javax.swing.JButton undo;
    // End of variables declaration//GEN-END:variables
}
