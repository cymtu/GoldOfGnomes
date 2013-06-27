/*
 * Моя кнопка
 * 
 */
package goldofgnomes;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import javax.swing.*;

/**
 *
 * @author user
 */
    class MyButton extends JPanel implements MouseListener,MouseMotionListener
    {
        GoldOfGnomes Game;
        Windows Parent;
        private Image[] img_new = new Image[6];
        
        MyButton(GoldOfGnomes Game, Windows Parent)
        {
           this.Game=Game; 
           this.Parent=Parent;
           for(int i=0;i<6;i++)
           {
               URL imgURL = getClass().getResource("/goldofgnomes/resources/New" + i + ".png");
               Toolkit tk = Toolkit.getDefaultToolkit();
               img_new[i] = tk.getImage(imgURL);
           }
           this.addMouseListener(this);
           this.addMouseMotionListener(this);
           this.setToolTipText(Windows.res.getString("NewGame"));
        }
        // Прорисовываем кнопку
        @Override protected void paintComponent(Graphics g) 
        {
          boolean f=Game.EndGame();  
          super.paintComponent(g);  
          if(!f)
           g.drawImage(img_new[0], 0, 0, this);
          else
           g.drawImage(img_new[3], 0, 0, this);              
        } 
        // Кликаем по кнопке (Новая игра)
        @Override public void mouseClicked(MouseEvent e) 
        {
          boolean f=Game.EndGame();    
          Graphics g=this.getGraphics();
          if(!f)
           g.drawImage(img_new[0], 0, 0, this);
          else
           g.drawImage(img_new[3], 0, 0, this);
          
           Game.NewGame();
           Parent.repaint();
        }
        // Нажали кнопку
        @Override public void mousePressed(MouseEvent e) 
        {
          boolean f=Game.EndGame();           
          Graphics g=this.getGraphics();
          if(!f)
           g.drawImage(img_new[2], 0, 0, this);
          else
           g.drawImage(img_new[5], 0, 0, this);            
        }

        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        // Вышли за пределы кнопку
        @Override public void mouseExited(MouseEvent e) 
        {
          boolean f=Game.EndGame();    
          Graphics g=this.getGraphics();
          if(!f)
           g.drawImage(img_new[0], 0, 0, this);
          else
           g.drawImage(img_new[3], 0, 0, this);  
        }

        @Override public void mouseDragged(MouseEvent e) {}
        // Двигаемся по кнопке
        @Override public void mouseMoved(MouseEvent e) 
        {
          Color r;
          Color fon = new Color(214,217,223); // Задний фон картинки
          boolean f=Game.EndGame();         
          int w = this.getWidth();
          int h = this.getHeight();
          // Для боллее выразительного отображения кнопки 
          // Удерживаем кнопку от поднятия при движений курсора по заднему фону
          BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
          Graphics2D g = bi.createGraphics();
          this.paint(g);
          int temp = bi.getRGB(e.getX(), e.getY());
          r = new Color(temp);
          Graphics g2 = this.getGraphics();
          if(fon.getBlue()!=r.getBlue() && fon.getRed()!=r.getRed() && fon.getGreen()!=r.getGreen())
          {  
            if(!f)
             g2.drawImage(img_new[1], 0, 0, this);
            else
             g2.drawImage(img_new[4], 0, 0, this);               
          } 
          else
          {
            if(!f)
             g2.drawImage(img_new[0], 0, 0, this);
            else
             g2.drawImage(img_new[3], 0, 0, this);                 
          }     
        }
        
    }
