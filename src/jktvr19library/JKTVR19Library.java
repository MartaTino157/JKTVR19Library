/*
 * Ветка SaveToBase2
 * Подключение базы данных и сохранение сущностей в базу
 * Шаги:
 * 1. Добавить библиотеки поставщика персистентсности
 * (EclipseLink(JPA-2.1)) и драйвер базы данных (С:\Program Files\NetBeans 8.2\ide\modules\ext\msql-connector-java-5.1.23-bin.jar)
 * 2. Добавить аннотации @Entity, @Id, @GeneratedValue и др. к полям сущностей
 * 3. Создание БД с помошью phpMyAdmin
 * 4. Создание persistence.xml (файла подключения к базе)
 *      В свойствах подключения прописываем после знака вопроса: 
 *          useUnicode=true&characterEncoding=utf8
 * 5. Добавляем в persistence.xml классы
 * 6. Создаем файл-менеджер сохранения в базу
 * 7. Ветка saveDataBaseOnly
        Подключение программы ек базе данных без сохранения массивов в файл
 */
package jktvr19library;

/**
 *
 * @author pupil
 * 
 */
public class JKTVR19Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String flag = "base";
        if(args.length > 0 ){
            flag = args[0];
        }
        App app = new App(flag);
        app.run();
    }
    
}
