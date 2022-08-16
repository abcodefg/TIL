package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// #1620 나는야 포켓몬 마스터 이다솜(...) 풀이
/*1번부터 순서대로 포켓몬의 이름을 입력받고 숫자 혹은 포켓몬 이름이 주어졌을 때
* 상응하는 포켓몬 이름/ 숫자를 출력하는 문제*/
public class Map_1620 {
    // 문자열 탐색에 있어 HashMap을 사용하는 것이 유리하다.
    // 두 가지 방식으로 풀어보았다.
    // 첫 번째 방식은 시간이 초과됐기에, 엄밀히 말하면 두 번째 방식이 맞는 풀이다.
    // 다만, (1) stream을 알고리즘 풀이에 처음 적용해봤고,
    // (2) map에서 value로부터 key를 구하는 방식을 사용했다는 점이 의미있는 것 같아 기록해둔다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int num = 0;
        HashMap<String, Integer> map = new HashMap<>();

        //(1) value -> key 탐색에 keySet과 stream을 활용한 풀이
        for (int i = 0; i < M; i++) {
            map.put(br.readLine(), ++num);
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (Character.isLetter(str.charAt(0))) {
                System.out.println(map.get(str));
            } else {
                map.keySet().stream().filter(j -> map.get(j) == Integer.parseInt(str))
                        .forEach(System.out::println);
            }
        }

        //(2) value -> key 탐색을 배열로 대신한 풀이
        String[] arr = new String[M + 1];

        for (int i = 1; i <= M; i++) {
            String str = br.readLine();
            map.put(str, i);
            arr[i] = str;
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (Character.isLetter(str.charAt(0))) {
                System.out.println(map.get(str));
            } else {
                System.out.println(arr[Integer.parseInt(str)]);
            }
        }
    }
}
