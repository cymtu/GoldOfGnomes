/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goldofgnomes;

import java.util.Random;
/**
 *
 * @author a.v.barabanov
 */
public class GoldOfGnomes
{
   // Стандарные размеры игры
   private SizeBoard SizeBeginner = new SizeBoard(9,9,8,4,2,1,0,0);
   private SizeBoard SizeBeginner2 = new SizeBoard(9,18,16,8,4,2,0,0);
   private SizeBoard SizeBeginner3 = new SizeBoard(14,18,24,12,6,3,0,0);
   
   private SizeBoard SizeExpert = new SizeBoard(13,13,16,8,4,2,1,0);
   private SizeBoard SizeExpert2 = new SizeBoard(13,26,32,16,8,4,2,0);  
   private SizeBoard SizeExpert3 = new SizeBoard(20,26,48,24,12,6,3,0);   
   
   private SizeBoard SizeProfessional = new SizeBoard(18,18,32,16,8,4,2,1);
   private SizeBoard SizeProfessional2 = new SizeBoard(18,36,64,32,16,8,4,2);
   private SizeBoard SizeProfessional3 = new SizeBoard(27,36,96,48,24,12,6,3); 
   
   private SizeBoard SizeCurrent;
   // Стандарные уровни ИИ
   private AILevel AINoAI = new AILevel(0,0);
   private AILevel AIBeginner = new AILevel(30,100);
   private AILevel AIExpert = new AILevel(60,100);
   private AILevel AIProfessional = new AILevel(90,100);
   // Текущие настройки игры
   private AILevel AICurrent;
   // Тип игры по размеру
   public enum TypeSize{Beginner, Beginner2, Beginner3, Expert,  Expert2,  Expert3, Professional,  Professional2,  Professional3, Special};
   // Тип AI
   public enum TypeAI{NoAI, Beginner, Expert, Professional, Special}; 
   // Тип статуса ячейки
   public enum TypeStatusCell{Open, Close, Metka, MetkaAnswer};
   // Field of Game
   // if value<0 then jewel
   // if value>=0 then number 
   private int [][] ValueCell;
   // Status cell of Field
   // 0 - cell open
   // 1 - cell close
   // 2 - metka
   // 3 - metka2
   private TypeStatusCell [][] StatusCell;
   // Type Size and AI
   private TypeSize TSize;
   private TypeAI TAI;
   // Count
   private int CountPlayer;
   private int CountComputer;
   // Course
   private int Course;
   private int min,max;
   // Constructor
   public GoldOfGnomes(TypeSize TSize, TypeAI TAI,SizeBoard SCurrent,AILevel AICurrent)
   {
     NewGame(TSize, TAI,SCurrent,AICurrent);
   }  
   // Конструктор
   public GoldOfGnomes(TypeSize Level,TypeAI LevelAI)
   {     
     NewGame(Level,LevelAI);      
   }   
   // return value of Field[line][column]
   public int GetValueCell(int line, int column)
   {
       return ValueCell[line][column];
   }
   public TypeStatusCell GetStatusCell(int line, int column)
   {
       return StatusCell[line][column];
   }   
   // return SizeCurrent
   public SizeBoard GetSCurrent()
   {
       return SizeCurrent;
   }
   public SizeBoard GetSCurrent(TypeSize S)
   {
       if(S==TypeSize.Beginner) return SizeBeginner;
       if(S==TypeSize.Beginner2) return SizeBeginner2;    
       if(S==TypeSize.Beginner3) return SizeBeginner3;     
       if(S==TypeSize.Expert) return SizeExpert;
       if(S==TypeSize.Expert2) return SizeExpert2;     
       if(S==TypeSize.Expert3) return SizeExpert3;      
       if(S==TypeSize.Professional) return SizeProfessional;
       if(S==TypeSize.Professional2) return SizeProfessional2;   
       if(S==TypeSize.Professional3) return SizeProfessional3;      
       return SizeCurrent;        
   }   
   // return columns
   public AILevel GetAICurrent()
   {
       return AICurrent;
   }
   public AILevel GetAICurrent(TypeAI A)
   {
       if(A==TypeAI.NoAI) return AINoAI;       
       if(A==TypeAI.Beginner) return AIBeginner;  
       if(A==TypeAI.Expert) return AIExpert;   
       if(A==TypeAI.Professional) return AIProfessional;        
       return AICurrent;
   }  
   public TypeSize GetTypeSize()
   {
       return TSize;
   }
   public TypeAI GetTypeAI()
   {
       return TAI;
   }
   // Ход игрока
   public void Course(int line, int column, String button)
   {
       // Если ячейка уже открыта, то пытаемся пометить ячейки и уходим
       if(StatusCell[line-1][column-1]==TypeStatusCell.Open) 
       {
           //MarkAllEmpty(line, column);
           return;
       }
       // Ход
       if("left".equals(button))
       {
           Course++;
           StatusCell[line-1][column-1]=TypeStatusCell.Open;
           if(ValueCell[line-1][column-1]<0)
           {
              CountPlayer+=Math.abs(ValueCell[line-1][column-1]);
              MinusValueJewel(line-1,column-1);
           }else{
    	   if(ValueCell[line-1][column-1]==0) MarkAllEmpty(line-1, column-1);
           }
       }
       else // Ставим или Убираем Метку в зависимости от состояния ячейки
       {
           if(StatusCell[line-1][column-1]==TypeStatusCell.Close) {StatusCell[line-1][column-1]=TypeStatusCell.Metka;return;}
           if(StatusCell[line-1][column-1]==TypeStatusCell.Metka) {StatusCell[line-1][column-1]=TypeStatusCell.MetkaAnswer;return;}    
           if(StatusCell[line-1][column-1]==TypeStatusCell.MetkaAnswer) {StatusCell[line-1][column-1]=TypeStatusCell.Close;return;}            
       }
   }
   
