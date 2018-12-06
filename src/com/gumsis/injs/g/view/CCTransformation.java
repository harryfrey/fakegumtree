package com.gumsis.injs.g.view;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

public class CCTransformation
  extends PasswordTransformationMethod
{
  private static CCTransformation sInstance;
  
  public static CCTransformation getInstance()
  {
    if (sInstance != null) {
      return sInstance;
    }
    sInstance = new CCTransformation();
    return sInstance;
  }
  
  public CharSequence getTransformation(CharSequence paramCharSequence, View paramView)
  {
    return paramCharSequence;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/view/CCTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */