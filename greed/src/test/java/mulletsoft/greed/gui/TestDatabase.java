package mulletsoft.greed.gui;

import mulletsoft.greed.model.Source;
import mulletsoft.greed.model.User;


public class TestDatabase {
  
  static void createTestDatabase(){
    User zenon = new User();
    zenon.setName("Zenon");
    zenon.setPasswordHash("90be34c24cc2645b98db810dfbb84a97");
    zenon.setSalt("abc");
    
    User jan = new User();
    jan.setName("Jan");
    jan.setPasswordHash("3ef18d32af753194e03d2d39fc7f1822");
    jan.setSalt("123");
    
    Source im = new Source();
    
  }
  
}
