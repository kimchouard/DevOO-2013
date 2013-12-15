package devoo.h4301.model;

import devoo.h4301.outils.MyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;



/**
 * @author LouisePonton & Cedric Dupuis
 *
 */

public class GraphUtil implements Graph {
	private int nbVertices;
	private int maxArcCost;
	private int minArcCost;
	private int[][] cost; 
	private ArrayList<Itineraire> ensembleTrajets;
	private ArrayList<ArrayList<Integer>> succ; 

	/**
	 * Creates a graph such that each vertex is connected to the vertices which are in the same duration of the day , or the duration +1 and
	 * such that the cost of each arc is a calculated integer with the speed and the lenght of each arc
	 * @param n a number of vertices such that <code>n > 0</code>
	 * @param d a degree such that <code>0 < d < n</code>
	 * @param min a minimal arc cost such that <code>0 < min</code>
	 * @param max a maximal arc cost such that <code>min < max</code>
	 */
        
	public GraphUtil(Tournee ens) throws MyException
	{	
                LinkedList<Livraison> tabLivraison = ens.getLivraisons();
                // Initialisation
		nbVertices = tabLivraison.size()+1;
                maxArcCost = 0;
                minArcCost = Integer.MAX_VALUE;
		cost = new int[nbVertices][nbVertices];
		for (int i = 0; i < nbVertices; i++) {
			for (int j = 0; j < nbVertices; j++) {
				cost[i][j] = Integer.MAX_VALUE;
			}
		}
		succ = new ArrayList<>();
		
		ArrayList<Integer> PlagesHoraires = getOrderedTabDuration(tabLivraison);
                enterIdSuccAndCost(PlagesHoraires,tabLivraison,ens);		
                
                // Definition of cost if it has not been defined yet
                for (int i = 0; i < nbVertices; i++) {
			for (int j = 0; j < nbVertices; j++) {
				if (cost[i][j] == Integer.MAX_VALUE) {
					cost[i][j] = maxArcCost + 1;
				}
			}
		}
	}
        
       // CREATE AN ORDERED TAB OF DURATIONS
        private ArrayList<Integer> getOrderedTabDuration(LinkedList<Livraison> tabLivraison)
        {
            int i;
            int unePH;
            boolean find = false ; 
            ArrayList<Integer> PlagesHoraires =  new ArrayList<>();
            
            for(i=0; i<nbVertices-1; i++)
            {
                    // Finding all the differents durations for all the vertices
                    unePH = tabLivraison.get(i).getHoraire().getDebut().getMinutes();
                    // Finding if the duration is already in the tab "PlagesHoraires" if yes find = true
                    int j = 0;
                    while(unePH>=PlagesHoraires.get(j))
                    {
                            if(PlagesHoraires.get(j) == unePH)
                            {
                                    find = true;
                            }
                            j++;
                    }
                    // if (find = false) enter the duration in the tab in the right order
                    if(find == false)
                    {
                        PlagesHoraires.add(j,unePH);
                    }
                    find = false;
            }
            return PlagesHoraires;
        }
        
        
        // ENTERING THE VERTICES IN SUCC AND COST 
        private void enterIdSuccAndCost(ArrayList<Integer> PlagesHoraires, LinkedList<Livraison> tabLivraison, Tournee ens) throws MyException
        {
               	int PH1,PH2;
               // Insert entrepot at the beginning
                int i;
                ArrayList<Integer> suivantEntrepot = new ArrayList<>();
		for(i=0; i<nbVertices-1; i++)
		{
			PH1 = tabLivraison.get(i).getHoraire().getDebut().getMinutes();
			int indexPH1 = PlagesHoraires.indexOf(PH1);
			if(indexPH1 == 0)
			{
                                suivantEntrepot.add(tabLivraison.get(i).getDestination().getId());
			}
		}
                succ.add(ens.getEntrepot().getId(),suivantEntrepot);

		// For each livraison minus entrepot
		for(i=0; i<nbVertices-1; i++)
		{
			PH1 = tabLivraison.get(i).getHoraire().getDebut().getMinutes();
			int indexPH1 = PlagesHoraires.indexOf(PH1);
			int noeud1 = tabLivraison.get(i).getDestination().getId();
                        ArrayList<Integer> l = new ArrayList<>();

			int j;
			for(j=0; j<nbVertices-1; j++)
			{
				PH2 = tabLivraison.get(j).getHoraire().getDebut().getMinutes();
				int indexPH2 = PlagesHoraires.indexOf(PH2);
				int noeud2 = tabLivraison.get(j).getDestination().getId();
				// If duration of vertex2 is = or +1 of duration of vertex 1 -> enter in succ
				if((indexPH1 == indexPH2) || (indexPH2 == indexPH1+1))
				{
					l.add(noeud2);
                                        Itineraire iti = new Itineraire();
                                        iti.setPrevLivraisonId(noeud1);
                                        iti.setNextLivraisonId(noeud2);
                                        LinkedList<Troncon> ensembleTroncons = getPath(noeud1,noeud2,ens); 
                                        cost[noeud1][noeud2] = calculCost(ensembleTroncons);
                                        iti.setEnsembleTroncons(ensembleTroncons);
                                        ensembleTrajets.add(iti);
                                        setMinMax(cost[noeud1][noeud2]);
				}
			}
			
			// Insert entrepot at the end
			if(indexPH1 == PlagesHoraires.size()-1) 
			{
				l.add(ens.getEntrepot().getId());
                                Itineraire iti = new Itineraire();
                                iti.setPrevLivraisonId(noeud1);
                                iti.setNextLivraisonId(ens.getEntrepot().getId());
                                LinkedList<Troncon> ensembleTroncons = getPath(noeud1,ens.getEntrepot().getId(),ens); 
                                cost[noeud1][ens.getEntrepot().getId()] = calculCost(ensembleTroncons);
                                iti.setEnsembleTroncons(ensembleTroncons);
                                ensembleTrajets.add(iti);
                                setMinMax(cost[noeud1][ens.getEntrepot().getId()]);
			}
			
			succ.add(noeud1,l);
		}
                
                
        }
        
