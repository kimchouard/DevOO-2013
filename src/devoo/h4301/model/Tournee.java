/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import java.util.LinkedList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author pmdartus
 */
public class Tournee {

    private String livreur;
    private Plan plan;
    private LinkedList<Livraison> listeLivraison;
    private Noeud entrepot;

    private static Tournee instanceTournee = null;

    private Tournee() {
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     * @return l'instance unique de Tournee
     */
    public static Tournee getInstance() {
        if (instanceTournee == null) {
            instanceTournee = new Tournee();
        }
        return instanceTournee;
    }

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
    
    public void addLivraison(Livraison livraison){
        this.listeLivraison.add(livraison);
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

    public int construireAPartirDomXML(Element racine) {

        //Traitement de l'entrepot
        NodeList listEntrepot = racine.getElementsByTagName("Entrepot");
        if (listEntrepot.getLength() != 1) {
            return 1;
        }
        
        Element entrepotElem = (Element) listEntrepot.item(0);
        int adresse = Integer.parseInt(entrepotElem.getAttribute("adresse"));
        Noeud add = this.getPlan().getNoeudById(adresse);
        //todo : vérifier que l'adresse de l'entrepot est bien dans le plan
        this.setEntrepot(add);

        System.out.println("entrepot ok " + adresse );
        
        
        // Traitement des plages horaires
        //Récupération de "plagesHoraires"
        NodeList listPlages = racine.getElementsByTagName("PlagesHoraires");
        if (listPlages.getLength() != 1) {
            return 1;
        }
        Element plagesElem = (Element) listPlages.item(0);
        //Récupération de la liste des "plageHoraire"
        NodeList listPlage = plagesElem.getElementsByTagName("Plage");
        for (int i = 0; i < listPlage.getLength(); i++) {
            Element palgeElem = (Element) listPlage.item(i);
            PlageHoraire plage = new PlageHoraire();
            plage.construireAPartirDomXML(palgeElem);

            
            //Traitement des livraisons
            //Récupération de "Livraisons"
            NodeList listLiv1 = palgeElem.getElementsByTagName("Livraisons");
            if (listLiv1.getLength() != 1) {
                return 1;
            }
            Element livraisonsElem = (Element) listEntrepot.item(0);
            
            //Récupération de la liste des "Livraison"
            NodeList listLiv2 = livraisonsElem.getElementsByTagName("Livraison");
            
             System.out.println("dans livraisons " );
            for (int j = 0; j < listLiv2.getLength(); j++) {
                             System.out.println("dans livraison " );
                Element livraisonElem = (Element) listPlage.item(i);
                Livraison livraison = new Livraison();
                livraison.construireAPartirDomXML(livraisonElem, plage);
                this.addLivraison(livraison);
            }
        }

        return 0;

    }
}
