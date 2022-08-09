package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//#10825 국영수 풀이
/*학생 N명의 이름과 국어, 영어, 수학 점수가 주어질 때 다음의 조건으로 학생의 성적을 정렬하시오
*   1. 국어 점수가 감소하는 순서로
*   2. 국어 점수가 같으면 영어 점수가 증가하는 순서로
*   3. 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
*   4. 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로*/
public class Sort_10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[][] arr = new String[N][4];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = st.nextToken();
            arr[i][1] = st.nextToken();
            arr[i][2] = st.nextToken();
            arr[i][3] = st.nextToken();
        }

        Arrays.sort(arr, (e1, e2) -> {
            if (e1[1].equals(e2[1])) {
                if (e1[2].equals(e2[2])) {
                    if(e1[3].equals(e2[3])) {
                        // String 타입의 데이터끼리 비교할 때 compareTo() 메서드 활용
                        return e1[0].compareTo(e2[0]);
                    }
                    return Integer.parseInt(e2[3]) - Integer.parseInt(e1[3]);
                }
                return Integer.parseInt(e1[2]) - Integer.parseInt(e2[2]);
            }
            return Integer.parseInt(e2[1]) - Integer.parseInt(e1[1]);
        });

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i][0]).append('\n');
        }
        System.out.println(sb);
    }
}
