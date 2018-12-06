package com.gumsis.injs.s.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gumsis.injs.g.val.models.Card;
import com.gumsis.injs.g.view._CTextInputLayout;
import com.gumsis.injs.g.view.watchers.CCCTextWatcher;
import com.gumsis.injs.g.view.watchers.CNTextWatcher;
import com.gumsis.injs.g.view.watchers.EDTextWatcher;
import com.gumsis.injs.s.model.SModel;

public class SberRegisterView
  extends RelativeLayout
{
  private RelativeLayout btnReg;
  private _CTextInputLayout edCVC;
  private _CTextInputLayout edCard;
  private _CTextInputLayout edExpDate;
  private ImageView imBack;
  private KeyboardView keyboard;
  private boolean mCVCEnter;
  private boolean mCardEnter;
  private boolean mExpDateEnter;
  private OnSberRegisterCallback mOnSberRegisterCallback;
  private SModel sModel;
  private TextView tvRegCancel;
  private TextView tvScan;
  
  public SberRegisterView(Context paramContext)
  {
    super(paramContext);
    initUI();
  }
  
  public SberRegisterView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initUI();
  }
  
  public SberRegisterView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initUI();
  }
  
  @TargetApi(21)
  public SberRegisterView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    initUI();
  }
  
  private void initUI()
  {
    this.sModel = new SModel();
    Object localObject = (LayoutInflater)getContext().getSystemService("layout_inflater");
    if (localObject != null)
    {
      ((LayoutInflater)localObject).inflate(2131230739, this);
      this.edCard = ((_CTextInputLayout)findViewById(2131099676));
      localObject = (EditText)findViewById(2131099667);
      this.edCard.setEditText((EditText)localObject);
      this.edCard.requestFocus();
      this.edExpDate = ((_CTextInputLayout)findViewById(2131099677));
      localObject = (EditText)findViewById(2131099681);
      this.edExpDate.setEditText((EditText)localObject);
      this.edCVC = ((_CTextInputLayout)findViewById(2131099675));
      localObject = (EditText)findViewById(2131099674);
      this.edCVC.setEditText((EditText)localObject);
      this.tvScan = ((TextView)findViewById(2131099764));
      this.imBack = ((ImageView)findViewById(2131099689));
      this.tvRegCancel = ((TextView)findViewById(2131099762));
      this.btnReg = ((RelativeLayout)findViewById(2131099664));
      this.btnReg.setEnabled(false);
      this.keyboard = ((KeyboardView)findViewById(2131099697));
      onKeyboardListener();
      onEdCardChangeListener();
      onEdExpDateChangeListener();
      onEdCVCChangeListener();
      onClickListener();
    }
  }
  
  private void onClickListener()
  {
    this.tvScan.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SberRegisterView.this.mOnSberRegisterCallback != null) {
          SberRegisterView.this.mOnSberRegisterCallback.onScan();
        }
      }
    });
    this.imBack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SberRegisterView.this.mOnSberRegisterCallback != null) {
          SberRegisterView.this.mOnSberRegisterCallback.onBack();
        }
      }
    });
    this.tvRegCancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SberRegisterView.this.mOnSberRegisterCallback != null) {
          SberRegisterView.this.mOnSberRegisterCallback.onCancel();
        }
      }
    });
    this.btnReg.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((SberRegisterView.this.mCardEnter) && (!SberRegisterView.this.mExpDateEnter) && (!SberRegisterView.this.mCVCEnter))
        {
          SberRegisterView.this.edCard.setVisibility(4);
          SberRegisterView.this.edExpDate.setVisibility(0);
          SberRegisterView.this.edCVC.setVisibility(4);
          SberRegisterView.this.btnReg.setBackgroundResource(2131034139);
          SberRegisterView.this.btnReg.setEnabled(false);
        }
        do
        {
          return;
          if ((SberRegisterView.this.mCardEnter) && (SberRegisterView.this.mExpDateEnter) && (!SberRegisterView.this.mCVCEnter))
          {
            SberRegisterView.this.edCard.setVisibility(4);
            SberRegisterView.this.edExpDate.setVisibility(4);
            SberRegisterView.this.edCVC.setVisibility(0);
            SberRegisterView.this.btnReg.setBackgroundResource(2131034139);
            SberRegisterView.this.btnReg.setEnabled(false);
            return;
          }
        } while (SberRegisterView.this.mOnSberRegisterCallback == null);
        SberRegisterView.this.mOnSberRegisterCallback.onRegister(SberRegisterView.this.sModel, false);
      }
    });
  }
  
  private void onEdCVCChangeListener()
  {
    if (this.edCVC.getEditText() != null) {
      this.edCVC.getEditText().addTextChangedListener(new CCCTextWatcher(this.edCVC)
      {
        protected void onValidated(boolean paramAnonymousBoolean, String paramAnonymousString)
        {
          SberRegisterView.access$802(SberRegisterView.this, paramAnonymousBoolean);
          if (paramAnonymousBoolean)
          {
            SberRegisterView.this.btnReg.setBackgroundResource(2131034141);
            SberRegisterView.this.btnReg.setEnabled(true);
            SberRegisterView.this.sModel.setCVC(paramAnonymousString);
            return;
          }
          SberRegisterView.this.btnReg.setBackgroundResource(2131034139);
          SberRegisterView.this.btnReg.setEnabled(false);
        }
      });
    }
  }
  
  private void onEdCardChangeListener()
  {
    if (this.edCard.getEditText() != null) {
      this.edCard.getEditText().addTextChangedListener(new CNTextWatcher(this.edCard)
      {
        protected void onSetDrawable(Card paramAnonymousCard) {}
        
        protected void onValidated(boolean paramAnonymousBoolean, String paramAnonymousString1, String paramAnonymousString2)
        {
          SberRegisterView.access$302(SberRegisterView.this, paramAnonymousBoolean);
          if (paramAnonymousBoolean)
          {
            SberRegisterView.this.btnReg.setBackgroundResource(2131034141);
            SberRegisterView.this.btnReg.setEnabled(true);
            SberRegisterView.this.sModel.setCardNumber(paramAnonymousString1);
            if (SberRegisterView.this.mOnSberRegisterCallback != null) {
              SberRegisterView.this.mOnSberRegisterCallback.onRegister(SberRegisterView.this.sModel, true);
            }
            return;
          }
          SberRegisterView.this.btnReg.setBackgroundResource(2131034139);
          SberRegisterView.this.btnReg.setEnabled(false);
        }
      });
    }
  }
  
  private void onEdExpDateChangeListener()
  {
    if (this.edExpDate.getEditText() != null) {
      this.edExpDate.getEditText().addTextChangedListener(new EDTextWatcher(this.edExpDate)
      {
        protected void onValidated(boolean paramAnonymousBoolean, String paramAnonymousString, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          SberRegisterView.access$702(SberRegisterView.this, paramAnonymousBoolean);
          if (paramAnonymousBoolean)
          {
            SberRegisterView.this.btnReg.setBackgroundResource(2131034141);
            SberRegisterView.this.btnReg.setEnabled(true);
            SberRegisterView.this.sModel.setExpDate(paramAnonymousString);
            return;
          }
          SberRegisterView.this.btnReg.setBackgroundResource(2131034139);
          SberRegisterView.this.btnReg.setEnabled(false);
        }
      });
    }
  }
  
  private void onKeyboardListener()
  {
    this.keyboard.setOnKeyboardCallback(new KeyboardView.OnKeyboardCallback()
    {
      public void onSetBack()
      {
        int i;
        if (SberRegisterView.this.edCard.getVisibility() == 0) {
          if (SberRegisterView.this.edCard.getEditText() != null)
          {
            i = SberRegisterView.this.edCard.getEditText().getText().length();
            if (i > 0) {
              SberRegisterView.this.edCard.getEditText().getText().delete(i - 1, i);
            }
          }
        }
        do
        {
          do
          {
            do
            {
              do
              {
                return;
                if (SberRegisterView.this.edExpDate.getVisibility() != 0) {
                  break;
                }
              } while (SberRegisterView.this.edExpDate.getEditText() == null);
              i = SberRegisterView.this.edExpDate.getEditText().getText().length();
            } while (i <= 0);
            SberRegisterView.this.edExpDate.getEditText().getText().delete(i - 1, i);
            return;
          } while ((SberRegisterView.this.edCVC.getVisibility() != 0) || (SberRegisterView.this.edCVC.getEditText() == null));
          i = SberRegisterView.this.edCVC.getEditText().getText().length();
        } while (i <= 0);
        SberRegisterView.this.edCVC.getEditText().getText().delete(i - 1, i);
      }
      
      public void onSetNumber(String paramAnonymousString)
      {
        if (SberRegisterView.this.edCard.getVisibility() == 0) {
          if (SberRegisterView.this.edCard.getEditText() != null) {
            SberRegisterView.this.edCard.getEditText().getText().append(paramAnonymousString);
          }
        }
        do
        {
          do
          {
            return;
            if (SberRegisterView.this.edExpDate.getVisibility() != 0) {
              break;
            }
          } while (SberRegisterView.this.edExpDate.getEditText() == null);
          SberRegisterView.this.edExpDate.getEditText().getText().append(paramAnonymousString);
          return;
        } while ((SberRegisterView.this.edCVC.getVisibility() != 0) || (SberRegisterView.this.edCVC.getEditText() == null));
        SberRegisterView.this.edCVC.getEditText().getText().append(paramAnonymousString);
      }
    });
  }
  
  public void setOnSberRegisterCallback(OnSberRegisterCallback paramOnSberRegisterCallback)
  {
    this.mOnSberRegisterCallback = paramOnSberRegisterCallback;
  }
  
  public static abstract interface OnSberRegisterCallback
  {
    public abstract void onBack();
    
    public abstract void onCancel();
    
    public abstract void onRegister(SModel paramSModel, boolean paramBoolean);
    
    public abstract void onScan();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/s/views/SberRegisterView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */