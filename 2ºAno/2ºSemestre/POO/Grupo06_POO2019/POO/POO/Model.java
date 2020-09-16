import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;
import java.awt.Point;


/**
 * Classe model
 */
public class Model implements Serializable {
    
    private Map<String,Cliente> listCli;
    private Map<String,Proprietario> listProp;
    private Map<String,Veiculo> listVeiculo;
    private Set<String[]> alugueresPorFazerFich;
    private LeituraHistorico ler;
    private ReadWriteFichBinario emp;
    private Set<Aluguer> alugueres;
    private Map<String,Integer> classificacoes;


    /**
     * Faz leitura do ficheiro incial e guarda-os no sistema
     */
    public void initModel() {
            ler=new LeituraHistorico();
            ler.ler();
            listCli=ler.getCli();
            listProp=ler.getProp();
            listVeiculo=ler.getVei();
            alugueresPorFazerFich=ler.getAl();
            fazAlugueres();
            alugueres =new HashSet<>();
            classificacoes=ler.getClassificacoes();
            distribuiClassif();
         }

    /**
     * Faz os alugueres do ficheiro
     */
    public void fazAlugueres(){
                
                Veiculo veic=new Veiculo();
                double d=0;
                    for(String[] s: alugueresPorFazerFich){
                        
                        if(listCli.containsKey(s[1])){
                            Point.Double fim= new Point.Double(Double.parseDouble(s[2]),Double.parseDouble(s[3]));
                            String cNif= s[1];
                            Aluguer alug=new Aluguer(cNif,listCli.get(cNif).getLocalizacao(),fim);
                            
                            if(s[5].equals("MaisBarato")) {
                                veic=alug.carroB(getListVeiculo());}
                             else if(s[5].equals("MaisPerto")){
                                veic=alug.carroP(getListVeiculo());}
                            
                            if(!(veic.equals(null))){
                                if(listVeiculo.containsKey(veic.getMatricula())) {
                                    String pNif= achaProp(veic.getMatricula());
                                    String mat=veic.getMatricula();
                                if(listProp.containsKey(pNif)){
                                    listProp.get(pNif).adicionaAluguerF(alug);
                                    listCli.get(s[1]).adicionaAluguer(alug);
                                    listProp.get(pNif).adiconaALCarro(alug,mat);
                                    listVeiculo.get(mat).adicionaAluguer(alug);
                                    Point.Double ponto=new Point.Double(Double.parseDouble(s[2]),Double.parseDouble(s[3]));
                                    double dist=distancia(ponto,listCli.get(cNif).getLocalizacao());
                                    seeAutonomia(veic.getMatricula(),dist);
                               }   
                            }
                        }
                    }
                }  
               
    }


    /**
     * Metodo que procura o proprietario  no sistema
     * @param mat
     * @return
     */
    private String achaProp (String mat){
        String m=null;
        for(Proprietario p : listProp.values()){
            for(Veiculo vei : p.getCarros()){
                if(mat.equals(vei.getMatricula())){
                    { m=p.getNif();}
                }
            }   
        }
        return m;
    }

    /**
     * Faz o login de um utilizador, verifica a sua existencia e vê se a password está correta, caso contrario devolve as suas Exceptions correspondentes
     * @param email
     * @param pass
     * @return
     * @throws PasswordErradaException
     * @throws ElemNotFoundException
     */
    public Utilizador iniciarSessao(String email, String pass) throws PasswordErradaException,ElemNotFoundException {
            Utilizador u= new Utilizador();
            u=null;

            if(listCli.values().stream().anyMatch(c->email.equals(c.getEmail())) ) {

                for (Cliente x : listCli.values()) {
                    if (email.equals(x.getEmail())) {
                        if (pass.equals(x.getPassword())) {
                            u=x;
                        }
                        else throw new PasswordErradaException("Password errada");
                    }
                }
            }


            else if( listProp.values().stream().anyMatch(c->email.equals(c.getEmail())) ) {
                for (Proprietario x : listProp.values()) {
                    if (email.equals(x.getEmail())) {
                        if (pass.equals(x.getPassword())) {
                            u=x;
                        }
                        else throw new PasswordErradaException("Password errada");
                    }
                }
            }
            else throw new ElemNotFoundException("Utilizador nao valido");
                return u;
    }

