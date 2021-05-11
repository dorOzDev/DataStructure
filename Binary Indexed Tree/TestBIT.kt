package datastructures.binaryindexedtree

fun main(args: Array<String>) {

    val arr = arrayOf(1, 2, 4, 3, 10, 12, 13, 5, 1, 3, 2, 4, 10, 30)
    val bit = BinaryIndexedTree(arr)

    print(bit.getSum(5))
}