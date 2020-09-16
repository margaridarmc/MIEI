
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.awt.Point;

/**
 * Classe Aluguer onde são efetuados alugueres e os seus métodos correspondentes
 */
public class Aluguer implements Serializable
{
    /**
     * Atributos da Classe
     */
    private String cliente;
    private String proprietario;
    private String carro;
    private Point.Double fim;
    private Point.Double inicio;
    private Double dist;
    private Double preco;
    private Double duracao;
    private String tempoM;
    private Double dPe;

    /**
     * Construtores da classe , clone()
     */
   public Aluguer () {
        this.cliente = "n/a";
        this.inicio = null;
        this.carro="n/a";
        this.dist=0.0;
        this.preco=0.0;
        this.duracao=0.0;
        this.tempoM="n/a";
        this.dPe=0.0;
        //this.utilizado = utilizado;
    }
    
   public Aluguer (String cliente,Point.Double inicio,Point.Double fim) {
        this.cliente = cliente;
        this.inicio = inicio;
        this.fim = fim;
        this.carro="n/a";
        this.dist=0.0;
        this.preco=0.0;
        this.duracao=0.0;
        this.tempoM="n/a";
        this.dPe=0.0;
        //this.utilizado = utilizado;
    }

   public Aluguer(Aluguer a) {
        this.cliente=a.getCliente();
        this.proprietario=a.getProprietario();
        this.carro=a.getCarro();
        this.fim=a.getFim();
        this.inicio=a.getInicio();
        this.dist=a.getDistancia();
        this.preco=a.getPreco();
        this.duracao=a.getDuracao();
        this.tempoM=a.getTemp();
        this.dPe=a.getDPe();
     }
    
    @Override
   public Aluguer clone(){
        return new Aluguer (this);
    }

    /**
     * Distancia entre dois pontos
     *
     * @param i
     * @param f
     * @return 
     */

    public double distancia (Point.Double i, Point.Double f){
        double distanciaF = i.distance(f);
        return distanciaF;
    }

    /**
     * Calculo do tempo de viagem dependendo em variados fatores devidos da metereologia
     * @param velocidade
     * @return 
     */
    
   public double tempoViagem (double velocidade){
        Random gerador = new Random();
        int t = gerador.nextInt(3);
        double tempo =0;
        switch (t) {
            case 2:
                tempo = 0.7*((this.dist)/velocidade);
                this.tempoM="Estrada com neve";
                break;
            case 1:
                tempo= 0.85*((this.dist)/velocidade);
                this.tempoM="Estrada molhada";
                break;
            default:
                tempo= (this.dist)/velocidade;
                this.tempoM="Estrada com condiçoes favoraveis";
                break;
        }
        tempo *= 60;
        //System.out.println(tempo);
        return tempo;
    }

    /**
     * preço da viagem dependendo da distancia e do custo do veiculo
     * @param i
     * @param f
     * @param precoC
     * @return 
     */

    public double precoViagem(Point.Double i,Point.Double f,Double precoC){
        double distancia = i.distance(f);
        double preco = distancia*precoC;
        return preco;
    }
   

    /**
     * Metodo que devolve o carro mais barato dependento do seu tipo (gasolina, hibrido, eletrico)
     * @param listVeiculo
     * @return 
     */
    
    public Veiculo carroB(Map<String,Veiculo> listVeiculo){
        Veiculo veiculo=new Veiculo();
        List<CarroGasolina> cG= achaCG(listVeiculo);
        List<CarroHibrido> cH= achaCH(listVeiculo);
        List<CarroEletrico> cE= achaCE(listVeiculo);
        double preco=1000000000;
         for( CarroGasolina vv:cG) {
            if(precoViagem(vv.getLocalizacao(),fim,vv.getPreco())<preco && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim)) { 
                                                              preco=precoViagem(vv.getLocalizacao(),fim,vv.getPreco()) ;
                                                              veiculo=vv;
                                                            }
        }
        
