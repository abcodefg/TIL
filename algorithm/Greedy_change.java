package algorithm;

// 그리디 알고리즘 - 거스름돈 주기
    /*500원, 100원, 50원, 10원, 5원 1원짜리 동전이 있다.
    *거스름돈을 줄 때 필요한 동전 개수의 최솟값을 구하라*/
public class Greedy_change {

    public int change(int k) {
        int count = 0;
        int change = 500;

        while(k != 0) {
            // 액수가 큰 동전으로 지불하고 남은 나머지가 동전의 액수보다 작아지면
            // 동전간의 상관관계를 이용해 동전의 크기를 한 단계 줄인다
            if(k < change) {
                int temp = change;
                while(temp >= 10) {
                    temp /= 10;
                }
                if(temp == 5) change /= 5;
                else change /= 2;
            }

            count = count + (k / change);
            k %= change;
        }
        return count;
    }
}
