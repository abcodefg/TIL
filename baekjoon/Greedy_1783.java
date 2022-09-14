package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//#1783 병든 나이트
/*병든 나이트가 N * M(세로 * 가로) 크기의 체스판의 가장 왼쪽아래 칸에 위치해 있다.
* 병든 나이트는 다음 4가지 방식으로만 움직일 수 있다.
* 1) 2칸 위로, 1칸 오른쪽
* 2) 1칸 위로, 2칸 오른쪽
* 3) 1칸 아래로, 2칸 오른쪽
* 4) 2칸 아래로, 1칸 오른쪽
* 병든 나이트의 이동 횟수가 4번보다 적지 않다면, 이동 방법을 모두 한 번씩 사용해야 한다.
* 이동 횟수가 4번보다 적은 경우(방문한 칸이 5개 미만)에는 이동 방법에 대한 제약이 없다.
* 체스판의 크기가 주어졌을 때 병든 나이트가 방문할 수 있는 칸의 최대 개수를 구하라.*/
public class Greedy_1783 {
    // TODO 확인할 것: 기준을 잡고 하나씩 조건 분기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int count = 0;

        /*
        * N의 크기에 따라 조건을 분기하고,
        * 각 경우에 필요에 따라 M의 크기에 따라 조건을 분기한다.
        * */

        // N이 1인 경우, 병든 나이트는 1,2,3,4 중 어느 방식으로도 움직일 수 없다.
        if(N == 1) {
            count = 1;
        }
        // N이 2인 경우, 2,3의 방식으로만 움직일 수 있다.
        // 단, 이동 방법을 모두 사용하지 않는 경우 이동 횟수가 4번보다 적어야 하므로
        // 방문한 칸은 4칸을 넘을 수 없다.
        else if(N == 2) {
            count = Math.min((M + 1) / 2, 4);
        }
        // N이 3 이상인 경우
        // N은 이동 횟수의 제한 조건이 되지 않는다.
        // 단, 처음 4번의 이동은 4가지 방법을 모두 사용해야 하고,
        // 이를 위해선 최소 7(1+1+1+2+2)의 가로 길이가 확보되어야 한다.
        // 따라서, M이 7보다 작다면 이동 횟수가 4번보다 적어야 한다.
        // M이 7일 때 방문할 수 있는 칸의 최대 개수는 5이다.
        // 그 다음부터는 1,4의 방법을 반복하여 M이 1씩 늘 때마다 방문할 수 있는 최대 칸도 1씩 늘 것이다.
        // 따라서 count = M - 2이다.
        else if(N >= 3) {
            if(M < 7) {
                count = Math.min(M, 4);
            }
            else {
                count = M - 2;
            }
        }
        System.out.println(count);
    }
}
