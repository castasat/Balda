package com.openyogaland.denis.balda;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class FieldFragment extends Fragment
{
  private Game game;
  
  private static final int   NUM_OF_ROWS    = 5;
  private static final int   NUM_OF_COLUMNS = 5;
  private static final float CELL_WEIGHT    = 1.0F;
  
  
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.field_fragment, container, false);
    LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
  
    LinearLayout[]   line = new LinearLayout[NUM_OF_ROWS];
    SquareButton[][] cell = new SquareButton[NUM_OF_ROWS][NUM_OF_COLUMNS];
  
    LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
        LayoutParams.WRAP_CONTENT, CELL_WEIGHT);
    
    for (int row = 0; row < NUM_OF_ROWS; row++)
    {
      line[row] = new LinearLayout(getContext());
      line[row].setOrientation(LinearLayout.HORIZONTAL);
      linearLayout.addView(line[row]);
      
      for(int column = 0; column < NUM_OF_COLUMNS; column++)
      {
        cell[row][column] = new SquareButton(getContext());
        cell[row][column].setLayoutParams(params);
        line[row].addView(cell[row][column]);
      }
    }
    
    return view;
  }
  
    
  
  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    
    try
    {
      game = (Game) context;
    }
    catch(ClassCastException e)
    {
      throw new ClassCastException(context.toString() + e);
    }
  }
}