    /**
     * Adiciona um novo cliente ao sistema se este já não exixtir neste. Compara pelo nif
     * @param nome
     * @param nif
     * @param email
     * @param morada
     * @param x
     * @param y
     * @param pass
     * @throws ElemJaExisteException
     */
   public void addCli (String nome,String nif, String email, String morada,double x, double y,String pass)throws ElemJaExisteException{
       if(!(listCli.containsKey(nif))){
        Cliente cli = new Cliente(nome,nif,email,morada,x,y,pass);
        listCli.put(cli.getNif(),cli);
      }
      else{
          throw new ElemJaExisteException("Cliente ja existe no sistema");
     }
    
    }

    /**
     * Adiciona um novo proprietario ao sistema se este já não exixtir neste. Compara pelo nif
      * @param nome
     * @param nif
     * @param email
     * @param morada
     * @param pass
     * @throws ElemJaExisteException
     */
   public void addProp (String nome,String nif, String email, String morada,String pass)throws ElemJaExisteException{
        if(!(listProp.containsKey(nif))){
        List<Veiculo> carros=new ArrayList<>();
        Proprietario p = new Proprietario(nome,nif,email,morada,pass,carros);
        listProp.put(p.getNif(),p);
    }
        else{
          throw new ElemJaExisteException("Proprietario ja existe no sistema");
     }
    }

    /**
     * Adiciona um novo carro ao sistema se este já não existir. Verifica pela matricula
     * @param opcao
     * @param marca
     * @param matricula
     * @param velocidade
     * @param preco
     * @param x
     * @param y
     * @param autonomia
     * @param consumo
     * @param prop
     * @throws ElemJaExisteException
     */
   public void addCarro (int opcao,String marca,String matricula, double velocidade,double preco,double x,double y,double autonomia,double consumo,String prop)throws ElemJaExisteException{
       if(!(listVeiculo.containsKey(matricula))){
       if(opcao==1) {
                        CarroGasolina cg=new CarroGasolina(marca, matricula,velocidade,preco,consumo,autonomia, x,y);
                        listVeiculo.put(cg.getMatricula(),cg);
                        listProp.get(prop).adicionaCarros(cg);
                        
                    }
                    
       if(opcao==2) {
                        CarroHibrido ch=new CarroHibrido(marca, matricula,velocidade,preco,consumo,autonomia, x,y);
                        listVeiculo.put(ch.getMatricula(),ch);
                        listProp.get(prop).adicionaCarros(ch);
                    } 
                    
       if(opcao==3) {
                        CarroEletrico ce=new CarroEletrico(marca, matricula,velocidade,preco,consumo,autonomia, x,y);
                        listVeiculo.put(ce.getMatricula(),ce);
                        listProp.get(prop).adicionaCarros(ce);
                    }  
                }
                     else{
          throw new ElemJaExisteException("Carro ja existe no sistema");
     }
    
   }

    /**
     * Procura carro com preço mais baixo e verifica se encontrou
     * @param cli
     * @param fim
     * @return
     * @throws ElemNotFoundException
     */
    public Aluguer carroB(Cliente cli,Point.Double fim)throws ElemNotFoundException{
        Aluguer a=new Aluguer(cli.getNif(),cli.getLocalizacao(),fim );
        Map<String,Veiculo> aux =getListVeiculo();
        Veiculo m=a.carroB(aux);
        if(!(m.getMatricula().equals("n/d")) ){
            String p=achaProp(m.getMatricula());
            a.setProprietario(p);
            alugueres.add(a.clone());
            return a.clone();
        }
        else throw new ElemNotFoundException("Nenhum carro achado com este filtro");
    }

    /**
     * Procura carro com o preço mais baixo numa determinada distancia e verifica se encontrou
     * @param cli
     * @param fim
     * @param dist
     * @return
     * @throws ElemNotFoundException
     */
    public Aluguer carroBD(Cliente cli,Point.Double fim,int dist)throws ElemNotFoundException{
        Aluguer a=new Aluguer(cli.getNif(),cli.getLocalizacao(),fim );
        Map<String,Veiculo> aux =getListVeiculo();
        Veiculo m =a.carroBD(dist,aux);
        if(!(m.getMatricula().equals("n/d")) ){
            String p=achaProp(m.getMatricula());
            a.setProprietario(p);
            alugueres.add(a.clone());
            return a.clone();
       }
       else throw new ElemNotFoundException("Nenhum carro achado com este filtro");
    }

