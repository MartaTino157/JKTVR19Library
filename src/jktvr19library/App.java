/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19library;

import tools.savers.ReadersStorageManager;
import tools.creators.BookManager;
import tools.creators.ReaderManager;
import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.GregorianCalendar;
import java.util.Scanner;
import security.SecureManager;
import tools.savers.BooksStorageManager;
import tools.savers.HistoriesStorageManager;
import tools.creators.UserCardManager;
import tools.savers.UsersStorageManager;
import ui.UserInterface;

/**
 *
 * @author pupil
 */
public class App {
    
    private Reader[] readers = new Reader[10];
    private Book[] books = new Book[10];
    private History[] histories = new History[10];
    private User[] users = new User[10];
    
    private BooksStorageManager booksStorageManager = new BooksStorageManager();
    private ReadersStorageManager readersStorageManager = new ReadersStorageManager();
    private HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();
    private UsersStorageManager userStorageManager = new UsersStorageManager();
    
    public static User loggedInUser;

    public App() {
        Reader[] loadedReaders = readersStorageManager.loadFromFile();
        if(loadedReaders != null){
            readers = loadedReaders;
        }
        Book[] loadedBooks = booksStorageManager.loadFromFile();
        if(loadedBooks != null){
            books = loadedBooks;
        }
        History[] loadedHistories = historiesStorageManager.loadHistoriesFromFile();
        if(loadedHistories != null){
            histories = loadedHistories;
        }
        User[] loadedUser = userStorageManager.loadUsersFromFile();
        if(loadedUser != null){
            users = loadedUser;
        }      
    }
    
    public void run() {
        System.out.println("----- БИБЛИОТЕКА     -----");
        SecureManager secureManager = new SecureManager();
        this.loggedInUser = secureManager.checkInLogin(users, readers);
        UserInterface userInterface = new UserInterface();
        if ("MANAGER".equals(App.loggedInUser.getRole())) {
            userInterface.printManagerUI(users, readers, books, histories);
        }else if ("READER".equals(App.loggedInUser.getRole())) {
            userInterface.printReaderUI(users, readers, books, histories);
        }
    }
}
