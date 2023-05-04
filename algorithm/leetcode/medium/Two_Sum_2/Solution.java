package algorithm.leetcode.medium.Two_Sum_2;

import java.util.HashMap;

public class Solution {
    /**
     * [Two Pointer 풀이]
     *
     *  주어진 배열이 정렬되어 있으므로 투 포인터를 사용할 수 있다.
     *  배열의 양 끝에 포인터를 두고 포인터가 있는 인덱스의 숫자들의 합이
     *  target에 비해 큰지/작은지에 따라 포인터를 옮기며 답을 찾는다.
     *
     *  Time : O(n)
     *  Space : O(1)
     *  Runtime : 1 ms (98.84%)
     *  Memory : 45.4 MB (49.47%)
     */
    public int[] twoSum_tp(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        int[] ans = new int[2];
        while (i < j) {
            int sum = numbers[i] + numbers[j];

            if (sum < target) { // 두 숫자의 합이 target보다 작다면, i를 오른쪽으로 옮겨 합의 크기를 키운다. (오름차순으로 정렬되어 있으므로)
                i++;
            } else if (sum > target) {  // 두 숫자의 합이 target보다 크다면,  j를 왼쪽으로 옮겨 합의 크기를 줄인다.
                j--;
            } else {    // 두 숫자의 합이 target과 일치한다면, 두 숫자를 배열에 담아 return한다.
                break;
            }
        }

        ans[0] = i+1;
        ans[1] = j+1;

        return ans;
    }

    /**
     * [HashMap 풀이]
     *
     *  numbers[i]를 key, i를 value로 하는 HashMap을 생성한다.
     *  HashMap에 target - numbers[i]가 존재한다면(sum이 target이 되는 값이 존재한다면)
     *  현재 인덱스와 해당 값의 인덱스를 담아 return한다.
     *
     *  Time : O(n)
     *  Space : O(n)
     *  Runtime : 5 ms (19.90%)
     *  Memory : 44.9 MB (89.31%)
     */
    public int[] twoSum_hm(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] ans = new int[2];

        for(int i = 0; i < numbers.length; i++){
            if(map.containsKey(target-numbers[i])){
                ans[1] = i+1;
                ans[0] = map.get(target-numbers[i])+1;
                return ans;
            }
            else{
                map.put(numbers[i],i);
            }
        }

        return ans;
    }
}
