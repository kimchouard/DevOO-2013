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

    protected Integer id;
    protected Integer x;
    protected Integer y;

    public Integer getId() {
        return id;
    }
    
   
    
    public void construireAPartirDomXML(Element noeudDOMRacine){
// todo : gerer les erreurs de syntaxe dans le fichier XML !
this.id = Integer.parseInt(noeudDOMRacine.getAttribute("id"));
this.x = Integer.parseInt(noeudDOMRacine.getAttribute("x"));
this.y = Integer.parseInt(noeudDOMRacine.getAttribute("y"));
     System.out.println("noeud : " + id );

}
}
