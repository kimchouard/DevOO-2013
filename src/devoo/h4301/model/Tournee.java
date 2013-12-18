/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import devoo.h4301.outils.MyException;
import java.util.ArrayList;

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
     * Noeud entrepot de la tournée appartenant au plan
     */
    private LinkedList<PlageHoraire> horaires;

    /**
     * Instance unique de Tournee, par défaut à nul
     */
    private static Tournee instanceTournee = null;

    private ArrayList<Itineraire> itineraires;

    /**
     * Constructeur privé de Tournee. Initialise la liste de livraison
     */
    public Tournee() {
        livraisons = new LinkedList<Livraison>();
        horaires = new LinkedList<PlageHoraire>();
        itineraires = new ArrayList<Itineraire>();
    }

    /**
     * Remet à zéro la tournée
     */
    public void resetTournee() {
        livraisons = new LinkedList<Livraison>();
        horaires = new LinkedList<PlageHoraire>();
        itineraires = new ArrayList<Itineraire>();
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
     * Getter sur itiniraires
     *
     * @return la liste itineraires de la tournee
     */
    public ArrayList<Itineraire> getItineraires() {
        return itineraires;
    }

    /**
     * Setter sur la liste itineraires
     *
     * @param itineraires
     */
    public void setItineraires(ArrayList<Itineraire> itineraires) {
        this.itineraires = itineraires;
    }

    /**
     * Vérifie que la tournée a bien un plan
     *
     * @return true si la tournée a une instance de plan, false sinon
     */
    public Boolean hasAPlan() {
        if (this.plan == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Getter sur la liste des plages horaires
     *
     * @return horaires
     */
    public LinkedList<PlageHoraire> getHoraires() {
        return horaires;
    }

    /**
     * Ajout d'une plage horaire dans la liste. Vérifie que la plage horaire
     * n'est pas déjà dans horaires.
     *
     * @param horaire à ajouter à horairess
     * @throws Exception lévée en cas de doublons dans les plages horaires crées
     */
    public void addHoraire(PlageHoraire horaire) throws Exception {
        if (this.horaires.contains(horaire) == true) {
            MyException e = new MyException("Plusieurs plages horaires sont similaires dans le fichier");
            throw e;
        }
        this.horaires.add(horaire);

    }

    /**
     * Vérifie que les plages horaires sont bien ordonnée : la date de fin d'une
     * plage horaire doit être plus tôt que l'heure de début de la suivante.
     *
     * @throws Exception
     */
    public void cheekPlageHoraires() throws Exception {
        LinkedList<PlageHoraire> list = this.getHoraires();
        for (int i = 1; i < list.size(); i++) {
            //compareTo renvoie 0 si date identique, >0 si début de i> fin de i-1
            if (list.get(i).getDebut().compareTo(list.get(i - 1).getFin()) < 0) {
                MyException e = new MyException("Les plages horaires se chevauchent ou ne sont pas dans le bon ordre");
                throw e;
            }
        }
        System.out.println("vérif pl ok");
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
     *
     * @param livraisons
     */
    public void setLivraisons(LinkedList<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    /**
     * Ajout d'une livraison dans la liste de livraison de la tournée
     *
     * @param livraison a ajouter à la tournée
     * @throws Exception
     */
    public void addLivraison(Livraison livraison) throws Exception {
        if (this.horaires.contains(livraison.getHoraire()) != true) {
            throw new MyException("La plage horaire de la livraison ajoutée n'est pas les plages horaires de la tournée ");
        }

        Noeud dest = livraison.getDestination();
        if (this.plan.getNoeuds().contains(dest) != true) {
            throw new MyException("La destination de la livraison n'est pas connu dans le plan");
            
        }
        if (dest.getX() == null || dest.getY() == null) {
            throw new MyException("Les coordonnées de la destination ne sont pas correctement renseignés");
        }
        this.livraisons.add(livraison);
    }
    
    public void ajoutLivraison(Livraison livraison) throws MyException{
       if(this.horaires.contains(livraison.getHoraire()) != true){
            throw new MyException("Les horraires ne sont pas valides");
       }
      
       Noeud dest = livraison.getDestination();
       if (this.plan.getNoeuds().contains(dest) !=true){
            throw new MyException("Les horraires ne sont pas valides");
       }
       if(dest.getX() == null || dest.getY() == null){
            throw new MyException("Les horraires ne sont pas valides");
       }
        this.livraisons.add(livraison);
    }

    /**
     * @return le noeud entrepot de la tournée
     */
    public Noeud getEntrepot() {
        return entrepot;
    }

    /**
     * Supprime une livraison de la liste livraisons de la tournée
     *
     * @param liv livraison a supprimer
     * @throws java.lang.Exception
     */
    public void supprimerLivraison(Livraison liv) throws Exception {
        for (int i = 0; i < this.livraisons.size(); i++) {
            if (this.livraisons.get(i).equals(liv)) {
                this.livraisons.remove(i);
                return;
            }
        }
        throw new Exception("Impossible de supprimer la dite livraison");
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
        this.resetTournee();

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
        for (int i = 0; i < listPlage.getLength(); i++) {
            Element palgeElem = (Element) listPlage.item(i);
            PlageHoraire plage = new PlageHoraire();
            plage.construireAPartirDomXML(palgeElem);
            this.addHoraire(plage);

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
                livraison.construireAPartirDomXML(livraisonElem, plage, this.getPlan());
                this.addLivraison(livraison);
            }
        }
    }

}
