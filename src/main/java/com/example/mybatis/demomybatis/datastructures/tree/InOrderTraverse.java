package com.example.mybatis.demomybatis.datastructures.tree;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Stack;
import lombok.extern.slf4j.Slf4j;

/**
 * 树的【中序】遍历，核心思想同样是利用递归
 * 中序遍历含义：左子树->root->右字树
 * 当前节点如果有左子树，一直遍历下去，所以下面的方法里遍历左子树的方法在前面
 * 当遍历到叶子节点的时候，其下面已经没有左子树，那么打印当前叶子节点的值
 *
 * 中序的含义是：在左子树，root，右子树当中，root节点在中间位置被访问
 * @author jacksparrow414
 * @date 2021/3/7
 */
@Slf4j
public class InOrderTraverse {
    
    public void inOrderTraverse(BinaryTree tree) {
        if (tree == null) {
            return;
        }
        inOrderTraverse(tree.getLeft());
        String node = tree.getVal();
        log.info("当前节点值为{}", node);
        inOrderTraverse(tree.getRight());
    }
    
    public void inOrderTraverseAndPrint(BinaryTree tree, List<String> list) {
        if (tree == null) {
            return;
        }
        inOrderTraverseAndPrint(tree.getLeft(), list);
        String node = tree.getVal();
        list.add(node);
        log.info("当前节点值为{}", node);
        inOrderTraverseAndPrint(tree.getRight(), list);
    }
    
    /**
     * 非递归中序遍历.和{@link PreOrderTraverse#preOrderNonRecursive(BinaryTree, List)}一样，只不过位置变了而已
     * @param tree 二叉树
     * @param list list
     */
    public void inOrderNonRecursive(BinaryTree tree, List<String> list) {
        Stack<BinaryTree> stack = new Stack<>();
        while (tree !=null || !stack.isEmpty()) {
            while (tree != null) {
                stack.push(tree);
                tree = tree.getLeft();
            }
            if (!stack.isEmpty()) {
                tree = stack.pop();
                list.add(tree.getVal());
                tree = tree.getRight();
            }
        }
    }
    public static void main(String[] args) {
        BinaryTree tree = TreeUtil.generateBinaryTree();
        InOrderTraverse inOrderTraverse = new InOrderTraverse();
//        inOrderTraverse.inOrderTraverse(tree);
        List<String> nodes = Lists.newArrayList();
//        inOrderTraverse.inOrderTraverseAndPrint(tree, nodes);
        
        inOrderTraverse.inOrderNonRecursive(tree, nodes);
        log.info("中序遍历结果是{}", nodes);
    }
}
