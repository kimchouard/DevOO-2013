/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import devoo.h4301.outils.MyException;

import java.util.LinkedList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Classe tournée. Singleton. Une tournée comporte un plan et une liste de
 * livraison.
 *
 * @author pmdartus
 */
public class Tournee {

    /**
     * Nom du livreur de la tournée
     */
    private String livreur;

    /**
     * Plan de la zone urbaine dans laquelle se trouve la tournée
     */
    private Plan plan;

    /**
     * Liste de livraison de la tournée
     */
    private LinkedList<Livraison> livraisons;

    /**
     * Noeud entrepot de la tournée appartenant au plan
     */
    private Noeud entrepot;

    /**
     * Instance unique de Tournee, par défaut à nul
     */
    private static Tournee instanceTournee = null;

    /**
     * Constructeur privé de Tournee. Initialise la liste de livraison
     */
    private Tournee() {
        livraisons = new LinkedList<Livraison>();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return l'instance unique de Tournee
     */
    public static Tournee getInstance() {
        if (instanceTournee == null) {
            instanceTournee = new Tournee();
        }
        return instanceTournee;
    }

    /**
     * @return le nom du livreur
     */
    public String getLivreur() {
        return livreur;
    }

    /**
     * @param livreur nom du livreur à attacher à la tournée
     */
    public void setLivreur(String livreur) {
        this.livreur = livreur;
    }

    /**
     * @return le plan de la tournée
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * @param plan a attacher à la tournée
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * @return livraisons la liste de livraison de la tournée
     */
    public LinkedList<Livraison> getLivraisons() {
        return livraisons;
    }

    /**
     * Ajout d'une livraison dans la liste de livraison de la tournée
     *
     * @param livraison a ajouter à la tournée
     */
    public void addLivraison(Livraison livraison) {
        this.livraisons.add(livraison);
    }

    /**
     * @return le noeud entrepot de la tournée
     */
    public Noeud getEntrepot() {
        return entrepot;
    }

    /**
     * @param entrepot noeud attaché comme entrepot à la tournée
     */
    public void setEntrepot(Noeud entrepot) {
        this.entrepot = entrepot;
    }

    /**
     * Constructeur à partir d'un noeudDOMXML. Parcours les attributs et les
     * noeuds DOMXML sous-jacents pour remplir l'objet tournée appelant.
     * Récupère l'entrepot, puis la liste des plages horaires puis la liste des
     * livraisons et fait à chaque fois appel à leur constructeur respectif.
     *
     * @param racine noeud DOMXML parcouru
     * @throws Exception de synthaxe ou de modèle levées lors de la lecteur du
     * fichierXML
     */
    public void construireAPartirDomXML(Element racine) throws Exception {

        //Traitement de l'entrepot
        NodeList listEntrepot = racine.getElementsByTagName("Entrepot");
        if (listEntrepot.getLength() != 1) {
            MyException e = new MyException("Il y a plusieurs entrepots dans le fichier de livraison");
            throw e;
        }

        Element entrepotElem = (Element) listEntrepot.item(0);
        int adresse = Integer.parseInt(entrepotElem.getAttribute("adresse"));
        Noeud add = this.getPlan().getNoeudById(adresse);
        //todo : vérifier que l'adresse de l'entrepot est bien dans le plan
        this.setEntrepot(add);
        System.out.println("entrepot créé");

        // Traitement des plages horaires
        //Récupération de "plagesHoraires"
        NodeList listPlages = racine.getElementsByTagName("PlagesHoraires");
        if (listPlages.getLength() != 1) {
            MyException e = new MyException("Il y a plusieurs listes de plages horaires dans le fichier de livraison");
            throw e;
        }
        Element plagesElem = (Element) listPlages.item(0);
        //Récupération de la liste des "plageHoraire"
        NodeList listPlage = plagesElem.getElementsByTagName("Plage");
        for (int i = 0; i <= listPlage.getLength(); i++) {
            Element palgeElem = (Element) listPlage.item(i);
            PlageHoraire plage = new PlageHoraire();
            plage.construireAPartirDomXML(palgeElem);

            //Traitement des livraisons
            //Récupération de "Livraisons"
            NodeList listLiv1 = palgeElem.getElementsByTagName("Livraisons");
            if (listLiv1.getLength() != 1) {
                MyException e = new MyException("Il y a plusieurs listes de livraisons au sein d'une plage horaire dans le fichier de livraison");
                throw e;
            }
            Element livraisonsElem = (Element) listLiv1.item(0);

            //Récupération de la liste des "Livraison"
            NodeList listLiv2 = livraisonsElem.getElementsByTagName("Livraison");

            for (int j = 0; j < listLiv2.getLength(); j++) {
                Element livraisonElem = (Element) listLiv2.item(j);
                Livraison livraison = new Livraison();
                livraison.construireAPartirDomXML(livraisonElem, plage);
                this.addLivraison(livraison);
            }
        }
    }
}
