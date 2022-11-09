package programmers;
import java.util.*;

import static java.util.stream.Collectors.*;

/*스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.
* 스파이가 가진 의상들의 종류와 이름이 담긴 2차원 배열이 주어질 때,
* 서로 다른 옷의 조합의 수를 구하세요.
* 한 종류의 옷은 한 번에 한 개만 입을 수 있습니다.
* 스파이는 하루에 최소 한 개의 의상을 입습니다.*/
public class Hash_위장 {
    /**
     * HashMap에 옷의 종류를 key, 각 종류에 속하는 옷의 개수를 value로 하여 저장한 후,
     * 아래에 따라 게산해서 풀면 된다.
     *
     * "상의"가 n개, "하의"가 m개 주어졌을 때,
     * 상의는 입지 않거나 하나를 입는 경우의 수는 n + 1,
     * 하의를 입지 않거나 하나를 입는 경우의 수는 m + 1이므로,
     * 옷을 입는 모든 경우의 수는 (n+1) * (m+1)이다.
     * 그런데 최소 한 개의 의상은 입어야 하므로 모든 경우의 수는 (n+1) * (m+1) -1 이다.
     */
    public static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            String type = clothes[i][1];
            if (map.containsKey(type)) {
                map.put(type, map.get(type) + 1);
            } else {
                map.put(type, 1);
            }
        }
        int result = 1;
        for (String key : map.keySet()) {
            result *= (map.get(key) + 1);
        }

        return result - 1;
    }

    /**
     * 다른 분의 풀이를 보고 생각할 거리가 생겨서 기록한다.
     * 아래의 풀이는 array의 map으로의 변환, value를 사용한 계산 등
     * 위의 로직을 stream으로 그대로 옮겨놓은 것이라고 보면 된다.
     *
     * groupingBy, mapping 등 stream Collectors의 해당 메서드에 익숙하다면
     * 위의 풀이에 비해 간결하므로 로직을 한 눈에 파악하기 수월할 것이다.
     *
     * 그런데 위의 풀이에 비해 수행시간이 적게는 수십배, 크게는 100배 넘게 차이날 정도로
     * 속도가 현저히 떨어진다는 것을 발견했다.
     *
     * 이와 관련해 알아본 결과, stream을 사용할 때의 속도가 저하되는 원인과
     * 그럼에도 stream 사용을 고려할 만한 조건 등에 대해 잘 정리한 글을 발견해 링크를 남기겠다.
     * https://jypthemiracle.medium.com/java-stream-api%EB%8A%94-%EC%99%9C-for-loop%EB%B3%B4%EB%8B%A4-%EB%8A%90%EB%A6%B4%EA%B9%8C-50dec4b9974b
     */
    public int solution_stream(String[][] clothes) {
        return Arrays.stream(clothes)
                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                .values()
                .stream()
                .collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
    }
}
