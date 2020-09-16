/**
 * subclasse da classe veiculo -> carro Hibrido
 */

public class CarroHibrido extends Veiculo{
    /**
     * atributos
     */
  private double consumo;
  private double autonomia;
  public static final int auto=1200;

    /**
     * construtor
     */
  public CarroHibrido(){
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
     * @param x
     * @param y
     * @param consumo
     * @param autonomia
     */
  public CarroHibrido(String marca,String matricula,double velocidade,double preco,double x,double y,double consumo,double autonomia){
      super(marca,matricula,velocidade,preco,x,y);
      this.consumo = consumo;
      this.autonomia = autonomia;
    }

    /**
     * construtor, clone(), equals(), hashCode(), gets e sets
     *
     */
   public CarroHibrido(CarroHibrido c){
      super(c);
      this.consumo = c.getConsumo();
      this.autonomia = c.getAutonomia();
  }
  
  @Override
  public CarroHibrido clone(){
      return new CarroHibrido(this);
  }
  
    @Override
    public String toString() {
        return "CarroHibrido{" + "consumo=" + consumo + ", autonomia=" + autonomia + '}';
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
        final CarroHibrido other = (CarroHibrido) obj;
        if (Double.doubleToLongBits(this.consumo) != Double.doubleToLongBits(other.getConsumo())) {
            return false;
        }
        return Double.doubleToLongBits(this.autonomia) == Double.doubleToLongBits(other.getAutonomia());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.consumo) ^ (Double.doubleToLongBits(this.consumo) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.autonomia) ^ (Double.doubleToLongBits(this.autonomia) >>> 32));
        return hash;
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
