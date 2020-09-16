/**
 * Excessao devida a um elemento não existente procurado
 */

public class ElemNotFoundException extends Exception {

    ElemNotFoundException(String mario) {
        super(mario);
    }
    
}
