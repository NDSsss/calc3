package com.example.nds.calc3;

import android.view.View;
import android.widget.TextView;

public class ButtonClicks {
    TextView textViewMain,textViewHistory;
    Calculator calculator;
    int openBrackets=0,closeBracets=0;

    public ButtonClicks(TextView textViewMain,TextView textViewHistory){
        this.textViewMain=textViewMain;
        this.textViewHistory = textViewHistory;
        calculator = new Calculator();
    }

    public void onFigureClick(View v) {
        String text = textViewMain.getText().toString();
        if (text.equals("0"))
            textViewMain.setText(v.getTag().toString());
        else
            textViewMain.setText(text + v.getTag().toString());
    }


    public void onOperationClick(View v){
        String text = textViewMain.getText().toString();
        if(isFigure(text.substring(text.length()-1,text.length()))||text.substring(text.length()-1,text.length()).equals(")"))
            textViewMain.setText(text+v.getTag().toString());
    }
    public void onRemoveClick(View v){
        String text = textViewMain.getText().toString();
        if(text.length()==1)
            textViewMain.setText("0");
        else
            textViewMain.setText(text.substring(0,text.length()-1));
    }
    public void onEqualClick(View v){

        if(textViewHistory.getText().toString().equals("")){
            textViewHistory.setText(textViewMain.getText().toString() + "=" + calculator.calculate(textViewMain.getText().toString()));
        }
        else {
            textViewHistory.setText(textViewHistory.getText().toString() + '\n' + textViewMain.getText().toString() + "=" + calculator.calculate(textViewMain.getText().toString()));
        }
        textViewMain.setText("0");

    }
    public void onClearClick(View v){
        textViewMain.setText("0");
        openBrackets=0;
        closeBracets=0;
    }
    public void onBracetsClick(View v){
        String text =textViewMain.getText().toString();
        if(text.length()>2) {
            if (v.getTag().toString().equals("(")) {
                if (isOperation(text.substring(text.length() - 1, text.length()))) {
                    textViewMain.setText(text + "(");
                    openBrackets++;
                }
            }
            else{
                if(isFigure(text.substring(text.length()-1,text.length()))&&closeBracets<openBrackets)
                {
                    textViewMain.setText(text+")");
                    closeBracets++;
                }
            }
        }

    }
    public void onPointClick(View v){
        String text=textViewMain.getText().toString();
        int start=1;
        char c;
        String ch;
        if(text.length()>0){


            if(text.contains("+")||text.contains("-")||text.contains("/")||text.contains("*")){
                for(int i=text.length()-1;i>1;i--)
                {
                    c=text.charAt(i);
                    ch=String.valueOf(c);
                    if(ch.equals("+")||ch.equals("-")||ch.equals("*")||ch.equals("/")) {
                        start = i;
                        break;
                    }
                }
                if(!text.substring(start,text.length()).contains(".")){
                    textViewMain.setText(text+".");
                    return;
                }
            }
            if(!text.contains(".")) {
                textViewMain.setText(text+".");
                return;
            }
        }

    }

    public boolean isFigure(String c){
        if(c.equals("1")||c.equals("2")||c.equals("3")||c.equals("4")||c.equals("5")||
                c.equals("6")||c.equals("7")||c.equals("8")||c.equals("9")||c.equals("0"))
            return true;

        return false;
    }
    public boolean isOperation(String s){
        if(s.equals("+")||s.equals("-")||s.equals("/")||s.equals("*"))
            return true;
        return false;
    }
}