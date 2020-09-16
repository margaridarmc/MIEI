import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public class GestModel implements IGestModel 
    {
    LeituraF lerP;
    LeituraF lerC;
    LeituraF lerV;
    CatProdutos Cp;
    CatClientes Cc;
    CatVendas Cv ;
    Filial filial;
    Faturacao fat;
    Crono tempo1;
    Crono tempo2;
    Crono tempo3;

    /**
     * Inicializa as variaveis de instancia
     */
    public void initModel(){
        this.lerP= new LeituraF();
        this.lerC= new LeituraF();
        this.lerV= new LeituraF();
        this.Cp = new CatProdutos();
        this.Cc = new CatClientes();
        this.Cv = new CatVendas();
        this.filial=new Filial();
        this.fat=new Faturacao();
        this.tempo1 = new Crono();
        this.tempo2 = new Crono();
        this.tempo3 = new Crono();
    }
    
    /**
     * Constroi o catalogo de produtos
     */
    public String leP(){
        tempo1.start();
        lerP.lerFiles("Produtos.txt");
        Cp.validaProduto(lerP.getLinhas());
        tempo1.stop();
        return tempo1.print();
    }

     /**
     * Constroi o catalogo de clientes
     */
      public String leC(){
        tempo2.start();
        lerC.lerFiles("Clientes.txt");
        Cc.validaCliente(lerC.getLinhas());
        tempo2.stop();
        return tempo2.print();
    }
    
     /**
     * Constroi o catalogo de vendas
     */
    public String leV(int i){
        tempo3.start();
        if(i==1) lerV.lerFiles("Vendas_1M.txt");
        else if(i==2)lerV.lerFiles("Vendas_3M.txt");
        else if(i==3) lerV.lerFiles("Vendas_5M.txt");
        Cv.validaVendas(lerV.getLinhas(),Cp.getCatP(),Cc.getCatalogoC());
        tempo3.stop();
        return tempo3.print();
    }

     /**
     * Constroi a faturaçao e a filial
     */
    public void lerFileFat(){
        filial.fazFilial(Cv.getCatVendas());
        fat.addFaturacao(Cv.getCatVendas());
    }

     /**
     * querie1-model
     */
    public Set<String> querie1aux() {
        Set<String> lista=new TreeSet<>();
        for(Venda v:Cv.getCatVendas()){
            lista.add(v.getProd());
        }
        Set<String> lFinal=new TreeSet<>();
        int i=0;
        while(i<26){
           for(String str: Cp.getCatP()) {

              if((lista.contains(str))==false) {lFinal.add(str);}

          }
           i++;
         }
        return lFinal;
    }
    
     /**
     * querie2-model
     */
    public Triplo q2P1(int mes){
        ArrayList<String> clili = new ArrayList<>();
        int total = 0;
        int clientes = 0;
        String nn;
        //int count=Cv.getCatVendas().size(); 

        total = fat.getFat().get(mes).size();

        for (Venda vee : fat.getFat().get(mes)) {
                   nn = vee.getCli();
                   if(clili.contains(nn)==false) {
                       clili.add(nn);
                       clientes++;
                    }
               }
        Triplo t = new Triplo(total,clientes,total);

        return t;
    }
    
    /**
     * querie2-model
     */
    public  Triplo q2P2(int fi) throws NaoExisteException{
            if(fi>0 && fi<4){
                int tf=0;

                int clientes = 0;
                String nn;
                ArrayList<String> clili = new ArrayList<>();
                int clif = filial.getFilial().get(fi).size();
                for (Venda vee : fat.getFat().get(fi)) {
                    nn = vee.getCli();
                    if (clili.contains(nn) == false) {
                        clili.add(nn);
                        clientes++;
                    }
                }

                Triplo t= new Triplo(clif,clientes,tf);

                return t;
            }
            else throw new NaoExisteException ("Filial nao existente.Introduza nova filial:");
        }
       
    /**
     * querie3-model
     */
    public Triplo querie3aux(String cliente,int mes) throws NaoExisteException{
        if(this.Cc.getCatalogoC().contains(cliente)){
            int nCompras=0;
            double tP=0;

          Object[] object = new Object[3];
          Set<String> nProd=new HashSet<>();
            for(Venda v: fat.getFat().get(mes)){
                if(v.getCli().equals(cliente)) {

                    nCompras+=v.getQuant();
                    if(nProd.contains(v.getProd())==false) nProd.add(v.getProd());
                    tP+=v.getQuant() * v.getPreco();
                }
            }
            Triplo t= new Triplo(nCompras,tP,nProd.size());
            return t;
        }
        else throw new NaoExisteException("Cliente inexistente");
    }

    /**
     * querie4-model
     */
    public Triplo querie4aux(String produto,int mes) throws NaoExisteException{
        if(this.Cp.getCatP().contains(produto)){
        int nCompras=0;
        Set<String> nCli=new HashSet<>();
        float tP=0;

        Object[] object = new Object[3];
        for(Venda v: fat.getFat().get(mes)){
                if(v.getProd().equals(produto) ){
                    nCompras+=v.getQuant();
                    if(nCli.contains(v.getCli())==false) nCli.add(v.getCli());
                    tP+=v.getQuant() * v.getPreco();
                }
            }
            Triplo t= new Triplo(nCompras,tP,nCli.size());
            return t;
        }
         else throw new NaoExisteException("Cliente inexistente");
    }
    
    /**
     * querie5-model
     */
    public Map<String,Integer> querie5aux(String cliente) throws NaoExisteException{
        if(this.Cc.getCatalogoC().contains(cliente)){
        Map<String,Integer> list=new HashMap<>();
        int count=0,i=1;
         while(i<4)
         {
            for(Venda v:filial.getFilial().get(i).get(cliente)){

                if (list.containsKey(v.getProd())) count=list.get(v.getProd()) + v.getQuant();
                else count =v.getQuant();
                list.put(v.getProd(),count);
            }
            i++;
        }
        return list;
        }
        else throw new NaoExisteException("Cliente inexistente");
    }

    
    /**
     * querie6-model
     */
    public  Set<String> q6Aux (int f) {


       Map<String, Integer> map = new TreeMap<>();
       for(Venda v: Cv.getCatVendas()) {
           if( !(map.containsKey(v.getProd())) ){
               map.put(v.getProd(),0);}
           int d=map.get(v.getProd());
           map.put(v.getProd(),d+v.getQuant());
        }

        Map<String, Integer> sorted = map.entrySet()
                                          .stream()
                                          .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                                          .limit(f)
                                          .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,LinkedHashMap::new));

       //for(String a :sorted.keySet()) System.out.println(a);
       Map<String,Set<String>> mfinal=new HashMap<>();

       for(Venda v: Cv.getCatVendas() ){
           if(sorted.containsKey(v.getProd()) ) {
             if(!(mfinal.containsKey(v.getProd()))){
               Set<String> s=new HashSet<>();
               mfinal.put(v.getProd(),s);}
             if(!(mfinal.get(v.getProd()).contains(v.getCli()))) {mfinal.get(v.getProd()).add(v.getCli());}

            }
        }

        Set<String> l = new HashSet<>();
        for (String p:sorted.keySet()) {
            l.add("Produto :  " + p + "        Numero de clientes diferentes:  " + mfinal.get(p).size() );
        }
        return l;
    }
    
    /**
     * querie7-model
     */
    public Map<String,Double> querie7aux(int f) throws NaoExisteException   {

        if(f>0 && f<4){
        double count=0;
        Map<String,Double> cli = new HashMap<>();
        for( String s:  filial.getFilial().get(f).keySet() )
            {
               count=0;
               for( Venda v : filial.getFilial().get(f).get(s)){
                   count+=(v.getQuant()*v.getPreco());
                }
               cli.put(s,count);
         }
        Map<String,Double> sort =cli .entrySet() .stream()
                                     .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                                    .limit(3)
                                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,LinkedHashMap::new));


        return sort;
      }
      else throw new NaoExisteException ("Filial nao existente.Introduza nova filial:");
    }
    
    /**
     * querie8-model
     */
    public Map<String,Integer> q8Aux (int f) {


       Map<String, Set<String>> map = new TreeMap<>();
       for(Venda v: Cv.getCatVendas()) {
           if(!(map.containsKey(v.getCli()))){
               Set<String> a=new HashSet<>();
               map.put(v.getCli(),a);}
           map.get(v.getCli()).add(v.getProd());
        }
       Map<String,Integer> mapf =new TreeMap<>();
       for(String s : map.keySet()) {
           mapf.put(s,map.get(s).size());
        }

       Map<String, Integer> sorted = mapf.entrySet()
                                          .stream()
                                          .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                                          .limit(f)
                                          .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,LinkedHashMap::new));


        return sorted;
    }

    /**
     * querie9-model
     */
    public Map<String, Double> q9Aux(String produto,int n) throws NaoExisteException{
         Set<String> teste = new HashSet<>();

        Map<String,Map<String,Double>> res = new HashMap<>();
        Map<String,Double> easy ;
        double guarda ;
        double valorG ;
        if(this.Cp.getCatP().contains(produto)){
        for(Venda vee : Cv.getCatVendas()){
                String prod = vee.getProd();
                if(teste.contains(vee.getProd())==false) teste.add(vee.getProd());
                if(res.containsKey(prod)==false) {

                    easy = new HashMap<>();
                    valorG = (vee.getQuant())*(vee.getPreco());
                    easy.put(vee.getCli(), valorG);
                    res.put(prod,easy);

                }
                else {
                    easy = res.get(prod);
                    valorG = (vee.getQuant())*(vee.getPreco());
                    if(easy.containsKey(vee.getCli())==true){
                            guarda = easy.get(vee.getCli()) + valorG;
                            easy.put(vee.getCli(), guarda);
                            res.put(prod,easy);
                    }
                    if(easy.containsKey(vee.getCli())==false){
                        easy.put(vee.getCli(), valorG);
                        res.put(prod,easy);
                    }
                    }

            }

        //ordenar pelo valor gasto;
          Map<String, Double> sorted = res.get(produto).entrySet().stream()
                                                        .limit(n)
                                                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                                                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,LinkedHashMap::new));

          return sorted;
         }
         else throw new NaoExisteException("Produto nao existe.");
     }

     /**
     * querie10-model
     */
    public Map<Integer,Map<Integer,Double>> q10Aux(String prodDesejado) throws NaoExisteException {
       Map<Integer,Map<Integer,Double>> resultado = new HashMap<>();
       Map<Integer,Double> aux ;
        int guardaMes;
        int guardaFilial;
        double fazContaMed;

        if(this.Cp.getCatP().contains(prodDesejado)){
        for(Map.Entry<Integer,Set<Venda>> a : fat.getFat().entrySet()){
            guardaMes= a.getKey();
            for(Venda b : a.getValue()){
                fazContaMed = b.getPreco() * b.getQuant();
                if(b.getProd().equals(prodDesejado)){
                    aux = new HashMap<>();
                    guardaFilial = b.getFilial();
                    if(resultado.containsKey(guardaMes)==true) {
                        aux = resultado.get(guardaMes);
                        if(aux.containsKey(guardaFilial)==true) {
                            fazContaMed = fazContaMed + aux.get(guardaFilial);
                        }
                    }
                    aux.put(guardaFilial, fazContaMed);
                    resultado.put(guardaMes, aux);
                    }
                }
            }
        return resultado;
          }
       else throw new NaoExisteException("Produto nao existe.");
     }
        public Map<Integer,Integer> comprasMes(){
            int i;
            Map<Integer,Integer> mymap= new HashMap<>();
            for(i = 1; i<13; i++){
                mymap.put(i,fat.getFat().get(i).size());
            }
            return mymap;
        }
   /**
    * Numero de clientes distintos que compraram
    */
   public int clientesCompras(){
         Set<String> nCli= new HashSet<>();
         for(String c : Cc.getCatalogoC()){
             nCli.add(c);
            }
            return nCli.size();
        }
    
   /**
    * Numero de vendas com faturaçao igual a 0
    */
   public int precoZero(){
            int r=0;
            for(Venda v : Cv.getCatVendas()){
                if(0.0 == v.getPreco()) r++;
            }
            return r;
         }
         
    /**
     * Faturaçao total
     */
      public double fatTotal(){
          double res =0;
          for(Venda v : Cv.getCatVendas()){
              res += v.getPreco()*v.getQuant();
            }
          return res;
   }

  /**
   * Auxiliar 
   */
  private double fatTM1(int i,int ii) {

           double res1 = 0;
           double res2 = 0;
           double res3 = 0;
           Set<String> s1 = new HashSet<>();
           Set<String> s2 = new HashSet<>();
           Set<String> s3 = new HashSet<>();

           for (Venda v : fat.getFat().get(i)) {
               if (v.getFilial() == 1) {
                   res1 += v.getPreco() * v.getQuant();
                   s1.add(v.getCli());
               } else if (v.getFilial() == 2) {
                   res2 += v.getPreco() * v.getQuant();
                   s2.add(v.getCli());
               } else {
                   res3 += v.getPreco() * v.getQuant();
                   s3.add(v.getCli());
               }

           }
           if(ii==1){ return res1; }
           else if(ii==2){ return res2; }
           else if(ii==3){ return res3; }
           else if(ii==4){ return s1.size(); }
           else if(ii==5){ return s2.size(); }
           else if(ii==6){ return s3.size();}
           return 0;
       }
       
       /**
        * Map com total faturado por mes
        */
       public Map<Integer,Double> tableF(int filial) {
           int mes;
           Map<Integer,Double> fatF1 = new HashMap<>();
           for(mes=1;mes<13;mes++) { fatF1.put(mes,fatTM1(mes,1));}
           Map<Integer,Double> fatF2 = new HashMap<>();
           for(mes=1;mes<13;mes++) { fatF2.put(mes,fatTM1(mes,2));}
           Map<Integer,Double> fatF3 = new HashMap<>();
           for(mes=1;mes<13;mes++) { fatF3.put(mes,fatTM1(mes,3));}
         if (filial==1) return fatF1;
         if (filial==2) return fatF2;
         return fatF3;
       }

       /**
        * Map com numero de clientes distintos por mes
        */
        public Map<Integer,Double> tableC (int filial) {
            int mes;
            Map<Integer,Double> fatF1 = new HashMap<>();
            for(mes=1;mes<13;mes++) { fatF1.put(mes,fatTM1(mes,4));}
            Map<Integer,Double> fatF2 = new HashMap<>();
            for(mes=1;mes<13;mes++) { fatF2.put(mes,fatTM1(mes,5));}
            Map<Integer,Double> fatF3 = new HashMap<>();
            for(mes=1;mes<13;mes++) { fatF3.put(mes,fatTM1(mes,6));}
            if (filial==1) return fatF1;
            if (filial==2) return fatF2;
            return fatF3;
        }



    /**
     * Setters e Getters
     */
    
    public LeituraF getLerP() {
        return lerP;
    }

    public void setLerP(LeituraF lerP) {
        this.lerP = lerP;
    }

    public LeituraF getLerC() {
        return lerC;
    }

    public void setLerC(LeituraF lerC) {
        this.lerC = lerC;
    }

    public LeituraF getLerV() {
        return lerV;
    }

    public void setLerV(LeituraF lerV) {
        this.lerV = lerV;
    }

    public CatProdutos getCp() {
        return Cp;
    }

    public void setCp(CatProdutos cp) {
        Cp = cp;
    }

    public CatClientes getCc() {
        return Cc;
    }

    public void setCc(CatClientes cc) {
        Cc = cc;
    }

    public CatVendas getCv() {
        return Cv;
    }

    public void setCv(CatVendas cv) {
        Cv = cv;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Faturacao getFat() {
        return fat;
    }

    public void setFat(Faturacao fat) {
        this.fat = fat;
    }

    public Crono getTempo1() {
        return tempo1;
    }

    public void setTempo1(Crono tempo1) {
        this.tempo1 = tempo1;
    }

    public Crono getTempo2() {
        return tempo2;
    }

    public void setTempo2(Crono tempo2) {
        this.tempo2 = tempo2;
    }

    public Crono getTempo3() {
        return tempo3;
    }

    public void setTempo3(Crono tempo3) {
        this.tempo3 = tempo3;
    }
}
