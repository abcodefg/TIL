package algorithm.Permutation;

import java.util.ArrayList;
import java.util.Arrays;

// 입력받은 횟수만큼 가위바위보를 할 때, 한 사람이 낼 수 있는 모든 경우의 수를 담은 ArrayList를 반환하는 문제
// 단, 이때 rock, paper, scissors 순으로 정렬한다
//         입력           출력
// ex) rounds = 5 -> [[rock, rock, rock, rock, rock], [rock, rock, rock, rock, paper], ......]
public class Permutation_rockPaperScissors {

    public ArrayList<String[]> rockPaperScissors(int rounds) {

        ArrayList<String[]> list = new ArrayList<>();
        return perm(rounds, new String[]{}, list);
    }

    // 결과를 담을 out 자리에 빈 배열을 입력한다
    // 재귀호출할 때마다 rounds를 줄이는 동시에 결과를 담을 배열을 한 칸씩 채운다.
    public ArrayList<String[]> perm(int rounds, String[] out, ArrayList<String[]> list) {
        // rounds가 0에 도달하면 경우의 수 하나를 담은 배열이 다 찬 것이므로, 이를 list에 더하고 list를 반환한다
        if(rounds == 0) {
            list.add(out);
            return list;
        }

        String[] option = {"rock", "paper", "scissors"};
        // 옵션을 순회하며 배열에 차례대로 채운다
        for(int i = 0; i < option.length; i++) {
            // 지정된 옵션을 현재값에 입력한다
            String current = option[i];
            // 배열의 크기를 한 칸 늘리고 그 자리에 현재값을 입력한다
            String[] newArr = Arrays.copyOf(out, out.length + 1);
            newArr[newArr.length - 1] = current;
            // 채워야 하는 round를 하나 줄이고 재귀 호출한다
            list = perm(rounds - 1, newArr, list);
        }
        return list;
    }
}