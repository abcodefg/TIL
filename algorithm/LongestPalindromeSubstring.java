package algorithm;

//부분문자열 중 가장 긴 palindrome의 길이 구하기
//palindrome은 'aba', 'daqad' 처럼 역순으로 읽어도 같은 말, 숫자 등을 의미한다.
public class LongestPalindromeSubstring {
    public static int longestPalindrome(String str) {
        int len = str.length();
        if(len < 2) return len;
        int[] maxLength = new int[1];
        for (int i = 0; i < len - 1; i++) {
            //paindrome의 가운데 문자가 한 글자일 경우 ex) aba
            extend(str, i, i, maxLength);
            //palindrome의 가운데 문자가 두 글자일 경우 ex) abba
            extend(str, i, i + 1, maxLength);
        }

        return maxLength[0];
    }

    static void extend(String str, int i, int j, int[] maxLength) {
        //두 인덱스를 비교해 문자가 같을 경우, 인덱스를 좌우로 확장한다.
        while (i >= 0 && j < str.length() && str.charAt(i) == str.charAt(j)) {
            i--; j++;
        }
        i++; j--;

        //끝까지 확장한 i, j를 양 끝 인덱스로 하는 palindrome의 길이가
        //최대값보다 크다면 값을 변경한다.
        if (j - i + 1 > maxLength[0]) {
            maxLength[0] = j - i + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("a"));
    }
}
