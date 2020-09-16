/**
 * Subclasse do veiculo-> carro Eletrico
 */
public class CarroEletrico extends Veiculo{
    /**
     * atributos do carro eletrico
     */
  private double consumo;
  private double autonomia;
  public static final int auto=1000;

    /**
     * Construtor
     */
  public CarroEletrico(){
      super();
      this.consumo = 0;
      this.autonomia = 0;
    }

    /**
     * Construtor parametrizado
     * @param marca
     * @param matricula
     * @param velocidade
     * @param preco
     * @param x
     * @param y
     * @param consumo
     * @param autonomia
     */
  public CarroEletrico(String marca,String matricula, double velocidade,double preco,double x,double y,double consumo,double autonomia){
      super(marca,matricula,velocidade,preco,x,y);
      this.consumo = consumo;
      this.autonomia = autonomia;
    }

    /**
     * contrutor
      * @param c
     */
  public CarroEletrico(CarroEletrico c){
      super(c);
      this.consumo = c.getConsumo();
      this.autonomia = c.getAutonomia();
  }

    /**
     * clone()
     * @return
     */
  @Override
  public CarroEletrico clone(){
      return new CarroEletrico(this);
  }

    /**
     * gets e sets , toString e Equals()
     * @return
     */
  public double getConsumo() {
        return consumo;
    }

  public double getAutonomia() {
        return autonomia;
    }

  public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    @Override
    public String toString() {
        return "CarroEletrico{" + "consumo=" + consumo + ", autonomia=" + autonomia + '}';
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
        final CarroEletrico other = (CarroEletrico) obj;
        if (Double.doubleToLongBits(this.consumo) != Double.doubleToLongBits(other.getConsumo())) {
            return false;
        }
        if (Double.doubleToLongBits(this.autonomia) != Double.doubleToLongBits(other.getAutonomia())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.consumo) ^ (Double.doubleToLongBits(this.consumo) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.autonomia) ^ (Double.doubleToLongBits(this.autonomia) >>> 32));
        return hash;
    }

  public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }
}
