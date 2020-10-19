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
import jktvr19library.App;

/**
 *
 * @author pupil
 */
public class UserCardManager {
    private Scanner scanner = new Scanner(System.in);
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();

    public History checkOutBook(Book[] books, Reader[] readers) {
        System.out.println("----- СПИСОК КНИГ -----");
        int bookNumber;       
        do{
            if(!bookManager.printListBooks(books)){
                return null;
            }
            System.out.printf("Выберите номер книги: ");
            String bookNumberStr = scanner.nextLine();
            try {
                bookNumber = Integer.parseInt(bookNumberStr);
                if (bookNumber < 1 && bookNumber >=books.length){
                    throw new Exception(" Выход за диапазон массива книг");
                }
                break;
            } catch (Exception e) {
                System.out.println("Выберите номер из указанного выше списка книг");
                bookNumberStr = scanner.nextLine();
            }
        }while(true);
        Book book = books[bookNumber-1];
        Reader reader = null;
        if("MANAGER".equals(App.loggedInUser.getRole())){
            int readerNumber;
            do {            
                System.out.println("----- СПИСОК ЧИТАТЕЛЕЙ -----");
                readerManager.printListReaders(readers);
                System.out.printf("Выберите номер читателя: ");
                String readerNumberStr = scanner.nextLine();
                try {
                    readerNumber = Integer.parseInt(readerNumberStr);
                    if (readerNumber < 1 && readerNumber > readers.length){
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного выше списка читателей");
                    readerNumberStr = scanner.nextLine();
                }
            } while (true);
            reader = readers[readerNumber-1];
        }else if("READER".equals(App.loggedInUser.getRole())){
            reader = App.loggedInUser.getReader();
        }
            
        Calendar calendar = new GregorianCalendar();
        return new History(book, reader, calendar.getTime(), null);
    }
    public void returnBook(History[] histories){
        System.out.println("Читаемые книги: ");
        this.printListReadBooks(histories);
        System.out.println("Выберите номер возвращаемой книги: ");
        int historyNumber = scanner.nextInt();
        histories[historyNumber-1].setReturnDate(new GregorianCalendar().getTime());
    }

    public void addHistoryToArray(History history, History[] histories) {
        for (int i = 0; i < histories.length; i++) {
            if (histories[i] == null) {
                histories[i] = history;
                break;
            }
        }
    }

    public void printListReadBooks(History[] histories) {
        boolean notReadBooks = true;
        if("MANAGER".equals(App.loggedInUser.getRole())){
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
            }
        }else if("READER".equals(App.loggedInUser.getRole())){
            for (int i=0; i<histories.length; i++) {
                if(histories[i] !=null 
                        && histories[i].getReturnDate() == null
                        && histories[i].getReader().equals(App.loggedInUser.getReader())){
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
            }
        }
    }
}
