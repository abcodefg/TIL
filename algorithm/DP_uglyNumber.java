package algorithm;

//Ugly Numbers
/*ugly number 는 2, 3, 5로만 나누어 떨어지는 수이다.
* 1은 1번째 ugly number 이다.
* ugly number 중 n 번째 수를 구하라. */
public class DP_uglyNumber {
    //bottom-up DP로 풀이했다.
    public int uglyNumbers(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for(int i = 1; i < n; i++) {
            ugly[i] = Math.min(ugly[i2] * 2, Math.min(ugly[i3] * 3, ugly[i5] * 5));
            if(ugly[i] == ugly[i2] * 2) i2++;
            if(ugly[i] == ugly[i3] * 3) i3++;
            if(ugly[i] == ugly[i5] * 5) i5++;
        }
        return ugly[n - 1];
    }
}