   public void CalcMinAndMax(){
       int lines = SizeCurrent.Getlines();
       int columns = SizeCurrent.Getcolumns();
       min=1000;
       max=0;
       for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {
              if(ValueCell[l][c]>=0 && StatusCell[l][c]==TypeStatusCell.Open){
                  if(min>ValueCell[l][c])min=ValueCell[l][c];
                  if(max<ValueCell[l][c])max=ValueCell[l][c];
              }
          }
   }
   
   public int getMin(){
       return min;
   }
   
   public int getMax(){
       return max;
   }
   
   private void MinusValueJewel(int l, int c){
       int min=0;
       int lines = SizeCurrent.Getlines();
       int columns = SizeCurrent.Getcolumns();        
       //1
       for(int i=0;i<columns;i++) {
    	   if(ValueCell[l][i]>0){ValueCell[l][i] = ValueCell[l][i] + ValueCell[l][c];}
    	   if(ValueCell[l][i]==0 && StatusCell[l][i]==TypeStatusCell.Open){ MarkAllEmpty(l, i);}
       }
       //2
       for(int i=0;i<lines;i++) {
    	   if(ValueCell[i][c]>0){ValueCell[i][c] = ValueCell[i][c] + ValueCell[l][c];}
    	   if(ValueCell[i][c]==0  && StatusCell[i][c]==TypeStatusCell.Open){MarkAllEmpty(i, c);}
       }
       //3
       min=Math.min(l, c);
       for(int i=0; (l- min + i)<lines && (c - min + i)<columns;i++) {
    	   if(ValueCell[l - min + i][c - min + i]>0){ValueCell[l - min + i][c - min + i] = ValueCell[l - min + i][c - min + i]  + ValueCell[l][c];}
    	   if(ValueCell[l - min + i][c - min + i]==0  && StatusCell[l - min + i][c - min + i]==TypeStatusCell.Open){MarkAllEmpty(l - min + i, c - min + i);}	   
       }
       //4
       min=Math.min(lines-l-1, c);
       for(int i=0; (l + min - i)>=0 && (c - min + i)<columns;i++) {
    	   if(ValueCell[l + min - i][c - min + i]>0){ValueCell[l + min - i][c - min + i] = ValueCell[l + min - i][c - min + i]  + ValueCell[l][c];}
    	   if(ValueCell[l + min - i][c - min + i]==0   && StatusCell[l + min - i][c - min + i]==TypeStatusCell.Open){MarkAllEmpty(l + min - i, c - min + i);}   	   
       }
       
   }
   // Get CountPlayer
   public int GetCountPlayer()
   {
       return CountPlayer;
   }
   // Get CountPlayer
   public int GetCountComputer()
   {
       return CountComputer;
   } 
   // Кол-во сделанных ходов
   public int GetCourse()
   {
       return Course;
   }
   // New game
   final public void NewGame()
   {
      int lines = SizeCurrent.Getlines();
      int columns = SizeCurrent.Getcolumns();
      ValueCell = new int [lines][columns];
      StatusCell = new TypeStatusCell [lines][columns]; 
      CountPlayer=0;
      CountComputer=0;
      Course=0;
      
      for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {
              StatusCell[l][c]=TypeStatusCell.Close; // cell close
              ValueCell[l][c]=0;
              //System.out.println("l=" +l + " c=" + c);
          }
          // Растоновка драгоценностей
          RandomCollocation(SizeCurrent.GetCountNuggets(),SizeBoard.Nugget);
          RandomCollocation(SizeCurrent.GetCountAmethysts(),SizeBoard.Amethyst);
          RandomCollocation(SizeCurrent.GetCountChrysolites(),SizeBoard.Chrysolite);
          RandomCollocation(SizeCurrent.GetCountPearls(),SizeBoard.Pearl);
          RandomCollocation(SizeCurrent.GetCountSapphires(),SizeBoard.Sapphire);          
          RandomCollocation(SizeCurrent.GetCountRubies(),SizeBoard.Ruby);  
          // Расчитываем значение ячеек
          Calculate();
   }
   // Новая игра с параметрами пользователя 
   public void NewGame(TypeSize TSize, TypeAI TAI,SizeBoard SCurrent,AILevel AICurrent)
   {
       this.TSize=TSize;
       this.TAI=TAI;
       this.SizeCurrent=SCurrent;
       this.AICurrent=AICurrent;
       NewGame();
   }
   // Новая игра со стандартными значениями
   public void NewGame(TypeSize Level,TypeAI LevelAI)
   {
     if(Level==TypeSize.Beginner){
       this.TSize=TypeSize.Beginner;      
       this.SizeCurrent=SizeBeginner;        
     }    
     if(Level==TypeSize.Beginner2){
       this.TSize=TypeSize.Beginner2;      
       this.SizeCurrent=SizeBeginner2;        
     }   
     if(Level==TypeSize.Beginner3){
       this.TSize=TypeSize.Beginner3;      
       this.SizeCurrent=SizeBeginner3;        
     }       
     if(Level==TypeSize.Expert){
       this.TSize=TypeSize.Expert;          
       this.SizeCurrent=SizeExpert;   
     }   
     if(Level==TypeSize.Expert2){
       this.TSize=TypeSize.Expert2;          
       this.SizeCurrent=SizeExpert2;   
     }   
     if(Level==TypeSize.Expert3){
       this.TSize=TypeSize.Expert3;          
       this.SizeCurrent=SizeExpert3;   
     }       
     if(Level==TypeSize.Professional){
       this.TSize=TypeSize.Professional;          
       this.SizeCurrent=SizeProfessional;       
     }
     if(Level==TypeSize.Professional2){
       this.TSize=TypeSize.Professional2;          
       this.SizeCurrent=SizeProfessional2;       
     }    
     if(Level==TypeSize.Professional3){
       this.TSize=TypeSize.Professional3;          
       this.SizeCurrent=SizeProfessional3;       
     }     
     if(Level==TypeSize.Special){
       this.TSize=TypeSize.Special;          
       this.SizeCurrent= new SizeBoard();       
     }     
     
     if(LevelAI==TypeAI.NoAI){
       this.TAI=TypeAI.NoAI;  
       this.AICurrent=AINoAI;     
     }      
     if(LevelAI==TypeAI.Beginner){
       this.TAI=TypeAI.Beginner;  
       this.AICurrent=AIBeginner;      
     }    
     if(LevelAI==TypeAI.Expert){
       this.TAI=TypeAI.Expert;          
       this.AICurrent=AIExpert;   
     }   
     if(LevelAI==TypeAI.Professional){
       this.TAI=TypeAI.Professional;           
       this.AICurrent=AIProfessional;     
     }  
     if(LevelAI==TypeAI.Special){
       this.TAI=TypeAI.Special;           
       this.AICurrent=new AILevel();     
     }     
     NewGame(); 
   }
   // Random растановка
   private void RandomCollocation(int count, int value)
   {
       int line;
       int column;
       Random rand = new Random();
       while(count>0)
       {
           line = rand.nextInt(SizeCurrent.Getlines());
           column = rand.nextInt(SizeCurrent.Getcolumns());
           if(ValueCell[line][column]==0)
           {
              ValueCell[line][column]=-value;
              //System.out.println("count=" + count + " line=" + line + " column=" + column);
              count--;
           }
       }
   }
   // Расчет ячеек
   private void Calculate()
   {
       int lines = SizeCurrent.Getlines();
       int columns = SizeCurrent.Getcolumns();       
       int sum;
       int min=0;
       for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   sum=0;
              min=0;
              if(ValueCell[l][c]==0)
              {
                  //1
                  for(int i=0;i<columns;i++) if(ValueCell[l][i]<0){sum+=ValueCell[l][i];}
                  //2
                  for(int i=0;i<lines;i++) if(ValueCell[i][c]<0){sum+=ValueCell[i][c];}
                  //3
                  min=Math.min(l, c);
                  for(int i=0; (l- min + i)<lines && (c - min + i)<columns;i++) if(ValueCell[l - min + i][c - min + i]<0){sum+=ValueCell[l - min + i][c - min + i];}
                  //4
                  min=Math.min(lines-l-1, c);
                  for(int i=0; (l + min - i)>=0 && (c - min + i)<columns;i++) if(ValueCell[l + min - i][c - min + i]<0){sum+=ValueCell[l + min - i][c - min + i];}
                  
                  //
                  ValueCell[l][c]=-sum;
              }
          }      
   }
   // Находим сумму всех драгоценностей открытых полей которые видны из ячейки Cell(l,c)
   public int GetFind(int l, int c)
   {
       int sum=0;
       int min=0;
       int lines = SizeCurrent.Getlines();
       int columns = SizeCurrent.Getcolumns();        
       //1
       for(int i=0;i<columns;i++) if(ValueCell[l][i]<0 && StatusCell[l][i]==TypeStatusCell.Open){sum+=ValueCell[l][i];}
       //2
       for(int i=0;i<lines;i++) if(ValueCell[i][c]<0 && StatusCell[i][c]==TypeStatusCell.Open){sum+=ValueCell[i][c];}
       //3
       min=Math.min(l, c);
       for(int i=0; (l- min + i)<lines && (c - min + i)<columns;i++) if(ValueCell[l - min + i][c - min + i]<0  && StatusCell[l - min + i][c - min + i]==TypeStatusCell.Open){sum+=ValueCell[l - min + i][c - min + i];}
       //4
       min=Math.min(lines-l-1, c);
       for(int i=0; (l + min - i)>=0 && (c - min + i)<columns;i++) if(ValueCell[l + min - i][c - min + i]<0 && StatusCell[l + min - i][c - min + i]==TypeStatusCell.Open){sum+=ValueCell[l + min - i][c - min + i];}
       
       return -1*sum;
   }
   // Находим кол-во полей которые закрыты и видны из ячейки Cell(l,c)
   public int GetFindCloseCell(int l, int c)
   {
       int min=0;
       int sum=0;
       int lines = SizeCurrent.Getlines();
       int columns = SizeCurrent.Getcolumns();        
       //1
       for(int i=0;i<columns;i++) if(StatusCell[l][i]!=TypeStatusCell.Open){sum++;}
       //2
       for(int i=0;i<lines;i++) if(StatusCell[i][c]!=TypeStatusCell.Open){sum++;}
       //3
       min=Math.min(l, c);
       for(int i=0; (l- min + i)<lines && (c - min + i)<columns;i++) if(StatusCell[l - min + i][c - min + i]!=TypeStatusCell.Open){sum++;}
       //4
       min=Math.min(lines-l-1, c);
       for(int i=0; (l + min - i)>=0 && (c - min + i)<columns;i++) if(StatusCell[l + min - i][c - min + i]!=TypeStatusCell.Open){sum++;}
       
       return sum;
   }   
   // Помечаем меткой все ячеки, которые видны из Cell(l,c)
   private void MarkAllEmpty(int l, int c)
   {
      int lines = SizeCurrent.Getlines();
      int columns = SizeCurrent.Getcolumns(); 
      int min=0;
      //l=l-1;
      //c=c-1;
      if(ValueCell[l][c]==0) // Если значение совпадают, значит все драгоценности найденны для этой ячейки и можно закрытые пометить
      {
       for(int i=0;i<columns;i++) if(StatusCell[l][i]==TypeStatusCell.Close){StatusCell[l][i]=TypeStatusCell.Metka;}
       //2
       for(int i=0;i<lines;i++) if(StatusCell[i][c]==TypeStatusCell.Close){StatusCell[i][c]=TypeStatusCell.Metka;}
       //3
       min=Math.min(l, c);
       for(int i=0; (l- min + i)<lines && (c - min + i)<columns;i++) if(StatusCell[l - min + i][c - min + i]==TypeStatusCell.Close){StatusCell[l - min + i][c - min + i]=TypeStatusCell.Metka;}
       //4
       min=Math.min(lines-l-1, c);
       for(int i=0; (l + min - i)>=0 && (c - min + i)<columns;i++) if(StatusCell[l + min - i][c - min + i]==TypeStatusCell.Close){StatusCell[l + min - i][c - min + i]=TypeStatusCell.Metka;}         
      }
   }
   // Проверка условия окончания игры
   public boolean EndGame()
   {
       boolean res=false;
       int sum = SizeCurrent.GetSumValueJewels();
       if(sum==(this.GetCountComputer() + this.GetCountPlayer()))
       {
           res=true;
       }
       return res;
   }
   // Формируем информацию о ячеке
   public String GetInfoPole(int y,int x)
   {
       String str="";
       int count=0;
       int find=0;
       // Закрыто
       if(GetStatusCell(y-1,x-1)!=TypeStatusCell.Open)
       {
           str=Windows.res.getString("Close");
       }
       else
       {
          count=GetValueCell(y-1,x-1);  
          // Если число, то показываем сколько нашли/ сколько осталось найти
          if(count>=0)
          {
              //find=GetFind(y-1,x-1);
              str=Windows.res.getString("Find/Rest") + " : " + (count);
          }
          else
          {
              if(count==-1)str=Windows.res.getString("Nugget") + " " + SizeBoard.Nugget + "$";
              if(count==-2)str=Windows.res.getString("Amethyst") + " " + SizeBoard.Amethyst + "$";
              if(count==-4)str=Windows.res.getString("Chrysolite") + " " + SizeBoard.Chrysolite + "$"; 
              if(count==-8)str=Windows.res.getString("Pearl") + " " + SizeBoard.Pearl + "$";
              if(count==-16)str=Windows.res.getString("Sapphire") + " " + SizeBoard.Sapphire + "$";
              if(count==-32)str=Windows.res.getString("Ruby") + " " + SizeBoard.Ruby + "$";              
          }
       }
       return str;
   }
   // Ход компьютера 
   public void AICourse()
   {
       int lines = SizeCurrent.Getlines();
       int columns = SizeCurrent.Getcolumns(); 
       int RestJewel=SizeCurrent.GetSumValueJewels();                // Всего драгоценностей на доске
       RestJewel=RestJewel - this.CountComputer - this.CountPlayer;  // Сумма всех драгоценностей, которые ещё не нашли
       int CloseCell=0;
       double Average=0;
       int min;
       double MaxLevel,MinLevel;                                     // Мах и Min ценнсть всех закрытых ячеек на доске
       double [][] Density = new double[lines][columns];
       // Находим сначала кол-во ещё закрытых ячеек
       for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
              if(StatusCell[l][c]!=TypeStatusCell.Open)
              {
                 CloseCell++;
              }
          }
       // Находим среднее значение ценности каждой закрытой ячейки
       Average=1.0*RestJewel/CloseCell;
       MaxLevel=MinLevel=Average;   // Min=Max=Average
       // Раставляем начальное значение ценности каждой закрытой ячейки
        for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
              if(StatusCell[l][c]!=TypeStatusCell.Open)
              {
                 Density[l][c]=Average;
              }
              else
              {
                  Density[l][c]=-1;  // Открыте ячейки
              }
          }
 
        // Начинаем растановку на повышение
        for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
              if(StatusCell[l][c]==TypeStatusCell.Open && ValueCell[l][c]>0)
              {
                 Average=1.0*(ValueCell[l][c] - GetFind(l,c))/GetFindCloseCell(l,c);   // Средняя ценность закрытых ячеек для данной открытой ячеики.         
                        //1
                        for(int i=0;i<columns;i++) if(StatusCell[l][i]!=TypeStatusCell.Open && Density[l][i]<Average){Density[l][i]=Average;}
                        //2
                        for(int i=0;i<lines;i++) if(StatusCell[i][c]!=TypeStatusCell.Open && Density[i][c]<Average){Density[i][c]=Average;}
                        //3
                        min=Math.min(l, c);
                        for(int i=0; (l- min + i)<lines && (c - min + i)<columns;i++) if(StatusCell[l - min + i][c - min + i]!=TypeStatusCell.Open && Density[l - min + i][c - min + i]<Average){Density[l - min + i][c - min + i]=Average;}
                        //4
                        min=Math.min(lines-l-1, c);
                        for(int i=0; (l + min - i)>=0 && (c - min + i)<columns;i++) if(StatusCell[l + min - i][c - min + i]!=TypeStatusCell.Open && Density[l + min - i][c - min + i]<Average){Density[l + min - i][c - min + i]=Average;}  
              }
          }        
        // Начинаем растановку на уменьшение
        for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
              if(StatusCell[l][c]==TypeStatusCell.Open && ValueCell[l][c]>0)
              {
                 Average=1.0*(ValueCell[l][c] - GetFind(l,c))/GetFindCloseCell(l,c);    // Средняя ценность закрытых ячеек для данной открытой ячеики.           
                        //1
                        for(int i=0;i<columns;i++) if(StatusCell[l][i]!=TypeStatusCell.Open && Density[l][i]>Average){Density[l][i]=Average;}
                        //2
                        for(int i=0;i<lines;i++) if(StatusCell[i][c]!=TypeStatusCell.Open && Density[i][c]>Average){Density[i][c]=Average;}
                        //3
                        min=Math.min(l, c);
                        for(int i=0; (l- min + i)<lines && (c - min + i)<columns;i++) if(StatusCell[l - min + i][c - min + i]!=TypeStatusCell.Open && Density[l - min + i][c - min + i]>Average){Density[l - min + i][c - min + i]=Average;}
                        //4
                        min=Math.min(lines-l-1, c);
                        for(int i=0; (l + min - i)>=0 && (c - min + i)<columns;i++) if(StatusCell[l + min - i][c - min + i]!=TypeStatusCell.Open && Density[l + min - i][c - min + i]>Average){Density[l + min - i][c - min + i]=Average;}  
              }
          }
         // Находим Min и Max уровни ячеек
         MinLevel=1000000;MaxLevel=0;
         for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                          if(Density[l][c]>=0)
                          {
                            if(Density[l][c]>MaxLevel)MaxLevel=Density[l][c];
                            if(Density[l][c]<MinLevel)MinLevel=Density[l][c];  
                          }
          }
        // Расчитываем уровни для фильтра всех подходящи ходов
        double tempMax,tempMin;         
        tempMax=MaxLevel - (AILevel.Max - this.GetAICurrent().GetAIHigh())*(MaxLevel-MinLevel)/(AILevel.Max - AILevel.Min);
        tempMin=MinLevel + (this.GetAICurrent().GetAILow() - AILevel.Min)*(MaxLevel-MinLevel)/(AILevel.Max - AILevel.Min);
        MaxLevel=tempMax;
        MinLevel=tempMin;

        int maxl=0,maxc=0;
        int max=0;
        // Находим кол-во возможных ходов удовлетворяющих фильтру
        for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Density[l][c]>=MinLevel && Density[l][c]<=MaxLevel)
                {
                    max++;
                }
          }
        // Находим ход из возможных вариантов
        Random rand = new Random();
        if(max==0)
        { // Если вариантов ходов вообще нет (такое возможно когда фильтр слишком узкий (MaxLevel-MinLevel)<<100)
          // То находим ближайщий уровень way к MaxLevel
          double way=10000;
          for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Math.abs(MaxLevel-Density[l][c])<way)
                {
                   way=Math.abs(MaxLevel-Density[l][c]);
                }
          } 
          // Находим кол-во ячеек удовлеворяющий уровню way
          for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Math.abs(MaxLevel-Density[l][c])==way)
                {
                    max++;
                }
          }          
          // Выбираем из найденных ячеек случайно ячейку для хода
          max = rand.nextInt(max)+1;
          for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Math.abs(MaxLevel-Density[l][c])==way)
                {
                    max--;
                    if(max==0)
                    {
                        maxl=l;maxc=c;
                    }
                }
          }           
        }
        else
        {  // Если max!=0, то ищщем сразу возможный ход
           max = rand.nextInt(max)+1;
        
          // Выбираем ход
          for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Density[l][c]>=MinLevel && Density[l][c]<=MaxLevel)
                {
                    max--;
                    if(max==0)
                    {
                        maxl=l;maxc=c;
                    }
                }
          }
        }
           // Компьютер делает ход
           Course++;
           StatusCell[maxl][maxc]=TypeStatusCell.Open;
           if(ValueCell[maxl][maxc]<0)
           {  // Компьютер нашел драгоценность увеличиваем его ход
              CountComputer+=Math.abs(ValueCell[maxl][maxc]);
           }
   }
   
   // Ход компьютера Основаной на расчете среднего
   // Тесты показали что это лучшие решение< чем AICourse() отсавляем его.
   public void AICourse2()
   {
       int lines = SizeCurrent.Getlines();
       int columns = SizeCurrent.Getcolumns(); 
       int RestJewel=SizeCurrent.GetSumValueJewels();                // Всего драгоценностей на доске
       RestJewel=RestJewel - this.CountComputer - this.CountPlayer;  // Сумма всех драгоценностей, которые ещё не нашли
       int CloseCell=0;
       double Average=0;
       int min;
       double MaxLevel,MinLevel;                                     // Мах и Min ценнсть всех закрытых ячеек на доске
       double [][] Density = new double[lines][columns];
       double [][] CountDensity = new double[lines][columns];   
       double [][] tempDensity = new double[lines][columns];       
       // Находим сначала кол-во ещё закрытых ячеек
       for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
              if(StatusCell[l][c]!=TypeStatusCell.Open)
              {
                 CloseCell++;
              }
          }
       // Находим среднее значение ценности каждой закрытой ячейки
       Average=1.0*RestJewel/CloseCell;
       MaxLevel=MinLevel=Average;   // Min=Max=Average
       // Раставляем начальное значение ценности каждой закрытой ячейки
        for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {  
              CountDensity[l][c]=1;
              if(StatusCell[l][c]!=TypeStatusCell.Open)
              {
                 Density[l][c]=Average;
                 tempDensity[l][c]=Average;
              }
              else
              {
                  Density[l][c]=-1;  // Открыте ячейки
                  tempDensity[l][c]=-1;  // Открыте ячейки
              }
          }
 
        // Начинаем растановку на повышение
        for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
              if(StatusCell[l][c]==TypeStatusCell.Open && ValueCell[l][c]>=0)
              {
                 //Average=1.0*(ValueCell[l][c] - GetFind(l,c))/GetFindCloseCell(l,c);   // Средняя ценность закрытых ячеек для данной открытой ячеики. 
                  Average=1.0*(ValueCell[l][c])/GetFindCloseCell(l,c);
                        //1
                        for(int i=0;i<columns;i++) if(StatusCell[l][i]!=TypeStatusCell.Open && Density[l][i]>0)
                         {
                             tempDensity[l][i]+=Average;
                             CountDensity[l][i]++;
                             Density[l][i]=tempDensity[l][i]/CountDensity[l][i];
                             if(Average==0)Density[l][i]=0;
                         }
                        //2
                        for(int i=0;i<lines;i++) if(StatusCell[i][c]!=TypeStatusCell.Open && Density[i][c]>0)
                        {
                            tempDensity[i][c]+=Average;
                            CountDensity[i][c]++;
                            Density[i][c]=tempDensity[i][c]/CountDensity[i][c];
                            if(Average==0)Density[i][c]=0;
                        }
                        //3
                        min=Math.min(l, c);
                        for(int i=0; (l- min + i)<lines && (c - min + i)<columns;i++) if(StatusCell[l - min + i][c - min + i]!=TypeStatusCell.Open && Density[l - min + i][c - min + i]>0)
                        {
                            tempDensity[l - min + i][c - min + i]+=Average;
                            CountDensity[l - min + i][c - min + i]++;
                            Density[l - min + i][c - min + i]=tempDensity[l - min + i][c - min + i]/CountDensity[l - min + i][c - min + i];
                            if(Average==0)Density[l - min + i][c - min + i]=0;                           
                        }
                        //4
                        min=Math.min(lines-l-1, c);
                        for(int i=0; (l + min - i)>=0 && (c - min + i)<columns;i++) if(StatusCell[l + min - i][c - min + i]!=TypeStatusCell.Open && Density[l + min - i][c - min + i]>0)
                        {
                            tempDensity[l + min - i][c - min + i]+=Average;
                            CountDensity[l + min - i][c - min + i]++;
                            Density[l + min - i][c - min + i]=tempDensity[l + min - i][c - min + i]/CountDensity[l + min - i][c - min + i];
                            if(Average==0)Density[l + min - i][c - min + i]=0;                     
                        }  
              }
          }        

         // Находим Min и Max уровни ячеек
         MinLevel=1000000;MaxLevel=0;
         for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                          if(Density[l][c]>=0)
                          {
                            if(Density[l][c]>MaxLevel)MaxLevel=Density[l][c];
                            if(Density[l][c]<MinLevel)MinLevel=Density[l][c];  
                          }
          }
        // Расчитываем уровни для фильтра всех подходящи ходов
        double tempMax,tempMin;         
        tempMax=MaxLevel - (AILevel.Max - this.GetAICurrent().GetAIHigh())*(MaxLevel-MinLevel)/(AILevel.Max - AILevel.Min);
        tempMin=MinLevel + (this.GetAICurrent().GetAILow() - AILevel.Min)*(MaxLevel-MinLevel)/(AILevel.Max - AILevel.Min);
        MaxLevel=tempMax;
        MinLevel=tempMin;

        int maxl=0,maxc=0;
        int max=0;
        // Находим кол-во возможных ходов удовлетворяющих фильтру
        for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Density[l][c]>=MinLevel && Density[l][c]<=MaxLevel)
                {
                    max++;
                }
          }
        // Находим ход из возможных вариантов
        Random rand = new Random();
        if(max==0)
        { // Если вариантов ходов вообще нет (такое возможно когда фильтр слишком узкий (MaxLevel-MinLevel)<<100)
          // То находим ближайщий уровень way к MaxLevel
          double way=10000;
          for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Math.abs(MaxLevel-Density[l][c])<way)
                {
                   way=Math.abs(MaxLevel-Density[l][c]);
                }
          } 
          // Находим кол-во ячеек удовлеворяющий уровню way
          for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Math.abs(MaxLevel-Density[l][c])==way)
                {
                    max++;
                }
          }          
          // Выбираем из найденных ячеек случайно ячейку для хода
          max = rand.nextInt(max)+1;
          for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Math.abs(MaxLevel-Density[l][c])==way)
                {
                    max--;
                    if(max==0)
                    {
                        maxl=l;maxc=c;
                    }
                }
          }           
        }
        else
        {  // Если max!=0, то ищщем сразу возможный ход
           max = rand.nextInt(max)+1;
        
          // Выбираем ход
          for(int l=0;l<lines;l++)
          for(int c=0;c<columns;c++)
          {   
                if(Density[l][c]>=MinLevel && Density[l][c]<=MaxLevel)
                {
                    max--;
                    if(max==0)
                    {
                        maxl=l;maxc=c;
                    }
                }
          }
        }
           // Компьютер делает ход
           Course++;
           StatusCell[maxl][maxc]=TypeStatusCell.Open;
           if(ValueCell[maxl][maxc]<0)
           {  // Компьютер нашел драгоценность увеличиваем его ход
              CountComputer+=Math.abs(ValueCell[maxl][maxc]);
              MinusValueJewel(maxl,maxc);
           }
   }   
}
