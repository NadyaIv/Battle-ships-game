import manager.ManagerImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class testManager {
    private ManagerImpl manager;
    @Before
    public void SetUp() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this. manager= new ManagerImpl();
    }
    @Test
    public void isShooted(){

    }
}
