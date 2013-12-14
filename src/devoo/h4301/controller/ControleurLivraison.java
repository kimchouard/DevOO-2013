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
import devoo.h4301.views.VueEditLivraison;
import javax.swing.JScrollPane;

/**
 *
 * @author Mimi
 */
public class ControleurLivraison {
    private ControleurPrincipal controleurPrincipal;
    
    private VueListLivraison vueListLivraison;
    private VueEditLivraison vueEditLivraison;
    
     public ControleurLivraison(ControleurPrincipal controleurPrincipal) {
        this.vueListLivraison = new VueListLivraison(this);
        this.vueEditLivraison = new VueEditLivraison();
        this.controleurPrincipal = controleurPrincipal;
    }
     
     public void afficherListLivraison(JScrollPane panneauLiv) {
        panneauLiv.setViewportView(vueListLivraison);
        this.vueListLivraison.updateUI();
    }
     
     // To do pour undo et redo deux fonctions d'ajout et de suppression  sans l'affichage de l'edition
     // Fonction mirroir de l'ajout et de la suppression donc avec correspondance métier etc
     
     
     
     public void afficherCreationLivraison(JScrollPane paneRight, Noeud noeud) {
         VueEditLivraison viewNewLiv = new VueEditLivraison();
         paneRight.setViewportView(viewNewLiv);
         viewNewLiv.setVisible(true);
         Noeud newnoeud = new Noeud(); // récupérer champs
         Integer colis =0; // récupérer champs et initaliser
         Integer id = 0; // récupérer champs et initialiser
         Client client = new Client(id);
         String nom = "M"; // récupérer champs et initaliser
         client.setName(nom);
         PlageHoraire horaire = new PlageHoraire();
         
         Livraison liv = new Livraison(newnoeud, colis, horaire, client);
         Tournee.getInstance().addLivraison(liv);
         this.controleurPrincipal.addCommandeLivraison(liv, false);
         
     }
     
     public void afficherUneLivraison(){
         // a refaire
     }
     
    public void rafraichirVueListLivraison(Tournee tournee) {
        this.vueListLivraison.reset();
        this.vueListLivraison.setTournee(tournee);
    }
     
     public VueListLivraison getVueListLisvraison() {
        return vueListLivraison;
    }
     
     public int supprLiv(Livraison liv) {
         for (int  i = 0; i < this.vueListLivraison.getVueLivraison().size() ; i++){
             if (this.vueListLivraison.getVueLivraison().get(i).getLivraison().equals(liv))
             {
                 this.vueListLivraison.getVueLivraison().remove(liv);
                 Tournee.getInstance().supprimerLivraison(liv);  
                 return 0;
             }
         }
         return 1;
     }
    
     
}
