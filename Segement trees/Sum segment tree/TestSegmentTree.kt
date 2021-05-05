package datastructures.segmenttree.sumsegmenttree

fun main() {

    val segTree = SumSegmentTreeArrayImpl(arrayOf(1, 2, 3, 4))

    segTree.updateSegTree(1, 4)

    segTree.printSegTree()

    println(segTree.getRangeSum(1, 1))
}