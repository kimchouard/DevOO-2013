/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;
import java.util.LinkedList;

/**
 *Classe Itineraire, à but utilitaire car elle stocke la succession de troncons 
 * pour aller d'une livraison d'id<code>prevLivraisonId</code> à une livraison
 * d'id <code>nextLivraisonId</code>
 * @author cedric dupuis
 */
public class Itineraire {
    /**
     * Entier, id de la livraison de départ
     */
    private int prevLivraisonId;
    
    /**
     * Entier, id de la livraison d'arrivée
     */
    private int nextLivraisonId;
    
    /**
     * Liste chainée de Troncons, ordonnée 
     */
    private LinkedList<Troncon> ensembleTroncons;
    
    /**
     * Constructeur, initialise la liste ensembleTroncons
     */
    public Itineraire(){
        ensembleTroncons = new LinkedList();
    }
    
    /**
     * Setter
     * @param int previous 
     */
    public void setPrevLivraisonId(int previous){
        prevLivraisonId = previous;
    }
    /**
     * Getter
     * @return int prevLivraisonId
     */
    public int getPrevLivraisonId(){
        return prevLivraisonId;
    }
    
    /**
     * Setter
     * @param int next 
     */
    public void setNextLivraisonId(int next){
        nextLivraisonId = next;
    }
    
    /**
     * Getter
     * @return int nextLivraisonId 
     */
    public int getNextLivraisonId(){
        return nextLivraisonId;
    }
    
    /**
     * Setter
     * @param LinkedList<Troncon> ensemble 
     */
    public void setEnsembleTroncons(LinkedList<Troncon> ensemble){
        ensembleTroncons = ensemble;
    }
    
    /**
     * Add Troncon to ensembleTroncons
     * @param Troncon troncon 
     */
    public void addTroncon(Troncon troncon){
        ensembleTroncons.add(troncon);
    }
    
    /**
     * Remove Troncon from ensembleTroncon
     * @param Troncon troncon 
     */
    public void removeTroncon(Troncon troncon){
        ensembleTroncons.remove(troncon);
    }
    
    /**
     * Méthode avancée pour nettoyer la liste (plus utilisée que les remove)
     * Plus efficace sur une LinkedList
     * Clear ensembleTroncon
     */
    public void clearEnsembleTroncons(){
        ensembleTroncons.clear();
    }
    
    /**
     * Getter
     * @return LinkedList<Troncon> ensembleTroncon
     */
    public LinkedList<Troncon> getEnsembleTroncons(){
        return ensembleTroncons;
    }
}
