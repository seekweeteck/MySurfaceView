package my.edu.tarc.mysurfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by KweeTeck on 4/14/2017.
 */

public class MySurfaceView extends SurfaceView implements SurfaceView.OnClickListener {

    MainActivity mainActivity;

    private SurfaceHolder surfaceHolder;
    private Bitmap bmpIcon, bmpStation, bmpLine;
    private MyThread myThread;
    private int stationCount = 5;
    int xStation;
    int yStation;
    int xPos = 0;
    int yPos = 0;
    int deltaX = 5;
    int deltaY = 5;
    int iconWidth;
    int iconHeight;
    int lineHeight;

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
        myThread = new MyThread(this);

        surfaceHolder = getHolder();


        bmpIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_directions_bus_black_24dp);

        iconWidth = bmpIcon.getWidth();
        iconHeight = bmpIcon.getHeight();

        bmpStation = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_brightness_1_black_24dp);

        xStation = 0;
        yStation = 0;

        bmpLine = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_line_black_24dp);

        lineHeight = bmpLine.getHeight();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                myThread.setRunning(true);
                myThread.start();

                /*Canvas canvas = holder.lockCanvas(null);
                drawSomething(canvas);
                holder.unlockCanvasAndPost(canvas);*/
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
        xPos = mainActivity.xPos;
        yPos = mainActivity.yPos;

        canvas.drawColor(Color.LTGRAY);
        for(int i=0, x = xPos, y = yPos; i < stationCount; i++){
            canvas.drawBitmap(bmpStation,
                    x, y, null);
            y += 24;
            canvas.drawBitmap(bmpLine,
                    x, y, null);
            y += 64;
        }

    }

    @Override
    public void onClick(View view) {

    }
}