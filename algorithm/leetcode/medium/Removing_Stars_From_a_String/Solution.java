package algorithm.leetcode.medium.Removing_Stars_From_a_String;

import java.util.Stack;

public class Solution {
    /**
     * [Stack을 활용한 풀이]
     * 문자열을 순회하다가 '*'이 나타나면 직전의 문자( = 이전에 마지막으로 순회한 문자)를 제거해야 하므로 Stack을 활용해볼 수 있다.
     * 다만, Stack에 담긴 문자들을 String 타입으로 변환할 때 순서를 뒤집어줘야 한다는 점이 번거롭다.
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 219 ms (Beats 43.24%)
     * Memory : 43.4 MB (Beats 65.69%)
     */
    public String removeStars(String s) {
        Stack<Character> stack = new Stack();
        for(int i=0;i<s.length();i++) {
            // '*'이라면 왼쪽에 있는 문자를 제거한다. ('*'도 스택에 포함되지 않는다.)
            if(s.charAt(i)=='*')
                stack.pop();
            // '*'이 아닌 문자라면 스택에 포함한다.
            else
                stack.push(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();

        while(!(stack.isEmpty())) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    /**
     * [StringBuilder만을 활용한 풀이]
     * 첫 번째 풀이와 접근 방식은 동일하지만, 유효한 문자를 저장하거나, "*" 앞의 문자를 제거할 때
     * Stack / Stack.pop() 이 아닌 StringBuilder / StringBuilder.setLength()를 사용한다는 차이가 있다.
     * Stack을 활용한 풀이에서처럼 String 타입으로 변환하기 위한 과정이 최소화된다는 점에서 더 우수한 풀이이다.
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 31 ms (Beats 93.18%)
     * Memory : 43.2 MB (Beats 90.60%)
     */
    public String removeStars2(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray())
            if (c == '*')
                // TODO Check : StringBuilder.setLength()의 활용
                res.setLength(res.length() - 1);
            else
                res.append(c);
        return res.toString();
    }

    /**
     * [배열을 활용한 풀이]
     * 앞의 두 풀이와 유사한 로직을 char 배열과 인덱스 값의 조정만으로 구현했다.
     * i는 주어진 문자열의 각 문자를 순서대로 가리키고,
     * j는 최종적으로 '*'를 제거한 문자열의 인덱스를 나타낸다.
     *
     * 앞의 풀이에서는 Stack / StringBuilder에서 마지막 문자를 제거하는 식으로 '*' 왼쪽의 문자를 제거했다면,
     * 이 풀이에서는 정답을 입력하는 인덱스를 한 칸 당겨서 기존에 입력한 문자에 덮어씌운다.
     *
     * 예를 들어, String s = "ab*c"인 경우, i와 j가 배열에서 가리키는 위치와 배열의 변화는 다음과 같을 것이다.
     *      "a  b  *  c"  -> "a  b  *  c"  -> "a  c  *  c" -> "a  c  *  c" (반복문 종료)
     *            ij             j     i          j     i            j      i
     * 이후, j 이전까지의 배열을 문자열로 변환하여 return한다.
     *
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 13 ms (Beats 99.65%)
     * Memory : 43.4 MB (Beats 87.43%)
     * ㅇ
     */
    public String removeStars3(String s) {
        char[] res = s.toCharArray();
        int j = 0;
        for(int i = 0; i < res.length; i++, j++){
            res[j] = res[i];
            if(j > 0 && res[j] == '*') {
                j -= 2;
            }
        }
        // TODO Check : char 배열의 일부를 문자열로 변환
        return new String(res, 0, j);
    }
}
