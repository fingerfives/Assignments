import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class makeFrame extends Assig4{
  
  private static JFrame frame = new JFrame();
  private static JButton button1;
  private static JButton button2;
  private static JButton options;
  private static JPanel panela = new JPanel();
  private static JPanel panelb = new JPanel();
  private static JPanel panelc = new JPanel();
  private static JLabel label = new JLabel();
  
  
  public makeFrame(){
    
    
    main();
    
    
  }
  
  
  
  public static void main(){
    
    frame = new JFrame("Poll");
    frame.setSize(900,900);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    ButtonListener listener = new ButtonListener();
    
    panela = new JPanel();
    panelb = new JPanel();
    
    panela.setBackground(Color.WHITE);
    panelb.setBackground(Color.WHITE);
    panelc.setBackground(Color.WHITE);
    
    button1 = new JButton("Login to Vote");
    button2 = new JButton("Cast your vote!");
    
    button2.setEnabled(false);
    
    
    button1.addActionListener(listener);
    
    
    button2.addActionListener(listener);
    
    
    //frame.revalidate();
    
    
    panela.setLayout(new BoxLayout(panela, BoxLayout.LINE_AXIS));
    panela.add(button1);
    panela.add(button2);
    
    frame.add(panelb);
    
    
    frame.add(panela, BorderLayout.EAST);
    
    
    frame.setVisible(true); 
    
  }
  
  
  public static void addBallot(int numBallots, String[]name, int numChoices, String[]choices){
    
    String ballotName = "dummy";
    ballotName = name[1];
    label = new JLabel(ballotName);
    
    panelb.add(label);
    
    
    
    for (int x = 0; x < numChoices; x++){
      
      options = new JButton(choices[x]);
      
      
      options.setEnabled(false);
      panelb.add(options);
      
    }
    
    
    panelb.setLayout(new BoxLayout(panelb, BoxLayout.Y_AXIS));
    
  }
  
  
  
  public static void main(String[]args){
    new makeFrame();
    new VoterIn();
    new Assig4();
  }
  
  
  public static void updateBallot(int numBallots, String[]name, int numChoices, String[]choices){
    
    String ballotName = "dummy";
    ballotName = name[1];
    label = new JLabel(ballotName);
    ButtonListener listener = new ButtonListener();
    
    panelc.add(label);
    
    
    for (int x = 0; x < numChoices; x++){
      
      options = new JButton(choices[x]);  
      options.addActionListener(listener);
      options.setActionCommand(choices[x]);
      panelc.add(options);
      
    }
    
    panelc.setLayout(new BoxLayout(panelc, BoxLayout.Y_AXIS));
    
    frame.add(panelc);
    frame.revalidate();
    frame.setVisible(true);
  }
  
  
  
  
  
  private static class ButtonListener implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
      
      if (e.getSource() == button1) {
        
        boolean voted = true;
        
        String voter = "Frodo";
        
        try {
          
          voter = VoterIn.login();
          
        } catch (IOException except){
          
          System.out.println("Caught the exception!");
          
        }
        
        JOptionPane.showMessageDialog(null, voter + ", please cast your votes!");
        panelb.setVisible(false);
        panelc.setVisible(true);
        button1.setEnabled(false);
        button2.setEnabled(true);
        
        
      }else if (e.getSource() == button2) {
        
        int voted = JOptionPane.showConfirmDialog(null, "Confirm Vote!");
        
        if (voted == 0){
          JOptionPane.showMessageDialog(null, "Thank you for voting!");
          button1.setEnabled(true);
          button2.setEnabled(false);
          panelc.setVisible(false);
          panelb.setVisible(true);
        }
        
      } else {
        
        String select = e.getActionCommand();
        
        try{
          
        Assig4.saveBallot(select);
        
        } catch (IOException t){
          
          System.out.println("IOException caught!");
          
        }

      }
      
    }
  }
  
  
  
}