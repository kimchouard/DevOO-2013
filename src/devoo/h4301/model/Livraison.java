/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import org.w3c.dom.Element;

/**
 * Class Livraison. Une livraison doit être effectuée, dans la plage horaire qui
 * lui est ratachée, au noeud destination. Chaque livraison correspond à un
 * client.
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

    /**
     * Getter sur la destination
     *
     * @return la destination
     */
   public Noeud getDestination() {
        return destination;
    }

    /**
     * Setter sur la destination
     *
     * @param destination à attacher à la livraison
     */
    public void setDestination(Noeud destination) {
        this.destination = destination;
    }

    /**
     * Getter sur le colis
     *
     * @return le numéro de colis de la livraison
     */
    public Integer getColis() {
        return colis;
    }

    /**
     * Setter sur le colis
     *
     * @param colis à attacher à la livraison
     */
    public void setColis(Integer colis) {
        this.colis = colis;
    }

    /**
     * Getter sur la plage horaire
     *
     * @return la plage horaire
     */
    public PlageHoraire getHoraire() {
        return horaire;
    }

    /**
     * Setter sur la plage horaire
     *
     * @param horaire à attacher à la livraison
     */
    public void setHorraire(PlageHoraire horaire) {
        this.horaire = horaire;
    }

    /**
     * Getter sur le client
     *
     * @return le client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Setter sur le client
     *
     * @param client à attacher à la livraison
     */
    public void setClient(Client client) {
        this.client = client;
    }
    
    /**
     * Setter sur la livraison
     * @param livraison à mettre à la place de la livraison actuelle
     */
    public void setLivraison(Livraison livraison)
    {
        this.client = livraison.getClient();
        this.colis = livraison.getColis();
        this.destination = livraison.getDestination();
        this.horaire = livraison.getHoraire();
    }

    /**
     * Constructeur à partir d'un noeudDOMXML. Parcours les attributs pour
     * remplir l'objet livraison appelant et cré le client associé.
     *
     * @param noeudDOMRacine noeud DOMXML parcouru
     * @param plage plage horaire attachée à la livraison
     * @param plan plan de la zone urbaine sur lequel est construit la tournée
     * contenant cette livraison
     * @throws Exception de synthaxe ou de modèle levées lors de la lecteur du
     * fichierXML
     */
    public void construireAPartirDomXML(Element noeudDOMRacine, PlageHoraire plage, Plan plan) throws Exception {
        // todo : gerer les erreurs de syntaxe dans le fichier XML !
        this.setHorraire(plage);

        Integer adresse = Integer.parseInt(noeudDOMRacine.getAttribute("adresse"));
        Noeud add = plan.getNoeudById(adresse);
        this.setDestination(add);

        Integer idClient = Integer.parseInt(noeudDOMRacine.getAttribute("client"));
        Client client = new Client(idClient);
        this.client = client;

    }
}
