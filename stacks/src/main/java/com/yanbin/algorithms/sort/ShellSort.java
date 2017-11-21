package com.yanbin.algorithms.sort;

/**
 * 希尔排序
 * 基于插入排序，但在引入一条新特性：加大插入排序元素间的间隔(h)，从而使数据能大跨度移动，减小间隔在进行排序，以此类推，知道完成 h = 1的排序。
 * 经过大间隔的排序，数组已基本有序，小间隔排序的移动次数减少，相比插入排序的效率有较大提升。
 * 间隔取值：1 -4 -13- h*3 + 1,
 * 也可取其他间隔序列，必须有h=1的间隔
 * 算法复杂度: O(N*(logN)^2)
 * @author yanbin
 * @date 2017/11/21 14:02
 */
public class ShellSort {

    public ShellSort(int[] arr) {
        int h = 1,inner,outer;
        //获取可排序的最大间隔
        while (h <= ((arr.length - 1) / 3)) {
            h = h*3 + 1;
        }

        while (h >= 1) {
            for (outer = h; outer < arr.length; outer++) {
                int temp = arr[outer];
                inner = outer;
                while (inner >= h && temp <= arr[inner - h]) {
                    arr[inner] = arr[inner - h];
                    inner -= h;
                }
                arr[inner] = temp;
            }

            h = (h - 1) / 3;
        }


    }
}
