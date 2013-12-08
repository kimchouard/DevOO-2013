/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import org.w3c.dom.Element;

/**
 *
 * @author pmdartus
 */
public class Livraison {

    /**
     * Adresse d'arrivee de la livraison
     */
    protected Noeud destination;

    /**
     * Numero du colis a livrer
     */
    protected Integer colis;

    /**
     * Plage horraire de la livraison
     */
    protected PlageHoraire horraire;

    /**
     * Client à livrer
     */
    protected Client client;

    public Noeud getDestination() {
        return destination;
    }

    public void setDestination(Noeud destination) {
        this.destination = destination;
    }

    public Integer getColis() {
        return colis;
    }

    public void setColis(Integer colis) {
        this.colis = colis;
    }

    public PlageHoraire getHorraire() {
        return horraire;
    }

    public void setHorraire(PlageHoraire horraire) {
        this.horraire = horraire;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void construireAPartirDomXML(Element noeudDOMRacine, PlageHoraire plage) {
        // todo : gerer les erreurs de syntaxe dans le fichier XML !
        this.setHorraire(horraire);
        
        Integer adresse = Integer.parseInt(noeudDOMRacine.getAttribute("adresse"));
        Noeud add = Tournee.getInstance().getPlan().getNoeudById(adresse);
        this.setDestination(add);

        Integer idClient = Integer.parseInt(noeudDOMRacine.getAttribute("client"));
       //todo :  faire ou récupérer client
    System.out.println("livraison crée pour client : "+ idClient);

    }
}
