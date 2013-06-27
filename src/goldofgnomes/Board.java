/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goldofgnomes;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
/**
 *
 * @author a.v.barabanov
 */
public class Board extends JPanel implements MouseListener,MouseMotionListener
{
  GoldOfGnomes Game;
  Windows Parent;
  int SIZE_IMG;
  private Image[] img_cell = new Image[4];
  private Image[] img_jewel = new Image[6];
  private Image[] img_blue = new Image[10]; 
  
  Board(Color fon, GoldOfGnomes Game,Windows Parent)
  {
      super();
      setBackground(fon);     
      this.Game=Game;
      this.Parent=Parent;
      SIZE_IMG=23;
      
      Toolkit tk = Toolkit.getDefaultToolkit();
      URL imgURL;
      
      for(int i=0;i<4;i++)
      {
          imgURL = getClass().getResource("/goldofgnomes/resources/cell" + i + ".png");
          img_cell[i] = tk.getImage(imgURL); 
      }
      for(int i=0;i<6;i++)
      {
          imgURL = getClass().getResource("/goldofgnomes/resources/jewel" + i + ".png");
          img_jewel[i] = tk.getImage(imgURL);          
      }
      for(int i=0;i<10;i++)
      {
          imgURL = getClass().getResource("/goldofgnomes/resources/blue" + i + ".png");
          img_blue[i] = tk.getImage(imgURL);            
      }
      this.addMouseMotionListener(this);
      this.addMouseListener(this);
  }
  
  void MousePressed(MouseEvent evt)
  {
      Graphics g;
      g = this.getGraphics();
      if(Game.EndGame()) return;
      
        int x=(evt.getX()-3 - 1)/SIZE_IMG + 1;
        int y=(evt.getY()-3 - 1)/SIZE_IMG + 1;
        int maxX=Game.GetSCurrent().Getcolumns();
        int maxY=Game.GetSCurrent().Getlines();
        if(x<0) x=0;if(x>maxX)x=maxX;
        if(y<0) y=0;if(y>maxY) y=maxY;
 
        if( evt.getModifiers()==MouseEvent.BUTTON1_MASK)
        {
            if(Game.GetStatusCell(y-1, x-1)!=GoldOfGnomes.TypeStatusCell.Open)
            { // Ход игрока
              Game.Course(y, x, "left"); 
              OpenCell(g,y-1, x-1); 
              ShowCells(g); 
              if(Game.EndGame()) // проверяем окончание игры
              {
                     Parent.RefreshStatus();
                     Parent.RefreshPanel();  
                     SaveWinner.Add(Game.GetCourse(), Game.GetCountPlayer(), Game.GetCountComputer(), Game.GetTypeSize(), Game.GetTypeAI());
                     return;
              }  
              // Ход компьютера
              if(Game.GetTypeAI()!=GoldOfGnomes.TypeAI.NoAI)
              {   
                 Game.AICourse2();             // ход компьютера 
                 ShowCells(g);                // отобразить все ячейки
                 if(Game.EndGame()) // проверяем окончание игры
                 {
                     Parent.RefreshStatus();
                     Parent.RefreshPanel();                     
                     SaveWinner.Add(Game.GetCourse(), Game.GetCountPlayer(), Game.GetCountComputer(), Game.GetTypeSize(), Game.GetTypeAI());
                     return;
                 }                             
              }
              // Пересовываем окна
              Parent.RefreshStatus();
              Parent.RefreshPanel();              
            }
            else
            { // Помечаем пустые ячейки
              Game.Course(y, x, "left");                
              ShowCells(g); 
            }
        }
        if( evt.getModifiers()==MouseEvent.BUTTON3_MASK)
        {
           //System.out.println("right");
            Game.Course(y, x, "right");   
            OpenCell(g,y-1, x-1);
        } 
  }
  
  @Override protected void paintComponent(Graphics g) 
  {
      super.paintComponent(g);
      ShowCells(g);    
  } 
  
