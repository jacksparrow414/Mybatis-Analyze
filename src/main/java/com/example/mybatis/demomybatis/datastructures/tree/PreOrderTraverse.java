package com.example.mybatis.demomybatis.datastructures.tree;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

/**
 * 要记住：树的前序、中序、后序遍历，是指访问root节点的方式，理解了这一点，就明白具体怎么写了
 *
 * 树的前序遍历.
 * 核心思想是使用递归.
 * 既然是前序遍历，那么遍历顺序肯定是 root->左子树->右字树
 * 所以只需要排好顺序，递归调用即可了
 *
 * 前序的含义是：在 root，左子树，右子树中，root节点第一个被访问
 *
 * 拓展：
 * 1、根据一棵树的前序遍历结果和中序遍历结果{@link InOrderTraverse}，构造正确的二叉树
 * 2、根据一棵树的前序遍历结果和中序遍历结果{@link PostOrderTraverse}，构造正确的二叉树
 *
 * 以第1个问题为例推断
 * 前序遍历结果是[A, B, D, H, K, E, C, F, I, G, J]
 * 中序遍历结果是[H, K, D, B, E, A, I, F, C, G, J]
 *
 * 根据前序、中序、后序的含义可知
 * A是根节点，那么在中序遍历里，A左边的H,K,D,B,E是左子树部分，而I,F,C,G,J是右子树部分
 * 先看左子树部分：
 * 那么接下来看前序遍历中第二个元素B，说明B是左子树的根节点，根据上面一步相同的判断，B的左子树部分为H,K,D,右子树就彻底确定下来了,是E
 * 同理，继续判断D，D是B左子树下的根节点，那么结合中序遍历结果D的左子树部分为H,K，D没有右子树
 * 继续，推断出K是H的右子树
 * 接下来看右子树部分：
 * 由于左子树部分应清晰了，那么前序遍历中可以先不看左子树的数据，剩下的就是A,C,F,I,G,J，那么第一个右子树是以C为根节点的
 * 结合中序遍历推断出C的左子树部分为I,F右子树部分为G,J
 * 同理推断，F是C的左子树，结合中序遍历确定I是F的左子树
 * 同理，G是C的右子树，J是G的右子树
 *
 * 经过上面完整的推断则原始的树应该为：
 * @author jacksparrow414
 * @date 2021/3/7
 */
@Slf4j
public class PreOrderTraverse {
    
    public void preOrder(BinaryTree binaryTree) {
        if (binaryTree == null) {
            return;
        }
        // 先拿当前节点值
        String currentNode = binaryTree.getVal();
        log.info("当前节点值为{}", currentNode);
        // 再递归调用左子树
        preOrder(binaryTree.getLeft());
        // 再递归调用右字树
        preOrder(binaryTree.getRight());
    }
    
    public void preOrderAndPrint(BinaryTree binaryTree, @NonNull List<String> list) {
        if (binaryTree == null) {
            return;
        }
        list.add(binaryTree.getVal());
        preOrderAndPrint(binaryTree.getLeft(), list);
        preOrderAndPrint(binaryTree.getRight(), list);
    }
    
    public static void main(String[] args) {
        BinaryTree tree = TreeUtil.generateBinaryTree();
        PreOrderTraverse preOrderTraverse = new PreOrderTraverse();
        preOrderTraverse.preOrder(tree);
        List<String> nodes = Lists.newArrayList();
        preOrderTraverse.preOrderAndPrint(tree, nodes);
        log.info("前序遍历的结果是{}", nodes);
    }
}
