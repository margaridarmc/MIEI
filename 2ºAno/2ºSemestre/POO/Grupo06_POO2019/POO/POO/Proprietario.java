import java.util.*;
import java.util.stream.Collectors;
public class Proprietario extends Utilizador
{
    
    private List<Veiculo> carros;
    private Set<Aluguer> aluguerP; 
    private Set<Aluguer> aluguerF; 
    private List<Integer> classificacaoP; 
    
    /**
     * Construtores para objetos da classe Proprietario, clone() , equals, toString() e hashCode
     */
    
    public Proprietario(){
         super();
         this.carros=new ArrayList<>();
         this.aluguerP = new HashSet<>();
         this.aluguerF = new HashSet<>();
         this.classificacaoP = new ArrayList<>();
    }
    
    public Proprietario(String nome,String nif, String email, String morada,String pass){
        super(nome,nif,email,morada,pass);
        this.carros=new ArrayList<>();
        this.aluguerP = new HashSet<>();
        this.aluguerF = new HashSet<>();
        this.classificacaoP = new ArrayList<>();
    }
    
    public Proprietario(Proprietario a){
        super(a);
        this.carros=a.getCarros();
        this.aluguerP = a.getAluguerP();
        this.aluguerF = a.getAluguerF();
        this.classificacaoP = a.getClassificacaoP();
    }
    
    @Override
    public Proprietario clone(){
        return new Proprietario(this);
    }

    @Override
    public String toString() {
        return "Proprietario{" + "carros=" + carros +  '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proprietario other = (Proprietario) obj;
        
        return Objects.equals(this.carros, other.getCarros());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.carros);
        hash = 23 * hash + Objects.hashCode(this.aluguerP);
        hash = 23 * hash + Objects.hashCode(this.aluguerF);
        hash = 23 * hash + Objects.hashCode(this.classificacaoP);
        return hash;
    }

    public Proprietario(String nome,String nif, String email, String morada,String pass,List<Veiculo> carros) {
        super(nome,nif,email,morada,pass);
        this.carros=new ArrayList<>();
        this.aluguerP = new HashSet<>();
        this.aluguerF = new HashSet<>();
        this.classificacaoP = new ArrayList<>();
    }

    /**
     * get da Lista de carros de um proprietario
     * @return
     */
    public List<Veiculo> getCarros() {
         List<Veiculo> c = new ArrayList<>();
        for(Veiculo v : carros){
            c.add(v);
        }
        return c;
    }


    /**
     * Set da lista de carros de um proprietario
     * @param carros
     */
    public void setCarros(List<Veiculo> carros) {
        this.carros = new ArrayList<>();
        this.carros.addAll(carros);
    }

    /**
     * adiciona um carro `a lista de carros de um proprietario
     * @param v
     */
    public void addCarro(Veiculo v){
        carros.add(v);
    }


    /**
     * get de aluguer
     * @return
     */
    public Set<Aluguer> getAluguerP(){
        Set<Aluguer> res = new HashSet<>();
        for(Aluguer s : this.aluguerP){
            res.add(s);
        }
        return res;
    }

    /**
     * set de aluguer
     * @param aluguerP
     */
    public void setAluguerP(Set<Aluguer> aluguerP){
        this.aluguerP = aluguerP;
    }

    
    public Set<Aluguer> getAluguerF(){
        Set<Aluguer> res = new HashSet<>();
        for(Aluguer s : this.aluguerF){
            res.add(s);
        }
        return res;
    }
    
    public void setAluguerF(Set<Aluguer> aluguerF){
        this.aluguerF = aluguerF;
    }

    /**
     * Lista de classificações
     * @return
     */
    public List<Integer> getClassificacaoP(){
        return this.classificacaoP.stream()
                                 .collect(Collectors.toList());
    }

    /**
     * adiciona uma classificação à lista de classificações
     * @param cla
     */
    public void adicionaClassificacao(int cla){
        this.classificacaoP.add(cla);
    }
    
     public void adicionaAluguerP(Aluguer a){
        this.aluguerP.add(a);
    }
    
    public void adicionaAluguerF(Aluguer a){
        this.aluguerF.add(a);
    }
    
    public void removeAluguerP(Aluguer a){
        this.aluguerP.remove(a);
    }
    
    public void adiconaALCarro(Aluguer a,String mat){
        for(Veiculo c: carros){
            if(c.getMatricula().equals(mat)){
                c.adicionaAluguer(a);
            }
        }
    }
    
    public void adiconaClaCarro(int cla,String mat){
        for(Veiculo c: carros){
            if(c.getMatricula().equals(mat)){
                c.adicionaClassificacao(cla);
            }
        }
    }
    
    /**
     * Adiciona um carro
     * @param v
     */
    public void adicionaCarros(Veiculo v){
       this.carros.add(v);
    }

    /**
     * calcula a média das classificações
     * @return
     */
    public double mediaClassificacao (){
        double soma,res;
        soma = this.classificacaoP.stream().mapToDouble(l -> Double.valueOf(l)).sum();
        res = soma/(this.classificacaoP.size());
        return res;
    }
}
