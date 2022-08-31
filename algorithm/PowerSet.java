package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*집합인 문자열을 입력받아 각 문자로 만들 수 있는 모든 부분집합을 ArrayList에 담아 출력하는 문제
* 이때, 원래 집합은 원소의 중복을 허용하지 않고,
* 부분집합의 각 요소는 알파벳순, ArrayList는 오름차순으로 정렬되어야 한다.
* ex) 입력: "abc"  출력: ["", "a", "ab", "abc", "ac", "b", "bc", "c"]*/

public class PowerSet {
    // DFS를 응용해서 풀었다.
    public static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        String str = "jump";
        String newStr = "";

        // TODO 확인할 것 (1): 문자열의 중복 제거
        for (int i = 0; i < str.length(); i++) {
            if(str.indexOf(str.charAt(i)) == i)
                newStr += str.charAt(i);
        }
        char[] arr = newStr.toCharArray();
        Arrays.sort(arr);

        powerSet(arr, new boolean[arr.length], 0);
        Collections.sort(list);
        System.out.println(list);
    }

    static void powerSet(char[] arr, boolean[] visited, int depth) {
        // depth는 인덱스의 역할을 한다.
        // 각 재귀 호출 단계에서 depth의 요소를 선택할지 결정하고
        // 재귀 호출이 끝나면 선택된 요소들을 취합하여 문자열(부분집합)을 생성해 list에 더한다.
        if (depth == arr.length) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < arr.length; i++) {
                if(visited[i]) {
                    sb.append(arr[i]);
                }
            }
            list.add(sb.toString());
            return;
        }
        // TODO 확인할 것 (2): 배열의 특정 요소 선택하거나 하지 않고 재귀 호출
        visited[depth] = true;
        powerSet(arr, visited, depth + 1);
        visited[depth] = false;
        powerSet(arr, visited, depth + 1);
    }
}
