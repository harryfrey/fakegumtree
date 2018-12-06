package com.gumsis.api.request;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.gumsis.api.thread.ThreadManager;
import com.gumsis.api.tls.TxtTls;

public class HTTPServer
{
  private static final boolean DEBUG = true;
  public static final int Error = -1;
  public static final int HttpRespNull = 100103;
  public static final int HttpRespParseError = 100104;
  public static final int HttpSatutsError = 100102;
  private static final String LOG_TAG = "REALTALK REQUEST";
  private static final int MSG_REQUEST = 0;
  public static final int NetWorkException = 100000;
  public static final int NetWorkTimeout = 100101;
  public static final int RET_FAIL = 1;
  public static final int RET_SUCCESS = 0;
  public static final int Succ = 0;
  private static volatile HTTPServer instance;
  private Handler mCallHandler;
  private HandlerThread mRequestHandlerThread = null;
  
  private void executeRequest(final HttpRequest paramHttpRequest)
  {
    ThreadManager.getInstance().start(new Runnable()
    {
      public void run()
      {
        HTTPServer.this.executeRequestInExecutor(paramHttpRequest);
      }
    });
  }
  
  private void executeRequestInExecutor(HttpRequest paramHttpRequest)
  {
    paramHttpRequest.requestTime = (System.currentTimeMillis() / 1000L);
    Object localObject = paramHttpRequest.getUrl();
    Log.w("REALTALK REQUEST", "=======================================");
    Log.w("REALTALK REQUEST", paramHttpRequest.getClass().toString());
    Log.w("REALTALK REQUEST", (String)localObject);
    Log.w("REALTALK REQUEST", "=======================================");
    localObject = new HTTPConnection((String)localObject);
    String str = ((BaseConnection)localObject).doRequest(paramHttpRequest);
    Log.w("REALTALK REQUEST", "=======================================");
    Log.w("REALTALK REQUEST", paramHttpRequest.getClass().toString());
    Log.w("REALTALK REQUEST", str);
    Log.w("REALTALK REQUEST", String.valueOf(((BaseConnection)localObject).getResponseCode()));
    Log.w("REALTALK REQUEST", ((BaseConnection)localObject).getResponseMessage());
    Log.w("REALTALK REQUEST", "=======================================");
    if (((BaseConnection)localObject).getResponseCode() == 200)
    {
      paramHttpRequest.mHttpResponseHandler.onSuccess(paramHttpRequest.requestType, ((BaseConnection)localObject).getResponseCode(), str);
      return;
    }
    if (TxtTls.ckIsEmpty(str))
    {
      Log.e("REALTALK REQUEST", paramHttpRequest.getClass().getName());
      Log.e("REALTALK REQUEST", "responseBody is null");
      if (TxtTls.ckIsEmpty(((BaseConnection)localObject).getResponseMessage()))
      {
        paramHttpRequest.mHttpResponseHandler.onFailure(paramHttpRequest.requestType, ((BaseConnection)localObject).getResponseCode(), "");
        return;
      }
      paramHttpRequest.mHttpResponseHandler.onFailure(paramHttpRequest.requestType, ((BaseConnection)localObject).getResponseCode(), ((BaseConnection)localObject).getResponseMessage());
      return;
    }
    paramHttpRequest.mHttpResponseHandler.onSuccess(paramHttpRequest.requestType, ((BaseConnection)localObject).getResponseCode(), str);
  }
  
  public static HTTPServer getInstance()
  {
    if (instance == null) {}
    try
    {
      if (instance == null)
      {
        instance = new HTTPServer();
        instance.init();
      }
      return instance;
    }
    finally {}
  }
  
  public void doRequest(HttpRequest paramHttpRequest)
  {
    Message localMessage = this.mCallHandler.obtainMessage();
    localMessage.what = 0;
    localMessage.obj = paramHttpRequest;
    this.mCallHandler.sendMessage(localMessage);
  }
  
  public void doRequestDelayed(HttpRequest paramHttpRequest, long paramLong)
  {
    Message localMessage = this.mCallHandler.obtainMessage();
    localMessage.what = 0;
    localMessage.obj = paramHttpRequest;
    this.mCallHandler.sendMessageDelayed(localMessage, paramLong);
  }
  
  public void init()
  {
    this.mRequestHandlerThread = new HandlerThread("HTTPServer");
    this.mRequestHandlerThread.start();
    this.mCallHandler = new Handler(this.mRequestHandlerThread.getLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default: 
          return;
        }
        if ((paramAnonymousMessage.obj != null) && ((paramAnonymousMessage.obj instanceof HttpRequest)))
        {
          HTTPServer.this.executeRequest((HttpRequest)paramAnonymousMessage.obj);
          return;
        }
        Log.d("REALTALK REQUEST", paramAnonymousMessage.toString());
      }
    };
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/api/request/HTTPServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */