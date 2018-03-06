package com.openyogaland.denis.balda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class FieldFragment extends Fragment implements OnClickListener
{
  private static final int    NUM_OF_ROWS            = 5;
  private static final int    NUM_OF_COLUMNS         = 5;
  
  private static final int    INITIAL_WORD_ROW_INDEX = 2;
  private static final String INITIAL_WORD           = "балда";
  
  private static final String CELL_NAME_PREFIX       = "cell_";
  private static final String ID_RESOURCE_TIPE       = "id";
  private static final String PACKAGE_NAME           = "com.openyogaland.denis.balda";
  
  private final SquareButton[][] cell   = new SquareButton[NUM_OF_ROWS][NUM_OF_COLUMNS];
  private final String[][]       matrix = new String[NUM_OF_ROWS][NUM_OF_COLUMNS];
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }
  
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.field_fragment, container, false);
        
    for (int row = 0; row < NUM_OF_ROWS; row++)
    {
      for (int column = 0; column < NUM_OF_COLUMNS; column++)
      {
        String cellIndex = String.valueOf(row) + String.valueOf(column);
        int cell_id = getResources().getIdentifier(CELL_NAME_PREFIX + cellIndex, ID_RESOURCE_TIPE, PACKAGE_NAME);
        cell[row][column] = view.findViewById(cell_id);
        
        if (row == INITIAL_WORD_ROW_INDEX)
        {
          writeInitialWord(column);
        }
        
        String cellText = (String) cell[row][column].getText();
        if(cellText.isEmpty())
        {
          // restore saved value
          cell[row][column].setText(matrix[row][column]);
        }
        else
        {
          // save value
          matrix[row][column] = cellText;
        }
      }
    }
    return view;
  }
  
  private void writeInitialWord(int column)
  {
    String letterToWrite = INITIAL_WORD.substring(column, column + 1);
    cell[INITIAL_WORD_ROW_INDEX][column].setText(letterToWrite);
  }
  
  @Override
  public void onClick(View view)
  {
  }
}
