package programmers;
import java.util.*;
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
}
