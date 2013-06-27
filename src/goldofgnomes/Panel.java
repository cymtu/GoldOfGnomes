/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goldofgnomes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author a.v.barabanov
 */
public class Panel extends JPanel
{   
   GoldOfGnomes Game;
   Windows Parent;
   private MyButton newgame;
   final private int SIZE_IMG_RED=13;     
   private Image img_field;
   private Image[] img_red = new Image[11];    
   
   Panel(Color fon, GoldOfGnomes Game,Windows Parent)
   {
      super();
      setBackground(fon);  
      this.Game=Game;
      this.Parent=Parent;
      
      setLayout(null);
      
      Toolkit tk = Toolkit.getDefaultToolkit();
      URL imgURL;
      
      imgURL=getClass().getResource("/goldofgnomes/resources/Field.png");
      img_field = tk.getImage(imgURL);
      
      for(int i=0;i<11;i++)
      {
          imgURL = getClass().getResource("/goldofgnomes/resources/red" + i + ".png");
          img_red[i] = tk.getImage(imgURL);
      }  
      
      newgame = new MyButton(Game,Parent);
      //newgame.setBackground(Color.red);
      newgame.setSize(26, 26);
      add(newgame);
   }
    
  @Override protected void paintComponent(Graphics g) 
  {
      super.paintComponent(g);
      newgame.setLocation((getSize().width-newgame.getSize().width)/2,10);
      Show(g);
  } 
    // Показываем счет
    public void Show(Graphics g)
    {
      ShowCount(g,Game.GetCountPlayer(),10);
      ShowCount(g,Game.GetCountComputer(),this.getWidth()-53); 
      newgame.paintComponent(newgame.getGraphics());
    }
    // Отображает  счет
    public void ShowCount(Graphics g, int Count, int x)
    {
        g.drawImage(img_field, x, 10, this);
        int t =Count;
        int count=t;
        count=t/100;
        t=t%100;
        if(count==0)
        {
          g.drawImage(img_red[10], x + 2 + SIZE_IMG_RED*0 , 12, this);       
        }
        else
        {
          g.drawImage(img_red[count], x + 2 + SIZE_IMG_RED*0 , 12, this);         
        }
        count=t/10;
        t=t%10;   
        if(count==0 && Count<10)
        {
           g.drawImage(img_red[10], x + 2 + SIZE_IMG_RED*1 , 12, this);       
        }
        else
        {
           g.drawImage(img_red[count], x + 2 + SIZE_IMG_RED*1 , 12, this);         
        } 
        count=t;
        g.drawImage(img_red[count], x + 2 + SIZE_IMG_RED*2 , 12, this);      
    } 
  
}
