package com.openyogaland.denis.balda;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;

public class FieldFragment extends Fragment
{
  private static final int   NUM_OF_LINES  = 5;
  private static final int   NUM_OF_CELLS  = 5;
  private static final float BUTTON_WEIGHT = 1.0F;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.field_fragment, container, false);
    LinearLayout fieldLayout = view.findViewById(R.id.fieldLayout);
    createGameField(fieldLayout);
    return view;
  }
  
  private void createGameField(ViewManager viewManager)
  {
    LinearLayout[] line = new LinearLayout[NUM_OF_LINES];
    LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    
    Context context = getActivity().getApplicationContext();
  
    for(int i = 0; i < NUM_OF_LINES; i++)
    {
      line[i] = new LinearLayout(context);
      line[i].setOrientation(LinearLayout.HORIZONTAL);
      addCells(line[i]);
      viewManager.addView(line[i], layoutParams);
    }
  }
  
  private void addCells(ViewGroup viewGroup)
  {
    Button[] button = new Button[NUM_OF_CELLS];
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
        LayoutParams.WRAP_CONTENT, BUTTON_WEIGHT);
    
    for (int i = 0; i < NUM_OF_CELLS; i++)
    {
      button[i] = new Button(getContext());
      button[i].setLayoutParams(layoutParams);
      viewGroup.addView(button[i]);
    }
  }
  
}
