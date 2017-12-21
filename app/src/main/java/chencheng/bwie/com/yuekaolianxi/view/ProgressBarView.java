package chencheng.bwie.com.yuekaolianxi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dell on 2017/12/19.
 */

public class ProgressBarView extends View {
    private Paint paint;
    private int currentX=100;
    private int currentY=100;
    private int count;
    private PointF pointF = new PointF(currentX,currentY);
    private  int mProgress;

    public ProgressBarView(Context context) {
        super(context);
       initpaint(context);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initpaint(context);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initpaint(context);
    }

    private void initpaint(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(0);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(pointF.x,pointF.y,20,paint);
        canvas.drawCircle(pointF.x,pointF.y,30,paint);
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        RectF recyF = new RectF(75,75,125,125);
        canvas.drawArc(recyF,-90,mProgress,false,paint);

        paint.setStrokeWidth(1);
        paint.setColor(Color.BLUE);
        canvas.drawText(count+"",98,102,paint);
    }

    public void setProgress(int progress){
        this.mProgress = progress;
        if (mProgress == 120){
            count = 2;
        }
        if (mProgress == 240){
            count = 1;
        }
        if (mProgress == 360){
            count = 0;
        }
        invalidate();

    }
}
