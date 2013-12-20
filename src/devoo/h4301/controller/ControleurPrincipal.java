/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.controller;

import devoo.h4301.outils.LecteurXml;
import devoo.h4301.model.*;
import devoo.h4301.outils.MyException;
import devoo.h4301.views.*;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

/**
 *
 * @author chouard
 */
public final class ControleurPrincipal {
    // Constantes Couleur
    public static final Color rougeMaps = new Color(217, 95, 87);
    public static final Color blancMaps = new Color(255, 255, 255);
    public static final Color jauneMaps = new Color(248, 228, 122);
    public static final Color bleuMaps = new Color(77, 118, 194);
    public static final Color grisFonceMaps = new Color(200, 196, 186);
    public static final Color grisMaps = new Color(231, 228, 219);
    public static final Color grisFond = new Color(240, 240, 240);
    public static final Color vertMaps = new Color(207, 222, 171);
    public static final ArrayList<Color> tronconsColor = new ArrayList() {{
        add(new Color(255, 0, 0));
        add(new Color(0, 255, 0));
        add(new Color(0, 0, 255));
        add(new Color(0, 255, 255));
        add(new Color(255, 0, 255));
        add(new Color(255, 255, 0));
    }};
    // Constantes Noeuds
    public static final int diamNoeud = 15;
    //Constante plan
    public static final int padding = 20;
    // Constantes Troncons
    public static final float largeurTraitTroncon = 4;
    public static final float contourTroncon = 1;
    public static final int xMinTroncon = 4;
    public static final int yMinTroncon = 4;
    

    private JScrollPane panneauPlan;
    private JScrollPane panneauLiv;
    private FenetrePrincipale fenParent;
    private JFileChooser jFileChooserXML;

    private final ControleurPlan controleurPlan;
    private final ControleurLivraison controleurLivraison;
    private ControleurGraph controleurGraph;
    private ControllerCommand commandeControleur;
    private final LecteurXml lecteurXml;

    public ControleurPrincipal(JScrollPane scrollPanePlan, JScrollPane scrollPaneLiv, FenetrePrincipale fenParent) {
        this.setPanneauPlan(scrollPanePlan);
        this.setPanneauLiv(scrollPaneLiv);
        this.setFenParent(fenParent);

        this.controleurPlan = new ControleurPlan(this);
        this.lecteurXml = new LecteurXml();
        this.controleurLivraison = new ControleurLivraison(this);
        this.commandeControleur = new ControllerCommand(this, this.fenParent);
        this.controleurGraph = new ControleurGraph(this,this.fenParent);
    }

    //--------------------------------
    //  Public functions
    
    /**
     * Charge des nouvelles livraisons dans le modèle et met a jour l'ui.
     * @param String lien vers le fichier xml de livraisons (si == "", ouvre un dialogue pour choisir le fichier)
     */
    public void chargerPlan(String urlPlan) {
        if (urlPlan == "") {
            urlPlan = ouvrirFichier();
        }

        try {
            this.lecteurXml.construirePlanAPartirXML(urlPlan);

            commandeControleur.resetCommand();
            
            this.reloadGraph();
            
            this.reloadUI(true);
            this.fenParent.setZoomScaleNum(this.controleurPlan.getVuePlan().getZoomScale());
        } catch (Exception e) {
            commandeControleur.resetCommand();
            this.resetUI(true);
            System.out.println("Error: " + e.getMessage());
        }
    }   

    /**
     * Charge des nouvelles livraisons dans le modèle et met a jour l'ui.
     * @param String lien vers le fichier xml de livraisons (si == "", ouvre un dialogue pour choisir le fichier)
     */
    public void chargerLiv(String urlLiv) throws MyException {
        if (Tournee.getInstance().getPlan() != null) {
            if (urlLiv == "") {
                urlLiv = ouvrirFichier();
            }

            try {
                this.lecteurXml.construireLivraisonAPartirXML(urlLiv);
                
                // observer 
                commandeControleur.resetCommand();

                this.reloadGraph();
                
                this.reloadUI(true);
                this.fenParent.setZoomScaleNum(this.controleurPlan.getVuePlan().getZoomScale());
            } catch (Exception e) {
                commandeControleur.resetCommand();
                this.resetUI(false);
                System.out.println("Error : " + e.getMessage());
            }
            
        } else {
            System.out.println("Error: Merci de charger un plan avant de charger des livraisons.");
        }
    }
    
    /**
     * Recalcule le graphe des livraisons
     */
    public void reloadGraph() {
        try {
            this.controleurGraph.UpdateGraphe(Tournee.getInstance());
        } catch (Exception e) {
            System.out.println("Impossible de calculer l'itineraire. \n"+e.toString());
        }
    }

    
    /**
     * Recharge toute l'UI (plan, list, graphe, etc...)
     * @param boolean true pour avoir un redimensionnement automatique du plan
     */
    public void reloadUI(boolean autoScale) {
        this.controleurPlan.resetPlan();
        Tournee t = Tournee.getInstance();

        controleurPlan.setTournee(t);
        if (autoScale) {
            controleurPlan.scaleAutoVuePlan();
        }
        controleurPlan.rafraichirVuePlan(t);
        controleurPlan.afficherPlan(panneauPlan);

        controleurLivraison.effacerVueListLivraison();
        controleurLivraison.effacerItemLivraison();
        controleurLivraison.rafraichirVueListLivraison(t);

        Boolean possibleToLoadLivraisons = Tournee.getInstance().getPlan() != null;
        Boolean possibleToPrintLivraisons = Tournee.getInstance().getLivraisons().size() != 0;
        this.fenParent.updateLoadLivState(possibleToLoadLivraisons);
        this.fenParent.updatePrintState(possibleToPrintLivraisons);
    }
    
