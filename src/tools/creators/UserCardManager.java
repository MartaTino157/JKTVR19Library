/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class UserCardManager {
    private Scanner scanner = new Scanner(System.in);

    public History giveBook(Book[] books, Reader[] readers) {
        //History history = new History();
        System.out.println("----- СПИСОК ЧИТАТЕЛЕЙ -----");
        int n = 0;
        for (Reader r : readers) {
            if(r != null){
                System.out.println(n+1+". "+r.toString());
                n++;
            }
        }
        System.out.printf("Выберите номер читателя: ");
        int readerNumber = scanner.nextInt();
        scanner.nextLine(); //помогает не записать enter
        Reader reader = readers[readerNumber-1];
        System.out.println("----- СПИСОК КНИГ -----");
        int j = 0;
        for (Book b : books) {
            if(b != null){
                System.out.println(j+1+". "+b.toString());
                j++;
            }
        }
        System.out.printf("Выберите номер книги: ");
        int bookNumber = scanner.nextInt();
        Book book = books[bookNumber-1];
        Calendar calendar = new GregorianCalendar();
        /*history.setBook(book);
        history.setReader(reader);
        history.setTakeOnDate(calendar.getTime());
        History history = new History(book, reader, calendar.getTime(), null);        
        return history;*/
        return new History(book, reader, calendar.getTime(), null);
    }
    public void returnBook(History[] histories){
        System.out.println("Читаемые книги: ");
        boolean notReadBooks = true;
        for (int i=0; i<histories.length; i++) {
            if(histories[i] !=null && histories[i].getReturnDate() == null){
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                        ,i+1
                        ,histories[i].getBook().getName()
                        ,histories[i].getReader().getFirstname()
                        ,histories[i].getReader().getLastname()
                );
                notReadBooks = false;
            }
        }
        if(notReadBooks){
            System.out.println("Читаемых книг нет");
            return;
        }
        System.out.println("Выберите номер возвращаемой книги: ");
        int historyNumber = scanner.nextInt();
        histories[historyNumber-1].setReturnDate(new GregorianCalendar().getTime());
        
    }
}
