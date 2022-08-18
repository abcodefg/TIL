package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// #14719 빗물 풀이
/*2차원 세계의 높이와 너비, 왼쪽부터 블록이 쌓인 높이를 차례대로 입력받는다.
* 2차원 세계에 비가 내렸을 때, 고이는 빗물의 총량을 구하는 문제*/
public class Simulation_14719 {
    // 처음에 입력받은 블록의 높이들을 산술적으로 처리하는 방식으로 접근을 잘못해서 많이 헤맸다.
    // 겨우 찾아낸 문제 풀이의 포인트는 다음과 같다.
    // (1) 블록이 쌓인 2차원 세계를 형상화한 2차원 배열을 구성하고
    // (2) 빗물이 고이는 양을 가로로 한줄씩 계산하는 것
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        // (1)에 해당하는 풀이이다.
        int[][] arr = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++) {
            int block = Integer.parseInt(st.nextToken());
            // 여기서 범위를 설정하는 게 까다로웠는데, 예시를 통해 연관관계를 파악했다.
            for(int j = H - 1; j > (H - 1) - block ; j--) {
                arr[j][i] = 1;
            }
        }

        // (2)에 해당하는 풀이이다.
        int temp = 0;
        int sum = 0;
        boolean count = false;

        // 가로 한줄씩 왼쪽부터 순회한다.
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
            // 핵심은 앞서 블록이 나온 경우에
                if(count) {
                //이후에 나오는 빈 공간만큼 temp를 채우고
                    if(arr[i][j] == 0) {
                        temp++;
                // 블록이 한 번 더 나오면 temp에 누적된 값을 sum으로 전환하는 것
                    } else {
                        sum += temp;
                        temp = 0;
                    }
                } else if(arr[i][j] == 1) {
                    count = true;
                }
            }
            // 한 줄의 순회가 끝나면 count와 temp를 초기화한다.
            count = false;
            temp = 0;
        }
        System.out.println(sum);
    }
}
