import java.io.Serializable;
import java.util.Objects;

/**
 * classe Utilizador
 */
public class Utilizador implements Serializable
{
    /**
     *     variáveis de instância
     */
    private String email;
    private String nif;
    private String nome;
    private String password;
    private String morada;
    
    /**
     * COnstrutor para objetos da classe Utilizador
     */
    public Utilizador()
    {
        this.email = "N/D";
        this.nif = "N/D";
        this.nome = "N/D";
        this.password = "N/D";
        this.morada = "N/D";
        
    }

    /**
     * construtor parametrizado
     * @param nome
     * @param nif
     * @param email
     * @param morada
     * @param pass
     */
    public Utilizador(String nome,String nif, String email, String morada,String pass){
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.password = pass;
        this.morada = morada;
    
    }
    
    public Utilizador(Utilizador u){
        this.nome = u.getNome();
        this.nif = u.getNif();
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.morada = u.getMorada();
    }
        
    public Utilizador clone() {
        return new Utilizador(this);
    }
   
        
    
    @Override
    public String toString() {
        return "Utilizador{" + "email=" + email + ", nome=" + nome + ", morada=" + morada + '}';
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
        
        final Utilizador other = (Utilizador) obj;
        if (!Objects.equals(this.email, other.getEmail())) {
            return false;
        }
        if (!Objects.equals(this.nome, other.getNome())) {
            return false;
        }
        if (!Objects.equals(this.password, other.getPassword())) {
            return false;
        } else {
        }
        return Objects.equals(this.morada, other.getMorada());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.morada);
        return hash;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }
    
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNif(){
        return nif;
    }
    
    public void setNif(String nif){
        this.nif = nif;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }


    
}
    

