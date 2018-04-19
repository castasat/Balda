package com.openyogaland.denis.balda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreFragment extends Fragment
{
  @Override @Nullable
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.score_fragment, container, false);
    
    // TODO just for testing
    final String WORD = "Балда";
    
    int colorPlayerScore   = ContextCompat.getColor(getContext(), R.color.colorPlayerScore);
    int colorOpponentScore = ContextCompat.getColor(getContext(), R.color.colorOpponentScore);
    
    for(int i = 0; i < 10; i++)
    {
      // player score item
      LinearLayout playerScoreLayout       = view.findViewById(R.id.playerScoreLayout);
      View         playerItem              = inflater.inflate(R.layout.score_item, playerScoreLayout, false);
      TextView     playerWordTextView      = playerItem.findViewById(R.id.wordTextView);
      TextView     playerWordScoreTextView = playerItem.findViewById(R.id.wordScoreTextView);
      playerWordScoreTextView.setTextColor(colorPlayerScore);
      
      // TODO just for testing
      playerWordTextView.setText(i+1 + ". "+ WORD);
      playerWordScoreTextView.setText(String.valueOf(WORD.length()));
  
      playerItem.getLayoutParams().width = LayoutParams.MATCH_PARENT;
      playerScoreLayout.addView(playerItem);
    }
    
    for (int i = 0; i < 10; i++)
    {
      // opponent score item
      LinearLayout opponentScoreLayout = view.findViewById(R.id.opponentScoreLayout);
      View opponentItem = inflater.inflate(R.layout.score_item, opponentScoreLayout, false);
      TextView opponentWordTextView = opponentItem.findViewById(R.id.wordTextView);
      TextView opponentWordScoreTextView = opponentItem.findViewById(R.id.wordScoreTextView);
      opponentWordScoreTextView.setTextColor(colorOpponentScore);
      
      opponentWordTextView.setText(i+1 + ". " + WORD);
      opponentWordScoreTextView.setText(String.valueOf(WORD.length()));
  
      opponentItem.getLayoutParams().width = LayoutParams.MATCH_PARENT;
      opponentScoreLayout.addView(opponentItem);
    }
    
    return view;
  }
}
