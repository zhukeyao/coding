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
    vector<int> preorderTraversal(TreeNode *root) {
        vector<int> result;
        if (root == NULL)
            return result;
            
        stack<TreeNode*> nodeStack;
        nodeStack.push(root);
        while (nodeStack.size() > 0) {
            TreeNode* currentNode = nodeStack.top();
            nodeStack.pop();
            result.push_back(currentNode->val);
            if (currentNode->right) {
                nodeStack.push(currentNode->right);
            }
            if (currentNode->left) {
                nodeStack.push(currentNode->left);
            }
        }
        return result;
    }
};

int main() {
    TreeNode* node1 = new TreeNode(1);
    TreeNode* node2 = new TreeNode(2);
    TreeNode* node3 = new TreeNode(3);
    TreeNode* node4 = new TreeNode(4);

    node1->left=node2;
    node1->right = node3;
    node2->left = node4;
    Solution* sol = new Solution();
    vector<int> result = sol->preorderTraversal(node1);
    for (int i=0; i<result.size(); i++) {
        cout << result[i];
    };
    return 0;
}
