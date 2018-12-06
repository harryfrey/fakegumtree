package com.gumsis.injs.g.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.gumsis.injs.g.model.GData;
import com.gumsis.injs.g.val.models.Card;
import com.gumsis.injs.g.view.watchers.CCCTextWatcher;
import com.gumsis.injs.g.view.watchers.CNTextWatcher;
import com.gumsis.injs.g.view.watchers.EDTextWatcher;

public class GView
  extends RelativeLayout
{
  private GData GData;
  private Button btnSave;
  private _CTextInputLayout edCVC;
  private _CTextInputLayout edCard;
  private _CTextInputLayout edExpDate;
  private OnGPCallback gpCallback;
  private ImageView imAmex;
  private ImageView imMastercard;
  private ImageView imVisa;
  private LinearLayout llSecond;
  private boolean mValidCVC;
  private boolean mValidCard;
  private boolean mValidExpDate;
  
  public GView(Context paramContext)
  {
    super(paramContext);
    initUI();
  }
  
  public GView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initUI();
  }
  
  public GView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initUI();
  }
  
  @TargetApi(21)
  public GView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    initUI();
  }
  
  private void enableBtnSave()
  {
    int j;
    int i;
    boolean bool1;
    if ((this.mValidCard) && (this.mValidExpDate) && (this.mValidCVC))
    {
      j = 2130903049;
      i = 2130903047;
      bool1 = true;
    }
    for (boolean bool2 = true;; bool2 = false)
    {
      this.btnSave.setTextColor(ContextCompat.getColor(getContext(), j));
      this.btnSave.setBackgroundColor(ContextCompat.getColor(getContext(), i));
      this.btnSave.setClickable(bool1);
      this.btnSave.setEnabled(bool2);
      return;
      j = 2130903048;
      i = 2130903046;
      bool1 = false;
    }
  }
  
  private void initUI()
  {
    this.GData = new GData();
    Object localObject = (LayoutInflater)getContext().getSystemService("layout_inflater");
    if (localObject != null)
    {
      ((LayoutInflater)localObject).inflate(2131230721, this);
      this.edCard = ((_CTextInputLayout)findViewById(2131099676));
      localObject = (EditText)findViewById(2131099667);
      this.edCard.setEditText((EditText)localObject);
      this.edExpDate = ((_CTextInputLayout)findViewById(2131099677));
      localObject = (EditText)findViewById(2131099681);
      this.edExpDate.setEditText((EditText)localObject);
      this.edCVC = ((_CTextInputLayout)findViewById(2131099675));
      localObject = (EditText)findViewById(2131099674);
      this.edCVC.setEditText((EditText)localObject);
      this.imVisa = ((ImageView)findViewById(2131099692));
      this.imMastercard = ((ImageView)findViewById(2131099691));
      this.imAmex = ((ImageView)findViewById(2131099688));
      this.llSecond = ((LinearLayout)findViewById(2131099701));
      this.btnSave = ((Button)findViewById(2131099665));
      onEdCardChangeListener();
      onEdExpirationDateChangeListener();
      onEdCVCChangeListener();
      onBtnSaveListener();
    }
  }
  
  private void onBtnSaveListener()
  {
    this.btnSave.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (GView.this.gpCallback != null) {
          GView.this.gpCallback.onSave(GView.this.GData, false);
        }
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
          GView.access$1102(GView.this, paramAnonymousBoolean);
          if (paramAnonymousBoolean) {
            GView.this.GData.setCVC(paramAnonymousString);
          }
          GView.this.enableBtnSave();
        }
      });
    }
  }
  
  private void onEdCardChangeListener()
  {
    if (this.edCard.getEditText() != null) {
      this.edCard.getEditText().addTextChangedListener(new CNTextWatcher(this.edCard)
      {
        protected void onSetDrawable(Card paramAnonymousCard)
        {
          if (paramAnonymousCard == null)
          {
            GView.this.imAmex.setVisibility(0);
            GView.this.imVisa.setVisibility(0);
            GView.this.imMastercard.setVisibility(0);
            return;
          }
          paramAnonymousCard = paramAnonymousCard.getCardName();
          int i = -1;
          switch (paramAnonymousCard.hashCode())
          {
          }
          for (;;)
          {
            switch (i)
            {
            default: 
              return;
            case 0: 
              GView.this.imAmex.setVisibility(0);
              GView.this.imVisa.setVisibility(8);
              GView.this.imMastercard.setVisibility(8);
              return;
              if (paramAnonymousCard.equals("American Express"))
              {
                i = 0;
                continue;
                if (paramAnonymousCard.equals("Solder"))
                {
                  i = 1;
                  continue;
                  if (paramAnonymousCard.equals("Zisa")) {
                    i = 2;
                  }
                }
              }
              break;
            }
          }
          GView.this.imAmex.setVisibility(8);
          GView.this.imVisa.setVisibility(8);
          GView.this.imMastercard.setVisibility(0);
          return;
          GView.this.imAmex.setVisibility(8);
          GView.this.imVisa.setVisibility(0);
          GView.this.imMastercard.setVisibility(8);
        }
        
        protected void onValidated(boolean paramAnonymousBoolean, String paramAnonymousString1, String paramAnonymousString2)
        {
          GView.access$002(GView.this, paramAnonymousBoolean);
          if (paramAnonymousBoolean)
          {
            if (GView.this.llSecond.getVisibility() != 0) {
              GView.this.llSecond.setVisibility(0);
            }
            GView.this.edExpDate.requestFocus();
            GView.this.GData.setCardNumber(paramAnonymousString1);
            if (GView.this.gpCallback != null) {
              GView.this.gpCallback.onSave(GView.this.GData, true);
            }
          }
          for (;;)
          {
            GView.this.enableBtnSave();
            return;
            if (GView.this.llSecond.getVisibility() != 4) {
              GView.this.llSecond.setVisibility(4);
            }
          }
        }
      });
    }
  }
  
  private void onEdExpirationDateChangeListener()
  {
    if (this.edExpDate.getEditText() != null) {
      this.edExpDate.getEditText().addTextChangedListener(new EDTextWatcher(this.edExpDate)
      {
        protected void onValidated(boolean paramAnonymousBoolean, String paramAnonymousString, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          GView.access$902(GView.this, paramAnonymousBoolean);
          if (paramAnonymousBoolean)
          {
            GView.this.edCVC.requestFocus();
            GView.this.GData.setExpDate(paramAnonymousString);
          }
          GView.this.enableBtnSave();
        }
      });
    }
  }
  
  public void setGpCallback(OnGPCallback paramOnGPCallback)
  {
    this.gpCallback = paramOnGPCallback;
  }
  
  public static abstract interface OnGPCallback
  {
    public abstract void onSave(GData paramGData, boolean paramBoolean);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/view/GView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */