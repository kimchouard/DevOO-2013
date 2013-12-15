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
    
    public void BuildGraphe()throws MyException{
        
        graphe = new GraphUtil(Tournee.getInstance());
        tsp = new TSP(graphe);
        tournee = Tournee.getInstance();
    }
    
    //Lancé à chaque ajout/suppression de livraison
    //Précondition: le tableau des livraisons a été mis à jour (ajout/suppression déjà reportés), 
    //les deux listes contiennent les mêmes éléments
    public void UpdateGraphe(int delay){
        
        //Gérer les autres cas, surtout quand on trouve une solution pas assurée d'etre optimale
        switch(tsp.solve(delay, graphe.getMaxArcCost()))
        {
            case OPTIMAL_SOLUTION_FOUND:
                int[] nextTable = tsp.getNext();
                int tableIt = 0;
                int max = graphe.getNbVertices();
                LinkedList<Livraison> livraisons = tournee.getLivraisons();
                Livraison intrus = null;
                for(int i=0;i<max;i++)
                {
                    //on compare notre liste de livraisons au nouveau calcul
                    if(livraisons.get(i).getDestination().getId() == nextTable[tableIt])
                    {
                        // tant que les livraisons sont ordonnées, on continue
                        tableIt++;
                    }
                    else
                    {
                        //première incohérence, on la stocke
                        if(intrus == null)
                        {
                            intrus = livraisons.get(i);
                        }
                        //deuxième incohérence, signifie qu'il faut remplacer à cette position
                        else
                        {
                            //signifie qu'il y a eu permutation, on permute
                            if(intrus == livraisons.get(i))
                            {
                                
                            }
                            //signifie qu'il y a eu ajout ou suppression
                            if(intrus != livraisons.get(i))
                            {
                                
                            }
                        }
                    }
                }
                graphe.getEnsembleTrajets();
                break;
            case SOLUTION_FOUND:
                break;
            case NO_SOLUTION_FOUND:
                break;
            case INCONSISTENT:
                break;
        }
    }
    
    public void CleanGraph() throws MyException{
        graphe.clearEnsembleTrajets();
        int[] nextTable = tsp.getNext();
        int tableIt=0;
        while(tableIt<nextTable.length)
        {
            Itineraire iti = new Itineraire();
            iti.setEnsembleTroncons(graphe.getPath(nextTable[tableIt],nextTable[tableIt+1],tournee));
            iti.setPrevLivraisonId(nextTable[tableIt]);
            tableIt++;
            iti.setNextLivraisonId(nextTable[tableIt]);
            graphe.addItineraire(iti);
        }
    }
    
}
