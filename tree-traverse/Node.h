#ifndef _NODE_
#define _NODE_
template<class T> 
class Node {
    private: 
	T data;
	Node<T>* leftChild;
    	Node<T>* rightChild;

    public:
	Node(T input); 
        T getData() {return data};
        Node* getLeftChild() {return leftChild};
        Node* getRightChild() {return rightChild};
        void setLeftChild(T value);
        void setRightChild(T value);
}
#endif
