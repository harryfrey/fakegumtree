package com.gumsis.api.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadManager
{
  private static final String EXECUTOR_NAME_TEMP = "TEMP_THREADS";
  public static final int LOOPER_TYPE_ANDROID_MAIN = 0;
  public static final int LOOPER_TYPE_BG = 1;
  public static final int LOOPER_TYPE_REQUEST = 2;
  public static final int LOOPER_TYPE_TEMP = 3;
  private static final int MAX_RUNNING_THREAD = 3;
  private static final String THREAD_NAME_BG = "BG";
  private static final String THREAD_NAME_REQUEST = "REQUEST";
  private static final String THREAD_NAME_TEMP = "TEMP";
  private static volatile ThreadManager instance;
  private ExecutorService executor;
  private HandlerThread mBgHandlerThread = null;
  private Handler mPostDelayTempHandler;
  private HandlerThread mRequestHandlerThread = null;
  private HandlerThread mTempHandlerThread = null;
  
  public static ThreadManager getInstance()
  {
    if (instance == null) {}
    try
    {
      if (instance == null) {
        instance = new ThreadManager();
      }
      return instance;
    }
    finally {}
  }
  
  public Looper getLooper(int paramInt)
  {
    if (paramInt == 0) {
      return Looper.getMainLooper();
    }
    if (paramInt == 1)
    {
      if (this.mBgHandlerThread == null)
      {
        this.mBgHandlerThread = new HandlerThread("BG");
        this.mBgHandlerThread.start();
      }
      return this.mBgHandlerThread.getLooper();
    }
    if (paramInt == 2)
    {
      if (this.mRequestHandlerThread == null)
      {
        this.mRequestHandlerThread = new HandlerThread("REQUEST");
        this.mRequestHandlerThread.start();
      }
      return this.mRequestHandlerThread.getLooper();
    }
    if (this.mTempHandlerThread == null)
    {
      this.mTempHandlerThread = new HandlerThread("TEMP");
      this.mTempHandlerThread.start();
    }
    return this.mTempHandlerThread.getLooper();
  }
  
  public void runOnUIThread(Runnable paramRunnable)
  {
    try
    {
      new Handler(Looper.getMainLooper()).post(paramRunnable);
      return;
    }
    catch (Exception paramRunnable)
    {
      paramRunnable.printStackTrace();
    }
  }
  
  public void start(Runnable paramRunnable)
  {
    if (this.executor == null) {}
    try
    {
      this.executor = Executors.newFixedThreadPool(3, new CommonThreadFactory());
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        try
        {
          this.executor.submit(paramRunnable);
          return;
        }
        catch (Throwable paramRunnable)
        {
          paramRunnable.printStackTrace();
        }
        localThrowable = localThrowable;
        this.executor = Executors.newCachedThreadPool(new CommonThreadFactory());
      }
    }
  }
  
  public void start(Runnable paramRunnable, int paramInt)
  {
    if (this.mPostDelayTempHandler == null) {
      this.mPostDelayTempHandler = new Handler(getLooper(3));
    }
    this.mPostDelayTempHandler.postDelayed(paramRunnable, paramInt * 1000);
  }
  
  private class CommonThreadFactory
    implements ThreadFactory
  {
    private final ThreadGroup group;
    private final String namePrefix;
    private final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    
    public CommonThreadFactory()
    {
      this$1 = System.getSecurityManager();
      if (ThreadManager.this != null) {}
      for (this$1 = ThreadManager.this.getThreadGroup();; this$1 = Thread.currentThread().getThreadGroup())
      {
        this.group = ThreadManager.this;
        this.namePrefix = ("TEMP_THREADS- pool-" + this.poolNumber.getAndIncrement());
        return;
      }
    }
    
    public Thread newThread(Runnable paramRunnable)
    {
      paramRunnable = new Thread(this.group, paramRunnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
      if (paramRunnable.isDaemon()) {
        paramRunnable.setDaemon(false);
      }
      if (paramRunnable.getPriority() != 1) {
        paramRunnable.setPriority(1);
      }
      return paramRunnable;
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/api/thread/ThreadManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */