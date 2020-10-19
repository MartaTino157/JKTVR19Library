/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Book;
import java.util.Scanner;

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
        System.out.printf("Год издания: ");
        do{
            String publishedYear = scanner.nextLine();
            try{
                book.setPublishedYear(Integer.parseInt(publishedYear));
                break;
            }catch(Exception e){
                System.out.println("Вводите цифрами!");
            }
        }while(true);
        return book;
    }

    public void addBookToArray(Book book, Book[] books) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            }
        }
    }

    public boolean printListBooks(Book[] books) {
        if(books == null || books.length < 1){
            System.out.println("Книг нет!");
            return false;
        }
        int n = 0;
        for (Book b : books) {
            if(b != null){
                System.out.println(n+1+". "+b.toString());
                n++;
            }
        }
        return true;
    }
    
}
