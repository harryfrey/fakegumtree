package com.gumsis.api.request;

import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection
  extends BaseConnection
{
  private HttpURLConnection mConn = null;
  
  public HTTPConnection(String paramString)
  {
    try
    {
      this.mConn = ((HttpURLConnection)new URL(paramString).openConnection());
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  protected HttpURLConnection getURLConnection()
  {
    return this.mConn;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/api/request/HTTPConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */