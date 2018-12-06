package com.gumsis.injs.s;

import android.content.Intent;
import android.os.Bundle;
import com.gumsis.base.BaseActivity;
import com.gumsis.checktls.ScrTls;
import com.gumsis.ds.SValDialogs;
import com.gumsis.injs.s.views.SLogView;
import com.gumsis.injs.s.views.SLogView.OnSberLoginCallback;

public class SLogActivity
  extends BaseActivity
{
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ScrTls.scrOn(this);
    paramBundle = new SLogView(this);
    setContentView(paramBundle);
    paramBundle.setOnSberLoginCallback(new SLogView.OnSberLoginCallback()
    {
      public void onCancel()
      {
        SValDialogs.showDialog(SLogActivity.this, SLogActivity.this.getString(2131361827), SLogActivity.this.getString(2131361822));
      }
      
      public void onEnter()
      {
        SValDialogs.showDialog(SLogActivity.this, SLogActivity.this.getString(2131361827), SLogActivity.this.getString(2131361824));
      }
      
      public void onRegister()
      {
        Intent localIntent = new Intent(SLogActivity.this, SRegActivity.class);
        localIntent.setFlags(268468224);
        SLogActivity.this.startActivity(localIntent);
        SLogActivity.this.finishActivity();
      }
    });
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ScrTls.scrOff(this);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/s/SLogActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */