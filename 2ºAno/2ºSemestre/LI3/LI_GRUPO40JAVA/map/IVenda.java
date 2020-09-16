
public interface IVenda
{
    public String getProd();
    public String getCli();
    public String getPromo();
    public int getMes();
    public int getFilial();
    public String getCodigo();
    public int getQuant();
    public double getPreco();
    public double getValor();
    public void linhaToVenda(String linha);
}
