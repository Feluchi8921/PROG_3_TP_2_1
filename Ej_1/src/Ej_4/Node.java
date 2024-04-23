package Ej_4;

public class Node {

    //Atributos
    private Integer value;
    private Node left;
    private Node right;

    //Constructor
    public Node(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    //Getter and Setters


    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() { //Solo getValue porque si hago el Set me lo pueden cambiar desde afuera
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}