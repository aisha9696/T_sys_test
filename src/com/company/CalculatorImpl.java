package com.company;


import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Aisha on 01.09.2018.
 */
public class CalculatorImpl implements Calculator {

    private Stack<String> operationStack = new Stack<>();
    private Stack<String> outputStack = new Stack<>();
    DecimalFormat decimalFormat = new DecimalFormat("##.####");


    private boolean isNumber(String token){
        try {
            Double.parseDouble(token);
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    private static int precedence(String token){
        if (token.equals("+") || token.equals("-")){
            return 1;
        }
        else if (token .equals("*") || token.equals("/")) {
            return 2;
        }
        else return 0;
    }

    private static boolean isOperator(String token){
        return precedence(token) > 0;
    }

    private boolean isOpenBrackets(String token){
        return token.equals("(");
    }

    private boolean isCloseBrackets(String token){
        return token.equals(")");
    }

    @Override
    public String Evaluate(String evaluation) {
       return calculate(shunting_yard(evaluation));
    }

    // Алгоритм Сортировочной станции посмотрела из википедии
    // https://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BE%D1%87%D0%BD%D0%BE%D0%B9_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B8
    private Stack<String> shunting_yard (String evaluation){
        try{
            evaluation = evaluation.replace(" ","").replace("(-", "(0-").replace(",-", ",0-");
            StringTokenizer stringTokenizer = new StringTokenizer(evaluation,"+*-/()",true);
            operationStack.clear();
            outputStack.clear();
            while (stringTokenizer.hasMoreTokens()){
                String token = stringTokenizer.nextToken();
                if (isOpenBrackets(token)){
                    operationStack.push(token);
                }
                else if (isCloseBrackets(token)){
                    while (!operationStack.empty() && !isOpenBrackets(operationStack.lastElement())){
                        outputStack.push(operationStack.pop());
                    }
                    operationStack.pop();
                }
                else if (isNumber(token)){
                    outputStack.push(token);
                }
                else if (isOperator(token)){
                    while (!operationStack.empty() && isOperator(operationStack.lastElement()) && precedence(operationStack.peek()) >= precedence(token)){
                        outputStack.push(operationStack.pop());
                    }
                    operationStack.push(token);
                }
            }
            while (!operationStack.empty()){
                outputStack.push(operationStack.pop());
            }
            //Collections.reverse(outputStack);
            return outputStack;

        }catch (Exception e){
            return null;
        }

    }
    // решение уровнения с помощью постфиксной формы посмотрела из
    // https://www.youtube.com/watch?v=y_snKkv0gWc
    private String calculate (Stack<String> outputStack){
        if(outputStack!=null){
            operationStack.clear();
            for (String token : outputStack){
                if (isNumber(token)){
                    operationStack.push(token);
                }else if (isOperator(token)){
                    if(token.equals("+")){
                        Double result = Double.parseDouble(operationStack.pop()) + Double.parseDouble(operationStack.pop());
                        operationStack.push(result.toString());
                    }
                    else if(token.equals("-")){
                        Collections.reverse(operationStack);
                        Double result = Double.parseDouble(operationStack.pop())- Double.parseDouble(operationStack.pop());
                        operationStack.push(result.toString());
                    }else if(token.equals("*")){
                        Double result = Double.parseDouble(operationStack.pop()) * Double.parseDouble(operationStack.pop());
                        operationStack.push(result.toString());
                    }
                    else if(token.equals("/")){
                        Collections.reverse(operationStack);
                        Double result = Double.parseDouble(operationStack.pop()) / Double.parseDouble(operationStack.pop());
                        operationStack.push(result.toString());
                    }
                }
                else if(isOpenBrackets(token) || isCloseBrackets(token)){
                    return null;

                }
            }
            return decimalFormat.format(Double.parseDouble(operationStack.pop()));
        }else{
            return null;
        }

    }

}


