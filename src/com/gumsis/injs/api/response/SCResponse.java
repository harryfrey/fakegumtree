package com.gumsis.injs.api.response;

import com.gumsis.api.request.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class SCResponse
  extends HttpResponse
{
  public String answer;
  
  public void parseJson(JSONObject paramJSONObject)
  {
    super.parseBaseJson(paramJSONObject);
  }
  
  protected void parseSuccessRespones(JSONObject paramJSONObject)
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


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/api/response/SCResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */