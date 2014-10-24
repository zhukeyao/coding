#include <iostream>
#include<vector>
#include<queue>

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
            
        queue<TreeNode*> nodeQueue;
        nodeQueue.push(root);
        while (nodeQueue.size() > 0) {
            /* note: mistake made 
               1. queue/stack is implemented by deque, pop function does not return anyting.
                  use front/top then pop
            */
            TreeNode* currentNode = nodeQueue.front();
            nodeQueue.pop();
            result.push_back(currentNode->val);
            if (currentNode->left) {
                nodeQueue.push(currentNode->left);
            }
            if (currentNode->right) {
                nodeQueue.push(currentNode->right);
            }
        }
        return result;
    }
};

int main() {
    TreeNode* node1 = new TreeNode(1);
    TreeNode* node2 = new TreeNode(2);
    TreeNode* node3 = new TreeNode(3);

    node1->right = node2;
    node2->left = node3;
    Solution* sol = new Solution();
    vector<int> result = sol->preorderTraversal(node1);
    for (int i=0; i<result.size(); i++) {
        cout << result[i];
    };
    return 0;
}