        //calcul final cost by adding costs of Troncons
        private int calculCost(LinkedList<Troncon> itineraire)
        {
            double cout = 0;
            int Isize = itineraire.size();
            int i;
            for(i=0;i<Isize;i++){
                cout = cout + itineraire.get(i).getDuree();
            }
            return (int) cout;
        }
	
	
	//Calculating cost between pt1 and pt2 using the sortest path of Djikstra 
	public LinkedList<Troncon> getPath(int pt1, int pt2, Tournee ens) throws MyException
	{
		Plan P = ens.getPlan();
		ArrayList<Noeud> tabnoeuds = P.getNoeuds();
		int Psize = tabnoeuds.size();
		
		ArrayList<Integer> vu = new ArrayList<>();
		ArrayList<Integer> notvu = new ArrayList<>();
                // Link idNode and his predecessor in the shortest path
		Map<Integer, Integer> previous = new HashMap<>();
                // Link idNode and duration
		Map<Integer, Integer> duree = new HashMap<>();
                // Initialisation
                int i;
                for(i=0;i<Psize;i++)
                {
                    duree.put(tabnoeuds.get(i).getId(),-1);
                }
		duree.put(pt1,0);
		notvu.add(pt1);
		
		while(notvu.isEmpty() == false)
		{
                    // On trouve newNoeud : le noeud le plus proche de la source dans notvu
                    int newNoeud = getNoeudProche(notvu,duree);
                    // On l'ajoute a vu
                    vu.add(newNoeud);
                    // On l'enlève de notvu
                    notvu.remove(newNoeud);
                    // De tous les voisins de newNoeud on ajoute à duree previous et notvu les voisins dont les durées à partir de newNoeud sont mini
                    findDureeMini(newNoeud, ens, notvu,duree,previous,vu);
		}
		
                //Find path
		LinkedList<Integer> path = new LinkedList<>();
                if (previous.get(pt2) == null) 
                {
                  return null;
                }
                path.add(pt2);
                while (previous.get(pt2) != null) 
                {
                  pt2 = previous.get(pt2);
                  path.add(pt2);
                }
                
                LinkedList<Troncon> pathFinal = transformNoeudTroncon(path,ens);
                return pathFinal;
	}
        
        // Transform group of Noeuds into group of Troncons
        private LinkedList<Troncon> transformNoeudTroncon(LinkedList<Integer> path,Tournee ens) throws MyException
        {   
                ArrayList<Troncon> tabtroncons = ens.getPlan().getTroncons();
                int tronSize = tabtroncons.size();
                LinkedList<Troncon> pathFinal = new LinkedList<>();
                Troncon inter;
                boolean findTron = false;
                int pathSize = path.size();
                int cptPath;
                for(cptPath=0;cptPath<pathSize-1;cptPath++)
                {
                    int debut = path.get(cptPath); 
                    int fin = path.get(cptPath+1);
                    
                    int cptTron = 0;
                    while((cptTron<tronSize)||(findTron==false))
                    {
                        if((tabtroncons.get(cptTron).getOrigine().getId()==debut ) 
                                &&(tabtroncons.get(cptTron).getDestination().getId()== fin)) 
                        {
                            pathFinal.add(tabtroncons.get(cptTron));
                            findTron = true ;
                        }
                        cptTron++;
                    }
                    if ((cptTron == tronSize)&&(findTron==false))
                    {
                        throw new MyException("Troncon non trouvé dans le plan pour les points" + debut + "et "+ fin);//EXCEPTION
                    }
                    findTron = false;
                }  
                return pathFinal;      
        }
        
