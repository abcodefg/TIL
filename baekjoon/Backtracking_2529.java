package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//#2529 부등호
/*두 종류의 부등호 '<'과 '>'가 k개 나열된 순서열이 있다.
* k개의 부등호 순서를 만족하게끔 각 부등호의 좌우로 0~9의 한자리 수를 k+1개 배치시키려고 한다.
* 부등호를 제거하고 만든 k+1자리의 정수 중 가장 큰 수와 가장 작은 수를 구하시오.*/
public class Backtracking_2529 {
    static char[] signs;
    static boolean[] visited = new boolean[10];
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        signs = new char[k];
        for(int i = 0; i < k; i++) {
            signs[i] = sc.next().charAt(0);
        }
        dfs(0, new StringBuilder());
        System.out.println(list.get(list.size() - 1) + "\n" + list.get(0));
    }

    static void dfs(int idx, StringBuilder nums) {
        if(idx == signs.length + 1) {
            list.add(nums.toString());
            return;
        }

        for(int i = 0; i <= 9; i++) {
            // TODO 확인할 것 (1): 백트래킹은 모든 경우의 수 중 특정 조건을 만족하는 경우만 살펴본다.
            // TODO 확인할 것 (2): 숫자는 k+1개(목표 depth k+1), 부등호는 k개일 때
            //  ||(OR)을 통해 한 사이클을 추가하는 방식
            if(idx == 0 || (!visited[i] && compare(nums.charAt(nums.length() - 1) - '0', i, signs[idx - 1]))) {
                visited[i] = true;
                nums.append(i);
                dfs(idx + 1, nums);
                nums.deleteCharAt(nums.length() - 1);
                visited[i] = false;
            }
        }
    }

    static boolean compare(int num1, int num2, char sign) {
        if(sign == '<') return num1 < num2;
        else return num1 > num2;
    }
}
