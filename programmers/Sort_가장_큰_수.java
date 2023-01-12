package programmers;

import java.util.Arrays;

/*0 또는 양의 정수의 배열 numbers가 주어졌을 때,
* 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return하도록 solution 함수를 작성해주세요.
* 예를 들어, 주어진 정수가 [6, 10, 2]라면 만들 수 있는 가장 큰 수는 6102입니다.*/
public class Sort_가장_큰_수 {
    /**
     * 단순히 주어진 수의 정렬이라는 관점에서만 접근하니 풀이가 떠오르지 않았다.
     *
     * 결국 다른 사람의 풀이를 참고했고 문자열의 정렬에서 실마리를 찾을 수 있었다.
     *
     * 문자열로 변경한 두 수를 순서를 바꾸어가며 이어붙여 만들 수 있는 두 수 중,
     * 더 큰 수에서 앞에 붙어 있는 수가 앞에 오게끔 정렬해야 한다.
     *
     * 예를 들어, 숫자 [A, B, C]를 이어 붙여 수를 만들었을 때 ABC가 가장 큰 숫자였다고 가정하자.
     * 이 때, AB는 BA보다, BC는 CB보다 AC는 CA보다 크거나 같아야 한다.
     *
     * compareTo와 문자열 비교에 관한 설명은 링크로 대체한다.
     * https://mine-it-record.tistory.com/133
     */
    public String solution(int[] numbers) {
        String answer = "";

        // 숫자 배열 -> 문자열 배열로 변경
        String[] strings = new String[numbers.length];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = String.valueOf(numbers[i]);
        }

        // 문자열 배열을 붙였을 때 더 큰 수를 만드는 순서대로 정렬
        Arrays.sort(strings, (o1, o2) -> {return (o2 + o1).compareTo(o1 + o2);});

        // 가장 큰 수가 0이라면 0을 반환(2개 이상의 0들의 배열일 수도 있음)
        if (strings[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }

        return sb.toString();
    }
}
