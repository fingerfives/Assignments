import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

public class Assignement3{
  public static void main(String[] args)throws IOException{
    
    Scanner keyboard = new Scanner(System.in);
    
    System.out.println("What is the name of your file?");
    String filename = keyboard.next() + ".txt";
    File file = new File(filename);
    
    while (!file.exists()){
      
      System.out.println("What is the name of your file?");
      filename = keyboard.next() + ".txt";
      file = new File(filename);
      
    }
    
    
    //uses the scanner to read info from the file
    Scanner inFile = new Scanner(file);
    
    //constructor to get info about the questions
    Question question = new Question();
    
    //cals the method to count the total number of questions
    int numQuestions = question.questionCounter(inFile);
    inFile.close();
    
    //creates the array holding the questions
    Scanner inFile2 = new Scanner(file);
    String[][] questionArray = question.makeQuestionArray(inFile2);
    
    
    for(int questionNum = 0; questionNum < numQuestions; questionNum++){
      
      String num = questionArray[questionNum][8];
      int numAnswers = Integer.parseInt(num);
      
      System.out.println ("Question " + questionNum + ":\n" + questionArray[questionNum][0] + "\nAnswers: ");
      question.displayChoices(numAnswers, questionNum);
      
      //TRY TO CATCH THE IOMISMATCH EXCEPTION
      //MAYBE USE THE INTEGER.PARSEINT() METHOD
      System.out.println("Your answer? (enter a number): ");
      int answer = keyboard.nextInt();
      while (answer < 0 || answer > numAnswers - 1){
        
        System.out.println("Your answer? (enter a number): ");
        answer = keyboard.nextInt();
        
      }
      
      question.checkAnswer(answer, questionNum, questionArray);
    
    }
    
    System.out.println("Thank you for your time!\nHere's how you did:\n");
    
    question.displayResults(questionArray);
    
    int correct = Integer.parseInt(questionArray[0][7]);
    int wrong = numQuestions - correct;
    Double numQ = (double)numQuestions;
    Double percent = (correct / numQ) * 100;
    
    DecimalFormat f = new DecimalFormat("0.00");
    
    System.out.println("Your overall performance was:\n        Right: " + questionArray[0][7] + "\n        Wrong: " + wrong);
    System.out.println("        Pct: " + f.format(percent) + "%\n");
    
    System.out.println("Here are some cumulative statistics:\n");
    
    question.displayStats(questionArray);
    
    
    question.save(questionArray, filename);
    
    inFile.close();
    inFile2.close();
    
    System.out.println("All Done!");
    
  }
}























//The Quesiton class will hold all the information
//about the question, including a counter and a
//method to update statistics for each quesiton
class Question {
  

  int numQuestions = 0;
  int answerNum = 0;
  int numCorrect = 0;
  //ans array holds every possible answer to each question
  String[][] ans = new String [100][50];
  //stats hold # of times tried, answered correctly
  int[][] stats = new int [100][2];
  
  
  
  //This method counts the total # of questions
  //in the file.
  public int questionCounter(Scanner inFile){
    
    int questionNum = 0;
    
    loop3:
      while (inFile.hasNextLine()){
      
      String question = inFile.nextLine();
      //System.out.println(question);
      ++questionNum;
      
      int numAnswers = inFile.nextInt();
      //System.out.println(numAnswers);
      
      for(int x = 0; numAnswers >= x; x++){
        
        String choice = inFile.nextLine();
        //System.out.println(choice);
        
      }
      
      int correct = inFile.nextInt();
      //System.out.println(correct);
      
      int totalTries = inFile.nextInt();
      //System.out.println(totalTries);
      
      int totalRight = inFile.nextInt();
      //System.out.println(totalRight);
      
      if(inFile.hasNextLine() == false){
        
        break loop3;
        
      }
      
      String blankline = inFile.nextLine();
      //System.out.println(blankline);
      
    }
      numQuestions = questionNum;
      
      return numQuestions;
  }
  
  
  
  
  
  
  
