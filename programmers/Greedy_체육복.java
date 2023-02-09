package programmers;

public class Greedy_체육복 {
    /*점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다.
    * 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
    * 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
    *
    * 전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가
    * 매개변수로 주어질 때, 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.
    *
    * (여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다.
    * 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.)
     */
    public int solution(int n, int[] lost, int[] reserve) {
        // TODO 확인할 것 (1) : 문제의 조건에 유의
        //  a. 여벌의 체육복을 가져온 학생이 체육복을 도난당했을 수도 있다는 조건을 못 보고 푸느라 시간을 잡아먹었다.
        //      문제를 끝까지 정독하자.
        //  b. 이번 문제의 풀이에는 영향을 미치지 않았지만, 제공된 예시만을 보고 lost와 reserve가 당연히 오름차순으로 주어질 거라고 생각했다.
        //
        boolean[] isLost = new boolean[n + 1];
        // 다른 학생에게 '체육복을 빌려줄 수 있는' 학생을 나타내는 배열
        boolean[] isReserve = new boolean[n + 1];

        int answer = n;

        // 어떤 학생이 여벌의 체육복을 가져왔다면 true
        for (int num : reserve) {
            isReserve[num] = true;
        }

        // 체육복을 도난 당한 어떤 학생이
        for (int num : lost) {
            // 여벌의 체육복을 가져왔었다면, 남은 체육복이 하나이기에 빌려줄 수 없다.
            if (isReserve[num]) {
                isReserve[num] = false;
            }
            // 여벌의 체육복을 가져오지 않았다면, 체육복을 빌려야 한다.
            else {
                isLost[num] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            // 어떤 학생이 체육복을 빌리지 않아도 된다면 넘어간다.
            if (!isLost[i])
                continue;

            //
            if (i > 1 && isReserve[i - 1]) {
                isReserve[i - 1] = false;
            }
            else if (i < n && isReserve[i + 1]) {
                isReserve[i + 1] = false;
            }
            else {
                answer--;
            }
        }

        return answer;
    }

    /**
     * 내 풀이는 어떤 사람이 체육복을 도난당했는지, 여분을 가지고 있는지를 나타내기 위해 2개의 boolean 배열을 사용했다.
     * 명료하긴 하지만 공간복잡도를 높인다는 점에서 개선의 여지가 있다.
     *
     * 다른 사람의 풀이 중 상태를 int 배열 하나만으로 기록한 풀이가 있어서 기록한다.
     * 다른 부분은 대부분 첫 번째 풀이와 비슷하다.
     *
     */
    public int solution2(int n, int[] lost, int[] reserve) {
        // TODO 확인할 것 (2) : 여러 상태를 int 값 하나만으로 나타내기
        //  여러 개의 boolean 배열을 만들기보다 하나의 int 배열로
        // TODO 확인할 것 (3) : ArrayIndexOutOfBoundException 회피하기
        //  반복문을 통한 배열 탐색 중 index가 벗어날 경우를 대비해 index의 크기에 대한 조건문을 추가하는 대신
        //  탐색할 배열의 크기를 (앞 1 + 뒤 1 = 2) 크게 만드는 방법도 가능하다.
        int[] people = new int[n];
        int answer = n;

        for (int l : lost)
            people[l-1]--;
        for (int r : reserve)
            people[r-1]++;

        for (int i = 0; i < people.length; i++) {
            if(people[i] == -1) {
                if(i-1>=0 && people[i-1] == 1) {
                    people[i]++;
                    people[i-1]--;
                }else if(i+1< people.length && people[i+1] == 1) {
                    people[i]++;
                    people[i+1]--;
                }else
                    answer--;
            }
        }
        return answer;
    }
}
