package com.yanbin.algorithms.structure;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TypeHost;

/**
 * 将中缀表达式 转换 后缀表达式
 * @author yanbin
 * @date 2017/11/16 17:10
 */
public class InToPost {

    private StringBuffer output = new StringBuffer();
    private String input;
    private Stacks<Character> operators = new Stacks<>();

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
     *  栈非空，
     * @param opThis 运算符
     * @param priority 运算符等级 2:* / 1:+ -
     */
    private void getOperator(char opThis, int priority) {

    }

}
