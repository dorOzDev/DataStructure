package datastructures.avltree

import java.lang.Integer.max
import java.util.*

data class Node(val key: Int, var height: Int = 0, var left: Node? = null, var right: Node? = null)

class AVLTree : IPrintTree {

    private var root: Node? = null
    private fun height(node: Node?): Int =
        node?.height ?: -1

    private fun leftRotation(node: Node): Node {
        // If a right rotation is needed, than it's safe to assume node.right is not null
        val right = node.right!!
        val rightLeft = right.left

        node.right = rightLeft
        right.left = node

        updateNodeHeight(node)
        updateNodeHeight(right)
        updateRoot(node, right)

        return right
    }

    private fun rightRotation(node: Node): Node {
        // If a left rotation is needed, than it's safe to assume node.left is not null
        val left = node.left!!
        val leftRight = left.right

        left.right = node
        node.left = leftRight

        updateNodeHeight(node)
        updateNodeHeight(left)
        updateRoot(node, left)

        return left
    }

    private fun updateRoot(node: Node, newNode: Node) {

        if(node == root) {
            root = newNode
        }
    }

    private fun getBalance(node: Node?): Int {
        if(node == null) return -1

        return height(node.right) - height(node.left)
    }

    fun insert(key: Int): Node {

        if(root == null) {
            val node = Node(key)
            root = node
            return root ?: node
        }

        return insertHelper(root, key)
    }

    fun insertAll(list: Array<Int>) {

        for(elem in list) {
            insert(elem)
        }
    }

    fun insertAll(list: Collection<Int>) {

        for(elem in list) {
            insert(elem)
        }
    }

    private fun insertHelper(node: Node?, key: Int): Node {

        if(node == null) return Node(key)

        when {
            (key < node.key) -> {
                node.left = insertHelper(node.left, key)
            }

            (node.key < key) -> {
                node.right = insertHelper(node.right, key)
            }
            // Duplicates are forbidden
            else -> return node
        }

        updateNodeHeight(node)
        return fixRotation(node, key)
    }

    private fun fixRotation(node: Node, key: Int): Node {

        when(getBalance(node)) {
            in 2..Int.MAX_VALUE -> {
                return fixRightUnbalanceHeight(node, key)
            }

            in Int.MIN_VALUE..-2 -> {
                return fixLeftUnbalanceHeight(node, key)
            }
        }

        return node
    }

    private fun fixLeftUnbalanceHeight(node: Node, key: Int): Node {
        // If there's unbalance on the left side, than it's safe to assume the right node is not null
        val left = node.left!!
        if(key < left.key)
            return rightRotation(node)
        if(left.key < key) {
            node.left = leftRotation(left)
            return rightRotation(node)
        }

        return node
    }

    private fun fixRightUnbalanceHeight(node: Node, key: Int): Node {
        // If there's unbalance on the right side, than it's safe to assume the right node is not null
        val right = node.right!!
        if(right.key < key)
            return leftRotation(node)
        if(key < right.key) {
            node.right = rightRotation(right)
            return leftRotation(node)
        }

        return node
    }

    private fun updateNodeHeight(node: Node) {
        node.height = max(height(node.right), height(node.left)) + 1
    }

    private fun preOrderPrint(node: Node?) {
        if(node != null) {
            print("${node.key} ")
            preOrderPrint(node.left)
            preOrderPrint(node.right)
        }
    }

    private fun postOrderPrint(node: Node?) {
        if(node != null) {
            postOrderPrint(node.left)
            postOrderPrint(node.right)
            print("${node.key} ")
        }

    }

    private fun inOrderPrint(node: Node?) {
        if(node != null) {
            inOrderPrint(node.left)
            print("${node.key} ")
            inOrderPrint(node.right)
        }

    }

    fun getTree(traversalOrder: TreeTraversalsOrder): List<Int> = when(traversalOrder) {

        TreeTraversalsOrder.PRE_ORDER ->  {
            getPreOrderTree()
        }
        TreeTraversalsOrder.IN_ORDER -> TODO("Not yet implemented")
        TreeTraversalsOrder.POST_ORDER -> TODO("Not yet implemented")
    }

    private fun getPreOrderTree(): List<Int> {
        val stack = Stack <Node>()
        val list = mutableListOf<Int>()

        root?.let { stack.push(it) }

        while(!stack.isEmpty()) {
            val elem = stack.pop()
            list.add(elem.key)
            elem?.right?.let { stack.push(it) }
            elem?.left?.let { stack.push(it) }
        }

        return list
    }

    override fun printTree(traversalOrder: TreeTraversalsOrder): Unit = when(traversalOrder) {
            TreeTraversalsOrder.PRE_ORDER -> {
                preOrderPrint(root)
                println()
            }

            TreeTraversalsOrder.POST_ORDER -> {
                postOrderPrint(root)
                println()
            }

            TreeTraversalsOrder.IN_ORDER -> {
                inOrderPrint(root)
                println()
            }
        }
}

