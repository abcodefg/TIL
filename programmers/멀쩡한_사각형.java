package programmers;

public class 멀쩡한_사각형 {

    public long solution(int w, int h) {
        long answer = 0;
        for (int i = 0; i < w; i++) {
            answer += (long) h * i / w;
        }

        return answer * 2;
    }
}
