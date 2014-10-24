/*
 Tree tranverse:
 1. pre-order:  curret->left-tree->right-tree
                stack (or recursive)
 2. in-order:   left-tree->current->right-tree
                stack (or recursive)
 3. post-order: left-tree->right-tree->current
                stack (or recursive)
 4. by level:   current->left-child->right-child->left-child-child-> ...
                use queue (or recursive by level)
 */
#include <iostream>
#include<vector>
#include<stack>

using namespace std;

class TreeNode {
    public: 
	int val;
        TreeNode *left;
        TreeNode *right;
    public: 
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    vector<int> inorderTraversal(TreeNode *root) {
        stack<TreeNode*> nodeStack;
        vector<int> result;
        if (root == NULL) 
            return result;
        
        TreeNode* currentNode = root->left;
        nodeStack.push(root);
        /* note: 1. do not forget currentNode != NULL condition 
                 2. should be || instead of &&
         */
        while (nodeStack.size() > 0 || currentNode != NULL) {
            if (currentNode != NULL) {
                nodeStack.push(currentNode);
                currentNode = currentNode->left;
                continue;
            }
            else {
                TreeNode* node = nodeStack.top();
                nodeStack.pop();
                result.push_back(node->val);
                currentNode = node->right;
            }
        }
    }
};

/* Node:
    stack.top()   (not front())
    queue.front() (not top())
