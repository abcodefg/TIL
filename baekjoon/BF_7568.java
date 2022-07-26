package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 #7568 덩치 풀이
    /* '사람의 수'와 '각 사람의 키와 몸무게 값'을 입력받아 등수를 출력하는 문제
     다른 사람에 비해 키와 몸무게가 모두 크면 덩치가 큰 것이다.
     덩치가 클수록 등수가 높으며, 키나 몸무게 어느 하나만 크다면 등수는 같다.*/
public class BF_7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // '사람의 수 X 3' 크기의 2차원배열을 만들어, 행마다 각 사람의 정보를 저장한다.
        int[][] arr = new int[N][3];

        // 입력받은 키와 몸무게는 각각 0번, 1번 인덱스에 저장한다.
        // 초기 등수는 동일하게 1로 설정해 2번 인덱스에 저장한다.
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = 1;
        }

        // 등수를 '서수'가 아니라 '기수'의 개념으로 보는 것이 풀이의 핵심이었다.
        // 이를테면, N등은 'N번째로 덩치가 큰 사람'이기도 하지만 '자신보다 덩치가 큰 사람이 N-1명인 사람'이기도 하다.
        // 따라서 모든 비교대상을 순회하며 자신보다 덩치가 큰 사람을 발견할 때마다 등수를 1씩 더해주면
        // 등수 초기값(1) + 덩치 큰 사람의 수(N - 1) = 등수(N)를 구할 수 있다.

        // 초기 대상을 반복문으로 순회하며 지정한다.
        for(int i = 0; i < N - 1; i++) {
            // 비교 대상을 반복문으로 순회하며 지정한다.
            for (int j = i + 1; j < N; j++) {
                // 초기 대상의 키와 몸무게가 더 크면 반복문 3번 인덱스를 1 늘린다.
                if(arr[i][0] > arr[j][0] && arr[i][1] > arr[j][1]) {
                    arr[j][2] += 1;
                }
                // 비교 대상의 키와 몸무게가 크면 초기값의 3번 인덱스를 1 늘린다.
                else if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
                    arr[i][2] += 1;
                }
            }
        }

        String str = "";
        for (int i = 0; i < N; i++) {
            str += arr[i][2] + " ";
        }

        System.out.println(str);
    }
}

