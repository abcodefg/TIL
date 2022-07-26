package baekjoon;

// 백준 #4673 셀프넘버 풀이
    /*양의 정수 n에 대해서 n과 n의 각 자릿수를 더한 숫자가 d(n)이라고 하자.
    * 이 때 n을 d(n)의 생성자라고 하며, 생성자가 없는 수를 셀프넘버라고 한다.
    * 10000보다 작거나 같은 셀프넘버를 한 줄에 하나씩 출력하는 문제 */
public class SelfNumber_4673 {

    public static void main(String[] args) {
        // 10000 이하의 양의 정수 목록을 만든다.
        // 따로 지정하지 않을 경우, boolean 배열의 요소는 false로 초기화된다.
        boolean[] arr = new boolean[10001];

        // 10000보다 작은 모든 양의 정수 n에 대해서 d(n)을 계산한다.
        for(int i = 1; i <= 10000; i++) {
            int x = i;
            int sum = 0;
            while(x != 0) {
                sum += x % 10;
                x /= 10;
            }
            sum = sum + i;

        // d(n)의 인덱스에 해당하는 요소만을 true로 바꾸어주어 목록에서 제거한다.
            if(sum <= 10000) arr[sum] = true;
        }

        // 이후, 목록을 순회하며 값이 false인 요소의 인덱스(셀프넘버)를 출력한다.
        for(int i = 1; i <= 10000; i++) {
            if(!arr[i]) {
                System.out.println(i);
            }
        }

        // boolean 배열로 긴 목록 체크하기, 거듭 10으로 나누어주며 자릿수 구하기 등
        // 다른 문제에서 떠올리거나 배웠던 방법들을 활용해서 문제를 풀었다.

    }
}
