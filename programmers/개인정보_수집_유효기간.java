package programmers;

import java.util.*;

/*고객의 약관 동의를 얻어서 수집된 1~n번으로 분류되는 개인정보 n개가 있습니다.
* 약관 종류는 여러 가지 있으며 각 약관마다 개인정보 보관 유효기간이 정해져 있습니다.
* 당신은 각 개인정보가 어떤 약관으로 수집됐는지 알고 있습니다.
* 수집된 개인정보는 유효기간 전까지만 보관 가능하며, 유효기간이 지났다면 반드시 파기해야 합니다.
*
* 오늘 날짜를 의미하는 문자열 today, 약관의 유효기간을 담은 1차원 문자열 배열 terms와 수집된 개인정보의 정보를 담은 1차원 문자열 배열 privacies가 매개변수로 주어집니다.
* 이때 파기해야 할 개인정보의 번호를 오름차순으로 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
*
* (제한사항)
* - today는 "YYYY.MM.DD" 형태로 오늘 날짜를 나타냅니다.
* - terms의 원소는 "약관 종류 유효기간" 형태의 약관 종류와 유효기간을 공백 하나로 구분한 문자열입니다.
* - privacies의 원소는 "날짜 약관 종류" 형태의 날짜와 약관 종류를 공백 하나로 구분한 문자열입니다.
* - today와 privacy에 등장하는 "날짜"의 MM이 한 자릿수인 경우 앞에 0이 붙습니다.
*
* https://school.programmers.co.kr/learn/courses/30/lessons/150370
 */
public class 개인정보_수집_유효기간 {
    /**
     * 간단한 구현 문제인데, 눈여겨 볼 점은 크게 두 가지이다.
     * 1. 날짜 계산시 예외 케이스에 유의한다.
     * 2. 약관별 유효기간을 확인하는 방법으로 privacies의 반복문 내부에 terms의 반복문을 순회하는 것 역시 가능하다.
     *      terms의 길이가 짧은 경우 이 방법의 수행 시간이 더 짧게 나오기도 하나,
     *      terms의 길이가 길어지고 매 privacies의 원소마다 비교해야 할 terms의 원소의 개수가 많아질수록
     */
    public static void main(String[] args) {
        String today = "2020.01.01";
        String[] terms = {"Z 3", "D 5"};
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
        System.out.println(Arrays.toString(solution(today, terms, privacies)));
    }
    public static int[] solution(String today, String[] terms, String[] privacies) {

        Map<String, String> map = new HashMap<>();
        for (String term : terms) {
            String[] curr = term.split(" ");
            map.put(curr[0], curr[1]);
        }

        List<Integer> list = new ArrayList<>();

        String[] privacy;
        for (int i = 0; i < privacies.length; i++) {
            privacy = privacies[i].split(" ");

            String expirationDate = getExpirationDate(Integer.parseInt(map.get(privacy[1])), privacy[0]);
            if (today.compareTo(expirationDate) >= 0)
                list.add(i + 1);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    public static String getExpirationDate(int period, String startDate) {
        String[] dateArray = startDate.split("\\.");
        int month = Integer.parseInt(dateArray[1]);
        month += period;

        // TODO 확인할 것 : 예외 케이스 확인할 것!
        if (month % 12 == 0) {
            dateArray[0] = String.valueOf(Integer.parseInt(dateArray[0]) + ((month-1) / 12));
            dateArray[1] = String.valueOf(month - (12 * ((month-1) / 12)));
        } else {
            dateArray[0] = String.valueOf(Integer.parseInt(dateArray[0]) + (month / 12));
            dateArray[1] = String.valueOf(month - (12 * (month / 12)));
        }
        dateArray[1] = dateArray[1].length() == 1 ? "0" + dateArray[1] : dateArray[1];

        return String.join(".", dateArray);
    }
}
