/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;

import java.util.LinkedList;

/**
 *
 * @author pmdartus
 */
public class Itineraire {
     private int prevLivraisonId;
    private int nextLivraisonId;
    private LinkedList<Troncon> ensembleTroncons;
    
    public Itineraire(){
        ensembleTroncons = new LinkedList();
    }
    
    public void setPrevLivraisonId(int previous){
        prevLivraisonId = previous;
    }
    
    public int getPrevLivraisonId(){
        return prevLivraisonId;
    }
    
    public void setNextLivraisonId(int next){
        nextLivraisonId = next;
    }
    
    public int getNextLivraisonId(){
        return nextLivraisonId;
    }
    
    public void setEnsembleTroncons(LinkedList<Troncon> ensemble){
        ensembleTroncons = ensemble;
    }
    
    public void addTroncon(Troncon troncon){
        ensembleTroncons.add(troncon);
    }
    
    public void removeTroncon(Troncon troncon){
        ensembleTroncons.remove(troncon);
    }
    
    public void clearEnsembleTroncons(){
        ensembleTroncons.clear();
    }
    
    public LinkedList<Troncon> getEnsembleTroncons(){
        return ensembleTroncons;
    }
}
