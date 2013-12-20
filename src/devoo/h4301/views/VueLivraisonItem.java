/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.ControleurLivraison;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.PlageHoraire;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Mimi
 */
public class VueLivraisonItem extends javax.swing.JPanel {
    
    private Livraison livraison;
    private final DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private ControleurLivraison controleurLivraison;
    
    protected boolean selected;
    /**
     * Create new form VueLivraison 
     */
    public VueLivraisonItem(){
        initialize();
    }
    
    /**
     * Creates new form VueLivraison
     */
    /*public VueLivraisonItem(Livraison livraison, VueListLivraison vueListLivraison) {
        this.initialize();
        this.setLivraison(livraison);
        this.vueListLivraison = vueListLivraison;
        
    }*/
    
    
    public VueLivraisonItem(ControleurLivraison controleurLivraison, Livraison liv, int i){
        this.initialize();
        this.controleurLivraison = controleurLivraison;
        this.setLivraison(liv, i);
        
    }
       
    
      
     /*   //Le suite est utile, ou pas !!!
        this.idLivraison.setText(this.livraison.getDestination().getId().toString());
        this.nomClient.setText(this.livraison.getClient().getName());
        PlageHoraire ph = this.livraison.getHoraire();
        String horaire = "De "+ph.getDebut().toString()+" à "+ph.getFin().toString();
        this.plageHoraire.setText(horaire);
    */
    
    public void initialize() {
        initComponents();
        
        this.setOpaque(false);
        this.setVisible(true);
    }
    
    public Livraison getLivraison() {
        return livraison;
    }

     public void setLivraison(Livraison liv, int i){
        this.livraison = liv;
        String s = Integer.toString(i);
        this.idLivraison.setText(s);
        
        PlageHoraire ph = this.livraison.getHoraire();
        String horaire = "De "+ formatter.format(ph.getDebut())+" à "+ formatter.format(ph.getFin());
        this.plageHoraire.setText(horaire);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public void cacherRetour(){
        this.retour.setVisible(false);
    }

    
    
    
     
   //@Override
//    public void paintCoponent(Graphics g)
//    {
//        // s'inspirer de celle de noeud, avec un get color
////        Graphics2D graphics = (Graphics2D) g;
////        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
////        super.paintComponent(g);
////        g.fillOval(0,0,this.getWidth(),this.getHeight());
//    }
    
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idLivraison = new javax.swing.JLabel();
        plageHoraire = new javax.swing.JLabel();
        supprBouton = new javax.swing.JButton();
        retour = new javax.swing.JButton();

        setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        setPreferredSize(new java.awt.Dimension(280, 50));

        idLivraison.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        idLivraison.setText("[1]");

        plageHoraire.setText("Plage horaire");

        supprBouton.setText("Suppr");
        supprBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supprLiv(evt);
            }
        });
        supprBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprBoutonActionPerformed(evt);
            }
        });

        retour.setText("Retour");
        retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(idLivraison)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plageHoraire, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(retour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supprBouton)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(idLivraison)
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plageHoraire, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(retour)
                    .addComponent(supprBouton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // A modifié probablement 
    // pas de suppression au niveau de la vue
    private void supprLiv(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supprLiv
        
    }//GEN-LAST:event_supprLiv

    private void supprBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprBoutonActionPerformed
        // TODO add your handling code here:
        controleurLivraison.supprimerLivraison(livraison);
    }//GEN-LAST:event_supprBoutonActionPerformed

    private void retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourActionPerformed
        // TODO add your handling code here:
        this.controleurLivraison.afficherListLivraisonInitiale();
    }//GEN-LAST:event_retourActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idLivraison;
    private javax.swing.JLabel plageHoraire;
    private javax.swing.JButton retour;
    private javax.swing.JButton supprBouton;
    // End of variables declaration//GEN-END:variables
}
