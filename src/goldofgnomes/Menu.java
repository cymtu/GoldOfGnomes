/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goldofgnomes;

import java.awt.Desktop;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
/**
 *
 * @author a.v.barabanov
 */
public class Menu extends JMenuBar
{      
    
    GoldOfGnomes Game;
    Windows W;
    private JMenu MenuGame;
    private JMenuItem MenuNewGame;
    private JMenuItem MenuStatistics;
    private JMenuItem MenuParameters;   
    private JMenuItem MenuExit;    
    
    private JMenu MenuHelpMain;
    private JMenuItem MenuAbout;
    private JMenuItem MenuHelp;
    
    private JFrame window;
    
    Menu(JFrame window,GoldOfGnomes Game)
    {
        super();
        this.window=window;
        this.Game=Game;
        this.W = (Windows) window;
        // Главное меню
        MenuGame = new JMenu(Windows.res.getString("Game"));
        add(MenuGame);
        // Новая игра
        MenuNewGame = new JMenuItem(Windows.res.getString("NewGame"));
        MenuNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0)); 
        MenuNewGame.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                NewGame();
            }
        });         
        MenuGame.add(MenuNewGame);
        // Полоса
        MenuGame.add(new JPopupMenu.Separator());
        // Статистика
        MenuStatistics = new JMenuItem(Windows.res.getString("Statistics"));
        MenuStatistics.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));  
        MenuStatistics.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                ShowWinner();
            }
        });          
        MenuGame.add(MenuStatistics);
        // Параметры
        MenuParameters = new JMenuItem(Windows.res.getString("Parameters"));
        MenuParameters.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));  
        MenuParameters.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                ShowDialog();
            }
        });          
        MenuGame.add(MenuParameters); 
        // Полоса
        MenuGame.add(new JPopupMenu.Separator()); 
        // Выход
        MenuExit = new JMenuItem(Windows.res.getString("Exit"));
        MenuExit.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                Save();  
                System.exit(0);
            }
        });        
        MenuGame.add(MenuExit); 
        
        // Второе меню
        //--------------------------
        
        MenuHelpMain = new JMenu(Windows.res.getString("Help"));
        add(MenuHelpMain);
        // Помощь
        MenuHelp = new JMenuItem(Windows.res.getString("Help"));
        MenuHelp.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                try {
                    Desktop.getDesktop().open(new File(Windows.res.getString("GoldOfGnomesChm")));
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        MenuHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));        
        MenuHelpMain.add(MenuHelp); 
        // Полоса
        MenuHelpMain.add(new JPopupMenu.Separator());       
        // О Программе
        MenuAbout = new JMenuItem(Windows.res.getString("About"));
        MenuAbout.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
                ShowAbout();
            }
        });        
        MenuHelpMain.add(MenuAbout);          
    }
    // Показать окно выбора параметров игры
    void ShowDialog()
    { 
       Answer Ans = new Answer();     
       Dialog D = new Dialog(window,Windows.res.getString("Parameters"),true, Ans,Game);
       if(Ans.IsOk()==true)
       {// новая игра если нажата кнопка ОК
           W.ChangeSize();
       }
    }
    // показать диалог о программе
    void ShowAbout()
    {      
       About D = new About(window,Windows.res.getString("About"),true);
    }  
    // показать победителей
    void ShowWinner()
    {      
       ShowWinner D = new ShowWinner(window,Windows.res.getString("Statistics"),true);
    }     
    // Новая игра
    void NewGame()
    {
        Game.NewGame();
        W.repaint();
    }
    // Сохраняем параметры игры
    void Save()
    {
       SaveWinner.Save(Game);  
    }

}
