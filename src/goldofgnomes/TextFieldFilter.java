/*
 * Фильтр для текстовых полей расположенных в class Dialog
 * Дает вводить в поля только цифры
 */
package goldofgnomes;

import javax.swing.*;
import javax.swing.text.*;

/**
 *
 * @author user
 */
class TextFieldFilter extends DocumentFilter 
{

    private final int max;

    public TextFieldFilter(int max) {
        this.max = max;
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                             String text, AttributeSet attr) 
            throws BadLocationException {
        if (fb.getDocument().getLength() + text.length() < max)
        {
          try
          {
            Integer.parseInt(text);
            super.insertString(fb, offset, text, attr);           
          }
          catch(Exception e)
          {
            super.insertString(fb, offset, "", attr);
          }
        }
        else 
            showError();
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset,
                        int length, String text, AttributeSet attrs)
            throws BadLocationException {
        int documentLength = fb.getDocument().getLength();
        if (documentLength - length + text.length() < max)
        { 
          try
          {
            Integer.parseInt(text);
            super.replace(fb, offset, length,text, attrs);           
          }
          catch(Exception e)
          {
            super.insertString(fb, offset, "", attrs);
          }  
        }
        else 
            showError();      
    }

    private void showError() {
        //JOptionPane.showMessageDialog(null, "Too many characters entered");
    }
}
