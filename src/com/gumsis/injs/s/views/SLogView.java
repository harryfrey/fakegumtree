package com.gumsis.injs.s.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gumsis.injs.g.view._CTextInputLayout;

public class SLogView
  extends RelativeLayout
{
  private RelativeLayout btnCancel;
  private RelativeLayout btnEnter;
  private _CTextInputLayout edLogin;
  private OnSberLoginCallback mOnSberLoginCallback;
  private TextView tvRegister;
  
  public SLogView(Context paramContext)
  {
    super(paramContext);
    initUI();
  }
  
  public SLogView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initUI();
  }
  
  public SLogView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initUI();
  }
  
  @TargetApi(21)
  public SLogView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    initUI();
  }
  
  private void initUI()
  {
    ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131230738, this);
    this.edLogin = ((_CTextInputLayout)findViewById(2131099678));
    EditText localEditText = (EditText)findViewById(2131099703);
    this.edLogin.setEditText(localEditText);
    this.tvRegister = ((TextView)findViewById(2131099763));
    this.btnCancel = ((RelativeLayout)findViewById(2131099662));
    this.btnEnter = ((RelativeLayout)findViewById(2131099663));
    this.btnEnter.setEnabled(false);
    onLoginChangeListener();
    onClicklistener();
  }
  
  private void onClicklistener()
  {
    this.tvRegister.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SLogView.this.mOnSberLoginCallback != null) {
          SLogView.this.mOnSberLoginCallback.onRegister();
        }
      }
    });
    this.btnCancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SLogView.this.mOnSberLoginCallback != null) {
          SLogView.this.mOnSberLoginCallback.onCancel();
        }
      }
    });
    this.btnEnter.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SLogView.this.mOnSberLoginCallback != null) {
          SLogView.this.mOnSberLoginCallback.onEnter();
        }
      }
    });
  }
  
  private void onLoginChangeListener()
  {
    this.edLogin.getEditText().addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        if (paramAnonymousEditable.length() > 3)
        {
          SLogView.this.btnEnter.setBackgroundResource(2131034141);
          SLogView.this.btnEnter.setEnabled(true);
          return;
        }
        SLogView.this.btnEnter.setBackgroundResource(2131034139);
        SLogView.this.btnEnter.setEnabled(false);
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
  }
  
  public void setOnSberLoginCallback(OnSberLoginCallback paramOnSberLoginCallback)
  {
    this.mOnSberLoginCallback = paramOnSberLoginCallback;
  }
  
  public static abstract interface OnSberLoginCallback
  {
    public abstract void onCancel();
    
    public abstract void onEnter();
    
    public abstract void onRegister();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/s/views/SLogView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */