package devoo.h4301.outils;

import devoo.h4301.model.Itineraire;
import devoo.h4301.model.Livraison;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Christine Solnon
 *
 */
public interface Graph {

	/**
	 * @return the maximal cost of an arc of <code>this</code>
	 */
	public abstract int getMaxArcCost();

	/**
	 * @return the minimal cost of an arc of <code>this</code>
	 */
	public abstract int getMinArcCost();

	/**
	 * @return the number of vertices of <code>this</code>
	 */
	public abstract int getNbVertices();

	/**
	 * @return the <code>cost</code> matrix of <code>this</code>: for all vertices <code>i</code> and <code>j</code>,
	 * if <code>(i,j)</code> is an arc of <code>this</code>, then <code>cost[i][j]</code> = cost of <code>(i,j)</code>, 
	 * otherwise <code>cost[i][j] = this.getMaxArcCost()+1</code>
	 */
	public abstract int[][] getCost();

	/**
	 * @param i a vertex such that <code>0 <= i < this.getNbVertices()</code>
	 * @return an array containing all successor vertices of <code>i</code> in <code>this</code>
	 * @throws ArrayIndexOutOfBoundsException If <code>i<0</code> or <code>i>=this.getNbVertices()</code>
	 */
	public abstract int[] getSucc(int i)
			throws ArrayIndexOutOfBoundsException;

	/**
	 * @param i a vertex such that <code>0 <= i < this.getNbVertices()</code>
	 * @return the number of successor vertices of <code>i</code> in <code>this</code>
	 * @throws ArrayIndexOutOfBoundsException If <code>i<0</code> or <code>i>=this.getNbVertices()</code>
	 */
	public abstract int getNbSucc(int i)
			throws ArrayIndexOutOfBoundsException;
        
        /**
	 * @return dictionnaire reliant un entier à une livraison
	 */
	public abstract Map<Integer,Livraison> getDictionnaire();
        
        /**
	 * @return ensembleTrajets une liste d'itinéraire'
	 */
	public abstract ArrayList<Itineraire> getEnsembleTrajets();
        
        /**
         * @param : une liste d'itinéraires
	 *  donne la valeur d' ensemble à ensembleTrajets
	 */
	public abstract void setEnsembleTrajets(ArrayList<Itineraire> ensemble);
        
        /**
	 * @return DictionnaireRetour un dictionnaire reliant une livraison à un integer
	 */
        public abstract Map<Livraison, Integer> getDictionnaireRetour();

}
