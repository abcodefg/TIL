package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// #11652 카드 풀이
/*카드의 개수 N과 각 카드에 적힌 정수들을 입력받아 가장 많이 가지고 있는 정수를 구하는 문제
* 정수의 범위는 -2^62 ~ 2^62이다.*/
public class Sort_11652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int max = 1;
        Map<Long, Integer> map = new HashMap<>();

        // Map을 활용하여, key에 정수를 입력받고 그 value로서 각 정수의 개수를 입력한다.
        for(int i = 0; i < N; i++) {
            long key = Long.parseLong(br.readLine());
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
                if (max < map.get(key)) {
                    max = map.get(key);
                }
            }
            else {
                map.put(key, 1);
            }
        }
        // ArrayList로 개수가 가장 많은 정수들의 목록을 만든다.
            // (1) for-each loop를 활용한 방법
        ArrayList<Long> list = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }
            // (2) Iterator를 활용한 방법
        Iterator<Map.Entry<Long, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, Integer> entry = it.next();
            if(entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }

        // ArrayList를 오름차순으로 정렬한 뒤 그 중 가장 작은 정수를 출력한다.
        Collections.sort(list);

        System.out.println(list.get(0));
    }
}
