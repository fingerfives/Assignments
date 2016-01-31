import java.util.Scanner;
import java.text.DecimalFormat;

public class SaleItems {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        DecimalFormat f = new DecimalFormat("0.00");
        
        int answer = 1;
        int firstcust = 1;
       
        
        while(answer == 1){
        //This will display the prices for the items we're selling
        System.out.println ("Here is a list of the items we have for sale:");
        System.out.println ("Books.... $5.00 each \nBookmarks.... $1.00 each \n  6 for $5.00");
        System.out.println ("Paintings of books.... $100");
        
        //This will get the total number of bookmarks and sort them into individual ones and packs of 6
        System.out.println ("How many bookmarks would you like to purchase?");
        int totbookmarks = input.nextInt();
        
        //Stops the user from entering a negative value
        while (totbookmarks < 0){
          System.out.println ("Hey! You can't order a negative number of bookmarks!\n");
          System.out.println ("How many bookmarks would you like to purchase?");
          totbookmarks = input.nextInt();
        }
        
        int packs = totbookmarks / 6;
        int indiv = totbookmarks % 6;
        
        //This will calculate the cost of the boomarks
        double indivBookmarks = 1.00;
        double packBookmarks = 5.00;
        double bookmarkCost = (packBookmarks * packs) + (indivBookmarks * indiv);
        
        //This will get the total number of books and calculate the cost
        System.out.println ("How many books would you like to purchase?");
        int totbooks = input.nextInt();
        
        //Stops the user from entering a negative value
        while (totbooks < 0){
          System.out.println ("Hey! You can't order a negative number of books!\n");
          System.out.println ("How many books would you like to purchase?");
          totbooks = input.nextInt();
        }

        double books = 5.00;
        double bookCost = books * totbooks;
        
        //This will get the total number of paintings and calculate the cost
        System.out.println("How many paintings of books would you like to purchase?");
        int totpaint = input.nextInt();
        
        //Stops the user from entering a negative value
        while (totpaint < 0){
          System.out.println ("Hey! You can't order a negative number of paintings!\n ");
          System.out.println ("How many paintings would you like to purchase?");
          totpaint = input.nextInt();
        }
        
        double painting = 100.00;
        double paintCost = painting * totpaint;
        
        System.out.println("Here is your order:\n" + totbooks + " books \n" + totpaint + " paintings \n" + totbookmarks + " bookmarks");
        
        System.out.println("Is this okay? \nEnter 1 for 'yes' and 2 for 'no'.");
        int isOkay = input.nextInt();
        
        //Stops the user from entering an invalid number
        while (isOkay != 1 && isOkay != 2){
          System.out.println ("That is not a valid response!\nEnter 1 for 'yes' and 2 for 'no'");
          isOkay = input.nextInt(); 
        }
        
        //IGiving the customer the option of changing a part of their order
        while (isOkay == 2){
            
            System.out.println("Which part of your order would you  like to edit? \n"
                    + "Enter 1 for books, 2 for bookmarks, or 3 for paintings: ");
            int editResponse;
            editResponse = input.nextInt();
            
            //Prompts the user to enter a correct response
            while (editResponse != 1 && editResponse!=2 && editResponse!= 3){
              System.out.println ("That is an invalid response.\nWhich part of your order would you  like to edit? \n"
                    + "Enter 1 for books, 2 for bookmarks, or 3 for paintings: ");
              editResponse = input.nextInt();
            }
            
            //Editing the number of books
            if (editResponse == 1){
                System.out.println ("How many books would you like to purchase?");
                totbooks = input.nextInt();
                
                //Stops the user from entering a negative value
                while (totbooks < 0){
                  System.out.println ("Hey! You can't order a negative number of books!\n");
                  System.out.println ("How many books would you like to purchase?");
                  totbooks = input.nextInt();
                }
                
                books = 5.00;
                bookCost = books * totbooks;
                
            }else if(editResponse == 2){
            
              System.out.println ("How many bookmarks would you like to purchase?");
              totbookmarks = input.nextInt();
              
              //Stops the user from entering a negative value
               while (totbookmarks < 0){
                 System.out.println ("Hey! You can't order a negative number of bookmarks\n!");
                 System.out.println ("How many bookmarks would you like to purchase?");
                 totbookmarks = input.nextInt();
               }
               
              packs = totbookmarks/6;
              indiv = totbookmarks%6;
              
              //This will calculate the cost of the boomarks
              indivBookmarks = 1.00;
              packBookmarks = 5.00;
              bookmarkCost = (packBookmarks * packs) + (indivBookmarks * indiv);
             
            }else if (editResponse == 3){
                    
                    //This will get the total number of paintings and calculate the cost
                    System.out.println("How many paintings of books would you like to purchase?");
                    totpaint = input.nextInt();
                    
                    //Stops the user from entering a negative value
                    while (totpaint < 0){
                       System.out.println ("Hey! You can't order a negative number of paintings!\n");
                       System.out.println ("How many paintings would you like to purchase?");
                       totpaint = input.nextInt();
                     }
                    
                    painting = 100.00;
                    paintCost = painting * totpaint;
            }
            
            System.out.println("Here is your order:\n" + totbooks + " books \n" + totpaint + " paintings \n" + totbookmarks + " bookmarks");
            
            System.out.println("Is this okay? \nEnter 1 for 'yes' and 2 for 'no':");
            isOkay = input.nextInt();
            
            //Promts the user for 
            while (isOkay != 1 && isOkay != 2){
              System.out.println ("That is not a valid response!\nEnter 1 for 'yes' and 2 for 'no'");
              isOkay = input.nextInt();
          
            }
        }
        
        //custnum is the customer number counter
        //whether or not the customer wins the discount is coningent on whether custnum%3 = 0
        
        int custnum = firstcust++;
        int isDiscount = custnum % 3;
        
        if (isDiscount == 0){
          
          double prediscount = (paintCost + bookCost + bookmarkCost);
          double discount = prediscount * .1;
          double subtotal = prediscount - discount;
          double total = subtotal * 1.07;
          double tax = subtotal * .07;
          System.out.println ("\nYou won a 10% discount!");
          
          if(totpaint > 0){
            System.out.println (totpaint + "   paintings:             $" + f.format(paintCost));
          }
          if(totbooks > 0){
            System.out.println (totbooks + "   books:                 $" + f.format(bookCost));
          }
          if (indiv > 0){
            System.out.println (indiv + "   individual bookmarks:  $" + f.format((indivBookmarks * indiv)));
          }
          if (packs > 0){
            System.out.println (packs + "   bookmark packs:        $" + f.format((packBookmarks * packs)));
          }
          
          System.out.println ("\n  Subtotal:                $" + f.format(subtotal));
          System.out.println ("  Tax:                     $" + f.format(tax));
          System.out.println ("  Discount:               -$" + discount);
          System.out.println ("\n  Total                    $" + f.format(total));     
          
          System.out.println ("\nPlease enter you payment:");
          float payment = input.nextFloat();
          float change = (float)total - payment;
        
          while (change > 1){
          
            System.out.println ("Not enough money - please re-enter:");
            payment = input.nextFloat();
            change = (float)total - payment;
          
          }
        
          if (change < 0){
          
            change = change * (-1);
            System.out.println ("Your change is $" + f.format(change));
          
          }
        }
        
        else{
          
          double subtotal = paintCost + bookCost + bookmarkCost;
          double taxamount = subtotal * .07;
          double total = subtotal * 1.07;
          System.out.println ("You did not get a discount! Better luck next time!");
          
          if(totpaint > 0){
          System.out.println (totpaint + "   paintings:             $" + f.format(paintCost));
          }
          if(totbooks > 0){
          System.out.println (totbooks + "   books:                 $" + f.format(bookCost));
          }
          if (indiv > 0){
          System.out.println (indiv + "   individual bookmarks:  $" + f.format((indivBookmarks * indiv)));
          }
          if (packs > 0){
          System.out.println (packs + "   bookmark packs:        $" + f.format((packBookmarks * packs)));
          }
          
          System.out.println ("\n  Subtotal:                $" + f.format(subtotal));
          System.out.println ("  Tax:                     $" + f.format(taxamount));
          System.out.println ("\n  Total                    $" + f.format(total));                   
                              
          System.out.println ("\nPlease enter you payment:");
          float payment = input.nextFloat();
          double change = total - payment;
        
          while (change > 1){
          
            System.out.println ("Not enough money - please re-enter:");
            payment = input.nextFloat();
            change = total - payment;
          
          }
        
          if (change < 0){
            change = change * (-1);
            System.out.println ("Your change is $" + f.format(change));
          
          }
          
          System.out.println ("Thank you for your patronage!");
        }
        
        System.out.println ("\n\nIs there another customer in line? \nEnter 1 for 'yes' and 2 for 'no'");
        answer = input.nextInt();
        
        //Prompts the user for a valid response
        while (answer != 1 && answer != 2){
          System.out.println ("That is not a valid response!\nEnter 1 for 'yes' and 2 for 'no'");
          answer = input.nextInt();
          
        }
        }
        
        System.exit(0);
    }    
}