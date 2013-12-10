/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import org.w3c.dom.Element;

/**
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

    public Noeud getOrigine() {
        return origine;
    }

    public void setOrigine(Noeud origine) {
        this.origine = origine;
    }

    public Noeud getDestination() {
        return destination;
    }

    public void setDestination(Noeud destination) {
        this.destination = destination;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public void construireAPartirDomXML(Element noeudDOMRacine) {

// todo : gerer les erreurs de syntaxe dans le fichier XML !
        
        this.nomRue = noeudDOMRacine.getAttribute("nomRue");
        vitesse = Double.parseDouble((noeudDOMRacine.getAttribute("vitesse")));
        longueur = Double.parseDouble((noeudDOMRacine.getAttribute("longueur")));
        
        int idDestination = Integer.parseInt(noeudDOMRacine.getAttribute("destination"));
        destination = Tournee.getInstance().getPlan().getNoeudById(idDestination);
    }

}
