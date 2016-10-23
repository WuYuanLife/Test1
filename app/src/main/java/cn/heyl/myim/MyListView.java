package cn.heyl.myim;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *	Creaded by heyl 2016-10-9
 */
public class MyListView extends ListView{ 
    public MyListView(Context context, AttributeSet attrs) { 
        super(context, attrs); 
    } 
    public MyListView(Context context) { 
        super(context); 
    } 
    public MyListView(Context context, AttributeSet attrs, int defStyle) { 
        super(context, attrs, defStyle); 
    }     
    @Override 
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {      
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 8, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
} 
