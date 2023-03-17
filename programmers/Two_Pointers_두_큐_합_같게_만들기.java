package programmers;

public class Two_Pointers_두_큐_합_같게_만들기 {

    public int solution(int[] queue1, int[] queue2) {
        int[] queue = new int[queue1.length + queue2.length];

        long target = 0, sum = 0;
        for (int i = 0; i < queue1.length; i++) {
            target += queue[i] = queue1[i];
            target += queue[queue1.length + i] = queue2[i];

            sum += queue[i];
        }
        // 개선한 부분
        if (target % 2 == 1)
            return -1;

        target /= 2;
        int start = 0, end = queue1.length, count = 0;
        int answer = -1;
        while (end < queue.length) {
            if (target == sum) {
                answer = count;
                break;
            } else if (target < sum) {
                sum -= queue[start++];
            } else {
                sum += queue[end++];
            }
            count++;
        }

        return answer;
    }
}
