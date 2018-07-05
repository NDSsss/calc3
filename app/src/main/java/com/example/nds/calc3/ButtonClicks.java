package com.example.nds.calc3;

public class ButtonClicks {
    Calculator calculator;
    int openBrackets = 0, closeBracets = 0;
    TextViewsInterface textViewsInterface;

    public ButtonClicks(TextViewsInterface textViewsInterface) {
        calculator = new Calculator();
        this.textViewsInterface = textViewsInterface;
    }

    public void onFigureClick(String text, String tag) {
        if (text.equals("0"))
            textViewsInterface.setMainText(tag);
        else
            textViewsInterface.setMainText(text + tag);
    }


    public void onOperationClick(String text, String tag) {
        if (isFigure(text.substring(text.length() - 1, text.length())) || text.substring(text.length() - 1, text.length()).equals(")"))
            textViewsInterface.setMainText(text + tag);
    }

    public void onRemoveClick(String text) {
        if (text.length() == 1) {
            textViewsInterface.setMainText("0");
            openBrackets = 0;
            closeBracets = 0;
        } else
            textViewsInterface.setMainText(text.substring(0, text.length() - 1));
    }

    public void onEqualClick(String text) {

        if (text.equals("")) {
            textViewsInterface.setHistoryText(text + "=" + calculator.startCalculate(text));
        } else {
            textViewsInterface.setHistoryText(textViewsInterface.getHistoryText() + '\n' + text + "=" + calculator.startCalculate(text));
        }
        textViewsInterface.setMainText("0");
        openBrackets = 0;
        closeBracets = 0;

    }

    public void onClearClick() {
        textViewsInterface.setMainText("0");
        openBrackets = 0;
        closeBracets = 0;
    }

    public void onBracetsClick(String text, String tag) {
        if (text.length() > 1) {
            if (tag.equals("(")) {
                if (isOperation(text.substring(text.length() - 1, text.length()))) {
                    textViewsInterface.setMainText(text + "(");
                    openBrackets++;
                }
            } else {
                if (isFigure(text.substring(text.length() - 1, text.length())) && closeBracets < openBrackets) {
                    textViewsInterface.setMainText(text + ")");
                    closeBracets++;
                }
            }
        } else {
            textViewsInterface.setMainText("(");
            openBrackets++;
        }

    }

    public void onPointClick(String text) {
        int start = 1;
        char c;
        String ch;
        if (text.length() > 0) {


            if (text.contains("+") || text.contains("-") || text.contains("/") || text.contains("*")) {
                for (int i = text.length() - 1; i > 1; i--) {
                    c = text.charAt(i);
                    ch = String.valueOf(c);
                    if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/")) {
                        start = i;
                        break;
                    }
                }
                if (!text.substring(start, text.length()).contains(".")) {
                    textViewsInterface.setMainText(text + ".");
                    return;
                }
            }
            if (!text.contains(".")) {
                textViewsInterface.setMainText(text + ".");
                return;
            }
        }

    }

    public boolean isFigure(String c) {
        if (c.equals("1") || c.equals("2") || c.equals("3") || c.equals("4") || c.equals("5") ||
                c.equals("6") || c.equals("7") || c.equals("8") || c.equals("9") || c.equals("0"))
            return true;

        return false;
    }

    public boolean isOperation(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*"))
            return true;
        return false;
    }
}
