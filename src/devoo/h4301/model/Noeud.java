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
public class Noeud {
    
    /**
     * id du noeud
     */
    protected Integer id;
    
    /**
     * position en x du noeud
     */
    protected Integer x;
    
    /**
     * position en y du noeud
     */
    protected Integer y;
    
    /**
     * Retourne l'id du noeud
     * @return id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Constructeur d'un noeud grace au DOM XML
     * @param noeudDOMRacine noeud du DOM XML
     */
    public void construireAPartirDomXML(Element noeudDOMRacine) throws IllegalArgumentException, NumberFormatException {
        this.id = Integer.parseInt(noeudDOMRacine.getAttribute("id"));
        this.x = Integer.parseInt(noeudDOMRacine.getAttribute("x"));
        this.y = Integer.parseInt(noeudDOMRacine.getAttribute("y"));
        
        if(this.id == null || this.x == null || this.y == null) {
            throw new IllegalArgumentException("id or position is null");
        }
    }
}
