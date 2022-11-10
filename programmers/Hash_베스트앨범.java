package programmers;

import java.util.*;

/*스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려고 합니다.
* 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
* 1. 속한 노래가 많이 재생된 장르를 먼저 수록
* 2. 장르 내에서 많이 재생된 노래를 먼저 수록
* 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록
* 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return하는 함수를 완성하세요.*/
public class Hash_베스트앨범 {
    /**
     * 우선, map에 장르를 key로, 속한 노래의 재생 횟수 총합을 value로 기록한다.
     * 집계된 value를 기준으로 장르를 나열하고
     * 각 장르별로 반복문을 순회하며 첫번째, 두번째로 많이 재생된 노래를 찾아
     * 순서대로 정답 list에 추가한다.
     * 이후, list를 배열로 변환해 return한다.
     *
     * 어떻게든 풀긴 했는데 너무 어거지로 푼 것 같아 마음에 드는 풀이는 아니다.
     */

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            //기존 풀이
            if (map.containsKey(genre)) {
                map.put(genre, map.get(genre) + plays[i]);
            } else {
                map.put(genre, plays[i]);
            }

        //TODO 확인할 것 (1): Map의 getOrDefault()
            //개선한 풀이
            map.put(genre, map.getOrDefault(genre, 0) + plays[i]);
        }

        //TODO 확인할 것 (2): Set을 List로 변환
        //장르의 list를 만들고, 속한 노래가 많이 재생된 순서로 정렬한다.
        List<String> genreList = new ArrayList<>(map.keySet());
        Collections.sort(genreList, (a, b) -> map.get(b) - map.get(a));

        List<Integer> answer = new ArrayList<>();
        for (String genre : genreList) {
            //해당 장르의 가장 많이 재생된 노래를 찾아 answer에 추가한다.
            int max = 0;
            int idx = 0;
            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(genre) && plays[i] > max) {
                    max = plays[i];
                    idx = i;
                }
            }
            answer.add(idx);

            //두번째로 많이 재생된 노래를 찾고, 있다면 answer에 추가한다.
            int max2 = 0;
            int idx2 = 0;
            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(genre) && plays[i] > max2 && i != idx) {
                    max2 = plays[i];
                    idx2 = i;
                }
            }
            if (max2 != 0)
                answer.add(idx2);
        }

        //List를 배열로 변환.
        // stream을 사용하는 방법도 가능하지만
        // 속도에서 손해를 보게 되므로(Hash_위장 풀이 참고) for loop을 사용했다.
        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }
}
