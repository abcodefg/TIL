package algorithm;

/**
 * 1, 2, 3 으로만 이루어진 수열 바코드를 만든다.
 * 단, 바코드에서 인접한 두 개의 부분 수열은 동일해선 안 된다.
 * 주어진 길이(len)의 바코드 중 가장 작은 수를 구하라.
 * 예) 길이가 2인 경우, 11(x), 12(o)
 *    길이가 6인 경우, 123123(x), 121312(o)
 */
public class DFS_barcode {
    public static String barcode(int len) {
        int[] arr = new int[len];
        return dfs("", len);
    }

    static boolean isValid(String str) {
        //새로 추가된 숫자로 인해 연속된 부분수열이 생겼는지 확인해야 하므로
        //인덱스 설정을 편하게 하기 위해 입력받은 바코드를 뒤집어 준다.
        StringBuilder sb = new StringBuilder(str);
        String reverse = sb.reverse().toString();

        for(int i = 1; i <= reverse.length() / 2; i++) {
            if (reverse.substring(0, i).equals(reverse.substring(i, i * 2))) return false;
        }
        return true;
    }

    static String dfs(String str, int len) {
        if(str.length() == len) return str;

        for(int i = 1; i <= 3; i++) {
            String currentStr = str + i;
            if(isValid(currentStr)) {
                String founded = dfs(currentStr, len);
                if(founded != null) return founded;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(barcode(6));
    }
}
