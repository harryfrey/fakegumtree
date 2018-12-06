package com.gumsis.injs.g.view.watchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import com.gumsis.injs.g.view._CTextInputLayout;

public abstract class CCCTextWatcher
  implements TextWatcher
{
  private _CTextInputLayout mCTextInputLayout;
  
  protected CCCTextWatcher(_CTextInputLayout param_CTextInputLayout)
  {
    this.mCTextInputLayout = param_CTextInputLayout;
    this.mCTextInputLayout.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if ((CCCTextWatcher.this.mCTextInputLayout.hasValidInput()) || (CCCTextWatcher.this.mCTextInputLayout.getEditText().getText().toString().isEmpty()))
        {
          CCCTextWatcher.this.mCTextInputLayout.setError(null);
          return;
        }
        CCCTextWatcher.this.mCTextInputLayout.setError("Enter a valid card code");
      }
    });
  }
  
  private boolean isValid(CharSequence paramCharSequence)
  {
    return paramCharSequence.toString().length() == 3;
  }
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mCTextInputLayout.setError(null);
    boolean bool = isValid(paramCharSequence);
    this.mCTextInputLayout.setHasValidInput(bool);
    onValidated(bool, this.mCTextInputLayout.getEditText().getText().toString());
  }
  
  protected abstract void onValidated(boolean paramBoolean, String paramString);
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/view/watchers/CCCTextWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */