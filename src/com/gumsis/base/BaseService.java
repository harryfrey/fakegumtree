package com.gumsis.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class BaseService
  extends Service
{
  protected abstract IBinder baseOnBind(Intent paramIntent);
  
  public void baseOnDestroy()
  {
    try
    {
      sendBroadcast(new Intent("RestartService"));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void baseOnTaskRemoved()
  {
    try
    {
      sendBroadcast(new Intent("RestartService"));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return baseOnBind(paramIntent);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/base/BaseService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */