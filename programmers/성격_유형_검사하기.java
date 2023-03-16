package programmers;

import java.util.HashMap;

/*
* 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/118666
 */
public class 성격_유형_검사하기 {
    /**
     * 간단한 구현 문제라서 풀이 자체는 어렵지 않게 작성했지만
     * 주어진 데이터를 불필요하게 가공하거나
     * 사실상 같은 구조의 코드가 중복된다는 점에서 가독성이 떨어진다.
     *
     * Map을 사용해 성격 유형별 점수를 기록하고, 점수도 그냥 배열을 만들어 관리하는 등
     * 명시적으로 작성한 아래의 풀이가 훨씬 깔끔하다.
     */
    public String solution(String[] survey, int[] choices) {
        int RT = 0, CF = 0, JM = 0, AN = 0;
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < survey.length; i++) {
            int pt = choices[i] - 4;
            String type = survey[i];

            if (type.contains("R")) {
                if (type.startsWith("R"))
                    RT -= pt;
                else
                    RT += pt;
            } else if (type.contains("C")) {
                if (type.startsWith("C"))
                    CF -= pt;
                else
                    CF += pt;
            } else if (type.contains("J")) {
                if (type.startsWith("J"))
                    JM -= pt;
                else
                    JM += pt;
            } else {
                if (type.startsWith("A"))
                    AN -= pt;
                else
                    AN += pt;
            }
        }

        if (RT >= 0)
            answer.append("R");
        else
            answer.append("T");

        if (CF >= 0)
            answer.append("C");
        else
            answer.append("F");

        if (JM >= 0)
            answer.append("J");
        else
            answer.append("M");

        if (AN >= 0)
            answer.append("A");
        else
            answer.append("N");

        return answer.toString();
    }

    public String solution2(String[] survey, int[] choices) {
        String answer = "";

        char [][] type = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
        int [] score = {0, 3, 2, 1, 0, 1, 2, 3};
        HashMap<Character, Integer> point = new HashMap<Character, Integer>();

        // 점수 기록할 배열 초기화
        for (char[] t : type) {
            point.put(t[0], 0);
            point.put(t[1], 0);
        }

        // 점수 기록
        for (int idx = 0; idx < choices.length; idx++){
            if(choices[idx] > 4){
                point.put(survey[idx].charAt(1), point.get(survey[idx].charAt(1)) + score[choices[idx]]);
            } else {
                point.put(survey[idx].charAt(0), point.get(survey[idx].charAt(0)) + score[choices[idx]]);
            }
        }

        // 지표 별 점수 비교 후 유형 기입
        for (char[] t : type) {
            // TODO Check : 삼항연산자와 '+='
            answer += (point.get(t[1]) <= point.get(t[0])) ? t[0] : t[1];
        }

        return answer;
    }
}
