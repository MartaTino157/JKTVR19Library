/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19library;

import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class App {
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("----- БИБЛИОТЕКА     -----");
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
            System.out.println("Выберите задачу: ");
            System.out.println("======================================");
            String task = scanner.nextLine();
            switch (task) {
                case "0":
                    System.out.println("----- КОНЕЦ ПРОГРАММЫ -----");
                    repeat = false;
                    break;
                case "1":
                    System.out.println("----- ДОБАВИТЬ КНИГУ -----");
                    break;
                case "2":
                    System.out.println("----- СПИСОК КНИГ -----");
                    break;
                case "3":
                    System.out.println("----- ДОБАВИТЬ ЧИТАТЕЛЯ ------");
                    break;
                case "4":
                    System.out.println("----- СПИСОК ЧИТАТЕЛЕЙ -----");
                    break;
                case "5":
                    System.out.println("----- ВЫДАТЬ КНИГУ -----");
                    break;
                case "6":
                    System.out.println("----- ВЕРНУТЬ КНИГУ -----");
                    break;
                default:
                    System.out.println("Нет такой задачи");
            }
        }while(repeat);
    }
    
}
