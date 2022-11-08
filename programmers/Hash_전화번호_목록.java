package programmers;
import java.util.*;

/*전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하여,
* 있으면 false, 없으면 true를 반환합니다.
* phone_book의 길이는 1 이상 1,000,000 이하입니다.
* 각 전화번호의 길이는 1 이상 20 이하입니다.
* 같은 전화번호가 중복해서 들어있지 않습니다.
* ["19", "192", "1834"]이 주어진 경우, 19가 192의 접두어이므로 false*/
public class Hash_전화번호_목록 {
    /**
     * 가장 단순한 방법은 이중 반복문을 통해 모든 요소를 각각 비교하는 것이나
     * 시간복잡도가 O(n^2)이므로 비효율적인 풀이이다.
     *
     * 이 경우 반복문의 효율을 높이는 방향으로 풀이를 발전시킬 수 있다.
     * 무작위로 선정된 두 원소를 비교하는 것이 아닌
     * 서로 (접두어)포함관계에 있을 확률이 높은 두 요소를 비교하는 것이다.
     * String 배열의 정렬은 원소를 알파벳순으로 정렬한다.
     * ["19", "1834", "192"]라는 String 배열을 정렬한 결과는
     * ["1834", "19", "192"]가 된다.
     * 이 경우 ,어떤 String 배열 X의 원소 A가 B의 접두어라면,
     * X를 정렬했을 때 A와 B는 인접해 있을 것이므로,
     * 배열의 첫 요소부터 끝까지 순회하며 접두어 관계가 있는지 확인하면 된다.
     *
     * 풀이의 시간복잡도는 Arrays.sort()을 사용했으므로 O(n log n)이다.*/
    public boolean solution_sort(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i]))
                return false;
        }
        return true;
    }

    /**
     * 문제가 분류된 카테고리를 고려하여 해시를 적용해서도 풀어보려고 했다.
     * 그런데 HashSet을 사용하자니 순서를 보장하지 않는 set의 특성상 순차적인 탐색이 어렵고,
     * HashMap을 사용하자니 value를 무엇으로 해야 하는지가 애매했다.
     * 결국 해시를 사용한 풀이는 잘 떠오르지 않아 다른 분의 풀이를 참고했다.
     * 알고보니 value는 아무렇게나 설정하고
     * hashmap 클래스의 성능 좋은 탐색 메서드인 containsKey를 활용하기만 하면 되는 문제였다.
     * map의 key로 배열의 원소들을 저장하고 각 원소의 접두어가 map에 존재하는지 확인한다.
     */
    public boolean solution_hash(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < phone_book.length; i++) {
            map.put(phone_book[i], i);
        }

        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 1; j < phone_book[i].length(); j++) {
                if (map.containsKey(phone_book[i].substring(0,j)))
                    return false;
            }
        }
        return true;
    }
}
