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

/**
 *
 * @author pupil
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private Reader[] readers = new Reader[10];
    private Book[] books = new Book[10];
    private History[] histories = new History[10];
    private User[] users = new User[10];
    
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private UserCardManager userCardManager = new UserCardManager(); 
    
    private BooksStorageManager booksStorageManager = new BooksStorageManager();
    private ReadersStorageManager readersStorageManager = new ReadersStorageManager();
    private HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();
    
    private User loggedInUser;

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
        
    }
    

    public void run() {
        System.out.println("----- БИБЛИОТЕКА     -----");
        SecureManager secureManager = new SecureManager();
        this.loggedInUser = secureManager.checkInLogin(users, readers);
        boolean repeat = true;
        do {
            System.out.println("======================================");
            System.out.println("Задачи: ");
            System.out.println("0. Покинуть библиотеку");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Просмотреть список книг");
            System.out.println("3. Добавить читателя");
            System.out.println("4. Список читателей");
            System.out.println("5. Выдать книгу");
            System.out.println("6. Вернуть книгу");
            System.out.println("7. Список читаемых книг");
            
            System.out.printf("Выберите задачу: ");
            String task = scanner.nextLine();
            System.out.println("======================================");
            switch (task) {
                case "0":
                    System.out.println("----- КОНЕЦ ПРОГРАММЫ -----");
                    repeat = false;
                    break;
                case "1":
                    System.out.println("----- ДОБАВИТЬ КНИГУ -----");
                    Book book = bookManager.createBook();
                    bookManager.addBookToArray(book, books);
                    booksStorageManager.saveBooksToFile(books);
                    break;
                case "2":
                    System.out.println("----- СПИСОК КНИГ -----");
                    bookManager.printListBooks(books);
                    break;
                case "3":
                    System.out.println("----- ДОБАВИТЬ ЧИТАТЕЛЯ ------");
                    Reader reader = readerManager.createReader();
                    readerManager.addReaderToArray(reader, readers);
                    readersStorageManager.saveReadersToFile(readers);
                    break;
                case "4":
                    System.out.println("----- СПИСОК ЧИТАТЕЛЕЙ -----");
                    readerManager.printListReaders(readers);
                    break;
                case "5":
                    System.out.println("----- ВЫДАТЬ КНИГУ -----");
                    History history = userCardManager.checkOutBook(books, readers);
                    userCardManager.addHistoryToArray(history, histories);
                    historiesStorageManager.saveHistoriesToFile(histories);
                    break;
                case "6":
                    System.out.println("----- ВЕРНУТЬ КНИГУ -----");
                    userCardManager.returnBook(histories);
                    historiesStorageManager.saveHistoriesToFile(histories);
                    break;
                case "7":
                    System.out.println("----- СПИСОК ЧИТАЕМЫХ КНИГ -----");
                    userCardManager.printListReadBooks(histories);
                    break;
                default:
                    System.out.println("Нет такой задачи");
            }
        }while(repeat);
    }
    
}
