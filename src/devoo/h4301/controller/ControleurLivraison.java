/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Client;
import devoo.h4301.views.VueListLivraison;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Noeud;
import devoo.h4301.model.PlageHoraire;
import devoo.h4301.model.Tournee;
import devoo.h4301.outils.MyException;
import devoo.h4301.views.VueEditLivraison;
import devoo.h4301.views.VueLivraisonItem;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JScrollPane;

/**
 *
 * @author Meriem
 */
public class ControleurLivraison {
    
    /**
     * controleur principale de l'application
     */
    private final ControleurPrincipal controleurPrincipal;
    
    /**
     * vue sur les livraison du modele
     */
    private final VueListLivraison vueListLivraison;
    
    /**
     * vue sur l'icone de creation de livraison
     */
    private final VueEditLivraison vueEditLivraison;

    /**
     * Constructeur standard du controlleur de livraison
     * @param controleurPrincipal instance du controlleur principal
     */
    public ControleurLivraison(ControleurPrincipal controleurPrincipal) {
        this.vueListLivraison = new VueListLivraison(this);
        this.vueEditLivraison = new VueEditLivraison(this);
        this.controleurPrincipal = controleurPrincipal;
    }

    /**
     * Afficher la liste des livraisons dans la panneau de droite
     * @param panneauLiv 
     */
    public void afficherListLivraison() {
        this.controleurPrincipal.getPanneauLiv().setViewportView(vueListLivraison);
        this.vueListLivraison.updateUI();
    }

    /**
     * Afficher la liste des livraisons initiale dans le panneau de droite
     */
    public void afficherListLivraisonInitiale() {
         this.controleurPrincipal.getPanneauLiv().setViewportView(this.vueListLivraison);
         this.vueListLivraison.updateUI();
     }

    /**
     * Afficher l'icone de création de livraison
     * @param paneRight panneau de droite
     * @param noeud Noeud à partir du quel on souhaite creer une livraison
     * @throws Exception
     */
    public void afficherCreationLivraison(Noeud noeud) {
        VueEditLivraison viewNewLiv = new VueEditLivraison(this, noeud);
        this.controleurPrincipal.getPanneauLiv().setViewportView(viewNewLiv);
        viewNewLiv.setVisible(true);
     }

    /**
     * Création d'une livraison dans le modèle
     * @param noeud Noeud de livraison
     * @param nom nom du client
     * @param colis numero du colis
     * @param ph plage horaire de la livraison
     */
    public void creationLivraison(Noeud noeud, String nom, int colis, PlageHoraire ph ) {
         try {
            Client client = new Client(0);
            client.setName(nom);
            
            Livraison liv = new Livraison(noeud, colis, ph, client);
            
            this.ajoutLiv(liv);
         } catch (Exception e) {
             System.out.print("Impossible d'ajouter la livraison");
         }
     }

    /**
     * Afficher un livraison
     * @param i position de la livraison dans la tournne calculee
     * @param liv Livraison considérée
     */
    public void afficherUneLivraison(Livraison liv, int i ) {
         VueLivraisonItem vueLivraison = new VueLivraisonItem(this, liv, i);
         this.controleurPrincipal.getPanneauLiv().setViewportView(vueLivraison);
     }

    /**
     * Effacer la vue List livraison du panneau de droite
     */
    public void effacerVueListLivraison() {
        this.vueListLivraison.removeAll();
        this.vueListLivraison.updateUI();
     }

    /**
     * Effacer la vue d'edition de Livraison
     */
    public void effacerItemLivraison() {
         this.vueEditLivraison.removeAll();
         this.vueEditLivraison.updateUI();
     }
     
    /**
     * Rafraichir la Vue Liste Livraison
     * @param tournee Nouvelle tournee a considérer
     */
    public void rafraichirVueListLivraison(Tournee tournee) {
        this.vueListLivraison.removeAll();
        this.vueListLivraison.updateUI();
        ArrayList<Livraison> livs =  controleurPrincipal.getControleurGraph().getLivOrdered();
        this.vueListLivraison.setLivraisons(livs);
        this.controleurPrincipal.getPanneauLiv().setViewportView(this.vueListLivraison);
    }

    /**
     * Gettre de la Vue Liste Livraison
     * @return instace de la vue
     */
    public VueListLivraison getVueListLisvraison() {
        return vueListLivraison;
    }



    /**
     * Supprimer la livraison synchronisée
     * @param liv la dite livraison
     */
    public void supprimerLivraison(Livraison liv){
        try {
            Tournee.getInstance().supprimerLivraison(liv);
            this.controleurPrincipal.addCommandeLivraison(liv, true);
            this.controleurPrincipal.reloadGraph();
            this.controleurPrincipal.reloadUI(true);
        } catch (Exception ex) {
            System.out.println("Impossible de supprimer la livraison");
        }
     }
     
      /**
     * Ajout de la livraison dans le modèle
     * @param liv la dite livraison
     */
     public void ajoutLiv(Livraison liv) {
        try { 
            Tournee.getInstance().addLivraison(liv);
            this.controleurPrincipal.addCommandeLivraison(liv, false);
        } catch (Exception ex) {
            System.out.println("Impossible d'ajouter la livraison");
            return;
        }
        this.controleurPrincipal.reloadGraph();
        this.controleurPrincipal.reloadUI(true);
     }

}
