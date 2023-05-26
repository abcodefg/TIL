package algorithm.leetcode.medium.Maximum_Sequence_Score;

import java.util.*;

public class Solution {
    static class Index implements Comparable<Index> {
        int num1;
        int num2;
        public Index(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public int compareTo(Index index) {
            return this.num1 - index.num1;
        }
    }

    public long maxScore(int[] nums1, int[] nums2, int k) {
        List<Index> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            list.add(new Index(nums1[i], nums2[i]));
        }
        list.sort((o1, o2) -> o2.num2 - o1.num2);

        PriorityQueue<Index> heap = new PriorityQueue<>();
        long sum = 0, max = 0;
        for (int i = 0; i < k; i++) {
            Index cur = list.get(i);
            sum += cur.num1;
            heap.add(cur);
        }

        max = sum * list.get(k - 1).num2;

        for (int i = k; i < nums1.length; i++) {
            sum -= heap.poll().num1;

            Index next = list.get(i);
            sum += next.num1;

            heap.add(next);

            max = Math.max(max, sum * list.get(i).num2);
        }

        return max;
    }
}
