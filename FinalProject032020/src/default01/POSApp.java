import java.util.HashMap;
import java.util.Iterator;
import model.Item;
import controller.ItemManager;
import controller.ShoppingCart;

public class POSApp {

 public static void main(String[] args) {
  //Load list of available item list for reference
  ItemMgr itemMgr = new ItemMgr();
  HashMap<String, Item> itemMap = itemMgr.getAllItems();

  Iterator<String> iterator = itemMap.keySet().iterator();

  System.out.println("-----List of Available Items------[Item#, Description, Price, Currency]");

  while (iterator.hasNext()) {
   Item item = itemMap.get(iterator.next()); 
   System.out.println(item.printStatus());
  }

  System.out.println("--------");

  ShoppingCart cart = new ShoppingCart();
  cart.start();
  cart.checkOut();

  System.out.println("Thank you for using Simple POS, Have a great day! ");
 }
}
