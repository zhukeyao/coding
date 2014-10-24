/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
#include <iostream>
using namespace std;

class ListNode {
    public: 
	int val;
    public: 
	ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    bool hasCycle(ListNode *head) {
        ListNode* stepper1 = head;
        ListNode* stepper2 = head;
        while (stepper1 != NULL && stepper2 != NULL) {
            stepper2 = stepper2->next;
            if (stepper2 == stepper1)
                return true;
            if (stepper2 == NULL)
                return false;
            /* note:  mistake made
               stepper2 == stepper2->next;
             */
            stepper2 = stepper2->next;
            if (stepper2 == stepper1)
                return true;
            stepper1 = stepper1->next;
        }
        return false;
    }
};

int main() {
    ListNode* node1 = new ListNode(1);
    ListNode* node2 = new ListNode(2);
    node1->next = node2;
    node2->next = node1;
    Solution* sol = new Solution();
    if (sol->hasCycle(node1)) {
       cout << "has cycle";
    }
    else {
       cout << "no cycle";
    }
    return 0;
}
