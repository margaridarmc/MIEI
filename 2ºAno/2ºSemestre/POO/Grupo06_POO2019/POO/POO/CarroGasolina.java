/**
 * Subclaase do veiculo -> Carro a Gasolina
 */
public class CarroGasolina extends Veiculo{
  private double consumo;
  private double autonomia;
  public static final int auto=1500;

    /**
     * construtor
     */
  public CarroGasolina(){
      super();
      this.consumo = 0;
      this.autonomia = 0;
    }

    /**
     * construtor parametrizado
     * @param marca
     * @param matricula
     * @param velocidade
     * @param preco
     * @param consumo
     * @param autonomia
     * @param x
     * @param y
     */
  public CarroGasolina(String marca,String matricula, double velocidade,double preco,double consumo,double autonomia,double x,double y){
      super(marca,matricula,velocidade,preco,x,y);
      this.consumo = consumo;
      this.autonomia = autonomia;
    }

    /**
     * contrutor
     * @param c
     */
 public CarroGasolina(CarroGasolina c){
      super(c);
      this.consumo = c.getConsumo();
      this.autonomia = c.getAutonomia();
  }

    /**
     * Contrutor, equals(), clone(), toString(), HashCode(), gets e sets
     * @return
     */


  @Override
  public CarroGasolina clone(){
      return new CarroGasolina(this);
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
        final CarroGasolina other = (CarroGasolina) obj;
        if (Double.doubleToLongBits(this.consumo) != Double.doubleToLongBits(other.getConsumo())) {
            return false;
        }
        return Double.doubleToLongBits(this.autonomia) == Double.doubleToLongBits(other.getAutonomia());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.consumo) ^ (Double.doubleToLongBits(this.consumo) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.autonomia) ^ (Double.doubleToLongBits(this.autonomia) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "CarroGasolina{" + "consumo=" + consumo + ", autonomia=" + autonomia + '}';
    }

  public double getConsumo() {
        return consumo;
    }

  public double getAutonomia() {
        return autonomia;
    }

  public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

  public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }
}
