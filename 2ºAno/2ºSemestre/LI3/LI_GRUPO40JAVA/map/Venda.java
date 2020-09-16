
import static java.lang.System.out;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import java.io.Serializable;
//FAZ UMA VENDA;

public class Venda implements IVenda, Serializable{
    private String codigo; // parto do principio que é o código da vena completa
    private String prod;
    private double preco;
    private int quant;
    private String promo;
    private String cli;
    private int mes;
    private int filial;
    private double valor;//nao sei para que serve

    public Venda(){
        this.codigo="n/a";
        this.prod="n/a";
        this.preco=0;
        this.quant=0;
        this. promo="n/a";
        this.cli="n/a";
        this.mes=0;
        this.filial=0;
        this.valor=0;
    }
    
   public Venda(String codigo, String prod, double preco, int quant, String promo, String cli, int mes, int filial, double valor) {
        this.codigo = codigo;
        this.prod = prod;
        this.preco = preco;
        this.quant = quant;
        this.promo = promo;
        this.cli = cli;
        this.mes = mes;
        this.filial = filial;
        this.valor = valor;
    }
    
    public Venda(Venda v){
        this.codigo = v.codigo;
        this.prod = v.prod;
        this.preco = v.preco;
        this.quant = v.quant;
        this.promo = v.promo;
        this.cli = v.cli;
        this.mes = v.mes;
        this.filial = v.filial;
        this.valor = v.valor;
    }
    
    public Venda clone(){
        return new Venda(this);
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public String getCli() {
        return cli;
    }

    public void setCli(String cli) {
        this.cli = cli;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public void linhaToVenda(String linha){
        int quant;
        String promo;
        double prec=0.0;
        int mes=0;
        int fi=0;
        String codProd, codCli ;
        String[] campos = linha.split(" ");
        codProd = campos[0];
        try{
            prec=Double.parseDouble(campos[1]);
            
            this.setCodigo(linha);
            this.setProd(campos[0]);
            this.setPreco(prec);
        }
        catch(InputMismatchException | NumberFormatException e) {
          //  return null;
        }
        try{
            quant=Integer.parseInt(campos[2]);
            this.setQuant(quant);
        }
        catch(InputMismatchException | NumberFormatException e) {
          //  return null;
        }
        try{
            promo=campos[3];
            this.setPromo(promo);
        }
        catch(InputMismatchException | NumberFormatException e) {
            //return null;
        }
        try{
            codCli=campos[4];
            this.setCli(codCli);
        }
        catch(InputMismatchException | NumberFormatException e) {
          //  return null;
        }
        try{
            mes=Integer.parseInt(campos[5]);
            this.setMes(mes);
        }
        catch(InputMismatchException | NumberFormatException e) {
           // return null;
        }
        try{
            fi=Integer.parseInt(campos[6]);
            this.setFilial(fi);
        }
        catch(InputMismatchException | NumberFormatException e) {
          //  return null;
        }
        //return compra;
    }
    

}
