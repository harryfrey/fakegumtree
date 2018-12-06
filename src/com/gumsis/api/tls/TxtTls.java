package com.gumsis.api.tls;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import org.json.JSONObject;

public class TxtTls
{
  public static boolean ckIsEmpty(String paramString)
  {
    return (paramString == null) || (paramString.trim().equals("")) || (paramString.trim().equals("null"));
  }
  
  public static byte[] getBytesUTF8(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString) {}
    return null;
  }
  
  public static String getPostParamsUTF8(JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder;
    for (;;)
    {
      try
      {
        localStringBuilder = new StringBuilder();
        int i = 1;
        Iterator localIterator = paramJSONObject.keys();
        if (!localIterator.hasNext()) {
          break;
        }
        String str = (String)localIterator.next();
        Object localObject = paramJSONObject.get(str);
        if (i != 0)
        {
          i = 0;
          localStringBuilder.append(URLEncoder.encode(str, "UTF-8"));
          localStringBuilder.append("=");
          localStringBuilder.append(URLEncoder.encode(localObject.toString(), "UTF-8"));
        }
        else
        {
          localStringBuilder.append("&");
        }
      }
      catch (Exception paramJSONObject)
      {
        return "";
      }
    }
    paramJSONObject = localStringBuilder.toString();
    return paramJSONObject;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/api/tls/TxtTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */