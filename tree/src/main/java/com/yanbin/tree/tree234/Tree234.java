package com.yanbin.tree.tree234;

/**
 * 2-3-4树
 * @author yanbin
 * @date 2017/12/7 15:21
 */
public class Tree234 {

    private Node root;

    /**
     * 根据指定key查找数据项
     * @param key   指定key
     * @return  查找道德数据项，null:数据项不存在
     */
    public DataItem findItem(int key) {

        int childNum = root.find(key);
        if (childNum != -1) {
            //
        }

        return null;
    }




    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
