package programmers;
/*
* 양의 정수 n이 주어집니다. 이 숫자를 k진수로 바꿨을 때, 변환된 수 안에 아래 조건에 맞는 소수(Prime number)가 몇 개인지 알아보려 합니다.
*
* - 0P0처럼 소수 양쪽에 0이 있는 경우
* - P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
* - 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
* - P처럼 소수 양쪽에 아무것도 없는 경우
* - 단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
*       예를 들어, 101은 P가 될 수 없습니다.
*
* 예를 들어, 437674을 3진수로 바꾸면 211020101011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 왼쪽부터 순서대로 211, 2, 11이 있으며, 총 3개입니다.
* (211, 2, 11을 k진법으로 보았을 때가 아닌, 10진법으로 보았을 때 소수여야 한다는 점에 주의합니다.)
* 211은 P0 형태에서 찾을 수 있으며, 2는 0P0에서, 11은 0P에서 찾을 수 있습니다.
*
* 정수 n과 k가 매개변수로 주어집니다. n을 k진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 위 조건에 맞는 소수의 개수를 return 하도록 solution 함수를 완성해 주세요.
*
* (제한사항)
* 1 ≤ n ≤ 1,000,000
* 3 ≤ k ≤ 10
*
* 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92335
 */
public class k진수에서_소수_개수_구하기 {
    /**
     *
     */
    private boolean isPrime(long n) {
        if (n <= 1) return false;
        else if (n == 2) return true;
        // n의 제곱근보다 큰 자연수로 나누어진다면
        // 그 몫은 n의 제곱근보다 작은 자연수일 것이고, 반복문의 이전 단계에서 검토되었을 것
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }

        return true;
    }

    public int solution(int n, int k) {
        String num = "";
        while (n > 0) {
            num = n % k + num;
            n /= k;
        }
        num += "0";

        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (char c : num.toCharArray()) {
            if (c != '0') {
                sb.append(c);
                continue;
            } else if (sb.length() == 0) continue;

            long x = Long.parseLong(sb.toString());
            if (isPrime(x))
                count++;

            sb.setLength(0);
        }
        return count;
    }

    /**
     * 다른 사람의 풀이 -1
     * String.split(), Integer.toString() 등 라이브러리 함수들을 잘 활용해서 내 풀이보다 간결하다.
     *
     * 특히, Integer.toString()을 활용해 10진수를 String 타입의 진수로 변환하는 방법은 알아두면 비슷한 문제를 풀 때 활용하면 좋을 것 같다.
     */
    public int solution2(int n, int k) {
        int ans = 0;
        // TODO Check (1) : Integer.toString() 활용한 진법 변환
        //  N진수 -> 10진수로의 변환은 Integer.parseInt(N진수, N)
        String temp[] = Integer.toString(n, k).split("0");

        Loop : for(String t : temp) {
            if(t.length() == 0) continue;
            long a = Long.parseLong(t);
            if(a == 1) continue;
            for(int i=2; i<=Math.sqrt(a); i++)
                if(a%i == 0) continue Loop;

            ans++;
        }
        return ans;
    }

    /**
     * 다른 사람의 풀이 -2
     * 이 풀이는 문자열을 자를 때 substring()을 사용했는데,
     * 이중반복문을 사용해 그 범위를 설정하는 방식이 특이해서 봐두면 좋을 것 같다.
     */
    public int solution3(int n, int k) {
        String num = "";
        while (n > 0) {
            num = n % k + num;
            n /= k;
        }

        // TODO Check (2) : 이중반복문을 활용한 인덱스 범위 설정
        // 범위의 시작 지점을 수정하는 부분(i = j)과
        // 종료 지점의 확장을 종료하는 조건을 설정하는 부분(num.charAt(j) != '0'),
        // 내부 반복문의 함수 부분을 작성하지 않았다는 점
        // 등을 눈여겨 보자.
        int count = 0, i, j;
        for (i = 0; i < num.length(); i=j) {
            for (j = i + 1; j < num.length() && num.charAt(j) != '0'; j++);
            if (isPrime(Long.parseLong(num.substring(i, j))))
                count++;
        }
        return count;
    }
}
