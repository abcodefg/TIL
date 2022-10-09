package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

//#6603 로또
/*독일 로또는 {1, 2, ..., 49}에서 수 6개를 고른다.
* 로또 번호를 선택하는데 사용되는 가장 유명한 전략은 49가지 수 중 k(k>6)개의 수를 골라
* 집합 S를 만든 다음 그 수만 가지고 번호를 선택하는 것이다.*/
public class Backtracking_6603 {
    static int k;
    static int[] arr;
    static int[] pick;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while((k = sc.nextInt()) != 0) {
            arr = new int[k];
            pick = new int[6];
            for(int i = 0; i < k; i++) {
                arr[i] = sc.nextInt();
            }
            dfs(0, 0);
            System.out.println();
        }
    }

    static void dfs(int depth, int idx) {
        if(idx == 6) {
            System.out.println(Arrays.toString(pick).replace(",","")
                    .replace("[","").replace("]",""));
            return;
        }
        //백트래킹
        if(idx + (k - depth) < 6) return;
        pick[idx] = arr[depth];
        dfs(depth + 1, idx + 1);
        pick[idx] = 0;
        dfs(depth + 1, idx);
    }
}
