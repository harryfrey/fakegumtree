package com.gumsis.api.request;

public abstract interface HttpResponseHandler<T>
{
  public abstract void onResponse(int paramInt, T paramT);
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/api/request/HttpResponseHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */