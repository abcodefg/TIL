package programmers;

/*당신은 이진트리를 수로 표현하는 것을 좋아합니다.
*
* 이진트리를 수로 표현하는 방법은 다음과 같습니다.
*
* 1. 이진수를 저장할 빈 문자열을 생성합니다.
* 2. 주어진 이진트리에 더미 노드를 추가하여 포화 이진트리로 만듭니다. 루트 노드는 그대로 유지합니다.
* 3. 만들어진 포화 이진트리의 노드들을 가장 왼쪽 노드부터 가장 오른쪽 노드까지, 왼쪽에 있는 순서대로 살펴봅니다. 노드의 높이는 살펴보는 순서에 영향을 끼치지 않습니다.
* 4. 살펴본 노드가 더미 노드라면, 문자열 뒤에 0을 추가합니다. 살펴본 노드가 더미 노드가 아니라면, 문자열 뒤에 1을 추가합니다.
* 5. 문자열에 저장된 이진수를 십진수로 변환합니다.
*
* 이진트리에서 리프 노드가 아닌 노드는 자신의 왼쪽 자식이 루트인 서브트리의 노드들보다 오른쪽에 있으며,
* 자신의 오른쪽 자식이 루트인 서브트리의 노드들보다 왼쪽에 있다고 가정합니다.
*
* 당신은 수가 주어졌을때, 하나의 이진트리로 해당 수를 표현할 수 있는지 알고 싶습니다.
*
* 이진트리로 만들고 싶은 수를 담은 1차원 정수 배열 numbers가 주어집니다.
*
* numbers에 주어진 순서대로 하나의 이진트리로 해당 수를 표현할 수 있다면 1을,
* 표현할 수 없다면 0을 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 */
public class DivideAndConquer_표현_가능한_이진트리 {
    /**
     * 이진트리를 탐색하는 문제이다.
     * 풀이는 크게 두 가지 부분으로 나뉜다.
     *
     * 1) 주어진 10진수를 2진수로 변환하되, 2진수의 길이가 포화이진트리의 모든 노드의 개수가 되도록 한다.
     * 2) (더미노드를 추가한) 포화이진트리를 나타낼 수 있는지 자식 노드들이 루트 노드인 서브트리를 재귀적으로 탐색함으로써 확인한다.
     *
     * 1)의 경우 '포화이진트리의 모든 노드의 개수'라는 점을,
     * 2)의 경우 '부모 노드가 더미 노드이면 자식 노드도 더미 노드여야 한다'는 점을
     * 특히 유의해야 한다.
     */
    public String toBinary(long number) {
        // number를 이진수로 변환
        //  +) Long.toBinaryString()이라는 메서드를 사용할 수도 있다.
        StringBuilder sb = new StringBuilder();
        while (number != 0) {
            sb.append(number % 2);
            number /= 2;
        }

        // 포화이진트리의 높이가 h일 때, 모든 노드의 개수는 2^h - 1이다.
        // x를 2배씩 늘리면서, 2진수의 길이 len보다 크거나 같은 가장 작은 포화이진트리 노드의 개수 x - 1를 구하고
        // 부족한 길이만큼 더미노드 0을 붙인다.
        int x = 2;
        int len = sb.length();

        while (x - 1 < len) {
            x *= 2;
        }

        sb.append("0".repeat(x - 1 - len));

        // 10진수를 2진수로 변환하는 과정의 특성상 sb가 뒤집어져 있을 것이므로 순서를 반전시킨 뒤 return한다.
        return sb.reverse().toString();
    }

    // 재귀 방식으로 이진수를 탐색하며 포화이진트리 여부 확인
    public boolean checkBinaryTree(int start, int end, String binary) {
        // 리프 노드인 경우 true 반환
        if (start == end)
            return true;

        // 부모 노드가 더미 노드(=0)라면 자식 노드도 더미 노드여야 한다.
        // 따라서 부모 노드가 0인데 자식 노드가 1인 경우 false 반환
        int mid = (start + end) / 2;
        int left = (start + mid - 1) / 2;
        int right = (mid + 1 + end) / 2;
        if (binary.charAt(mid) == '0' &&
                (binary.charAt(left) == '1' || binary.charAt(right) == '1'))
            return false;

        // 왼쪽 / 오른쪽 자식 노드가 루트 노드인 서브트리가 모두 포화이진트리라면 true, 아니라면 false 반환
        return checkBinaryTree(start, mid - 1, binary)
                && checkBinaryTree(mid + 1, end, binary);
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = toBinary(numbers[i]);
            if (checkBinaryTree(0, binary.length() - 1, binary))
                answer[i] = 1;
            else
                answer[i] = 0;
        }
        return answer;
    }
}
