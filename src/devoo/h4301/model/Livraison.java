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
     * Plage horaire de la livraison
     */
    protected PlageHoraire horaire;

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

    public PlageHoraire getHoraire() {
        return horaire;
    }

    public void setHorraire(PlageHoraire horaire) {
        this.horaire = horaire;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void construireAPartirDomXML(Element noeudDOMRacine, PlageHoraire plage) throws Exception  {
        // todo : gerer les erreurs de syntaxe dans le fichier XML !
        this.setHorraire(plage);
        
        Integer adresse = Integer.parseInt(noeudDOMRacine.getAttribute("adresse"));
        Noeud add = Tournee.getInstance().getPlan().getNoeudById(adresse);
        this.setDestination(add);

        Integer idClient = Integer.parseInt(noeudDOMRacine.getAttribute("client"));
        Client client = new Client(idClient);
        this.client = client;
    System.out.println("livraison crée pour client : "+ idClient);

    }
}
