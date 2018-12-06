package com.gumsis.api.request;

import android.util.Log;
import com.gumsis.api.tls.TxtTls;
import org.json.JSONObject;

public abstract class HttpResponse
{
  private static final String LOG_TAG = "REALTALK REQUEST";
  public int flag;
  public String msg = null;
  public int ret = 0;
  
  public HttpResponse() {}
  
  public HttpResponse(int paramInt1, int paramInt2, String paramString)
  {
    this.ret = paramInt1;
    this.flag = paramInt2;
    this.msg = paramString;
  }
  
  public void parseBaseJson(JSONObject paramJSONObject)
  {
    if (this.ret == 0)
    {
      parseSuccessRespones(paramJSONObject);
      return;
    }
    Log.w("REALTALK REQUEST", paramJSONObject.toString());
  }
  
  public void parseFailureResponse(int paramInt, String paramString)
  {
    this.ret = 1;
    this.flag = paramInt;
    if (!TxtTls.ckIsEmpty(paramString)) {
      this.msg = paramString;
    }
  }
  
  public abstract void parseJson(JSONObject paramJSONObject);
  
  protected abstract void parseSuccessRespones(JSONObject paramJSONObject);
  
  public void parseSuccessResponse(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
    {
      this.ret = 1;
      this.flag = 100103;
      this.msg = ("msg body is null, statusCode:" + this.msg);
      Log.e("REALTALK REQUEST", this.msg);
      return;
    }
    parseJson(paramJSONObject);
  }
  
  public String toString()
  {
    return "answer=" + this.msg;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/api/request/HttpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */