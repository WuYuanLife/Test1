package cn.heyl.myim;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

import obsrcollview.ObservableScrollView;
import obsrcollview.ObservableScrollViewCallbacks;
import obsrcollview.ScrollState;
import obsrcollview.ScrollUtils;

public class OBSV4Activity extends ActionBarActivity implements ObservableScrollViewCallbacks {
    private static final float MAX_TEXT_SCALE_DELTA = 0.3f;
    private static final boolean TOOLBAR_IS_STICKY = true;

    private View mToolbar;
    private View mImageView;
    private View mOverlayView;
    //    private ObservableScrollView mScrollView;
    private TextView mTitleView;
    private View mFab;
    private RelativeLayout rlTitle;
    private int mActionBarSize;
    private int mFlexibleSpaceShowFabOffset;
    private int mFlexibleSpaceImageHeight;
    private int mToolbarColor;
    private ObservableScrollView mScrollView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> list;

    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obsv4);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
        mActionBarSize = getActionBarSize();
        mToolbarColor = getResources().getColor(R.color.primary);

        rlTitle= (RelativeLayout) findViewById(R.id.rlTitle);
        mOverlayView = findViewById(R.id.overlay);
        lv= (ListView) findViewById(R.id.mylv);
        mToolbar = findViewById(R.id.toolbar);
        if (!TOOLBAR_IS_STICKY) {
            mToolbar.setBackgroundColor(Color.TRANSPARENT);
        }


        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);
        mTitleView = (TextView) findViewById(R.id.title);
        setTitle(null);
        SetList();

        ScrollUtils.addOnGlobalLayoutListener(mScrollView, new Runnable() {
            @Override
            public void run() {
                mScrollView.scrollTo(0, mFlexibleSpaceImageHeight - mActionBarSize);

            }
        });
    }

    private void SetList() {
        list = new ArrayList<>();
        list.add("");
        list.add("广告轮播");
        list.add("引导滑动");
        list.add("下拉筛选");
        list.add("标题图片拉隐藏");
        list.add("标题栏下拉隐藏");
        list.add("标题布局拉隐藏");
        list.add("标题ListView下拉隐藏");
        list.add("爆炸DiaLog");
        list.add("三角形视图背景"); list.add("标题图片拉隐藏");
        list.add("标题栏下拉隐藏");
        list.add("标题布局拉隐藏");
        list.add("标题ListView下拉隐藏");
        list.add("爆炸DiaLog");
        list.add("三角形视图背景"); list.add("标题图片拉隐藏");
        list.add("标题栏下拉隐藏");
        list.add("标题布局拉隐藏");
        list.add("标题ListView下拉隐藏");
        list.add("爆炸DiaLog");
        list.add("三角形视图背景");
        adapter=new ArrayAdapter<String>(this,R.layout.item,R.id.list_item_text,list);
//
//        View view= LayoutInflater.from(this).inflate(R.layout.textview,null);
//        rlTitle= (RelativeLayout) view.findViewById(R.id.rlTitle);
//        mOverlayView = view.findViewById(R.id.overlay);
//        view.setBackgroundColor(Color.TRANSPARENT);
//        mScrollView.addHeaderView(view);
        lv.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        int minOverlayTransitionY = mActionBarSize - mOverlayView.getHeight();
        ViewHelper.setTranslationY(mOverlayView, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
        ViewHelper.setTranslationY(rlTitle, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));
//        ViewHelper.setTranslationY(mImageView, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));

        // Change alpha of overlay
        ViewHelper.setAlpha(mOverlayView, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));

        // Scale title text
        float scale = 1 + ScrollUtils.getFloat((flexibleRange - scrollY) / flexibleRange, 0, MAX_TEXT_SCALE_DELTA);
        ViewHelper.setPivotX(mTitleView, 0);
        ViewHelper.setPivotY(mTitleView, 0);
        ViewHelper.setScaleX(mTitleView, scale);
        ViewHelper.setScaleY(mTitleView, scale);

        // Translate title text
        int maxTitleTranslationY = (int) (mFlexibleSpaceImageHeight - mTitleView.getHeight() * scale);
        int maxTitleTranslationX = (int) (rlTitle.getWidth()-mTitleView.getWidth() * scale);
        int titleTranslationY = maxTitleTranslationY - scrollY;
        int titleTranslationX = titleTranslationY*100/maxTitleTranslationY*maxTitleTranslationX/100;
        if (TOOLBAR_IS_STICKY) {
            titleTranslationY = Math.max(0, titleTranslationY);

        }
        ViewHelper.setTranslationY(mTitleView, titleTranslationY);
        ViewHelper.setTranslationX(mTitleView, Math.abs(titleTranslationX));

        if (TOOLBAR_IS_STICKY) {
            // Change alpha of toolbar background
            if (-scrollY + mFlexibleSpaceImageHeight <= mActionBarSize) {
                mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(1, mToolbarColor));
            } else {
                mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, mToolbarColor));
            }
        } else {
            // Translate Toolbar
            if (scrollY < mFlexibleSpaceImageHeight) {
                ViewHelper.setTranslationY(mToolbar, 0);
            } else {
                ViewHelper.setTranslationY(mToolbar, -scrollY);
            }
        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
