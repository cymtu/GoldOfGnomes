/*
 * Информация о программе
 * 
 */
package goldofgnomes;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author user
 */
public class About  extends JDialog
{
    JButton ok;
    private Image pc;
    JLabel name,prog,email;
    
    About(JFrame mf,String title,boolean modal)
    {
        super(mf,title,modal);
        setResizable(false);  
        setSize(300,200);
        setLayout(null);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(new Point((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2));   
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        ok = new JButton(Windows.res.getString("Ok"));
        ok.setBounds(200, 130, 80, 30);
        ok.addActionListener(new ActionListener() 
        {
            @Override public void actionPerformed(ActionEvent evt) 
            {
               dispose();
            }
        });  
        add(ok);
        
        URL imgURL = getClass().getResource("/goldofgnomes/resources/gold.png");
        Toolkit tk = Toolkit.getDefaultToolkit();
        pc = tk.getImage(imgURL);
        // Наименование и версия игры
        name =new JLabel(Windows.res.getString("App_Name") + " " +  Windows.res.getString("ver") + ". 1.0.0");
        add(name);
        name.setBounds(50, 20, 150, 20);
        // О себе
        prog =new JLabel(Windows.res.getString("Programmer"));
        add(prog);
        prog.setBounds(10, 50, 250, 20);
        // Мой E-mail
        email =new JLabel("Email: cymtu@yandex.ru");
        add(email);
        email.setBounds(10, 80, 200, 20);  
        
        setVisible(true);
    }
    
    @Override public void paint(Graphics g) 
    {
      super.paint(g);
      g.drawImage(pc, 10, 30, this);
    } 
    
}
