package Goodies;

import java.io.*;
import java.util.*;

class Item  // public class
{
  String name;
  int price;

  public Item(String name, int price) // public constructor
{
    this.name = name;
    this.price = price;
}

  public String toString() // Getters
  { 
      return this.name + ": " + this.price;
  }
}

public class Main 
{
  public static void main(String[] args) throws Exception 
{
    FileInputStream fis=new FileInputStream("E:\\Sanjana\\input.txt");  // Path where input file is present     
    Scanner sc=new Scanner(fis);
    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]); // splits the string you have entered 
    sc.nextLine(); 
    sc.nextLine(); 
    sc.nextLine();

    ArrayList<Item> goodies_items = new ArrayList<Item>(); // ArrayList is used because heterogenous objects are allowed and its a growable array 

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    Collections.sort(goodies_items, new Comparator<Item>()  // It is used to sort the array in descending order
{
      public int compare(Item a, Item b) 
     { 
        return a.price - b.price; 
     } 
});

    int min_diff = goodies_items.get(goodies_items.size()-1).price;
    int min_index = 0;
    for(int i=0;i<goodies_items.size()-number_of_employees+1;i++) 
    {
      int diff = goodies_items.get(number_of_employees+i-1).price-goodies_items.get(i).price;

      if(diff<=min_diff) 
      {
        min_diff = diff;
        min_index = i;
      }
    } // Main logic for finding the goodies of a HR team can distribute so that the difference between the low price goodie and the high price goodie selected is minimum
    
    FileWriter fw = new FileWriter("output.txt"); // Once code is executed output will be saved in output.txt format
    fw.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + number_of_employees; i++) 
    {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
	fw.close();
  }
}