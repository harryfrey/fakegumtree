package com.gumsis.ping;

import android.text.TextUtils;
import java.net.InetAddress;

public class PingResult
{
  public String error = null;
  public String fullString;
  public final InetAddress ia;
  public boolean isReachable;
  public String result;
  public float timeTaken;
  
  public PingResult(InetAddress paramInetAddress)
  {
    this.ia = paramInetAddress;
  }
  
  public InetAddress getAddress()
  {
    return this.ia;
  }
  
  public String getError()
  {
    return this.error;
  }
  
  public float getTimeTaken()
  {
    return this.timeTaken;
  }
  
  public boolean hasError()
  {
    return !TextUtils.isEmpty(this.error);
  }
  
  public boolean isReachable()
  {
    return this.isReachable;
  }
  
  public String toString()
  {
    return "PingResult{ia=" + this.ia + ", isReachable=" + this.isReachable + ", error='" + this.error + '\'' + ", timeTaken=" + this.timeTaken + ", fullString='" + this.fullString + '\'' + ", result='" + this.result + '\'' + '}';
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/ping/PingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */