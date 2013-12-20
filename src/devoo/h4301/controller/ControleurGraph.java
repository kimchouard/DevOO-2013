/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.controller;

import devoo.h4301.outils.GraphUtil;
import devoo.h4301.model.Itineraire;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.PlageHoraire;
import devoo.h4301.outils.SolutionState;
import devoo.h4301.outils.TSP;
import devoo.h4301.model.Tournee;
import devoo.h4301.outils.MyException;
import devoo.h4301.views.FenetrePrincipale;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe ControleurGraph.
 * Construit GraphUtil et TSP et assure la gestion de leur mise à jour, 
 * le formatage des données pour la vue et leur écriture (impression texte)
 */
public class ControleurGraph {
    private GraphUtil graphe;
    private TSP tsp;
    private ControleurPrincipal controleurPrincipal;
    private FenetrePrincipale fenetrePrincipale;
    
    /**
     * 
     * @param controleurPrincipal
     * @param fenParent 
     */
    public ControleurGraph(ControleurPrincipal controleurPrincipal, FenetrePrincipale fenParent){
        this.fenetrePrincipale = fenParent;
        this.controleurPrincipal = controleurPrincipal;
        this.fenetrePrincipale.updatePrintState(false);
    }
    
    
    
    /**
     * Met à jour le graphe des tournées, regénère ce dernier ainsi que tsp.
     * Lance ensuite le calcul du plus court chemin.
     * @param Tournee t
     * @return
     * @throws MyException 
     */
    public int UpdateGraphe(Tournee t) throws MyException{
        try {
            graphe = new GraphUtil(t);
            tsp = new TSP(graphe);
            //Gérer les autres cas, surtout quand on trouve une solution pas assurée d'etre optimale
            switch(tsp.solve(1000, graphe.getMaxArcCost()*graphe.getNbVertices()))
            {
                case OPTIMAL_SOLUTION_FOUND:
                    return 1;
                case SOLUTION_FOUND:
                    return 1;
                case NO_SOLUTION_FOUND:
                    return 0;
                case INCONSISTENT:
                    return 0;
            }
            
            return 0;
        } catch (Exception ex) {
            this.resetGraph();
            System.out.println("Impossible de trouver un chemin valable.");
            return 0;
        }
    }
    
    /**
     * Getter, retourne une liste d'itinéraires, vide en cas d'erreur 
     * (le graphe n'est pas sensé être nul)
     * @return ArrayList<Itineraire>
     */
    public ArrayList<Itineraire> getItineraires() {
        if (graphe != null) {
            return graphe.getEnsembleTrajets();
        } else {
            return new ArrayList<Itineraire>();
        }
    }
    /**
     * Getter, retourne une liste de livraisons, vide en cas d'erreur 
     * (le graphe n'est pas sensé être nul)
     * @return ArrayList<Livraison>
     */
    public ArrayList<Livraison> getLivOrdered() {
        if (tsp != null) {
            return tsp.getTableFinal();
        } else {
            return new ArrayList<Livraison>();
        }
    }
    
    /**
     * Getter, retourne le GraphUtil
     * @return GraphUtil
     */
    public GraphUtil getGraphe() {
        return graphe;
    }

    /**
     * Setter
     * @param graphe 
     */
    public void setGraphe(GraphUtil graphe) {
        this.graphe = graphe;
    }

    /**
     * Getter, retourne le Tsp
     * @return TSP
     */
    public TSP getTsp() {
        return tsp;
    }

    /**
     * Setter
     * @param tsp 
     */
    public void setTsp(TSP tsp) {
        this.tsp = tsp;
    }
    
    /**
     * Méthode utilitaire, met à null le graphe et tsp
     */
    public void resetGraph() {
        graphe = null;
        tsp = null;
    }
    
    /**
     * Génère le fichier texte représentant la feuille de route version papier.
     * Récupère les erreurs d'input/output et celles de chemin de fichier sous 
     * forme d'exceptions.
     * @param tour
     * @throws MyException 
     */
    public void printTrip(Tournee tour) throws MyException{
        try{
            FileWriter fw = new FileWriter ("FDR.txt");
            BufferedWriter bw = new BufferedWriter ( fw ) ; 
            bw.newLine(); 
            try (PrintWriter pw = new PrintWriter ( bw )) {
                pw.println("Tournee du jour") ;
                pw.println();
                LinkedList<PlageHoraire> plages = tour.getHoraires();
                ArrayList<Livraison> livraisons = tsp.getTableFinal();
                ArrayList<Itineraire> trajets = graphe.getEnsembleTrajets();
                pw.println("Départ de l'entrepot");
                pw.println();
                for(int i=0;i<plages.size();i++)
                {
                    for(int j=0;j<livraisons.size()-1;j++)
                    {

                        pw.println();
                        pw.println("Aller à la prochaine livraison: "+livraisons.get(j).getDestination().getId() + " a réaliser " + livraisons.get(j).getHoraire().toString());
                        for(int k=0;k<trajets.size();k++)
                        {
                            if((trajets.get(k).getPrevLivraison() == livraisons.get(j)) && (trajets.get(k).getNextLivraison() == livraisons.get(j+1)))
                            {
                                //match livraison et trajet
                                Itineraire iti = trajets.get(k);
                                String nomRue="";
                                for(int l=0;l<iti.getEnsembleTroncons().size();l++)
                                {
                                    
                                    if( (nomRue.isEmpty()) || !(iti.getEnsembleTroncons().get(l).getNomRue().equals(nomRue)))
                                    {
                                        nomRue = iti.getEnsembleTroncons().get(l).getNomRue();
                                        pw.println("Prendre la rue "+nomRue);
                                    }
                                }
                            }
                        }
                    }
                }
                pw.println();
                pw.println("Retour à l'entrepot");
            }
            
        } catch(IOException e){
            
        }
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("FDR.txt");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
        // no application registered for PDFs
            }
        }
    }
}
