import java.lang.String;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;

public class CatVendas implements ICatVendas {
    /**
     * variavies de instancia
     */
    public Set<Venda> catVendas;

    /**
     * Construtores
     */
    public CatVendas() {
        this.catVendas= new HashSet<>();
    }

    public CatVendas(CatVendas c){
        this.catVendas = c.getCatVendas();
    }

    public CatVendas clone(){
        return new CatVendas(this);
    }

    public Set<Venda> getCatVendas() {
        return this.catVendas;
    }

    public void setCatVendas(Set<Venda> catVendas) {
        this.catVendas = catVendas;
    }



    public boolean achaPro(String l, List<Set<String>> catP){
         char letra1 = l.charAt(0);
         int i = letra1-'A';
         boolean t=false;

         for(String str: catP.get(i)){
             if(l.equals(str)) { t=true; break;}
            }


      return t;
    }

    public boolean achaCli(String l, Set<String> catC){

         boolean t=false;
         char letra1 = l.charAt(0);
         int i = letra1-'A';
         for(String str: catC){
                if(l.equals(str)) {t=true; break;}
            }

        return t;
     }

    /**
     * Mete em catVendas apenas os códigos de vendas válidas
     * @param linhas
     */
    public int validaVendas(List<String> linhas, Set<String> catP,Set<String> catC){
            Venda venda=new Venda();
            int count=0;
            
           for(String str: linhas){
               venda.linhaToVenda(str);
               String strP=venda.getProd();
               String strC=venda.getCli();
           if(!(catP.contains(strP))) {}
           else if(!(catC.contains(strC))) {}
           else if(venda.getMes()>12 && venda.getMes()<0 ) {}
           else if( !("N".equals(venda.getPromo())) && !("P".equals(venda.getPromo())) ){}
           else if( venda.getPreco()<0.0 && venda.getPreco()>999.99 ){}
           else if(venda.getQuant()>200 && venda.getQuant()<0 ) {}
           else if(venda.getFilial()>3 && venda.getFilial()<0) {} 
           
           else    {this.getCatVendas().add(venda.clone());count++;}
           }
           return count; 
       }
    

   
}