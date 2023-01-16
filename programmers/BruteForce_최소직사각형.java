package programmers;

/*명함 지갑을 만드는 회사에서 지갑의 크기를 정하려고 합니다.
* 모든 명함의 가로 길이와 세로 길이를 나타내는 2차원 배열 sizes가 매개변수로 주어질 때,
* 모든 명함을 수납할 수 있는 가장 작은 지갑의 크기를 return 하도록 solution 함수를 작성해주세요.
* (명함을 눕혀서 수납할 수도 있습니다.)
 */
public class BruteForce_최소직사각형 {
    /**
     * 구체적인 풀이보다는 접근법이 중요한 문제다.
     * 가로, 세로가 아니라 긴 변과 작은 변으로 보면 쉽게 풀린다.
     *
     * 지갑의 긴 변과 작은 변의 길이가 W와 H이고,
     * 명함의 긴 변과 작은 변의 길이가 w와 H일 때,
     * w > W 이거나 h > H 라면 (명함을 지갑에 수납할 수 없다면)
     * w > W > H 이거나 w > h > H 이므로 명함을 가로로 눕히더라도 지갑에 수납할 수 없다.
     */
    public int solution(int[][] sizes) {
        int w = 0;
        int h = 0;

        for (int i = 0; i < sizes.length; i++) {
            w = Math.max(w, Math.max(sizes[i][0], sizes[i][1]));
            h = Math.max(h, Math.min(sizes[i][0], sizes[i][1]));
        }
        return w * h;
    }
}
