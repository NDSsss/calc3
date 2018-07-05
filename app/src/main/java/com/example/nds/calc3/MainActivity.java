package com.example.nds.calc3;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView textViewMain,textViewHistory;
    Calculator calculator;
    ButtonClicks buttonClicks;
    public static final String MAINTEXT="textViewMain",HISTROYTEXT="textViewHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewMain = (TextView)findViewById(R.id.textViewMain);
        textViewMain.setMovementMethod(new ScrollingMovementMethod());
        textViewHistory = (TextView)findViewById(R.id.textViewHistory);
        textViewHistory.setMovementMethod(new ScrollingMovementMethod());
        calculator=new Calculator();
        buttonClicks = new ButtonClicks(textViewMain,textViewHistory);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MAINTEXT,textViewMain.getText().toString());
        outState.putString(HISTROYTEXT,textViewHistory.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
            textViewMain.setText(savedInstanceState.getString(MAINTEXT));
            textViewHistory.setText(savedInstanceState.getString(HISTROYTEXT));
        }
    }


    public void onPointClick(View view) {
        buttonClicks.onPointClick(view);
    }

    public void onFigureClick(View view) {
        buttonClicks.onFigureClick(view);
    }

    public void onOperationClick(View view) {
        buttonClicks.onOperationClick(view);
    }

    public void onEqualClick(View view) {
        buttonClicks.onEqualClick(view);
    }

    public void onClearClick(View view) {
        buttonClicks.onClearClick(view);
    }

    public void onRemoveClick(View view) {
        buttonClicks.onRemoveClick(view);
    }

    public void onBracetsClick(View view) {
        buttonClicks.onBracetsClick(view);
    }
}
