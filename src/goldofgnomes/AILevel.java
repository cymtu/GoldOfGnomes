/*
 * Данный класс хранить информацию об интервале, который используеться 
 * для фильтраций всех возможных вариантов ходов AI
 */
package goldofgnomes;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author user
 */
   public class AILevel implements Serializable
   {
      private int AIHigh;                     // Верхний уровень
      private int AILow;                      // Нижний уровень (AILow <= AIHigh)
      public static final int Max=100;        // Максимальный верхний уровень
      public static final int Min=0;          // Минимальный нижний уровень
      // Конструктор
      AILevel(int AILow,int AIHigh)
      {
         this.AILow=AILow;
         this.AIHigh=AIHigh;
         // Проверяем на корректность веденных данных, если что то не так, то делаем корректировку
         if(this.AILow<Min) this.AILow=Min;
         if(this.AILow>Max) this.AILow=Max; 
         if(this.AIHigh<Min) this.AIHigh=Min;
         if(this.AIHigh>Max) this.AIHigh=Max; 
         if(this.AILow>this.AIHigh) this.AILow=this.AIHigh;
      }
      // Конструктор случайно сгенерированных значений
      AILevel()
      {
        Random rand = new Random();
        this.AIHigh = Max/2 + rand.nextInt(Max/2);
        this.AILow = rand.nextInt(Max/2);        
      }
      
      public int GetAIHigh(){return AIHigh;}
      public int GetAILow(){return AILow;}    
   }
