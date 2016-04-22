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

public class SearchBar extends RelativeLayout implements View.OnClickListener {
    private Context mContext;

    private int mWidth;
    private int mHeight;

    private Button search;
    private TextView tips;
    private EditText input;
    private LinearLayout container;

    private boolean isShow;


    private float startX;

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
        setOnClickListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("haha", "dispatchTouchEvent");

        if (MotionEvent.ACTION_DOWN == ev.getAction()) {
            Log.v("haha", "action down");
            startX = ev.getX();
        } else if(MotionEvent.ACTION_MOVE == ev.getAction()){
            Log.v("haha", "action move");


            return true;
        } else if (MotionEvent.ACTION_UP == ev.getAction()) {
            Log.v("haha", "action up");
            float dx = startX - ev.getX();
            if(dx > 10.0 && isShow) {
                onDefault();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("haha", "onTouchEvent");
        return super.onTouchEvent(event);
    }

    /**
     * 输入状态
     */
    public void onInput() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(container, "translationX", mWidth / 2 - search.getWidth() / 2),
                ObjectAnimator.ofFloat(tips, "alpha", 1, 0.0f),
                ObjectAnimator.ofFloat(input, "Alpha",0, 1)
        );
        set.setDuration(500);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                input.getLayoutParams().width = mWidth - search.getWidth() * 2;
                input.requestLayout();
                input.setText("");
                input.setVisibility(View.VISIBLE);
                search.setEnabled(true);
                isShow = true;
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

    }

    /**
     * 默认状态
     */
    public void onDefault() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(input, "Alpha", 0 ),
                ObjectAnimator.ofFloat(container,"translationX", 0),
                ObjectAnimator.ofFloat(tips, "Alpha",0, 1)

        );
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                input.setText("");
                input.clearFocus();
                input.setVisibility(View.GONE);
                isShow = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.setDuration(500);
        set.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("haha", "width = " + MeasureSpec.getSize(widthMeasureSpec));
        Log.e("haha", "height = " + MeasureSpec.getSize(heightMeasureSpec));
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onClick(View view) {
        Log.i("haha", "onClick view == " + view.getId());
        if (isShow) {
//            onDefault();
        } else {
            onInput();
        }
    }
}
