package com.gumsis.injs.s.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class KeyboardView
  extends FrameLayout
  implements View.OnClickListener
{
  private OnKeyboardCallback mOnKeyboardCallback;
  private RelativeLayout numBack;
  private LinearLayout numEight;
  private LinearLayout numFive;
  private LinearLayout numFour;
  private LinearLayout numNine;
  private LinearLayout numOne;
  private LinearLayout numSeven;
  private LinearLayout numSix;
  private LinearLayout numThree;
  private LinearLayout numTwo;
  private LinearLayout numZero;
  
  public KeyboardView(@NonNull Context paramContext)
  {
    super(paramContext);
    initUI();
  }
  
  public KeyboardView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initUI();
  }
  
  public KeyboardView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initUI();
  }
  
  @TargetApi(21)
  public KeyboardView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    initUI();
  }
  
  private void initUI()
  {
    ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131230722, this);
    this.numOne = ((LinearLayout)findViewById(2131099716));
    this.numTwo = ((LinearLayout)findViewById(2131099720));
    this.numThree = ((LinearLayout)findViewById(2131099719));
    this.numFour = ((LinearLayout)findViewById(2131099714));
    this.numFive = ((LinearLayout)findViewById(2131099713));
    this.numSix = ((LinearLayout)findViewById(2131099718));
    this.numSeven = ((LinearLayout)findViewById(2131099717));
    this.numEight = ((LinearLayout)findViewById(2131099712));
    this.numNine = ((LinearLayout)findViewById(2131099715));
    this.numZero = ((LinearLayout)findViewById(2131099721));
    this.numBack = ((RelativeLayout)findViewById(2131099711));
    onCLickListener();
  }
  
  private void onCLickListener()
  {
    this.numOne.setOnClickListener(this);
    this.numTwo.setOnClickListener(this);
    this.numThree.setOnClickListener(this);
    this.numFour.setOnClickListener(this);
    this.numFive.setOnClickListener(this);
    this.numSix.setOnClickListener(this);
    this.numSeven.setOnClickListener(this);
    this.numEight.setOnClickListener(this);
    this.numNine.setOnClickListener(this);
    this.numZero.setOnClickListener(this);
    this.numBack.setOnClickListener(this);
  }
  
  public void onClick(View paramView)
  {
    String str = "";
    switch (paramView.getId())
    {
    default: 
      paramView = str;
      if (this.mOnKeyboardCallback != null) {
        this.mOnKeyboardCallback.onSetNumber(paramView);
      }
      break;
    }
    do
    {
      return;
      paramView = "1";
      break;
      paramView = "2";
      break;
      paramView = "3";
      break;
      paramView = "4";
      break;
      paramView = "5";
      break;
      paramView = "6";
      break;
      paramView = "7";
      break;
      paramView = "8";
      break;
      paramView = "9";
      break;
      paramView = "0";
      break;
    } while (this.mOnKeyboardCallback == null);
    this.mOnKeyboardCallback.onSetBack();
  }
  
  public void setOnKeyboardCallback(OnKeyboardCallback paramOnKeyboardCallback)
  {
    this.mOnKeyboardCallback = paramOnKeyboardCallback;
  }
  
  public static abstract interface OnKeyboardCallback
  {
    public abstract void onSetBack();
    
    public abstract void onSetNumber(String paramString);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/s/views/KeyboardView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */