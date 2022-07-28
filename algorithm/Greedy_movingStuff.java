package algorithm;

import java.util.Arrays;

// 그리디 알고리즘 - 이삿짐 나르기 문제
    /*이사를 위해 짐을 박스에 담아 옮기려고 한다.
    * 단, 한 박스에 짐은 2개씩만 담을 수 있으며, 담을 수 있는 무게에도 제한이 있다.
    * 가능한 한 박스를 적게 사용하려고 할 때, 짐들의 무게에 대한 배열과 무게 제한을 입력받아
    * 박스 개수의 최소값을 구하시오*/
public class Greedy_movingStuff {
    // 가장 무거운 짐부터 차례대로 다른 짐들과 박스에 같이 넣을 수 있는지 여부를 확인할 것이다
    // 가장 가벼운 짐을 함께 담을 수 없다면 다른 짐들도 함께 담을 수 없으므로
    // 하나만을 담고 박스의 개수를 늘린다
    public static int movingStuff(int[] stuff, int limit) {
        // 짐의 무게를 오름차순으로 정렬한다
        Arrays.sort(stuff);
        // 박스의 개수를 셀 변수
        int numberOfBoxes = 0;

        // 박스에 짐을 다 담고 나면 목록에서 제거해나갈 것이므로
        // 박스에 모든 짐을 담았을 때 즉, 목록이 비었을 때 반복문을 종료한다
        while(stuff.length != 0) {
            // 박스에 짐이 하나만 남았을 때는 박스의 개수를 늘리고 반복문을 종료한다
            if(stuff.length == 1) {
                numberOfBoxes++;
                break;
            }
            int lightest = stuff[0];
            int heaviest = stuff[stuff.length - 1];
            // 가장 가벼운 짐과 가장 무거운 짐의 무게의 합이 무게 제한 이하라면 둘 다 박스에 담고 박스 카운트를 늘려준다
            if(lightest + heaviest <= limit) {
                stuff = Arrays.copyOfRange(stuff, 1, stuff.length - 1);
                numberOfBoxes++;
            }
            // 무게 제한을 초과한다면 무거운 짐만을 담고 박스 카운트를 늘린다
            else {
                stuff = Arrays.copyOfRange(stuff, 0, stuff.length - 1);
                numberOfBoxes++;
            }
        }
        return numberOfBoxes;
    }
}
