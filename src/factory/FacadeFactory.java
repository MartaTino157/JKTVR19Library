/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import entity.facades.BookFacade;
import entity.facades.HistoryFacade;
import entity.facades.ReaderFacade;
import entity.facades.UserFacade;

/**
 *
 * @author pupil
 */
public class FacadeFactory {
    public static BookFacade getBookFacade(){
        return new BookFacade();
    }
    public static UserFacade getUserFacade(){
        return new UserFacade();
    }
    public static ReaderFacade getReaderFacade(){
        return new ReaderFacade();
    }
    public static HistoryFacade getHistoryFacade(){
        return new HistoryFacade();
    }
}
