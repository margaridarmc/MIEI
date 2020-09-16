import java.util.Comparator;

public class ComparatorAluguer implements Comparator<Cliente>
{
    /**
     *
     *   Compara Alugueres de Clientes

     * @param c1
     * @param c2
     * @return
     */
    public int compare(Cliente c1,Cliente c2){
        int res;
        if(c1.getAluguer().size()>c2.getAluguer().size()) res = 1;
        else if(c1.getAluguer().size()<c2.getAluguer().size()) res = -1;
        else res = 0;
        return res;
    }
}