        //De tous les noeuds dans not vu, on retourne le noeud de celui qui a la plus petite duree depuis la source
	private int getNoeudProche(ArrayList<Integer> notvu, Map<Integer, Integer> duree)
	{
            int mini = -1;
            int Nsize = notvu.size();
            int i ;
            for(i=0;i<Nsize;i++)
            {
                if(mini == -1)
                {
                    mini = notvu.get(i);
                }
                else if(getShortestDuration(notvu.get(i),duree) < getShortestDuration(mini,duree))
                {
                    mini = notvu.get(i);
                }
            }
            return mini;
	}
	
        // De tous les voisins de @param newNoeud on ajoute à duree previous et notvu les voisins dont les durées à la source à partir de newNoeud sont mini
	private void findDureeMini(int newNoeud, Tournee ens, ArrayList<Integer> notvu, Map<Integer, Integer> duree, Map<Integer, Integer> previous,ArrayList<Integer> vu) 
	{
		ArrayList<Integer> voisins = getVoisins(newNoeud,ens,vu);
		int voisinsSize = voisins.size() ;
                int i;
		for (i=0;i<voisinsSize;i++) 
		{
		  if (getShortestDuration(voisins.get(i),duree) > getShortestDuration(newNoeud,duree)+ getDuration(newNoeud, voisins.get(i), ens)) 
		  {
			duree.put(voisins.get(i), getShortestDuration(newNoeud,duree)+ getDuration(newNoeud, voisins.get(i), ens));
			previous.put(voisins.get(i), newNoeud);
			notvu.add(voisins.get(i));
		  }
		}
	}
	
	//Cherche les voisins non encore vus d'un point @param noeud pris comme origine 
	private ArrayList<Integer> getVoisins(int noeud, Tournee ens,ArrayList<Integer> vu)
	{
		ArrayList<Troncon> tabTroncons = ens.getPlan().getTroncons();
                ArrayList<Integer> voisins = new ArrayList<>();
                int Tsize = tabTroncons.size();
                int i =0;
                while(i<Tsize)
                {
		 if((tabTroncons.get(i).getOrigine().getId() == noeud)
                         &&(vu.contains(tabTroncons.get(i).getDestination().getId())==false))
                 {
                     voisins.add(tabTroncons.get(i).getDestination().getId());
                 }
                i++;
                }
                return voisins;
	}
         
        //Cherche la durée entre un point et un autre qui sont VOISINS 
	private int getDuration(int noeud1, int noeud2, Tournee ens)
	{
                ArrayList<Troncon> tabTroncons = ens.getPlan().getTroncons();
                int Tsize = tabTroncons.size();
                int i =0;
                while(i<Tsize)
                {
		 if((tabTroncons.get(i).getOrigine().getId() == noeud1)
                     && (tabTroncons.get(i).getDestination().getId() == noeud2))
                 {
                     return (int) tabTroncons.get(i).getDuree();
                 }
                i++;
                }
                return -1;
	}
	
	// Retourne la durée d'un noeud à la source si noeud n'est pas entré dans durée retourne valeur max
	private int getShortestDuration(int pt, Map<Integer, Integer> duree ) 
        {
            int d = duree.get(pt);
            if (d == -1) {
              return Integer.MAX_VALUE;
            }
            else {
              return d;
            }
          }
	
	
        @Override
	public int getMaxArcCost() {
		return maxArcCost;
	}

        @Override
	public int getMinArcCost() {
		return minArcCost;
	}

        @Override
	public int getNbVertices() {
		return nbVertices;
	}

        @Override
	public int[][] getCost(){
		return cost;
	}

        @Override
	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException{
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		int[] tab = new int[succ.get(i).size()];
		for(int j=0;j<tab.length;j++){
			tab[j] = succ.get(i).get(j);
		}
		return tab;
	}


        @Override
	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		return succ.get(i).size();
	}
        
                                                    
        // Defining min and max 
        private void setMinMax (int newCout)
        { 
            if(newCout>maxArcCost){
                maxArcCost = newCout;
            }
            if(newCout<minArcCost){
                minArcCost = newCout;
            }
        }

         public ArrayList<Itineraire> getEnsembleTrajets(){
            return ensembleTrajets;
        }
        
        public void setEnsembleTrajets(ArrayList<Itineraire> ensemble){
            ensembleTrajets = ensemble;
        }
        
        public void clearEnsembleTrajets(){
            ensembleTrajets.clear();
        }
        
        public void addItineraire(Itineraire iti){
            ensembleTrajets.add(iti);
        }
        
        public void removeItineraire(Itineraire iti){
            ensembleTrajets.remove(iti);
        }

}
