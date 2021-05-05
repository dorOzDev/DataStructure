package datastructures.segmenttree.sumsegmenttree

import kotlin.math.max
import kotlin.math.min

class SumSegmentTreeArrayImpl(val arr: Array<Int>): BaseSumSegmentTree(arr) {

    private val segTree: Array<Int> = Array(2 * arr.size) { 0 }

    init {
        buildSegmentTree()
    }

    override fun getRangeSum(left: Int, right: Int): Int =
        getRangeSumHelper(0, 0, array.size - 1, left, right)

    private fun getRangeSumHelper(vertex: Int,
                                  leftSegment: Int,
                                  rightSegment: Int,
                                  leftQuery: Int,
                                  rightQuery: Int): Int {

        if(leftQuery > rightQuery) return 0

        if(leftQuery == leftSegment && rightQuery == rightSegment) return segTree[vertex]

        val mid = (leftSegment + rightSegment) / 2
        var leftChild = getLeftChildIndex(vertex)
        var rightChild = getRightChildIndex(vertex, mid, leftSegment)

        return getRangeSumHelper(leftChild, leftSegment, mid, leftQuery, min(rightQuery, mid)) +
                getRangeSumHelper(rightChild, mid + 1, rightSegment, max(leftQuery, mid + 1), rightQuery)
    }

    override fun updateSegTree(position: Int, newVal: Int) {
        // First update the original array.
        array[position] = newVal
        // Than update the seg tree with the helper method
        updateSegTreeHelper(0, 0, array.size - 1, position, newVal)
    }

    private fun updateSegTreeHelper(vertex: Int, leftBoundary: Int, rightBoundary: Int, position: Int, newVal: Int) {

        if(rightBoundary == leftBoundary) {
            segTree[vertex] = newVal
        }

        else {
            val mid = (leftBoundary + rightBoundary) / 2
            val leftChild = getLeftChildIndex(vertex)
            val rightChild = getRightChildIndex(vertex, mid, leftBoundary)

            if(position <= mid) {
                updateSegTreeHelper(
                    leftChild,
                    leftBoundary,
                    mid,
                    position,
                    newVal)
            }

            else {
                updateSegTreeHelper(
                    rightChild,
                    mid + 1,
                    rightBoundary,
                    position,
                    newVal)
            }

            segTree[vertex] = segTree[leftChild] + segTree[rightChild]
        }
    }

    override fun buildSegmentTree() {
        buildSegmentTreeHelper(0, 0, arr.size - 1)
    }

    private fun buildSegmentTreeHelper(vertex: Int, leftBoundary: Int, rightBoundary: Int) {

        if(leftBoundary == rightBoundary) {
            segTree[vertex] = arr[leftBoundary]
        }

        else {
            val mid = (leftBoundary + rightBoundary) / 2
            val leftChild = getLeftChildIndex(vertex)
            val rightChild = getRightChildIndex(vertex, mid, leftBoundary)


            buildSegmentTreeHelper(leftChild, leftBoundary, mid)
            buildSegmentTreeHelper(rightChild, mid + 1, rightBoundary)
            segTree[vertex] = segTree[leftChild] + segTree[rightChild]
        }
    }

    fun printSegTree() {

        util.printArray(segTree)
    }

    private fun getLeftChildIndex(vertex: Int): Int =
        vertex + 1

    private fun getRightChildIndex(vertex: Int, middle: Int, leftBoundary: Int): Int =
       vertex + 2 * (middle - leftBoundary + 1)

}