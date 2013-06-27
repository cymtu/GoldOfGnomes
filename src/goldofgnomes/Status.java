/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goldofgnomes;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author a.v.barabanov
 */
public class Status extends JPanel
{
    GoldOfGnomes Game;
    JLabel Course;
    JLabel Pole;
    
    Status(Color fon,GoldOfGnomes Game)
    {
      super();
      setBackground(fon);
      this.Game=Game;
      this.setLayout(null);
      Course = new JLabel(Windows.res.getString("Course") + " = 0");//new JLabel("Course = 0");
      //Course.setOpaque(true);
      //Course.setBackground(Color.red);
      Course.setBounds(2,2,90,26);
      add(Course);
      
      Pole = new JLabel();
      //Pole.setOpaque(true);
      //Pole.setBackground(Color.blue);
      Pole.setBounds(100,2,130,26);
      add(Pole);      
    }
    
    @Override protected void paintComponent(Graphics g) 
    {
      super.paintComponent(g);
      SetText(); 
    }
    // Отображаем информацию об ходе и об окончаний игры
    public void SetText()
    {
      if(!Game.EndGame())
       Course.setText(Windows.res.getString("Course") + " = " + String.valueOf(Game.GetCourse())); 
      else
       Course.setText(Windows.res.getString("GameOver"));          
    }
    // Информация об ячейках
    public void SetTextPole(String str)
    {
      Pole.setText(str);  
    }
}
