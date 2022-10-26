package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrefixSum_16139 {
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int[][] sum = new int[S.length()][26];

        // 1번째 초기화
        sum[0][S.charAt(0) - 'a']++;

        // 1~끝까지 누적합 구하기
        for(int i = 1; i < S.length(); i++) {
            int tmp = S.charAt(i) - 'a';

            for(int j = 0; j < 26; j++) {
                sum[i][j] = sum[i - 1][j];
            }
            sum[i][tmp]++;
        }

        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char find = st.nextToken().charAt(0);
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if(s == 0) {
                sb.append(sum[e][find - 'a']).append('\n');
            }
            else {
                sb.append(sum[e][find - 'a'] - sum[s - 1][find - 'a']).append('\n');
            }
        }

        System.out.println(sb);
    }
}
