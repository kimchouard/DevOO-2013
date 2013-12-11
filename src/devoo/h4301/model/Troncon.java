/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import org.w3c.dom.Element;

/**
 * Classe correspondant au tronçon. Un tronçon est un arc orienté entre deux
 * point, possèdant des caractéristiques de longueur et de vitesse.
 *
 * @author Leslie Breynat
 */
public class Troncon {

    /**
     * Noeud origine du troncon
     */
    protected Noeud origine;

    /**
     * Noeud destination du troncon
     */
    protected Noeud destination;

    /**
     * Nom de la rue
     */
    protected String nomRue;

    /**
     * Longueur de la rue
     */
    protected double longueur;

    /**
     * Vitesse limite du troncon
     */
    protected double vitesse;

    /**
     *Getter sur l'origine
     * @return le noeud origine du tronçon
     */
    public Noeud getOrigine() {
        return origine;
    }

    /**
     *Setter sur l'origine
     * @param origine noeud à attacher au tronçon
     */
    public void setOrigine(Noeud origine) {
        this.origine = origine;
    }

    /**
     *Getter sur le noeud destination
     * @return le noeud destination du tronçon
     */
    public Noeud getDestination() {
        return destination;
    }

    /**
     *Setter sur la destination
     * @param destination noeud à attacher au tronçon
     */
    public void setDestination(Noeud destination) {
        this.destination = destination;
    }

    /**
     *Getter sur le nom de la rue
     * @return nom de la rue 
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Setter sur le nom de la rue du tronçon
     * @param nomRue nom de rue à attacher au tronçon
     */
    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    /**
     *Getter sur la longueur du tronçon
     * @return la longeur
     */
    public double getLongueur() {
        return longueur;
    }

    /**
     *Setter sur la longueur
     * @param longueur à attacher au tronçon
     */
    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    /**
     *Getter sur la vitesse
     * @return la vitesse du tronçon
     */
    public double getVitesse() {
        return vitesse;
    }

    /**
     *Setter sur la vitesse 
     * @param vitesse à attacher au tronçon
     */
    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    /**
     *Constructeur
     */
    public Troncon() {
    }

    /**
     *Constructeur à partir d'un noeudDOMXML. Parcours les attributs pour remplir l'objet tronçon appelant.
     * @param noeudDOMRacine noeud DOMXML parcouru
     * @throws Exception de synthaxe ou de modèle levées lors de la lecteur du
     * fichierXML
     */
    public void construireAPartirDomXML(Element noeudDOMRacine) throws Exception {

/// todo : gerer les erreurs de syntaxe dans le fichier XML !
        this.nomRue = noeudDOMRacine.getAttribute("nomRue");
        vitesse = Double.parseDouble((noeudDOMRacine.getAttribute("vitesse")));
        longueur = Double.parseDouble((noeudDOMRacine.getAttribute("longueur")));
        System.out.println("troncon créé");
        Integer idDestination = Integer.parseInt(noeudDOMRacine.getAttribute("destination"));
        Tournee t = Tournee.getInstance();
        Plan p = t.getPlan();
        destination = p.getNoeudById(idDestination);
        System.out.println("troncon créé totalement");
    }

}