    private void ShowCells(Graphics g)
    {
      //Game.CalcMinAndMax();// определяем max и min
      for(int line=0; line<Game.GetSCurrent().Getlines();line++)
      {
       for(int column=0;column<Game.GetSCurrent().Getcolumns();column++)
       {
           OpenCell(g,line,column);
           //g.drawImage(img_jewel[0], SIZE_IMG*column + 2, SIZE_IMG*line + 2, this); 
       }
      }          
    }
    // Прорисовка одной отдельной ячейки
    private void OpenCell(Graphics g,int line, int column)
    {
          Graphics temp;   
          temp = g;//this.getGraphics();
          int img=0;
          int t;
          if(Game.GetStatusCell(line, column)==GoldOfGnomes.TypeStatusCell.Open) img=0;
          if(Game.GetStatusCell(line, column)==GoldOfGnomes.TypeStatusCell.Close) img=1;
          if(Game.GetStatusCell(line, column)==GoldOfGnomes.TypeStatusCell.Metka) img=2;
          if(Game.GetStatusCell(line, column)==GoldOfGnomes.TypeStatusCell.MetkaAnswer) img=3;        
          temp.drawImage(img_cell[img], SIZE_IMG*column + 2, SIZE_IMG*line + 2, this);           
          if(Game.GetStatusCell(line, column)==GoldOfGnomes.TypeStatusCell.Open)
          {  // show jewel
             if(Game.GetValueCell(line, column)==-1)temp.drawImage(img_jewel[0], SIZE_IMG*column + 2, SIZE_IMG*line + 2, this); 
             if(Game.GetValueCell(line, column)==-2)temp.drawImage(img_jewel[1], SIZE_IMG*column + 2, SIZE_IMG*line + 2, this);
             if(Game.GetValueCell(line, column)==-4)temp.drawImage(img_jewel[2], SIZE_IMG*column + 2, SIZE_IMG*line + 2, this); 
             if(Game.GetValueCell(line, column)==-8)temp.drawImage(img_jewel[3], SIZE_IMG*column + 2, SIZE_IMG*line + 2, this);
             if(Game.GetValueCell(line, column)==-16)temp.drawImage(img_jewel[4], SIZE_IMG*column + 2, SIZE_IMG*line + 2, this); 
             if(Game.GetValueCell(line, column)==-32)temp.drawImage(img_jewel[5],  SIZE_IMG*column + 2, SIZE_IMG*line + 2, this);
             
             // show number
             if(Game.GetValueCell(line, column)>0){
             t=Game.GetValueCell(line, column);
             Game.CalcMinAndMax();
             int del = Game.getMax() - Game.getMin();
             int blue=255;
             int green=127;
             int red=127;
             if(del==0){
                 blue=167;
                 green=0;
                 red=0;
             }else{
                 int sm = (int)(1.0*(t - Game.getMin())*200/del);
                 if(sm<=127){
                     blue=255;
                     green=127 - sm;
                     red=127 - sm;
                 }else
                 {
                     blue=255 - (sm-127);
                     if(blue>255)blue=255;
                     green=0;
                     red = 0;
                 }
             }
             temp.setColor(new Color(red,green,blue));              
             if(t>0 && t<10)
             {
                temp.fillRect(SIZE_IMG*column + 8 + 2, SIZE_IMG*line + 6 + 2, 6, 10);  
                temp.drawImage(img_blue[Game.GetValueCell(line, column)], SIZE_IMG*column + 8 + 2, SIZE_IMG*line + 6 + 2, this);
             }
             if(t>=10 && t<100)
             {
                temp.fillRect(SIZE_IMG*column + 5 + 2, SIZE_IMG*line + 6 + 2, 6, 10); 
                temp.drawImage(img_blue[Game.GetValueCell(line, column)/10], SIZE_IMG*column + 5 + 2, SIZE_IMG*line + 6 + 2, this);
                temp.fillRect(SIZE_IMG*column + 12 + 2, SIZE_IMG*line + 6 + 2, 6, 10); 
                temp.drawImage(img_blue[Game.GetValueCell(line, column)%10], SIZE_IMG*column + 12 + 2, SIZE_IMG*line + 6 + 2, this);
             }   
             if(t>=100 && t<1000)
             {
                int n;
                n=t/100;                
                t=t%100;
                temp.fillRect(SIZE_IMG*column + 1 + 2, SIZE_IMG*line + 6 + 2, 6, 10); 
                temp.drawImage(img_blue[n], SIZE_IMG*column + 1 + 2, SIZE_IMG*line + 6 + 2, this);
                n=t/10;
                t=t%10;
                temp.fillRect(SIZE_IMG*column + 8 + 2, SIZE_IMG*line + 6 + 2, 6, 10); 
                temp.drawImage(img_blue[n], SIZE_IMG*column + 8 + 2, SIZE_IMG*line + 6 + 2, this);
                n=t;
                temp.fillRect(SIZE_IMG*column + 15 + 2, SIZE_IMG*line + 6 + 2, 6, 10); 
                temp.drawImage(img_blue[n], SIZE_IMG*column + 15 + 2, SIZE_IMG*line + 6 + 2, this);                
             } 
             }
          }  
    }

    @Override public void mouseDragged(MouseEvent e) {}
    // Движение мышки по доске
    @Override public void mouseMoved(MouseEvent e) 
    {
        int x=(e.getX()-3 - 1)/SIZE_IMG + 1;
        int y=(e.getY()-3 - 1)/SIZE_IMG + 1;
        int maxX=Game.GetSCurrent().Getcolumns();
        int maxY=Game.GetSCurrent().Getlines();
        if(x<0) x=0;if(x>maxX)x=maxX;
        if(y<0) y=0;if(y>maxY) y=maxY;
        
        Parent.S.SetTextPole(Game.GetInfoPole(y, x));
    }

    @Override public void mouseClicked(MouseEvent e) {}

    @Override public void mousePressed(MouseEvent e) {MousePressed(e);}

    @Override public void mouseReleased(MouseEvent e) {}

    @Override public void mouseEntered(MouseEvent e) {}
    // Очистить информацию из поля
    @Override public void mouseExited(MouseEvent e) 
    {
        Parent.S.SetTextPole("");     
    }
}
