package com.openyogaland.denis.balda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ScoreAdapter extends BaseAdapter
{
  /**
   * constants
   */
  public static final boolean PLAYER   = true;
  public static final boolean OPPONENT = false;
  
  /**
   * fields
   */
  private LayoutInflater       inflater;
  private ArrayList<ScoreItem> scoreItems;
  
  private boolean whichPlayer;
  private int     colorPlayerScore,
                  colorOpponentScore;
  
  /**
   * constructor
   * @param context the context that this adapter will eventually be attached to
   * @param scoreItems an ArrayList of ScoreItem elements
   * @param whichPlayer - may be ScoreAdapter.PLAYER or ScoreAdapter.OPPONENT
   */
  ScoreAdapter(@NonNull Context context, @NonNull ArrayList<ScoreItem> scoreItems, boolean whichPlayer)
  {
    this(context, scoreItems);
    this.whichPlayer = whichPlayer;
    colorPlayerScore = ContextCompat.getColor(context, R.color.colorPlayerScore);
    colorOpponentScore = ContextCompat.getColor(context, R.color.colorOpponentScore);
  }
  
  /**
   * constructor
   * @param context the context that this adapter will eventually be attached to
   * @param scoreItems an ArrayList of ScoreItem elements
   */
  private ScoreAdapter(@NonNull Context context, @NonNull ArrayList<ScoreItem> scoreItems)
  {
    this.scoreItems = scoreItems;
    inflater = LayoutInflater.from(context);
  }
  
  /**
   * @return number of items
   */
  @Override
  public int getCount()
  {
    return scoreItems.size();
  }
  
  /**
   * @param position - position of item
   * @return Object item by it's position
   * @throws IllegalArgumentException if position is greater than the size of the list
   */
  @Override
  public Object getItem(int position) throws IllegalArgumentException
  {
    if(position > getCount())
    {
      throw new IllegalArgumentException(
          "List size overflow. Position shouldn't be greater than the size of the list");
    }
    return scoreItems.get(position);
  }
  
  /**
   * @param position of item
   * @return item id by it's position
   * @throws IllegalArgumentException if position is greater than the size of the list
   */
  @Override
  public long getItemId(int position) throws IllegalArgumentException
  {
    if(position > getCount())
    {
      throw new IllegalArgumentException(
          "List size overflow. Position shouldn't be greater than the size of the list");
    }
    return position;
  }
  
  /**
   * @param position - position of item
   * @return ScoreItem by it's position
   * @throws IllegalArgumentException if position is greater than the size of the list
   */
  private ScoreItem getScoreItem(int position) throws IllegalArgumentException
  {
    if(position > getCount())
    {
      throw new IllegalArgumentException(
          "List size overflow. Position shouldn't be greater than the size of the list");
    }
    return ((ScoreItem) getItem(position));
  }
  
  /**
   * creates a View from score item
   * @param position - position of item
   * @param convertView the old view to reuse, if possible.
   * @param parent ViewGroup that this view will eventually be attached to
   * @return a View created from score item at the specified position
   * @throws IllegalArgumentException if position is greater than the size of the list
   */
  @Override
  public View getView(int position, View convertView, @Nullable ViewGroup parent)
      throws IllegalArgumentException
  {
    if(position > getCount())
    {
      throw new IllegalArgumentException(
          "List size overflow. Position shouldn't be greater than the size of the list");
    }
    
    if(convertView == null)
    {
      convertView = inflater.inflate(R.layout.score_item, parent, false);
    }
    
    TextView wordTextView      = convertView.findViewById(R.id.wordTextView);
    TextView wordScoreTextView = convertView.findViewById(R.id.wordScoreTextView);
    
    // fill View with data from ScoreItem class
    ScoreItem scoreItem = getScoreItem(position);
    wordTextView.setText(scoreItem.getWord());
    wordScoreTextView.setText(scoreItem.getScore());
    
    // set color depending on parameter
    if (whichPlayer == PLAYER)
    {
      wordScoreTextView.setTextColor(colorPlayerScore);
    }
    else if (whichPlayer == OPPONENT)
    {
      wordScoreTextView.setTextColor(colorOpponentScore);
    }
    
    return convertView;
  }
  
  // TODO add method public addScoreItem(String word)
}
