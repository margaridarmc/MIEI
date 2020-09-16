import java.lang.String;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.List;

public class CatProdutos implements ICatProdutos{
    /**
     * variavies de instancia
     */
    private Set<String> CatalogoProdutos; // de produtos validos

    /**
     * Construtores
     */
    public CatProdutos() {
        this.CatalogoProdutos= new TreeSet<String>();
    }

    public CatProdutos(CatProdutos c){
        this.CatalogoProdutos = c.getCatP();
    }

    public CatProdutos clone(){
        return new CatProdutos(this);
    }

    public Set<String> getCatP() {
        return this.CatalogoProdutos;
    }
    public void setProd(Set<String> p) {
        this.CatalogoProdutos = p;
    }

    /**
     * Mete em CatalogoProdutos apenas os códigos de produtos válidos
     * @param linhas
     */
    public void validaProduto(List<String> linhas){
        char letra1;
        char letra2;
        String numS;
        int num;
        int indice;
        int i=0;
         for(String str: linhas){
            letra1=str.charAt(0);
            letra2=str.charAt(1);
            numS=str.substring(2,6);
            num=Integer.parseInt(numS);
            indice=letra1-'A';
           if(letra1<='Z' && letra1>='A'){
            if(letra2<='Z' && letra2>='A') {
                if(num>999 && num<10000) {

                    this.CatalogoProdutos.add(str);  //adiciona o produto se este for valido;
                    }
            }
        }
       }
     }


}
