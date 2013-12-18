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
        this.commandeControleur = new ControllerCommand(fenParent);
        this.lecteurXml = new LecteurXml();
        this.controleurLivraison = new ControleurLivraison(this);
        this.commandeControleur = new ControllerCommand(this.fenParent);
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
        
            // rajouter
            controleurLivraison.rafraichirVueListLivraison(t, this.panneauLiv );
            //controleurLivraison.afficherListLivraison(this.panneauLiv);
        } else {
            System.out.println("Error: Merci de charger un plan avant de charger des livraisons.");
        }
    }

    public void chargerDebug() {
        try {
            Tournee t = initDebug();
            controleurPlan.setTournee(t);
            controleurPlan.scaleAutoVuePlan(panneauPlan);
            controleurPlan.rafraichirVuePlan(t, panneauPlan);
            controleurPlan.afficherPlan(panneauPlan);
        } catch (Exception ex) {
            Logger.getLogger(ControleurPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void selectLivraison(Livraison liv) {
        // éclairer la bonne livraison
        this.controleurLivraison.afficherUneLivraison(this.panneauLiv);

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

    private Tournee initDebug() throws Exception {Tournee tournee = new Tournee();
        Noeud entrepot = new Noeud(0, 0, 0);
        tournee.setEntrepot(entrepot);
        PlageHoraire ph = new PlageHoraire();
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date debut = formatter.parse("12:00:00");
        Date fin = formatter.parse("13:00:00");
        ph.setDebut(debut);
        ph.setFin(fin);
        Noeud a = new Noeud(1, 1, 1);
        Noeud b = new Noeud(2,2,2);
        Noeud c = new Noeud(3,3,3);
        Noeud d = new Noeud(4,4,4);
        Troncon t1 = new Troncon(entrepot, a, null, 1, 2);
        Troncon t5 = new Troncon(a, entrepot, null, 5, 25);
        Troncon t2 = new Troncon(entrepot, c, null, 5, 25);
        Troncon t8 = new Troncon(c, d, null, 5, 25);
        Troncon t9 = new Troncon(d, b, null, 5, 25);
        Troncon t4 = new Troncon(b, a, null, 5, 25);
        Troncon t3 = new Troncon(c, entrepot, null, 1, 2);
        Troncon t10 = new Troncon(a, b, null, 1, 2);
        Troncon t6 = new Troncon(b, d, null, 1, 2);
        Troncon t7 = new Troncon(d, c, null, 1, 2);
        Livraison l2 = new Livraison();
        l2.setDestination(b);
        l2.setHorraire(ph);
        Livraison l3 = new Livraison();
        l3.setDestination(c);
        l3.setHorraire(ph);
        Plan plan = new Plan();
        plan.addNoeud(entrepot);
        plan.addNoeud(a);
        plan.addNoeud(b);
        plan.addNoeud(c);
        plan.addNoeud(d);
        plan.addTroncon(t1);
        plan.addTroncon(t2);
        plan.addTroncon(t3);
        plan.addTroncon(t4);
        plan.addTroncon(t5);
        plan.addTroncon(t6);
        plan.addTroncon(t7);
        plan.addTroncon(t8);
        plan.addTroncon(t9);
        plan.addTroncon(t10);
        tournee.setPlan(plan);
        tournee.addLivraison(l2);
        tournee.addLivraison(l3);
        System.out.println("coucou");
        GraphUtil instance = new GraphUtil(tournee);
        for(int i=0;i<instance.getNbVertices();i++)
        {
            System.out.println("i: "+i);
            System.out.println("prev: "+instance.getEnsembleTrajets().get(i).getPrevLivraison().getDestination().getId());
            System.out.println("next: "+instance.getEnsembleTrajets().get(i).getNextLivraison().getDestination().getId());
        }      
        return tournee;
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
}
