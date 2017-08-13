package my.edu.tarc.mysurfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

/**
 * Created by KweeTeck on 4/14/2017.
 */

public class MySurfaceView extends SurfaceView {

    MainActivity mainActivity;

    private SurfaceHolder surfaceHolder;
    private Drawable  drawableStation, drawableLine;
    private Bitmap bitmapStation, bitmapLine;
    private TextView textViewStation;
    private MyThread myThread;
    private int stationCount=5;
    int midStage;
    int yStation;
    int xPos = 0;
    int yPos = 50;
    int iconWidth;
    int xLine, yLine;
    int heightStation = 10, heightLine=50;
    int gapSize;

    public MySurfaceView(Context context) {
        super(context);
        init(context);
    }

    public MySurfaceView(Context context,
                         AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySurfaceView(Context context,
                         AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

        mainActivity = (MainActivity) context;

        //Start a separate Thread
        /*myThread = new MyThread(this);*/
        surfaceHolder = getHolder();

        Drawable drawableStation = VectorDrawableCompat.create(getResources(), R.drawable.ic_station, null);
        bitmapStation = BitmapFactory.decodeResource(getResources() , R.drawable.ic_action_station);

        if(drawableStation != null){
            heightStation = drawableStation.getMinimumHeight() / 2;
        }



        Drawable drawableLine = VectorDrawableCompat.create(getResources(), R.drawable.ic_line, null);

        if(drawableLine != null){
            bitmapLine = BitmapFactory.decodeResource(getResources(), R.drawable.ic_line);
            heightLine = drawableLine.getMinimumHeight() / 2;
        }


        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //myThread.setRunning(true);
                //myThread.start();

                /*midStage = getWidth() / 2 - drawableStation.getMinimumWidth() / 2;
                xLine = getWidth() / 2 - getContext().drawableLine.getMinimumWidth() / 2;*/

                midStage = getWidth() / 2;
                xLine = getWidth() / 2;

                Canvas canvas = holder.lockCanvas(null);
                drawStation(canvas);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // TODO Auto-generated method stub
                boolean retry = true;
                myThread.setRunning(false);
                while (retry) {
                    try {
                        myThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    protected void drawSomething(Canvas canvas) {

    }

    protected void drawStation(Canvas canvas) {
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLUE);

        textPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.textNormal));

        stationCount = mainActivity.stationCount;
        canvas.drawColor(Color.WHITE);

        gapSize = heightStation / 2;

        for (int i = 1; i <= stationCount; i++) {
            canvas.drawCircle(midStage,yPos,heightStation, textPaint);
            canvas.drawText("Station " + i, midStage+50, yPos+15, textPaint);

            yPos += heightStation; //12

            if(i != stationCount){
                canvas.drawRect(midStage-5, yPos, midStage+5, (yPos + heightLine),textPaint);
                yPos += heightLine; //100
            }
        }
    }

    /*@Override
    public void onClick(View view) {
        stationCount = mainActivity.stationCount;
    }*/
}