  //Will make the question array
  public String[][] makeQuestionArray(Scanner inFile){
    
    String[][] dummyArray = new String[numQuestions][9];
    int questionNum = 0;

    //This instance of code is going to be used to fill
    //the array with the questions.
    loop3:
      while (inFile.hasNextLine()){
      
      String question = inFile.nextLine();
      
      dummyArray[questionNum][0] = question;
      
      int numAnswers = inFile.nextInt();
      
      numAnswers += 1;
      
      dummyArray[questionNum][8] = Integer.toString(numAnswers);
      
      
      for(int x = 0; numAnswers > x; x++){
        
        String choices = inFile.nextLine();
        ans[questionNum][x] = choices;
        
      }
      
      int correct = inFile.nextInt();
      
      dummyArray[questionNum][1] = ans[questionNum][correct + 1];
      
      int[][] stats = new int[numQuestions][2];
      
      int totalTries = inFile.nextInt();
      stats[questionNum][0] = totalTries;
      int totalRight = inFile.nextInt();
      stats[questionNum][1] = totalRight;
      
      if(inFile.hasNextLine() == false){
        
        break loop3;
        
      }
      
      String blankline = inFile.nextLine();
      ++questionNum;
    }
      
      return dummyArray;
  }
  
  
  
  
  
  
  
  
  public void displayChoices(int numAnswers, int questionNum){
    
    for (int x = 0; x < numAnswers; x++){
      
      switch(x){
        case 0:
          break;
        default:
          System.out.println(x + ": " + ans[questionNum][x] );
      }
      
    }
  }
  
  
  
  
  public String[][] checkAnswer(int choice, int questionNum, String[][] checkArray){
    
    
    //The method takes three inputs from the main method and
    //uses them to check to see whether or not the answer
    //selected by the user is correct. This is possible b/c
    //the [questionNum][1] in the array holds the correct answer for
    //every question and the global variable in this class ans[][]
    //holds every possibe answer for each question. The method returns the
    //array with the user's guess and the result in it(2, 3 respectively)
    
    //choice -= 1;
    String check = ans[questionNum][choice];
    checkArray[questionNum][2] = ans[questionNum][choice];
    
    boolean correct;
    
    if (check.equals(checkArray[questionNum][1])){
      
      correct = true;
      checkArray[questionNum][3] = "CORRECT! Great work!";
      
    } else {
      
      correct = false;
      checkArray[questionNum][3] = "INCORRECT! Remember the answer for next time!";
    
  }
    
    updateStats(questionNum, checkArray, correct);
    
    return checkArray;
  }
  
  
  
  
  
  
  
  
  
  private String[][] updateStats(int questionNum, String[][] checkArray, boolean correct){
    
    if (correct == true){
      
      stats[questionNum][0] += 1;
      stats[questionNum][1] += 1;
      numCorrect += 1;
      
    } else{
      
     stats[questionNum][0] += 1;
      
    }
    
    int totalTried = stats[questionNum][0];
    int totalCorrect = stats[questionNum][1];
    double percentage = (totalCorrect / totalTried) * 100;
    
    checkArray[questionNum][4] = Integer.toString(totalTried);
    checkArray[questionNum][5] = Integer.toString(totalCorrect);
    checkArray[questionNum][6] = Double.toString(percentage);
    checkArray[0][7] = Integer.toString(numCorrect);
    
    return checkArray;
   
    
  }
  
  
  
  
  public void displayResults(String[][] array){
    
    for (int x = 0; x < numQuestions; x++){
      
      System.out.println("Question: " + array[x][0]);
      System.out.println("Answer: " + array[x][1]);
      System.out.println("Player Guess: " + array[x][2]);
      System.out.println("        Result: " + array[x][3] + "\n");
      
    }
    
    
  }
        
 
  
  public void displayStats(String[][] array){
    
    for (int x = 0; x < numQuestions; x++){
      
      System.out.println("Question: " + array[x][0]);
      System.out.println("        Times Tried: " + array[x][4]);
      System.out.println("        Times Correct: " + array[x][5]);
      System.out.println("        Percent Correct: " + array[x][6]);
    }
  }
  
 
  
  public void save(String[][]array, String filename)throws IOException{
    
    PrintWriter outFile = new PrintWriter(filename);
    for (int x = 0; x < numQuestions; x++){
      
      int numAns = Integer.parseInt(array[x][8]);
      
      //writes question
      outFile.println(array[x][0]);
      
      //wries # of answers for the question
      outFile.println(array[x][8]);
      
      //writes possible answers for each questions
      for (int y = 0; y < numAns; y++){
        outFile.println(ans[x][y]);
      }
      
      boolean n = false;
      
      /*while(n == false){
        
        int y = 0;
        String key = ans[x][y];
        String check = (array[x][1]);
        if(key.equals(check)){
          
          n = true;
          //writes correct answer
          System.out.println(array[x][y]);
          outFile.println(array[x][y]);
          System.out.println(ans[x][y]);
          
        }
        y++;
      }*/
      
      //writes # of times question has been tried
      outFile.println(array[x][4] + " ");
      
      //writes # of times answered correctly
      outFile.println(array[x][5] + " ");
      
      System.out.println("help");
      
    }
    
    outFile.close();
  }
    
  }
