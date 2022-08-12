package baekjoon;
import java.io.*;
import java.util.*;

// #11656 접미사 배열 풀이
/*주어진 단어의 접미사를 사전순으로 정렬해서 출력하는 문제
* 문자열 정렬이 이루어지는 방식을 알 수 있었다*/
public class Sort_11656 {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String word = br.readLine();
            String[] arr = new String[word.length()];
            for(int i = 0; i < word.length(); i++) {
                arr[i] = word.substring(i);
            }

            Arrays.sort(arr);
            for(int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }
    }
