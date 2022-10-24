package algorithm;

/*한 변의 길이가 2의 제곱수인 정사각형의 흑백 이미지가 2차원 배열로 주어진다.
* 각 좌표에는 0 또는 1이 저장되어 있다.
* 이미지에 포함된 데이터가 모두 1 혹은 모두 0이면 "1" 혹은 "0" 한 글자로 압축할 수 있다.
* 그렇지 않은 경우 대문자 X로 표시하고 전체를 4등분하여 재귀적으로 압축한다.
* 4등분한 영역의 순서는 좌측 상단, 우측 상단, 좌측 하단, 우측 하단이다.*/
public class Recursion_decompression {
    /**
     * 재귀 호출할 메서드의 매개변수를 설정하고,
     * 영역을 4등분하는 것을 반복문으로 구현하는 것이 관건인 문제였다.
     *
     * 어떤 영역이 압축가능한지 확인하려면 초기값(시작점)과 반복 횟수(길이)라는 정보가 필요했기에
     * 이를 통해 자연스럽게 매개변수를 떠올릴 수 있었고
     * 매개변수를 기준 삼아 반복문도 구현할 수 있었다.
     */
    public String decompression(int[][] image) {
        return comp(0,0,image.length, image);
    }
    // 재귀적으로 배열을 압축하는 메서드
    // 인자로는 압축하고자 하는 영역의 가장 왼쪽 상단의 좌표, 영역의 한 변의 길이를 입력받는다.
    private String comp(int x, int y, int length, int[][] image) {
        // 길이가 1이면 해당 좌표의 숫자를 반환한다.
        if(length == 1) {
            return String.valueOf(image[y][x]);
        }

        String str = "";
        // 영역이 압축 가능하다면(영역 내 모든 요소의 값이 같다면) 그 값을 결과값에 더한다.
        if(isCompressible(x, y, length, image)) {
            str += image[y][x];
        }
        // 압축이 불가능하다면, 영역을 4등분하여 재귀 호출한다.
        else {
            str += 'X';
            for(int i = 0; i < length; i += length / 2) {
                for(int j = 0; j < length; j += length / 2) {
                    str += comp(x + j, y + i, length / 2, image);
                }
            }
        }
        return str;
    }

    private boolean isCompressible(int x, int y, int length, int[][] image) {
        int val = image[y][x];
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                if(image[y + i][x + j] != val) return false;
            }
        }
        return true;
    }
}
