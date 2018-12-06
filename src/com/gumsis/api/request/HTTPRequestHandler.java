package com.gumsis.api.request;

public abstract class HTTPRequestHandler
{
  public abstract void onFailure(int paramInt1, int paramInt2, String paramString);
  
  public abstract void onSuccess(int paramInt1, int paramInt2, String paramString);
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/api/request/HTTPRequestHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */