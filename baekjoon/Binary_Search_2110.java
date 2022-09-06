package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// #2110 공유기 설치
/*집 N개가 수직선 위에 있을 때, 공유기를 한 집당 1개씩 총 C개를 설치하고자 한다.
* 가장 인접한 두 공유기 사이 거리의 최대값을 구하시오.*/
public class Binary_Search_2110 {
    // 이분탐색의 방법은 동일하지만 무엇을 탐색할 요소로 설정할지,
    // 무엇을 탐색의 방향을 정할 조건으로 할지 떠올리는 게 관건인 문제였다.
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // n은 집의 수, c는 공유기의 수를 의미한다.
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        // 공유기 간 거리의 최소값과 최대값을 구한다.
        int low = 1;
        // TODO 확인할 것: 변수값 설정 주의!
        //  Upper Bound는 찾고자 하는 값을 초과하는 첫번째 인덱스
        //  가능한 공유기 사이 거리의 최대값(arr[n - 1] - arr[0])이 찾고자 하는 값인 경우를 포함하기 위해 1을 더한다.
        int high = arr[n - 1] - arr[0] + 1;

        // 이분탐색을 통해 공유기 사이의 거리를 탐색한다.
        // 어떤 거리(mid)가 가장 인접한 공유기 사이의 거리일 때
        // 설치할 수 있는 공유기의 최대 개수가 목표 개수 c에 비해 많은지 적은지를 기준으로 탐색한다.
       while(low < high) {
            int mid = (low + high) / 2;
            // 설치할 수 있는 공유기의 대수가 목표대수보다 많거나 같다면
            if(routersAreEnough(mid, c)) {
                // 탐색 범위를 큰 쪽으로 좁힌다.
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        System.out.println(low - 1);
    }
    // 공유기 간의 최소 거리가 주어졌을 때
    // 설치할 수 있는 공유기의 수가 c보다 많거나 같은지(충분한지) 확인
    static boolean routersAreEnough(int distance, int c) {
        int point = arr[0];
        int count = 1;

        for(int i = 1; i < arr.length; i++) {
            // 공유기 사이의 거리가 최소 거리보다 크거나 같다면
            // count를 늘리고, 길이 측정의 시작점을 갱신한다.
            if((arr[i] - point) >= distance) {
                count++;
                point = arr[i];
            }
            // 반복문의 중간이라도 요건을 충족한다면 true를 반환한다.
            if(count >= c) {
                return true;
            }
        }
        return false;
    }
}
