import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.FileReader;


public class Blackjack{
  
  public static void main(String[] args)throws IOException{
    
    Scanner input = new Scanner(System.in);
    //Reads from package used to help make this class
        
    
    int handsPlayed = 0;
    int handsWon = 0;
    double moneySaved = 100.00;
    String cont = "Y";
    String hitStay = "H";
    double bet;
    Card newCard = new Card();
    DecimalFormat f = new DecimalFormat("0.00");
    
    
    
    System.out.println ("What is your name?");
    String userName = (String) input.next();
    Player user = new Player (userName);
    
    File file = new File(userName + ".txt");
    
    if (!file.exists()){
      PrintWriter outputFile = new PrintWriter(file);
      System.out.println("Welcome to Your New Game!");
    } else {
      System.out.println ("Welcome back to you game " + userName);
    }
    
    Scanner inputFile = new Scanner(file);

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();
        String text = out.toString();
        String[] userSave = text.split(" ");

    
    //Checking for the existence of certain saved information
    //In the file and importing their values into the game
    
       handsPlayed =  Integer.parseInt(userSave[1]);
       handsWon = Integer.parseInt(userSave[2]);
       moneySaved = Double.parseDouble(userSave[3]);
   
    
    
    
    
    
    
    //At the end of this while loop, the user will be asked whether or not they
    //would like to start another hand. This allows the game to be played
    //an infinite number of times.
    
    while (cont.equalsIgnoreCase("y")){
      int playerScore = 0;
      double winnings = 0;
      boolean playerBust = false;
      boolean dealerBust = false;
      
      
      
      //At the beginning of each hand, the user will be shown
      //their current statistics (money, hands played, etc...)
      System.out.printf ("Name: %15s",userName + "\n");
      System.out.printf ("Total Hands: %8s",handsPlayed + "\n");
      System.out.printf ("Hands Won: %10s",handsWon + "\n");
      System.out.printf ("Money: %16s", f.format(moneySaved) + "\n\n\n");
      
      
      
      System.out.println ("How much do you want to bet?");
      bet = input.nextDouble();
      
      
      //trying to do an input mismatch exception here but
      //it doesn't seem to be working
      //will try as a last thing after the load data bug has been resolved
      /*
       try{
       System.out.println ("How much do you want to bet?");
       bet = (double) input.nextDouble();
       } catch (InputMismatchException e){
       
       System.out.println ("INVALID RESPONSE!! How much money would you like to bet?");
       bet = (double) input.nextDouble();
       
       }
       */
      
      
      while (bet > moneySaved || bet <= 0){
        System.out.println ("You don't have that amount...\nHow much do you want to bet:");
        bet = input.nextDouble();
      }
      
      
      
      
      //This is the beginnng of the player's turn
      System.out.println ("Player Hand");
      
      
      //Generates the first two cards of the hand using
      //the "cardGenerator" method from the "Card" class
      playerScore += newCard.cardGenerator(1);
      playerScore += newCard.cardGenerator(1);
      
      System.out.println ("Score: " + playerScore);
      
      System.out.println ("Would you like to [H]it or [S]tay:");
      hitStay = input.next();
      
      while (!hitStay.equalsIgnoreCase("h") && !hitStay.equalsIgnoreCase("s")){
        
        System.out.println("ERROR!!\nWould you like to [H]it or [S]tay:");
        hitStay = (String) input.next();
      }
      
      
      
      //Asking the user whether they want to hit or not
      //This "label2" is for the case where the player's score goes
      //over 21. The break statement within the nested if statement will
      //leave this while loop and execute the code following it.
      label2:
        while (hitStay.equalsIgnoreCase("Hit") || hitStay.equalsIgnoreCase("h")){
        
        playerScore += newCard.cardGenerator(1);
        System.out.println ("Score: " + playerScore);
        
        //If at any point the player's score goes over 21, the system
        //will immediately end the user's turn and display a "busted" message.
        if (playerScore > 21){
          System.out.println ("PLAYER HAS BUSTED!!");
          playerBust = true;
          break label2;
        }
        System.out.println ("Would you like to [H]it or [S]tay:");
        hitStay = input.next();
        
        while (!hitStay.equalsIgnoreCase("h") && !hitStay.equalsIgnoreCase("s")){
          
          System.out.println ("Would you like to [H]it or [S]tay:");
          hitStay = (String) input.next();
        }
        
      }
        
        
        System.out.println ("Player score: " + playerScore + "\n\n");
        
        
        
        
        
        
        
        
        
        //Now we will begin the dealer's hand
        System.out.println ("Dealer Hand");
        int dealerScore = 0;
        
        dealerScore += newCard.cardGenerator(1);
        dealerScore += newCard.cardGenerator(1);
        
        while (dealerScore < 18){
          
          System.out.println ("Score " + dealerScore);
          
          if (dealerScore < 18){
            System.out.println ("Hit!");
            dealerScore += newCard.cardGenerator(1);
          }
          
        }
        
        
        //This marks the end of the Dealer's hand
        if (dealerScore > 21){
          System.out.println ("DEALER HAS BUSTED!!");
          dealerBust = true;
        } else {
          System.out.println ("Score " + dealerScore);
          System.out.println ("Stay");
        }
        
        System.out.println ("Dealer score: " + dealerScore + "\n\n");
        
        
        
        
        
        
        //Here the amount of money won/lost by the player will
        //be displayed as well as the result of the game
        //(player/dealer busted, push, etc...)
        if (playerBust == false && dealerBust == false){
          
          if (playerScore == dealerScore){
            
            winnings = 0.00;
            System.out.println("PUSH!!\nNeither side won anything!!");
            
          } else if (dealerScore > playerScore){
            
            winnings = (bet * (-1));
            System.out.println("You LOST your bet of " + bet + "!!");
            
          } else if (playerScore > dealerScore){
            
            winnings = (bet * 2);
            System.out.println("You won " + winnings + "!!\nThat's double your bet!!");
            handsWon = user.numWonCounter(handsWon);
            
          } else if (playerScore == 21){
            
            winnings = (bet * 1.5);
            System.out.println("BLACKJACK!!\nYou won " + winnings);
            user.numWonCounter(handsWon);
            handsWon++;
            
          }
        } else if (playerBust == true && dealerBust == false){
          
          winnings = (bet * (-1));
          System.out.println ("You have BUSTED and lost your bet of " + f.format(bet));
          
        } else if (playerBust == false && dealerBust == true){
          
          winnings = (bet * 2);
          System.out.println("You won " + f.format(winnings) + "!!\nThat's double your bet!!");
          handsWon = user.numWonCounter(handsWon);
          
        } else {
          
          winnings = (bet * (-1));
          System.out.println ("Both player and dealer have BUSTED!!\nYou have lost your bet of " + bet + "!!");
          
          
        }
        
        
        moneySaved += winnings;
        
        
        
        //Calls the respective method to save values to the
        //Player class and then write those values to the .txt
        //file 
        handsPlayed = user.addHand(handsPlayed);
        user.addMoney(moneySaved);
        
        
        
        //This calls the method within the "Player" class which writes all
        //the current game information to the user's .txt file.
        user.writeFile();
        
        
        
        
        //The player is asked if they would like to play another hand.
        //If they elect not to continue the system will exit the program
        System.out.println ("Would you like to play another hand? (Y/N)");
        cont = (String) input.next();
        
        while (!cont.equalsIgnoreCase("Y") && !cont.equalsIgnoreCase("N")){
          
          System.out.println ("ERROR!!\nWould you like to play another hand? (Y/N)");
          cont = (String) input.next();  
        }
        
    }
    
    
    
    
    
    
    input.close();
    inputFile.close();
    
    
    //Just a friendly closing remark
    System.out.println ("Thank you for playing!");
    
    
  }
}









//This is the Player class which will keep track of different user data
//using their respective methods. The last method in this class
//"writeFile" will write the user's save data to their .txt file
class Player{
  
  String playerName;
  int totalHands;
  int iWonNum;
  double savedCash = 0.00;
  
  public Player(String name){
    playerName = name;
  }
  
  public int addHand(int hands){
    
    hands += 1;
    totalHands = hands;
    return hands;
  }
  
  public void addMoney(double cash){
    
    savedCash = cash;
  }
  
  public int numWonCounter(int wonNum){
    
    wonNum += 1;
    iWonNum = wonNum;
    return wonNum;
  }
  
  public void writeFile()throws IOException{
    
    PrintWriter outputFile = new PrintWriter(playerName + ".txt");
    outputFile.println (playerName + " ");
    outputFile.println (totalHands + " ");
    outputFile.println (iWonNum + " ");
    outputFile.println (savedCash + " ");
    
    
    outputFile.close();
  }
  
  
}







//The card class will generate a random card each time
//its method is called in the main method. There is also
//a method within this class to create and return the score
//for the hand
class Card{
  
  int numAces = 0;
  int numCards;
  Random r = new Random();
  int newScore;
  int points = 0;
  int returnPoints;
  
  public int cardGenerator(int loopNum){
    
    String suitc;
    
    for (int x = 0; x < loopNum; x++){
      int face = r.nextInt(12) + 1;
      int suit = r.nextInt(4) + 1;
      
      
      if (suit == 1){
        suitc = "c";
      }else if (suit == 2){
        suitc = "d";
      }else if (suit == 3){
        suitc = "h";
      }else{
        suitc = "s";
      }
      
      
      //Using the randomly generated face value, the system
      //prints out the card
      System.out.print ("Card Dealt: ");
      if (face == 1){
        System.out.print ("A" + suitc + "  \n");
      } else if (face == 10){
        System.out.print ("T" + suitc + "  \n");
      } else if (face == 11){
        System.out.print ("J" + suitc + "  \n");
      } else if (face == 12){
        System.out.print ("Q" + suitc + "  \n");
      } else if (face == 13){
        System.out.print ("K" + suitc + "  \n");
      } else {
        System.out.print (face + suitc + "  \n");
      }
      
      if ( face == 1){
        numAces += 1;
      }
      
      if (face >= 11 && face <=13){
        points = 10;
      } else if (face > 1 && face < 11){
        points = face;
      } else if (face == 1){
        points = face;
      }
    }
    
    int returnPoints = addScore(points, numAces);
    return returnPoints;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public int addScore (int points, int numAces){
    int score;
    int oldScore = points;
    
    // If there are no aces, or if score is less than 21 with aces at
    // 11 points each, then the actual score is just
    // equal to the number of points.
    
    if (numAces == 0 || oldScore <= 21) {
      score = points;
    } else {
      
      // Otherwise, we need to check what is the BEST score is,
      // and that gets a little complicated.  We set a placeholder
      // -1 for best score, and a placeholder potential score.
      // We will keep track of what the best score is, and try
      // different potential scores against it.  Whatever is
      // highest without going over 21 will win as the best score.
      
      int bestScore = -1;
      int potentialScore = points;
      
      // Loop through _number of aces_ times.  Each time, try an
      // increasing number of aces as a 1 value instead of an
      // 11 value (thus, subtract 10 * j from the total points
      // value, which assumes all Aces are equal to 11 points).
      
      for (int j = 0; j <= numAces; j++) {
        potentialScore = (points - (10 * j));
        
        // For each iteration, if the potential score is
        // better than the already-best score, but it is NOT
        // over 21 (causing us to bust), then the
        // potential score should count as our new best score.
        
        if (potentialScore > bestScore && potentialScore <= 21) {
          bestScore = potentialScore;
        }
      }
      
      // We could have busted even when all of our aces were set
      // to one point.  In this case, we might never have gotten a
      // valid "best" score.  But our best potential score is the closest
      // to a best score we have, so we will replace our placeholder -1
      // best with the best potential score we got.
      
      // Otherwise, just set the score to the best score.
      
      if (bestScore == -1) {
        score = potentialScore;
      } else {
        score = bestScore;
      }
    }
    return score;
  }
}





