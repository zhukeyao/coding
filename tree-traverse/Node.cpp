#include "Node.h"

template<class T>
Node(T input) {
	data = input; 
	leftChild=0; 
	rightChild=0;
}

template<class T>
void setLeftChild(T value) {
	if (leftChild) delete leftChild;
	leftChild = new Node<T>(value);
}

template<class T>
void setRightChild(T value) {
	if (rightChild) delete rightChild;
	rightChild = new Node<T>(value);
}
