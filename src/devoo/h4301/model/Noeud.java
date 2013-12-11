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

    public Noeud() {
    }

    /**
     * Constructeur du noeud
     * @return Noeud
     */
    public Noeud(Integer id, Integer x, Integer y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructeur d'un noeud grace au DOM XML
     * @param noeudDOMRacine noeud du DOM XML
     * @throws java.lang.Exception
     */
    public void construireAPartirDomXML(Element noeudDOMRacine)throws Exception {
        // todo : gerer les erreurs de syntaxe dans le fichier XML !
        this.id = Integer.parseInt(noeudDOMRacine.getAttribute("id"));
        this.x = Integer.parseInt(noeudDOMRacine.getAttribute("x"));
        this.y = Integer.parseInt(noeudDOMRacine.getAttribute("y"));
        
        if(this.id == null || this.x == null || this.y == null) {
            throw new IllegalArgumentException("id or position is null");
        }
    }

    public Noeud(Integer id) {
        this.id = id;
    }

    public Noeud() {
    }
    
    /**
     * Retourne l'id du noeud
     * @return id
     */
    public Integer getId() {
        return id;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
