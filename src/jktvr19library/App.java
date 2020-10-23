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
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
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
    
    private List<Reader> listReaders = new ArrayList<>();
    private List<Book> listBooks = new ArrayList<>();
    private List<History> listHistories = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
    
    private BooksStorageManager booksStorageManager = new BooksStorageManager();
    private ReadersStorageManager readersStorageManager = new ReadersStorageManager();
    private HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();
    private UsersStorageManager userStorageManager = new UsersStorageManager();
    
    public static User loggedInUser;

    public App() {
        List<Reader> loadedReaders = readersStorageManager.loadReadersFromFile();
        if(loadedReaders != null){
            listReaders = loadedReaders;
        }
        List<Book> loadedBooks = booksStorageManager.loadBooksFromFile();
        if(loadedBooks != null){
            listBooks = loadedBooks;
        }
        List<History> loadedHistories = historiesStorageManager.loadHistoriesFromFile();
        if(loadedHistories != null){
            listHistories = loadedHistories;
        }
        List<User> loadedUser = userStorageManager.loadUsersFromFile();
        if(loadedUser != null){
            listUsers = loadedUser;
        }      
    }
    
    public void run() {
        System.out.println("----- БИБЛИОТЕКА     -----");
        SecureManager secureManager = new SecureManager();
        this.loggedInUser = secureManager.checkInLogin(listUsers, listReaders);
        UserInterface userInterface = new UserInterface();
        if ("MANAGER".equals(App.loggedInUser.getRole())) {
            userInterface.printManagerUI(listUsers, listReaders, listBooks, listHistories);
        }else if ("READER".equals(App.loggedInUser.getRole())) {
            userInterface.printReaderUI(listUsers, listReaders, listBooks, listHistories);
        }
    }
}
