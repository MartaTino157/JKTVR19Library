/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.facades.BookFacade;
import entity.facades.HistoryFacade;
import entity.facades.ReaderFacade;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import jktvr19library.App;
import security.SecureManager;

/**
 *
 * @author pupil
 */
public class UserCardManager {
    private Scanner scanner = new Scanner(System.in);
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    
    private BookFacade bookFacade = new BookFacade(Book.class);
    private ReaderFacade readerFacade = new ReaderFacade(Reader.class);
    private HistoryFacade historyFacade = new HistoryFacade(History.class);

    public void checkOutBook() {
        System.out.println("----- СПИСОК КНИГ -----");
        Long bookNumber;       
        do{
            if(!bookManager.printListBooks()){
                return;
            }
            System.out.printf("Выберите номер книги: ");
            String bookNumberStr = scanner.nextLine();
            try {
                bookNumber = Long.parseLong(bookNumberStr);
                break;
            } catch (Exception e) {
                System.out.println("Выберите номер из списка");
            }
        }while(true);
        Book book = bookFacade.find(bookNumber);
        Reader reader = null;
        if(SecureManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            Long readerNumber;
            do {            
                System.out.println("----- СПИСОК ЧИТАТЕЛЕЙ -----");
                readerManager.printListReaders();
                System.out.printf("Выберите номер читателя: ");
                String readerNumberStr = scanner.nextLine();
                try {
                    readerNumber = Long.parseLong(readerNumberStr);
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного выше списка читателей");
                    readerNumberStr = scanner.nextLine();
                }
            } while (true);
            reader = readerFacade.find(readerNumber);
        }else if(SecureManager.role.READER.toString().equals(App.loggedInUser.getRole())){
            reader = App.loggedInUser.getReader();
        }
        Calendar calendar = new GregorianCalendar();
        History history = new History(book, reader, calendar.getTime(), null);
        historyFacade.create(history);
    }
    public void returnBook(){
        System.out.println("Читаемые книги: ");
        if(this.printListReadBooks()){
            Long historyNumber;
            do {                
                System.out.println("Выберите номер возвращаемой книги: ");
                String historyNumberStr = scanner.nextLine();
                try {
                    historyNumber = Long.parseLong(historyNumberStr);
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного выше списка");
                }
            } while (true);
            History history = historyFacade.find(historyNumber);
            history.setReturnDate(new GregorianCalendar().getTime());
            historyFacade.edit(history);
        }
    }

    public boolean printListReadBooks() {
        if(SecureManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            List<History> listHistories = historyFacade.findAll(true);
            if(listHistories == null){
                System.out.println("Читаемых книг нет");
                return false;
            }
            for (int i=0; i<listHistories.size(); i++) {
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                        ,listHistories.get(i).getId()
                        ,listHistories.get(i).getBook().getName()
                        ,listHistories.get(i).getReader().getFirstname()
                        ,listHistories.get(i).getReader().getLastname()
                );
            }
        }else if(SecureManager.role.READER.toString().equals(App.loggedInUser.getRole())){
            List<History> listHistories = historyFacade.findAll(App.loggedInUser.getReader());
            if(listHistories == null){
                System.out.println("Читаемых книг нет");
                return false;
            }
            for (int i=0; i<listHistories.size(); i++) {
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                        ,listHistories.get(i).getId()
                        ,listHistories.get(i).getBook().getName()
                        ,listHistories.get(i).getReader().getFirstname()
                        ,listHistories.get(i).getReader().getLastname()
                );
            }
        }
        return true;
    }
}
