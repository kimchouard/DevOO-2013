/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;

/**
 *
 * @author pmdartus
 */
public class Client {

    /**
     *
     */
    protected Integer id;

    /**
     *
     */
    protected String name;
    
    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public Client(Integer id) {
        this.id = id;
    }
}
