package com.company;

import java.util.ArrayList;
import java.util.List;
import com.company.Module;

public class Node {
    public Node parent = null;
    public List<Node> children;
    public int x, y, number;

    public Node(int x, int y, int number) {
        this.children = new ArrayList<>();
        this.y = y;
        this.x = x;
        this.number = number;
    }

    public void buildTree(Module currentPath, Node currentNode, List<Module> numberedModules, Bot jacek){
        Node root = new Node(jacek.getCoordX(), jacek.getCoordY(), 0);
        root.findNext(this.number, this.x, this.y, root);
    }

    private void findNext(int number, int coordX, int coordY, Node currentRoot){
        //warunek stopu dla rekurencji do dodania
        for (int counter = 0; counter < Module.getModuleList().size(); ++counter){
            if (Module.getModuleList().get(counter).getNumber() == number + 1){
                //sprawdzamy czy modul jest przejezdny
                if (Module.getModuleList().get(counter).getType() == 'O'){
                    continue;
                }
                //dodajemy do naszego nodea to dziecko
                Node child = new Node(Module.getModuleList().get(counter).getCoordX(), Module.getModuleList().get(counter).getCoordY(), Module.getModuleList().get(counter).getNumber());
                addChild(child);
                //rekurencyjnie wywolujemy szukanie nastepnych dzieci dziecka
                findNext(child.number, child.x, child.y, child);
            }
        }
    }

    public void addChild(Node childNode) {
        this.children.add(childNode);
        childNode.parent = this;
    }

    public static void main(String[] args) {

    }
}
