package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Combination_newBlackJack {
    /*주어진 숫자카드 뭉치에서 3개의 카드를 뽑아
    * 그 합이 소수인 경우의 수를 구하라*/
    public static void main(String[] args) {
        int[] cards = {1, 2, 3, 4};
        System.out.println(boringBlackjack(cards));
    }
    public static int boringBlackjack(int[] cards) {
        // 어떤 수가 소수인지 보여주는 boolean 배열을 만든다
        int maxSum = cards[cards.length - 3] +  cards[cards.length - 2] + cards[cards.length - 1];
        boolean[] isPrime = new boolean[maxSum + 1];
        for(int i = 2; i < maxSum + 1; i++) {
            isPrime[i] = true;
        }
        for(int i = 2; i < maxSum + 1; i++) {
            if(isPrime[i]) {
                for(int j = 2; j <= maxSum / i; j++) {
                    isPrime[i * j] = false;
                }
            }
        }
        // 주어진 카드 목록에서 3개를 뽑는 조합을 모두 구한다
        ArrayList<int[]> list = combination(cards, new int[]{}, 0, 3, new ArrayList<int[]>());

        // 구한 조합을 순회하며 요소의 합이 소수라면 카운트한다
        int count = 0;
        for(int[] arr : list) {
            if(isPrime[arr[0] + arr[1] + arr[2]]) {
                count++;
            }
        }
        return count;
    }

    // 주어진 카드 목록에서 3개를 뽑는 조합을 모두 구한다
    // 빈 ArrayList를 입력받아 조합이 모두 채워진 ArrayList를 반환한다
    public static ArrayList<int[]> combination(int[] cards, int[] selected, int start, int r, ArrayList<int[]> list) {
        if(r == 0) {
            list.add(selected);
            return list;
        }
        int sum = 0;
        // 재귀호출할 때마다 start를 1씩 늘려주고
        // 반복문의 시작을 start에서 함으로써 중복을 회피한다
        for(int i = start; i < cards.length; i++) {
            int[] newSelected = Arrays.copyOf(selected, selected.length + 1);
            newSelected[newSelected.length - 1] = cards[i];
            // 재귀호출할 때마다 r을 하나씩 줄여서 r==0인 경우를 base case로 삼는다
            list = combination(cards, newSelected, i + 1, r - 1, list);
        }
        return list;
    }
}
