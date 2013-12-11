/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import org.w3c.dom.Element;

/**
 * Classe plageHoraire
 *
 * @author pmdartus
 */
public class PlageHoraire {

    /**
     * Constructeur à partir d'un noeudDOMXML. Parcours les attributs pour
     * remplir l'objet plageHoraire appelant.
     *
     * @param noeudDOMRacine noeud DOMXML parcouru
     * @throws Exception de synthaxe ou de modèle levées lors de la lecteur du
     * fichierXML
     */
    public void construireAPartirDomXML(Element noeudDOMRacine) throws Exception {
        // todo : gerer les erreurs de syntaxe dans le fichier XML !
        //todo : si jamais les plages horaires se chevauchent, envoyer un msg d'erreur
        System.out.println("plage horaire crée");
    }
}
