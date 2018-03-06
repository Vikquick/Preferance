package Controllers;

import Models.Gamers.Names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NamingController {
    static Names[] names = Names.values();
    static List<Names> namesList = new ArrayList<Names>(Arrays.asList(names));

    public String nameGamer() {
        Collections.shuffle(namesList);
        String name = namesList.get(0).toString();
        namesList.remove(0);
        return name;
    }
}
