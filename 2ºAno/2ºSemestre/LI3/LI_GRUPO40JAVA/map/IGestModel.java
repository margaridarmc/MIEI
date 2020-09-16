import java.util.*;


public interface IGestModel
{
     Set<String> querie1aux();
     Triplo q2P1(int mes);
     Triplo q2P2(int fi) throws NaoExisteException;
     Triplo querie3aux(String cliente,int mes) throws NaoExisteException;
     Triplo querie4aux(String produto,int mes) throws NaoExisteException;
     Map<String,Integer> querie5aux(String cliente) throws NaoExisteException;
     Set<String> q6Aux (int f);
     Map<String,Double> querie7aux(int f) throws NaoExisteException;
     public Map<String,Integer> q8Aux (int f);
     public Map<String, Double> q9Aux(String produto,int n) throws NaoExisteException;
     public Map<Integer,Map<Integer,Double>> q10Aux(String prodDesejado) throws NaoExisteException ;
    
}
