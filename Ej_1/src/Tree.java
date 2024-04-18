import java.util.ArrayList;
import java.util.List;

public class Tree {

    //Atributo
    private Node root;

    //Constructor
    public Tree() {
        this.root = null;
    }

    //Metodos
    public Integer getRoot() {
        if (root != null) {
            return root.getValue(); // Regresa el valor solo si root existe
        } else {
            return null; // Indica árbol vacío
        }
    }

    //-------------Has Element----------------------
    public boolean hasElem(Integer value) {
        // Verifica si el valor o el árbol están vacíos
        if (value == null || root == null) {
            return false;
        }

        // Búsqueda recursiva basada en comparación de valores
        return hasElemRecursive(root, value);
    }

    private boolean hasElemRecursive(Node node, Integer value) {
        if (node == null) {
            return false; // Elemento no encontrado si se llega a un nodo nulo
        } else if (value.equals(node.getValue())) {
            return true; // Elemento encontrado si coincide con el valor del nodo
        } else if (value < node.getValue()) {
            return hasElemRecursive(node.getLeft(), value); // Busca en el subárbol izquierdo
        } else {
            return hasElemRecursive(node.getRight(), value); // Busca en el subárbol derecho
        }
    }

    //----------------Add--------------------
    public void add(Integer value) {
        if (this.root == null)
            this.root = new Node(value);
        else
            this.addRecursive(this.root, value);
    }

    private void addRecursive(Node actual, Integer value) {
        if (actual.getValue() > value) {
            if (actual.getLeft() == null) {
                Node temp = new Node(value);
                actual.setLeft(temp);
            } else {
                addRecursive(actual.getLeft(), value);
            }
        } else if (actual.getValue() < value) {
            if (actual.getRight() == null) {
                Node temp = new Node(value);
                actual.setRight(temp);
            } else {
                addRecursive(actual.getRight(), value);
            }
        }
    }

    //--------------Delete-------------------

    public boolean delete(Integer value) {
        if (value == null || root == null) {
            return false; // No se puede eliminar si el valor es nulo o si el árbol está vacío
        } else {
            return deleteRecursive(value, root, null);
        }
    }

    private boolean deleteRecursive(Integer value, Node current, Node parent) {
        if (current == null) {
            return false; // El valor no se encontró en el árbol
        }

        // Buscar el nodo que contiene el valor a eliminar
        if (value.equals(current.getValue())) {
            // Caso 1: El nodo a eliminar es una hoja (no tiene hijos)
            if (current.getLeft() == null && current.getRight() == null) {
                if (current == root) {
                    root = null; // El árbol tenía un solo nodo
                } else if (parent.getLeft() == current) {
                    parent.setLeft(null); // Desconectar nodo izquierdo
                } else {
                    parent.setRight(null); // Desconectar nodo derecho
                }
            }
            // Caso 2: El nodo a eliminar tiene un solo hijo
            else if (current.getLeft() == null) {
                promoteChild(current, parent, current.getRight()); // Promover el hijo derecho
            } else if (current.getRight() == null) {
                promoteChild(current, parent, current.getLeft()); // Promover el hijo izquierdo
            }
            // Caso 3: El nodo a eliminar tiene dos hijos
            else {
                Node successor = getSuccessor(current); // Encontrar el sucesor del nodo a eliminar
                promoteChild(current, parent, successor); // Promover el sucesor
                successor.setLeft(current.getLeft()); // Conectar el hijo izquierdo del nodo a eliminar al sucesor
            }
            return true; // Nodo eliminado exitosamente
        }

        // Continuar la búsqueda en el subárbol izquierdo o derecho
        if (value < current.getValue()) {
            return deleteRecursive(value, current.getLeft(), current);
        } else {
            return deleteRecursive(value, current.getRight(), current);
        }
    }

