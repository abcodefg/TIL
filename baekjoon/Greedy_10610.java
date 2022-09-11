package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

//#10610 30
public class Greedy_10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        Integer[] numArr = new Integer[arr.length];
        int sum = 0;
        boolean containsZero = false;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == '0')
                containsZero = true;
            if (i == arr.length - 1 && !containsZero) {
                System.out.println(-1);
                return;
            }
            int num = arr[i] - '0';
            numArr[i] = num;
            sum += num;
        }

        if(sum % 3 != 0) {
            System.out.println(-1);
            return;
        }
        Arrays.sort(numArr, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for(int num : numArr) {
            sb.append(num);
        }
        System.out.println(sb);
    }
}
