package datastructures.binaryindexedtree

class BinaryIndexedTree(val arr: Array<Int>) {

    private val binaryIndexTree = Array<Int>(arr.size) { 0 }

    init {
        for(i in arr.indices) {
            add(i, arr[i])
        }
    }

    fun getSum(index: Int): Int {

        var sum = 0
        var tempIndex = index

        while(tempIndex >= 0) {
            sum += binaryIndexTree[tempIndex]
            tempIndex = (tempIndex and (tempIndex + 1)) - 1
         }

        return sum
    }

    fun getSumInRange(left: Int, right: Int) =
        getSum(right) - getSum(left)

    fun set(index: Int, newValue: Int) {
        add(index, newValue - arr[index])
    }

    fun add(index: Int, delta: Int) {

        var tempIndex = index

        while(tempIndex < arr.size) {
            print("$tempIndex ")
            binaryIndexTree[tempIndex] += delta
            tempIndex = tempIndex or (tempIndex + 1)
        }
        println()
    }
}