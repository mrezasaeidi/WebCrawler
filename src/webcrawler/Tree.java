package webcrawler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pgr0101
 */
public class Tree {

    private Node root;
    private ArrayList<Node> allnodes;

    public Tree(Node root, String path) {
        this.root = root;
        this.allnodes = null;

    }

    public Object getData() {
        return this.root.getData();
    }

    public String getPath() {
        return this.root.getPath();
    }

    public void visitAll(Node node) {
        try {
            this.allnodes.addAll(0, root.getChildren());
            this.allnodes.forEach((el) -> {
                visitAll(el);
                System.out.println(el.getData());
            });
        } catch (Exception e) {
            return;
        }

    }
    /**
     * returning all nodes as a list
     * 
     */
    public ArrayList<Node> getAllNodes() {
        allnodes.add(root);
        System.out.println(root.getData());
        visitAll(root);
        return allnodes;
    }
    
    /**
     * return the root of the tree
     * 
     **/
    public Node getRoot(){
        return this.root;
    }
}
