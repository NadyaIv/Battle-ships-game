import entities.*;
import interfaces.Manager;
import manager.ManagerImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
      Manager manager=new ManagerImpl();
        Player firstPlayer= new Player();
        Player secondPlayer= new Player();
      manager.addPlayer(firstPlayer);
      manager.addPlayer(secondPlayer);
      manager.play();

    }
}
