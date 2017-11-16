package com.yanbin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanbin
 * @date 2017/11/16 12:23
 */
public class Person {

    private int position;

    public Person(){}

    public Person(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Person{" +
                "position=" + position +
                '}';
    }

    public static void main(String[] args) {
        List<Person> list = initList(3);

        List<Person> personList = remove(list, 1, 3);

        System.out.println(personList.toString());

    }

    private static List<Person> initList(int size) {
        List<Person> list = new ArrayList<>();

        for (int i = 1; i <= size;) {
            Person person = new Person(i++);
            list.add(person);
        }

        return list;
    }

    /**
     * 移除指定集合中的元素
     * @param list  指定集合
     * @param start  开始数数值
     * @param number    被移除的规则
     * @return
     */
    private static List<Person> remove(List<Person> list, int start, int number) {
        if (list == null) {
            throw new NullPointerException();
        }



        for(int i = 0; i < list.size();) {
            if ((start++ / (number)) == 0) {
                System.out.println(list.get(i) + " 报数" + (start - 1) + " -------被移除");
                list.remove(i);
            } else {
                System.out.println(list.get(i) + " 报数" + (start - 1));
                i++;
            }
        }

        if (list.size() > 1) {
            return remove(list, start, number);
        } else {
            return list;
        }


    }



}