    /**
     * Reset l'UI (plan, graph).
     */
    public void resetUI(boolean resetPlan) {
        this.controleurGraph.resetGraph();
        if (resetPlan) {
            this.controleurPlan.resetPlan();
        }
        this.fenParent.updateLoadLivState(false);
        this.fenParent.updatePrintState(false);
    }
    
    /**
     * Met à jour la vue pour zoomer de pourcent%
     * @param double pourcentage du zoom actuel
     */
    public double zoomChange(double pourcent) {
        this.controleurPlan.zoomChange(pourcent);
        this.reloadUI(false);
        return this.controleurPlan.getVuePlan().getZoomScale();
    }
    
    /**
     * Prend en charge le rechargement de la vue pour qu'elle remplisse au mieux le panneau.
     */
    public double zoomAuto() {
        this.controleurPlan.scaleAutoVuePlan();
        this.reloadUI(false);
        return this.controleurPlan.getVuePlan().getZoomScale();
    }
    
    /**
     * Demande au controleur principale d'afficher les détails d'une livraison
     * Binde le click sur une livraison depuis le plan.
     * @param  Livraion la livraison a afficher
     */
    public void selectLivraison(Livraison liv) {
        if (Tournee.getInstance().getLivraisons().size() > 0) {
            ArrayList <Livraison> listLiv = this.controleurGraph.getLivOrdered();
            int i = 1;
            while (listLiv.get(i) != liv){
                i++;
            }
            this.controleurLivraison.afficherUneLivraison(liv, i);
        } else {
            this.unSelectLivraisons();
            System.out.println("Il est necessaire de charger des livraisons avant de pouvoir les modifier.");
        }
    }
    
    /**
     * Revenir à la liste des livraisons (délègue au controleur principal).
     */
    public void unSelectLivraisons() {
        this.controleurLivraison.afficherListLivraison();
    }

    /**
     * Demande au controleur principale d'afficher la création d'une nouvelle livraison
     * Binde le click sur un noeud vide depuis le plan.
     * @param  Noeud ou créer la nouvelle livraison
     */
    public void createLiv(Noeud noeud) {
        if (Tournee.getInstance().getLivraisons().size() > 0) {
            this.controleurLivraison.afficherCreationLivraison(noeud);
        } else {
            this.unSelectLivraisons();
            System.out.println("Il est necessaire de charger des livraison avant de pouvoir en une nouvelle.");
        }
     }
    
    // ?????????
    public void addCommandeLivraison(Livraison liv, boolean deleted)
    {
        this.commandeControleur.addCommand(liv, deleted);
    }
       
    //--------------------------------
    //  Private functions
    /**
     * Ouvre un dialogue système pour choisir un fichier
     *
     * @return String urlFichier
     */
    private String ouvrirFichier() {
        jFileChooserXML = new JFileChooser();
        jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (jFileChooserXML.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String filePath = jFileChooserXML.getSelectedFile().getAbsolutePath();
            System.out.println("You chose to open this file: " + filePath);
            return filePath;
        }
        return null;
    }

    /**
     * Annule le dernier ajout/suppression de livraison
     */
    public void undo(){
        try {
            this.commandeControleur.undo(Tournee.getInstance());
        } catch (Exception ex) {
            System.out.println("Impossible to undo");
        }
    }

    /**
     * Réitère la dernière action annulé
     */
    public void redo() {
        try {
            this.commandeControleur.redo(Tournee.getInstance());
        } catch (Exception ex) {
            System.out.println("Impossible to redo");
        }
    }
    
    /**
     * Affiche les livraisons du lendemain sous la forme d'une feuille de tournée imprimable.
     */
    public void print(){
        try{
            this.controleurGraph.printTrip(Tournee.getInstance());
        } catch (MyException e){
            System.out.println("Impossible de générer la version papier");
        }
    }

    //--------------------------------
    //  Geter - Seter
    
    public JScrollPane getPanneauPlan() {
        return panneauPlan;
    }

    /**
     * Set le panneau contenant le plan, et définit le background en blanc.
     * @param  JScrollPane nouveau panneau
     */
    public void setPanneauPlan(JScrollPane panneauPlan) {
        panneauPlan.setBackground(Color.WHITE);
        this.panneauPlan = panneauPlan;
    }

    public ControleurGraph getControleurGraph() {
        return controleurGraph;
    }

    public void setControleurGraph(ControleurGraph controleurGraph) {
        this.controleurGraph = controleurGraph;
    }

    public JScrollPane getPanneauLiv() {
        return panneauLiv;
    }

    public void setPanneauLiv(JScrollPane panneauLiv) {
        this.panneauLiv = panneauLiv;
    }

    public FenetrePrincipale getFenParent() {
        return fenParent;
    }

    public void setFenParent(FenetrePrincipale fenParent) {
        this.fenParent = fenParent;
    }
}
