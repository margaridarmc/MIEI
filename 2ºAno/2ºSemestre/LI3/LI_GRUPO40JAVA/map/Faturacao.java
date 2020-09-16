import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Faturacao implements IFaturacao {
    /**
     * variavies de instancia
     */
    public Map<Integer,Set<Venda>> listaFat;

    /**
     * Construtores
     */
    public Faturacao(){
        this.listaFat = new HashMap<>();
        geraListas();
    }
    
    private void geraListas(){
        for(int i = 1; i <= 12; i++){
            Set<Venda> lista = new HashSet<>();
            this.listaFat.put(i,lista);
        }
    }
    public Faturacao(Faturacao c){
        this.listaFat = c.getFat();
    }

    public Faturacao clone(){
        return new Faturacao(this);
    }
    
     public Map <Integer,Set<Venda>> getFat(){
        return this.listaFat;
    }


    /**
     * Adiciona e divide vendas pelo respetivo mes
     * @param v
     */
    public void addFaturacao(Set<Venda> v){
        
        for(Venda ve: v){
            
            if(ve.getMes()==1){
                this.listaFat.get(1).add(ve);
            }
            else if(ve.getMes()==2){
                this.listaFat.get(2).add(ve);
            }
            else if(ve.getMes()==3){
                this.listaFat.get(3).add(ve);
            }
            else if(ve.getMes()==4){
                listaFat.get(4).add(ve);
            }
            else if(ve.getMes()==5){
                this.listaFat.get(5).add(ve);
            }
            else if(ve.getMes()==6){
                this.listaFat.get(6).add(ve);
            }
            else if(ve.getMes()==7){
                this.listaFat.get(7).add(ve);
            }
            else if(ve.getMes()==8){
                this.listaFat.get(8).add(ve);
            }
            else if(ve.getMes()==9){
                this.listaFat.get(9).add(ve);
            }
            else if(ve.getMes()==10){
                this.listaFat.get(10).add(ve);
            }
            else if(ve.getMes()==11){
                this.listaFat.get(11).add(ve);
            }
            else if(ve.getMes()==12){
                this.listaFat.get(12).add(ve);
            }
        }
    }
    
}