package com.example.mybatis.demomybatis.algorithms;

import com.example.mybatis.demomybatis.datastructures.List.ListNode;
import com.example.mybatis.demomybatis.datastructures.List.ListUtil;

/**
 * 反转链表.
 * 1->2->3->4->5->NULL
 * 5->4->3->2->1->NULL
 *
 * 将1和2之间断开
 * 将1设置为2的next
 * 然后把2设置为当前节点，重复操作
 * @author jacksparrow414
 * @date 2021/4/14 11:20
 */
public class ListNodeReverse {

    public void reverseList(com.example.mybatis.demomybatis.datastructures.List.ListNode head) {
        com.example.mybatis.demomybatis.datastructures.List.ListNode pre = null;
        com.example.mybatis.demomybatis.datastructures.List.ListNode cur = head;
        while (cur != null) {
            com.example.mybatis.demomybatis.datastructures.List.ListNode next = cur.getNext();
            // 将1的下一个设置为null.将2的next设置为1
            cur.setNext(pre);
            // 将前一个设置为当前值
            pre = cur;
            // 将当前值设置为下一个
            cur = next;
        }
        System.out.println("链表翻转完毕");
        com.example.mybatis.demomybatis.datastructures.List.ListNode node = pre;
        while (node != null) {
            System.out.println(node.getVal());
            node = node.getNext();
        }
    }

    public static void main(String[] args) {
        ListNode head = ListUtil.generateListNode();
        ListNodeReverse reverse = new ListNodeReverse();
        reverse.reverseList(head);
    }
}
