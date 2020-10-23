/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.savers;

import entity.Reader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pupil
 */
public class ReadersStorageManager {

    public void saveReadersToFile(List<Reader> listReaders) {
        String fileName = "readers";
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listReaders);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.println("Нет такого файла!");
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода");
        }
        
    }
    public List<Reader> loadReadersFromFile() {
        //Reader[] readers = new Reader[10];
        List<Reader>  readers = null;
        String fileName = "readers";
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            return (List<Reader> ) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("Нет такого файла!");
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода");
        } catch (ClassNotFoundException ex) {
            System.out.println("Нет такого класса");
        }
        
        return readers;
    }
    
}
