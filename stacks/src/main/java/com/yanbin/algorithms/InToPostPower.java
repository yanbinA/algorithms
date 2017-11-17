package com.yanbin.algorithms;

import com.yanbin.algorithms.structure.Stacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 计算 算术表达式 加强版
 * @author yanbin
 * @date 2017/11/17 11:48
 */
public class InToPostPower {
    /**
     * 读取数值
     */
    private StringBuffer outputNum = new StringBuffer();
    /**
     * 后缀表达式 元素列表
     */
    private List<String> outputList = new ArrayList<>();
    private String input;
    private Stacks<Character> operators = new Stacks<>();
    private Stacks<Double> numbers = new Stacks<>();

    private final static char LEFT_PAREN = '(';

    public InToPostPower(String in) {
        this.input = in;
    }

    public List<String> toTrans() {

        for (char ch : input.toCharArray()) {
            switch (ch) {
                case '+':
                case '-':
                    getOutput();
                    getOperator(ch, 1);
                    break;
                case '*':
                case '/':
                    getOutput();
                    getOperator(ch, 2);
                    break;
                case '(':
                    getOutput();
                    operators.push(ch);
                    break;
                case ')':
                    getOutput();
                    getParen(ch);
                    break;
                default:
                    this.outputNum.append(ch);
                    break;
            }
        }

        this.getOutput();

        while (!operators.isEmpty()) {
            this.outputList.add(operators.pop() + "");
        }
        return outputList;
    }

    private void getOutput() {

        if (outputNum.length() > 0) {
            outputList.add(outputNum.toString());
            outputNum.delete(0, outputNum.length());
        }

    }

    /**
     * 读取右括号
     * 将栈中元素写出，
     * 直到遇到 '(' 弹出
     * 结束
     * @param ch 右括号
     */
    private void getParen(char ch) {
        if (operators.isEmpty()) {
            return;
        }

        while (operators.peek() != LEFT_PAREN) {
            this.outputList.add(operators.pop() + "");
        }
        if (!operators.isEmpty()) {
            operators.pop();
        }
    }

    /**
     *  若栈为空，推入
     *  栈非空，循环 读取栈顶：
     *      如果栈顶是 '('，写入栈
     *      如果是运算符 opPop，
     *          opPop.priority < opThis.priority:将opThis写入栈
     *          否则，将opPop弹出，输出
     * @param opThis 运算符
     * @param priority 运算符等级 2:* / 1:+ -
     */
    private void getOperator(char opThis, int priority) {

        while (!operators.isEmpty()) {
            char opPop = operators.pop();
            if (opPop == '(') {
                operators.push(opPop);
                break;
            }

            int priorityPop = 1;
            if (opPop == '*' || opPop == '/') {
                priorityPop = 2;
            }

            if (priority > priorityPop) {
                operators.push(opPop);
                break;
            } else {
                outputNum.append(opPop);
            }
        }
        operators.push(opThis);

    }

    /**
     * 计算后缀表达式
     * @return  计算结果
     */
    public double doParse() {

        for (String elem : outputList) {

            if (elem.matches("[0-9]+(\\.{1}[0-9]+)?")) {
                numbers.push(Double.valueOf(elem));
            } else {
                double num1 = numbers.pop();
                double num2 = numbers.pop();
                double result = 0;
                switch (elem) {
                    case "+":
                        result = num2 + num1;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num2 * num1;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                    default:
                        break;
                }
                numbers.push(result);
            }
        }
        return numbers.pop();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {

            System.out.print("输入中缀表达式：");
            String line = in.nextLine();
            if ("exit".equals(line)) {
                break;
            }
            InToPostPower post = new InToPostPower(line);
            String out = post.toTrans().toString();

            System.out.printf("后缀表达式%s,结果:%.2f%n", out, post.doParse());
        }
    }



}
