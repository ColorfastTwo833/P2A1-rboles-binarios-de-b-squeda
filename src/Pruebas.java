/**
 * Pruebas adicionales del ABB: búsqueda con ruta, y los casos de eliminación
 * (hoja, un hijo, dos hijos, raíz y clave inexistente).
 */
public class Pruebas {

    // Imprime los nodos visitados al buscar una clave (misma lógica que buscarRec)
    static void ruta(ArbolBinario arbol, int clave) {
        StringBuilder sb = new StringBuilder();
        Nodo actual = arbol.raiz;
        while (actual != null) {
            sb.append(actual.clave);
            if (clave == actual.clave) break;
            sb.append(" -> ");
            actual = (clave < actual.clave) ? actual.izquierdo : actual.derecho;
        }
        if (actual == null) sb.append("null");
        System.out.println("Ruta para " + clave + ": " + sb);
        System.out.println("buscar(" + clave + ") = " + arbol.buscar(clave));
    }

    static void eliminarYMostrar(ArbolBinario arbol, int clave, String caso) {
        arbol.eliminar(clave);
        System.out.print("Eliminar " + clave + " (" + caso + ") -> inorden: ");
        arbol.inorden();
        System.out.println();
    }

    static ArbolBinario nuevoArbol() {
        ArbolBinario a = new ArbolBinario();
        int[] claves = {50, 30, 20, 40, 70, 60, 80};
        for (int c : claves) a.insertar(c);
        return a;
    }

    public static void main(String[] args) {
        ArbolBinario arbol = nuevoArbol();

        System.out.println("=== PRUEBA 1: BÚSQUEDA ===");
        ruta(arbol, 40);
        ruta(arbol, 90);
        ruta(arbol, 50);

        System.out.println("\n=== PRUEBA 2: ELIMINACIÓN (un caso por árbol nuevo) ===");
        arbol = nuevoArbol();
        eliminarYMostrar(arbol, 20, "hoja");

        arbol = nuevoArbol();
        arbol.eliminar(20);                       // deja a 30 con un solo hijo (40)
        eliminarYMostrar(arbol, 30, "un hijo");

        arbol = nuevoArbol();
        eliminarYMostrar(arbol, 70, "dos hijos");

        arbol = nuevoArbol();
        eliminarYMostrar(arbol, 50, "raíz con dos hijos");
        System.out.println("Nueva raíz: " + arbol.raiz.clave);

        arbol = nuevoArbol();
        eliminarYMostrar(arbol, 99, "clave inexistente, no cambia nada");

        System.out.println("\n=== PRUEBA 3: ELIMINAR TODO EN SECUENCIA ===");
        arbol = nuevoArbol();
        int[] orden = {20, 70, 50, 30, 40, 60, 80};
        for (int c : orden) eliminarYMostrar(arbol, c, "secuencia");
        System.out.println("Árbol vacío: " + (arbol.raiz == null));
    }
}
