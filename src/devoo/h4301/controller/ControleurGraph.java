/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.controller;

import devoo.h4301.model.GraphUtil;
import devoo.h4301.model.Itineraire;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.SolutionState;
import devoo.h4301.model.TSP;
import devoo.h4301.model.Tournee;
import devoo.h4301.outils.MyException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author cdupuis
 */
public class ControleurGraph {
    private GraphUtil graphe;
    private TSP tsp;
    
    
    //Lancé à chaque nouvelle demande de calcul
    public int UpdateGraphe(Tournee t) throws MyException{
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
    }
    
    public ArrayList<Itineraire> getItineraires() {
        if (graphe != null) {
            return graphe.getEnsembleTrajets();
        } else {
            return new ArrayList<Itineraire>();
        }
    }
    
    public ArrayList<Livraison> getLivOrdered() {
        if (tsp != null) {
            return tsp.getTableFinal();
        } else {
            return new ArrayList<Livraison>();
        }
    }

    public GraphUtil getGraphe() {
        return graphe;
    }

    public void setGraphe(GraphUtil graphe) {
        this.graphe = graphe;
    }

    public TSP getTsp() {
        return tsp;
    }

    public void setTsp(TSP tsp) {
        this.tsp = tsp;
    }
    
    
}
