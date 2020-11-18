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
import entity.facades.BookFacade;
import entity.facades.ReaderFacade;
import entity.facades.UserFacade;
import java.util.List;
import java.util.Scanner;
import tools.creators.BookManager;
import tools.creators.ReaderManager;
import tools.creators.UserCardManager;
import tools.creators.UserManager;
import tools.savers.FileManager;
import tools.savers.StorageManagerInterface;

/**
 *
 * @author pupil
 */
public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private UserCardManager userCardManager = new UserCardManager();

    public void printManagerUI() {
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
                    break;
                case "2":
                    System.out.println("----- СПИСОК КНИГ -----");
                    bookManager.printListBooks();
                    break;
                case "3":
                    System.out.println("----- ДОБАВИТЬ ЧИТАТЕЛЯ ------");
                    UserManager userManager = new UserManager();
                    User user = userManager.createUser();
                    break;
                case "4":
                    System.out.println("----- СПИСОК ЧИТАТЕЛЕЙ -----");
                    readerManager.printListReaders();
                    break;
                case "5":
                    System.out.println("----- ВЫДАТЬ КНИГУ -----");
                    userCardManager.checkOutBook();
                    break;
                case "6":
                    System.out.println("----- ВЕРНУТЬ КНИГУ -----");
                    userCardManager.returnBook();
                    break;
                case "7":
                    System.out.println("----- СПИСОК ЧИТАЕМЫХ КНИГ -----");
                    userCardManager.printListReadBooks();
                    break;
                default:
                    System.out.println("Нет такой задачи");
            }
        } while (repeat);
    }
    public void printReaderUI() {
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
                    bookManager.printListBooks();
                    break;
                case "2":
                    System.out.println("----- ВЗЯТЬ КНИГУ -----");
                    userCardManager.checkOutBook();
                    break;
                case "3":
                    System.out.println("----- ВЕРНУТЬ КНИГУ -----");
                    userCardManager.returnBook();
                    break;
                case "4":
                    System.out.println("----- СПИСОК ЧИТАЕМЫХ КНИГ -----");
                    userCardManager.printListReadBooks();
                    break;
                default:
                    System.out.println("Нет такой задачи");
            }
        } while (repeat);
    }
}
