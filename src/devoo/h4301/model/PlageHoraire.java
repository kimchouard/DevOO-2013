/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import org.w3c.dom.Element;

/**
 * Classe plageHoraire
 *
 * @author pmdartus
 */
public class PlageHoraire {
    
    private Date debut;
    private Date fin;

    /**
     * Constructeur à partir d'un noeudDOMXML. Parcours les attributs pour
     * remplir l'objet plageHoraire appelant.
     *
     * @param noeudDOMRacine noeud DOMXML parcouru
     * @throws Exception de synthaxe ou de modèle levées lors de la lecteur du
     * fichierXML
     */
    public void construireAPartirDomXML(Element noeudDOMRacine) throws Exception {
        String stringHeureDebut = noeudDOMRacine.getAttribute("heureDebut");
        String stringHeureFin = noeudDOMRacine.getAttribute("heureFin");
        
        setDebut(construireDateAPartirString(stringHeureDebut));
        setFin(construireDateAPartirString(stringHeureFin));
        
        if (getFin().before(getDebut())) {
            throw new IllegalArgumentException("Heure de fin avant heure de debut");
        }
    }
    
    private Date construireDateAPartirString(String stringHeure) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date convertedDate = formatter.parse(stringHeure);

        return convertedDate;
    }

    /**
     * @return the debut
     */
    public Date getDebut() {
        return debut;
    }

    /**
     * @param debut the debut to set
     */
    public void setDebut(Date debut) {
        this.debut = debut;
    }

    /**
     * @return the fin
     */
    public Date getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlageHoraire other = (PlageHoraire) obj;
        if (!Objects.equals(this.debut, other.debut)) {
            return false;
        }
        else if (!Objects.equals(this.fin, other.fin)) {
            return false;
        }
        return true;
    }
    
}
