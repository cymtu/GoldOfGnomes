/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goldofgnomes;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author user
 */
public class ShowWinner extends JDialog
{
      JButton ok; 
      JButton reset;
      JList Size;
      JList AI;
      JList Winners;
       
      ShowWinner(Frame mf,String title,boolean modal)
      {
        super(mf,title,modal);
        setResizable(false);  
        setSize(555,400);
        setLayout(null);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(new Point((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2));   
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        ok = new JButton(Windows.res.getString("Ok"));
        ok.setBounds(455, 330, 80, 30);
        ok.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
               dispose();
            }
        });  
        add(ok); 
        
        reset = new JButton(Windows.res.getString("Reset"));
        reset.setBounds(355, 330, 80, 30);
        reset.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
               SaveWinner.Reset();
               MouseClick();
            }
        });  
        add(reset);        
        
        Size = new JList();
        Size.setModel(new DefaultListModel());
        //for(GoldOfGnomes.TypeSize s : GoldOfGnomes.TypeSize.values())
        //{
        //    if(s!=GoldOfGnomes.TypeSize.Special)((DefaultListModel)Size.getModel()).addElement(s.toString()); 
        //}
        ((DefaultListModel)Size.getModel()).addElement(Windows.res.getString("Beginner")); 
        ((DefaultListModel)Size.getModel()).addElement(Windows.res.getString("Beginner2"));
        ((DefaultListModel)Size.getModel()).addElement(Windows.res.getString("Beginner3"));        
        ((DefaultListModel)Size.getModel()).addElement(Windows.res.getString("Expert")); 
        ((DefaultListModel)Size.getModel()).addElement(Windows.res.getString("Expert2")); 
        ((DefaultListModel)Size.getModel()).addElement(Windows.res.getString("Expert3"));        
        ((DefaultListModel)Size.getModel()).addElement(Windows.res.getString("Professional"));  
        ((DefaultListModel)Size.getModel()).addElement(Windows.res.getString("Professional2")); 
        ((DefaultListModel)Size.getModel()).addElement(Windows.res.getString("Professional3"));        
        Size.setVisibleRowCount(3);
        Size.setBounds(10, 10, 130, 195);
        Size.setBorder(new TitledBorder(Windows.res.getString("SizeBoard")));
        //Size.setBackground(Color.red);
        Size.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Size.setSelectedIndex(0);
        Size.addMouseListener(new JListMouseListener()); 
        Size.addMouseMotionListener(new JListMouseListener());
        Size.addKeyListener(new JListMouseListener());        
        add(Size);
        
        AI = new JList();
        AI.setModel(new DefaultListModel());
        //for(GoldOfGnomes.TypeAI s : GoldOfGnomes.TypeAI.values())
        //{
        //    if(s!=GoldOfGnomes.TypeAI.Special)((DefaultListModel)AI.getModel()).addElement(s.toString()); 
        //}  
        ((DefaultListModel)AI.getModel()).addElement(Windows.res.getString("NoAI"));        
        ((DefaultListModel)AI.getModel()).addElement(Windows.res.getString("Beginner")); 
        ((DefaultListModel)AI.getModel()).addElement(Windows.res.getString("Expert")); 
        ((DefaultListModel)AI.getModel()).addElement(Windows.res.getString("Professional"));  
        AI.setVisibleRowCount(3);
        AI.setBounds(10, 215, 130, 105);
        AI.setBorder(new TitledBorder(Windows.res.getString("LevelAI")));
        //Size.setBackground(Color.red);
        AI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        AI.setSelectedIndex(0);
        AI.addMouseListener(new JListMouseListener()); 
        AI.addMouseMotionListener(new JListMouseListener());
        AI.addKeyListener(new JListMouseListener());
        add(AI);
        
        //55 символов
        //String d3[] = {"NoAI", "Beginner", "Expert", "Professional", "5", "6", "35   17/15   Computer/korore", "37   25/7     Computer/Man", "1234567890123456789012345678901234567890123456789012345"};
        Winners = new JList(new DefaultListModel());                                                //"37   25/7    Computer/Man"
        Winners.setVisibleRowCount(9);
        Winners.setBounds(150, 10, 390, 310);
        Winners.setBorder(new TitledBorder(Windows.res.getString("Winners")));
        //Size.setBackground(Color.red);
        Winners.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(Winners);
        
        /*JLabel La = new JLabel("35   17/15   Computer/korore");
        La.setBounds(10, 220, 400, 10);
        add(La);
        
        La = new JLabel("37   25/7    Computer/Man");
        La.setBounds(10, 230, 400, 10);
        add(La);  */     
        
                
        setVisible(true);
      }
      
      private void MouseClick()
      {
          if(SaveWinner.listIsNull()==false) return;
          ((DefaultListModel)Winners.getModel()).removeAllElements();
          
          GoldOfGnomes.TypeAI AI=GoldOfGnomes.TypeAI.NoAI;
          GoldOfGnomes.TypeSize Size=GoldOfGnomes.TypeSize.Beginner;
          //AI=GoldOfGnomes.TypeAI.valueOf(this.AI.getSelectedValue().toString());
          //Size=GoldOfGnomes.TypeSize.valueOf(this.Size.getSelectedValue().toString());  
          if(this.AI.getSelectedIndex()==0)AI=GoldOfGnomes.TypeAI.NoAI;         
          if(this.AI.getSelectedIndex()==1)AI=GoldOfGnomes.TypeAI.Beginner;
          if(this.AI.getSelectedIndex()==2)AI=GoldOfGnomes.TypeAI.Expert;
          if(this.AI.getSelectedIndex()==3)AI=GoldOfGnomes.TypeAI.Professional;   
          
          if(this.Size.getSelectedIndex()==0)Size=GoldOfGnomes.TypeSize.Beginner;    
          if(this.Size.getSelectedIndex()==1)Size=GoldOfGnomes.TypeSize.Beginner2;   
          if(this.Size.getSelectedIndex()==2)Size=GoldOfGnomes.TypeSize.Beginner3;            
          if(this.Size.getSelectedIndex()==3)Size=GoldOfGnomes.TypeSize.Expert;  
          if(this.Size.getSelectedIndex()==4)Size=GoldOfGnomes.TypeSize.Expert2; 
          if(this.Size.getSelectedIndex()==5)Size=GoldOfGnomes.TypeSize.Expert3;            
          if(this.Size.getSelectedIndex()==6)Size=GoldOfGnomes.TypeSize.Professional;
          if(this.Size.getSelectedIndex()==7)Size=GoldOfGnomes.TypeSize.Professional2;  
          if(this.Size.getSelectedIndex()==8)Size=GoldOfGnomes.TypeSize.Professional3;            
                    
          for (SaveWinner.Winner record : SaveWinner.list) 
          {
            String s="";           
            if(AI==record.AI && Size==record.Size)
            {
              s=String.format("%4s", record.Course) + "  "  + String.format("%3s", record.CountWinner) + "/" + String.format("%-3s", record.CountLoser)  +  "   " + record.NameWinner + "/" + record.NameLoser;
              s="<html><pre>" + s +"</html></pre>";
              ((DefaultListModel)Winners.getModel()).addElement(s);   
            }
          }
      }
      
      public class JListMouseListener implements MouseListener, MouseMotionListener, KeyListener
      {
        @Override public void mouseClicked(MouseEvent e) {
            MouseClick();
        }

        @Override public void mousePressed(MouseEvent e) {
            MouseClick();
        }

        @Override public void mouseReleased(MouseEvent e) {
            MouseClick();
        }

        @Override public void mouseEntered(MouseEvent e) {
            MouseClick();
        }

        @Override public void mouseExited(MouseEvent e) {
            MouseClick();
        }

        @Override public void mouseDragged(MouseEvent e) {
            MouseClick();
        }

        @Override public void mouseMoved(MouseEvent e) {
            MouseClick();
        }

        @Override public void keyTyped(KeyEvent e) {
            MouseClick();
        }

        @Override public void keyPressed(KeyEvent e) {
            MouseClick();
        }

        @Override public void keyReleased(KeyEvent e) {
            MouseClick();
        }
      }
}
