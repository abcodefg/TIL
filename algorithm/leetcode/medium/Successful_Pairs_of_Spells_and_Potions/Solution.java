package algorithm.leetcode.medium.Successful_Pairs_of_Spells_and_Potions;

import java.util.Arrays;

public class Solution {
    public int[] successfulPairs1(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);

        int[] ans = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            long n = spells[i];
            int lo = 0, hi = potions.length - 1;
            int idx = potions.length;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (n * potions[mid] >= success){
                    hi = mid - 1;
                    idx = mid;
                } else {
                    lo = mid + 1;
                }
            }

            ans[i] = potions.length - idx;
        }

        return ans;
    }

    /**
     * 계수정렬을 활용한 풀이
     */
    public int[] successfulPairs2(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;

        int maxPotion = -1;
        for(int p: potions) {
            maxPotion = Math.max(maxPotion, p);
        }

        int[] potionCount = new int[maxPotion + 1];
        for(int p: potions) {
            potionCount[p]++;
        }

        for(int i = maxPotion - 1; i >= 0; i--) {
            potionCount[i] += potionCount[i + 1];
        }

        int[] res = new int[n];
        for(int i = 0; i < n; i++) {
            long target = success / spells[i];
            if(target > maxPotion) {
                continue;
            }

            while(target * spells[i] < success) {
                target++;
            }

            if(target > maxPotion) {
                continue;
            }

            res[i] = potionCount[(int)target];
        }

        return res;
    }
}
