package tech.sidespell.prelimexam;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity{

    RadioButton radioButton1;
    RadioButton radioButton2;
    SeekBar seekBar;
    TextView textview;
    TextView textview2;
    ToggleButton toggleButton;
    int count = 0;
    int progressChanged = 0;
    int progressDelay = 0;
   Handler handler;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       radioButton1 = (RadioButton)findViewById(R.id.radioButton);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        textview = (TextView)findViewById(R.id.textView);
       textview2 = (TextView)findViewById(R.id.textView2);
       toggleButton = (ToggleButton)findViewById(R.id.toggleButton);

        forToggleButton();
        forSeekBar();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void forSeekBar() {

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //textview2.setText(String.valueOf(progressChanged));
                Toast.makeText(MainActivity.this, "seek bar progress:" + progressChanged,
                        Toast.LENGTH_SHORT).show();
                progressDelay = progressChanged;

            }
        });
        textview2.setText(String.valueOf(progressDelay));
    }

    private void forToggleButton() {

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // The toggle is enabled
                   handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if(radioButton1.isChecked()){
                                count += 1;
                                textview.setText(String.valueOf(count));
                                handler.postDelayed(this,progressDelay);
                            }
                            if(radioButton2.isChecked()){
                                count -= 1;
                                textview.setText(String.valueOf(count));
                                handler.postDelayed(this,progressDelay);
                            }

                        }
                    };

                }
                else {
                    // The toggle is disabled
                    Toast.makeText(MainActivity.this, "Counting ends",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
