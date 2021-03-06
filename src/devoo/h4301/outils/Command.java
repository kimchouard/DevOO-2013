/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.outils;

import devoo.h4301.model.Livraison;

/**
 *
 * @author pmdartus
 */
public class Command {
    private Livraison Livraison;
    private Boolean deleted;
    
    /**
     * Constructeur par defaut
     * @param liv livraison concernee
     * @param deleted est vrai si la commande supprime la livraison associee 
     */
    public Command (Livraison liv, Boolean deleted) {
        this.Livraison = liv;
        this.deleted = deleted;
    }
    
    /**
     * @return the deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * @return the Livraison
     */
    public Livraison getLivraison() {
        return Livraison;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
