package com.example.nds.calc3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
public class Calculator {
    public String startCalculate(String text) {
            String sIn;

            try {
                sIn = opn(text);
                return (String.valueOf(calculate(sIn)));
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        /**
         * Преобразовать строку в обратную польскую нотацию
         * @param sIn Входная строка
         * @return Выходная строка в обратной польской нотации
         */
        private static String opn(String sIn) throws Exception {
            StringBuilder sbStack = new StringBuilder(""), sbOut = new StringBuilder("");
            char cIn, cTmp;

            for (int i = 0; i < sIn.length(); i++) {
                cIn = sIn.charAt(i);
                if (isOp(cIn)) {
                    while (sbStack.length() > 0) {
                        cTmp = sbStack.substring(sbStack.length()-1).charAt(0);
                        if (isOp(cTmp) && (opPrior(cIn) <= opPrior(cTmp))) {
                            sbOut.append(" ").append(cTmp).append(" ");
                            sbStack.setLength(sbStack.length()-1);
                        } else {
                            sbOut.append(" ");
                            break;
                        }
                    }
                    sbOut.append(" ");
                    sbStack.append(cIn);
                } else if ('(' == cIn) {
                    sbStack.append(cIn);
                } else if (')' == cIn) {
                    cTmp = sbStack.substring(sbStack.length()-1).charAt(0);
                    while ('(' != cTmp) {
                        if (sbStack.length() < 1) {
                            throw new Exception("Ошибка разбора скобок. Проверьте правильность выражения.");
                        }
                        sbOut.append(" ").append(cTmp);
                        sbStack.setLength(sbStack.length()-1);
                        cTmp = sbStack.substring(sbStack.length()-1).charAt(0);
                    }
                    sbStack.setLength(sbStack.length()-1);
                } else {
                    // Если символ не оператор - добавляем в выходную последовательность
                    sbOut.append(cIn);
                }
            }

            // Если в стеке остались операторы, добавляем их в входную строку
            while (sbStack.length() > 0) {
                sbOut.append(" ").append(sbStack.substring(sbStack.length()-1));
                sbStack.setLength(sbStack.length()-1);
            }

            return  sbOut.toString();
        }

        /**
         * Функция проверяет, является ли текущий символ оператором
         */
        private static boolean isOp(char c) {
            switch (c) {
                case '-':
                case '+':
                case '*':
                case '/':
                    return true;
            }
            return false;
        }

        /**
         * Возвращает приоритет операции
         * @param op char
         * @return byte
         */
        private static byte opPrior(char op) {
            switch (op) {
                case '^':
                    return 3;
                case '*':
                case '/':
                case '%':
                    return 2;
            }
            return 1; // Тут остается + и -
        }

        /**
         * Считает выражение, записанное в обратной польской нотации
         * @param sIn
         * @return double result
         */
        private static double calculate(String sIn) throws Exception {
            double dA = 0, dB = 0;
            String sTmp;
            Deque<Double> stack = new ArrayDeque<Double>();
            StringTokenizer st = new StringTokenizer(sIn);
            while(st.hasMoreTokens()) {
                try {
                    sTmp = st.nextToken().trim();
                    if (1 == sTmp.length() && isOp(sTmp.charAt(0))) {
                        if (stack.size() < 2) {
                            throw new Exception("Неверное количество данных в стеке для операции " + sTmp);
                        }
                        dB = stack.pop();
                        dA = stack.pop();
                        switch (sTmp.charAt(0)) {
                            case '+':
                                dA += dB;
                                break;
                            case '-':
                                dA -= dB;
                                break;
                            case '/':
                                dA /= dB;
                                break;
                            case '*':
                                dA *= dB;
                                break;
                            default:
                                throw new Exception("Недопустимая операция " + sTmp);
                        }
                        stack.push(dA);
                    } else {
                        dA = Double.parseDouble(sTmp);
                        stack.push(dA);
                    }
                } catch (Exception e) {
                    throw new Exception("Недопустимый символ в выражении");
                }
            }


            return stack.pop();
        }

}
