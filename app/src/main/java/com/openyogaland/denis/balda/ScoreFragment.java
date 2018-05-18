package com.openyogaland.denis.balda;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreFragment extends Fragment implements OnScoreUpdatedListener
{
  /*
  * fields
  */
  private ArrayList<ScoreItem> playerList   = new ArrayList<>();
  private ArrayList<ScoreItem> opponentList = new ArrayList<>();
  private TextView             playerScore;
  private TextView             opponentScore;
  
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
      playerScore = view.findViewById(R.id.playerScore);
      opponentScore = view.findViewById(R.id.opponentScore);
      
      // player score
      RecyclerView playerRecyclerView = view.findViewById(R.id.playerRecyclerView);
      // improve performance if changes in content do not change the layout size of the RecyclerView
      playerRecyclerView.setHasFixedSize(true);
      // set layout manager
      LayoutManager playerLayoutManager = new LinearLayoutManager(context);
      playerRecyclerView.setLayoutManager(playerLayoutManager);
      // set adapter
      ScoreAdapter playerScoreAdapter = new ScoreAdapter(playerList, context, ScoreAdapter.PLAYER);
      playerScoreAdapter.setOnScoreUpdatedListener(this);
      playerRecyclerView.setAdapter(playerScoreAdapter);
  
      // opponent score
      RecyclerView opponentRecyclerView = view.findViewById(R.id.opponentRecyclerView);
      // improve performance if changes in content do not change the layout size of the RecyclerView
      opponentRecyclerView.setHasFixedSize(true);
      // set layout manager
      LayoutManager opponentLayoutManager = new LinearLayoutManager(context);
      opponentRecyclerView.setLayoutManager(opponentLayoutManager);
      // set adapter
      ScoreAdapter opponentScoreAdapter = new ScoreAdapter(opponentList, context, ScoreAdapter.OPPONENT);
      opponentScoreAdapter.setOnScoreUpdatedListener(this);
      opponentRecyclerView.setAdapter(opponentScoreAdapter);
      
      playerScoreAdapter.addScoreItem("Англия");
      playerScoreAdapter.addScoreItem("Англия");
      playerScoreAdapter.addScoreItem("Англия");
      playerScoreAdapter.addScoreItem("Англия");
      playerScoreAdapter.addScoreItem("Англия");
      playerScoreAdapter.addScoreItem("Англия");
      playerScoreAdapter.addScoreItem("Англия");
      playerScoreAdapter.addScoreItem("Англия");
      playerScoreAdapter.addScoreItem("Англия");
      playerScoreAdapter.addScoreItem("Англия");
      
      opponentScoreAdapter.addScoreItem("Германия");
      opponentScoreAdapter.addScoreItem("Германия");
      opponentScoreAdapter.addScoreItem("Германия");
      opponentScoreAdapter.addScoreItem("Германия");
      opponentScoreAdapter.addScoreItem("Германия");
      opponentScoreAdapter.addScoreItem("Германия");
      opponentScoreAdapter.addScoreItem("Германия");
      opponentScoreAdapter.addScoreItem("Германия");
      opponentScoreAdapter.addScoreItem("Германия");
      opponentScoreAdapter.addScoreItem("Германия");
    }
    return view;
  }
  
  @Override
  public void onScoreUpdated(int totalScore, boolean whichPlayer)
  {
    if (whichPlayer == ScoreAdapter.PLAYER)
    {
      playerScore.setText(String.valueOf(totalScore));
    }
    if (whichPlayer == ScoreAdapter.OPPONENT)
    {
      opponentScore.setText(String.valueOf(totalScore));
    }
  }
}

