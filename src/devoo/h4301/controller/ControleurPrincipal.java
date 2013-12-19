/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.controller;

import devoo.h4301.model.*;
import devoo.h4301.outils.MyException;
import devoo.h4301.views.*;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public static final Color grisFonceMaps = new Color(200, 196, 186);
    public static final Color grisMaps = new Color(231, 228, 219);
    public static final Color vertMaps = new Color(207, 222, 171);
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
        this.controleurGraph = new ControleurGraph();
    }

    //--------------------------------
    //  Public functions
    public void chargerPlan(String urlPlan) {
        if (urlPlan == "") {
            urlPlan = ouvrirFichier();
        }

        try {
            this.lecteurXml.construirePlanAPartirXML(urlPlan);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        Tournee t = Tournee.getInstance();
        controleurPlan.setTournee(t);

        commandeControleur.resetCommand();

        controleurPlan.rafraichirVuePlan(t, panneauPlan);
        controleurPlan.afficherPlan(panneauPlan);
        controleurLivraison.effacerVueListLivraison(this.panneauLiv );
        controleurLivraison.effacerItemLivraison(this.panneauLiv);
        controleurLivraison.rafraichirVueListLivraison(t, this.panneauLiv );
    
    }   

    public void chargerLiv(String urlLiv) throws MyException {
        if (Tournee.getInstance().getPlan() != null) {
            if (urlLiv == "") {
                urlLiv = ouvrirFichier();
            }

            try {
                this.lecteurXml.construireLivraisonAPartirXML(urlLiv);
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
            // observer 
            Tournee t = Tournee.getInstance();
            commandeControleur.resetCommand();

            this.controleurGraph.UpdateGraphe(t);
           
            controleurPlan.setTournee(t);
            controleurPlan.rafraichirVuePlan(t, panneauPlan);
            controleurPlan.afficherPlan(panneauPlan);
            controleurLivraison.effacerItemLivraison(panneauLiv);
        
            // rajouter
            controleurLivraison.rafraichirVueListLivraison(t, this.panneauLiv );
        } else {
            System.out.println("Error: Merci de charger un plan avant de charger des livraisons.");
        }
    }
    public void selectLivraison(Livraison liv) {
        // éclairer la bonne livraison
        this.controleurLivraison.afficherUneLivraison(this.panneauLiv, liv);

    }

    public void createLiv(Noeud noeud) throws Exception {
        // Tester si le noeud est bien une livraison alors afficher sans l'édition
       this.controleurLivraison.afficherCreationLivraison(this.panneauLiv, noeud);
       //this.controleurLivraison.afficherListLivraison(this.panneauLiv);
     }

    
    public void addCommandeLivraison(Livraison liv, boolean deleted)
    {
        this.commandeControleur.addCommand(liv, deleted);
    }

    public ControleurGraph getControleurGraph() {
        return controleurGraph;
    }

    public void setControleurGraph(ControleurGraph controleurGraph) {
        this.controleurGraph = controleurGraph;
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

    //--------------------------------
    //  Geter - Seter
    public JScrollPane getPanneauPlan() {
        return panneauPlan;
    }

    public void setPanneauPlan(JScrollPane panneauPlan) {
        panneauPlan.setBackground(ControleurPrincipal.grisMaps);
        this.panneauPlan = panneauPlan;
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

    public void undo(){
        try {
            this.commandeControleur.undo(Tournee.getInstance());
        } catch (Exception ex) {
            System.out.println("Impossible to undo");
        }
    }

    public void redo() {
        try {
            this.commandeControleur.redo(Tournee.getInstance());
        } catch (Exception ex) {
            System.out.println("Impossible to redo");
        }
    }
    
    public void print(){
        try{
            this.controleurGraph.printTrip(Tournee.getInstance());
        } catch (MyException e){
            System.out.println("Impossible de générer la version papier");
        }
    }

    void reloadUI() {
        try {
            this.controleurGraph.UpdateGraphe(Tournee.getInstance());
        } catch (MyException ex) {
            System.out.println("Impossible de recharger la UI");
        }
        
        this.controleurLivraison.rafraichirVueListLivraison(Tournee.getInstance(), this.panneauLiv);
        this.controleurPlan.rafraichirVuePlan(Tournee.getInstance(), this.panneauPlan);
    }
}
