package com.openyogaland.denis.balda;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;


class Cell extends AppCompatButton
{
  /**
   * constants
   */
  final static byte INITIAL_CELL_MASK        = 0b00000000;
  final static byte LETTER_CHOOSED_CELL_MASK = 0b00000001;
  final static byte LETTER_ENTERED_CELL_MASK = 0b00000010;
  final static byte WORD_CHOOSED_CELL_MASK   = 0b00000100;
  final static byte WORD_ENTERED_CELL_MASK   = 0b00001000;
  
  /**
   * fields
   */
  private byte   state  = INITIAL_CELL_MASK;
  private String letter = "";
  
  /**
   * getter
   * @return state - bit flags of cell state
   */
  public byte getState()
  {
    return state;
  }
  
  /**
   * setter
   * @param state - bit flags of cell state
   */
  public void setState(byte state)
  {
    this.state = state;
  }
  
  /**
   * getter
   * @return letter - the letter of the cell
   */
  public String getLetter()
  {
    return letter;
  }
  
  /**
   * setter
   * @param letter - the letter of the cell
   */
  public void setLetter(String letter)
  {
    this.letter = letter;
  }
  
  /**
   * set bit INITIAL_CELL_MASK
   */
  void setInitialCell()
  {
    state = INITIAL_CELL_MASK;
  }
  
  /**
   * set bit LETTER_CHOOSED_CELL_MASK
   */
  void setLetterChoosed()
  {
    state = (byte) (state | LETTER_CHOOSED_CELL_MASK);
  }
  
  /**
   * set bit LETTER_ENTERED_CELL_MASK
   */
  void setLetterEntered()
  {
    state = (byte) (state | LETTER_ENTERED_CELL_MASK);
  }
  
  /**
   * set bit WORD_CHOOSED_CELL_MASK
   */
  void setWordChoosed()
  {
    state = (byte) (state | WORD_CHOOSED_CELL_MASK);
  }
  
  /**
   * set bit WORD_ENTERED_CELL_MASK
   */
  void setWordEntered()
  {
    state = (byte) (state | WORD_ENTERED_CELL_MASK);
  }
  
  /**
   * check bit
   * @return state has bit INITIAL_CELL_MASK
   */
  boolean isCellInitial()
  {
    return (state == INITIAL_CELL_MASK);
  }
  
  /**
   * check bit
   * @return state has bit LETTER_CHOOSED_CELL_MASK
   */
  boolean isLetterChoosed()
  {
    return (state & LETTER_CHOOSED_CELL_MASK) > 0;
  }
  
  /**
   * check bit
   * @return state has bit LETTER_ENTERED_CELL_MASK
   */
  boolean isLetterEnterd()
  {
    return (state & LETTER_ENTERED_CELL_MASK) > 0;
  }
  
  /**
   * check bit
   * @return state has bit WORD_CHOOSED_CELL_MASK
   */
  boolean isWordChoosed()
  {
    return (state & WORD_CHOOSED_CELL_MASK) > 0;
  }
  
  /**
   * check bit
   * @return state has bit WORD_ENTERED_CELL_MASK
   */
  boolean isWordEntered()
  {
    return (state & WORD_ENTERED_CELL_MASK) > 0;
  }
  
  /**
   * constructor
   * @param context - context in which this cell occurs
   */
  public Cell(Context context)
  {
    super(context);
  }
  
  /**
   * constructor
   * @param context - context in which this cell occurs
   * @param attrs - a set of attributes from layout file
   */
  public Cell(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }
  
  /**
   * constructor
   * @param context - context in which this cell occurs
   * @param attrs - a set of attributes from layout file
   * @param defStyleAttr - a style to use by this cell
   */
  public Cell(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }
}