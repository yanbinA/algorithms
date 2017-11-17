package com.yanbin.algorithms;

import com.yanbin.algorithms.structure.Stacks;

import java.util.Scanner;

/**
 * 将中缀表达式 转换 后缀表达式，个位数运算
 * @author yanbin
 * @date 2017/11/16 17:10
 */
public class InToPost {

    private StringBuffer output = new StringBuffer();
    private String input;
    private Stacks<Character> operators = new Stacks<>();
    private Stacks<Integer> numbers = new Stacks<>();

    private final static char LEFT_PAREN = '(';

    public InToPost(String in) {
        this.input = in;
    }

    public String toTrans() {

        for (char ch : input.toCharArray()) {
            switch (ch) {
                case '+':
                case '-':
                    getOperator(ch, 1);
                    break;
                case '*':
                case '/':
                    getOperator(ch, 2);
                    break;
                case '(':
                    operators.push(ch);
                    break;
                case ')':
                    getParen(ch);
                    break;
                default:
                    this.output.append(ch);
                    break;
            }
        }

        while (!operators.isEmpty()) {
            this.output.append(operators.pop());
        }
        return output.toString();
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
            this.output.append(operators.pop());
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
                output.append(opPop);
            }
        }
        operators.push(opThis);

    }

    /**
     * 计算后缀表达式
     * @param input 后缀表达式
     * @return  计算结果
     */
    public int doParse(String input) {
        for (char ch : input.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                numbers.push(Integer.parseInt(ch + ""));
            } else {
                int num1 = numbers.pop();
                int num2 = numbers.pop();
                int result = 0;
                switch (ch) {
                    case '+':
                        result = num2 + num1;
                        break;
                    case '-':
                        result = num2 - num1;
                        break;
                    case '*':
                        result = num2 * num1;
                        break;
                    case '/':
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
            InToPost post = new InToPost(line);
            String out = post.toTrans();

            System.out.printf("后缀表达式%s,结果:%d%n", out, post.doParse(out));
        }
    }

}
