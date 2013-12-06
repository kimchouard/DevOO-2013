/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Leslie Breynat
 */
public class Plan {

    private static Plan instancePlan;

    protected ArrayList<Noeud> noeuds;
    protected ArrayList<Troncon> troncons;
    
    public static Plan getInstance(){
    
        if(instancePlan == null){
            instancePlan = new Plan();
        }
        
        return instancePlan;
    }
    
    private Plan(){
        noeuds = new ArrayList<Noeud>();
        troncons = new ArrayList<Troncon>();

    }

    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }

    public void addNoeud(Noeud noeud) {
        this.noeuds.add(noeud);
    }

    public ArrayList<Troncon> getTroncons() {
        return troncons;
    }

    public void addTroncon(Troncon troncon) {
        this.troncons.add(troncon);
    }
    

//public Noeud getNoeudById(Integer id){}
    public int construireAPartirDomXML(Element racine) {


        Plan plan = getInstance();
// Traitement des noeuds
        NodeList list = racine.getElementsByTagName("Noeud");
        System.out.println("traitement des noeuds");

        for (int i = 0; i < list.getLength(); i++) {
            Element noeudElem = (Element) list.item(i);
            Noeud noeudNouveau = new Noeud();
            System.out.println("noeud ajouté");
            noeudNouveau.construireAPartirDomXML(noeudElem);
//todo : Ajout du noeud noeudà la liste de noeud du plan
            plan.addNoeud(noeudNouveau);
        }
        System.out.println("traitement des noeuds fini");

        String tag = "TronconSortant";
        for (int i = 0; i < list.getLength(); i++) {
            Element noeudElem = (Element) list.item(i);
            NodeList listeTroncon = noeudElem.getElementsByTagName(tag);

//Pour chaque noeud, on récupère sa liste de troncon
            for (int j = 0; j < listeTroncon.getLength(); j++) {
                Element tronconElem = (Element) listeTroncon.item(j);
                Troncon tronconNouveau = new Troncon();
                tronconNouveau.construireAPartirDomXML(tronconElem);
// todo : Ajout du troncon à la liste de troncon du plan
//troncons.add(tronconNouveau);
            }
        }
        return 0;

    }
}
