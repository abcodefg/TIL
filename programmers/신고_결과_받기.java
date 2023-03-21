package programmers;

import java.util.*;

/*
* 신입사원 무지는 게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템을 개발하려 합니다. 무지가 개발하려는 시스템은 다음과 같습니다.
*
* 각 유저는 한 번에 한 명의 유저를 신고할 수 있습니다.
* 신고 횟수에 제한은 없습니다. 서로 다른 유저를 계속해서 신고할 수 있습니다.
* 한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
* k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
* 유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송합니다.
*
* 이용자의 ID가 담긴 문자열 배열 id_list, 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report, 정지 기준이 되는 신고 횟수 k가 매개변수로 주어질 때,
* 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.
*
* 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92334
 */
public class 신고_결과_받기 {
    /**
     * key가 id, value가 각각 '신고당한 횟수'와 '신고한 id의 목록'인 Map을 만들어 기록하는 방식으로 접근했다.
     * key가 id, value가 해당 id의 id_list에서의 인덱스인 HashMap을 추가해서 마지막 반복문
     *
     */
    public int[] solution(String[] id_list, String[] report, int k) {
        // 변수 초기화
        Map<String, Integer> reportCount = new HashMap<>();
        Map<String, Set<String>> map = new HashMap<>();
        for (String id : id_list) {
            map.put(id, new HashSet<>());
        }

        // 각각 id별 신고당한 횟수, id별 신고한 id의 목록을 기록
        for (String r : report) {
            String[] ids = r.split(" ");
            // TODO : Check : Map의 getOrDefault()
            reportCount.put(ids[1], reportCount.getOrDefault(ids[1], 0) + 1);
            map.get(ids[0]).add(ids[1]);
        }

        // 각 유저가 신고한 유저들 중 신고횟수가 k 이상인 유저의 수만큼 메일 받는 횟수 +1
        int[] answer = new int[id_list.length];
        for (int i = 0; i < answer.length; i++) {
            String id = id_list[i];
            for (String reportedId : map.get(id)) {
                if (reportCount.get(reportedId) >= k)
                    answer[i]++;
            }
        }
        return answer;
    }

    /**
     * 다른 사람의 풀이 중 객체지향적으로 설계된 코드가 있어서 기록한다.
     *
     * 정지된 유저들의 리스트를 따로 만든다는 점을 제외하면 내 풀이와 구조가 비슷한데,
     * User라는 클래스를 통해 신고/피신고 목록을 통합적으로 관리한다는 차이가 있다.
     */
    public int[] solution2(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        ArrayList<User> users = new ArrayList<>();
        HashMap<String,Integer> suspendedList = new HashMap<>(); //<이름>
        HashMap<String,Integer> idIdx = new HashMap<>(); // <이름, 해당 이름의 User 클래스 idx>
        int idx = 0;

        for(String name : id_list) {
            idIdx.put(name,idx++);
            users.add(new User(name));
        }

        // 각 신고 기록을 신고한 유저와 신고당한 유저의 필드에 기록
        for(String re : report){
            String[] str = re.split(" ");
            users.get( idIdx.get(str[0])).reportList.add(str[1]);
            users.get( idIdx.get(str[1])).reportedList.add(str[0]);
        }

        // 자신을 신고한 유저의 수가 k 이상인 유저들을 정지 목록에 추가
        for(User user : users){
            if(user.reportedList.size() >= k)
                suspendedList.put(user.name,1);
        }

        // 각 유저가 신고한 유저가 정지 목록에 포함되어 있다면 메일 받는 횟수 +1
        for(User user : users){
            for(String nameReport : user.reportList){
                if(suspendedList.get(nameReport) != null){
                    answer[idIdx.get(user.name)]++;
                }
            }
        }

        return answer;
    }

    static class User{
        String name;                    // 유저의 Id
        HashSet<String> reportList;     // 해당 유저가 신고한 유저들의 목록
        HashSet<String> reportedList;   // 해당 유저를 신고한 유저들의 목록
        public User(String name){
            this.name = name;
            reportList = new HashSet<>();
            reportedList = new HashSet<>();
        }
    }
}
