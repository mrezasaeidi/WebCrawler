package webcrawler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pgr0101
 */
public class Tree {

    private Node<String> root;
    private List<Node> allnodes;

    public Tree(String root, String path) {
        this.root = new Node<String>(root, path);
        this.allnodes = null;

    }

    public String getData() {
        return this.root.getData();
    }

    public String getPath() {
        return this.root.getPath();
    }

    private void visitAll(Node node) {
        try {
            this.allnodes.addAll(0, root.getChildren());
            this.allnodes.forEach((el) -> {
                visitAll(el);
            });
        } catch (Exception e) {
            return;
        }

    }
    /**
     * returning all nodes as a list
     * 
     */
    public List<Node> getAllNodes() {
        allnodes.add(root);
        visitAll(root);
        return allnodes;
    }
}
