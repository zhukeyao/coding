#include "Node.h"
#include "Functor.h"

template<class T>
void visit(Node<T>* root, Functor<T>& func) {
    if (root == null) return;
    visit(root->getLeft());
    visit(root->getRight());
    func(root->getData());
    return;
}

