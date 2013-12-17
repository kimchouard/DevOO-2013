/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import java.util.LinkedList;

/**
 * Classe représentant un itinéraire. Un itinéraire est un ensemble de tronçon
 * ordonnés constituant un trajet entre deux noeuds.
 *
 * @author pmdartus
 */
public class Itineraire {

    private int prevLivraisonId;
    private int nextLivraisonId;
    private LinkedList<Troncon> ensembleTroncons;

    /**
     *Constructeur
     */
    public Itineraire() {
        ensembleTroncons = new LinkedList();
    }

    /**
     *Setter sur l'id du noeud précèdent
     * @param previous id du noeud précèdent
     */
    public void setPrevLivraisonId(int previous) {
        prevLivraisonId = previous;
    }

    /**
     *Getter sur l'id du noeud précèdent
     * @return l'id du noeud précèdent
     */
    public int getPrevLivraisonId() {
        return prevLivraisonId;
    }

    /**
     *Setter sur le prochain noeud
     * @param next id du prochain noeud
     */
    public void setNextLivraisonId(int next) {
        nextLivraisonId = next;
    }

    /**
     *Getter sur le prochain noeud
     * @returnl'id du prochain noeud
     */
    public int getNextLivraisonId() {
        return nextLivraisonId;
    }

    /**
     *Setter sur un ensemble de tronçon
     * @param ensemble liste de tronçon
     */
    public void setEnsembleTroncons(LinkedList<Troncon> ensemble) {
        ensembleTroncons = ensemble;
    }

    /**
     *Ajout d'un tronçon la liste tronçon
     * @param troncon à ajouter
     */
    public void addTroncon(Troncon troncon) {
        ensembleTroncons.add(troncon);
    }

    /**
     *Supprime un tronçon de la liste tronçons
     * @param troncon à supprimer
     */
    public void removeTroncon(Troncon troncon) {
        ensembleTroncons.remove(troncon);
    }

    /**
     *Supprime l'ensemble des tronçons
     */
    public void clearEnsembleTroncons() {
        ensembleTroncons.clear();
    }

    /**
     *Getter sur la liste des tronçons
     * @return tronçons
     */
    public LinkedList<Troncon> getEnsembleTroncons() {
        return ensembleTroncons;
    }
}
