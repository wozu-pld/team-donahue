package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import model.Item;
import model.Price;

public class ItemMgr {

 /**
  * Method to return all available Item information after reading from underlying data source, such as file.
  * @return Map of Items in key/value pair, where key is itemId and value is other item details
  */
 public HashMap<String, Item> getAllItems() {
 
  HashMap<String, Item> itemMap = new HashMap<String, Item>();
 
  //create file object from the external file
  File itemFile = new File("itemmaster.csv");
 
  //When reading a file it is necessary to catch Exception, 
  //such as FileNotFoundException by using try-catch block.
  try {
   //Use Scanner to read external file : itemmaster.csv
   Scanner scanner = new Scanner(itemFile);
  
   //continue the loop till the end of file
   while (scanner.hasNext()) {
   
    //Read record from the external file line by line
    String fileRecord = scanner.nextLine();

    //split the record based on the comma (,) delimiter
    //comma delimiter to split the string record 
    //into separate string and set to array String[]

    String[] fileRecordSplit = fileRecord.split(",");  
   
    //Populate item object from the split record read from the file
    Item item = new Item();
    item.setId(fileRecordSplit[0]);
    item.setDescription(fileRecordSplit[1]);
   
    //create price object to be set later into item object
    Price price = new Price();
    price.setAmount(Double.parseDouble(fileRecordSplit[2]));
    price.setCurrency(fileRecordSplit[3]);

    item.setPrice(price);
   
    itemMap.put(item.getId(), item); //key is ItemId and Value is entire item Object
   }
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }

  return itemMap;
 }
}