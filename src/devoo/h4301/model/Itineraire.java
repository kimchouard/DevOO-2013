/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;
import java.util.LinkedList;

import java.util.LinkedList;

/**
 *Classe Itineraire, à but utilitaire car elle stocke la succession de troncons 
 * pour aller d'une livraison <code>prevLivraison</code> à une livraison
 * <code>nextLivraison</code>
 * @author cedric dupuis
 */
public class Itineraire {
    /**
     * Livraison de départ
     */
    private Livraison prevLivraison;
    
    /**
     *Livraison d'arrivée
     */
    private Livraison nextLivraison;
    
    /**
     * Liste chainée de Troncons, ordonnée de la livraison next à la livraison prev
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
     * @param Livraison previous 
     */
    public void setPrevLivraison(Livraison previous){
        prevLivraison = previous;
    }
    /**
     * Getter
     * @return Livraison prevLivraison
     */
    public Livraison getPrevLivraison(){
        return prevLivraison;
    }
    
    /**
     * Setter
     * @param Livraison next 
     */
    public void setNextLivraison(Livraison next){
        nextLivraison = next;
    }
    
    /**
     * Getter
     * @return Livraison nextLivraison 
     */
    public Livraison getNextLivraison(){
        return nextLivraison;
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
