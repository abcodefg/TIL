package programmers;

import java.util.*;

/*
* 주차장의 요금표와 차량이 들어오고(입차) 나간(출차) 기록이 주어졌을 때, 차량별로 주차 요금을 계산하려고 합니다.
*
* - 어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주합니다.
* - 0000번 차량은 18:59에 입차된 이후, 출차된 내역이 없습니다. 따라서, 23:59에 출차된 것으로 간주합니다.
* - 00:00부터 23:59까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산합니다.
* - 누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구합니다.
* - 누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.
*   - 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림합니다.
*   - ⌈a⌉ : a보다 작지 않은 최소의 정수를 의미합니다. 즉, 올림을 의미합니다.
*
* 주차 요금을 나타내는 정수 배열 fees, 자동차의 입/출차 내역을 나타내는 문자열 배열 records가 매개변수로 주어집니다.
* 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return 하도록 solution 함수를 완성해주세요.
*
* 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92341
 */
public class 주차_요금_계산 {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new HashMap<>();

        for (String r : records) {
            String[] recArr = r.split(" ");
            String car = recArr[1];
            int time = timeStringToMin(recArr[0]);
            if (recArr[2].equals("IN"))
                time = -time;
            map.put(car, map.getOrDefault(car, 0) + time);
        }

        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = calculateFee(fees, map.get(list.get(i)));
        }

        return answer;
    }

    private int timeStringToMin(String s) {
        String[] arr = s.split(":");
        // TODO check : Integer.parseInt()를 사용해 "09"와 같은 문자열도 정수형으로 바꿀 수 있다
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }

    private int calculateFee(int[] fees, int time) {
        if (time <= 0)
            time = 1439 + time;

        int minFee = fees[1], extraFee;

        if (time <= fees[0])
            return minFee;

        time -= fees[0];
        time = time % fees[2] == 0 ? time / fees[2] : time / fees[2] + 1;
        extraFee = fees[3] * time;

        return minFee + extraFee;
    }
}
