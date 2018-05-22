package com.openyogaland.denis.balda;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;


class Cell extends AppCompatButton
{
  /**
   * constants
   */
  final static int INITIAL_STATE  = 0;
  final static int LETTER_CHOOSED = 1;
  final static int LETTER_ENTERED = 2;
  final static int WORD_CHOOSED   = 3;
  final static int WORD_ENTERED   = 4;
  
  /**
   * fields
   */
  private Context context;
  private int     state  = INITIAL_STATE;
  private String  letter = "";
  
  /**
   * getter
   * @return state - bit flags of cell state
   */
  public int getState()
  {
    return state;
  }
  
  /**
   * setter
   * @param state - bit flags of cell state
   */
  public void setState(int state) throws IllegalArgumentException
  {
    if (INITIAL_STATE <= state && state <= WORD_ENTERED)
    {
      this.state = state;
    }
    else
    {
      throw new IllegalArgumentException(context.getString(R.string.check_range_exception_message));
    }
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
   * constructor
   * @param context - context in which this cell occurs
   */
  public Cell(Context context)
  {
    super(context);
    this.context = context;
  }
  
  /**
   * constructor
   * @param context - context in which this cell occurs
   * @param attrs - a set of attributes from layout file
   */
  public Cell(Context context, AttributeSet attrs)
  {
    super(context, attrs);
    this.context = context;
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
    this.context = context;
  }
}