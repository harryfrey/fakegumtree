package com.gumsis.base.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.gumsis.checktls.AndroidUtilities;

public class RadialProgressView
  extends View
{
  private AccelerateInterpolator accelerateInterpolator;
  private RectF cicleRect = new RectF();
  private float currentCircleLength;
  private float currentProgressTime;
  private DecelerateInterpolator decelerateInterpolator;
  private long lastUpdateTime;
  private int progressColor;
  private Paint progressPaint;
  private float radOffset;
  private boolean risingCircleLength;
  private final float risingTime = 500.0F;
  private final float rotationTime = 2000.0F;
  private int size = AndroidUtilities.dp(40.0F);
  
  public RadialProgressView(Context paramContext)
  {
    super(paramContext);
    this.progressColor = AndroidUtilities.getColor(paramContext, 2130903058);
    this.decelerateInterpolator = new DecelerateInterpolator();
    this.accelerateInterpolator = new AccelerateInterpolator();
    this.progressPaint = new Paint(1);
    this.progressPaint.setStyle(Paint.Style.STROKE);
    this.progressPaint.setStrokeCap(Paint.Cap.ROUND);
    this.progressPaint.setStrokeWidth(AndroidUtilities.dp(3.0F));
    this.progressPaint.setColor(this.progressColor);
  }
  
  private void updateAnimation()
  {
    long l3 = System.currentTimeMillis();
    long l2 = l3 - this.lastUpdateTime;
    long l1 = l2;
    if (l2 > 17L) {
      l1 = 17L;
    }
    this.lastUpdateTime = l3;
    this.radOffset += (float)(360L * l1) / 2000.0F;
    int i = (int)(this.radOffset / 360.0F);
    this.radOffset -= i * 360;
    this.currentProgressTime += (float)l1;
    if (this.currentProgressTime >= 500.0F) {
      this.currentProgressTime = 500.0F;
    }
    if (this.risingCircleLength)
    {
      this.currentCircleLength = (266.0F * this.accelerateInterpolator.getInterpolation(this.currentProgressTime / 500.0F) + 4.0F);
      if (this.currentProgressTime == 500.0F)
      {
        if (this.risingCircleLength)
        {
          this.radOffset += 270.0F;
          this.currentCircleLength = -266.0F;
        }
        if (this.risingCircleLength) {
          break label225;
        }
      }
    }
    label225:
    for (boolean bool = true;; bool = false)
    {
      this.risingCircleLength = bool;
      this.currentProgressTime = 0.0F;
      invalidate();
      return;
      this.currentCircleLength = (4.0F - (1.0F - this.decelerateInterpolator.getInterpolation(this.currentProgressTime / 500.0F)) * 270.0F);
      break;
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    int i = (getMeasuredWidth() - this.size) / 2;
    int j = (getMeasuredHeight() - this.size) / 2;
    this.cicleRect.set(i, j, this.size + i, this.size + j);
    paramCanvas.drawArc(this.cicleRect, this.radOffset, this.currentCircleLength, false, this.progressPaint);
    updateAnimation();
  }
  
  public void setProgressColor(int paramInt)
  {
    this.progressColor = paramInt;
    this.progressPaint.setColor(this.progressColor);
  }
  
  public void setSize(int paramInt)
  {
    this.size = paramInt;
    invalidate();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/base/dialog/RadialProgressView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */