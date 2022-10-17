package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//#7453 합이 0인 네 정수
/*정수로 이루어진 크기가 같은 배열 A, B, C, D가 있다.
* A[a], B[b], C[c], D[d]의 합이 0인 (a, b, c, d) 쌍의 개수를 구하라.
* */
public class TwoPointer_7453 {
    /**
     * 풀이에 있어 중요한 점은 다음과 같다.
     * 1. 두 포인터를 사용하는 풀이를 떠올리는 것
     * 2. 적절한 자료형을 선택하는 것
     *
     * 두 부분합 배열을 구한 뒤에
     * 두번째 배열만을 정렬한 후, 이분탐색을 활용하는 방법도 가능하다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][4];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //각각 A와 B의 요소 / C와 D의 요소의 합을 모아놓은 배열
        int[] sum1 = new int[n * n];
        int[] sum2 = new int[n * n];

        //TODO 확인할 것 (1): 반복문 통한 부분합 배열 생성
        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sum1[idx] = arr[i][0] + arr[j][1];
                sum2[idx] = arr[i][2] + arr[j][3];
                idx++;
            }
        }

        Arrays.sort(sum1);
        Arrays.sort(sum2);

        int pointer1 = 0, pointer2 = sum2.length - 1;
        //TODO 확인할 것 (2): 자료형 선택
        // 가능한 합의 가짓수가 4000^4 개이므로 long 타입으로 선언한다.
        long cnt = 0;
        while(pointer1 < sum1.length && pointer2 >= 0) {
            int abSum = sum1[pointer1], cdSum = sum2[pointer2];
            int total = abSum + cdSum;
            if(total < 0)
                pointer1++;
            else if (total > 0)
                pointer2--;
            else {
                long cnt1 = 0, cnt2 = 0;
                while(pointer1 < sum1.length && sum1[pointer1] == abSum) {
                    pointer1++;
                    cnt1++;
                }

                while(pointer2 >= 0 && sum2[pointer2] == cdSum) {
                    pointer2--;
                    cnt2++;
                }
                cnt += cnt1 * cnt2;
            }
        }

        System.out.println(cnt);
    }
}