    /**
     * Procura carro mais perto e verifica se encontrou
     * @param cli
     * @param fim
     * @return
     * @throws ElemNotFoundException
     */
    public Aluguer carroP(Cliente cli,Point.Double fim)throws ElemNotFoundException{
        Aluguer a=new Aluguer(cli.getNif(),cli.getLocalizacao(),fim );
        Map<String,Veiculo> aux =getListVeiculo();
        Veiculo m=a.carroP(aux);
        if(!(m.getMatricula().equals("n/d")) ){
            String p=achaProp(m.getMatricula());
            a.setProprietario(p);
            alugueres.add(a.clone());
            return a.clone();
       }
       else throw new ElemNotFoundException("Nenhum carro achado com este filtro");
    }

    /**
     * Procura carro pelo tipo de autonomia e verifica se encontrou
     * @param op
     * @param cli
     * @param fim
     * @return
     * @throws ElemNotFoundException
     */
     public Aluguer carroA(int op,Cliente cli,Point.Double fim)throws ElemNotFoundException{
        Aluguer a=new Aluguer(cli.getNif(),cli.getLocalizacao(),fim );
        Map<String,Veiculo> aux =getListVeiculo();
        Veiculo m=a.carroA(op,aux);
        if(!(m.getMatricula().equals("n/d")) ){
            String p=achaProp(m.getMatricula());
            a.setProprietario(p);
            alugueres.add(a.clone());
            return a.clone();
        }
        else throw new ElemNotFoundException("Nenhum carro achado com este filtro");
    }

    /**
     * Procura carro especifico para fazer viagem e verifica se encontrou
     * @param mat
     * @param cli
     * @param fim
     * @return
     * @throws ElemNotFoundException
     */
     public Aluguer carroE(String mat,Cliente cli,Point.Double fim)throws ElemNotFoundException{
        Aluguer a=new Aluguer(cli.getNif(),cli.getLocalizacao(),fim );
        Map<String,Veiculo> aux =getListVeiculo();
         Veiculo m = a.carroE(mat, aux);
         if (!(m.getMatricula().equals("n/d"))) {
             String p = achaProp(m.getMatricula());
             a.setProprietario(p);
             alugueres.add(a.clone());

             return a.clone();
         } else throw new ElemNotFoundException("Nenhum carro achado com este filtro");
     }


    /**
     * Metodo que permite o proprietario aceitar ou nao um aluguer pedido
     * @param prop
     */
    public void aceitarAluguer( Proprietario prop){
                          Random gerador = new Random();
                          int num1,num2,num3;
                          Scanner scan = new Scanner(System.in);
                          String s=null;
                          String mat=null;
                           for ( Aluguer fst : prop.getAluguerP() ) {
                               
                               System.out.println("Cliente: " + fst.getCliente() + " Carro: "+ fst.getCarro());
                               s = scan.nextLine();
                               
                               if( s.equals("s") || s.equals("S") ) { /*Classificaçoes*/
                                                                      num1 = gerador.nextInt(100);
                                                                      num2 = gerador.nextInt(100);
                                                                      num3 = gerador.nextInt(100);
                                                                      listCli.get(fst.getCliente()).adicionaClassificacao(num1); 
                                                                      listProp.get(prop.getNif()).adicionaClassificacao(num2); 
                                                                      listProp.get(prop.getNif()).adiconaClaCarro(num3,fst.getCarro());
                                                                      listVeiculo.get(fst.getCarro()).adicionaClassificacao(num3); 
                                                                      /*Alugueres*/
                                                                      listProp.get(prop.getNif()).adicionaAluguerF(fst);
                                                                      listProp.get(prop.getNif()).removeAluguerP(fst);
                                                                      listCli.get(fst.getCliente()).adicionaAluguer(fst);
                                                                      listVeiculo.get(fst.getCarro()).adicionaAluguer(fst);
                                                                      listProp.get(prop.getNif()).adiconaALCarro(fst,fst.getCarro());
                                                                      /*Localizaçao*/
                                                                      listCli.get(fst.getCliente()).setLocalizacao(fst.getFim());
                                                                      listVeiculo.get(fst.getCarro()).setLocalizacao(fst.getFim());
                                                                      /*Abastecimento*/
                                                                      seeAutonomia(listVeiculo.get(fst.getCarro()).getMatricula(),fst.getDistancia());
                                                                      listVeiculo.get(fst.getCarro()).setLocalizacao(fst.getFim());
                                                                    }  
                                                                    
                        }
    }

