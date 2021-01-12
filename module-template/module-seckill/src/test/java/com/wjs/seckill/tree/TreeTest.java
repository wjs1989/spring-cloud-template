package com.wjs.seckill.tree;

public class TreeTest {

    public static void main(String[] args) {
        Node root = new Node("A");

        Node left = new Node("B");
        Node right = new Node("C");
        setNode(root,left,right);





    }

    public static void setNode(Node root,Node left,Node right){
        root.setLeft(left);
        root.setRight(right);
    }


    static class Node {
        Node left;
        Node right;

        private String value;

        public Node(String value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
