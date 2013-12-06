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
    
    protected Noeud origine;
    protected Noeud destination;
    protected String nomRue;
    protected double longueur;
    protected double vitesse;

        
        
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
