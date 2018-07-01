package com.openyogaland.denis.balda;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class FieldFragment extends Fragment implements OnClickListener
{
  public static final int    NUM_OF_ROWS            = 5;
  public static final int    NUM_OF_COLUMNS         = 5;
  
  private final Cell[][]   fieldCell = new Cell[NUM_OF_ROWS][NUM_OF_COLUMNS];
  
  private static final int    INITIAL_WORD_ROW_INDEX = 2;
  private static final String INITIAL_WORD           = "балда";
  
  private OnCellPressedListener onCellPressedListener;
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }
  
  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
      Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.field_fragment, container, false);
        
    for (int row = 0; row < NUM_OF_ROWS; row++)
    {
      for (int column = 0; column < NUM_OF_COLUMNS; column++)
      {
        String cellIndex = String.valueOf(row) + String.valueOf(column);
        
        if (getContext() != null)
        {
          String idResourceType = getString(R.string.id);
          String cellNamePrefix = getString(R.string.cell_name_prefix);
          
          int cellId = getResources().getIdentifier(cellNamePrefix + cellIndex, idResourceType,
              getContext().getPackageName());
          fieldCell[row][column] = view.findViewById(cellId);
          fieldCell[row][column].setOnClickListener(this);
          // set flags
          fieldCell[row][column].setState(Cell.INITIAL_STATE);
        }
        
        if (row == INITIAL_WORD_ROW_INDEX)
        {
          writeInitialWord(column);
        }
        
        String cellText = fieldCell[row][column].getText().toString();
        if(cellText.isEmpty())
        {
          // show saved value
          fieldCell[row][column].setText(fieldCell[row][column].getLetter());
        }
        else
        {
          // save value
          fieldCell[row][column].setLetter(cellText);
        }
      }
    }
    return view;
  }
  
  private void writeInitialWord(int column)
  {
    // TODO write initial word
    String letterToWrite = INITIAL_WORD.substring(column, column + 1);
    Cell currentCell     = fieldCell[INITIAL_WORD_ROW_INDEX][column];
    // show letter
    currentCell.setText(letterToWrite);
    // set letter
    currentCell.setLetter(letterToWrite);
    // set flags
    currentCell.setState(Cell.WORD_ENTERED);
  }
  
  @Override
  public void onClick(View view)
  {
    // if player has not selected fieldCell yet
    if(view instanceof Cell)
    {
      Cell cellPressed = (Cell) view;
      int cellPressedId = cellPressed.getId();
      // TODO выбранная клетка должна быть рядом с уже занятыми клетками
      onCellPressedListener.onCellPressed(cellPressedId);
    }
  }
  
  /**
   * Method to attach the fragment to it's context (activity)
   * @param context - context to attach the fragment
   **/
  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    
    try
    {
      onCellPressedListener = (OnCellPressedListener) context;
    }
    catch(ClassCastException e)
    {
      throw new ClassCastException(e + context.toString() + getString(R.string.on_cell_pressed_listener_warning));
    }
  }
}

interface OnCellPressedListener
{
  void onCellPressed(int cellPressedId);
}