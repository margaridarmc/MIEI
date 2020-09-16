import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * class leitura dos ficheiros
 */
public class LeituraHistorico implements Serializable {
    /**
     *  atributos
     *
     */
    Proprietario proprietario;
    private List<String> l;
    private Map<String,Cliente> cli;
    private Map<String,Proprietario> prop;
    private Map<String,Veiculo> vei;
    private Set<String[]> al;
    private List<String> linhas;
    private Map<String,Integer> classificacoes;

    /**
     * construtor
     */
    public LeituraHistorico() {
        proprietario = new Proprietario();
        this.l = new ArrayList<>();
        this.cli = new HashMap<>();
        this.prop = new HashMap<>();
        this.vei= new HashMap<>();
        this.al = new HashSet<>();
        this.linhas = new ArrayList<>();
        this.classificacoes = new HashMap<>();
    }

    /**
     * le ficheiro com o nome da String do parametro
     * @param fichtxt
     */
    public void ler_With_Files(String fichtxt)  {
        try {
            linhas = Files.readAllLines(Paths.get(fichtxt));
        } catch (IOException exc) {
            out.println(exc);
        }
    }

    /**
     * get
     * @return
     */
    public List<String> getLinhas() {
        List<String> res = new ArrayList<>();
        res.addAll(this.linhas);
        return res;
    }

    /**
     * set
     * @param linhas
     */
    public void setLinhas(List<String> linhas) {
        this.linhas = linhas;
    }

    /**
     * faz a leitura do ficheiro original e adiciona-o ao sistema separando em classes e os seus atributos
     */
    public  void ler()  {
        ler_With_Files("logsPOO_carregamentoInicial.bak");
        l = getLinhas();
        Proprietario pro;

        for (int i = 0; i < l.size(); i++) {

            for (String s : l.get(i).split(":")) {

                if (s.compareTo("NovoProp") == 0) {
                    String[] parts = l.get(i).split(":|,");
                    Proprietario p = new Proprietario(parts[1], parts[2], parts[3], parts[4], parts[2]);
                    prop.put(parts[2], p);
                } else if (s.compareTo("NovoCliente") == 0) {
                    String[] parts = l.get(i).split(":|,");
                    Cliente c = new Cliente(parts[1], parts[2], parts[3], parts[4], Double.parseDouble(parts[5]), Double.parseDouble(parts[6]), parts[2]);
                    cli.put(parts[2], c);
                } else if (s.compareTo("NovoCarro") == 0) {
                    String[] parts = l.get(i).split(":|,");

                    switch (parts[1]) {
                        case "Gasolina":
                            CarroGasolina cG = new CarroGasolina(parts[2], parts[3], Double.parseDouble(parts[5]), Double.parseDouble(parts[6]), Double.parseDouble(parts[9]), Double.parseDouble(parts[10]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                            for (Map.Entry<String, Proprietario> entry : prop.entrySet()) {
                                pro = new Proprietario();
                                pro = entry.getValue();
                                
                                if (pro.getNif().equals(parts[4])) {
                                    pro.addCarro(cG.clone());
                                    vei.put(cG.getMatricula(), cG.clone());
                                }
                                
                            }   break;
                        case "Electrico":
                            CarroEletrico cE = new CarroEletrico(parts[2], parts[3], Double.parseDouble(parts[5]), Double.parseDouble(parts[6]), Double.parseDouble(parts[9]), Double.parseDouble(parts[10]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                            for (Map.Entry<String, Proprietario> entry : prop.entrySet()) {
                                pro = new Proprietario();
                                pro = entry.getValue();
                                
                                if (pro.getNif().equals(parts[4])) {
                                    pro.addCarro(cE.clone());
                                    vei.put(cE.getMatricula(), cE.clone());
                                }
                                
                            }   break;
                        case "Hibrido":
                            CarroHibrido cH = new CarroHibrido(parts[2], parts[3], Double.parseDouble(parts[5]), Double.parseDouble(parts[6]), Double.parseDouble(parts[9]), Double.parseDouble(parts[10]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                            for (Map.Entry<String, Proprietario> entry : prop.entrySet()) {
                                pro = new Proprietario();
                                pro = entry.getValue();
                                
                                if (pro.getNif().equals(parts[4])) {
                                    pro.addCarro(cH.clone());
                                    vei.put(cH.getMatricula(), cH.clone());
                                }
                            }   break;
                        default:
                            break;
                    }

                } else if (s.compareTo("Aluguer") == 0) {
                    String[] parte = l.get(i).split(":|,");
                    //String[] alug={parte[1],parte[2],parte[3],parte[4],parte[5]};
                    al.add(parte);
                } else if (s.compareTo("Classificar") == 0) {
                    String[] parts = l.get(i).split(":|,");
                    classificacoes.put(parts[1], Integer.parseInt(parts[2]));
                }
            }
        }

    }

    /**
     * gets e sets
     *
     */
    public Map<String, Cliente> getCli() {   
       return this.cli.entrySet().stream()
                                 .collect(Collectors.toMap( e -> e.getKey(),e -> (Cliente)(e.getValue().clone()))); 
                                 
      }

    public Map<String, Proprietario> getProp() {
        return this.prop.entrySet().stream()
                                 .collect(Collectors.toMap( e -> e.getKey(),e -> (Proprietario)(e.getValue().clone())));   
    }

    public Map<String,Integer> getClassificacoes() {
        return this.classificacoes.entrySet().stream()
                                 .collect(Collectors.toMap( e -> e.getKey(),e -> (Integer)(e.getValue())));  
    }

    public Map<String,Veiculo> getVei() {
        return this.vei.entrySet().stream()
                                 .collect(Collectors.toMap( e -> e.getKey(),e -> (Veiculo)(e.getValue().clone())));   
    }
    
    public Set<String[] > getAl(){
        Set<String[]> res = new HashSet<>();
        res.addAll(this.al);
        return res;
    }
    
}
