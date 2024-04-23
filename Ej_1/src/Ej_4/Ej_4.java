package Ej_4;

public class Ej_4 {
    //Atributo
    private Node root;
    //Suponemos la estructura de arbol

    public void cargarValores(){
        if(root !=null){
            cargarRecursiva(root);
        }
    }
    private void cargarRecursiva(Node node){
        if(node.getRight()!=null && node.getLeft()!=null){
            //el nodo se vuelve el valor del nodo derecho menos el valor del nodo izquierdo
            node.setValue(node.getRight().getValue() - node.getLeft().getValue());
        }
        else if(node.getRight()==null || node.getLeft()==null){
            if(node.getRight()==null){
                node.setRight(new Node(0)); // Agregar un hijo derecho con valor 0
            }
            else{
                node.setLeft(new Node(0));  // Agregar un hijo izquierdo con valor 0
            }
            }
        }
    }

