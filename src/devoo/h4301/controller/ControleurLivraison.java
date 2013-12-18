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
import devoo.h4301.views.VueLivraisonItem;
import static java.sql.Types.NULL;
import java.util.Date;
import java.util.LinkedList;
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
        this.vueEditLivraison = new VueEditLivraison(this);
        this.controleurPrincipal = controleurPrincipal;
    }
     
     public void afficherListLivraison(JScrollPane panneauLiv) {
        panneauLiv.setViewportView(vueListLivraison);
        this.vueListLivraison.updateUI();
     
    }
     
     public void afficherListLivraisonInitiale(){
         (this.controleurPrincipal.getPanneauLiv()).setViewportView(vueListLivraison);
         this.vueListLivraison.updateUI();
     }
     
     public void afficherCreationLivraison(JScrollPane paneRight, Noeud noeud) throws Exception {
         VueEditLivraison viewNewLiv = new VueEditLivraison(this, noeud);
         paneRight.setViewportView(viewNewLiv);
         viewNewLiv.setVisible(true);
     }
     
     public void creationLivraison(Noeud noeud, String nom, String colis1) throws Exception{
         Client client = new Client(0);
         client.setName(nom);
         
         int colis = 7;
         PlageHoraire horaire = new PlageHoraire();
         Date deb = new Date(2000, 11, 20, 13, 12);
         Date fin = new Date(2000, 11, 22, 00, 00);
         
         horaire.setDebut(deb);
         horaire.setFin(fin);
         
         Livraison liv = new Livraison(noeud, colis, horaire, client);
         controleurPrincipal.addCommandeLivraison(liv, false);

         this.ajoutLiv(liv);
         this.afficherListLivraisonInitiale();
     }
     
     public void afficherUneLivraison(JScrollPane paneRight){
         VueLivraisonItem vueLivraison = new VueLivraisonItem();
         paneRight.setViewportView(vueLivraison);
     }
     
    public void rafraichirVueListLivraison(Tournee tournee, JScrollPane paneRight) {
        this.vueListLivraison.removeAll();
        this.vueListLivraison.updateUI();
        this.vueListLivraison.setTournee(tournee);
        paneRight.setViewportView(this.vueListLivraison);
        
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
     
      // To do pour undo et redo deux fonctions d'ajout et de suppression  sans l'affichage de l'edition
     // Fonction mirroir de l'ajout et de la suppression donc avec correspondance métier etc
     // done
    
     public void ajoutLiv(Livraison liv) throws Exception {
        Tournee.getInstance().addLivraison(liv);
        this.controleurPrincipal.addCommandeLivraison(liv, false); // utile ?
     }
     
     public boolean recupererLivraison(String nom, String colis ){
        return false;
        
     }
}
