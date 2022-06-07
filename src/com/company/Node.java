package com.company;

import java.util.ArrayList;
import java.util.List;
import com.company.Module;

public class Node {
    public Node parent = null;
    public List<Node> children;
    public int x, y, number;
    public double value, sum;

    public Node(int x, int y, int number) {
        this.children = new ArrayList<>();
        this.y = y;
        this.x = x;
        this.number = number;
    }

    public void buildTree(Module currentPath, Node currentNode, List<Module> numberedModules, Bot jacek){
        Node root = new Node(jacek.getCoordX(), jacek.getCoordY(), 0);
        root.findNext(this.number, this.x, this.y, root, null);
    }

    private void findNext(int number, int coordX, int coordY, Node currentRoot, Node currentParentRoot){
        //warunek stopu dla rekurencji do dodania
        Node child = null;
        for (int counter = 0; counter < Module.getModuleList().size(); ++counter){
            if (Module.getModuleList().get(counter).getNumber() == number + 1){
                //sprawdzamy czy modul jest przejezdny
                if (Module.getModuleList().get(counter).getType() == 'O'){
                    continue;
                }
                //musimy dodac, aby przypisaywac nastepne dzieci do dobrych rodzicow, czyli szukamy, jakie gridy z numerem number + 1, znajduja się w odleglosci 1 od obecnego gridu
                if (currentParentRoot != null){ //tzn, ze nie jest to pierwsze przejscie i jest wiecej niz jeden lisc
                    //dodajemy liscie do naszego węzła
                    for (int innerCounter = 0; innerCounter < currentParentRoot.children.size(); ++innerCounter){
                        for (int innerInner = 0; innerInner < Module.getModuleList().size(); ++innerInner){
                            if (Module.getModuleList().get(innerInner).getCoordX() == currentParentRoot.children.get(innerCounter).x + 1 || Module.getModuleList().get(innerInner).getCoordX() == currentParentRoot.children.get(innerCounter).x - 1 || Module.getModuleList().get(innerInner).getCoordY() == currentParentRoot.children.get(innerCounter).y + 1 || Module.getModuleList().get(innerInner).getCoordY() == currentParentRoot.children.get(innerCounter).y - 1 && Module.getModuleList().get(innerInner).getType() != 'O'){
                                child = new Node(Module.getModuleList().get(innerInner).getCoordX(), Module.getModuleList().get(innerInner).getCoordY(), Module.getModuleList().get(innerInner).getNumber());
                                addChild(child);
                            }
                        }
                    }
                    findNext(child.number, parent.x, parent.y, child, currentRoot);
                }
                //dodajemy do naszego nodea to dziecko
                child = new Node(Module.getModuleList().get(counter).getCoordX(), Module.getModuleList().get(counter).getCoordY(), Module.getModuleList().get(counter).getNumber());
                addChild(child);

                //rekurencyjnie wywolujemy szukanie nastepnych dzieci dziecka
                //findNext(child.number, child.x, child.y, child);
            }
        }
        findNext(child.number, parent.x, parent.y, currentRoot.children.get(0), currentRoot);
    }

    public void addChild(Node childNode) {
        this.children.add(childNode);
        setNodeValue(childNode);
        childNode.parent = this;
        childNode.sum = childNode.parent.value + childNode.value;
    }

    public void setNodeValue(Node node){
        value = gridValue(node);
    }

    private double gridValue(Node node){
        for (int counter = 0; counter < Module.getModuleList().size(); ++counter){
            if (node.x == Module.getModuleList().get(counter).getCoordX() && node.y == Module.getModuleList().get(counter).getCoordY()){
                switch (Module.getModuleList().get(counter).getType()){
                    case 'H':
                        return 0.5;
                    case 'B':
                        return 1;
                    case 'S':
                        return 2;
                }
            }
        }
        throw new RuntimeException("incorrectly initialized!?");
    }

    public double findProductModuleWithTheShortestTime(Node root){
        for ()
    }
}
