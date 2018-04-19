package com.openyogaland.denis.balda;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreFragment extends Fragment
{
  /**
   * fields
   */
  private ArrayList<ScoreItem> playerList   = new ArrayList<>();
  private ArrayList<ScoreItem> opponentList = new ArrayList<>();
  
  /**
   * Method onCreateView()
   * @param inflater - inflater to inflate resource layout
   * @param container - ViewGroup container of fragment
   * @param savedInstanceState - Bundle with saved instance state data
   * @return view of the fragment
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.score_fragment, container, false);
    Context context = getContext();
    
    if(context != null)
    {
      // player score
      ScoreAdapter playerScoreAdapter =
          new ScoreAdapter(context, playerList, ScoreAdapter.PLAYER);
      ListView playerListView   = view.findViewById(R.id.playerListView);
      playerListView.setAdapter(playerScoreAdapter);
  
      // opponent score
      ScoreAdapter opponentScoreAdapter =
          new ScoreAdapter(context, opponentList, ScoreAdapter.OPPONENT);
      ListView opponentListView = view.findViewById(R.id.opponentListView);
      opponentListView.setAdapter(opponentScoreAdapter);
      
      // TODO test
      playerScoreAdapter.addScoreItem("Англия");
      opponentScoreAdapter.addScoreItem("Германия");
    }
    return view;
  }
}
