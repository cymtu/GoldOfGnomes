/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goldofgnomes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

/**
 *
 * @author user
 */
public class Dialog extends JDialog
{
    GoldOfGnomes Game;   
    // Класс для сохранения результата нажатия кнопки
    Answer Ans;
    // радиокнопки для выбора размеров
    JRadioButton beginner,beginner2,beginner3,expert,expert2,expert3,professional,professional2,professional3,special;
    // радиокнопки для выбора уровня AI
    JRadioButton aino, aibeginner,aiexpert,aiprofessional,aispecial;    
    // Поля для ввода спецалных значений
    JTextField lines,columns,Nuggets,Amethysts,Chrysolites,Pearls,Sapphires,Rubies;
    JTextField LevelAILow,LevelAIHigh;    
    // Кнопки
    JButton ok,cancel;
    
    Dialog(JFrame mf,String title,boolean modal,Answer Ans,GoldOfGnomes Game)
    {
        super(mf,title,modal);
        this.Ans = Ans;
        this.Game=Game;
        setResizable(false);  
        setSize(406,585);
        setLayout(null);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(new Point((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2));   
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JPanel JSize = new JPanel();
        JSize.setBorder(new TitledBorder(Windows.res.getString("SizeBoard")));
        JSize.setSize(400, 330);
        JSize.setLayout(null);
        add(JSize);
        
        
        ButtonGroup bg = new ButtonGroup();

        beginner  =new JRadioButton(Windows.res.getString("Beginner"));
        bg.add(beginner);
        beginner.setBounds(10,20,120,30);
        //beginner.setSelected(true);
        beginner.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });
        JSize.add(beginner);
        
