package programmers;

/*사전에 알파벳 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다.
*사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
*
*단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.
*/
public class DFS_모음사전 {
    /**
     * 크게 두 가지 작업이 이루어진다.
     * 1. 단어들을 사전 순서대로 탐색하며 순서를 기록한다.
     * 2. word와 일치한다면 순서를 기록하고 메서드를 종료한다.
     *
     * 1번은 dfs 문제를 풀어봤다면 크게 어렵지 않으나
     * 2번의 메서드 종료 조건은 생각해볼 여지가 있다.
     *
     * 단순하게 접근하자면, StringBuilder를 사용해 일치하는 알파벳들을 쌓아나가고
     * 이를 String으로 변환한 값이 word와 일치하는지 확인하는 식으로
     * 탐색을 종료하는 조건을 정할 수 있을 것이다.
     * 그런데 이는 간편하기는 하나, 조건을 확인할 때마다 String.equals()와 StringBuilder.toString()를 호출해야 한다는 단점이 존재한다.
     *
     * 따라서 찾으려는 word 알파벳의 인덱스를 1씩 늘리면서 찾아나가는 방식으로 조건을 설정했다.
     * 이렇게 하면 StringBuilder 객체가 필요없고, 조건을 단순 비교연산자로 표현할 수 있다.
     *
     * +) 다른 분들의 풀이 중에서 경우의 수를 구해서 위의 작업을 축약한 풀이가 있었으나
     * 지나치게 생략해서 직관적이지도 않을 뿐더러,
     * 문제의 조건을 조금만 바꿔도 필요한 변수의 값을 새로 계산해야 하므로
     * 간결하다는 것을 빼고는 장점이 적은 풀이라고 생각해 따로 기록하지는 않았다.
     */

    //'단어의 순서'와 '찾으려는 word 알파벳의 인덱스'
    private int answer = 0, wordIdx = 0;
    private char[] vowels = {'A','E','I','O','U'};

    private void dfs(String word, int len) {
        // 탐색하려는 자릿수가 5를 넘었다면 돌아간다.
        if (len > 5)
            return;

        for (char vowel : vowels) {
            // TODO 확인할 것 : 재귀 종료 조건의 위치
            //  wordIdx가 word.length()와 같다는 것은 word의 마지막 문자까지 찾았고
            //  answer는 word의 순서를 담고 있다는 것을 의미
            //  -> 더 이상의 탐색 불필요
            //  (이 조건문을 반복문 전에 위치시키면 추가적인 재귀 호출은 막을 수 있으나 반복문 순회를 막지 못하고
            //  재귀 호출 구문의 뒤에 위치시키면 반복문 순회는 막을 수 있으나 추가적인 재귀 호출을 막지 못함)
            if (wordIdx == word.length())
                return;

            // 카운트를 1 늘린다.
            answer++;

            // word와 탐색중인 단어의 특정 위치에 있는 알파벳이 일치하면 찾으려는 알파벳의 인덱스를 1 늘린다.
            if (wordIdx == len - 1 && word.charAt(wordIdx) == vowel)
                wordIdx++;

            dfs(word, len + 1);

        }
    }

    public int solution(String word) {

        dfs(word, 1);

        return answer;
    }
}
