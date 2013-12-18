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
public class ControllerGraph {
    private GraphUtil graphe;
    private TSP tsp;
    private Tournee tournee;
    
    private void BuildGraphe()throws MyException{
        
        graphe = new GraphUtil(Tournee.getInstance());
        tsp = new TSP(graphe);
        tournee = Tournee.getInstance();
    }
    
    //Lancé à chaque nouvelle demande de calcul
    public void UpdateGraphe(int delay) throws MyException{
        
        //Gérer les autres cas, surtout quand on trouve une solution pas assurée d'etre optimale
        switch(tsp.solve(delay, graphe.getMaxArcCost()))
        {
            case OPTIMAL_SOLUTION_FOUND:
                BuildGraphe();
                break;
            case SOLUTION_FOUND:
                break;
            case NO_SOLUTION_FOUND:
                break;
            case INCONSISTENT:
                break;
        }
    }
    
}
