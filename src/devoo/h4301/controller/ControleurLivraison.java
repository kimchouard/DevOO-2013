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
 * @author Mimi
 */
public class ControleurLivraison {
    
    private final ControleurPrincipal controleurPrincipal;
    
    private final VueListLivraison vueListLivraison;
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
    public void afficherListLivraison(JScrollPane panneauLiv) {
        panneauLiv.setViewportView(vueListLivraison);
        this.vueListLivraison.updateUI();
    }

    /**
     * Afficher la liste des livraisons initiale
     */
    public void afficherListLivraisonInitiale() {
         this.controleurPrincipal.getPanneauLiv().setViewportView(this.vueListLivraison);
         this.vueListLivraison.updateUI();
     }

    /**
     * Afficher le panneau de création de livraison
     * @param paneRight panneau de droite
     * @param noeud Noeud de livraison
     * @throws Exception
     */
    public void afficherCreationLivraison(JScrollPane paneRight, Noeud noeud) throws Exception {
         VueEditLivraison viewNewLiv = new VueEditLivraison(this, noeud);
         paneRight.setViewportView(viewNewLiv);
         viewNewLiv.setVisible(true);
     }

    /**
     * Création d'une livraison
     * @param noeud Noeud de livraison
     */
    public void creationLivraison(Noeud noeud, String nom, int colis, PlageHoraire ph ) throws ParseException, MyException {
         
            Client client = new Client(0);
            client.setName(nom);
            
            Livraison liv = new Livraison(noeud, colis, ph, client);
            
            this.ajoutLiv(liv);
     }

    /**
     * Afficher une livraison
     * @param paneRight Panneau de droite
     * @param liv Livraison considérée
     */
    public void afficherUneLivraison(JScrollPane paneRight, Livraison liv ) {
         VueLivraisonItem vueLivraison = new VueLivraisonItem(this, liv);
         paneRight.setViewportView(vueLivraison);
     }

    /**
     * Effacer la vue List livraison
     * @param paneRight
     */
    public void effacerVueListLivraison(JScrollPane paneRight) {
        this.vueListLivraison.removeAll();
        this.vueListLivraison.updateUI();
     }

    /**
     * Effacer la vue Edit Livraison
     * @param panneauLiv
     */
    public void effacerItemLivraison(JScrollPane panneauLiv) {
         this.vueEditLivraison.removeAll();
         this.vueEditLivraison.updateUI();
     }
     
    /**
     * Rafraichir la Vue Liste Livraison
     * @param tournee Nouvelle tournee a considérer
     * @param paneRight Panneau de droite
     */
    public void rafraichirVueListLivraison(Tournee tournee, JScrollPane paneRight) {
        this.vueListLivraison.removeAll();
        this.vueListLivraison.updateUI();
        ArrayList<Livraison> livs =  controleurPrincipal.getControleurGraph().getLivOrdered();
        this.vueListLivraison.setLivraisons(livs);
        paneRight.setViewportView(this.vueListLivraison);
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
            this.controleurPrincipal.reloadUI();
        } catch (Exception ex) {
            System.out.println("Impossible de supprimer la livraison");
            return;
        }
     }
     
      /**
     * Ajout de la livraison dans le modèle
     * @param liv la dite livraison
     */
     public void ajoutLiv(Livraison liv) throws MyException{
        try { 
            Tournee.getInstance().addLivraison(liv);
            this.controleurPrincipal.addCommandeLivraison(liv, false);
        } catch (Exception ex) {
            System.out.println("Impossible d'ajouter la livraison");
            return;
        }
        this.controleurPrincipal.reloadUI();
        this.rafraichirVueListLivraison(Tournee.getInstance(), this.controleurPrincipal.getPanneauLiv());
        this.controleurPrincipal.rafraichirVueGraph();
     }

}
