/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.savers;

import entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class BaseManager implements StorageManagerInterface{
    
    @Override
    public void save(List arrayList, String fileName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            for (int i = 0; i < arrayList.size(); i++) {
                if(Book.class.equals(arrayList.get(i))){
                    List<Book> listBook = (List<Book>) arrayList;
                    if(listBook.get(i).getId() == null){
                        em.persist(listBook.get(i));
                    }
                }
            }
        tx.commit();
    }

    @Override
    public List load(String fileName) {
        try {
            String className = null;
            switch (fileName) {
                case "books":
                    className = "Book";
                    break;
                case "readers":
                    className = "Reader";
                    break;
                case "users":
                    className = "User";
                    break;
                case "histories":
                    className = "History";
                    break;
            }
            List arrayList = em.createQuery("SELECT entity FROM "+className+" entity")
                    .getResultList();
            return arrayList;
        } catch (Exception e) {
            System.out.println("База даных пуста");
            return new ArrayList();
        }
    }
    
}
