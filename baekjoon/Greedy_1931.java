package baekjoon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//#1931 회의실 배정
/*한 회의실을 사용하려는 N개의 회의에 대해 사용표를 만든다.
* 시작하는 시간, 끝나는 시간이 주어질 때 회의실을 사용할 수 있는 회의의 최대 개수를 구하라.*/
public class Greedy_1931 {
    // 회의가 끝나는 시간을 기준으로 오름차순으로 정렬한다.
    // 끝나는 시간이 동일하다면 시작하는 시간이 빠른 순서(오름차순)으로 정렬한다.
    // 정렬된 목록에서 맨 처음에 오는 회의부터 시간이 겹치지 않는 회의을 더해나간다.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N][2];
        for(int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        
        // TODO 확인할 것: 이차원 배열의 정렬
        //(1) Comparator 익명 클래스를 구현하는 방법
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        //(2) 람다식을 사용하여 간략화한 방법
        Arrays.sort(arr, (int[]o1, int[] o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });

        int[] current = arr[0];
        int count = 1;
        for(int i = 1; i < N; i++) {
            if(current[1] <= arr[i][0]) {
                current = arr[i];
                count++;
            }
        }

        System.out.println(count);
    }
}
