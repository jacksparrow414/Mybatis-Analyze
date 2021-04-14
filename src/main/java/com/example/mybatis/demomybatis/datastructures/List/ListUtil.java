package com.example.mybatis.demomybatis.datastructures.List;

/**
 * @author jacksparrow414
 * @date 2021/4/14 11:15
 */
public class ListUtil {

    private ListUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static ListNode generateListNode() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        head.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(fifth);
        return head;
    }
}
