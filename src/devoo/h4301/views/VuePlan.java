/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.ControleurPlan;
import devoo.h4301.controller.ControleurPrincipal;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Noeud;
import devoo.h4301.model.Plan;
import devoo.h4301.model.Tournee;
import devoo.h4301.model.Troncon;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author chouard
 */
public class VuePlan extends javax.swing.JPanel {
    private ControleurPlan controlerPlan;
    
    private Tournee tournee;
    private ArrayList<VueNoeud> vueNoeuds = new ArrayList();
    private ArrayList<VueTroncon> vueTroncons = new ArrayList();
    private ArrayList<VueLiv> vueLivs = new ArrayList();
    
    protected double zoomScale = 1.0;

    /**
     * Creates new form VuePlan
     */
    public VuePlan(ControleurPlan controlerPlan) {
        this.setControlerPlan(controlerPlan);
        initialize();
    }
    
    private void initialize() {
        initComponents();

        this.setOpaque(true);
        this.setVisible(true); 
    }
    
    public void reset() {
        this.removeAll();
        this.updateUI();
        this.vueNoeuds.clear();
        this.vueTroncons.clear();
        this.vueLivs.clear();
    }
    
    public void ajouterNoeud(Noeud noeud) {
        VueNoeud v = new VueNoeud(noeud, this);
        this.placerNoeud(v);
        this.updateVuePlanFrame();
        
        this.vueNoeuds.add(v);
        this.add(v);
        v.setVisible(true);
    }
    
    public boolean hideNoeud(Noeud noeud) {
        for(VueNoeud v : this.vueNoeuds) {
            if (v.getNoeud().getId() == noeud.getId()) {
                v.setVisible(false);
                
                return true;
            }
        }
        
        return false;
    }
    
    public void ajouterLiv(Livraison liv) {
        VueLiv vl = new VueLiv(liv, this);
        this.placerNoeud((VueNoeud) vl);
        this.updateVuePlanFrame();
        
        this.hideNoeud(liv.getDestination());
        
        this.vueLivs.add(vl);
        this.add(vl);
        vl.setVisible(true);
    }
    
    public void placerNoeud(VueNoeud vueNoeud) {
        vueNoeud.setSize(ControleurPrincipal.diamNoeud, ControleurPrincipal.diamNoeud);
        
        int xLocation = this.scaledCoordinateHorizontal(vueNoeud.getXNoeud()) - vueNoeud.getWidth()/2;
        int yLocation = this.scaledCoordinateVertical(vueNoeud.getYNoeud()) - vueNoeud.getHeight()/2;
        vueNoeud.setLocation(xLocation, yLocation);
    }

    public void ajouterTroncon(Troncon troncon) {
        VueTroncon v = new VueTroncon(troncon, this);
        this.placerTroncon(v);
        this.vueTroncons.add(v);
        this.add(v);
    }
    
    public void placerTroncon(VueTroncon vueTroncon) {
        Troncon troncon = vueTroncon.getTroncon();
        
        int x = Math.min(troncon.getOrigine().getX(), troncon.getDestination().getX());
        int y = Math.min(troncon.getOrigine().getY(), troncon.getDestination().getY());
        vueTroncon.setLocation(this.scaledCoordinateHorizontal(x) - ControleurPrincipal.diamNoeud/2, this.scaledCoordinateVertical(y) - ControleurPrincipal.diamNoeud/2);
        
        int larg = Math.abs(troncon.getDestination().getX() - troncon.getOrigine().getX());
        int haut = Math.abs(troncon.getDestination().getY() - troncon.getOrigine().getY());
        vueTroncon.setSize(this.scaledSize(larg) + ControleurPrincipal.diamNoeud, this.scaledSize(haut) + ControleurPrincipal.diamNoeud);
    }
    
    public void unselectNoeuds() {
        //Reset all the others noeuds
        for (VueLiv vl : this.vueLivs) {
           vl.setSelected(false);
        }
        for (VueNoeud vn : this.vueNoeuds) {
           vn.setSelected(false);
        }
    }
    
    public void selectLiv(Livraison liv) {
        //Do the check
        unselectNoeuds();
        controlerPlan.selectLivraison(liv);
    }
    
    public void createLiv(Noeud noeud) {
        unselectNoeuds();
        controlerPlan.createLiv(noeud);
    }
    
    public ArrayList<VueNoeud> getVueNoeuds() {
        return vueNoeuds;
    }
    
    public double getZoomScale() {
        return zoomScale;
    }

    public void setZoomScale(double zoomScale) {
        this.zoomScale = zoomScale;
        this.updateVuePlanFrame();
    }
    
    private void updateVuePlanFrame() {
        int planWidth = tournee.getPlan().getMaxX() - tournee.getPlan().getMinX();
        int planHeight = tournee.getPlan().getMaxY() - tournee.getPlan().getMinY();
        
        Dimension dimension = new Dimension(this.scaledSize(planWidth) + ControleurPrincipal.padding*2, this.scaledSize(planHeight) + ControleurPrincipal.padding*2);
        this.setPreferredSize(dimension);
    }
    
    private int scaledCoordinateVertical(int coordonate) {
        return (int)(this.zoomScale * (coordonate - tournee.getPlan().getMinY()) ) + ControleurPrincipal.padding;
    }
    
    private int scaledCoordinateHorizontal(int coordonate) {
        return (int)(this.zoomScale * (coordonate - tournee.getPlan().getMinX()) ) + ControleurPrincipal.padding;
    }
    
    private int scaledSize(int size) {
        return (int)(this.zoomScale * size);
    }

    public Tournee getTournee() {
        return tournee;
    }

    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }

    public ControleurPlan getControlerPlan() {
        return controlerPlan;
    }

    public void setControlerPlan(ControleurPlan controlerPlan) {
        this.controlerPlan = controlerPlan;
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
