package com.gumsis.injs.s;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.gumsis.base.BaseActivity;

public class SSplashActivity
  extends BaseActivity
{
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131230740);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        SSplashActivity.this.startActivity(new Intent(SSplashActivity.this, SLogActivity.class));
        SSplashActivity.this.finishActivity();
      }
    }, 1000L);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/s/SSplashActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */