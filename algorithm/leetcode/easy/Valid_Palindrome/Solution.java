package algorithm.leetcode.easy.Valid_Palindrome;

public class Solution {
    /**
     *  [주어진 문자열을 가공하는 풀이]
     *  라이브러리 함수와 정규표현식을 사용해 주어진 문자열을 소문자 알파벳과 숫자로만 이루어진 문자의 배열로 변환해 문제를 풀었다.
     *
     *  Time : O(n)
     *  Space : O(n)
     *  Runtime : 17 ms (48.15%)
     *  Memory : 43.8 MB (13.80%)
     */
    public boolean isPalindrome_Modified(String s) {
        char[] converted = s.toLowerCase().replaceAll("[^a-z0-9]", "").toCharArray();
        int i = 0, j = converted.length - 1;
        while (i < j) {
            if (converted[i] != converted[j]) return false;
            i++;
            j--;
        }

        return true;
    }

    /**
     * [문자열을 그대로 사용하는 풀이]
     *  Character.isLetterOrDigit을 사용해 알파벳과 숫자를 제외한 문자가 등장하면 건너뛰는 방식으로 풀었다.
     *  문자열을 변경할 필요가 없으므로 실행 시간이 훨씬 적다.
     *
     *  Time : O(n)
     *  Space : O(1)
     *  Runtime : 3 ms (98.1%)
     *  Memory : 42.5 MB (81.8%)
     */
    public boolean isPalindrome_AsIs(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char left = Character.toLowerCase(s.charAt(i));
            char right = Character.toLowerCase(s.charAt(j));
            if (!Character.isLetterOrDigit(left)) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(right)) {
                j--;
                continue;
            }
            if (left != right) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
