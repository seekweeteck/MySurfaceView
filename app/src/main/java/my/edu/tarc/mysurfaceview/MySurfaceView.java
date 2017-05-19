package my.edu.tarc.mysurfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KweeTeck on 4/14/2017.
 */

public class MySurfaceView extends SurfaceView implements SurfaceView.OnClickListener {

    MainActivity mainActivity;

    private SurfaceHolder surfaceHolder;
    private Bitmap bmpIcon, bmpStation, bmpLine;
    private TextView textViewStation;
    private MyThread myThread;
    private int stationCount=5;
    int midStage;
    int yStation;
    int xPos = 0;
    int yPos = 0;
    int iconWidth;
    int iconHeight;
    int xLine, yLine;
    int heightStation, heightLine;

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


        bmpIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_directions_bus_black_24dp);

        bmpStation = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_station);
        heightStation = bmpStation.getHeight() / 2;

        bmpLine = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_line);
        heightLine = bmpLine.getHeight() / 2;

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //myThread.setRunning(true);
                //myThread.start();

                midStage = getWidth() / 2 - bmpStation.getWidth() / 2;
                xLine = getWidth() / 2 - bmpLine.getWidth() / 2;

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
        //textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.textNormal));

        //stationCount = mainActivity.stationCount;
        //yPos = mainActivity.yPos;

        canvas.drawColor(Color.WHITE);
        for (int i = 1; i <= stationCount; i++) {
            canvas.drawBitmap(bmpStation,
                    midStage, yPos, null);
            canvas.drawText("Station " + i, midStage + 50, yPos, textPaint);

            yPos = yPos + heightStation; //12
            Log.d("yPos", i +"=" + yPos + " heightStation=" + heightStation);
            if(i != stationCount){
                canvas.drawBitmap(bmpLine,
                        xLine, yPos, null);
                yPos = yPos + heightLine; //100
                Log.d("yPos", "=" + yPos + " heightLine=" + heightLine);
            }
        }
    }

    @Override
    public void onClick(View view) {
        stationCount = mainActivity.stationCount;
    }
}
