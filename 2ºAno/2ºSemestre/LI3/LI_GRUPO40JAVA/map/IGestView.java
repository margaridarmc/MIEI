import java.util.*;

public interface IGestView
{
   void showOptions(List<String> linhas);
   void imprimeSpecs(int a, int b, int c, int d,int e,double f);
   void imprimeTable (Map<Integer,Double> a,Map<Integer,Double> b,Map<Integer,Double> c,Map<Integer,Double> d,Map<Integer,Double> e,Map<Integer,Double> f,Map<Integer,Integer> g);
   void paginacao(Set<String> listagem, int u);
   void q3(Triplo t, int mes);
   void q4(Triplo t, int mes);
   void q5(Map<String, Integer> m) ;
   void q6(Set<String> m);
   void q7( Map<String,Double> m);
   void q8(Map<String, Integer> m);
   void q9(Map<String, Double> sorted);
   void q10(String prodDesejado, Map<Integer,Map<Integer,Double>> resultado );
}
