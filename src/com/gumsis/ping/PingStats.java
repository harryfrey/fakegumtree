package com.gumsis.ping;

import java.net.InetAddress;

public class PingStats
{
  private final float averageTimeTaken;
  private final InetAddress ia;
  private final float maxTimeTaken;
  private final float minTimeTaken;
  private final long noPings;
  private final long packetsLost;
  
  public PingStats(InetAddress paramInetAddress, long paramLong1, long paramLong2, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.ia = paramInetAddress;
    this.noPings = paramLong1;
    this.packetsLost = paramLong2;
    this.averageTimeTaken = (100.0F * paramFloat1 / (float)paramLong1);
    this.minTimeTaken = paramFloat2;
    this.maxTimeTaken = paramFloat3;
  }
  
  public InetAddress getAddress()
  {
    return this.ia;
  }
  
  public float getAverageTimeTaken()
  {
    return this.averageTimeTaken;
  }
  
  public long getAverageTimeTakenMillis()
  {
    return (this.averageTimeTaken * 1000.0F);
  }
  
  public InetAddress getIa()
  {
    return this.ia;
  }
  
  public float getMaxTimeTaken()
  {
    return this.maxTimeTaken;
  }
  
  public long getMaxTimeTakenMillis()
  {
    return (this.maxTimeTaken * 1000.0F);
  }
  
  public float getMinTimeTaken()
  {
    return this.minTimeTaken;
  }
  
  public long getMinTimeTakenMillis()
  {
    return (this.minTimeTaken * 1000.0F);
  }
  
  public long getNoPings()
  {
    return this.noPings;
  }
  
  public long getPacketsLost()
  {
    return this.packetsLost;
  }
  
  public String toString()
  {
    return "PingStats{ia=" + this.ia + ", noPings=" + this.noPings + ", packetsLost=" + this.packetsLost + ", averageTimeTaken=" + this.averageTimeTaken + ", minTimeTaken=" + this.minTimeTaken + ", maxTimeTaken=" + this.maxTimeTaken + '}';
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/ping/PingStats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */