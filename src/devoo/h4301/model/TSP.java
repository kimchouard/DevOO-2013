package devoo.h4301.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import solver.ResolutionPolicy;
import solver.Solver;
import solver.constraints.IntConstraintFactory;
import solver.search.loop.monitors.SearchMonitorFactory;
import solver.search.strategy.IntStrategyFactory;
import solver.variables.IntVar;
import solver.variables.VariableFactory;

/**
 * @author Christine Solnon, Jean-Guillaume Fages
 */

public class TSP {

	private int[] next;
	private int totalCost;
	private SolutionState state;
	private Graph graph;
        private ArrayList<Livraison> tableFinal;
        private Map<Livraison,String> mapHeureExacte;

	public TSP(Graph graph) {
		state = SolutionState.NO_SOLUTION_FOUND;
		this.graph = graph;
	}

	/**
	 * Searches for a tour in <code>graph</code> lower than <code>bound</code> and tries to prove its optimality in less than <code>timeLimit</code> milliseconds.
	 * @param timeLimit a limit of CPU time in milliseconds
	 * @param bound an upper bound on the total cost of the tour
	 * @return NO_SOLUTION_FOUND, if no tour lower than <code>bound</code> has been found within <code>timeLimit</code> milliseconds,<br>
	 * SOLUTION_FOUND, if a tour lower than <code>bound</code> has been found, but its optimality has not been proven within <code>timeLimit</code> milliseconds,<br> 
	 * OPTIMAL_SOLUTION_FOUND, if a tour lower than <code>bound</code> has been found, and its optimality has been proven,<br>
	 * or INCONSISTENT, if there does not exist a tour lower than <code>bound</code>.
	 */
	public SolutionState solve(int timeLimit, int bound) {
		int n = graph.getNbVertices();
		int minCost = graph.getMinArcCost();
		int maxCost = graph.getMaxArcCost();
		int[][] cost = graph.getCost();
		next = new int[n];
		Solver solver = new Solver();

		// Create variables
		// xNext[i] = vertex visited after i
		IntVar[] xNext = new IntVar[n];
		for (int i = 0; i < n; i++)
			xNext[i] = VariableFactory.enumerated("Next " + i, graph.getSucc(i), solver);
		// xCost[i] = cost of arc (i,xNext[i])
		IntVar[] xCost = VariableFactory.boundedArray("Cost ", n, minCost, maxCost, solver);
		// xTotalCost = total cost of the solution
		IntVar xTotalCost = VariableFactory.bounded("Total cost ", n*minCost, bound - 1, solver);

		// Add constraints
		for (int i = 0; i < n; i++) 
			solver.post(IntConstraintFactory.element(xCost[i], cost[i], xNext[i], 0, "none"));
		solver.post(IntConstraintFactory.circuit(xNext,0));
		solver.post(IntConstraintFactory.sum(xCost, xTotalCost));

		// limit CPU time
		SearchMonitorFactory.limitTime(solver,timeLimit);
		// set the branching heuristic (branch on xNext only by selecting smallest domains first)
		solver.set(IntStrategyFactory.firstFail_InDomainMin(xNext));
		// try to find and prove the optimal solution
		solver.findOptimalSolution(ResolutionPolicy.MINIMIZE,xTotalCost);
		// record solution and state
		if(solver.getMeasures().getSolutionCount()>0){
			if (solver.getSearchLoop().hasReachedLimit()) 
				state = SolutionState.SOLUTION_FOUND;
			else 
				state = SolutionState.OPTIMAL_SOLUTION_FOUND;
			for(int i=0;i<n;i++) next[i] = xNext[i].getValue();
			totalCost = xTotalCost.getValue();
		}
		else {
			if(solver.getSearchLoop().hasReachedLimit()) 
				state = SolutionState.NO_SOLUTION_FOUND;
			else 
				state = SolutionState.INCONSISTENT;
		}
                translationAndClean();
                verifPH();
		return state;
	}
        
        /**
         * Remplissage de la map mapHeureExacte qui lie une livraison à l'horaire exacte 
         * verifie qu'une livraison ne depasse pas de sa plage horaire, si oui notifie au superviseur
         */
        private void verifPH()
        {
                Map<Livraison, Integer> dico = graph.getDictionnaireRetour();
                mapHeureExacte = new HashMap<>();
                int[][] cost = graph.getCost();
                int avanceePHheure = tableFinal.get(1).getHoraire().getDebut().getHours();
                int avanceePHmin = 60*avanceePHheure + tableFinal.get(1).getHoraire().getDebut().getMinutes();
                int horaireAPasDepasserHeure = tableFinal.get(1).getHoraire().getFin().getHours();
                int horaireAPasDepasserMin = 60*horaireAPasDepasserHeure + tableFinal.get(1).getHoraire().getFin().getMinutes();
                Date plageEnCours = tableFinal.get(1).getHoraire().getDebut();
                int correspondantLivraison1;
                int correspondantLivraison2;
                
                for(int i = 1; i<tableFinal.size();i++)
                {
                    if(!plageEnCours.equals(tableFinal.get(i).getHoraire().getDebut()))
                    {
                        avanceePHheure = tableFinal.get(i).getHoraire().getDebut().getHours();
                        avanceePHmin = 60*avanceePHheure+ tableFinal.get(i).getHoraire().getDebut().getMinutes();
                        horaireAPasDepasserHeure = tableFinal.get(i).getHoraire().getFin().getHours();
                        horaireAPasDepasserMin = 60*horaireAPasDepasserHeure + tableFinal.get(i).getHoraire().getFin().getMinutes();
                        plageEnCours = tableFinal.get(i).getHoraire().getDebut();
                    }
                    correspondantLivraison1 = dico.get(tableFinal.get(i-1));
                    correspondantLivraison2 = dico.get(tableFinal.get(i));
                    int arrondiCout = (int)(cost[correspondantLivraison1][correspondantLivraison2]/60);
                    avanceePHmin = avanceePHmin + arrondiCout +10;
                    
                    if (avanceePHmin > horaireAPasDepasserMin)
                    {
                        System.out.println("La livraison "+tableFinal.get(i).getDestination().getId()+"n'a pas pu être placée dans sa plage horaire , vous pouvez la supprimer sur cette magnifique interface");
                    }
                    
                    String heureExacte = (int)Math.floor(avanceePHmin/60) +" : "+avanceePHmin%60;
                    mapHeureExacte.put(tableFinal.get(i),heureExacte);
                    
                }

        }
        
        
        
        /**
         * Traduit next[] en un tableau de livraisons grâce à la map de graph
         * et nettoie ensemble trajets des itineraires non utilisés
         */
        public void translationAndClean(){
            tableFinal = new ArrayList<>();
            int cpt;
            tableFinal.add(graph.getDictionnaire().get(0));
            cpt = next[0];
            for(int i = 0; i<next.length;i++)
            {
               tableFinal.add(graph.getDictionnaire().get(cpt));
               cpt = next[cpt];
            }

                // Clean ensembleTrajets
                int tableIt=0;
                ArrayList<Itineraire>listeItineraires = graph.getEnsembleTrajets();
                while(tableIt<tableFinal.size()-1)
                {
                    for(int i=0;i<listeItineraires.size();i++)
                    { 
                        if(listeItineraires.get(i).getPrevLivraison() == tableFinal.get(tableIt))
                        {
                            if(listeItineraires.get(i).getNextLivraison() != tableFinal.get(tableIt+1))
                            {
                                listeItineraires.remove(i);
                            }
                        }   
                    }                
                    tableIt++;
                }
                // Bouclage en rentrant à l'entrepot
                for(int i=0;i<listeItineraires.size();i++)
                { 
                    if(listeItineraires.get(i).getPrevLivraison() == tableFinal.get(tableFinal.size()-1))
                    {
                        if(listeItineraires.get(i).getNextLivraison() != tableFinal.get(0))
                        {
                            listeItineraires.remove(i);
                        }
                    }   
                }
                
                graph.setEnsembleTrajets(listeItineraires);
            }

	/**
	 * @return an array <code>next</code> such that <code>next[i]</code> gives the vertex visited just after <code>i</code> in the last computed solution
	 * @throws NullPointerException If <code>this.getSolutionState()</code> not in <code>{SOLUTION_FOUND, OPTIMAL_SOLUTION_FOUND}</code>
	 */
	public int[] getNext() throws NullPointerException {
		if ((state == SolutionState.NO_SOLUTION_FOUND) || (state == SolutionState.INCONSISTENT))
			throw new NullPointerException();
		return next;
	}

	/**
	 * @return the total cost of the last computed solution 
	 * @throws NullPointerException If <code>this.getSolutionState()</code> not in <code>{SOLUTION_FOUND, OPTIMAL_SOLUTION_FOUND}</code>
	 */
	public int getTotalCost() throws NullPointerException {
		if ((state == SolutionState.NO_SOLUTION_FOUND) || (state == SolutionState.INCONSISTENT))
			throw new NullPointerException();
		return totalCost;
	}

	/**
	 * @return NO_SOLUTION_FOUND if no solution has been found during the last call to solve,<br>
	 * SOLUTION_FOUND if a solution has been found during the last call to solve but its optimality has not been proven,<br>
	 * OPTIMAL_SOLUTION_FOUND if a solution has been found during the last call to solve, and its optimality proven,<br>
	 * INCONSISTENT if the last call to solve has proven that the problem does not have a solution. 
	 */
	public SolutionState getSolutionState() {
		return state;
	}
        /*
         * return TableFinal contenant les livraisons dans l'ordre de passage
         */
        public ArrayList<Livraison> getTableFinal() {
            return tableFinal;
        }
        
        /*
         * return mapHeureExacte qui contient les heures exactes de passage à une livraison
         */
        public Map<Livraison, String> getMapHeureExacte() {
            return mapHeureExacte;
        }


}
