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

    /**
     * Constructeur du noeud grace au DOM XML
     * @param noeudDOMRacine Noeud considere pour la creation
     */
    public void construireAPartirDomXML(Element noeudDOMRacine){

        // todo : gerer les erreurs de syntaxe dans le fichier XML !
        //nomRue = String.parseString(noeudDOMRacine.getAttribute("nomRue"));
        //todo :  parser en float???
        //vitesse = Integer.parseInt(noeudDOMRacine.getAttribute("vitesse"));
            //longueur = Integer.parseInt(noeudDOMRacine.getAttribute("longueur"));
        int idDestination = Integer.parseInt(noeudDOMRacine.getAttribute("destination"));
         System.out.println("destination du troncon" + idDestination);

        //Todo : récupérer la destination en fonction de son id
    }
    
}