    private void promoteChild(Node current, Node parent, Node child) {
        if (current == root) {
            root = child; // El hijo promovido se convierte en la nueva raíz
        } else if (parent.getLeft() == current) {
            parent.setLeft(child); // Conectar el hijo promovido al padre del nodo a eliminar
        } else {
            parent.setRight(child); // Conectar el hijo promovido al padre del nodo a eliminar
        }
    }

    // Método auxiliar para encontrar el sucesor de un nodo (el nodo más pequeño en el subárbol derecho)
    private Node getSuccessor(Node node) {
        Node successorParent = node;
        Node successor = node;
        Node current = node.getRight(); // Empezar desde el subárbol derecho
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeft(); // Ir al hijo más izquierdo
        }
        if (successor != node.getRight()) {
            successorParent.setLeft(successor.getRight()); // Desconectar el sucesor de su padre
            successor.setRight(node.getRight()); // Conectar el subárbol derecho del nodo eliminado al sucesor
        }
        return successor;
    }

    //-------------Is Empty---------------------
    public boolean isEmpty() {
        return this.root == null;
    }

    //---------------InOrder------------
    public void printInOrder() {
        if (!isEmpty())
            this.printInOrder(this.root);
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.getLeft());
            System.out.println(node.getValue());
            printInOrder(node.getRight());
        }
    }


    //--------PreOrder-------------
    public void printPreOrder() {
        if (!isEmpty()) {
            this.printPreOrder(this.root);
        }
    }

    private void printPreOrder(Node node) {
        if (node != null) {
            System.out.println(node.getValue());
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }


    //--------PostOrder----------------
    public void printPostOrder() {
        if (!isEmpty()) {
            this.printPostOrder(this.root);
        }
    }

    private void printPostOrder(Node node) {
        if (node != null) {
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            System.out.println(node.getValue());
        }
    }

    //--------Get Height-----------
    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(Node node) {
        if (node == null) {
            return 0; // Altura de un árbol vacío es 0
        } else {
            // Calcular la altura de los subárboles izquierdo y derecho recursivamente
            int leftHeight = getHeightRecursive(node.getLeft());
            int rightHeight = getHeightRecursive(node.getRight());

            // La altura del árbol es la máxima altura de los subárboles izquierdo y derecho, más 1 por el nodo actual
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    //--------------getLongestBranch
    public List<Integer> getLongestBranch() {
        List<Integer> longestBranch = new ArrayList<>();
        List<Integer> currentBranch = new ArrayList<>();

        findLongestBranch(root, currentBranch, longestBranch);

        return longestBranch;
    }

    private void findLongestBranch(Node node, List<Integer> currentBranch, List<Integer> longestBranch) {
        if (node == null) {
            // Si llegamos a un nodo nulo, la rama actual termina
            if (currentBranch.size() > longestBranch.size()) {
                // Si la rama actual es más larga que la más larga encontrada hasta ahora, actualizamos la más larga
                longestBranch.clear();
                longestBranch.addAll(currentBranch);
            }
            return;
        }

        // Agregamos el valor del nodo actual a la rama actual
        currentBranch.add(node.getValue());

        // Exploramos recursivamente los subárboles izquierdo y derecho
        findLongestBranch(node.getLeft(), currentBranch, longestBranch);
        findLongestBranch(node.getRight(), currentBranch, longestBranch);

        // Al retroceder, eliminamos el valor del nodo actual de la rama actual
        currentBranch.remove(currentBranch.size() - 1);
    }


    //---------------GetFrontera--------------
    public List<Integer> getFrontera() {
        List<Integer> frontera = new ArrayList<>();
        findFrontera(root, frontera);
        return frontera;
    }

    private void findFrontera(Node node, List<Integer> frontera) {
        if (node == null) {
            return; // Si llegamos a un nodo nulo, simplemente retornamos
        }

        // Si es un nodo hoja (no tiene hijos), lo agregamos a la frontera
        if (node.getLeft() == null && node.getRight() == null) {
            frontera.add(node.getValue());
        }

        // Exploramos recursivamente los subárboles izquierdo y derecho
        findFrontera(node.getLeft(), frontera);
        findFrontera(node.getRight(), frontera);
    }


    //------------------GetMaxElem--------------
    public Integer getMaxElem() {
        if (root == null) {
            return null; // Si el árbol está vacío, retornamos null
        }

        Node current = root;
        while (current.getRight() != null) {
            current = current.getRight(); // Avanzamos hacia la derecha hasta encontrar el nodo más a la derecha
        }

        return current.getValue(); // Devolvemos el valor del nodo más a la derecha, que es el valor máximo
    }

    //-------GetElemAtLevel-------------
    public List<Integer> getElemAtLevel(int level) {
        List<Integer> elements = new ArrayList<>();
        getElemAtLevelRecursive(root, level, 1, elements);
        return elements;
    }

    private void getElemAtLevelRecursive(Node node, int targetLevel, int currentLevel, List<Integer> elements) {
        if (node == null) {
            return; // Si llegamos a un nodo nulo, no hay elementos en ese nivel
        }

        if (currentLevel == targetLevel) {
            elements.add(node.getValue()); // Si estamos en el nivel deseado, agregamos el valor del nodo a la lista
        } else {
            // Exploramos recursivamente los subárboles izquierdo y derecho para el siguiente nivel
            getElemAtLevelRecursive(node.getLeft(), targetLevel, currentLevel + 1, elements);
            getElemAtLevelRecursive(node.getRight(), targetLevel, currentLevel + 1, elements);
        }
    }

    //---------------Cantidad de nodos internos--------
    public int internalNodes() {
        int count = 0;
        if (root == null) {
            return 0; // Empty tree has 0 nodes
        } else {
            //Recorro pre orden, si es hoja no lo agrego.

        }
        return count;
    }

    private int countFronteraNodesRecursive(Node node) {
        if (node == null) {
            return 0; // Reached an empty branch (not a leaf node)
        } else if (node.getLeft() == null && node.getRight() == null) {
            return 1; // Current node is a leaf node, count it
        } else {
            int leftCount = countFronteraNodesRecursive(node.getLeft()); // Count leaf nodes in the left subtree
            int rightCount = countFronteraNodesRecursive(node.getRight()); // Count leaf nodes in the right subtree
            return leftCount + rightCount; // Sum leaf node counts from subtrees
        }
    }

    //Encontrar mayores que k

    public List<Integer> elementosMayoresQueK(int k) {
        return elementosMayoresQueKRecursivo(root, k);
    }

    private List<Integer> elementosMayoresQueKRecursivo(Node node, int k) {

        //Si la raíz es nula, devuelvo un arrayList vacio
        if (node == null) {
            return new ArrayList<>();
        }

        //Armo dos arraylist, en uno voy guardando los mayores por derecha y en el otro los mayores por izquierda
        List<Integer> elementosMayoresIzquierda = elementosMayoresQueKRecursivo(node.getLeft(), k);
        List<Integer> elementosMayoresDerecha = elementosMayoresQueKRecursivo(node.getRight(), k);

        //En este arreglo guardo los mayores posta
        List<Integer> elementosMayores = new ArrayList<>();
        if (node.getValue() > k) {
            elementosMayores.add(node.getValue());
        }

        //Agrego todos los elementos mayores por derecha y por izquierda
        elementosMayores.addAll(elementosMayoresIzquierda);
        elementosMayores.addAll(elementosMayoresDerecha);

        return elementosMayores;
    }


    public int countInternalNodes() {
        return countInternalNodesRecursive(root);
    }

    private int countInternalNodesRecursive(Node node) {
        if (node == null) {
            return 0;
        }

        // Si el nodo actual tiene hijos en ambos lados, es un nodo interno
        if (node.getLeft() != null || node.getRight() != null) {
            // Contar este nodo actual como un nodo interno
            // y continuar el recorrido en ambos subárboles
            return 1 + countInternalNodesRecursive(node.getLeft()) + countInternalNodesRecursive(node.getRight());
        }

        return 0;
    }
}


