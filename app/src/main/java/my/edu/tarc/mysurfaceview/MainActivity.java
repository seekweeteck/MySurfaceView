package my.edu.tarc.mysurfaceview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    SurfaceView mySurfaceView;
    EditText editTextX, editTextY;
    int stationCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextX = (EditText)findViewById(R.id.editTextX);

    }

    public void setXY(View v){
        stationCount = Integer.valueOf(editTextX.getText().toString());
    }
}
