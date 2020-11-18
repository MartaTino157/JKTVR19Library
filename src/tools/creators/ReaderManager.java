/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Reader;
import entity.facades.ReaderFacade;
import factory.FacadeFactory;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class ReaderManager {
    private ReaderFacade readerFacade = FacadeFactory.getReaderFacade();

    public Reader createReader() {
        Reader reader = new Reader();
        System.out.println(" --- Зарегистрировать читателя --- ");
        System.out.printf("Введите имя: ");
        Scanner scanner = new Scanner(System.in);
        reader.setFirstname(scanner.nextLine());
        System.out.printf("Введите фамилию: ");
        reader.setLastname(scanner.nextLine());
        System.out.printf("Введите номер телефона: ");
        reader.setPhone(scanner.nextLine());
        readerFacade.create(reader);
        return reader;
    }

    public void printListReaders() {
        List<Reader> listReaders = readerFacade.findAll();
        if(listReaders == null){
            System.out.println("Нет читателей");
            return;
        }
        for (Reader r : listReaders) {
            if(r != null){
                System.out.println(r.getId()+". "+r.toString());
            }
        }
    }
    
}
