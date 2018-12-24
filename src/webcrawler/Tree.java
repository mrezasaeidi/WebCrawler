package webcrawler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pgr0101
 */
public class Tree<T> {

    private Node<T> root;

    public Tree(T root) {
        this.root = new Node<T>(root);
    }

    public void addChild(T child) {
        this.root.addChild(new Node<T>(child));
    }

    private class Node<T> {

        private T data = null;
        private List<Node<T>> children = new ArrayList<>();
        private Node<T> parent = null;

        public Node(T data) {
            this.data = data;
        }

        public void addChild(Node<T> child) {
            child.setParent(this);
            this.children.add(child);
        }

        public void addChildren(List<Node<T>> children) {
            children.forEach(each -> each.setParent(this));
            this.children.addAll(children);
        }

        public List<Node<T>> getChildren() {
            return children;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        private void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public Node<T> getParent() {
            return parent;
        }

    }

}
