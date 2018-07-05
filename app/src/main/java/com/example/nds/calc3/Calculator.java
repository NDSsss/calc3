package com.example.nds.calc3;

/**
 *@about This is program named "calculator", for calculating string entered by user.
 *
 *@author Shumilin "Shuma" Alexandr
 */
public class Calculator //Main class
{
    private StringBuilder cstring = new StringBuilder();//for user string storage
    private boolean PlusSign = true;//saving result sign
    private int CloseBracket,OpenBracket;//saving start and finish substring positions
    private int FinishPos = 0, StartPos = 0;//saving start and finish expression part positions



    private int valueCalc(String input)//calculating how many slots are needed for secondary array
    {
        int Slots = 0;
        for (int i = 0; i < input.length(); i++)
        {
            if ((input.charAt(i) == '+') || (input.charAt(i) == '-') ||
                    (input.charAt(i) == '*') || (input.charAt(i) == '/')||
                    (input.charAt(i) == ')')|| (input.charAt(i) == '('))
            {
                Slots++;
            }
        }
        return Slots;
    }
    public String calculate(String input)//main method
    {
        int[] CharsPos = new int[valueCalc(input)+2];//"+2" because also needed
        //start and finish string positions
        float a = 0, b = 0;//for parse parts
        Float c = null;//result between parts
        cstring.delete(0, cstring.length());//cleaning
        cstring.append(input);
        /////////////////////////////////////////////////////////////////////////
//<first priority block>
        for (int i = 0; i < cstring.length(); i++)
        {
            if(cstring.charAt(i)=='(')
            {
                OpenBracket=i;
            }
            if(cstring.charAt(i)==')')//for locating max priority
            {
                CloseBracket=i;
                String subString=new String();
                subString=cstring.substring(OpenBracket+1, CloseBracket);
                cstring.delete(OpenBracket, CloseBracket+1);
                StringBuilder mainString=new StringBuilder(cstring);
                calculate(subString);
                mainString.insert(OpenBracket,cstring.toString());
                cstring.delete(0, cstring.length());
                cstring.insert(0, mainString.toString());
                calculate(cstring.toString());
            }
        }
//</first priority block>
        ////////////////////////////////////////////////////////////////////////
//<secondary priority block>
        for (int i = 0; i < cstring.length(); i++)
        {
            if (cstring.charAt(i) == '*')
            {
                CharsPos[0]=0;
                for (int j = 0,  k = 1; j < cstring.length()-1; j++)
                {
                    if ((cstring.charAt(j) == '+') || (cstring.charAt(j) == '-') ||
                            (cstring.charAt(j) == '*') || (cstring.charAt(j) == '/')||
                            (cstring.charAt(j) == '(')|| (cstring.charAt(j) == ')'))
                    {
                        CharsPos[k] = j+1;
                        k++;
                    }
                    CharsPos[CharsPos.length-1]=cstring.length()+1;
                }
                for(int k=0;k<CharsPos.length;k++)
                {

                    if(CharsPos[k]==i+1)
                    {
                        StartPos=CharsPos[k-1];
                        FinishPos=CharsPos[k+1]-1;
                    }
                }

                a = Float.parseFloat(cstring.substring(StartPos, i));
                b = Float.parseFloat(cstring.substring(i + 1, FinishPos));
                cstring.delete(StartPos, FinishPos);
                c = a * b;
                cstring.insert(StartPos, c.toString());
                calculate(cstring.toString());
                break;
            }
            if (cstring.charAt(i) == '/')
            {
                CharsPos[0]=0;
                for (int j = 0,  k = 1; j < cstring.length()-1; j++)
                {
                    if ((cstring.charAt(j) == '+') || (cstring.charAt(j) == '-') ||
                            (cstring.charAt(j) == '*') || (cstring.charAt(j) == '/')||
                            (cstring.charAt(j) == '(')|| (cstring.charAt(j) == ')'))
                    {
                        CharsPos[k] = j+1;
                        k++;
                    }
                    CharsPos[CharsPos.length-1]=cstring.length()+1;
                }
                for(int k=0;k<CharsPos.length;k++)
                {

                    if(CharsPos[k]==i+1)
                    {
                        StartPos=CharsPos[k-1];
                        FinishPos=CharsPos[k+1]-1;
                    }
                }

                a = Float.parseFloat(cstring.substring(StartPos, i));
                b = Float.parseFloat(cstring.substring(i + 1, FinishPos));
                cstring.delete(StartPos, FinishPos);
                c = a / b;
                cstring.insert(StartPos, c.toString());
                calculate(cstring.toString());
                break;
            }

        }
        for (int i = 0; i < cstring.length(); i++)
        {
            if ((cstring.charAt(i) == '*') || (cstring.charAt(i) == '/'))
            {
                calculate(cstring.toString());
            }
        }
//</secondary priority block>
        ////////////////////////////////////////////////////////////////////////
        //<third priority block>
        for (int i = 0; i < cstring.length(); i++)
        {
            if ((cstring.charAt(i) == '+') && (PlusSign == true))
            {
                CharsPos[0]=0;
                for (int j = 0,  k = 1; j < cstring.length()-1; j++)
                {
                    if ((cstring.charAt(j) == '+') || (cstring.charAt(j) == '-') ||
                            (cstring.charAt(j) == '*') || (cstring.charAt(j) == '/')||
                            (cstring.charAt(j) == '(')|| (cstring.charAt(j) == ')'))
                    {
                        CharsPos[k] = j+1;
                        k++;
                    }
                    CharsPos[CharsPos.length-1]=cstring.length()+1;
                }
                for(int k=0;k<CharsPos.length;k++)
                {

                    if(CharsPos[k]==i+1)
                    {
                        StartPos=CharsPos[k-1];
                        FinishPos=CharsPos[k+1]-1;
                    }
                }
                a = Float.parseFloat(cstring.substring(StartPos, i));
                b = Float.parseFloat(cstring.substring(i + 1, FinishPos));
                cstring.delete(StartPos, FinishPos);
                c = a + b;
                cstring.insert(StartPos, c.toString());
                break;
            }
            if ((cstring.charAt(i) == '+') && (PlusSign == false))
            {
                CharsPos[0]=0;
                for (int j = 0,  k = 1; j < cstring.length()-1; j++)
                {
                    if ((cstring.charAt(j) == '+') || (cstring.charAt(j) == '-') ||
                            (cstring.charAt(j) == '*') || (cstring.charAt(j) == '/')||
                            (cstring.charAt(j) == '(')|| (cstring.charAt(j) == ')'))
                    {
                        CharsPos[k] = j+1;
                        k++;
                    }
                    CharsPos[CharsPos.length-1]=cstring.length()+1;
                }
                for(int k=0;k<CharsPos.length;k++)
                {

                    if(CharsPos[k]==i+1)
                    {
                        StartPos=CharsPos[k-1];
                        FinishPos=CharsPos[k+1]-1;
                    }
                }
                a = Float.parseFloat(cstring.substring(0, i));
                b = Float.parseFloat(cstring.substring(i + 1, FinishPos));
                cstring.delete(0, FinishPos);
                if (b > a)
                {
                    c = b - a;
                    PlusSign = true;
                }
                else
                {
                    c = a - b;
                }
                cstring.insert(0, c.toString());
                break;
            }
            if ((cstring.charAt(i) == '-') && (PlusSign == true))
            {
                CharsPos[0]=0;
                for (int j = 0,  k = 1; j < cstring.length()-1; j++)
                {
                    if ((cstring.charAt(j) == '+') || (cstring.charAt(j) == '-') ||
                            (cstring.charAt(j) == '*') || (cstring.charAt(j) == '/')||
                            (cstring.charAt(j) == '(')|| (cstring.charAt(j) == ')'))
                    {
                        CharsPos[k] = j+1;
                        k++;
                    }
                    CharsPos[CharsPos.length-1]=cstring.length()+1;
                }
                for(int k=0;k<CharsPos.length;k++)
                {

                    if(CharsPos[k]==i+1)
                    {
                        StartPos=CharsPos[k-1];
                        FinishPos=CharsPos[k+1]-1;
                    }
                }
                a = Float.parseFloat(cstring.substring(0, i));
                b = Float.parseFloat(cstring.substring(i + 1, FinishPos));
                cstring.delete(0, FinishPos);
                if (b > a)
                {
                    c = b - a;
                    PlusSign = false;
                }
                else
                {
                    c = a - b;
                }
                cstring.insert(0, c.toString());
                break;
            }
            if ((cstring.charAt(i) == '-') && (PlusSign == false))
            {
                CharsPos[0]=0;
                for (int j = 0,  k = 1; j < cstring.length()-1; j++)
                {
                    if ((cstring.charAt(j) == '+') || (cstring.charAt(j) == '-') ||
                            (cstring.charAt(j) == '*') || (cstring.charAt(j) == '/')||
                            (cstring.charAt(j) == '(')|| (cstring.charAt(j) == ')'))
                    {
                        CharsPos[k] = j+1;
                        k++;
                    }
                    CharsPos[CharsPos.length-1]=cstring.length()+1;
                }
                for(int k=0;k<CharsPos.length;k++)
                {

                    if(CharsPos[k]==i+1)
                    {
                        StartPos=CharsPos[k-1];
                        FinishPos=CharsPos[k+1]-1;
                    }
                }
                a = Float.parseFloat(cstring.substring(0, i));
                b = Float.parseFloat(cstring.substring(i + 1, FinishPos));
                cstring.delete(0, FinishPos);
                c = a + b;
                cstring.insert(0, c.toString());
                break;
            }
        }
        for (int i = 0; i < cstring.length(); i++)
        {
            if ((cstring.charAt(i) == '+') || (cstring.charAt(i) == '-'))
            {
                calculate(cstring.toString());
            }
        }
        return cstring.toString();
    }
    //</third priority block>

}