    
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.List;


public class CatClientes implements ICatClientes {
    /**
     * variavies de instancia
     */
    public Set<String>  CatalogoClientes ; // de Clientes validos;

    /**
     * Construtores
     */
    public CatClientes() {
        this.CatalogoClientes = new TreeSet<String> ();
    }

    public CatClientes(CatClientes c){
        this.CatalogoClientes  = c.getCatalogoC();
    }


    public CatClientes clone(){
        return new CatClientes(this);
    }

    public Set<String>  getCatalogoC() {
        return this.CatalogoClientes;
    }

    public void setCatalogoClientes(Set<String>  CatalogoClientes) {
        this.CatalogoClientes = CatalogoClientes;
    }

    /**
     * Mete em CatalogoClientes apenas os códigos de clientes válidos
     * @param linhas
     */
    public void validaCliente(List<String> linhas){
        char letra1;
        String num;
        int numero;
        int indice;
        int i=0;
        for(String str: linhas){
            letra1 = str.charAt(0);
            num = str.substring(1,5);
            numero = Integer.parseInt(num);
            
            indice = letra1-'A';
        if(letra1<='Z' && letra1>='A'){
                if(numero>999 && numero<=5000) {
                    this.CatalogoClientes.add(str);   //adiciona a hash o cliente se ente for valido;
                }
            }
        }
    }






    
}
