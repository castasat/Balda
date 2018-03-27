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
import android.widget.Button;

public class FieldFragment extends Fragment implements OnClickListener
{
  private static final int    NUM_OF_ROWS            = 5;
  private static final int    NUM_OF_COLUMNS         = 5;
  
  private static final int    INITIAL_WORD_ROW_INDEX = 2;
  private static final String INITIAL_WORD           = "балда";
  
  private static final String CELL_NAME_PREFIX       = "cell_";
  private static final String ID_RESOURCE_TIPE       = "id";
  
  private final Button[][] cell   = new Button[NUM_OF_ROWS][NUM_OF_COLUMNS];
  private final String[][] matrix = new String[NUM_OF_ROWS][NUM_OF_COLUMNS];
  
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
          int cell_id = getResources().getIdentifier(CELL_NAME_PREFIX + cellIndex,
            ID_RESOURCE_TIPE, getContext().getPackageName());
          cell[row][column] = view.findViewById(cell_id);
          cell[row][column].setOnClickListener(this);
        }
        
        // TODO check
        if (row == INITIAL_WORD_ROW_INDEX)
        {
          writeInitialWord(column);
        }
        
        String cellText = cell[row][column].getText().toString();
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
    // TODO check language
    // TODO get a random word from dictionary
    
    String letterToWrite = INITIAL_WORD.substring(column, column + 1);
    Button currentCell = cell[INITIAL_WORD_ROW_INDEX][column];
    currentCell.setText(letterToWrite);
    disableButton(currentCell);
  }
  
  @Override
  public void onClick(View view)
  {
    if (view instanceof Button)
    {
      Button cellPressed = (Button) view;
      // TODO get cellPressed id
      onCellPressedListener.onCellPressed(cellPressed);
    }
  }
  
  // метод прикрепления фрагмента к контексту (активности)
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
      throw new ClassCastException(e + context.toString() +
                                   " should implement OnCellPressedListener interface");
    }
  }
  
  private static void disableButton(Button currentCell)
  {
    currentCell.setClickable(false);
    currentCell.setFocusable(false);
  }
}