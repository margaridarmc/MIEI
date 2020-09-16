import java.awt.Point;
import java.util.Objects;
import java.util.*;
import java.util.stream.Collectors;

/**
 * subClasse de User- > Cliente
 */
public class Cliente extends Utilizador
{
    /**
     * variáveis de instância
     */

    private Point.Double localizacao;
    private Set<Aluguer> aluguer; 
    private List<Integer> classificacaoC;
    private double kms;
    
    /**
     * COnstrutor para objetos da classe Cliente
     */
    public Cliente(){
        super();
        this.localizacao= new Point.Double();
        this.aluguer=new HashSet<>();
        this.classificacaoC = new ArrayList<>();
    }

    /**
     * Construtor parametrizado
     * @param nome
     * @param nif
     * @param email
     * @param morada
     * @param x
     * @param y
     * @param pass
     */
    public Cliente(String nome,String nif, String email, String morada,double x, double y,String pass){
       super(nome,nif,email,morada,pass);
       this.localizacao = new Point.Double(x,y);
       this.aluguer=new HashSet<>();
       this.classificacaoC = new ArrayList<>();
    }

    public Cliente(Cliente a){
        super(a);
       // this.hist = a.getHist();
        this.localizacao = a.getLocalizacao();
        this.aluguer=a.getAluguer();
        this.classificacaoC = new ArrayList<>();
    }

    /**
     * Clone(), ToString(), HashCode(), gets e sets
     *
     */

    public Cliente clone(){
        return new Cliente(this);
    }
    
    
    @Override
    public String toString() {
        return "Cliente{" + "localiza\u00e7ao=" + localizacao + '}';
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.localizacao, other.getLocalizacao())) {
            return false;
        }
        return Objects.equals(this.aluguer, other.getAluguer());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.localizacao);
        hash = 37 * hash + Objects.hashCode(this.aluguer);
        return hash;
    }
    
  
    public Point.Double getLocalizacao() {
        return localizacao;
     }

    public void setLocalizacao(Point.Double localizacao) {
        this.localizacao = localizacao;
    }
 
    public double getKms(){
        return this.kms;
        }
  
    public Set<Aluguer> getAluguer(){
        Set<Aluguer> res = new HashSet<>();
        res.addAll(this.aluguer);
        return res;
    }
    
    public void setAluguer(Set<Aluguer> aluguer){
        this.aluguer= aluguer;
    }

    public List<Integer> getClassificacaoC(){
        return this.classificacaoC.stream()
                                 .collect(Collectors.toList());
    }


    /**
     * adiciona uma classificação
     * @param cla
     */
    public void adicionaClassificacao(int cla){
        this.classificacaoC.add(cla);
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
        soma = this.classificacaoC.stream().mapToDouble(l -> Double.valueOf(l)).sum();
        res = soma/(this.classificacaoC.size());
        return res;
    }
}
