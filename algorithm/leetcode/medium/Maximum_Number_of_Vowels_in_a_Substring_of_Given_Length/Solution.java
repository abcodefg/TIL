package algorithm.leetcode.medium.Maximum_Number_of_Vowels_in_a_Substring_of_Given_Length;

public class Solution {
    /**
     * [첫 풀이]
     * 첫 k 길이의 substring에서 모음의 개수 vowels를 구한 뒤,
     * 기존/이후 substring의 첫 문자를 가리키는 포인터 l,r을 한 칸씩 옮겨가며, 각각 모음인지 확인한다.
     * 이에 따라 vowels의 크기를 조정하고 모음 개수의 최대값 max를 갱신한다.
     *
     * Time : O(n)
     * Space : O(n) (char[] c 배열)
     * Runtime : 17 ms (54.54%)
     * Memory : 44.3 MB (6.15%)
     */
    public int maxVowels(String s, int k) {
        int l = 0, r = k;
        int vowels = 0;
        char[] c = s.toCharArray();

        for (int i = l; i < r; i++) {
            if (isVowel(c[i])) {
                vowels++;
            }
        }

        int max = vowels;
        while (r < s.length()) {
            if (max == k) break;
            vowels = isVowel(c[l]) ? vowels - 1 : vowels;
            vowels = isVowel(c[r]) ? vowels + 1 : vowels;
            l++;
            r++;

            max = Math.max(max, vowels);
        }

        return max;
    }

    /**
     * [수정한 풀이]
     * substring에서 삭제할 문자와 추가할 문자 사이의 간격이 k로 일정하므로 substring에 추가될 문자를 가리키는 포인터 하나만 있어도 i, i - k와 같이 두 지점을 모두 나타낼 수 있다.
     *
     * Time : O(n)
     * Space : O(n) (char[] c 배열)
     * Runtime : 13 ms (88.49%)
     * Memory : 44.2 MB (6.15%)
     */
    public int maxVowels_modified(String s, int k) {
        int vowels = 0;
        char[] c = s.toCharArray();

        for (int i = 0; i < k; i++) {
            if (isVowel(c[i])) {
                vowels++;
            }
        }

        int max = vowels;
        for (int i = k; i < s.length(); i++) {
            if (isVowel(c[i])) {
                vowels++;
            }

            if (isVowel(c[i - k])) {
                vowels--;
            }

            max = Math.max(max, vowels);
        }

        return max;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
