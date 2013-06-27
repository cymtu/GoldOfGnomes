/*
 * Класс для сохрания информаций какая кнопка была нажата 
 * в диалоговом окне.
 */
package goldofgnomes;

/**
 *
 * @author user
 */

    public class Answer
    {
        // Перечисление видов кнопок
        enum Value { OK, Cancel };
        Value result;
        // Интерфейс
        public Answer()
        {   // По умолчанию нажата кнопка Отмена
            result = Value.Cancel;
        }
        // Нажата кнопка ОК
        public void OK()
        {
            result = Value.OK;
        }
        // Нажата кнопка Cancel
        public void Cancel()
        {
            result = Value.Cancel;
        }
        // Проверка состояния
        public boolean IsOk()
        {
            if (result == Value.OK) return true;
            return false;
        }
    }
        

