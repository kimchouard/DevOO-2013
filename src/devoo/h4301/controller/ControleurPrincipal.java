/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.*;
import devoo.h4301.views.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

/**
 *
 * @author chouard
 */
public class ControleurPrincipal {
    private JScrollPane panneauPlan;
    private FenetrePrincipale fenParent;
    private JFileChooser jFileChooserXML;
    
    private ControleurPlan controleurPlan;
    private ControllerCommand commandeControleur;
    private LecteurXml lecteurXml;

    public ControleurPrincipal(JScrollPane scrollPanePlan, FenetrePrincipale fenParent) {
        this.setPanneauPlan(scrollPanePlan);
        this.setFenParent(fenParent);
        
        this.controleurPlan = new ControleurPlan(this);
        this.commandeControleur = new ControllerCommand();
        this.lecteurXml = new LecteurXml();
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
            System.out.println("Error: "+e.getMessage());
        }
        Tournee t = Tournee.getInstance();
        controleurPlan.setTournee(t);

        commandeControleur.resetCommand();

        controleurPlan.scaleAutoVuePlan(panneauPlan);
        controleurPlan.rafraichirVuePlan(t);
        controleurPlan.afficherPlan(panneauPlan);
    }
    
    public void chargerLiv(String urlLiv) {
        if (Tournee.getInstance().getPlan() != null) {
            if (urlLiv == "") {
                urlLiv = ouvrirFichier();
            }
            
            try {
                this.lecteurXml.construireLivraisonAPartirXML(urlLiv);
            } catch (Exception e) {
                System.out.println("Error : "+e.getMessage());
            }
            Tournee t = Tournee.getInstance();
            controleurPlan.setTournee(t);
            controleurPlan.rafraichirVuePlan(t);
            controleurPlan.afficherPlan(panneauPlan);
        } else {
            System.out.println("Error: Merci de charger un plan avant de charger des livraisons.");
        }
    }
    
//    public void chargerPlanDebug() {
//        try {
//            Tournee t = initDebug();
//            controleurPlan.setTournee(t);
//            controleurPlan.scaleAutoVuePlan(panneauPlan);
//            controleurPlan.rafraichirVuePlan();
//            controleurPlan.afficherPlan(panneauPlan);
//        } catch (Exception ex) {
//            Logger.getLogger(ControleurPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public void selectLivraison(Livraison liv) {
        //TODO open edit liv on right
    }
    
    public void createLiv(Noeud noeud) {
        //TODO Open add new liv on right
    }
    
    //--------------------------------
    //  Private functions

    /**
     * Ouvre un dialogue système pour choisir un fichier
     * @return String urlFichier
     */
    private String ouvrirFichier(){
        jFileChooserXML = new JFileChooser();
        // Note: source for ExampleFileFilter can be found in FileChooserDemo,
        // under the demo/jfc directory in the JDK.
//        ExampleFileFilter filter = new ExampleFileFilter();
//        filter.addExtension("xml");
//        filter.setDescription("Fichier XML");
//        jFileChooserXML.setFileFilter(filter);
        jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (jFileChooserXML.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String filePath = jFileChooserXML.getSelectedFile().getAbsolutePath();
            System.out.println("You chose to open this file: " + filePath);
            return filePath;
        }
        return null;
    }
    
//    private Tournee initDebug() throws Exception {
//        Plan p = new Plan();
//        
//        Noeud n0 = new Noeud(0, 200, 200);
//        Noeud n1 = new Noeud(0, 100, 100);
//        Noeud n2 = new Noeud(0, 150, 100);
//        Noeud n3 = new Noeud(0, 200, 100);
//        Noeud n4 = new Noeud(0, 250, 100);
//        Noeud n5 = new Noeud(0, 300, 100);
//        Noeud n6 = new Noeud(0, 300, 150);
//        Noeud n7 = new Noeud(0, 300, 200);
//        Noeud n8 = new Noeud(0, 300, 250);
//        Noeud n9 = new Noeud(0, 300, 300);
//        Noeud n10 = new Noeud(0, 250, 300);
//        Noeud n11 = new Noeud(0, 200, 300);
//        Noeud n12 = new Noeud(0, 150, 300);
//        Noeud n13 = new Noeud(0, 100, 300);
//        Noeud n14 = new Noeud(0, 100, 250);
//        Noeud n15 = new Noeud(0, 100, 200);
//        Noeud n16 = new Noeud(0, 100, 150);
//        p.addNoeud(n0);
//        p.addNoeud(n1);
//        p.addNoeud(n2);
//        p.addNoeud(n3);
//        p.addNoeud(n4);
//        p.addNoeud(n5);
//        p.addNoeud(n6);
//        p.addNoeud(n7);
//        p.addNoeud(n8);
//        p.addNoeud(n9);
//        p.addNoeud(n10);
//        p.addNoeud(n11);
//        p.addNoeud(n12);
//        p.addNoeud(n13);
//        p.addNoeud(n14);
//        p.addNoeud(n15);
//        p.addNoeud(n16);
//
//        Troncon t1 = new Troncon(n0, n1, "t1", 20.5, 1.5);
//        Troncon t2 = new Troncon(n0, n2, "t2", 20.5, 1.5);
//        Troncon t3 = new Troncon(n0, n3, "t3", 20.5, 1.5);
//        Troncon t4 = new Troncon(n0, n4, "t4", 20.5, 1.5);
//        Troncon t5 = new Troncon(n0, n5, "t5", 20.5, 1.5);
//        Troncon t6 = new Troncon(n0, n6, "t6", 20.5, 1.5);
//        Troncon t7 = new Troncon(n0, n7, "t7", 20.5, 1.5);
//        Troncon t8 = new Troncon(n0, n8, "t8", 20.5, 1.5);
//        Troncon t9 = new Troncon(n0, n9, "t9", 20.5, 1.5);
//        Troncon t10 = new Troncon(n0, n10, "t10", 20.5, 1.5);
//        Troncon t11 = new Troncon(n0, n11, "t11", 20.5, 1.5);
//        Troncon t12 = new Troncon(n0, n12, "t12", 20.5, 1.5);
//        Troncon t13 = new Troncon(n0, n13, "t13", 20.5, 1.5);
//        Troncon t14 = new Troncon(n0, n14, "t14", 20.5, 1.5);
//        Troncon t15 = new Troncon(n0, n15, "t15", 20.5, 1.5);
//        Troncon t16 = new Troncon(n0, n16, "t16", 20.5, 1.5);
//        p.addTroncon(t1);
//        p.addTroncon(t2);
//        p.addTroncon(t3);
//        p.addTroncon(t4);
//        p.addTroncon(t5);
//        p.addTroncon(t6);
//        p.addTroncon(t7);
//        p.addTroncon(t8);
//        p.addTroncon(t9);
//        p.addTroncon(t10);
//        p.addTroncon(t11);
//        p.addTroncon(t12);
//        p.addTroncon(t13);
//        p.addTroncon(t14);
//        p.addTroncon(t15);
//        p.addTroncon(t16);
//        
//        return p;
//    }
    
    //--------------------------------
    //  Geter - Seter
    public JScrollPane getPanneauPlan() {
        return panneauPlan;
    }

    public void setPanneauPlan(JScrollPane panneauPlan) {
        this.panneauPlan = panneauPlan;
    }

    public FenetrePrincipale getFenParent() {
        return fenParent;
    }

    public void setFenParent(FenetrePrincipale fenParent) {
        this.fenParent = fenParent;
    }
}
