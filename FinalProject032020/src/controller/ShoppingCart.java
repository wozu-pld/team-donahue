package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import model.Customer;
import model.Item;
import model.Machine;
import model.Operator;
import model.Transaction;
import dao.ShoppingCartDao;

public class ShoppingCart {

 ShoppingCartDao cartDao = new ShoppingCartDao();

 /**
  * Method to start Shopping process
  */
 public void start() {
 
  int choice = 0;
 
  Scanner scanner  = new Scanner(System.in);
 
  do {
   System.out.println("Enter [1 = Add, 2 = Delete, 0 = Exit]");
   choice = scanner.nextInt();
  
   switch(choice) {
    case 1 : 
     add();
     break;
    case 2 :
     Scanner sc = new Scanner(System.in);
     System.out.println("Enter Item id to be removed = ");
     String itemId = sc.next();
     delete(itemId);
     break;
    case 0 :
     break;
    default:
    System.out.println("Invalid choice.");

   }
  } while (choice != 0);
 }

 //DAO = Data Access object
 //csv = Comma Separate Value
 public void add() {
  //TODO: do validation if any
  cartDao.create();
 }

 /**
  * Method to prepare final transaction details and print the receipt on the screen
  */
 public void checkOut() {
  //TODO: do validation if any
  ArrayList<String> itemIds = cartDao.read();
 
  //Create details of each item based on the item id
  ArrayList<Item> purchaseditems = cartDao.loadItemDetails(itemIds);

  double total = 0;
 
  System.out.println("++++++ List of items purchased ++++++");
 
  for (Item item : purchaseditems) {
    total = total + item.getPrice().getAmount();
    System.out.println(item.printStatus());
  }

  System.out.println("-----------------------------------");
  System.out.println("Total amount : " + total);
  System.out.println("+++++++++++++++++++++++++++++++++++");
 
  //record the transaction to external persistent file
  recordTransaction(purchaseditems);
 }

 //method to persist transaction details to external file for further usage
 private void recordTransaction(ArrayList<Item> purchaseditems) {
  //create Transaction
  Transaction transaction = new Transaction();
  transaction.setId("T-788"); //TODO: write a method to generate unique Id for each transaction
 
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  transaction.setDate(dateFormat.format(new Date()));
 
  Operator operator = new Operator();
  operator.setId("O-005");
  transaction.setOperator(operator);
 
  Machine machine = new Machine();
  machine.setId("M-101");
  transaction.setMachine(machine);
 
  //create and set customer
  Customer customer = new Customer();
  customer.setId("C-101"); //TODO: write a method to generate unique Customer Id for each customer transaction  
  transaction.setCustomer(customer); 

  //Convert from ArrayList of item to the Array of item objects
  Item[] items = purchaseditems.toArray(new Item[purchaseditems.size()]);
  transaction.setItems(items);
 
  //save transaction to external file
   try {
             FileWriter writer = new FileWriter("alltransaction.csv", true); //set true for append mode
             BufferedWriter bufferedWriter =new BufferedWriter(writer);
             
             for (Item item : items) {             
              bufferedWriter.write(transaction.getId());
              bufferedWriter.write(",");
              bufferedWriter.write(transaction.getDate());
              bufferedWriter.write(",");
              bufferedWriter.write(transaction.getOperator().getId());
              bufferedWriter.write(",");
              bufferedWriter.write(transaction.getMachine().getId());
              bufferedWriter.write(",");
              bufferedWriter.write(transaction.getCustomer().getId());
              bufferedWriter.write(",");
              bufferedWriter.write(item.getId());
              bufferedWriter.write(",");
              //convert double to string before writing to file              
              bufferedWriter.write(String.valueOf(item.getPrice().getAmount())); 
              bufferedWriter.write("\n");
             }
             
             bufferedWriter.flush();
             bufferedWriter.close();
             writer.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
 }

 public void delete(String itemId) {
  //TODO: do validation if any
  cartDao.delete(itemId);
 }
}