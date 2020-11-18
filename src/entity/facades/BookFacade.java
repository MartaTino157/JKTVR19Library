/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.facades;

import entity.Book;
import factory.ConnectSingleton;
import javax.persistence.EntityManager;

/**
 *
 * @author pupil
 */
public class BookFacade extends AbstractFacade<Book>{

    public BookFacade() {
        super(Book.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        ConnectSingleton connect = ConnectSingleton.getInstance();
        return connect.getEntityManager();
    }
}
