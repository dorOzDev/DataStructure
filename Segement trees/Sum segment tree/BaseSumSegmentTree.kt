package datastructures.segmenttree.sumsegmenttree

abstract class BaseSumSegmentTree(protected val array: Array<Int>) {

    abstract fun getRangeSum(left: Int, right: Int): Int

    abstract fun updateSegTree(position: Int, newVal: Int)

    protected abstract fun buildSegmentTree()
}