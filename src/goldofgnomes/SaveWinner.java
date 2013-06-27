/*
 * Сохраняем лучшие результаты игры
 * для стандартных значений
 */
package goldofgnomes;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.*;

/**
 *
 * @author user
 */
public class SaveWinner 
{
   // Класс для хранения лучших результатов
   public static class Winner implements Serializable
   {
       public GoldOfGnomes.TypeSize Size;
       public GoldOfGnomes.TypeAI AI;
       public int Course;
       public int CountWinner;
       public int CountLoser;
       public String NameWinner;
       public String NameLoser;
   }
   // Максимальное кол-во строк для каждого вида рекорда
   public static final int MaximumSave=10;
   // Список самых лучших результатов
   public static ArrayList<Winner> list;
   // Сохраняем параметры игры и данные по лучшим игрокам
   public static void Save(GoldOfGnomes Game)
   {
      	        try
	            {   FileOutputStream file=new FileOutputStream("GoldOfGnomes.file");
	                ObjectOutput out = new ObjectOutputStream(file);
	                out.writeObject(Game.GetTypeSize());
	                out.writeObject(Game.GetTypeAI());    
                        out.writeObject(Game.GetSCurrent());
                        out.writeObject(Game.GetAICurrent());
                        out.writeObject(list);
	                out.close();
	                file.close();
	            }
	        catch(IOException e)
	            {
 	            System.out.println(Windows.res.getString("GoldOfGnomesFile"));                   
                    } 
   }
   // Загружаем параметры игры и результаты по лучшим игрокам
   public static void Load(GoldOfGnomes Game) throws FileNotFoundException, IOException
   {
       GoldOfGnomes.TypeSize Size;
       GoldOfGnomes.TypeAI AI;
       SizeBoard Board;
       AILevel   LevAI;
	        try
	            {
	                ObjectInputStream in =new ObjectInputStream(new FileInputStream("GoldOfGnomes.file"));
	                //Object obj=in.readObject();
	                Size=(GoldOfGnomes.TypeSize)in.readObject();
	                AI=(GoldOfGnomes.TypeAI)in.readObject();   
                        Board=(SizeBoard)in.readObject();
                        LevAI=(AILevel)in.readObject();
                        list = (ArrayList<Winner>)in.readObject();
	                in.close();
                        if(AI == GoldOfGnomes.TypeAI.Special || Size == GoldOfGnomes.TypeSize.Special)
                        Game.NewGame(Size, AI, Board, LevAI);
                        else
                        Game.NewGame(Size,AI);  
	            }
	        catch(ClassNotFoundException e)
	        {
	            //System.out.println(e);
	            //System.out.println("It is not possible to read the file");
                    //  Если не возможно прочитать файл, то нужно его удалить вообще
	        }
   } 
   
   public static void Reset()
   {
       if(list!=null) list.clear();
   }
   
   public static boolean listIsNull()
   {
       if(list!=null)
           return true;
       else
           return false;
   }
   public static void Add(int Course, int CountPlayer, int CountComputer,GoldOfGnomes.TypeSize Size, GoldOfGnomes.TypeAI AI)
   {   
       // НЕ сохраняем значение для этого вида игры
       if(AI == GoldOfGnomes.TypeAI.Special) return;
       if(Size == GoldOfGnomes.TypeSize.Special) return;  
       
       String Computer=Windows.res.getString("Computer");
       String Player=Windows.res.getString("Player");
       // Создаем класс для хранения результатов
       Winner temp = new Winner();
       temp.Size=Size;
       temp.AI=AI;  
       temp.Course=Course;
       if(CountPlayer>=CountComputer)
       {
           temp.CountWinner=CountPlayer;
           temp.CountLoser=CountComputer;
           temp.NameLoser=Computer;
           temp.NameWinner=Player;
       }
       else
       {
           temp.CountWinner=CountComputer;
           temp.CountLoser=CountPlayer;
           temp.NameLoser=Player;
           temp.NameWinner=Computer;           
       }
       // Если список ещё не создан
       if(list==null) 
       {
           list =new ArrayList<Winner>();
       }
       // Проверка на рекорд
       int AllRecord=0;      // Всего записей данного вида
       int IndexRecord=-1;   // Позиция записи нового рекорда
       int LastRecord=-1;
       Winner record;
       boolean flag=true;
       for (int i=0;i<list.size();i++) 
       {        
          record = list.get(i); 
          if(AI==record.AI && Size==record.Size)
          {
            if(flag)
            {
              if(Course<record.Course)
              {
                 IndexRecord=i;
                 flag=false;
              }
              else if(Course==record.Course)
              {
                if(temp.CountWinner>=record.CountWinner)
                {
                  IndexRecord=i; 
                  flag=false;
                }
              }
            }
            AllRecord++; 
          }
       }
       // Сохраняем рекорд
       if( (IndexRecord>-1 || LastRecord==-1) && (temp.NameWinner==Player))
       {
           temp.NameWinner=(String)JOptionPane.showInputDialog(null, Windows.res.getString("EnterYourName"), Player);
       }
       if(IndexRecord>-1)
       {
           list.add(IndexRecord, temp);
       }
       else
       {
           list.add(temp);
       }
       AllRecord++; 
       // Удаляем лишнее
       if(AllRecord>MaximumSave)
       {
         for (int i=0;i<list.size();i++) 
         {        
           record = list.get(i); 
           if(AI==record.AI && Size==record.Size)
           {
             LastRecord=i; 
           }
         }           
         list.remove(LastRecord);      
       }
   }
}
