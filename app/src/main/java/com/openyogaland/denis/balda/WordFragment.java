package com.openyogaland.denis.balda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class WordFragment extends DialogFragment implements OnClickListener, OnWordUpdateListener
{
  /**
   * fields
   */
  private TextView choosedWordTextView;
  private Button   clearWordButton;
  private Button   enterWordButton;
  
  /**
   * Called to have the fragment instantiate its user interface view.
   * This is optional, and non-graphical fragments can return null (which
   * is the default implementation).  This will be called between
   * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
   * <p>If you return a View from here, you will later be called in
   * {@link #onDestroyView} when the view is being released.
   * @param inflater The LayoutInflater object that can be used to inflate
   * any views in the fragment,
   * @param container If non-null, this is the parent view that the fragment's
   * UI should be attached to. The fragment should not add the view itself,
   * but this can be used to generate the LayoutParams of the view.
   * @param savedInstanceState If non-null, this fragment is being re-constructed
   * from a previous saved state as given here.
   * @return Return the View for the fragment's UI, or null.
   */
  @Override @Nullable
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState)
  {
    // we do not call getDialog(), because it returns null when called as a fragment
    // thus we do not setTitle() to the dialog
    View view = inflater.inflate(R.layout.word_fragment, container, false);
    
    // find views by ids
    choosedWordTextView = view.findViewById(R.id.choosedWordTextView);
    clearWordButton     = view.findViewById(R.id.clearWordButton);
    enterWordButton     = view.findViewById(R.id.enterWordButton);
    
    // set listeners
    clearWordButton.setOnClickListener(this);
    enterWordButton.setOnClickListener(this);
    
    // TODO make chooseWordTextView be updated as player chooses letters in FieldFragment
    
    return view;
  }
  
  /**
   * Called when a view has been clicked.
   * @param view The view that was clicked.
   */
  @Override
  public void onClick(View view)
  {
    if(view != null)
    {
      switch(view.getId())
      {
        default:
        case R.id.clearWordButton:
          // TODO clear word
          break;
        case R.id.enterWordButton:
          // TODO enter word
          break;
      }
    }
  }
  
  @Override
  public void onWordUpdate(@NonNull @NotNull String word)
  {
    if (!word.isEmpty())
    {
      choosedWordTextView.setText(word);
    }
  }
}
