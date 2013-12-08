/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;

/**
 *
 * @author pmdartus
 */
public class Livraison {

    /**
     * Adresse d'arrivee de la livraison
     */
    protected Noeud destination;

    /**
     * Numero du colis a livrer
     */
    protected Integer colis;

    /**
     * Plage horraire de la livraison
     */
    protected PlageHoraire horraire;

    /**
     * Client à livrer
     */
    protected Client client;
}
