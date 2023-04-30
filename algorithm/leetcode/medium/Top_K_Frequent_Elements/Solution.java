package algorithm.leetcode.medium.Top_K_Frequent_Elements;

import java.util.*;

public class Solution {
    /**
     * [O(n) 풀이]
     * nums의 숫자 n을 key로, n의 빈도인 f를 value로 갖는 map을 만든다.
     * f를 인덱스 삼아 숫자들을 배열에 기록한다.
     * (이때, 어떤 숫자 n은 최대 nums의 길이만큼만 나타날 수 있으므로, 배열의 길이는 nums.length + 1이다)
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 11 ms (93.35%)
     * Memory : 45.8 MB (6.66%)
     */
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer>[] cnt = new ArrayList[nums.length + 1];

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> ent : map.entrySet()) {
            int num = ent.getKey();
            int val = ent.getValue();
            if (cnt[val] == null)
                cnt[val] = new ArrayList<>();

            cnt[val].add(num);
        }

        int[] ans = new int[k];
        int idx = 0;
        for (int i = cnt.length - 1; i >= 0; i--) {
            if (cnt[i] == null) continue;
            if (idx == k) break;
            for (int num : cnt[i]) {
                ans[idx] = num;
                idx++;
            }
        }

        return ans;
    }

    /**
     *  [O(k log n) 풀이]
     *  빈도를 기준으로 정렬되는 최대 힙(max heap)에 숫자들을 넣는다.
     *  최대 힙에서 빈도가 가장 높은 숫자를 k번 꺼내서 배열에 담아 반환한다.
     *  (힙을 기반으로 구현한 우선순위 큐를 사용했다.)
     *
     *  Time : O(n log n)) (우선순위 큐 숫자 삽입 : O(n log n), 삭제 : O(k log n))
     *  Space : O(n)
     *  Runtime : 14 ms (65.13%)
     *  Memory : 45.5 MB (33.89%)
     */

    // TODO Check : 우선순위 큐에 저장할 객체 구현
    class Num implements Comparable<Num> {
        int num;
        int cnt;

        public Num(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Num o) {
            return o.cnt - this.cnt;
        }
    }

    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Num> pq = new PriorityQueue<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> ent : map.entrySet()) {
            pq.add(new Num(ent.getKey(), ent.getValue()));
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll().num;
        }

        return ans;
    }
}
