/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;

/**
 * Classe client regroupant les informations sur un client.
 * @author pmdartus
 */
public class Client {

    /**
     *Id du client
     */
    protected Integer id;

    /**
     *Nom du client
     */
    protected String name;
    
    /**
     *Getter sur l'id du client
     * @return l'id du client appelant
     */
    public Integer getId() {
        return id;
    }

    /**
     *Constructeur client
     * @param id du client à créer
     */
    public Client(Integer id) {
        this.id = id;
    }
}
