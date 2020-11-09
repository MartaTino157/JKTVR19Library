/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19library;

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
import tools.savers.FileManager;
import tools.creators.UserCardManager;
import tools.savers.BaseManager;
import ui.UserInterface;
import tools.savers.StorageManagerInterface;

/**
 *
 * @author pupil
 */
public class App {
    public static enum storageFile{BOOKS, READERS, USERS, HISTORIES}
    
    private List<Reader> listReaders = new ArrayList<>();
    private List<Book> listBooks = new ArrayList<>();
    private List<History> listHistories = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
    
    //private StorageManagerInterface storageManager = new FileManager();
    private StorageManagerInterface storageManager = new BaseManager();

    public static User loggedInUser;

    public App() {
        List<Reader> loadedReaders = storageManager.load(App.storageFile.READERS.toString());
        if(loadedReaders != null){
            listReaders = loadedReaders;
        }
        List<Book> loadedBooks = storageManager.load(App.storageFile.BOOKS.toString());
        if(loadedBooks != null){
            listBooks = loadedBooks;
        }
        List<History> loadedHistories = storageManager.load(App.storageFile.HISTORIES.toString());
        if(loadedHistories != null){
            listHistories = loadedHistories;
        }
        List<User> loadedUser = storageManager.load(App.storageFile.USERS.toString());
        if(loadedUser != null){
            listUsers = loadedUser;
        }      
    }
    
    public void run() {
        System.out.println("----- БИБЛИОТЕКА     -----");
        SecureManager secureManager = new SecureManager();
        this.loggedInUser = secureManager.checkInLogin(listUsers, listReaders);
        UserInterface userInterface = new UserInterface();
        if (SecureManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())) {
            userInterface.printManagerUI(listUsers, listReaders, listBooks, listHistories);
        }else if (SecureManager.role.READER.toString().equals(App.loggedInUser.getRole())) {
            userInterface.printReaderUI(listUsers, listReaders, listBooks, listHistories);
        }
    }
}
