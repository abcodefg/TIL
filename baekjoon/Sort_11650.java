package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// #11650 좌표 정렬하기 풀이
/*2차원 평면 위의 점 N개가 주어질 때, 좌표를 x좌표가 증가하는 순으로, x좌표가 같으면 y좌표가 증가하는 순서로 정렬하는 문제*/
public class Sort_11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 좌표값을 입력받는다.
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // Comparator를 이용해 정렬의 기준을 제공
        // 기본형 : Arrays.sort(T[] a, Comparator<? super T> c)
        // 이 때, '? super T'는 '? extends T'의 반대를 의미하는 와일드카드.
        // Comparator의 제너릭 타입이 T이거나 그 부모클래스여야 한다는 걸 의미함.
        Arrays.sort(arr, new Comparator<int[]>() {
            // Comparator 구현 클래스 정의 없이 Comparator 인스턴스 생성과 동시에 compare() 메서드를 오버라이드 하는 것도 가능
            @Override
            public int compare(int[] e1, int[] e2) {
                if(e1[0] == e2[0]) { // x좌표가 같으면 y좌표끼리 대소비교
                    return e1[1] - e2[1];
                } else {             // x좌표가 다르면 x좌표끼리 대소비교
                    return e1[0] - e2[0];
                }
            }
        });

        // 람다식으로 축약한 버전
        Arrays.sort(arr, (e1, e2) -> {
            if(e1[0] == e2[0]) {
                return e1[1] - e2[1];
            } else {
                return e1[0] - e2[0];
            }
        });

        // StringBuilder를 활용한 출력(System.out.println을 반복하는 것보다 빠르다)
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(arr[i][0] + " " + arr[i][1]).append('\n');
        }
        System.out.println(sb);
    }
}
