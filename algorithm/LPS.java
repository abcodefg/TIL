package algorithm;

//주어진 문자열의 가장 긴 접두어이자 접미어를 찾아 그 길이를 구하는 문제
//접두어와 접미어는 서로 겹치는 부분이 없어야 한다.
//LeetCode의 1392. Longest Happy Prefix 문제의 쉬운 버전
public class LPS {
    public int LPS(String str) {
        char[] arr = str.toCharArray();
        int length = str.length();
        //접두어와 접미어가 겹치지 않아야 하므로 접미어가 시작하는 부분을 지정한다.
        //문자열의 길이가 짝수/홀수인지에 따라 달라진다.
        int sufIdx = (length % 2 == 0) ? length / 2 : length / 2 + 1;
        int answer = 0;

        Outer:
        //접미사의 포인터
        for(int i = sufIdx; i < length; i++)  {
            int count = 0;
            //더할 값
            for(int j = 0; j < length - i; j++) {
                if(arr[j] == arr[i + j]) count++;
                else break;

                //끝까지 탐색했음에도 반복문이 종료되지 않은 경우
                //일치하는 접두어와 접미어가 있다는 것이므로 그 길이를 반환한다.
                if(j == length - i - 1) {
                    answer = count;
                    break Outer;
                }
            }
        }

        return answer;
    }
}
