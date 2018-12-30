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
public class Node {
    
        private String url = "";
        private DownloadFile data = null;
        private String path = null;
        private List<Node> children = new ArrayList<>();
        private Node parent = null;

        public Node(String url , String path) {
            this.url = url;
            this.path = path;
        }
        
        public Node(DownloadFile data){
            this.data = data;
            this.path = data.getPath();
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

        public String getURL() {
            return this.url;
        }


        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getParent() {
            return parent;
        }


        public DownloadFile getDownloadFile(){
            return this.data;
        }
        
}
