package com.gumsis.srs.api.responses;

import com.gumsis.api.request.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class StatusResponse
  extends HttpResponse
{
  public String answer;
  
  public void parseJson(JSONObject paramJSONObject)
  {
    super.parseBaseJson(paramJSONObject);
  }
  
  public void parseSuccessRespones(JSONObject paramJSONObject)
  {
    try
    {
      this.answer = paramJSONObject.getString("answer");
      return;
    }
    catch (JSONException paramJSONObject)
    {
      this.msg = null;
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/api/responses/StatusResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */