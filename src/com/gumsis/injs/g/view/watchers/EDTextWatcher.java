package com.gumsis.injs.g.view.watchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import com.gumsis.injs.g.view._CTextInputLayout;
import java.util.Calendar;

public abstract class EDTextWatcher
  implements TextWatcher
{
  private static int expMonth;
  private static int expYear;
  private String currentText = "";
  private _CTextInputLayout mCTextInputLayout;
  
  protected EDTextWatcher(_CTextInputLayout param_CTextInputLayout)
  {
    this.mCTextInputLayout = param_CTextInputLayout;
    this.currentText = this.mCTextInputLayout.getEditText().getText().toString();
    this.mCTextInputLayout.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if ((EDTextWatcher.this.mCTextInputLayout.hasValidInput()) || (EDTextWatcher.this.currentText.isEmpty()))
        {
          EDTextWatcher.this.mCTextInputLayout.setError(null);
          return;
        }
        EDTextWatcher.this.mCTextInputLayout.setError("Enter a valid expiration date");
      }
    });
  }
  
  private String getExpDate(String paramString)
  {
    this.mCTextInputLayout.setError(null);
    if (this.currentText.length() > paramString.length())
    {
      if (this.currentText.endsWith("/")) {
        return paramString.substring(0, paramString.length() - 1);
      }
    }
    else if (paramString.length() == 1)
    {
      if (Integer.parseInt(paramString) > 1) {
        return "0" + paramString + "/";
      }
    }
    else if (paramString.length() == 2)
    {
      if (paramString.startsWith("0"))
      {
        if (paramString.endsWith("0")) {
          return "0";
        }
        return paramString + "/";
      }
      if (paramString.startsWith("1"))
      {
        if (Integer.parseInt(paramString.substring(1)) > 2) {
          return "1";
        }
        return paramString + "/";
      }
      return paramString.substring(0, 1);
    }
    return paramString;
  }
  
  private static boolean isValidExpiringDate(String paramString)
  {
    boolean bool = true;
    try
    {
      if (paramString.length() != 5) {
        return false;
      }
      Calendar localCalendar = Calendar.getInstance();
      int i = localCalendar.get(2);
      int j = Integer.parseInt(String.valueOf(localCalendar.get(1)).substring(2));
      paramString = paramString.split("/");
      if (paramString[0].length() == 2)
      {
        expMonth = Integer.parseInt(paramString[0]);
        expYear = Integer.parseInt(paramString[1]);
        if (j < expYear) {
          return bool;
        }
        if (j == expYear)
        {
          j = expMonth;
          if (i >= j) {
            bool = false;
          }
        }
        else
        {
          bool = false;
        }
      }
    }
    catch (Exception paramString) {}
    return false;
    return bool;
  }
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (!paramCharSequence.toString().equals(this.currentText))
    {
      this.currentText = getExpDate(paramCharSequence.toString());
      this.mCTextInputLayout.getEditText().setText(this.currentText);
      this.mCTextInputLayout.setHasValidInput(false);
      this.mCTextInputLayout.getEditText().setSelection(this.currentText.length());
    }
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramCharSequence.length() == 5)
    {
      bool1 = bool2;
      if (isValidExpiringDate(paramCharSequence.toString())) {
        bool1 = true;
      }
    }
    this.mCTextInputLayout.setHasValidInput(bool1);
    onValidated(bool1, this.mCTextInputLayout.getEditText().getText().toString(), expMonth, expYear);
  }
  
  protected abstract void onValidated(boolean paramBoolean, String paramString, int paramInt1, int paramInt2);
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/view/watchers/EDTextWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */