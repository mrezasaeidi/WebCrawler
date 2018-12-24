/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author G50
 */
public class Node<T> {
    
        private T data = null;
        private String path = null;
        private List<Node> children = new ArrayList<>();
        private Node parent = null;

        public Node(T data , String Path) {
            this.data = data;
            this.path = Path;
        }

        public void addChild(Node child) {
            child.setParent(this);
            this.children.add(child);
        }


        public List<Node> getChildren() {
            return children;
        }
        
        public String getPath(){
            return this.path;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public Node<T> getParent() {
            return parent;
        }


}