         for(CarroHibrido vv:cH) {
            if(precoViagem(vv.getLocalizacao(),fim,vv.getPreco())<preco && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim)) {
                                                                preco=precoViagem(vv.getLocalizacao(),fim,vv.getPreco()) ;
                                                               veiculo=vv;}
         }
         
         for(CarroEletrico vv:cE) {
            if(precoViagem(vv.getLocalizacao(),fim,vv.getPreco())<preco && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim)) { 
                                                                 preco=precoViagem(vv.getLocalizacao(),fim,vv.getPreco()) ;
                                                                veiculo=vv;}
         }
        
        if(!(veiculo.getMatricula().equals("n/d"))){
        this.carro=veiculo.getMatricula();
        this.dist=distancia(veiculo.getLocalizacao(),fim);
        this.preco=precoViagem(veiculo.getLocalizacao(),fim,veiculo.getPreco());
        this.duracao=tempoViagem(veiculo.getVelocidade());
        this.dPe=distancia(inicio,veiculo.getLocalizacao());
        }
        return veiculo;
        
     }

    /**
     * Devolve o carro mais barato dentro de uma determinada distancia
     * @param dist
     * @param listVeiculo
     * @return 
     */

    public Veiculo carroBD(int dist,Map<String,Veiculo> listVeiculo){
        Veiculo veiculo=new Veiculo();
        double d=dist;
        
        List<CarroGasolina> cG= achaCG(listVeiculo);
        List<CarroHibrido> cH= achaCH(listVeiculo);
        List<CarroEletrico> cE= achaCE(listVeiculo);
        double preco= 100000000;
        for( CarroGasolina vv:cG) {
            
            if(distancia(inicio,vv.getLocalizacao())<=d && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim)&& precoViagem(vv.getLocalizacao(),fim,vv.getPreco())<preco)
                                { 
                                    d=distancia(inicio,vv.getLocalizacao()) ;
                                    preco=precoViagem(vv.getLocalizacao(),fim,vv.getPreco()) ;
                                    veiculo=vv;
                                }
        }
        
         for(CarroHibrido vv:cH) {
            if(distancia(inicio,vv.getLocalizacao())<=d && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim) && precoViagem(vv.getLocalizacao(),fim,vv.getPreco())<preco) 
                                { 
                                   d=distancia(inicio,vv.getLocalizacao()) ;
                                   preco=precoViagem(vv.getLocalizacao(),fim,vv.getPreco()) ;
                                   veiculo=vv;
                                }
         }
         
         for(CarroEletrico vv:cE) {
            if(distancia(inicio,vv.getLocalizacao())<=d && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim) && precoViagem(vv.getLocalizacao(),fim,vv.getPreco())<preco) 
                                {
                                 d=distancia(inicio,vv.getLocalizacao()) ;
                                 preco=precoViagem(vv.getLocalizacao(),fim,vv.getPreco()) ;
                                 veiculo=vv;}
         }
        
        if(!(veiculo.getMatricula().equals("n/d"))){
        this.carro=veiculo.getMatricula();
        this.dist=distancia(veiculo.getLocalizacao(),fim);
        this.preco=precoViagem(veiculo.getLocalizacao(),fim,veiculo.getPreco());
        this.duracao=tempoViagem(veiculo.getVelocidade());
        this.dPe=distancia(inicio,veiculo.getLocalizacao());
        }
        return veiculo;
    }


    /**
     *Metodo que devolve o carro ais proximo do cliente
     * @param listVeiculo
     * @return 
     */

    public Veiculo carroP(Map<String,Veiculo> listVeiculo){
        Veiculo veiculo=new Veiculo();
        List<CarroGasolina> cG= achaCG(listVeiculo);
        List<CarroHibrido> cH= achaCH(listVeiculo);
        List<CarroEletrico> cE= achaCE(listVeiculo);
        double d=100000;
        for( CarroGasolina vv:cG) { 
            if(distancia(inicio,vv.getLocalizacao())<d && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim)) { d=distancia(inicio,vv.getLocalizacao()) ;
                                                                                                          veiculo=vv;}
        }
        for(CarroHibrido vv:cH) { 
            if(distancia(inicio,vv.getLocalizacao())<=d && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim)) { d=distancia(inicio,vv.getLocalizacao()) ;
                                                              veiculo=vv;}
         }
        for(CarroEletrico vv:cE) { 
            if(distancia(inicio,vv.getLocalizacao())<=d && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim)) { d=distancia(inicio,vv.getLocalizacao()) ;
                                                              veiculo=vv;}
         }
        
        if(!(veiculo.getMatricula().equals("n/d"))){
        this.carro=veiculo.getMatricula();
        this.dist=distancia(veiculo.getLocalizacao(),fim);
        this.preco=precoViagem(veiculo.getLocalizacao(),fim,veiculo.getPreco());
        this.duracao=tempoViagem(veiculo.getVelocidade());
        this.dPe=distancia(inicio,veiculo.getLocalizacao());
        }
        return veiculo;
    }

    /**
     * Metodo que verifica se a autonomia é suficiente para a viajem
     * @param op
     * @param listVeiculo
     * @return 
     */
    public Veiculo carroA (int op,Map<String,Veiculo> listVeiculo){
        Veiculo veiculo=new Veiculo();
        List<CarroGasolina> cG= achaCG(listVeiculo);
        List<CarroHibrido> cH= achaCH(listVeiculo);
        List<CarroEletrico> cE= achaCE(listVeiculo);
        double d=100000000;
        switch(op){
                case 1: { 
                          for( CarroGasolina vv:cG) { 
                               if(distancia(inicio,vv.getLocalizacao())<d && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim))
                                        { d=distancia(inicio,vv.getLocalizacao()) ;
                                          veiculo=vv;
                                        }
                               }
                        }
                case 2: {
                         for(CarroHibrido vv:cH) { 
                                if(distancia(inicio,vv.getLocalizacao())<=d && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim)) 
                                        { d=distancia(inicio,vv.getLocalizacao()) ;
                                          veiculo=vv;
                                        }
                                }
                        }
                case 3: { 
                          for(CarroEletrico vv:cE) { 
                                if(distancia(inicio,vv.getLocalizacao())<=d && vv.getAutonomia()>=distancia(vv.getLocalizacao(),fim)) 
                                { d=distancia(inicio,vv.getLocalizacao()) ;
                                  veiculo=vv;
                                  
                                }
                                }
                         }
                
            }
            if(!(veiculo.getMatricula().equals("n/d"))){
                this.carro=veiculo.getMatricula();
                this.dist=distancia(veiculo.getLocalizacao(),fim);
                this.preco=precoViagem(veiculo.getLocalizacao(),fim,veiculo.getPreco());
                this.duracao=tempoViagem(veiculo.getVelocidade());
                this.dPe=distancia(inicio,veiculo.getLocalizacao());
            }
            return veiculo;
    }

    /**
     * Metodo que devolve um determinado veiculo em especifico. Este é procurado atraves da correspondencia da matricula.
     * Tensta se ele existe
     * @param mat
     * @param listVeiculo
     * @return 
     */
    public Veiculo carroE(String mat,Map<String,Veiculo> listVeiculo) {
        Veiculo veiculo=new Veiculo();
        Veiculo v=new Veiculo();
        if(listVeiculo.containsKey(mat)){
            veiculo=listVeiculo.get(mat);
            if(veiculo instanceof CarroGasolina) {
                                              CarroGasolina c= (CarroGasolina) veiculo;
                                              if(c.getAutonomia()<distancia(veiculo.getLocalizacao(),fim)) {return v;}
                                             }
            else if (veiculo instanceof CarroHibrido) {
                                                    CarroHibrido c= (CarroHibrido) veiculo;
                                                    if(c.getAutonomia()<distancia(veiculo.getLocalizacao(),fim)) {return v;}
                                                   }
            else  {
                        CarroEletrico c= (CarroEletrico) veiculo;
                        if(c.getAutonomia()<distancia(veiculo.getLocalizacao(),fim)) {return v;}
                    }
                                                
            }

        if(!(veiculo.getMatricula().equals("n/d"))){
        this.carro=veiculo.getMatricula();
        this.dist=distancia(veiculo.getLocalizacao(),fim);
        this.preco=precoViagem(veiculo.getLocalizacao(),fim,veiculo.getPreco());
        this.duracao=tempoViagem(veiculo.getVelocidade());
        this.dPe=distancia(inicio,veiculo.getLocalizacao());
        }
        return veiculo;
    }


    /**
     * Gets e Sets dos Atributos da Classe
     * @return 
     */
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getCarro() {
        return carro;
    }
    
    public String getTemp() {
        return this.tempoM;
    }
    public Double getDPe() {
        return this.dPe;
    }
    
    public void setCarro(String carro) {
        this.carro = carro;
     }

    public Double getDistancia() {
        return dist;
    }

    public void setDistancia(Double distancia) {
        this.dist = distancia;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public Point.Double getFim() {
        return fim;
    }

    public void setFim(Point.Double fim) {
        this.fim = fim;
    }

    public Point.Double getInicio() {
        return inicio;
    }

    public void setInicio(Point.Double inicio) {
        this.inicio = inicio;
    }


    /**
     * verifica se um determinado carro está no sistema. Metodos diferentes para carro a gasolina, eletrico e hibrido, respetivamente
     */
    private List<CarroGasolina> achaCG (Map<String,Veiculo> listVeiculo){

        List<CarroGasolina> list = new ArrayList<>();
        for (Map.Entry<String, Veiculo> stringVeiculoEntry : listVeiculo.entrySet()) {
            Veiculo s = stringVeiculoEntry.getValue();
            if (s instanceof CarroGasolina) {
                list.add((CarroGasolina) s);
            }
        }
        return list;
    }
    private List<CarroEletrico> achaCE (Map<String,Veiculo> listVeiculo){

        List<CarroEletrico> list = new ArrayList<>();
        for (Map.Entry<String, Veiculo> stringVeiculoEntry : listVeiculo.entrySet()) {
            Veiculo s = stringVeiculoEntry.getValue();
            if (s instanceof CarroEletrico) {
                list.add((CarroEletrico) s);
            }
        }
        return list;
    }
    
    private List<CarroHibrido> achaCH (Map<String,Veiculo> listVeiculo){

        List<CarroHibrido> list = new ArrayList<>();
        for (Map.Entry<String, Veiculo> stringVeiculoEntry : listVeiculo.entrySet()) {
            Veiculo s = stringVeiculoEntry.getValue();
            if (s instanceof CarroHibrido) {
                list.add((CarroHibrido) s);
            }
        }
        return list;
    }


}
