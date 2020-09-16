import java.util.Comparator;

public class ComparatorKm implements Comparator<Cliente>{
    /**
     * Compara os km feitos entre dois clientes
     * @param c1
     * @param c2
     * @return
     */
    public int compare(Cliente c1,Cliente c2){
        int res;
        if(c1.getKms()>c2.getKms()) res = 1;
        else if(c1.getKms()<c2.getKms()) res = -1;
        else res = 0;
        return res;
    }
}