        beginner2  =new JRadioButton(Windows.res.getString("Beginner2"));
        bg.add(beginner2);
        beginner2.setBounds(10,50,120,30);
        //beginner.setSelected(true);
        beginner2.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });
        JSize.add(beginner2);
        
        beginner3  =new JRadioButton(Windows.res.getString("Beginner3"));
        bg.add(beginner3);
        beginner3.setBounds(10,80,120,30);
        //beginner.setSelected(true);
        beginner3.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });
        JSize.add(beginner3);        
        
        
        expert  =new JRadioButton(Windows.res.getString("Expert"));
        bg.add(expert);
        expert.setBounds(10,110,120,30);
        expert.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });        
        JSize.add(expert); 
        
        expert2  =new JRadioButton(Windows.res.getString("Expert2"));
        bg.add(expert2);
        expert2.setBounds(10,140,120,30);
        expert2.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });        
        JSize.add(expert2);    
        
        expert3  =new JRadioButton(Windows.res.getString("Expert3"));
        bg.add(expert3);
        expert3.setBounds(10,170,120,30);
        expert3.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });        
        JSize.add(expert3);         
        
        professional  =new JRadioButton(Windows.res.getString("Professional"));
        bg.add(professional);
        professional.setBounds(10,200,140,30); 
        professional.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });      
        JSize.add(professional); 
        
        professional2  =new JRadioButton(Windows.res.getString("Professional2"));
        bg.add(professional2);
        professional2.setBounds(10,230,140,30); 
        professional2.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });      
        JSize.add(professional2); 
        
        professional3  =new JRadioButton(Windows.res.getString("Professional3"));
        bg.add(professional3);
        professional3.setBounds(10,260,140,30); 
        professional3.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });      
        JSize.add(professional3);         
        
        special  =new JRadioButton(Windows.res.getString("Special"));
        bg.add(special);
        special.setBounds(10,290,120,30);
        special.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChanged(evt);
            }
        });  
        JSize.add(special);         
        //---------------------
        lines = new JTextField(3);
        lines.setHorizontalAlignment(JTextField.RIGHT);
        String s=SizeBoard.MinLines + "<=" + Windows.res.getString("lines") + "<=" + SizeBoard.MaxLines;
        lines.setToolTipText(s);
        AbstractDocument d = (AbstractDocument) lines.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));
        JLabel l = new JLabel(Windows.res.getString("lines"));
        JSize.add(lines);
        JSize.add(l);
        lines.setBounds(240,20,27,20);
        l.setBounds(150,20,50,20);
        
        columns = new JTextField(3);
        columns.setHorizontalAlignment(JTextField.RIGHT);
        s=SizeBoard.MinColumns + "<=" + Windows.res.getString("columns") +" <=" + SizeBoard.MaxColumns;
        columns.setToolTipText(s);        
        d = (AbstractDocument) columns.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));        
        l = new JLabel(Windows.res.getString("columns"));
        JSize.add(columns);
        JSize.add(l);
        columns.setBounds(240,40,27,20);
        l.setBounds(150,40,70,20); 
        
        Nuggets = new JTextField(3);
        Nuggets.setHorizontalAlignment(JTextField.RIGHT);
        //int pl,pc;
        //pl=Integer.parseInt(lines.getText());pc=Integer.parseInt(columns.getText());
        s="<html>" + Windows.res.getString("Nugget") + "*" + SizeBoard.Nugget + "+" + Windows.res.getString("Amethyst") + "*" + SizeBoard.Amethyst + "+" + Windows.res.getString("Chrysolite") +"*" + SizeBoard.Chrysolite + "+" + Windows.res.getString("Pearl") + "*" + SizeBoard.Pearl + "+" + Windows.res.getString("Sapphire") + "*" + SizeBoard.Sapphire + "+" + Windows.res.getString("Ruby") + "*" + SizeBoard.Ruby +"&lt=" + SizeBoard.Max;
        //s=s + "Nuggets + Amethysts + Chrysolites + Pearls + Sapphires + Rubies<=" + Math.round(1.0*SizeBoard.MaxPrc*pl*pc);
        s=s + "<br>" + Windows.res.getString("AllJewels") + "&lt=" + SizeBoard.MaxPrc + "*" + Windows.res.getString("lines") + "*" + Windows.res.getString("columns") + "</html>";
        Nuggets.setToolTipText(s);        
        d = (AbstractDocument) Nuggets.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));         
        l = new JLabel(Windows.res.getString("Nugget"));
        JSize.add(Nuggets);
        JSize.add(l);
        Nuggets.setBounds(240,60,27,20);
        l.setBounds(150,60,70,20); 
        
        Amethysts = new JTextField(3);
        Amethysts.setHorizontalAlignment(JTextField.RIGHT);
        Amethysts.setToolTipText(s); 
        d = (AbstractDocument) Amethysts.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));         
        l = new JLabel(Windows.res.getString("Amethyst"));
        JSize.add(Amethysts);
        JSize.add(l);
        Amethysts.setBounds(240,80,27,20);
        l.setBounds(150,80,70,20); 
        
        Chrysolites = new JTextField(3);
        Chrysolites.setHorizontalAlignment(JTextField.RIGHT);
        Chrysolites.setToolTipText(s);         
        d = (AbstractDocument) Chrysolites.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));         
        l = new JLabel(Windows.res.getString("Chrysolite"));
        JSize.add(Chrysolites);
        JSize.add(l);
        Chrysolites.setBounds(240,100,27,20);
        l.setBounds(150,100,70,20); 
        
        Pearls = new JTextField(3);
        Pearls.setHorizontalAlignment(JTextField.RIGHT);
        Pearls.setToolTipText(s);       
        d = (AbstractDocument) Pearls.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));        
        l = new JLabel(Windows.res.getString("Pearl"));
        JSize.add(Pearls);
        JSize.add(l);
        Pearls.setBounds(240,120,27,20);
        l.setBounds(150,120,50,20); 
        
        Sapphires = new JTextField(3);
        Sapphires.setHorizontalAlignment(JTextField.RIGHT);
        Sapphires.setToolTipText(s);       
        d = (AbstractDocument) Sapphires.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));         
        l = new JLabel(Windows.res.getString("Sapphire"));
        JSize.add(Sapphires);
        JSize.add(l);
        Sapphires.setBounds(240,140,27,20);
        l.setBounds(150,140,60,20);   
        
        Rubies = new JTextField(3);
        Rubies.setHorizontalAlignment(JTextField.RIGHT); 
        Rubies.setToolTipText(s);       
        d = (AbstractDocument) Rubies.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));        
        l = new JLabel(Windows.res.getString("Ruby"));
        JSize.add(Rubies);
        JSize.add(l);
        Rubies.setBounds(240,160,27,20);
        l.setBounds(150,160,50,20);         
        //--------------------
        JPanel JAI = new JPanel();
        JAI.setBorder(new TitledBorder(Windows.res.getString("LevelAI")));
        JAI.setSize(400, 100);
        JAI.setBounds(0, 330,400,180);
        JAI.setLayout(null);
        add(JAI);   
        
        LevelAILow = new JTextField(3);
        LevelAILow.setHorizontalAlignment(JTextField.RIGHT);
        s=AILevel.Min +" <= " + Windows.res.getString("LevelAILow") + " <= " + Windows.res.getString("LevelAIHigh");
        LevelAILow.setToolTipText(s);  
        d = (AbstractDocument) LevelAILow.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));         
        l = new JLabel(Windows.res.getString("LevelAILow"));
        JAI.add(LevelAILow);
        JAI.add(l);
        LevelAILow.setBounds(270,20,27,20);
        l.setBounds(150,20,130,20);
        
        LevelAIHigh = new JTextField(3);
        LevelAIHigh.setHorizontalAlignment(JTextField.RIGHT);
        s=Windows.res.getString("LevelAILow") + " <= " + Windows.res.getString("LevelAIHigh") + " <= " + AILevel.Max;
        LevelAIHigh.setToolTipText(s); 
        d = (AbstractDocument) LevelAIHigh.getDocument();
        d.setDocumentFilter(new TextFieldFilter(4));         
        l = new JLabel(Windows.res.getString("LevelAIHigh"));
        JAI.add(LevelAIHigh);
        JAI.add(l);
        LevelAIHigh.setBounds(270,40,27,20);
        l.setBounds(150,40,130,20); 
        bg = new ButtonGroup();
  
        aino  =new JRadioButton(Windows.res.getString("NoAI"));
        bg.add(aino);
        aino.setBounds(10,20,120,30);
        //aino.setSelected(true);
        aino.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChangedAI(evt);
            }
        });
        JAI.add(aino); 
        
        aibeginner  =new JRadioButton(Windows.res.getString("Beginner"));
        bg.add(aibeginner);
        aibeginner.setBounds(10,50,120,30);
        aibeginner.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChangedAI(evt);
            }
        });
        JAI.add(aibeginner);
        
        aiexpert  =new JRadioButton(Windows.res.getString("Expert"));
        bg.add(aiexpert);
        aiexpert.setBounds(10,80,120,30); 
        aiexpert.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChangedAI(evt);
            }
        });      
        JAI.add(aiexpert); 
        
        aiprofessional  =new JRadioButton(Windows.res.getString("Professional"));
        bg.add(aiprofessional);
        aiprofessional.setBounds(10,110,120,30);  
        aiprofessional.addActionListener(new ActionListener()        
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChangedAI(evt);
            }
        });        
        JAI.add(aiprofessional); 
        
        aispecial  =new JRadioButton(Windows.res.getString("Special"));
        bg.add(aispecial);
        aispecial.setBounds(10,140,120,30);  
        aispecial.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                itemStateChangedAI(evt);
            }
        });
        JAI.add(aispecial);            
        
        ok = new JButton(Windows.res.getString("Ok"));
        ok.setBounds(310, 520, 80, 30);
        ok.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                ButtonClick(evt);
            }
        });  
        add(ok);
        
        cancel = new JButton(Windows.res.getString("Cancel"));
        cancel.setBounds(210, 520, 80, 30);
        cancel.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                ButtonClick(evt);
            }
        });         
        add(cancel); 
        
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Beginner) beginner.doClick();
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Beginner2) beginner2.doClick();  
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Beginner3) beginner3.doClick();        
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Expert) expert.doClick();
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Expert2) expert2.doClick();
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Expert3) expert3.doClick();       
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Professional) professional.doClick();  
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Professional2) professional2.doClick();
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Professional3) professional3.doClick();          
        if(Game.GetTypeSize()==GoldOfGnomes.TypeSize.Special) special.doClick();         
        if(Game.GetTypeAI()==GoldOfGnomes.TypeAI.NoAI) aino.doClick();  
        if(Game.GetTypeAI()==GoldOfGnomes.TypeAI.Beginner) aibeginner.doClick();    
        if(Game.GetTypeAI()==GoldOfGnomes.TypeAI.Expert) aiexpert.doClick();  
        if(Game.GetTypeAI()==GoldOfGnomes.TypeAI.Professional) aiprofessional.doClick();
        if(Game.GetTypeAI()==GoldOfGnomes.TypeAI.Special) aispecial.doClick();          
        setVisible(true);
    }

    void itemStateChanged(ActionEvent e)
    {
         GoldOfGnomes.TypeSize S = Game.GetTypeSize();
         
  	 if (e.getSource() == beginner)
         {
            dSetEnabled(false,false); 
            S=GoldOfGnomes.TypeSize.Beginner;
	 }   
  	 if (e.getSource() == beginner2)
         {
            dSetEnabled(false,false); 
            S=GoldOfGnomes.TypeSize.Beginner2;
	 } 
   	 if (e.getSource() == beginner3)
         {
            dSetEnabled(false,false); 
            S=GoldOfGnomes.TypeSize.Beginner3;
	 }         
  	 if (e.getSource() == expert)
         {
            dSetEnabled(false,false); 
            S=GoldOfGnomes.TypeSize.Expert;            
	 } 
  	 if (e.getSource() == expert2)
         {
            dSetEnabled(false,false); 
            S=GoldOfGnomes.TypeSize.Expert2;            
	 }
  	 if (e.getSource() == expert3)
         {
            dSetEnabled(false,false); 
            S=GoldOfGnomes.TypeSize.Expert3;            
	 }         
 	 if (e.getSource() == professional)
         {
            dSetEnabled(false,false); 
            S=GoldOfGnomes.TypeSize.Professional;           
	 }   
 	 if (e.getSource() == professional2)
         {
            dSetEnabled(false,false); 
            S=GoldOfGnomes.TypeSize.Professional2;           
	 } 
 	 if (e.getSource() == professional3)
         {
            dSetEnabled(false,false); 
            S=GoldOfGnomes.TypeSize.Professional3;           
	 }          
	 if (e.getSource() == special)
         {
            dSetEnabled(true,false); 
            S=GoldOfGnomes.TypeSize.Special;            
	 } 
  
         SizeBoard Size = Game.GetSCurrent(S);       
         
         lines.setText(String.valueOf(Size.Getlines())); 
         columns.setText(String.valueOf(Size.Getcolumns()));  
         Nuggets.setText(String.valueOf(Size.GetCountNuggets())); 
         Amethysts.setText(String.valueOf(Size.GetCountAmethysts())); 
         Chrysolites.setText(String.valueOf(Size.GetCountChrysolites())); 
         Pearls.setText(String.valueOf(Size.GetCountPearls())); 
         Sapphires.setText(String.valueOf(Size.GetCountSapphires())); 
         Rubies.setText(String.valueOf(Size.GetCountRubies())); 
         
    }
    void itemStateChangedAI(ActionEvent e)
    {
         GoldOfGnomes.TypeAI A = Game.GetTypeAI();
          
  	 if (e.getSource() == aino)
         {
            dSetVisible(false); 
            A=GoldOfGnomes.TypeAI.NoAI;
	 }          
  	 if (e.getSource() == aibeginner)
         {
            dSetVisible(true);            
            dSetEnabled(false,true); 
            A=GoldOfGnomes.TypeAI.Beginner;            
	 }         
  	 if (e.getSource() == aiexpert)
         {
            dSetVisible(true);             
            dSetEnabled(false,true); 
            A=GoldOfGnomes.TypeAI.Expert;            
	 }         
 	 if (e.getSource() == aiprofessional)
         {
            dSetVisible(true);            
            dSetEnabled(false,true); 
            A=GoldOfGnomes.TypeAI.Professional;           
	 }        
	 if (e.getSource() == aispecial)
         {
            dSetVisible(true);            
            dSetEnabled(true,true);
            A=GoldOfGnomes.TypeAI.Special;            
	 }    

         AILevel Ai = Game.GetAICurrent(A);        

         LevelAILow.setText(String.valueOf(Ai.GetAILow())); 
         LevelAIHigh.setText(String.valueOf(Ai.GetAIHigh()));
         
    }    
    // Блокируем поля ввода
    void dSetEnabled(boolean flag,boolean what)
    {
        if(what==false)
        {
            lines.setEnabled(flag); 
            columns.setEnabled(flag); 
            Nuggets.setEnabled(flag);
            Amethysts.setEnabled(flag);
            Chrysolites.setEnabled(flag);
            Pearls.setEnabled(flag);
            Sapphires.setEnabled(flag);
            Rubies.setEnabled(flag); 
        }
        else
        {
            LevelAILow.setEnabled(flag); 
            LevelAIHigh.setEnabled(flag);            
        }
    }
   // Делаем невидимы поля 
   void dSetVisible(boolean flag)
   {
            LevelAILow.setVisible(flag); 
            LevelAIHigh.setVisible(flag);        
   }
   // Нажатие кнопки
   void ButtonClick(ActionEvent evt)
   {
       GoldOfGnomes.TypeSize S=GoldOfGnomes.TypeSize.Beginner;
       GoldOfGnomes.TypeAI A=GoldOfGnomes.TypeAI.Beginner;
       SizeBoard SIZE;
       AILevel AI;
       
       if (evt.getSource() == ok)
       {
         // Если параметры формы ведены не верно то возращаемся
         if(!showError()) return;  
         Ans.OK();
         if(beginner.isSelected()){S=GoldOfGnomes.TypeSize.Beginner;}
         if(beginner2.isSelected()){S=GoldOfGnomes.TypeSize.Beginner2;}   
         if(beginner3.isSelected()){S=GoldOfGnomes.TypeSize.Beginner3;}         
         if(expert.isSelected()){S=GoldOfGnomes.TypeSize.Expert;}  
         if(expert2.isSelected()){S=GoldOfGnomes.TypeSize.Expert2;}  
         if(expert3.isSelected()){S=GoldOfGnomes.TypeSize.Expert3;}          
         if(professional.isSelected()){S=GoldOfGnomes.TypeSize.Professional;} 
         if(professional2.isSelected()){S=GoldOfGnomes.TypeSize.Professional2;} 
         if(professional3.isSelected()){S=GoldOfGnomes.TypeSize.Professional3;}         
         if(special.isSelected()){S=GoldOfGnomes.TypeSize.Special;}           
         
         if(aino.isSelected()){A=GoldOfGnomes.TypeAI.NoAI;}  
         if(aibeginner.isSelected()){A=GoldOfGnomes.TypeAI.Beginner;} 
         if(aiexpert.isSelected()){A=GoldOfGnomes.TypeAI.Expert;}  
         if(aiprofessional.isSelected()){A=GoldOfGnomes.TypeAI.Professional;} 
         if(aispecial.isSelected()){A=GoldOfGnomes.TypeAI.Special;}          
         
         if(special.isSelected() || aispecial.isSelected())
         {
            //Game.NewGame(S,A);
            int ilines, icolumns, iCountNuggets, iCountAmethysts, iCountChrysolites, iCountPearls, iCountSapphires, iCountRubies;
            int iLevelAILow, iLevelAIHigh;            
            ilines = Integer.parseInt(lines.getText());
            icolumns = Integer.parseInt(columns.getText());
            iCountNuggets = Integer.parseInt(Nuggets.getText());
            iCountAmethysts = Integer.parseInt(Amethysts.getText());
            iCountChrysolites = Integer.parseInt(Chrysolites.getText());
            iCountPearls = Integer.parseInt(Pearls.getText());  
            iCountSapphires = Integer.parseInt(Sapphires.getText());
            iCountRubies = Integer.parseInt(Rubies.getText());            
            SIZE = new SizeBoard(ilines , icolumns, iCountNuggets, iCountAmethysts, iCountChrysolites, iCountPearls, iCountSapphires, iCountRubies);
            iLevelAILow = Integer.parseInt(LevelAILow.getText());  
            iLevelAIHigh = Integer.parseInt(LevelAIHigh.getText());              
            AI = new AILevel(iLevelAILow,iLevelAIHigh);
            Game.NewGame(S,A,SIZE,AI);
         }
         else
         {
            Game.NewGame(S,A);        
         }  
       }
       dispose();
   }
   
    private boolean showError() 
    {
        String s="";
        int ilines, icolumns, iCountNuggets, iCountAmethysts, iCountChrysolites, iCountPearls, iCountSapphires, iCountRubies;
        int iLevelAILow, iLevelAIHigh;            
        ilines = Integer.parseInt(lines.getText());
        icolumns = Integer.parseInt(columns.getText());
        iCountNuggets = Integer.parseInt(Nuggets.getText());
        iCountAmethysts = Integer.parseInt(Amethysts.getText());
        iCountChrysolites = Integer.parseInt(Chrysolites.getText());
        iCountPearls = Integer.parseInt(Pearls.getText());  
        iCountSapphires = Integer.parseInt(Sapphires.getText());
        iCountRubies = Integer.parseInt(Rubies.getText());
        iLevelAILow = Integer.parseInt(LevelAILow.getText());  
        iLevelAIHigh = Integer.parseInt(LevelAIHigh.getText());         
        
        if(ilines<SizeBoard.MinLines)
        {
           JOptionPane.showMessageDialog(null, Windows.res.getString("Error") + ": " + Windows.res.getString("lines") + " < " + SizeBoard.MinLines);
           return false;
        }
        
        if(ilines>SizeBoard.MaxLines)
        {
           JOptionPane.showMessageDialog(null, Windows.res.getString("Error") + ": " + Windows.res.getString("lines") + " >" + SizeBoard.MaxLines);
           return false;
        }  
        
        if(icolumns<SizeBoard.MinColumns)
        {
           JOptionPane.showMessageDialog(null, Windows.res.getString("Error") + ": " + Windows.res.getString("columns") + " < " + SizeBoard.MinColumns);
           return false;
        }
        
        if(icolumns>SizeBoard.MaxColumns)
        {
           JOptionPane.showMessageDialog(null, Windows.res.getString("Error") + ": " + Windows.res.getString("columns") + " >" + SizeBoard.MaxColumns);
           return false;
        }  
        
        if(!SizeBoard.RuleOne(ilines, icolumns, iCountNuggets, iCountAmethysts, iCountChrysolites, iCountPearls, iCountSapphires, iCountRubies))
        {
            s= Windows.res.getString("AllJewels") + " > " + Math.round(SizeBoard.MaxPrc*ilines*icolumns);
            JOptionPane.showMessageDialog(null, Windows.res.getString("Error") + ": " + s);            
            return false;
        }
        
        if(!SizeBoard.RuleTwo(iCountNuggets, iCountAmethysts, iCountChrysolites, iCountPearls, iCountSapphires, iCountRubies))
        {
            //s="Nuggets*" + SizeBoard.Nugget + "+Amethysts*" + SizeBoard.Amethyst + "+Chrysolites*" + SizeBoard.Chrysolite + "+Pearls*" + SizeBoard.Pearl + "+Sapphires*" + SizeBoard.Sapphire + "+ Rubies*" + SizeBoard.Ruby +"<=" + SizeBoard.Max;
            s=Windows.res.getString("Nugget") + "*" + SizeBoard.Nugget + "+" + Windows.res.getString("Amethyst") + "*" + SizeBoard.Amethyst + "+" + Windows.res.getString("Chrysolite") +"*" + SizeBoard.Chrysolite + "+" + Windows.res.getString("Pearl") + "*" + SizeBoard.Pearl + "+" + Windows.res.getString("Sapphire") + "*" + SizeBoard.Sapphire + "+" + Windows.res.getString("Ruby") + "*" + SizeBoard.Ruby +" > " + SizeBoard.Max;
            JOptionPane.showMessageDialog(null, Windows.res.getString("Error") + ": " + s);            
            return false;
        }        
        
        if(!(AILevel.Min<=iLevelAILow))
        {
           JOptionPane.showMessageDialog(null, Windows.res.getString("Error") + ": " + AILevel.Min + " > " + Windows.res.getString("LevelAILow"));
           return false;
        }
        
        if(!(iLevelAILow<=iLevelAIHigh))
        {
           JOptionPane.showMessageDialog(null, Windows.res.getString("Error") + ": " + Windows.res.getString("LevelAILow") + " > " + Windows.res.getString("LevelAIHigh"));
           return false;
        }       
        
        if(!(iLevelAIHigh<=AILevel.Max))
        {
           JOptionPane.showMessageDialog(null, Windows.res.getString("Error") + ": " + Windows.res.getString("LevelAIHigh") + " > " + AILevel.Max);
           return false;
        }          
        
        return true;
    }   
   
}
