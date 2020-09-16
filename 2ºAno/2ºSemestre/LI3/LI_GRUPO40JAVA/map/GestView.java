import java.math.BigDecimal;
import java.math.*;
import java.util.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GestView implements IGestView {


    /**
     * Imprime uma lista representativa de opçoes
     */
    public void showOptions(List<String> linhas) {
        for (String linha : linhas)
            System.out.println(linha);
        System.out.println("Escolha a opção:");
    }

    /**
     *Imprime dados dos ficheiros
     */
    public void imprimeSpecs(int a, int b, int c, int d,int e,double f){

        System.out.println("\n");
        System.out.println("CONSULTAS ESTATISTICAS:");
        System.out.println("\n");

        System.out.println("Nº total de diferentes produtos nao comprados: " + a);
        System.out.println("Nº total de diferentes produtos comprados: " +b);
        System.out.println("Nº de clientes que realizam compras: " + c);
        System.out.println("Nº de clientes que nao realizam compras: " + d);
        System.out.println("Total de compras de valor total igual a 0.0: " + e);
        System.out.println("Faturaçao total: " +f);

    }


    /**
     * imprime Tabela
     * @param a Faturação de todos os meses para a filial1 (key->mes , Value->fat)
     * @param b Faturação de todos os meses para a filial2 (key->mes , Value->fat)
     * @param c Faturação de todos os meses para a filial3 (key->mes , Value->fat)
     * @param d Nºde clientes distintos em todos os mesmo para a filial1 (key->mes , Value->nº clientes)
     * @param e Nºde clientes distintos em todos os mesmo para a filial2 (key->mes , Value->nº clientes)
     * @param f Nºde clientes distintos em todos os mesmo para a filial3 (key->mes , Value->nº clientes)
     * @param g
     */
   public void imprimeTable (Map<Integer,Double> a,Map<Integer,Double> b,Map<Integer,Double> c,Map<Integer,Double> d,Map<Integer,Double> e,Map<Integer,Double> f,Map<Integer,Integer> g){
        System.out.println("\n");
        System.out.println(" Nº de Clientes Distintos -->  NºC.D");
        System.out.println("\n");
       NumberFormat formatter = new DecimalFormat("#0.0");
       String format = "|%1$-15s|%2$-13s|%3$-13s|%4$-13s|%5$-13s|%6$-13s|%7$-13s|%8$-13s|%9$-13s|%10$-13s|%11$-13s|%12$-13s|%13$-13s|\n";
       System.out.format(format, "Filial\\Mês", "1", "2","3","4","5","6","7","8","9","10","11","12");
       System.out.println("==============================================================================================================================================================================================================");
       System.out.format(format, "1    Faturação", formatter.format(BigDecimal.valueOf(a.get(1))),formatter.format(BigDecimal.valueOf(a.get(2))),formatter.format(BigDecimal.valueOf(a.get(3))),formatter.format(BigDecimal.valueOf(a.get(4))),formatter.format(BigDecimal.valueOf(a.get(5))),formatter.format(BigDecimal.valueOf(a.get(6))),formatter.format(BigDecimal.valueOf(a.get(7))),formatter.format(BigDecimal.valueOf(a.get(8))),formatter.format(BigDecimal.valueOf(a.get(9))),formatter.format(BigDecimal.valueOf(a.get(10))),formatter.format(BigDecimal.valueOf(a.get(11))),formatter.format(BigDecimal.valueOf(a.get(12))));
              System.out.println("                  -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       System.out.format(format, "         NºC.D",d.get(1),d.get(2),d.get(3),d.get(4),d.get(5),d.get(6),d.get(7),d.get(8),d.get(9),d.get(10),d.get(11),d.get(12));
       System.out.println("=======================================================================================================================================================================================================================");
       System.out.format(format, "2    Faturação", formatter.format(BigDecimal.valueOf(b.get(1))),formatter.format(BigDecimal.valueOf(b.get(2))),formatter.format(BigDecimal.valueOf(b.get(3))),formatter.format(BigDecimal.valueOf(b.get(4))),formatter.format(BigDecimal.valueOf(b.get(5))),formatter.format(BigDecimal.valueOf(b.get(6))),formatter.format(BigDecimal.valueOf(b.get(7))),formatter.format(BigDecimal.valueOf(b.get(8))),formatter.format(BigDecimal.valueOf(b.get(9))),formatter.format(BigDecimal.valueOf(b.get(10))),formatter.format(BigDecimal.valueOf(b.get(11))),formatter.format(BigDecimal.valueOf(b.get(12))));
              System.out.println("                  ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       System.out.format(format, "         NºC.D", e.get(1),e.get(2),e.get(3),e.get(4),e.get(5),e.get(6),e.get(7),e.get(8),e.get(9),e.get(10),e.get(11),e.get(12));
       System.out.println("=================================================================================================================================================================================================================");
       System.out.format(format, "3    Faturação",formatter.format(BigDecimal.valueOf(c.get(1))),formatter.format(BigDecimal.valueOf(c.get(2))),formatter.format(BigDecimal.valueOf(c.get(3))),formatter.format(BigDecimal.valueOf(c.get(4))),formatter.format(BigDecimal.valueOf(c.get(5))),formatter.format(BigDecimal.valueOf(c.get(6))),formatter.format(BigDecimal.valueOf(c.get(7))),formatter.format(BigDecimal.valueOf(c.get(8))),formatter.format(BigDecimal.valueOf(c.get(9))),formatter.format(BigDecimal.valueOf(c.get(10))),formatter.format(BigDecimal.valueOf(c.get(11))),formatter.format(BigDecimal.valueOf(c.get(12))));
              System.out.println("                  ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       System.out.format(format, "         NºC.D",  f.get(1),f.get(2),f.get(3),f.get(4),f.get(5),f.get(6),f.get(7),f.get(8),f.get(9),f.get(10),f.get(11),f.get(12));
       System.out.println("===================================================================================================================================================================================================================");

       String ex[] = { "Total Compras",""+g.get(1),""+g.get(2),""+g.get(3),""+g.get(4),""+g.get(5),""+g.get(6),""+g.get(7),""+g.get(8),""+g.get(9),""+g.get(10),""+g.get(11),""+g.get(12)};

       System.out.format(String.format(format, (Object[]) ex));
        System.out.println("\n\n\n\n\n\n\n\n");
   }

    /**
     * Imprime uma lista com paginação
     * @param listagem
     * @param u
     */
    public void paginacao(Set<String> listagem, int u) {
        System.out.print("\n");
        int paginas = 0;
        int v = 0;
        for (String s : listagem) {
            if (v % 20 == 0) paginas++;
            v++;
        }
        int i = 20 * (u - 1);
        String[] array = listagem.toArray(new String[listagem.size()]);

        int conta = 0;


        while (i < listagem.size() && conta <= 10) {

            if (i + 1 < listagem.size()) {
                System.out.println((array[i]) + "         " + (array[i + 1]));
                i = i + 2;
                conta++;
            } else {
                System.out.println((array[i]));
                i++;
                conta++;
            }


        }
        System.out.println("                               Pagina" + u + "/" + paginas);
        System.out.println("                               Voltar para menu Queries: 0");
        System.out.println("                               Escolha pagina:");
        Scanner myObj = new Scanner(System.in);
        int pagin;
        int r = 0;
        while (r == 0) {
            try {
                pagin = Input.lerInt();
                if (pagin == 0) {
                    r = 1;
                } else {
                    if (pagin <= paginas) {
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        paginacao(listagem, pagin);
                        break;
                    } else {
                        System.out.println("INSIRA NUMERO VALIDO");


                    }

                }
            } catch (NumberFormatException | InputMismatchException a) {
                System.out.println("Input Inválido!");
                continue;
            }
        }
    }

    /**
     * imprime output da querie 3
     * @param t
     * @param mes
     */
     public void q3(Triplo t, int mes){

         NumberFormat formatter = new DecimalFormat("#0.00");
          System.out.println("No mes: " +mes + " o cliente comprou  "+t.getN1()+"  produtos,sendo "+ t.getN3()+"  diferentes, gastando "+ formatter.format(t.getN2())+ " euros");
         
    }

    /**
     * imprime output da querie 4
     * @param t
     * @param mes
     */
    public void q4(Triplo t, int mes){

        NumberFormat formatter = new DecimalFormat("#0.00");
       System.out.println("No mes: "+ mes + " o produto foi comprado  "+t.getN1()+" vezes por "+t.getN3() +"  clientes diferentes, gastando "+formatter.format(t.getN2())+" euros");
      
    }

    /**
     * imprime output da querie 5
     * @param m
     */
    public void q5(Map<String, Integer> m) {
        System.out.println("\n");
        Set<String> myset= new TreeSet<>();
        for (String s : m.keySet()) {
            myset.add("Produto :  " + s + "    Quantidade: " + m.get(s) );
        }
        paginacao(myset,1);
        System.out.println("\n");
    }

    /**
     * imprime output da querie 6
     * @param m
     */
    public void q6(Set<String> m) {
        for (String s : m) {
            System.out.println(s);
        }
        System.out.println("\n");
    }

    /**
     * imprime output da querie 7
     * @param m
     */
    public void q7( Map<String,Double> m) {

        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.println("\n");
        for (String s : m.keySet()) {
            System.out.println("Cliente : " + s + " valor gasto: " + formatter.format(m.get(s))) ;}
        System.out.println("\n");
    }

    /**
     * imprime output da querie 8
     * @param m
     */
    public void q8(Map<String, Integer> m) {
        System.out.println();
        for (String s : m.keySet()) {
            System.out.println("Cliente: " + s + "      comprou o nºde produtos diferentes: " + m.get(s));
        }
        System.out.println("\n");

    }

    /**
     * imprime output da querie 9
     * @param sorted
     */
    public void q9(Map<String, Double> sorted){

        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.println();
           System.out.println("CLIENTES:          VALOR TOTAL GASTO:");
                for (Map.Entry<String, Double> entry : sorted.entrySet()) {
                 System.out.println(entry.getKey()+"               "+formatter.format(entry.getValue()));
                 
            }
        System.out.println("\n");
        }

    /**
     * imprime output da querie 10
     * @param prodDesejado
     * @param resultado
     */
    public void q10(String prodDesejado, Map<Integer,Map<Integer,Double>> resultado ){
        System.out.println();
            int mese=1;int i ;
            NumberFormat formatter = new DecimalFormat("#0.00");  
            System.out.println("FATURAÇÃO DO PRODUTO "+ prodDesejado +" POR MÊS E FILIAL:");
            System.out.println("Mes\\Filial  |      1       |      2        |      3       |");
            
          for(Integer b : resultado.keySet()){//PRIMIRO POR MES
             //System.out.println(b);
            Map<Integer,Double> aux = new HashMap<>();
            aux = resultado.get(b);
            if(b==mese){
                System.out.print("     "+b+"        ");
                for(i=1;i<4;i++){ 
                    if(aux.get(i)==null){
                                 System.out.print("       0        ");
               
                    }
                    else {
                                System.out.print("   "+formatter.format(aux.get(i))+"  ");
                    }
                    }
            }
            else {
                for(;b>mese;mese++){
                System.out.println("     "+mese+"             0              0               0      ");}

                 
                System.out.print("     "+b+"      ");
                for(i=1;i<4;i++){ 
                    if(aux.get(i)==null){
                                 System.out.print("       0        ");
               
                    }
                    else {
                                System.out.print("   "+formatter.format(aux.get(i))+"  ");
                    }
                    }
                    
            }
        
            mese++;System.out.println("");
          }
        
          for(;mese<13;mese++){ 
            System.out.println("     "+mese+"             0              0               0      ");;
           }

        System.out.println("\n");
    } 
    
}

