/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import devoo.h4301.outils.MyException;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Leslie Breynat
 */
public class Plan {

    protected ArrayList<Noeud> noeuds;
    protected ArrayList<Troncon> troncons;

    public Plan() {
        noeuds = new ArrayList<>();
        troncons = new ArrayList<>();

    }

    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }

    public void addNoeud(Noeud noeud) {
        this.noeuds.add(noeud);
    }

    public void removeNoeud(Integer idNoeud) throws Exception {
        Noeud noeud = this.getNoeudById(idNoeud);
        this.noeuds.remove(noeud);
    }

    public ArrayList<Troncon> getTroncons() {
        return troncons;
    }

    public void addTroncon(Troncon troncon) {
        this.troncons.add(troncon);
    }

    public Noeud getNoeudById(Integer id) throws Exception {

        Noeud noeudCherche = null;
        ArrayList<Noeud> list = this.getNoeuds();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                noeudCherche = list.get(i);
            }
        }
        if (noeudCherche == null) {
            MyException e = new MyException("Appel à un noeud inexistant");
            throw e;
        }
        return noeudCherche;
    }

    public void construireAPartirDomXML(Element racine) throws Exception {

// Traitement des noeuds
        NodeList list = racine.getElementsByTagName("Noeud");

        for (int i = 0; i < list.getLength(); i++) {
            Element noeudElem = (Element) list.item(i);
            Noeud noeudNouveau = new Noeud();
            noeudNouveau.construireAPartirDomXML(noeudElem);
            this.addNoeud(noeudNouveau);
        }


        String tag = "TronconSortant";
        for (int i = 0; i < list.getLength(); i++) {
            Element noeudElem = (Element) list.item(i);
            NodeList listeTroncon = noeudElem.getElementsByTagName(tag);

            Integer idOrigine = Integer.parseInt(noeudElem.getAttribute("id"));

            Noeud origine = getNoeudById(idOrigine);
//Pour chaque noeud, on récupère sa liste de troncon
                            System.out.println("début troncon");

            for (int j = 0; j < listeTroncon.getLength(); j++) {
                Element tronconElem = (Element) listeTroncon.item(j);
                Troncon tronconNouveau = new Troncon();

                tronconNouveau.setOrigine(origine);
                tronconNouveau.construireAPartirDomXML(tronconElem);

                this.addTroncon(tronconNouveau);
            }
        }

    }
}
