package algorithm.leetcode.medium.Swapping_Nodes_in_a_Linked_List;

public class Solution {
    /**
     * [Two Pointer 풀이]
     * 첫 노드부터 k번째 노드 사이의 간격은 마지막 노드와 끝에서 k번째 노드 사이의 간격과 같다.
     *
     * 따라서, k번째 노드를 구한 후, 각각 첫 번째 노드와 k 번째 노드에 왼쪽, 오른쪽 포인터를 두고
     * 오른쪽 포인터가 끝 포인터에 도달할 때까지 한 칸씩 포인터를 옮기면 왼쪽 포인터가 뒤에서 k번째 노드를 가리키게 된다.
     *
     * Time : O(n)
     * Space : O(1)
     * Runtime : 2 ms (99.55%)
     * Memory : 57.5 MB (69.37%)
     */

    public ListNode swapNodes(ListNode head, int k) {
        if (head.next == null) return head;

        ListNode cur = head;
        // 처음부터 k번째 노드를 구한다
        for (int i = 0; i < k - 1; i++) {
            cur = cur.next;
        }

        //
        ListNode l = cur, r = head;
        while (cur.next != null) {
            cur = cur.next;
            r = r.next;
        }

        int tmp = l.val;
        l.val = r.val;
        r.val = tmp;

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
