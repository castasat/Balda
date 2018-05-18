package com.openyogaland.denis.balda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.openyogaland.denis.balda.ScoreAdapter.ScoreViewHolder;
import org.jetbrains.annotations.Contract;
import java.util.ArrayList;

class ScoreAdapter extends Adapter<ScoreViewHolder>
{
  /**
   * constants
   */
  public static final boolean PLAYER   = true;
  public static final boolean OPPONENT = false;
  
  /**
  * fields
  */
  private Context              context;
  private ArrayList<ScoreItem> scoreItems;
  private boolean              whichPlayer;
  private int                  colorPlayerScore, colorOpponentScore;
  
  /**
   * constructor
   * @param context the context that this adapter will eventually be attached to
   * @param scoreItems an ArrayList of ScoreItem elements
   * @param whichPlayer - may be ScoreAdapter.PLAYER or ScoreAdapter.OPPONENT
   */
  ScoreAdapter(@NonNull ArrayList<ScoreItem> scoreItems, @NonNull Context context, boolean whichPlayer)
  {
    this(scoreItems);
    this. context      = context;
    this.whichPlayer   = whichPlayer;
    colorPlayerScore   = ContextCompat.getColor(context, R.color.colorPlayerScore);
    colorOpponentScore = ContextCompat.getColor(context, R.color.colorOpponentScore);
  }
  
  /**
   * constructor
   * @param scoreItems an ArrayList of ScoreItem elements
   */
  private ScoreAdapter(@NonNull ArrayList<ScoreItem> scoreItems)
  {
    this.scoreItems = scoreItems;
  }
  
  /**
   * inner static class ScoreViewHolder
   * Provides a reference to the views for each data item
   */
  static class ScoreViewHolder extends ViewHolder
  {
    FrameLayout scoreItemFrameLayout;
    TextView    wordTextView;
    TextView    wordScoreTextView;
    
    ScoreViewHolder(FrameLayout scoreItemFrameLayout)
    {
      super(scoreItemFrameLayout);
      this.scoreItemFrameLayout = scoreItemFrameLayout;
      wordTextView              = scoreItemFrameLayout.findViewById(R.id.wordTextView);
      wordScoreTextView         = scoreItemFrameLayout.findViewById(R.id.wordScoreTextView);
    }
  }
  
  /**
   * Creates new views (invoked by the layout manager). Called when RecyclerView needs a new
   * {@link ViewHolder} of the given type to represent an item. This new ViewHolder should be
   * constructed with a new View that can represent the items of the given type.
   * You can either create a new View manually or inflate it from an XML
   * layout file. The new ViewHolder will be used to display items of the adapter using
   * {onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
   * different items in the data set, it is a good idea to cache references to sub views of
   * the View to avoid unnecessary {@link View#findViewById(int)} calls.
   * @param parent The ViewGroup into which the new View will be added after it is bound to
   * an adapter position.
   * @param viewType The view type of the new View.
   * @return A new ViewHolder that holds a View of the given view type.
   * @see #getItemViewType(int)
   * @see #onBindViewHolder(ViewHolder, int)
   */
  @Override @NonNull
  public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
  {
    // create a new View
    LayoutInflater inflater             = LayoutInflater.from(parent.getContext());
    FrameLayout    scoreItemFrameLayout = (FrameLayout) inflater.inflate(R.layout.score_item, parent, false);
    return new ScoreViewHolder(scoreItemFrameLayout);
  }
  
  /**
   * Replaces the contents of a view (invoked by the layout manager) to display the data at the
   * specified position. This method should update the contents of the {@link ViewHolder#itemView}
   * to reflect the item at the given position.
   * Note that unlike {@link ListView}, in RecyclerView this method will not be called 
   * again if the position of the item changes in the data set unless the item itself is
   * invalidated or the new position cannot be determined. For this reason, you should only
   * use the <code>position</code> parameter while acquiring the related data item inside
   * this method and should not keep a copy of it. If you need the position of an item later
   * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
   * have the updated adapter position. Override {onBindViewHolder(ViewHolder, int, List)} instead
   * if Adapter can handle efficient partial bind.
   * @param scoreViewHolder The ViewHolder which should be updated to represent the contents of the
   * item at the given position in the data set.
   * @param position The position of the item within the adapter's data set.
   */
  @Override
  public void onBindViewHolder(@NonNull ScoreViewHolder scoreViewHolder, int position)
  {
    if(position > getItemCount())
    {
      throwListOverflowException();
    }
    
    // fill View with data from ScoreItem class
    ScoreItem scoreItem = getScoreItem(position);
    
    // get formatted string
    String word = context.getString(R.string.word_formatted, position + 1, scoreItem.getWord());
    scoreViewHolder.wordTextView.setText(word);
    
    scoreViewHolder.wordScoreTextView.setText(scoreItem.getScoreString());
  
    // set color depending on parameter
    if(whichPlayer == PLAYER)
    {
      scoreViewHolder.wordScoreTextView.setTextColor(colorPlayerScore);
    }
    if(whichPlayer == OPPONENT)
    {
      scoreViewHolder.wordScoreTextView.setTextColor(colorOpponentScore);
    }
  }
  
  
  /**
   * @param position - position of item
   * @return Object item by it's position
   * @throws IllegalArgumentException if position is greater than the size of the list
   */
  private Object getItem(int position) throws IllegalArgumentException
  {
    if(position > getItemCount())
    {
      throwListOverflowException();
    }
    return scoreItems.get(position);
  }
  
  @Contract(" -> fail")
  private void throwListOverflowException()
  {
    throw new IllegalArgumentException(context.getString(R.string.list_overflow));
  }
  
  /**
   * @param position of item
   * @return item id by it's position
   * @throws IllegalArgumentException if position is greater than the size of the list
   */
  @Override
  public long getItemId(int position) throws IllegalArgumentException
  {
    if(position > getItemCount())
    {
      throwListOverflowException();
    }
    return position;
  }
  
  /**
   * Number of items in the data set held by the adapter (invoked by LayoutManager).
   * @return The total number of items in this adapter.
   */
  @Override
  public int getItemCount()
  {
    return scoreItems.size();
  }
  
  /**
   * @param position - position of item
   * @return ScoreItem by it's position
   * @throws IllegalArgumentException if position is greater than the size of the list
   */
  private ScoreItem getScoreItem(int position) throws IllegalArgumentException
  {
    if(position > getItemCount())
    {
      throwListOverflowException();
    }
    return ((ScoreItem) getItem(position));
  }
  
  /**
   * Adds new ScoreItem
   * @param word - String word to calculate it's score
   */
  public void addScoreItem(String word)
  {
    ScoreItem scoreItem = new ScoreItem(word);
    scoreItems.add(scoreItem);
  }
}