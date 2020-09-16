

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Filial implements IFilial{
    /**
     * variavies de instancia
     */
        public Map <Integer,Map<String,Set<Venda>>> listaFilial;

    /**
     * Construtores
     */
    public Filial() {
        this.listaFilial = new HashMap<>(3);
        geraMap();
    }
    public Filial(Filial c){
        this.listaFilial = c.getFilial();
    }

    public Filial clone(){
        return new Filial(this);
    }
    
    public Map <Integer,Map<String,Set<Venda>>> getFilial(){
        return this.listaFilial;
    }
    
    private void geraMap(){
        for(int i = 1; i <= 3; i++){
            Map<String,Set<Venda>> map = new HashMap<>();
            this.listaFilial.put(i,map);
        }
    }

    /**
     * Adiciona e divide vendas pela respetiva filial
     * @param v
     */
    public void fazFilial(Set<Venda> v) {
        for(Venda ven: v){
            
            if(ven.getFilial()==1){
                separaClientes(ven,ven.getFilial());
            }
            if(ven.getFilial()==2){
                separaClientes(ven,ven.getFilial());
            }
            if(ven.getFilial()==3){
               separaClientes(ven,ven.getFilial());
            }
        }
    }
    
    public void separaClientes(Venda v,int i) {
            String cliente=v.getCli();
            Set<Venda> se=new HashSet<>();
            if(!(listaFilial.get(i).containsKey(cliente))){
                se.clear();
                listaFilial.get(i).put(cliente,se); }
            listaFilial.get(i).get(cliente).add(v);
        }
          
 }
    

