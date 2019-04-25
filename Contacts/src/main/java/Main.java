import model.Organization;
import model.Person;
import model.Storage;
import view.Menu;
import view.View;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Storage.add(new Person("Саша", "Федоров", new Date(), "male", "+7(905)333-33-33"));
        Storage.add(new Organization("Шиномонтаж", "+7(903)222-22-22", "ул. Шиномонтажная"));
        View menu = new Menu();
        menu.use();
    }

}
