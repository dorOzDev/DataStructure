package datastructures.avltree

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TestAVLTree {

    private val avlTree = AVLTree()

    @Test
    fun testInsertion() {

        val arr = arrayOf(10, 20, 30, 40, 50, 25)

        val expected = listOf(30, 20, 10, 25, 40, 50)

        avlTree.insertAll(arr)
        assertEquals(expected, avlTree.getTree(TreeTraversalsOrder.PRE_ORDER))
    }
}
