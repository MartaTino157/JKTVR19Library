/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Book;
import entity.controllers.BookController;
import java.util.List;
import java.util.Scanner;
import jktvr19library.App;
import tools.savers.BaseManager;
import tools.savers.FileManager;
import tools.savers.StorageManagerInterface;

/**
 *
 * @author pupil
 */
public class BookManager {

    public Book createBook() {
        Book book = new Book();
        System.out.println(" --- Добавить новую книгу --- ");
        System.out.printf("Введите название: ");
        Scanner scanner = new Scanner(System.in);
        book.setName(scanner.nextLine());
        System.out.printf("Введите автора: ");
        book.setAuthor(scanner.nextLine());
        int numPublishedYear;
        do{
            System.out.printf("Год издания: ");
            String strPublishedYear = scanner.nextLine();
            try{
                numPublishedYear = Integer.parseInt(strPublishedYear);
                break;
            }catch(Exception e){
                System.out.println("Вводите цифрами!");
            }
        }while(true);
        book.setPublishedYear(numPublishedYear);
        return book;
    }

    public boolean printListBooks() {
        BookController bc = new BookController();
        List<Book> listBooks = bc.findAll();
        if(listBooks == null || listBooks.size() < 1){
            System.out.println("Книг нет!");
            return false;
        }
        for (Book b : listBooks) {
            if(b != null){
                System.out.println(b.getId()+". "+b.toString());
            }
        }
        return true;
    }
    
}
