import java.util.Scanner;
import java.io.*;
import javax.swing.*;

public class VoterIn{
  
  private static Scanner input = new Scanner(System.in);
  private static int voterNum = 0;
  public static File voterFile = new File ("voters.txt");
  
  //[voterNum][0] contains their ID#
  //[voterNum][1] contians their name
  //[voterNum][2] contains their voting status
  private static String [][] voters = new String[200][4];
  
  public static void main(String[]args)throws IOException{
    
    File voterFile = new File("voters.txt");
    voterFile = checkFile("voters.txt");
    
    
    Scanner inFile1 = new Scanner(voterFile);
    
    while (inFile1.hasNextLine()){
      
      String info = inFile1.nextLine();
      String [] _x = info.split(":");
      
      voters[voterNum][0] = _x[0];
      voters[voterNum][1] = _x[1];
      voters[voterNum][2] = _x[2];
      JOptionPane.showMessageDialog(null, voterNum + "!");
      voterNum++;
    }
    
    String name = login();
    //String voterName = login(voterNum, voters);
    //voters = checkVotingStatus(); 
    System.out.println(name);
    System.out.println("SAVING....");
    
    PrintWriter outFile = new PrintWriter(voterFile);
    
    for (int x = 0; x < voterNum; x++){
      
      outFile.print(voters[x][0] + ":");
      outFile.print(voters[x][1] + ":");
      outFile.print(voters[x][2]);
      outFile.println("");
      
      
    }
    
    inFile1.close();
    outFile.close();
  }
  
  
  
  
  //checking for the user's information in the
  //txt file. A message is displayed if the user
  //has already voted
  public static String login() throws IOException{
    
    String username = "Fi";
    
    Scanner inFile1 = new Scanner(voterFile);
    
    while (inFile1.hasNextLine()){
      
      String info = inFile1.nextLine();
      String [] _x = info.split(":");
      
      voters[voterNum][0] = _x[0];
      voters[voterNum][1] = _x[1];
      voters[voterNum][2] = _x[2];
      
      voterNum++;
    }
    
    String id = JOptionPane.showInputDialog(null, "Please enter your voter id");
    
    boolean voted = checkVotingStatus(id);
    
    if (voted == true){
      
      JOptionPane.showMessageDialog(null, "You have already voted");
      
    } else {
      
      for(int x = 0; x < voterNum; x++){
        
        String check = voters[x][0];
        
        
        if (id.equals(check)){
          
          username = voters[x][1];

        }
      }
    }
    
    
    return username;
  }
  
  
  
  public static File checkFile(String name){
    
    File file = new File(name);
    
    while (!file.exists()){
      
      System.out.println("What is the name of the ballot file?");
      name = input.next() + ".txt";
      
      file = new File(name);
      
    }
    
    return file;
  }
  
  
  
  public static boolean checkVotingStatus(String id){
    
    boolean status = false;
    
    for(int x = 0; x < voterNum; x++){
      
      String check = voters[x][0];
      
      if(id.equals(check)){
        
        boolean voted = Boolean.parseBoolean(voters[x][2]);
        
        if(voted == true){
          status = true;
        }
        voters[x][2] = "true";
      }
    }
    
    return status;
  }
  
  
  
}