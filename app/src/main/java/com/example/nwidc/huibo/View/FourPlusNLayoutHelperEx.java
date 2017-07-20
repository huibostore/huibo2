package com.example.nwidc.huibo.View;

import android.graphics.Rect;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.AbstractFullFillLayoutHelper;
import com.alibaba.android.vlayout.layout.LayoutChunkResult;

import java.util.Arrays;

import static com.alibaba.android.vlayout.VirtualLayoutManager.VERTICAL;

/**
 * Created by hello on 2017/7/17.
 */

public class FourPlusNLayoutHelperEx extends AbstractFullFillLayoutHelper {

    private static final String TAG = "OnePlusNLayoutHelper";

    private Rect mAreaRect = new Rect();

    private View[] mChildrenViews;

    private float[] mColWeights = new float[0];

    private float mRowWeight = Float.NaN;

    public FourPlusNLayoutHelperEx() {
        setItemCount(0);
    }

    public FourPlusNLayoutHelperEx(int itemCount) {
        this(itemCount, 0, 0, 0, 0);
    }

    public FourPlusNLayoutHelperEx(int itemCount, int leftMargin, int topMargin, int rightMargin,
                                  int bottomMargin) {
        setItemCount(itemCount);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Currently, this layout supports maximum children up to 5, otherwise {@link
     * IllegalArgumentException}
     * will be thrown
     *
     * @param start start position of items handled by this layoutHelper
     * @param end   end position of items handled by this layoutHelper, if end &lt; start or end -
     *              start &gt 4, it will throw {@link IllegalArgumentException}
     */
    @Override
    public void onRangeChange(int start, int end) {
        if (end - start < 4) {
            throw new IllegalArgumentException(
                    "pls use OnePlusNLayoutHelper instead of OnePlusNLayoutHelperEx which childcount <= 5");
        }
        if (end - start > 5) {
            throw new IllegalArgumentException(
                    "OnePlusNLayoutHelper only supports maximum 7 children now");
        }
    }

    public void setColWeights(float[] weights) {
        if (weights != null) {
            this.mColWeights = Arrays.copyOf(weights, weights.length);
        } else {
            this.mColWeights = new float[0];
        }
    }

    public void setRowWeight(float weight) {
        this.mRowWeight = weight;
    }

    @Override
    public void layoutViews(RecyclerView.Recycler recycler, RecyclerView.State state,
                            VirtualLayoutManager.LayoutStateWrapper layoutState, LayoutChunkResult result, LayoutManagerHelper helper) {
        // reach the end of this layout
        final int originCurPos = layoutState.getCurrentPosition();
        if (isOutOfRange(originCurPos)) {
            return;
        }

        if (mChildrenViews == null || mChildrenViews.length != getItemCount()) {
            mChildrenViews = new View[getItemCount()];
        }

        int count = getAllChildren(mChildrenViews, recycler, layoutState, result, helper);

        if (count != getItemCount()) {
            Log.w(TAG, "The real number of children is not match with range of LayoutHelper");
        }

        final boolean layoutInVertical = helper.getOrientation() == VERTICAL;

        final int parentWidth = helper.getContentWidth();
        final int parentHeight = helper.getContentHeight();
        final int parentHPadding = helper.getPaddingLeft() + helper.getPaddingRight()
                + getHorizontalMargin() + getHorizontalPadding();
        final int parentVPadding = helper.getPaddingTop() + helper.getPaddingBottom()
                + getVerticalMargin() + getVerticalPadding();

        int mainConsumed = 0;


            mainConsumed = handleFive(layoutState, result, helper, layoutInVertical, parentWidth, parentHeight,
                    parentHPadding, parentVPadding);


        result.mConsumed = mainConsumed;

        Arrays.fill(mChildrenViews, null);
    }


    private float getViewMainWeight(ViewGroup.MarginLayoutParams params, int index) {
        if (mColWeights.length > index) {
            return mColWeights[index];
        }

        return Float.NaN;
    }


    private int mergeLayoutMargin(int viewMargin, int layoutMargin) {
        // TODO: collapse has problem
        if (layoutMargin > 0) {
            return (viewMargin <= layoutMargin) ? 0 : viewMargin - layoutMargin;
        }
        return viewMargin;
    }

    @Override
    public int computeAlignOffset(int offset, boolean isLayoutEnd, boolean useAnchor,
                                  LayoutManagerHelper helper) {
        if (getItemCount() == 3) {
            if (offset == 1 && isLayoutEnd) {
                Log.w(TAG, "Should not happen after adjust anchor");
                return 0;
            }
        } else if (getItemCount() == 4) {
            if (offset == 1 && isLayoutEnd) {
                return 0;
            }
        }

        if (helper.getOrientation() == VERTICAL) {
            if (isLayoutEnd) {
                return mMarginBottom + mPaddingBottom;
            } else {
                return -mMarginTop - mPaddingTop;
            }
        } else {
            if (isLayoutEnd) {
                return mMarginRight + mPaddingRight;
            } else {
                return -mMarginLeft - mPaddingLeft;
            }
        }
    }

    private int handleFive(VirtualLayoutManager.LayoutStateWrapper layoutState, LayoutChunkResult result, LayoutManagerHelper helper,
                           boolean layoutInVertical, int parentWidth, int parentHeight, int parentHPadding, int parentVPadding) {
        int mainConsumed = 0;
        OrientationHelper orientationHelper = helper.getMainOrientationHelper();

        final View child1 = mChildrenViews[0];
        final VirtualLayoutManager.LayoutParams lp1 = new VirtualLayoutManager.LayoutParams(
                child1.getLayoutParams());
        final View child2 = helper.getReverseLayout() ? mChildrenViews[4] : mChildrenViews[1];
        final VirtualLayoutManager.LayoutParams lp2 = new VirtualLayoutManager.LayoutParams(
                child2.getLayoutParams());
        final View child3 = helper.getReverseLayout() ? mChildrenViews[3] : mChildrenViews[2];
        final VirtualLayoutManager.LayoutParams lp3 = new VirtualLayoutManager.LayoutParams(
                child3.getLayoutParams());
        final View child4 = helper.getReverseLayout() ? mChildrenViews[2] : mChildrenViews[3];
        final VirtualLayoutManager.LayoutParams lp4 = new VirtualLayoutManager.LayoutParams(
                child4.getLayoutParams());
        final View child5 = helper.getReverseLayout() ? mChildrenViews[1] : mChildrenViews[4];
        final VirtualLayoutManager.LayoutParams lp5 = new VirtualLayoutManager.LayoutParams(
                child5.getLayoutParams());

        final float weight1 = getViewMainWeight(lp1, 0);
        final float weight2 = getViewMainWeight(lp1, 1);
        final float weight3 = getViewMainWeight(lp1, 2);
        final float weight4 = getViewMainWeight(lp1, 3);
        final float weight5 = getViewMainWeight(lp1, 4);

        if (layoutInVertical) {

            lp2.topMargin = lp1.topMargin;
            lp3.bottomMargin = lp4.bottomMargin = lp1.bottomMargin;
            lp3.leftMargin = lp2.leftMargin;
            lp4.rightMargin = lp2.rightMargin;
            lp5.rightMargin = lp3.rightMargin;

            float mAspectRatio = Float.NaN;
            if (!Float.isNaN(mAspectRatio)) {

                lp1.height = (int) ((parentWidth - parentHPadding) / mAspectRatio);
            }

            int availableSpace = parentWidth - parentHPadding - lp1.leftMargin - lp1.rightMargin
                    - lp2.leftMargin - lp2.rightMargin
                    - lp3.leftMargin - lp3.rightMargin;

            int width1 = Float.isNaN(weight1) ? (int) (availableSpace / 3.0f + 0.5f)
                    : (int) (availableSpace * weight1 / 100 + 0.5f);
            int width2 = Float.isNaN(weight2) ? (availableSpace - width1) / 2
                    : (int) (availableSpace * weight2 / 100 + 0.5f);
            int width3 = Float.isNaN(weight3) ? width2
                    : (int) (availableSpace * weight3 / 100 + 0.5f);
            int width4 = Float.isNaN(weight4) ? width2
                    : (int) (availableSpace * weight4 / 100 + 0.5f);
            int width5 = Float.isNaN(weight5) ? width2
                    : (int) (availableSpace * weight5 / 100 + 0.5f);

            helper.measureChildWithMargins(child1,
                    View.MeasureSpec.makeMeasureSpec(width1 + lp1.leftMargin + lp1.rightMargin,
                            View.MeasureSpec.EXACTLY),
                    helper.getChildMeasureSpec(helper.getContentHeight(), lp1.height, true));

            int height1 = child1.getMeasuredHeight();
            int height2 = Float.isNaN(mRowWeight) ?
                    (int) ((height1 - lp2.bottomMargin - lp3.topMargin) / 2.0f + 0.5f)
                    : (int) ((height1 - lp2.bottomMargin - lp3.topMargin) * mRowWeight / 100
                    + 0.5f);
            int height3 = (height1 - lp2.bottomMargin - lp3.topMargin) - height2;

            helper.measureChildWithMargins(child2,
                    View.MeasureSpec.makeMeasureSpec(width2 + lp2.leftMargin + lp2.rightMargin,
                            View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(height2 + lp2.topMargin + lp2.bottomMargin,
                            View.MeasureSpec.EXACTLY));

            helper.measureChildWithMargins(child3,
                    View.MeasureSpec.makeMeasureSpec(width3 + lp3.leftMargin + lp3.rightMargin,
                            View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(height3 + lp3.topMargin + lp3.bottomMargin,
                            View.MeasureSpec.EXACTLY));

            helper.measureChildWithMargins(child4,
                    View.MeasureSpec.makeMeasureSpec(width4 + lp4.leftMargin + lp4.rightMargin,
                            View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(height3 + lp4.topMargin + lp4.bottomMargin,
                            View.MeasureSpec.EXACTLY));

            helper.measureChildWithMargins(child5,
                    View.MeasureSpec.makeMeasureSpec(width5 + lp5.leftMargin + lp5.rightMargin,
                            View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(height3 + lp5.topMargin + lp5.bottomMargin,
                            View.MeasureSpec.EXACTLY));

            mainConsumed = Math.max(height1 + lp1.topMargin + lp1.bottomMargin,
                    height2 + lp2.topMargin + lp2.bottomMargin + Math
                            .max(height3 + lp3.topMargin + lp3.bottomMargin,
                                    height3 + lp4.topMargin + lp4.bottomMargin))
                    + getVerticalMargin() + getVerticalPadding();

            calculateRect(mainConsumed - getVerticalMargin() - getVerticalPadding(), mAreaRect, layoutState, helper);

            int right1 = mAreaRect.left + orientationHelper
                    .getDecoratedMeasurementInOther(child1);
            layoutChild(child1, mAreaRect.left, mAreaRect.top,
                    right1, mAreaRect.bottom, helper);

            int right2 = right1 + orientationHelper.getDecoratedMeasurementInOther(child2);
            layoutChild(child2, right1, mAreaRect.top, right2,
                    mAreaRect.top + orientationHelper.getDecoratedMeasurement(child2),
                    helper);

            int right3 = right2 + orientationHelper.getDecoratedMeasurementInOther(child3);
            layoutChild(child3, right2,
                    mAreaRect.top,
                    right3, mAreaRect.top + orientationHelper.getDecoratedMeasurement(child3), helper);

            int right4 = right1 + orientationHelper.getDecoratedMeasurementInOther(child4);
            layoutChild(child4, right1,
                    mAreaRect.bottom - orientationHelper.getDecoratedMeasurement(child4),
                    right4,
                    mAreaRect.bottom, helper);

            layoutChild(child5, right4,
                    mAreaRect.bottom - orientationHelper.getDecoratedMeasurement(child5),
                    right4 + orientationHelper.getDecoratedMeasurementInOther(child5),
                    mAreaRect.bottom, helper);
        } else {
            // TODO: horizontal support
        }

        handleStateOnResult(result, child1, child2, child3, child4, child5);

        return mainConsumed;
    }

}