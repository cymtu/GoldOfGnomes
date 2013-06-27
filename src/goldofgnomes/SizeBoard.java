/*
 * Данный класс содержить информацию об размерах и параметрах доски
 * А также правила по ограничению для размеров игры
 */
package goldofgnomes;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author user
 */
   public class SizeBoard implements Serializable
   {
      // Ценность каждой драгоценности(jewel) 
      public static final int Nugget = 1;
      public static final int Amethyst = 2;
      public static final int Chrysolite = 4;
      public static final int Pearl = 8;
      public static final int Sapphire = 16;
      public static final int Ruby = 32; 
      // Постоянные параметры игры
      public static final int Max=999;                // Общая сумма всех jewels <= 999
      public static final double MaxPrc=0.40;         // Кол-во всех jewels <= Round(MaxPrc*lines*columns)
      public static final int MinLines = 9;           // Минимальное кол-во линий
      public static final int MaxLines = 36;          // Максимальное кол-во линий
      public static final int MinColumns = 9;         // Минимальное кол-во столбцов
      public static final int MaxColumns = 72;        // Максимальное кол-во столбцов
      // Размеры доски
      private int lines;
      private int columns;
      // Кол-во каждой  jewels
      private int CountNuggets;
      private int CountAmethysts;
      private int CountChrysolites;
      private int CountPearls;
      private int CountSapphires;
      private int CountRubies;  
     
      SizeBoard()
      {        
        Rand();     
      }
      
      SizeBoard(int lines, int columns,int CountNuggets,int CountAmethysts,int CountChrysolites,int CountPearls,int CountSapphires,int CountRubies)
      {
         int sum; 
         double Prc;
         this.lines=lines;
         this.columns=columns;
         this.CountNuggets=CountNuggets;
         this.CountAmethysts=CountAmethysts;
         this.CountChrysolites=CountChrysolites;
         this.CountPearls=CountPearls;
         this.CountSapphires=CountSapphires;
         this.CountRubies=CountRubies;  
         // Проверка
         if(this.lines<MinLines) this.lines=MinLines;
         if(this.lines>MaxLines) this.lines=MaxLines;
         if(this.columns<MinColumns) this.lines=MinColumns;
         if(this.columns>MaxColumns) this.lines=MaxColumns;        
         // Правило №1 проверяет чтоб Кол-во всех jewels <= Round(MaxPrc*lines*columns)
         if(!RuleOne(this.lines, this.columns, this.CountNuggets, this.CountAmethysts, this.CountChrysolites, this.CountPearls, this.CountSapphires, this.CountRubies))
         {
           //System.out.println("1");  
           this.Rand();             
         }
         // Правило №2 проверяет чтоб Общая сумма всех jewels <= 999
         if(!RuleTwo(this.CountNuggets, this.CountAmethysts, this.CountChrysolites, this.CountPearls, this.CountSapphires, this.CountRubies))
         {
           //System.out.println("2");               
           this.Rand();            
         }         
         
      }
      // Правило №1 true - все в порядке, false - правило не выполняеться
      public static boolean RuleOne(int lines, int columns, int CountNuggets, int CountAmethysts, int CountChrysolites, int CountPearls, int CountSapphires, int CountRubies)
      {
         int sum=0;
         sum=CountNuggets + CountAmethysts + CountChrysolites + CountPearls + CountSapphires + CountRubies;
         //System.out.println("RuleOne" + sum +" > " +MaxPrc*lines*columns);
         if(sum>MaxPrc*lines*columns)
         {
           return false;  
           
         }          
         return true;
      }      
      // Правило №2 true - все в порядке, false - правило не выполняеться
      public static boolean RuleTwo(int CountNuggets, int CountAmethysts, int CountChrysolites, int CountPearls, int CountSapphires, int CountRubies)
      {
         int sum=0;
         sum=Nugget*CountNuggets + Amethyst*CountAmethysts + Chrysolite*CountChrysolites + Pearl*CountPearls + Sapphire*CountSapphires + Ruby*CountRubies;
         if(sum>Max)
         {
           return false;           
         }          
         return true;
      }
      
      // Случано выбираем размеры доски и равномерно распределяем jewels
      // Кол-во jewels Должно быть равно Math.round(MaxPrc*this.lines*this.columns)=n;
      // CountNuggets + CountAmethysts + CountChrysolites + CountPearls + CountSapphires + CountRubies = n (1)
      // Причем сумма каждого вида jewel должны быть равны между собой для равномерного распределения, то есть
      // CountNuggets*Nugget = CountAmethysts*Amethyst = CountChrysolites*Chrysolite = CountPearls*Pearl = CountSapphires*Sapphire = CountRubies*Ruby (2)
      // Тогда все неизвестный с префиксом Count... можно выразить через неизвестную CountNuggets и тогда уравнение (1) примет следующий вид
      // CountNuggets*(1 + Nugget/Amethyst + Nugget/Chrysolite + Nugget/Pearl + Nugget/Sapphire + Nugget/Ruby) = n (1')
      // Из этого уравнения находим CountNuggets, а остальные из соотношения (2)
      // Для соблюдения правила №2 ограничиваем CountNuggets <= 160.
      private void Rand()
      {
        int count; 
        double Prc; 
        Random rand = new Random();
        this.lines = MinLines + rand.nextInt(MaxLines-MinLines);
        this.columns = MinColumns + rand.nextInt(MaxColumns-MinColumns); 
        count=(int) Math.round(MaxPrc*this.lines*this.columns);
        Prc=1. + 1.*Nugget/Amethyst + 1.*Nugget/Chrysolite + 1.*Nugget/Pearl + 1.*Nugget/Sapphire + 1.*Nugget/Ruby;
        this.CountNuggets=(int)Math.round(count/Prc);
        if(this.CountNuggets>160) this.CountNuggets=160;
        Prc=0.5;
        this.CountAmethysts=(int) Math.round(Prc*this.CountNuggets);
        this.CountChrysolites=(int) Math.round(Prc*this.CountAmethysts);
        this.CountPearls=(int) Math.round(Prc*this.CountChrysolites); 
        this.CountSapphires=(int) Math.round(Prc*this.CountPearls);
        this.CountRubies=(int) Math.round(Prc*this.CountSapphires);          
      }
                 
      public int Getlines(){return lines;}
      public int Getcolumns(){return columns;}
      public int GetCountNuggets(){return CountNuggets;}  
      public int GetCountAmethysts(){return CountAmethysts;}
      public int GetCountChrysolites(){return CountChrysolites;}
      public int GetCountPearls(){return CountPearls;} 
      public int GetCountSapphires(){return CountSapphires;}
      public int GetCountRubies(){return CountRubies;} 
      // Функция возвращает общую сумму стоимости всех ценностей на доске
      public int GetSumValueJewels()
      {
          int sum=0;
          sum = Nugget*CountNuggets + Amethyst*CountAmethysts + Chrysolite*CountChrysolites + Pearl*CountPearls + Sapphire*CountSapphires + Ruby*CountRubies;
          return sum;
      }
   }
