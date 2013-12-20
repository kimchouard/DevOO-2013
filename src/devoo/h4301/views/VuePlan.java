/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.ControleurPlan;
import devoo.h4301.controller.ControleurPrincipal;
import devoo.h4301.model.Itineraire;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Noeud;
import devoo.h4301.model.PlageHoraire;
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
    private ArrayList<VueNoeudLivraison> vueLivs = new ArrayList();
    private ArrayList<VueTronconItineraire> vueItin = new ArrayList();
    private ArrayList<VuePlageHoraire> vuePlages = new ArrayList();
    
    protected double zoomScale = 1.0;

    /**
     * Creates new form VuePlan
     */
    public VuePlan(ControleurPlan controlerPlan) {
        this.setControlerPlan(controlerPlan);
        initialize();
    }

    /**
     * Initialise la vue
     */
    private void initialize() {
        initComponents();

        this.setOpaque(true);
        this.setVisible(true); 
    }
    
    /**
     * Vide le plan en rechargeant l'interface. 
     */
    public void reset() {
        this.removeAll();
        this.updateUI();
        this.vueNoeuds.clear();
        this.vueTroncons.clear();
        this.vueLivs.clear();
        this.vueItin.clear();
        this.vuePlages.clear();
    }
    
    /**
     * Ajouter un noeud sur le plan en créant la vue associéß
     * @param noeud correspond au noeud a ajouter
     */
    public void ajouterNoeud(Noeud noeud) {
        VueNoeud v = new VueNoeud(noeud, this);
        this.placerNoeud(v);
        this.updateVuePlanFrame();
        
        this.vueNoeuds.add(v);
        this.add(v);
        v.setVisible(true);
    }
    
    /**
     * Cache le noeud passé en paramètre (pour laisser la place a une livraison).
     * @param noeud le noeud a cacher
     */
    public boolean hideNoeud(Noeud noeud) {
        for(VueNoeud v : this.vueNoeuds) {
            if (v.getNoeud().getId() == noeud.getId()) {
                v.setVisible(false);
                
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Ajouter une livraison à la vuePlan
     * @param liv la livraison a ajouter
     */
    public void ajouterLiv(Livraison liv) {
        VueNoeudLivraison vl = new VueNoeudLivraison(liv, this);
        ajouterVueLiv(vl, liv);
    }
    
    /**
     * Ajoute l'entrepot
     * @param liv la livraison faisant office d'entrepot
     */
    public void ajouterEntrepot(Livraison liv) {
        VueNoeudEntrepot ve = new VueNoeudEntrepot(liv, this);
        ajouterVueLiv((VueNoeudLivraison) ve, liv);
    }
    
    /**
     * Ajoute la VueLivraison fraichement crée dans le plan.
     * @param vl la vueNoeudLivraison a ajouter
     * @param liv la livraison concerné
     */
    private void ajouterVueLiv(VueNoeudLivraison vl, Livraison liv) {
        this.placerNoeud((VueNoeud) vl);
        this.updateVuePlanFrame();
        
        this.hideNoeud(liv.getDestination());
        
        this.vueLivs.add(vl);
        this.add(vl);
        vl.setVisible(true);
    }
    
    /**
     * Place le noeud dans le plan
     * Prend en compte le zoom.
     * @param vueNoeud la vue du noeud concerné
     */
    public void placerNoeud(VueNoeud vueNoeud) {
        vueNoeud.setSize(ControleurPrincipal.diamNoeud, ControleurPrincipal.diamNoeud);
        
        int xLocation = this.scaledCoordinateHorizontal(vueNoeud.getXNoeud()) - vueNoeud.getWidth()/2;
        int yLocation = this.scaledCoordinateVertical(vueNoeud.getYNoeud()) - vueNoeud.getHeight()/2;
        vueNoeud.setLocation(xLocation, yLocation);
    }

    /**
     * Crée la vue pour un troncon et l'affiche dans le plan.
     * @param troncon le troncon a ajouter
     */
    public void ajouterTroncon(Troncon troncon) {
        VueTroncon v = new VueTroncon(troncon, this);
        this.placerTroncon(v);
        this.vueTroncons.add(v);
        this.add(v);
    }
    
    /**
     * Crée, si besoin, la vue pour l'itinéraire passé en param. L'ajoute a l'itinéraire le cas échéant.
     * Met ensuite a jour l'affichage pour prendre en compte les changement.
     * @param troncon le troncon concerné
     * @param plageHoraire la plage horaire concerné
     */
    public void ajouterItineraire(Troncon t, PlageHoraire ph) {
        VueTronconItineraire vi = getVueItineraire(t);
        if (vi == null) {
            VuePlageHoraire vph = getVuePlageHoraire(ph);
            vi = new VueTronconItineraire(t, vph, this);
            this.placerTroncon((VueTroncon) vi);
            this.updateVuePlanFrame();

            this.vueItin.add(vi);
            this.add(vi);
            vi.setVisible(true);
        } else {
            vi.addVuePlageHoraire(getVuePlageHoraire(ph));
            this.updateVuePlanFrame();
        }
    }
    
    /**
     * Cherche dans les vueItineraires du plan celle correspondant au troncon passsé en param.
     * Retourne null le cas échéant.
     * @param troncon
     */
    public VueTronconItineraire getVueItineraire(Troncon t) {
        for (VueTronconItineraire vi : this.vueItin) {
            if( (vi.getTroncon().getDestination().getId() == t.getDestination().getId())
             && (vi.getTroncon().getOrigine().getId() == t.getOrigine().getId())
            ) {
                return vi;
            }
        }
        
        return null;
    }
    
    /**
     * Cherche dans les vuePlageHoraire du plan celle correspondant à la plage horaire passsé en param.
     * Crée une nouvelle vuePlageHoraire avec la bonne couleur le cas échéant.
     * @param pplageHoraire
     */
    public VuePlageHoraire getVuePlageHoraire(PlageHoraire ph) {
        for (VuePlageHoraire vph : this.vuePlages) {
            if((vph.getPlageHoraire().getDebut() == ph.getDebut())
            && (vph.getPlageHoraire().getFin() == ph.getFin())
            ) {
                return vph;
            }
        }
        
        Color c = ControleurPrincipal.tronconsColor.get(this.vuePlages.size() % ControleurPrincipal.tronconsColor.size());
        VuePlageHoraire vph = new VuePlageHoraire(c, ph);
        this.vuePlages.add(vph);
        return vph;
    }
    
    /**
     * Place le troncon dans le plan
     * Prend en compte le zoom.
     * @param vueTroncon la vue du troncon concerné
     */
    public void placerTroncon(VueTroncon vueTroncon) {
        Troncon troncon = vueTroncon.getTroncon();
        
        int x = Math.min(troncon.getOrigine().getX(), troncon.getDestination().getX());
        int y = Math.min(troncon.getOrigine().getY(), troncon.getDestination().getY());
        vueTroncon.setLocation(this.scaledCoordinateHorizontal(x) - ControleurPrincipal.diamNoeud/2, this.scaledCoordinateVertical(y) - ControleurPrincipal.diamNoeud/2);
        
        int larg = Math.abs(troncon.getDestination().getX() - troncon.getOrigine().getX());
        int haut = Math.abs(troncon.getDestination().getY() - troncon.getOrigine().getY());
        vueTroncon.setSize(this.scaledSize(larg) + ControleurPrincipal.diamNoeud, this.scaledSize(haut) + ControleurPrincipal.diamNoeud);
    }
    
    /**
     * Déselectionne tous les noeud et livraison
     */
    public void unselectNoeudsEtLiv() {
        //Reset all the others noeuds
        for (VueNoeudLivraison vl : this.vueLivs) {
           vl.setSelected(false);
        }
        for (VueNoeud vn : this.vueNoeuds) {
           vn.setSelected(false);
        }
    }
    
    /**
     * Délèque au controleur la mise a jour de la vue lors du clic sur une livraison.
     * @param liv la livraison concerné
     */
    public void selectLiv(Livraison liv) {
        //Do the check
        this.unselectNoeudsEtLiv();
        controlerPlan.selectLivraison(liv);
    }
    
    /**
     * Signale au controleur qu'une livraison est déselectionée.
     */
    public void unSelectLivs() {
        controlerPlan.unSelectLivraisons();
    }
    
    /**
     * Signale au controleur que l'on veut créer une livraison sur ce noeud.
     * @param noeud le noeud concerné
     */
    public void createLiv(Noeud noeud) throws Exception {
        this.unselectNoeudsEtLiv();
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
    
    /**
     * Met a jour les dimension de la vue, fonction du zoom.
     */
    private void updateVuePlanFrame() {
        int planWidth = tournee.getPlan().getMaxX() - tournee.getPlan().getMinX();
        int planHeight = tournee.getPlan().getMaxY() - tournee.getPlan().getMinY();
        
        Dimension dimension = new Dimension(this.scaledSize(planWidth) + ControleurPrincipal.padding*2, this.scaledSize(planHeight) + ControleurPrincipal.padding*2);
        this.setPreferredSize(dimension);
    }
    
    /**
     * Renvoie les coordonnée Y d'un point en tenant compte du zoom.
     */
    private int scaledCoordinateVertical(int coordonate) {
        return (int)(this.zoomScale * (coordonate - tournee.getPlan().getMinY()) ) + ControleurPrincipal.padding;
    }
    
    /**
     * Renvoie les coordonnée X d'un point en tenant compte du zoom.
     */
    private int scaledCoordinateHorizontal(int coordonate) {
        return (int)(this.zoomScale * (coordonate - tournee.getPlan().getMinX()) ) + ControleurPrincipal.padding;
    }
    
    /**
     * Renvoie une taille en tenant compte du zoom.
     */
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
