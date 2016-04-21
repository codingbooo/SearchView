package com.example.searchview.weiget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.searchview.R;
import com.example.searchview.utils.PixelsUtil;

public class SearchBar extends RelativeLayout {

    private int mWidth;
    private int mHeight;

    private Button search;
    private TextView tips;
    private EditText input;
    private LinearLayout container;

    private boolean isShow;

    private Context mContext;

    public SearchBar(Context context) {
        super(context);
//        initView(context);
    }


    public SearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public SearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            if ("layout_height".equals(attrs.getAttributeName(i))) {
                String h = attrs.getAttributeValue(i);
                mHeight = PixelsUtil.dip2px(context, h);
                Log.d("haha", "mHeight " + mHeight);
            } else if ("layout_width".equals(attrs.getAttributeName(i))) {
                String w = attrs.getAttributeValue(i);
                mWidth = PixelsUtil.dip2px(context, w);
                Log.d("haha", "mWidth " + mWidth);
            }
        }
        LayoutInflater.from(context).inflate(R.layout.search_view_layout, this);
        isShow = false;
        mContext = context;
        search = (Button) findViewById(R.id.bt_search);
        tips = (TextView) findViewById(R.id.tv_tips);
        input = (EditText) findViewById(R.id.et_input);
        container = (LinearLayout) findViewById(R.id.container);
        // TODO 得到控件大小
        search.setEnabled(false);
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("haha", "search Clicked");
            }
        });
//        LayoutParams layoutParams = (LayoutParams) container.getLayoutParams();
//        Log.d("haha", "container = " + container.getWidth());
//        Log.d("haha", "tips = " + tips.getWidth());
//        Log.d("haha", "search = " + search.getWidth());
//
//
//        layoutParams.setMargins(mWidth / 2 - container.getWidth() / 2, 0, 0, 0);
//        container.requestLayout();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("haha", "dispatchTouchEvent");
        if (isShow) {

        } else {
            onInput();
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("haha", "onTouchEvent");
//        if (isShow) {
//
//        } else {
//            onInput();
//        }
//        onDefault();
        return super.onTouchEvent(event);
    }

    /**
     * 输入状态
     */
    public void onInput() {
//        LayoutParams layoutParams = (LayoutParams) container.getLayoutParams();
//        layoutParams.setMargins(mWidth / 2 + container.getWidth() / 2, 0 , 0, 0);
//        container.requestLayout();

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
//                ObjectAnimator.ofFloat(tips, "translationX", mWidth / 2 - search.getWidth()),
                ObjectAnimator.ofFloat(container, "translationX", mWidth / 2 - search.getWidth()
                        / 2),
                ObjectAnimator.ofFloat(tips, "alpha", 1, 0.0f)
        );
        set.setDuration(500);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                input.setVisibility(View.VISIBLE);
//                container.setVisibility(View.GONE);
//                LayoutParams inputParams = new LayoutParams(1, mHeight);
//                input.setLayoutParams(inputParams);
                input.getLayoutParams().width = mWidth - search.getWidth() * 2;
                input.requestLayout();
                search.setEnabled(true);
//                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.playTogether(
//                        ObjectAnimator.ofFloat(input, "translationX", -search.getWidth()),
//                        ObjectAnimator.ofFloat(input, "scaleX", mWidth - search.getWidth() * 3)
//                );
//                animatorSet.setDuration(500);
//                animatorSet.start();
            }


            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
//        set.playSequentially();
        set.start();
        isShow = true;
    }

    /**
     * 默认状态
     */
    public void onDefault() {
        isShow = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("haha", "width = " + MeasureSpec.getSize(widthMeasureSpec));
        Log.e("haha", "height = " + MeasureSpec.getSize(heightMeasureSpec));
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
