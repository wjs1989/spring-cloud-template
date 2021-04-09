package com.wjs.produce;

public class ToolsTest {
    /*
    @Test
    public void Test1() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties("application.yml", ClassUtils.getDefaultClassLoader());
//        System.out.println(properties);
    }*/

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        head.next = tail;
        for (int i = 0; i < 10; i++) {
            tail.next = new ListNode(i + 1);
            tail = tail.next;
        }
        ToolsTest.printNode(head);
        ToolsTest.printNode(ToolsTest.reverseList(head));
    }

    public static void printNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            System.out.print(" ");
            head = head.next;
        }
        System.out.println("");
    }

    public static ListNode reverseList(ListNode head) {

        ListNode tail = head;
        if (tail.next == null) {
            return tail;
        }

        ListNode nHead = tail.next;
        tail.next = null;

        do {
            ListNode sHead = nHead;
            nHead = sHead.next;
            sHead.next = tail;
            tail = sHead;

        } while (nHead != null);
        return tail;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


}
