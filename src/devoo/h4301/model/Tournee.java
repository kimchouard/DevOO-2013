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
public class Tournee {
    private String livreur;
    private Plan plan;
    private LinkedList<Livraison> listeLivraison;
    private Noeud entrepot;

    /**
     * @return the livreur
     */
    public String getLivreur() {
        return livreur;
    }

    /**
     * @param livreur the livreur to set
     */
    public void setLivreur(String livreur) {
        this.livreur = livreur;
    }

    /**
     * @return the plan
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * @param plan the plan to set
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * @return the listeLivraison
     */
    public LinkedList<Livraison> getListeLivraison() {
        return listeLivraison;
    }

    /**
     * @param listeLivraison the listeLivraison to set
     */
    public void setListeLivraison(LinkedList<Livraison> listeLivraison) {
        this.listeLivraison = listeLivraison;
    }

    /**
     * @return the entrepot
     */
    public Noeud getEntrepot() {
        return entrepot;
    }

    /**
     * @param entrepot the entrepot to set
     */
    public void setEntrepot(Noeud entrepot) {
        this.entrepot = entrepot;
    }
}
