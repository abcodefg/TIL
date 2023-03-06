package programmers;

import java.util.*;

public class UnionFind_표_병합 {
    public static void main(String[] args) {
        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
        System.out.println(Arrays.toString(solution(commands)));
    }
    public static int[] root = new int[2501];
    public static String[] value = new String[2501];


    public static int find(int x) {
        if (root[x] == x) {
            return x;
        } else {
            return root[x] = find(root[x]);
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y)
            return;

        root[y] = x;
    }

    public static int coordinateToNum(int x, int y) {
        return 50 * (x - 1) + y;
    }
    public static String[] solution(String[] commands) {
        for (int i = 1; i <= 2500; i++) {
            root[i] = i;
            value[i] = "";
        }

        List<String> answer = new ArrayList<>();

        for (String command : commands) {
            String[] array = command.split(" ");
            String head = array[0];

            if (head.equals("UPDATE")) {
                if (array.length == 4) {
                    int x = Integer.parseInt(array[1]);
                    int y = Integer.parseInt(array[2]);

                    int num = coordinateToNum(x, y);
                    value[find(num)] = array[3];
                } else {
                    for (int i = 0; i < value.length; i++) {
                        if (value[i] != null && value[i].equals(array[1])) {
                            value[i] = array[2];
                        }
                    }
                }
            } else if (head.equals("MERGE")) {
                    int x1 = Integer.parseInt(array[1]);
                    int y1 = Integer.parseInt(array[2]);
                    int x2 = Integer.parseInt(array[3]);
                    int y2 = Integer.parseInt(array[4]);
                    int n1 = coordinateToNum(x1, y1);
                    int n2 = coordinateToNum(x2, y2);
                    int root1 = find(n1);
                    int root2 = find(n2);

                    String rootString = value[root1].isBlank() ? value[root2] : value[root1];

                    union(coordinateToNum(x1, y1), coordinateToNum(x2, y2));
                    value[root1] = rootString;
                    value[root2] = "";
            } else if (head.equals("UNMERGE")) {
                int x = Integer.parseInt(array[1]);
                int y = Integer.parseInt(array[2]);
                int num = coordinateToNum(x, y);

                int numRoot = find(num);

                if (num != numRoot) {
                    value[num] = value[numRoot];
                    value[numRoot] = null;
                }

                for (int i = 0; i < root.length; i++) {
                    int roots = find(i);
                    if (roots == numRoot)
                        root[i] = i;
                }
            } else {
                int x = Integer.parseInt(array[1]);
                int y = Integer.parseInt(array[2]);
                int num = coordinateToNum(x, y);
                int rootNum = find(num);
                if (value[rootNum].isBlank()) {
                    answer.add("EMPTY");
                } else {
                    answer.add(value[rootNum]);
                }
            }
        }
        return answer.toArray(new String[0]);
    }
}
