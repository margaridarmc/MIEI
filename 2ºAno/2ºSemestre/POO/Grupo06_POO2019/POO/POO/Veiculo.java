import java.util.Random;
import java.awt.Point;
import java.util.*;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * classe veiculo
 */
public class Veiculo implements Serializable{
    /**
     * Atributos
     */
    private String matricula;
    private String marca;
    private double velocidade;
    private double preco;
    private Point.Double localizacao;
    private List<Integer> classificacaoV;
    private Set<Aluguer> aluguer; 

    /**
     * construtor
     */
    public Veiculo(){
        this.matricula = "n/d";
        this.marca = "n/d";
        this.velocidade = 0;
        this.preco = 0;
        Random random = new Random();
        this.localizacao = new Point.Double();
        this.aluguer=new HashSet<>();
        this.classificacaoV = new ArrayList<>();
    }

    /**
     * construtor parametrizado
     * @param marca
     * @param matricula
     * @param velocidade
     * @param preco
     * @param x
     * @param y
     */
    public Veiculo(String marca,String matricula, double velocidade,double preco,double x,double y){
        this.matricula = matricula;
        this.velocidade = velocidade;
        this.preco = preco;
        this.marca=marca;
        //this.fiabilidade = fiabilidade;
        this.localizacao = new Point.Double(x,y);
        this.aluguer=new HashSet<>();
        this.classificacaoV = new ArrayList<>();
    }

    public Veiculo(Veiculo vei){
        this.matricula = vei.getMatricula();
        this.velocidade = vei.getVelocidade();
        this.preco = vei.getPreco();
        this.marca=vei.getMarca();
        this.localizacao = vei.getLocalizacao();
        this.aluguer=vei.getAluguer();
        this.classificacaoV = vei.getClassificacaoV(); 
    }
    
    public Veiculo clone(){
        return new Veiculo(this);
    }


    @Override
    public String toString() {
        return "Veiculo{" + "matricula=" + matricula + ", velocidade=" + velocidade + ", preco=" + preco + ", localizacao=" + localizacao + '}';
    }

    public Veiculo getVeiculo(){
          return (Veiculo)this.clone();
    }
    

   
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Veiculo other = (Veiculo) obj;
        if (Double.doubleToLongBits(this.velocidade) != Double.doubleToLongBits(other.getVelocidade())) {
            return false;
        }
        if (Double.doubleToLongBits(this.preco) != Double.doubleToLongBits(other.getPreco())) {
            return false;
        }
        
        if (!Objects.equals(this.matricula, other.getMatricula())) {
            return false;
        }
        return Objects.equals(this.localizacao, other.getLocalizacao());
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
   public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }


    public void setLocalizacao(Point.Double localizacao) {
        this.localizacao = localizacao;
    }

    public String getMarca(){
        return this.marca;
    }
    
    public String getMatricula() {
        return this.matricula;
    }

    public double getVelocidade() {
        return this.velocidade;
    }

    public double getPreco() {
        return this.preco;
    }


    public Point.Double getLocalizacao() {
        return this.localizacao;
    }
    
    
    public Set<Aluguer> getAluguer(){
        
        Set<Aluguer> res = new HashSet<>();
        res.addAll(this.aluguer);
        return res;
    }
    
    public void setAluguer(Set<Aluguer> aluguer){
        this.aluguer= aluguer;
    }
    
   public List<Integer> getClassificacaoV(){
        return this.classificacaoV.stream()
                                 .collect(Collectors.toList());
    }

    /**
     * adiciona uma classificação
     * @param cla
     */
    public void adicionaClassificacao(int cla){
        this.classificacaoV.add(cla);
    }
    
    public void adicionaAluguer(Aluguer a){
        this.aluguer.add(a);
    }

    /**
     * faz a media das classificações
     * @return
     */
    public double mediaClassificacao (){
        double soma,res;
        soma = this.classificacaoV.stream().mapToDouble(l -> Double.valueOf(l)).sum();
        res = soma/(this.classificacaoV.size());
        return res;
    }
}
