package com.gumsis.ping;

import android.support.annotation.NonNull;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping
{
  private InetAddress address;
  private boolean cancelled = false;
  private int delayBetweenScansMillis = 0;
  private int timeOutMillis = 1000;
  private int times = 1;
  
  public static Ping onAddress(@NonNull String paramString)
    throws UnknownHostException
  {
    Ping localPing = new Ping();
    localPing.setAddress(InetAddress.getByName(paramString));
    return localPing;
  }
  
  public static Ping onAddress(@NonNull InetAddress paramInetAddress)
  {
    Ping localPing = new Ping();
    localPing.setAddress(paramInetAddress);
    return localPing;
  }
  
  private void setAddress(InetAddress paramInetAddress)
  {
    this.address = paramInetAddress;
  }
  
  public void cancel()
  {
    this.cancelled = true;
  }
  
  public Ping doPing(final PingListener paramPingListener)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        long l2 = 0L;
        long l1 = 0L;
        float f3 = 0.0F;
        float f2 = -1.0F;
        float f1 = -1.0F;
        Ping.access$002(Ping.this, false);
        int i = Ping.this.times;
        for (;;)
        {
          long l4;
          long l3;
          float f6;
          float f4;
          float f5;
          PingResult localPingResult;
          if (i <= 0)
          {
            l4 = l2;
            l3 = l1;
            f6 = f3;
            f4 = f2;
            f5 = f1;
            if (Ping.this.times != 0) {}
          }
          else
          {
            localPingResult = PingTools.doPing(Ping.this.address, Ping.this.timeOutMillis);
            if (paramPingListener != null) {
              paramPingListener.onResult(localPingResult);
            }
            l2 += 1L;
            if (!localPingResult.hasError()) {
              break label193;
            }
            l3 = l1 + 1L;
            f5 = f1;
            f4 = f2;
            f1 = f3;
          }
          for (;;)
          {
            i -= 1;
            if (!Ping.this.cancelled) {
              break;
            }
            f6 = f1;
            l4 = l2;
            if (paramPingListener != null) {
              paramPingListener.onFinished(new PingStats(Ping.this.address, l4, l3, f6, f4, f5));
            }
            return;
            label193:
            f6 = localPingResult.getTimeTaken();
            float f7 = f3 + f6;
            if (f1 != -1.0F)
            {
              f3 = f1;
              if (f6 <= f1) {}
            }
            else
            {
              f3 = f6;
            }
            if (f2 != -1.0F)
            {
              l3 = l1;
              f1 = f7;
              f4 = f2;
              f5 = f3;
              if (f6 >= f2) {}
            }
            else
            {
              f4 = f6;
              l3 = l1;
              f1 = f7;
              f5 = f3;
            }
          }
          try
          {
            Thread.sleep(Ping.this.delayBetweenScansMillis);
            l1 = l3;
            f3 = f1;
            f2 = f4;
            f1 = f5;
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
            l1 = l3;
            f3 = f1;
            f2 = f4;
            f1 = f5;
          }
        }
      }
    }).start();
    return this;
  }
  
  public PingResult doPing()
  {
    this.cancelled = false;
    return PingTools.doPing(this.address, this.timeOutMillis);
  }
  
  public Ping setDelayMillis(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Delay cannot be less than 0");
    }
    this.delayBetweenScansMillis = paramInt;
    return this;
  }
  
  public Ping setTimeOutMillis(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Times cannot be less than 0");
    }
    this.timeOutMillis = paramInt;
    return this;
  }
  
  public Ping setTimes(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Times cannot be less than 0");
    }
    this.times = paramInt;
    return this;
  }
  
  public static abstract interface PingListener
  {
    public abstract void onFinished(PingStats paramPingStats);
    
    public abstract void onResult(PingResult paramPingResult);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/ping/Ping.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */