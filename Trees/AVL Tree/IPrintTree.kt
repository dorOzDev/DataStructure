package datastructures.avltree

interface IPrintTree {

    fun printTree(traversalOrder: TreeTraversalsOrder)
}

enum class TreeTraversalsOrder {

    PRE_ORDER,
    IN_ORDER,
    POST_ORDER
}