package com.gumsis.base.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.StateSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.gumsis.checktls.AndroidUtilities;
import com.gumsis.checktls.Print;

public class AlertDialog
  extends Dialog
  implements Drawable.Callback
{
  private Rect backgroundPaddings = new Rect();
  private FrameLayout buttonsLayout;
  private ScrollView contentScrollView;
  private int currentProgress;
  private View customView;
  private int[] itemIcons;
  private CharSequence[] items;
  private int lastScreenWidth;
  private LineProgressView lineProgressView;
  private TextView lineProgressViewPercent;
  private CharSequence message;
  private TextView messageTextView;
  private DialogInterface.OnClickListener negativeButtonListener;
  private CharSequence negativeButtonText;
  private DialogInterface.OnClickListener neutralButtonListener;
  private CharSequence neutralButtonText;
  private DialogInterface.OnClickListener onClickListener;
  private DialogInterface.OnDismissListener onDismissListener;
  private ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
  private DialogInterface.OnClickListener positiveButtonListener;
  private CharSequence positiveButtonText;
  private FrameLayout progressViewContainer;
  private int progressViewStyle;
  private TextView progressViewTextView;
  private LinearLayout scrollContainer;
  private BitmapDrawable[] shadow = new BitmapDrawable[2];
  private AnimatorSet[] shadowAnimation = new AnimatorSet[2];
  private Drawable shadowDrawable;
  private boolean[] shadowVisibility = new boolean[2];
  private CharSequence subtitle;
  private TextView subtitleTextView;
  private CharSequence title;
  private TextView titleTextView;
  private int topBackgroundColor;
  private ImageView topImageView;
  private int topResId;
  
  public AlertDialog(Context paramContext, int paramInt)
  {
    super(paramContext, 2131427340);
    this.shadowDrawable = paramContext.getResources().getDrawable(2131296259).mutate();
    this.shadowDrawable.setColorFilter(new PorterDuffColorFilter(AndroidUtilities.getColor(paramContext, 17170443), PorterDuff.Mode.MULTIPLY));
    this.shadowDrawable.getPadding(this.backgroundPaddings);
    this.progressViewStyle = paramInt;
  }
  
  private boolean canTextInput(View paramView)
  {
    if (paramView.onCheckIsTextEditor()) {
      return true;
    }
    if (!(paramView instanceof ViewGroup)) {
      return false;
    }
    paramView = (ViewGroup)paramView;
    int i = paramView.getChildCount();
    while (i > 0)
    {
      int j = i - 1;
      i = j;
      if (canTextInput(paramView.getChildAt(j))) {
        return true;
      }
    }
    return false;
  }
  
  public static Drawable createRoundRectDrawable(int paramInt1, int paramInt2)
  {
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new RoundRectShape(new float[] { paramInt1, paramInt1, paramInt1, paramInt1, paramInt1, paramInt1, paramInt1, paramInt1 }, null, null));
    localShapeDrawable.getPaint().setColor(paramInt2);
    return localShapeDrawable;
  }
  
  private static Drawable getRoundRectSelectorDrawable(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      localDrawable = createRoundRectDrawable(AndroidUtilities.dp(3.0F), -1);
      int[] arrayOfInt = StateSet.WILD_CARD;
      int i = AndroidUtilities.getColor(paramContext, 17170444);
      return new RippleDrawable(new ColorStateList(new int[][] { arrayOfInt }, new int[] { i }), null, localDrawable);
    }
    paramContext = new StateListDrawable();
    Drawable localDrawable = createRoundRectDrawable(AndroidUtilities.dp(3.0F), 251658240);
    paramContext.addState(new int[] { 16842919 }, localDrawable);
    localDrawable = createRoundRectDrawable(AndroidUtilities.dp(3.0F), 251658240);
    paramContext.addState(new int[] { 16842913 }, localDrawable);
    paramContext.addState(StateSet.WILD_CARD, new ColorDrawable(0));
    return paramContext;
  }
  
  private void runShadowAnimation(final int paramInt, boolean paramBoolean)
  {
    AnimatorSet localAnimatorSet;
    BitmapDrawable localBitmapDrawable;
    if (((paramBoolean) && (this.shadowVisibility[paramInt] == 0)) || ((!paramBoolean) && (this.shadowVisibility[paramInt] != 0)))
    {
      this.shadowVisibility[paramInt] = paramBoolean;
      if (this.shadowAnimation[paramInt] != null) {
        this.shadowAnimation[paramInt].cancel();
      }
      this.shadowAnimation[paramInt] = new AnimatorSet();
      if (this.shadow[paramInt] != null)
      {
        localAnimatorSet = this.shadowAnimation[paramInt];
        localBitmapDrawable = this.shadow[paramInt];
        if (!paramBoolean) {
          break label165;
        }
      }
    }
    for (int i = 255;; i = 0)
    {
      localAnimatorSet.playTogether(new Animator[] { ObjectAnimator.ofInt(localBitmapDrawable, "alpha", new int[] { i }) });
      this.shadowAnimation[paramInt].setDuration(150L);
      this.shadowAnimation[paramInt].addListener(new AnimatorListenerAdapter()
      {
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
          if ((AlertDialog.this.shadowAnimation[paramInt] != null) && (AlertDialog.this.shadowAnimation[paramInt].equals(paramAnonymousAnimator))) {
            AlertDialog.this.shadowAnimation[paramInt] = null;
          }
        }
        
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          if ((AlertDialog.this.shadowAnimation[paramInt] != null) && (AlertDialog.this.shadowAnimation[paramInt].equals(paramAnonymousAnimator))) {
            AlertDialog.this.shadowAnimation[paramInt] = null;
          }
        }
      });
      try
      {
        this.shadowAnimation[paramInt].start();
        return;
      }
      catch (Exception localException)
      {
        label165:
        Print.e(localException.toString());
      }
    }
  }
  
  private void updateLineProgressTextView()
  {
    this.lineProgressViewPercent.setText(String.format("%e%%", new Object[] { Integer.valueOf(this.currentProgress) }));
  }
  
  public void dismiss()
  {
    super.dismiss();
  }
  
  public View getButton(int paramInt)
  {
    return this.buttonsLayout.findViewWithTag(Integer.valueOf(paramInt));
  }
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    this.contentScrollView.invalidate();
    this.scrollContainer.invalidate();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = new LinearLayout(getContext())
    {
      private boolean inLayout;
      
      public boolean hasOverlappingRendering()
      {
        return false;
      }
      
      protected void onLayout(boolean paramAnonymousBoolean, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        super.onLayout(paramAnonymousBoolean, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3, paramAnonymousInt4);
        if (AlertDialog.this.contentScrollView != null)
        {
          if (AlertDialog.this.onScrollChangedListener == null)
          {
            AlertDialog.access$1402(AlertDialog.this, new ViewTreeObserver.OnScrollChangedListener()
            {
              public void onScrollChanged()
              {
                boolean bool2 = false;
                AlertDialog localAlertDialog = AlertDialog.this;
                if ((AlertDialog.this.titleTextView != null) && (AlertDialog.this.contentScrollView.getScrollY() > AlertDialog.this.scrollContainer.getTop())) {}
                for (boolean bool1 = true;; bool1 = false)
                {
                  localAlertDialog.runShadowAnimation(0, bool1);
                  localAlertDialog = AlertDialog.this;
                  bool1 = bool2;
                  if (AlertDialog.this.buttonsLayout != null)
                  {
                    bool1 = bool2;
                    if (AlertDialog.this.contentScrollView.getScrollY() + AlertDialog.this.contentScrollView.getHeight() < AlertDialog.this.scrollContainer.getBottom()) {
                      bool1 = true;
                    }
                  }
                  localAlertDialog.runShadowAnimation(1, bool1);
                  AlertDialog.this.contentScrollView.invalidate();
                  return;
                }
              }
            });
            AlertDialog.this.contentScrollView.getViewTreeObserver().addOnScrollChangedListener(AlertDialog.this.onScrollChangedListener);
          }
          AlertDialog.this.onScrollChangedListener.onScrollChanged();
        }
      }
      
      protected void onMeasure(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        this.inLayout = true;
        int k = View.MeasureSpec.getSize(paramAnonymousInt1);
        int j = View.MeasureSpec.getSize(paramAnonymousInt2) - getPaddingTop() - getPaddingBottom();
        int i = j;
        int i1 = k - getPaddingLeft() - getPaddingRight();
        int n = View.MeasureSpec.makeMeasureSpec(i1 - AndroidUtilities.dp(48.0F), 1073741824);
        int m = View.MeasureSpec.makeMeasureSpec(i1, 1073741824);
        paramAnonymousInt1 = i;
        LinearLayout.LayoutParams localLayoutParams;
        if (AlertDialog.this.buttonsLayout != null)
        {
          int i2 = AlertDialog.this.buttonsLayout.getChildCount();
          paramAnonymousInt1 = 0;
          while (paramAnonymousInt1 < i2)
          {
            ((TextView)AlertDialog.this.buttonsLayout.getChildAt(paramAnonymousInt1)).setMaxWidth(AndroidUtilities.dp((i1 - AndroidUtilities.dp(24.0F)) / 2));
            paramAnonymousInt1 += 1;
          }
          AlertDialog.this.buttonsLayout.measure(m, paramAnonymousInt2);
          localLayoutParams = (LinearLayout.LayoutParams)AlertDialog.this.buttonsLayout.getLayoutParams();
          paramAnonymousInt1 = i - (AlertDialog.this.buttonsLayout.getMeasuredHeight() + localLayoutParams.bottomMargin + localLayoutParams.topMargin);
        }
        i = paramAnonymousInt1;
        if (AlertDialog.this.titleTextView != null)
        {
          AlertDialog.this.titleTextView.measure(n, paramAnonymousInt2);
          localLayoutParams = (LinearLayout.LayoutParams)AlertDialog.this.titleTextView.getLayoutParams();
          i = paramAnonymousInt1 - (AlertDialog.this.titleTextView.getMeasuredHeight() + localLayoutParams.bottomMargin + localLayoutParams.topMargin);
        }
        paramAnonymousInt1 = i;
        if (AlertDialog.this.subtitleTextView != null)
        {
          AlertDialog.this.subtitleTextView.measure(n, paramAnonymousInt2);
          localLayoutParams = (LinearLayout.LayoutParams)AlertDialog.this.subtitleTextView.getLayoutParams();
          paramAnonymousInt1 = i - (AlertDialog.this.subtitleTextView.getMeasuredHeight() + localLayoutParams.bottomMargin + localLayoutParams.topMargin);
        }
        paramAnonymousInt2 = paramAnonymousInt1;
        if (AlertDialog.this.topImageView != null)
        {
          AlertDialog.this.topImageView.measure(View.MeasureSpec.makeMeasureSpec(k, 1073741824), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(132.0F), 1073741824));
          paramAnonymousInt2 = paramAnonymousInt1 - (AlertDialog.this.topImageView.getMeasuredHeight() - AndroidUtilities.dp(8.0F));
        }
        if (AlertDialog.this.progressViewStyle == 0)
        {
          localLayoutParams = (LinearLayout.LayoutParams)AlertDialog.this.contentScrollView.getLayoutParams();
          if (AlertDialog.this.customView != null) {
            if ((AlertDialog.this.titleTextView == null) && (AlertDialog.this.messageTextView.getVisibility() == 8) && (AlertDialog.this.items == null))
            {
              paramAnonymousInt1 = AndroidUtilities.dp(16.0F);
              localLayoutParams.topMargin = paramAnonymousInt1;
              if (AlertDialog.this.buttonsLayout != null) {
                break label586;
              }
              paramAnonymousInt1 = AndroidUtilities.dp(8.0F);
              label477:
              localLayoutParams.bottomMargin = paramAnonymousInt1;
            }
          }
          label586:
          do
          {
            paramAnonymousInt1 = paramAnonymousInt2 - (localLayoutParams.bottomMargin + localLayoutParams.topMargin);
            AlertDialog.this.contentScrollView.measure(m, View.MeasureSpec.makeMeasureSpec(paramAnonymousInt1, Integer.MIN_VALUE));
            paramAnonymousInt2 = paramAnonymousInt1 - AlertDialog.this.contentScrollView.getMeasuredHeight();
            setMeasuredDimension(k, j - paramAnonymousInt2 + getPaddingTop() + getPaddingBottom());
            this.inLayout = false;
            if (AlertDialog.this.lastScreenWidth != AndroidUtilities.displaySize.x) {
              AndroidUtilities.runOnUIThread(new Runnable()
              {
                public void run()
                {
                  AlertDialog.access$1202(AlertDialog.this, AndroidUtilities.displaySize.x);
                  int j = AndroidUtilities.displaySize.x;
                  int k = AndroidUtilities.dp(56.0F);
                  int i;
                  if (AndroidUtilities.isTablet()) {
                    if (AndroidUtilities.isSmallTablet()) {
                      i = AndroidUtilities.dp(446.0F);
                    }
                  }
                  for (;;)
                  {
                    Window localWindow = AlertDialog.this.getWindow();
                    WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
                    localLayoutParams.copyFrom(localWindow.getAttributes());
                    localLayoutParams.width = (Math.min(i, j - k) + AlertDialog.this.backgroundPaddings.left + AlertDialog.this.backgroundPaddings.right);
                    localWindow.setAttributes(localLayoutParams);
                    return;
                    i = AndroidUtilities.dp(496.0F);
                    continue;
                    i = AndroidUtilities.dp(356.0F);
                  }
                }
              });
            }
            return;
            paramAnonymousInt1 = 0;
            break;
            paramAnonymousInt1 = 0;
            break label477;
            if (AlertDialog.this.items != null)
            {
              if ((AlertDialog.this.titleTextView == null) && (AlertDialog.this.messageTextView.getVisibility() == 8)) {}
              for (paramAnonymousInt1 = AndroidUtilities.dp(8.0F);; paramAnonymousInt1 = 0)
              {
                localLayoutParams.topMargin = paramAnonymousInt1;
                localLayoutParams.bottomMargin = AndroidUtilities.dp(8.0F);
                break;
              }
            }
          } while (AlertDialog.this.messageTextView.getVisibility() != 0);
          if (AlertDialog.this.titleTextView == null) {}
          for (paramAnonymousInt1 = AndroidUtilities.dp(19.0F);; paramAnonymousInt1 = 0)
          {
            localLayoutParams.topMargin = paramAnonymousInt1;
            localLayoutParams.bottomMargin = AndroidUtilities.dp(20.0F);
            break;
          }
        }
        if (AlertDialog.this.progressViewContainer != null)
        {
          AlertDialog.this.progressViewContainer.measure(n, View.MeasureSpec.makeMeasureSpec(paramAnonymousInt2, Integer.MIN_VALUE));
          localLayoutParams = (LinearLayout.LayoutParams)AlertDialog.this.progressViewContainer.getLayoutParams();
          paramAnonymousInt1 = paramAnonymousInt2 - (AlertDialog.this.progressViewContainer.getMeasuredHeight() + localLayoutParams.bottomMargin + localLayoutParams.topMargin);
        }
        for (;;)
        {
          paramAnonymousInt2 = paramAnonymousInt1;
          if (AlertDialog.this.lineProgressView == null) {
            break;
          }
          AlertDialog.this.lineProgressView.measure(n, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(4.0F), 1073741824));
          localLayoutParams = (LinearLayout.LayoutParams)AlertDialog.this.lineProgressView.getLayoutParams();
          paramAnonymousInt1 -= AlertDialog.this.lineProgressView.getMeasuredHeight() + localLayoutParams.bottomMargin + localLayoutParams.topMargin;
          AlertDialog.this.lineProgressViewPercent.measure(n, View.MeasureSpec.makeMeasureSpec(paramAnonymousInt1, Integer.MIN_VALUE));
          localLayoutParams = (LinearLayout.LayoutParams)AlertDialog.this.lineProgressViewPercent.getLayoutParams();
          paramAnonymousInt2 = paramAnonymousInt1 - (AlertDialog.this.lineProgressViewPercent.getMeasuredHeight() + localLayoutParams.bottomMargin + localLayoutParams.topMargin);
          break;
          paramAnonymousInt1 = paramAnonymousInt2;
          if (AlertDialog.this.messageTextView != null)
          {
            AlertDialog.this.messageTextView.measure(n, View.MeasureSpec.makeMeasureSpec(paramAnonymousInt2, Integer.MIN_VALUE));
            paramAnonymousInt1 = paramAnonymousInt2;
            if (AlertDialog.this.messageTextView.getVisibility() != 8)
            {
              localLayoutParams = (LinearLayout.LayoutParams)AlertDialog.this.messageTextView.getLayoutParams();
              paramAnonymousInt1 = paramAnonymousInt2 - (AlertDialog.this.messageTextView.getMeasuredHeight() + localLayoutParams.bottomMargin + localLayoutParams.topMargin);
            }
          }
        }
      }
      
      public void requestLayout()
      {
        if (this.inLayout) {
          return;
        }
        super.requestLayout();
      }
    };
    paramBundle.setOrientation(1);
    paramBundle.setBackgroundDrawable(this.shadowDrawable);
    boolean bool;
    label76:
    Object localObject1;
    int i;
    label282:
    label390:
    label702:
    Object localObject2;
    if (Build.VERSION.SDK_INT >= 21)
    {
      bool = true;
      paramBundle.setFitsSystemWindows(bool);
      setContentView(paramBundle);
      if ((this.positiveButtonText == null) && (this.negativeButtonText == null) && (this.neutralButtonText == null)) {
        break label895;
      }
      j = 1;
      if (this.topResId != 0)
      {
        this.topImageView = new ImageView(getContext());
        this.topImageView.setImageResource(this.topResId);
        this.topImageView.setScaleType(ImageView.ScaleType.CENTER);
        this.topImageView.setBackgroundDrawable(getContext().getResources().getDrawable(2131296260));
        this.topImageView.getBackground().setColorFilter(new PorterDuffColorFilter(this.topBackgroundColor, PorterDuff.Mode.MULTIPLY));
        this.topImageView.setPadding(0, 0, 0, 0);
        paramBundle.addView(this.topImageView, LayoutHelper.createLinear(-1, 132, 51, -8, -8, 0, 0));
      }
      if (this.title != null)
      {
        this.titleTextView = new TextView(getContext());
        this.titleTextView.setText(this.title);
        this.titleTextView.setTextColor(AndroidUtilities.getColor(getContext(), 17170444));
        this.titleTextView.setTextSize(1, 20.0F);
        this.titleTextView.setGravity(51);
        localObject1 = this.titleTextView;
        if (this.subtitle == null) {
          break label900;
        }
        i = 2;
        paramBundle.addView((View)localObject1, LayoutHelper.createLinear(-2, -2, 51, 24, 19, 24, i));
      }
      if (this.subtitle != null)
      {
        this.subtitleTextView = new TextView(getContext());
        this.subtitleTextView.setText(this.subtitle);
        this.subtitleTextView.setTextColor(AndroidUtilities.getColor(getContext(), 17170444));
        this.subtitleTextView.setTextSize(1, 14.0F);
        this.subtitleTextView.setGravity(51);
        localObject1 = this.subtitleTextView;
        if (this.items == null) {
          break label919;
        }
        i = 14;
        paramBundle.addView((View)localObject1, LayoutHelper.createLinear(-2, -2, 51, 24, 0, 24, i));
      }
      if (this.progressViewStyle == 0)
      {
        this.shadow[0] = ((BitmapDrawable)getContext().getResources().getDrawable(2131296256).mutate());
        this.shadow[1] = ((BitmapDrawable)getContext().getResources().getDrawable(2131296257).mutate());
        this.shadow[0].setAlpha(0);
        this.shadow[1].setAlpha(0);
        this.shadow[0].setCallback(this);
        this.shadow[1].setCallback(this);
        this.contentScrollView = new ScrollView(getContext())
        {
          protected boolean drawChild(Canvas paramAnonymousCanvas, View paramAnonymousView, long paramAnonymousLong)
          {
            boolean bool = super.drawChild(paramAnonymousCanvas, paramAnonymousView, paramAnonymousLong);
            if (AlertDialog.this.shadow[0].getPaint().getAlpha() != 0)
            {
              AlertDialog.this.shadow[0].setBounds(0, getScrollY(), getMeasuredWidth(), getScrollY() + AndroidUtilities.dp(3.0F));
              AlertDialog.this.shadow[0].draw(paramAnonymousCanvas);
            }
            if (AlertDialog.this.shadow[1].getPaint().getAlpha() != 0)
            {
              AlertDialog.this.shadow[1].setBounds(0, getScrollY() + getMeasuredHeight() - AndroidUtilities.dp(3.0F), getMeasuredWidth(), getScrollY() + getMeasuredHeight());
              AlertDialog.this.shadow[1].draw(paramAnonymousCanvas);
            }
            return bool;
          }
        };
        this.contentScrollView.setVerticalScrollBarEnabled(false);
        AndroidUtilities.setScrollViewEdgeEffectColor(this.contentScrollView, AndroidUtilities.getColor(getContext(), 2130903044));
        paramBundle.addView(this.contentScrollView, LayoutHelper.createLinear(-1, -2, 0.0F, 0.0F, 0.0F, 0.0F));
        this.scrollContainer = new LinearLayout(getContext());
        this.scrollContainer.setOrientation(1);
        this.contentScrollView.addView(this.scrollContainer, new FrameLayout.LayoutParams(-1, -2));
      }
      this.messageTextView = new TextView(getContext());
      this.messageTextView.setTextColor(AndroidUtilities.getColor(getContext(), 17170444));
      this.messageTextView.setTextSize(1, 16.0F);
      this.messageTextView.setGravity(51);
      if (this.progressViewStyle != 1) {
        break label930;
      }
      this.progressViewContainer = new FrameLayout(getContext());
      localObject1 = this.progressViewContainer;
      if (this.title != null) {
        break label925;
      }
      i = 24;
      paramBundle.addView((View)localObject1, LayoutHelper.createLinear(-1, 44, 51, 23, i, 23, 24));
      localObject1 = new RadialProgressView(getContext());
      ((RadialProgressView)localObject1).setProgressColor(AndroidUtilities.getColor(getContext(), 2130903043));
      localObject2 = LayoutHelper.createFrame(44, 44, 51);
      this.progressViewContainer.addView((View)localObject1, (ViewGroup.LayoutParams)localObject2);
      this.messageTextView.setLines(1);
      this.messageTextView.setSingleLine(true);
      this.messageTextView.setEllipsize(TextUtils.TruncateAt.END);
      this.progressViewContainer.addView(this.messageTextView, LayoutHelper.createFrame(-2, -2.0F, 19, 62.0F, 0.0F, 0.0F, 0.0F));
      if (TextUtils.isEmpty(this.message)) {
        break label1210;
      }
      this.messageTextView.setText(this.message);
      this.messageTextView.setVisibility(0);
    }
    for (;;)
    {
      label855:
      if (this.items != null)
      {
        i = 0;
        for (;;)
        {
          if (i < this.items.length)
          {
            if (this.items[i] == null)
            {
              i += 1;
              continue;
              bool = false;
              break;
              label895:
              j = 0;
              break label76;
              label900:
              if (this.items != null)
              {
                i = 14;
                break label282;
              }
              i = 10;
              break label282;
              label919:
              i = 10;
              break label390;
              label925:
              i = 0;
              break label702;
              label930:
              if (this.progressViewStyle == 2)
              {
                localObject1 = this.messageTextView;
                if (this.title == null) {}
                for (i = 19;; i = 0)
                {
                  paramBundle.addView((View)localObject1, LayoutHelper.createLinear(-2, -2, 51, 24, i, 24, 20));
                  this.lineProgressView = new LineProgressView(getContext());
                  this.lineProgressView.setProgress(this.currentProgress / 100.0F, false);
                  this.lineProgressView.setProgressColor(AndroidUtilities.getColor(getContext(), 2130903041));
                  this.lineProgressView.setBackColor(AndroidUtilities.getColor(getContext(), 2130903042));
                  paramBundle.addView(this.lineProgressView, LayoutHelper.createLinear(-1, 4, 19, 24, 0, 24, 0));
                  this.lineProgressViewPercent = new TextView(getContext());
                  this.lineProgressViewPercent.setGravity(51);
                  this.lineProgressViewPercent.setTextColor(AndroidUtilities.getColor(getContext(), 17170444));
                  this.lineProgressViewPercent.setTextSize(1, 14.0F);
                  paramBundle.addView(this.lineProgressViewPercent, LayoutHelper.createLinear(-2, -2, 51, 23, 4, 23, 24));
                  updateLineProgressTextView();
                  break;
                }
              }
              localObject1 = this.scrollContainer;
              localObject2 = this.messageTextView;
              if ((this.customView != null) || (this.items != null)) {}
              for (i = 20;; i = 0)
              {
                ((LinearLayout)localObject1).addView((View)localObject2, LayoutHelper.createLinear(-2, -2, 51, 24, 0, 24, i));
                break;
              }
              label1210:
              this.messageTextView.setVisibility(8);
              break label855;
            }
            localObject1 = new AlertDialogCell(getContext());
            localObject2 = this.items[i];
            if (this.itemIcons != null) {}
            for (k = this.itemIcons[i];; k = 0)
            {
              ((AlertDialogCell)localObject1).setTextAndIcon((CharSequence)localObject2, k);
              this.scrollContainer.addView((View)localObject1, LayoutHelper.createLinear(-1, 48));
              ((AlertDialogCell)localObject1).setTag(Integer.valueOf(i));
              ((AlertDialogCell)localObject1).setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  if (AlertDialog.this.onClickListener != null) {
                    AlertDialog.this.onClickListener.onClick(AlertDialog.this, ((Integer)paramAnonymousView.getTag()).intValue());
                  }
                  AlertDialog.this.dismiss();
                }
              });
              break;
            }
          }
        }
      }
    }
    if (this.customView != null)
    {
      if (this.customView.getParent() != null) {
        ((ViewGroup)this.customView.getParent()).removeView(this.customView);
      }
      this.scrollContainer.addView(this.customView, LayoutHelper.createLinear(-1, -2));
    }
    if (j != 0)
    {
      this.buttonsLayout = new FrameLayout(getContext())
      {
        protected void onLayout(boolean paramAnonymousBoolean, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
        {
          paramAnonymousInt4 = getChildCount();
          Object localObject = null;
          int i = paramAnonymousInt3 - paramAnonymousInt1;
          paramAnonymousInt1 = 0;
          if (paramAnonymousInt1 < paramAnonymousInt4)
          {
            View localView = getChildAt(paramAnonymousInt1);
            if (((Integer)localView.getTag()).intValue() == -1)
            {
              localObject = localView;
              localView.layout(i - getPaddingRight() - localView.getMeasuredWidth(), getPaddingTop(), i - getPaddingRight() + localView.getMeasuredWidth(), getPaddingTop() + localView.getMeasuredHeight());
            }
            for (;;)
            {
              paramAnonymousInt1 += 1;
              break;
              if (((Integer)localView.getTag()).intValue() == -2)
              {
                paramAnonymousInt3 = i - getPaddingRight() - localView.getMeasuredWidth();
                paramAnonymousInt2 = paramAnonymousInt3;
                if (localObject != null) {
                  paramAnonymousInt2 = paramAnonymousInt3 - (((View)localObject).getMeasuredWidth() + AndroidUtilities.dp(8.0F));
                }
                localView.layout(paramAnonymousInt2, getPaddingTop(), localView.getMeasuredWidth() + paramAnonymousInt2, getPaddingTop() + localView.getMeasuredHeight());
              }
              else
              {
                localView.layout(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + localView.getMeasuredWidth(), getPaddingTop() + localView.getMeasuredHeight());
              }
            }
          }
        }
      };
      this.buttonsLayout.setPadding(AndroidUtilities.dp(8.0F), AndroidUtilities.dp(8.0F), AndroidUtilities.dp(8.0F), AndroidUtilities.dp(8.0F));
      paramBundle.addView(this.buttonsLayout, LayoutHelper.createLinear(-1, 52));
      if (this.positiveButtonText != null)
      {
        paramBundle = new TextView(getContext())
        {
          public void setEnabled(boolean paramAnonymousBoolean)
          {
            super.setEnabled(paramAnonymousBoolean);
            if (paramAnonymousBoolean) {}
            for (float f = 1.0F;; f = 0.5F)
            {
              setAlpha(f);
              return;
            }
          }
        };
        paramBundle.setMinWidth(AndroidUtilities.dp(64.0F));
        paramBundle.setTag(Integer.valueOf(-1));
        paramBundle.setTextSize(1, 14.0F);
        paramBundle.setTextColor(AndroidUtilities.getColor(getContext(), 17170444));
        paramBundle.setGravity(17);
        paramBundle.setText(this.positiveButtonText.toString().toUpperCase());
        paramBundle.setBackgroundDrawable(getRoundRectSelectorDrawable(getContext()));
        paramBundle.setPadding(AndroidUtilities.dp(10.0F), 0, AndroidUtilities.dp(10.0F), 0);
        this.buttonsLayout.addView(paramBundle, LayoutHelper.createFrame(-2, 36, 53));
        paramBundle.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (AlertDialog.this.positiveButtonListener != null) {
              AlertDialog.this.positiveButtonListener.onClick(AlertDialog.this, -1);
            }
            AlertDialog.this.dismiss();
          }
        });
      }
      if (this.negativeButtonText != null)
      {
        paramBundle = new TextView(getContext())
        {
          public void setEnabled(boolean paramAnonymousBoolean)
          {
            super.setEnabled(paramAnonymousBoolean);
            if (paramAnonymousBoolean) {}
            for (float f = 1.0F;; f = 0.5F)
            {
              setAlpha(f);
              return;
            }
          }
        };
        paramBundle.setMinWidth(AndroidUtilities.dp(64.0F));
        paramBundle.setTag(Integer.valueOf(-2));
        paramBundle.setTextSize(1, 14.0F);
        paramBundle.setTextColor(AndroidUtilities.getColor(getContext(), 17170444));
        paramBundle.setGravity(17);
        paramBundle.setText(this.negativeButtonText.toString().toUpperCase());
        paramBundle.setBackgroundDrawable(getRoundRectSelectorDrawable(getContext()));
        paramBundle.setPadding(AndroidUtilities.dp(10.0F), 0, AndroidUtilities.dp(10.0F), 0);
        this.buttonsLayout.addView(paramBundle, LayoutHelper.createFrame(-2, 36, 53));
        paramBundle.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (AlertDialog.this.negativeButtonListener != null) {
              AlertDialog.this.negativeButtonListener.onClick(AlertDialog.this, -2);
            }
            AlertDialog.this.cancel();
          }
        });
      }
      if (this.neutralButtonText != null)
      {
        paramBundle = new TextView(getContext())
        {
          public void setEnabled(boolean paramAnonymousBoolean)
          {
            super.setEnabled(paramAnonymousBoolean);
            if (paramAnonymousBoolean) {}
            for (float f = 1.0F;; f = 0.5F)
            {
              setAlpha(f);
              return;
            }
          }
        };
        paramBundle.setMinWidth(AndroidUtilities.dp(64.0F));
        paramBundle.setTag(Integer.valueOf(-3));
        paramBundle.setTextSize(1, 14.0F);
        paramBundle.setTextColor(AndroidUtilities.getColor(getContext(), 17170444));
        paramBundle.setGravity(17);
        paramBundle.setText(this.neutralButtonText.toString().toUpperCase());
        paramBundle.setBackgroundDrawable(getRoundRectSelectorDrawable(getContext()));
        paramBundle.setPadding(AndroidUtilities.dp(10.0F), 0, AndroidUtilities.dp(10.0F), 0);
        this.buttonsLayout.addView(paramBundle, LayoutHelper.createFrame(-2, 36, 51));
        paramBundle.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (AlertDialog.this.neutralButtonListener != null) {
              AlertDialog.this.neutralButtonListener.onClick(AlertDialog.this, -2);
            }
            AlertDialog.this.dismiss();
          }
        });
      }
    }
    this.lastScreenWidth = AndroidUtilities.displaySize.x;
    int j = AndroidUtilities.displaySize.x;
    int k = AndroidUtilities.dp(48.0F);
    if (AndroidUtilities.isTablet()) {
      if (AndroidUtilities.isSmallTablet()) {
        i = AndroidUtilities.dp(446.0F);
      }
    }
    for (;;)
    {
      paramBundle = getWindow();
      localObject1 = new WindowManager.LayoutParams();
      ((WindowManager.LayoutParams)localObject1).copyFrom(paramBundle.getAttributes());
      ((WindowManager.LayoutParams)localObject1).dimAmount = 0.6F;
      ((WindowManager.LayoutParams)localObject1).width = (Math.min(i, j - k) + this.backgroundPaddings.left + this.backgroundPaddings.right);
      ((WindowManager.LayoutParams)localObject1).flags |= 0x2;
      if ((this.customView == null) || (!canTextInput(this.customView))) {
        ((WindowManager.LayoutParams)localObject1).flags |= 0x20000;
      }
      paramBundle.setAttributes((WindowManager.LayoutParams)localObject1);
      return;
      i = AndroidUtilities.dp(496.0F);
      continue;
      i = AndroidUtilities.dp(356.0F);
    }
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if (this.contentScrollView != null) {
      this.contentScrollView.postDelayed(paramRunnable, paramLong);
    }
  }
  
  public void setButton(int paramInt, CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
  {
    switch (paramInt)
    {
    default: 
      return;
    case -3: 
      this.neutralButtonText = paramCharSequence;
      this.neutralButtonListener = paramOnClickListener;
      return;
    case -2: 
      this.negativeButtonText = paramCharSequence;
      this.negativeButtonListener = paramOnClickListener;
      return;
    }
    this.positiveButtonText = paramCharSequence;
    this.positiveButtonListener = paramOnClickListener;
  }
  
  public void setCanceledOnTouchOutside(boolean paramBoolean)
  {
    super.setCanceledOnTouchOutside(paramBoolean);
  }
  
  public void setMessage(CharSequence paramCharSequence)
  {
    this.message = paramCharSequence;
    if (this.messageTextView != null)
    {
      if (!TextUtils.isEmpty(this.message))
      {
        this.messageTextView.setText(this.message);
        this.messageTextView.setVisibility(0);
      }
    }
    else {
      return;
    }
    this.messageTextView.setVisibility(8);
  }
  
  public void setProgress(int paramInt)
  {
    this.currentProgress = paramInt;
    if (this.lineProgressView != null)
    {
      this.lineProgressView.setProgress(paramInt / 100.0F, true);
      updateLineProgressTextView();
    }
  }
  
  public void setProgressStyle(int paramInt)
  {
    this.progressViewStyle = paramInt;
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if (this.contentScrollView != null) {
      this.contentScrollView.removeCallbacks(paramRunnable);
    }
  }
  
  public static class AlertDialogCell
    extends FrameLayout
  {
    private ImageView imageView;
    private TextView textView;
    
    public AlertDialogCell(Context paramContext)
    {
      super();
      if (Build.VERSION.SDK_INT >= 16) {
        setBackground(AndroidUtilities.getDrawable(paramContext, 17170444));
      }
      for (;;)
      {
        setPadding(AndroidUtilities.dp(23.0F), 0, AndroidUtilities.dp(23.0F), 0);
        this.imageView = new ImageView(paramContext);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(AndroidUtilities.getColor(paramContext, 2130903045), PorterDuff.Mode.MULTIPLY));
        addView(this.imageView, LayoutHelper.createFrame(24, 24, 19));
        this.textView = new TextView(paramContext);
        this.textView.setLines(1);
        this.textView.setSingleLine(true);
        this.textView.setGravity(1);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        this.textView.setTextColor(AndroidUtilities.getColor(paramContext, 17170444));
        this.textView.setTextSize(1, 16.0F);
        addView(this.textView, LayoutHelper.createFrame(-2, -2, 19));
        return;
        setBackgroundDrawable(AndroidUtilities.getDrawable(paramContext, 17170444));
      }
    }
    
    protected void onMeasure(int paramInt1, int paramInt2)
    {
      super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0F), 1073741824));
    }
    
    public void setGravity(int paramInt)
    {
      this.textView.setGravity(paramInt);
    }
    
    public void setTextAndIcon(CharSequence paramCharSequence, int paramInt)
    {
      this.textView.setText(paramCharSequence);
      if (paramInt != 0)
      {
        this.imageView.setImageResource(paramInt);
        this.imageView.setVisibility(0);
        this.textView.setPadding(AndroidUtilities.dp(56.0F), 0, 0, 0);
        return;
      }
      this.imageView.setVisibility(4);
      this.textView.setPadding(0, 0, 0, 0);
    }
    
    public void setTextColor(int paramInt)
    {
      this.textView.setTextColor(paramInt);
    }
  }
  
  public static class Builder
  {
    private AlertDialog alertDialog;
    
    public Builder(Context paramContext)
    {
      this.alertDialog = new AlertDialog(paramContext, 0);
    }
    
    public Builder(Context paramContext, int paramInt)
    {
      this.alertDialog = new AlertDialog(paramContext, paramInt);
    }
    
    public AlertDialog create()
    {
      return this.alertDialog;
    }
    
    public Context getContext()
    {
      return this.alertDialog.getContext();
    }
    
    public Builder setItems(CharSequence[] paramArrayOfCharSequence, DialogInterface.OnClickListener paramOnClickListener)
    {
      AlertDialog.access$802(this.alertDialog, paramArrayOfCharSequence);
      AlertDialog.access$1802(this.alertDialog, paramOnClickListener);
      return this;
    }
    
    public Builder setItems(CharSequence[] paramArrayOfCharSequence, int[] paramArrayOfInt, DialogInterface.OnClickListener paramOnClickListener)
    {
      AlertDialog.access$802(this.alertDialog, paramArrayOfCharSequence);
      AlertDialog.access$2302(this.alertDialog, paramArrayOfInt);
      AlertDialog.access$1802(this.alertDialog, paramOnClickListener);
      return this;
    }
    
    public Builder setMessage(CharSequence paramCharSequence)
    {
      AlertDialog.access$2802(this.alertDialog, paramCharSequence);
      return this;
    }
    
    public Builder setNegativeButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
    {
      AlertDialog.access$3002(this.alertDialog, paramCharSequence);
      AlertDialog.access$2002(this.alertDialog, paramOnClickListener);
      return this;
    }
    
    public Builder setNeutralButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
    {
      AlertDialog.access$3102(this.alertDialog, paramCharSequence);
      AlertDialog.access$2102(this.alertDialog, paramOnClickListener);
      return this;
    }
    
    public Builder setOnDismissListener(DialogInterface.OnDismissListener paramOnDismissListener)
    {
      this.alertDialog.setOnDismissListener(paramOnDismissListener);
      return this;
    }
    
    public Builder setPositiveButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
    {
      AlertDialog.access$2902(this.alertDialog, paramCharSequence);
      AlertDialog.access$1902(this.alertDialog, paramOnClickListener);
      return this;
    }
    
    public Builder setSubtitle(CharSequence paramCharSequence)
    {
      AlertDialog.access$2502(this.alertDialog, paramCharSequence);
      return this;
    }
    
    public Builder setTitle(CharSequence paramCharSequence)
    {
      AlertDialog.access$2402(this.alertDialog, paramCharSequence);
      return this;
    }
    
    public Builder setTopImage(int paramInt1, int paramInt2)
    {
      AlertDialog.access$2602(this.alertDialog, paramInt1);
      AlertDialog.access$2702(this.alertDialog, paramInt2);
      return this;
    }
    
    public Builder setView(View paramView)
    {
      AlertDialog.access$602(this.alertDialog, paramView);
      return this;
    }
    
    public AlertDialog show()
    {
      this.alertDialog.show();
      return this.alertDialog;
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/base/dialog/AlertDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */