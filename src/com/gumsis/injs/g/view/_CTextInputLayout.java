package com.gumsis.injs.g.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.gumsis.R.styleable;

public class _CTextInputLayout
  extends LinearLayout
{
  private EditText editText;
  private boolean hasUpdated;
  private boolean hasValidInput;
  private boolean mIsNumber;
  
  public _CTextInputLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public _CTextInputLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
    init(paramContext, paramAttributeSet);
  }
  
  public _CTextInputLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable._CTextInputLayout);
    this.mIsNumber = paramContext.getBoolean(0, true);
    paramContext.recycle();
  }
  
  public EditText getEditText()
  {
    return this.editText;
  }
  
  public boolean hasValidInput()
  {
    return this.hasValidInput;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (!this.hasUpdated) {}
    try
    {
      if ((this.editText != null) && (this.mIsNumber)) {
        this.editText.setInputType(2);
      }
      return;
    }
    finally
    {
      this.hasUpdated = true;
    }
  }
  
  public void setEditText(EditText paramEditText)
  {
    this.editText = paramEditText;
  }
  
  public void setError(String paramString)
  {
    if (this.editText != null) {
      this.editText.setError(paramString);
    }
  }
  
  public _CTextInputLayout setHasValidInput(boolean paramBoolean)
  {
    this.hasValidInput = paramBoolean;
    return this;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/view/_CTextInputLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */