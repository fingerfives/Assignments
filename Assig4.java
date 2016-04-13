import java.util.Scanner;
import java.io.*;

public class Assig4 extends VoterIn{
  
  private static Scanner input = new Scanner(System.in);
  private static int numBallots;
  
  //ballotArray holds the id# for the ballots in the order
  //that they are written in the file
  private static String[] ballotArray = new String[500];
  
  private static File ballotFile;
  private static String ballot;
  
  public static void main(String[]args)throws IOException{
    
    ballot = args[0];
    new VoterIn();
    new makeFrame();
    writeBallot();
    
  }
  
  public static void writeBallot()throws IOException{
    
    ballotFile = new File(ballot);
    ballotFile = checkFile(ballot);
    
    Scanner inFile = new Scanner(ballotFile);
    
    int numBallots = Integer.parseInt(inFile.nextLine());
    
    while (inFile.hasNextLine()){
      
      int x = 0;
      String [] _x = new String [3];
      String info = inFile.nextLine();
      _x = info.split(":");
      String choices = _x[2];
      
      ballotArray[x] = _x[0];
      
      String [] _y = choices.split(",");
      int numchoices = _y.length;
      
      makeFrame.updateBallot(numBallots, _x, numchoices, _y );
      makeFrame.addBallot(numBallots, _x, numchoices, _y );
      x++;
    }
    
  }
  

  
  public static File checkFile(String ballot){
    
    File file = new File(ballot);
    
    while (!file.exists()){
      
      System.out.println("What is the name of the ballot file?");
      ballot = input.next() + ".txt";
      
      file = new File(ballot);
      
    }
    
    return file;
  }
  
  
  
  

  public static void saveBallot(String ballotName)throws IOException{
    
    Scanner inFile = new Scanner(ballotFile);
    
    int numBallots = Integer.parseInt(inFile.nextLine());
    
    while (inFile.hasNextLine()){
      
      int x = 0;
      String info = inFile.nextLine();
      String [] _x = info.split(":");
      String choices = _x[2];
      
      String [] _y = choices.split(",");
      int numchoices = _y.length;
      
      for(int z = 0; z < numchoices; z++){
        
        String check = _y[z];
        
        
        //checks to see which ballot contains the choice
        //which matches with what the user cast as their vote.
        //if they match the Scanner imports that ballot's txt
        //file using the id number from _x[0] and adds the vote
        //to that choice
        if(ballotName.equals(check)){
          
          int a = 0;
          
          Scanner ballotIn = new Scanner(_x[0] + ".txt");
          PrintWriter out = new PrintWriter(_x[0] + ".txt");
          
          while (ballotIn.hasNextLine()){
            
            String line = ballotIn.nextLine();
            
            String [] addVote = line.split(":");
            
            
            //addVote[0] holds the name of the candidate
            //addVote[1] holds the # of votes it received
            String check2 = addVote[0];
            
            if(ballotName.equals(check2)){
              
            int adder = Integer.parseInt(addVote[1]);
            adder += 1;
            String towrite = Integer.toString(adder);
            out.print(addVote[0] + ":" + towrite);
            out.println("\n");
            
            } else {
              
               out.print(addVote[0] + ":" + addVote[1]);
              
            }
            
          }
          
          a++;
        }
        
      }
    }

    
 }
}