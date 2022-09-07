package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// #11662 민호와 강호
/*민호와 강호가 2차원 좌표 평면 위에 있다.
민호는 점 A(Ax, Ay)에서 점 B(Bx, By)를 향해 걸어가고 있고,
강호는 점 C(Cx, Cy)에서 점 D(Dx, Dy)를 향해 걸어가고 있다.
민호와 강호는 동시에 출발하고, 민호가 점 B에 도착하는 순간 강호도 점 D에 도착한다.
또, 두 사람은 항상 일정한 속도로 걸어간다.
두 사람의 거리가 가장 가까울 때, 거리를 구하는 프로그램을 작성하시오.*/
public class Ternary_Search_11662 {
    static double Ax, Ay, Bx, By, Cx, Cy, Dx, Dy;
    static double gradient1, gradient2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Ax = Double.parseDouble(st.nextToken());
        Ay = Double.parseDouble(st.nextToken());
        Bx = Double.parseDouble(st.nextToken());
        By = Double.parseDouble(st.nextToken());
        Cx = Double.parseDouble(st.nextToken());
        Cy = Double.parseDouble(st.nextToken());
        Dx = Double.parseDouble(st.nextToken());
        Dy = Double.parseDouble(st.nextToken());

        // 민호와 강호의 이동경로의 기울기를 각각 구한다.
        gradient1 = (By - Ay) / (Bx - Ax);
        gradient2 = (Dy - Cy) / (Dx - Cx);

        // 직선 상에서 이동할 경우 좌표의 x값과 y값은 동일한 비율로 변화할 것이다.
        // 이동한 x의 거리를 전체 x의 거리로 나눈 값 t를 탐색의 대상으로 한다.
        // 이때 t의 최솟값은 0, 최댓값은 1이다.
        // 이를 통해 특정 시점에서 민호와 강호의 위치를 비교할 수 있다.
        double lo = 0;
        double hi = 1;
        while(lo < hi) {
            // t의 변화에 따른 distance를 나타낸 아래로 볼록한 그래프에서
            // 최솟값을 찾기 위해 x값의 범위(lo ~ hi)를 3등분하는 두 지점
            // t1과 t2의 함수값인 f(t1)과 f(t2)을 비교한다.
            // f(t1)이 f(t2)보다 크거나 같다면
            // lo~t1까지의 범위에서 최솟값은 없으므로 큰 쪽으로 범위를 좁힌다.
            double t1 = (lo * 2 + hi) / 3;
            double t2 = (lo + hi * 2) / 3;
            if(distance(t1) >= distance(t2)) {
                lo = t1 + 0.00000001;
            } else {
                hi = t2;
            }
        }
        System.out.println(distance(lo));
    }

    static double distance(double t) {
        // t에 따른 민호와 강호의 좌표값과 둘 사이의 거리를 구한다.
        double x1 = Ax + (Bx - Ax) * t;
        double x2 = Cx + (Dx - Cx) * t;
        double y1 = gradient1 * x1 + Ay - gradient1 * Ax;
        double y2 = gradient2 * x2 + Cy - gradient2 * Cx;
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
