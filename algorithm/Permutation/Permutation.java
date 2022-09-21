package algorithm.Permutation;

//순열 알고리즘 정리
//n개 중 r개를 뽑는 순열(nPr)
public class Permutation {
    //swap을 이용해 나타낸 알고리즘
    //배열의 값을 직접 바꾼다.
    //순열들의 순서가 보장되지 않는다.
    static void permutation(int[] arr, int depth, int n, int r) {
        if (depth == n) {
            print(arr,r);
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }

    static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    //visited 배열을 이용한 순열
    //DFS를 돌며 모든 인덱스를 방문해 output에 값을 채운다.
    //순서가 보장된다(= 사전식으로 구할 수 있다.)
    static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            print(output, r);
            return;
        }

        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1, n, r);
                output[depth] = 0; //depth가 변하지 않아 덮어씌워질 것이기 때문에 없어도 됨.
                visited[i] = false;
            }
        }
    }

    static void print(int[] arr, int r) {
        for(int i = 0; i < r; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
