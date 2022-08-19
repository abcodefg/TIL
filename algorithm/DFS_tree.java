package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

// treeDFS
/*한 tree를 구성하는 노드를 입력받아 DFS(Depth First Search)를 하고,
* 탐색된 순서대로 노드의 값을 저장한 배열을 반환하는 문제*/
public class DFS_tree {
    public static void main(String[] args) {
        // tree의 예시
            // DFS 알고리즘을 검증한다.
        DFS_tree.tree root = new DFS_tree.tree("1");
        DFS_tree.tree rootChild1 = root.addChildNode(new DFS_tree.tree("2"));
        DFS_tree.tree rootChild2 = root.addChildNode(new DFS_tree.tree("3"));
        DFS_tree.tree leaf1 = rootChild1.addChildNode(new DFS_tree.tree("4"));
        DFS_tree.tree leaf2 = rootChild1.addChildNode(new DFS_tree.tree("5"));
        leaf1.addChildNode(new DFS_tree.tree("6"));
        rootChild2.addChildNode(new DFS_tree.tree("7"));
        ArrayList<String> output = dfs(root);
        System.out.println(output);
    }
    public static ArrayList<String> dfs(tree node) {
        // 더 이상 탐색할 자식노드가 없을 때, 이를 담은 배열을 반환한다.
        if(node.getChildrenNode() == null) {
            return new ArrayList<>(Arrays.asList(node.getValue()));
        }

        // 탐색할 자식노드가 있을 때, 입력받은 노드를 배열에 저장하고,
        ArrayList<String> list = new ArrayList<>();
        list.add(node.getValue());

        // 그 자식노드를 순서대로 탐색한다.
        for(tree child : node.getChildrenNode()) {
            // 배열의 뒷부분에 자식노드로부터 시작된 DFS로 얻은 배열을 추가한다.
            list.addAll(dfs(child));
        }

        return list;
    }

    public static class tree {
        private String value;
        private ArrayList<tree> children;

        public tree(String data) {
            this.value = data;
            this.children = null;
        }

        public tree addChildNode(tree node) {
            if(children == null) children = new ArrayList<>();
            children.add(node);
            return children.get(children.size() - 1);
        }

        public String getValue() {
            return value;
        }

        public ArrayList<tree> getChildrenNode() {
            return children;
        }
    }
}
