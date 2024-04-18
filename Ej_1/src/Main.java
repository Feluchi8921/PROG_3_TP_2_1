import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a BST instance
        Tree tree = new Tree();

        // Insert some elements into the tree
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(60);
        tree.add(80);
        tree.add(40);

        //Prueba de mayores
        int k = 65;
        List<Integer> mayores = tree.elementosMayoresQueK(k);
        System.out.print("Elementos mayores a " + k + ": ");
        for (int mayor : mayores) {
            System.out.print(mayor + " ");
        }

        //Imprimir cantidad de nodos internos
        System.out.println("La cantidad de nodos internos es: " + tree.countInternalNodes());

        // hasElem
        System.out.println("El elemento 40 está en el árbol? " + tree.hasElem(40));

        // Imprimir el arbol en orden
        System.out.print("In order:");
        tree.printInOrder();
        // Output: In-order traversal: 20 30 40 50 60 70 80

        // Imprimir arbol en pre Orden
        System.out.print("Pre Order");
        tree.printPreOrder();
        // Output: Pre-order traversal: 50 30 20 40 70 60 80

        // Imprimir arbol en post Orden
        System.out.print("Post Order");
        tree.printPostOrder();
        // Output: Post-order traversal: 20 40 30 60 80 70 50

        // Altura
        int height = tree.getHeight();
        System.out.println("La altura del arbol es: " + height); // Output: Tree height: 4

        // La rama mas larga es:
        List<Integer> longestBranch = tree.getLongestBranch();
        System.out.print("La rama más larga es: ");
        for (Integer element : longestBranch) {
            System.out.print(element + " ");
        }
        // Output: Longest branch: 80 70 50

        // Frontera
        List<Integer> frontera = tree.getFrontera();
        System.out.print("Frontera");
        for (Integer element : frontera) {
            System.out.print(element + " ");
        }
        // Output: Frontera (leaf nodes): 20 40 60 80

        // Elemento màximo
        Integer maxElem = tree.getMaxElem();
        System.out.println("Elemento máximo: " + maxElem); // Output: Maximum element: 80

        // Elementos de un nivel
        List<Integer> elementsAtLevel2 = tree.getElemAtLevel(2);
        System.out.print("Elementos del nivel 2: ");
        for (Integer element : elementsAtLevel2) {
            System.out.print(element + " ");
        }
        // Output: Elements at level 2: 30 70

        // Delete
        boolean deleted = tree.delete(50);
        System.out.println("Borrar el 50? " + deleted); // Output: Deleted element 50? true

        // Print the tree in in-order traversal after deletion
        System.out.print("El arbol luego de borrar ");
        tree.printInOrder();
        // Output: In-order traversal after deletion: 20 30 40 60 70 80



    }
}