package baekjoon;
import java.io.*;

// #2447 별 찍기 -10 풀이
/*3의 거듭제곱인 N이 주어졌을 때 가운데가 빈 N*N 크기의 정사각형 모양으로 별을 찍는다
* 이때, 이를 구성하는 작은 정사각형들도 가운데가 비어있는 재귀적인 형태를 가진다
* 자세한 문제 설명과 예시는 백준 사이트 참고*/
public class Recur_2447 {
    static String[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        arr = new String[N + 1][N + 1];
        // 메서드로 재귀적 패턴의 별을 찍는다.
        star(1, 1, N);
        // 별을 찍지 않은 곳의 값은 null로 남아있으므로
        // 이를 공백으로 바꿔주는 동시에 bufferedWriter로 한줄씩 출력한다
        for(int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if(arr[i][j] == null) {
                    bw.write(" ");
                }
                else {
                    bw.write(arr[i][j]);
                }
            }
            bw.write('\n');
            bw.flush();
        }
    }
    public static void star(int xStart, int yStart, int length) {
        // 길이가 1인 지점까지 재귀 호출된 경우, 해당 인덱스에 별을 찍는다.
        if(length == 1) {
            arr[xStart][yStart] = "*";
            return;
        }
        // 가로 세로의 길이를 3분의 1씩 줄여서 재귀를 호출하되,
        // 가로와 세로가 모두 3분의 2인 지점(가운데)은 공백으로 남겨놓는다.
        for(int i = xStart; i < xStart + length; i += length / 3) {
            for(int j = yStart; j < yStart + length; j += length / 3) {
                if(i == xStart + length / 3 && j == yStart + length / 3)
                    continue;
                star(i, j, length / 3);
            }
        }
    }
}
