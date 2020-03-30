package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Item;
import controller.ItemMgr;

public class ShoppingCartDao {
 //CRUD , Create, Read, Update, Delete
 //Data Source

 //Load items to in memory storage using ArrayList
 static ArrayList<String> itemIdList = new ArrayList<String>();

 /**
  * Add item id to itemIdList ArrayList
  */
 public void create() {
  Scanner scanner = new Scanner(System.in);
  String itemId = "0";
 
  do {
   //Prompt to Operator !!!
   System.out.println("Enter ItemId, [0 = Exit] : ");
   itemId = scanner.next();
   //do not add itemId as 0 (zero)
   if (!itemId.equals("0")) {
    itemIdList.add(itemId);
   }
  
  } while (!itemId.equals("0"));
 }

 /**
  * Return list of Item ids of the items purchased by customers
  * @return array list of item ids
  */
 public ArrayList<String> read() {
  return itemIdList;
 }

 /**
  * TODO : Implement this method if necessary
  */
 public void update() {
 }

 /**
  * Delete item id from the itemIdList 
  * @param itemid the id to be removed from the list
  */
 public void delete(String itemid) {
  itemIdList.remove(itemid);
 }

 /**
  * Method to create item objects based on list of item Ids
  * @param itemIds ids of the item to be populated with all necessary details such as Description, Price 
  * @return items with details 
  */
 public ArrayList<Item> loadItemDetails(ArrayList<String> itemIds) {
 
  ArrayList<Item> purchasedItemList = new ArrayList<Item>();
 
  //read all the available item map
  ItemMgr itemMgr = new ItemMgr();
  HashMap<String,Item> allItems = itemMgr.getAllItems();
 
  for (int i = 0; i < itemIds.size(); i++) {
   purchasedItemList.add(allItems.get(itemIds.get(i)));
  }
  return purchasedItemList;
 }
}
