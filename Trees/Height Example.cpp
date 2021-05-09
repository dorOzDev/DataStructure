#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
#include <string>
#include <ctype.h>
#include <map> 
#include "test.h"
#include <bitset>

using namespace std;

class Node {
public:
    int data;
    Node* left;
    Node* right;

    Node(int data) {
        Node::data = data;
        Node::left = NULL;
        Node::right = NULL;
    }
};


int heightHelper(Node* node, int height) {

    int leftHeight = node->left != NULL ? heightHelper(node->left, height + 1) : height;

    int rightHeight = node->right != NULL ? heightHelper(node->right, height + 1) : height;

    return max(leftHeight, rightHeight);
}

int height(Node* root) {

    if (root == NULL) return 0;

    return heightHelper(root, 0);
}


int main(int argc, const char* argv[]) {
    
    Node* root = new Node(3);
    Node* one = new Node(1);
    Node* seven = new Node(7);
    Node* five = new Node(5);
    Node* four = new Node(4);

    root->left = one;
    root->right = seven;
    seven->left = five;
    five->left = four;

    cout << height(root);
}
