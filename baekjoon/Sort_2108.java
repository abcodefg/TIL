package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 백준 #2108 통계학 풀이
public class Sort_2108 {

    /* 절댓값이 4000을 넘지 않는 정수들을 입력받아 산술평균, 중앙값, 최빈값, 범위를 계산하는 문제
    * 수의 배열에서 음의 정수의 빈도를 기록한 방법을 나중에 참고하면 좋을 것 같다.
    **/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫 입력값은 정수의 개수이다.
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;

        for(int i = 0; i < N; i++) {
            int current = Integer.parseInt(br.readLine());
            list.add(current);
            sum += current;
        }
        // 산술평균 출력
        System.out.println((int) Math.round((double) sum / list.size()));
        Collections.sort(list);

        // 중앙값 출력
        System.out.println(list.get(list.size() / 2));

        // 최빈값 출력
        int[] checklist = new int[8001];
        for(int i = 0 ; i < list.size(); i++) {
            checklist[list.get(i) + 4000]++;
        }
        int max = -1;
        int maxValue = 0;
        int secondMaxValue = 0;
        int count = 0;

        for(int i = 0; i < checklist.length; i++) {
            if(max < checklist[i]) {
                max = checklist[i];
                maxValue = i - 4000;
                count = 1;
            }
            else if(max == checklist[i]) {
                count++;
                if(count == 2) {
                    secondMaxValue = i - 4000;
                }
            }
        }
        int mode = Math.max(maxValue, secondMaxValue);
        System.out.println(mode);

        // 범위 출력
        System.out.println(list.get(list.size() - 1) - list.get(0));
    }
}
