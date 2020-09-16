
/**
 * Excessao causada por procura de um elemento nao existente
 *
 */
public class ElemJaExisteException extends Exception
{
     public ElemJaExisteException(String passWord_Errada) {
        super(passWord_Errada);
    }
}
