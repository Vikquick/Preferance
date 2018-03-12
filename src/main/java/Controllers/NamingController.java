package Controllers;

import Models.Gamers.Names;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NamingController {
    private static Logger logger = Logger.getLogger(NamingController.class);

    static Names[] names = Names.values();
    static List<Names> namesList = new ArrayList<>(Arrays.asList(names));

    public String nameGamer() {

        logger.info("При инициализации контроллера наименований берем массив имен из ENUM Names");
        Collections.shuffle(namesList);
        logger.info("Расставляем имена в массиве в случайном порядке");
        String name = namesList.get(0).toString();
        namesList.remove(0);
        logger.info("Возвращаем имя " + name + " в сеттер имени игрока, а само имя удаляем");
        return name;
    }
}
