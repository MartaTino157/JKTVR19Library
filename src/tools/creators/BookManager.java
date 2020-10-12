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

    public Book addBook() {
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
    
}
