package my.edu.tarc.mysurfaceview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    SurfaceView mySurfaceView;
    EditText editTextX, editTextY;
    int xPos=0, yPos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextX = (EditText)findViewById(R.id.editTextX);
        editTextY = (EditText)findViewById(R.id.editTextY);
    }

    public void setXY(View v){
       xPos = Integer.valueOf(editTextX.getText().toString());
       yPos = Integer.valueOf(editTextY.getText().toString());
    }
}
