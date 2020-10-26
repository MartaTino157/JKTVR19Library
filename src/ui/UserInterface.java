/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.List;
import java.util.Scanner;
import tools.creators.BookManager;
import tools.creators.ReaderManager;
import tools.creators.UserCardManager;
import tools.savers.StorageManager;

/**
 *
 * @author pupil
 */
public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private UserCardManager userCardManager = new UserCardManager();

    public void printManagerUI(List<User> listUsers, List<Reader> listReaders, List<Book> listBooks, List<History> listHistories) {
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
                    bookManager.addBookToArray(book, listBooks);
                    break;
                case "2":
                    System.out.println("----- СПИСОК КНИГ -----");
                    bookManager.printListBooks(listBooks);
                    break;
                case "3":
                    System.out.println("----- ДОБАВИТЬ ЧИТАТЕЛЯ ------");
                    Reader reader = readerManager.createReader();
                    readerManager.addReaderToArray(reader, listReaders);
                    break;
                case "4":
                    System.out.println("----- СПИСОК ЧИТАТЕЛЕЙ -----");
                    readerManager.printListReaders(listReaders);
                    break;
                case "5":
                    System.out.println("----- ВЫДАТЬ КНИГУ -----");
                    History history = userCardManager.checkOutBook(listBooks, listReaders);
                    userCardManager.addHistoryToArray(history, listHistories);
                    break;
                case "6":
                    System.out.println("----- ВЕРНУТЬ КНИГУ -----");
                    userCardManager.returnBook(listHistories);
                    break;
                case "7":
                    System.out.println("----- СПИСОК ЧИТАЕМЫХ КНИГ -----");
                    userCardManager.printListReadBooks(listHistories);
                    break;
                default:
                    System.out.println("Нет такой задачи");
            }
        } while (repeat);
    }
    public void printReaderUI(List<User> listUsers, List<Reader> listReaders, List<Book> listBooks, List<History> listHistories) {
        boolean repeat = true;
        do {
            System.out.println("======================================");
            System.out.println("Задачи: ");
            System.out.println("0. Покинуть библиотеку");
            System.out.println("1. Просмотреть список книг");
            System.out.println("2. Взять книгу");
            System.out.println("3. Вернуть книгу");
            System.out.println("4. Список читаемых книг");

            System.out.printf("Выберите задачу: ");
            String task = scanner.nextLine();
            System.out.println("======================================");
            switch (task) {
                case "0":
                    System.out.println("----- КОНЕЦ ПРОГРАММЫ -----");
                    repeat = false;
                    break;
                case "1":
                    System.out.println("----- СПИСОК КНИГ -----");
                    bookManager.printListBooks(listBooks);
                    break;
                case "2":
                    System.out.println("----- ВЗЯТЬ КНИГУ -----");
                    History history = userCardManager.checkOutBook(listBooks, listReaders);
                    if(history == null) break;
                    userCardManager.addHistoryToArray(history, listHistories);
                    break;
                case "3":
                    System.out.println("----- ВЕРНУТЬ КНИГУ -----");
                    userCardManager.returnBook(listHistories);
                    break;
                case "4":
                    System.out.println("----- СПИСОК ЧИТАЕМЫХ КНИГ -----");
                    userCardManager.printListReadBooks(listHistories);
                    break;
                default:
                    System.out.println("Нет такой задачи");
            }
        } while (repeat);
    }
}
