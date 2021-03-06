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
import java.util.GregorianCalendar;
import java.util.Scanner;
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

    public App() {
        ReadersStorageManager rsm = new ReadersStorageManager();
        //readers = rsm.loadFromFile();
        Reader[] loadedReaders = rsm.loadFromFile();
        if(loadedReaders != null){
            readers = loadedReaders;
        }
        BooksStorageManager bsm = new BooksStorageManager();
        //books = bsm.loadFromFile();
        Book[] loadedBooks = bsm.loadFromFile();
        if(loadedBooks != null){
            books = loadedBooks;
        }
        HistoriesStorageManager hsm = new HistoriesStorageManager();
        History[] loadedHistories = hsm.loadHistoriesFromFile();
        if(loadedHistories != null){
            histories = loadedHistories;
        }
        
    }
    

    public void run() {
        System.out.println("----- БИБЛИОТЕКА     -----");
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
                    BookManager bookManager = new BookManager();
                    Book book = bookManager.addBook();
                    for (int i = 0; i < books.length; i++) {
                        if (books[i] == null) {
                            books[i] = book;
                            break;
                        }
                    }
                    BooksStorageManager booksStorageManager = new BooksStorageManager();
                    booksStorageManager.saveBooksToFile(books);
                    break;
                case "2":
                    System.out.println("----- СПИСОК КНИГ -----");
                    int j = 0;
                    for (Book b : books) {
                        if(b != null){
                            System.out.println(j+1+". "+b.toString());
                            j++;
                        }
                    }
                    break;
                case "3":
                    System.out.println("----- ДОБАВИТЬ ЧИТАТЕЛЯ ------");
                    ReaderManager readerManager = new ReaderManager();
                    Reader reader = readerManager.addReader();
                    for (int i = 0; i < readers.length; i++) {
                        if (readers[i] == null) {
                            readers[i] = reader;
                            break;
                        }
                    }
                    ReadersStorageManager readersStorageManager = new ReadersStorageManager();
                    readersStorageManager.saveReadersToFile(readers);
                    break;
                case "4":
                    System.out.println("----- СПИСОК ЧИТАТЕЛЕЙ -----");
                    int n = 0;
                    for (Reader r : readers) {
                        if(r != null){
                            System.out.println(n+1+". "+r.toString());
                            n++;
                        }
                    }
                    break;
                case "5":
                    System.out.println("----- ВЫДАТЬ КНИГУ -----");
                    UserCardManager userCardManager = new UserCardManager(); 
                    History history = userCardManager.giveBook(books, readers);
                    for (int i = 0; i < histories.length; i++) {
                        if (histories[i] == null) {
                            histories[i] = history;
                            break;
                        }
                    }
                    HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();
                    historiesStorageManager.saveHistoriesToFile(histories);
                    break;
                case "6":
                    System.out.println("----- ВЕРНУТЬ КНИГУ -----");
                    userCardManager = new UserCardManager();
                    userCardManager.returnBook(histories);
                    historiesStorageManager = new HistoriesStorageManager();
                    historiesStorageManager.saveHistoriesToFile(histories);
                    break;
                case "7":
                    System.out.println("----- СПИСОК ЧИТАЕМЫХ КНИГ -----");
                    n = 0;
                    for (History h : histories) {
                        if(h != null && h.getReturnDate() == null){
                            System.out.println(n+1+". "+h.toString());
                            n++;
                        }
                    }
                    break;
                default:
                    System.out.println("Нет такой задачи");
            }
        }while(repeat);
    }
    
}
