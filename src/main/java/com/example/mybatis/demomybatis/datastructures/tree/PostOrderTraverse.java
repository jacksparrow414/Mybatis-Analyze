package com.example.mybatis.demomybatis.datastructures.tree;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * 后序遍历和其他两种一样，同样是递归
 * 后序的含义是：左子树->右子树->root，最后一次访问root节点
 * @author jacksparrow414
 * @date 2021/3/7
 */
@Slf4j
public class PostOrderTraverse {
    
    public void postOrder(BinaryTree tree) {
        if (tree == null) {
            return;
        }
        postOrder(tree.getLeft());
        postOrder(tree.getRight());
        String currentNode = tree.getVal();
        log.info("当前节点值为{}", currentNode);
    }
    
    public void postOrderAndPrint(BinaryTree tree, List<String> list) {
        if (tree == null) {
            return;
        }
        postOrderAndPrint(tree.getLeft(), list);
        postOrderAndPrint(tree.getRight(), list);
        String currentNode = tree.getVal();
        list.add(currentNode);
    }
    
    public static void main(String[] args) {
        BinaryTree tree = TreeUtil.generateBinaryTree();
        PostOrderTraverse postOrderTraverse = new PostOrderTraverse();
        postOrderTraverse.postOrder(tree);
        List<String> nodes = Lists.newLinkedList();
        postOrderTraverse.postOrderAndPrint(tree, nodes);
        log.info("后序遍历的结果是{}", nodes);
    }
}