    /**
     * Envia um aluguer para o respetivo proprietario
     * @param a
     * @return
     */
    public String solicitaA (Aluguer a) {
        String nifP=null;
        listProp.get(a.getProprietario()).adicionaAluguerP(a);
        nifP=listProp.get(a.getProprietario()).getNif();
        return nifP;  
    }

    /**
     * Adiciona classificações
     */
    public void distribuiClassif(){
        for(String id : classificacoes.keySet()){
            if(listCli.containsKey(id)){
                listCli.get(id).adicionaClassificacao(classificacoes.get(id));
            }
            else if(listProp.containsKey(id)){
                listProp.get(id).adicionaClassificacao(classificacoes.get(id));
            }
            else if(listVeiculo.containsKey(id)){
                listVeiculo.get(id).adicionaClassificacao(classificacoes.get(id));
                listProp.get(achaProp(id)).adiconaClaCarro(classificacoes.get(id),id);
            }
       }
    }

    /**
     * Abastece um veiculo
     * @param matC
     * @param d
     */
    public void seeAutonomia (String matC,double d){
        double a=0;
        Veiculo v =listVeiculo.get(matC);
        
        if(v instanceof CarroGasolina)  {  CarroGasolina cgg= (CarroGasolina) v;
                                           a=cgg.getAutonomia(); cgg.setAutonomia(a-d);
                                           if(cgg.getAutonomia()<CarroGasolina.auto)  cgg.setAutonomia(CarroGasolina.auto); }
                                            
        else if (v instanceof CarroHibrido) { CarroHibrido chh= (CarroHibrido) v;
                                              a=chh.getAutonomia(); chh.setAutonomia(a-d);
                                              if(chh.getAutonomia()<CarroHibrido.auto)  chh.setAutonomia(CarroHibrido.auto); }
        
        else if (v instanceof CarroEletrico) { CarroEletrico cee= (CarroEletrico) v;
                                               a=cee.getAutonomia(); cee.setAutonomia(a-d);
                                               if(cee.getAutonomia()<CarroEletrico.auto)  cee.setAutonomia(CarroEletrico.auto);}                
     }

    /**
     * Calcula distancia entre dois pontos
     * @param i
     * @param f
     * @return
     */
    public double distancia (Point.Double i,Point.Double f){
        double distancia = i.distance(f);
        return distancia;
    }

    /**
     * get
     * @return
     */
    public Map<String, Veiculo> getListVeiculo() {
        return Collections.unmodifiableMap(listVeiculo);
    }

    /**
     * Set
     * @return
     */
    public Set<Cliente> rankingKms(){
       ComparatorKm c = new ComparatorKm();
       return listCli.values().stream()
                              .sorted(c)
                              .map(e -> e.clone())
                              .collect(Collectors.toSet());
    }

    /**
     * altera um preço de um carro
     * @param matricula
     * @param preco
     * @param p
     * @param ind
     */
    public void alteraPreco(String matricula,Double preco,String p,int ind){
         listProp.get(p).getCarros().get(ind).setPreco(preco);
         listVeiculo.get(matricula).setPreco(preco);
   }

    /**
     * Devolve a lista do top 10 cliente que mais km fizeram
     * @return
     */
   public Set<Cliente> top10(){
       return listCli.values().stream()
                              .sorted(new ComparatorKm())
                              .limit(10)
                              .collect(Collectors.toSet());
    }

    /**
     * devolve a lista dos top 10 clientes que mais alugueres efetuaram
     * @return
     */
     public Set<Cliente> top102(){
       return listCli.values().stream()
                              .sorted(new ComparatorAluguer())
                              .limit(10)
                              .collect(Collectors.toSet());
    }
}
