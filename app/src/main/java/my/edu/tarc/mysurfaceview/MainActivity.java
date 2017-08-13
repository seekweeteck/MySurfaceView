package my.edu.tarc.mysurfaceview;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    SurfaceView mySurfaceView;
    EditText editTextX, editTextY;
    int stationCount=5;

    //private Paint mPaint;
    private boolean leftToRightMovement = true;
    private boolean rightToLeftMovement = false;
    private boolean atTheMiddlePositionWhileLeftToRight = false;
    private boolean atTheMiddlePositionWhileRightToLeft = false;
    private boolean firstHalf = true;
    private boolean secondHalf = false;
    private volatile double ballX = 0;
    private volatile double ballY = 0;

    //private boolean theCenterBeingDrawn = false;

    double angleAccel = 0.0;
    double angleVelocity = 0;
    double dt = 0.15;

    private boolean flag = false;

    boolean flagCondition = false;

    private int anchorX;
    private int anchorY;
    public static double initialAngle = Math.PI/4;
    public static double angle = Math.PI/4;
    double angleInThePreviousStep = Math.PI/4;

    private static final int length = 150;

    private Paint mPaint;

    private Paint mRefreshPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //stationCount = Integer.valueOf(editTextX.getText().toString());

        //setContentView(R.layout.activity_main);
        setContentView(new MySurfaceView(this));

        //editTextX = (EditText)findViewById(R.id.editTextX);
        //mySurfaceView = (SurfaceView)findViewById(R.id.surfaceView);

        /*
        Panel p = new Panel(this);

        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);

        mRefreshPaint = new Paint();
        mRefreshPaint.setColor(Color.BLACK);

        setContentView(p);*/
    }

    public void setXY(View v){
        stationCount = Integer.valueOf(editTextX.getText().toString());
        MySurfaceView mySurfaceView = new MySurfaceView(this);

    }
}
