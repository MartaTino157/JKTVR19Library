/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Reader;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class ReaderManager {

    public Reader addReader() {
        Reader reader = new Reader();
        System.out.println(" --- Зарегистрировать читателя --- ");
        System.out.printf("Введите имя: ");
        Scanner scanner = new Scanner(System.in);
        reader.setFirstname(scanner.nextLine());
        System.out.printf("Введите фамилию: ");
        reader.setLastname(scanner.nextLine());
        System.out.printf("Введите номер телефона: ");
        reader.setPhone(scanner.nextLine());
        
        return reader;
    }
    
}
