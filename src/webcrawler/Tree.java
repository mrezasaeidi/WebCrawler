package webcrawler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pgr0101
 */
public class Tree {

    private Node<String> root;

    public Tree(String root , String path) {
        this.root = new Node<String>(root , path);
    }

    public String getData(){
        return this.root.getData();
    }
    
    public String getPath(){
        return this.root.getPath();
    }
}
