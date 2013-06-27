/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goldofgnomes;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 *
 * @author a.v.barabanov
 */
public class Windows extends JFrame
{
    // Games
    GoldOfGnomes Game;
    Menu M;                  // Меню
    Panel P;                 // Панель показывающая счет
    Board B;                 // Доска
    Status S;                // Панель для пока информаций об ходе
    final private int SIZE_IMG=23;
    final private int SIZE_IMG_RED=13;   
    Color fon = new Color(214,217,223);  
    static public ResourceBundle res;
    
    Windows(String s)
    {
        super(s);
        setLayout(null);
         // Создаем игру
        Game = new GoldOfGnomes(GoldOfGnomes.TypeSize.Beginner,GoldOfGnomes.TypeAI.NoAI); 
        // Добавить элементы
        setJMenuBar(M=new Menu(this,Game));
        add(P=new Panel(fon,Game,this));
        add(B=new Board(fon,Game,this));
        add(S=new Status(fon,Game)); 
        B.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        P.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        S.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED)); 
        
        URL imgURL = getClass().getResource("/goldofgnomes/resources/gold2.png");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image im  = tk.getImage(imgURL);
        this.setIconImage(im);
        // Размер
        //setSize(400,400);
        setResizable(false); 
        getContentPane().setBackground(fon);
        // Закрываем приложение
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Обрабатываем закрытие окна
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            SaveWinner.Save(Game);          
            System.exit(0);
        }
        }); 
        // Проверяем наличие файла
        try {
            // Загружаем параметры последней игры
            SaveWinner.Load(Game);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Windows.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Windows.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChangeSize();
        // Выводим окно на экран
        setVisible(true);
    }  
    
    public static void main(String[] args)
    {
        res = ResourceBundle.getBundle("goldofgnomes.resources.local");
        JFrame window = new Windows(res.getString("App_Name"));
    }  
    // Изменяем размеры окна
    public void ChangeSize()
    {
        int width;
        int height;
        this.setSize(SIZE_IMG*(Game.GetSCurrent().Getcolumns()+1)-1,SIZE_IMG*(Game.GetSCurrent().Getlines()+7) - 5); 
        width=SIZE_IMG*Game.GetSCurrent().Getcolumns()+4;
        height=SIZE_IMG*Game.GetSCurrent().Getlines()+4;
        P.setBounds(5, 5, width, 50);
        B.setBounds(5, 60, width, height); 
        S.setBounds(5, 65 + height, width, 30);         
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(new Point((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2));
        repaint();
    }
    // Обновить панель Статус
    public void RefreshStatus()
    {
        S.SetText();
    }
    // Обновить панель отображающая счет
    public void RefreshPanel()
    {
        P.Show(P.getGraphics());
    } 
     
}
