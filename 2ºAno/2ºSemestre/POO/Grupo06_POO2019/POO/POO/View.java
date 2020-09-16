import java.util.*;
public class View
{
    /*
    public void logotipo(){
        System.out.println("888     888 888b     d888                                        888888          ");
        System.out.println("888     888 8888b   d8888                                          '88b          ");
        System.out.println("888     888 88888b.d88888                                           888          ");
        System.out.println("888     888 888Y88888P888  .d8888b  8888b.  888d888 888d888 .d88b.  888  8888b.  ");
        System.out.println("888     888 888 Y888P 888 d88P'        '88b 888P'   888P'  d88''88b 888     '88b ");
        System.out.println("888     888 888  Y8P  888 888      .d888888 888     888    888  888 888 .d888888 ");
        System.out.println("Y88b. .d88P 888   '   888 Y88b.    888  888 888     888    Y88..88P 88P 888  888 ");
        System.out.println(" 'Y88888P' 888       888  'Y8888P 'Y888888 888     888     'Y88P'  888 'Y888888 ");
        System.out.println("                                                                  .d88P          ");
        System.out.println("                                                                .d88P'           ");
        System.out.println("                                                               888P'");
    }*/


    /**
     * Menus das opcoes iniciais
     * @param linhas
     */
    public void showOptions(List<String> linhas) {
        for (String linha : linhas)
            System.out.println(linha);
        System.out.println("Escolha a opção:");
    }

    /**
     * Menu cliente
     * @param cli
     */
    public void showCli(Cliente cli){
        Scanner scan = new Scanner(System.in);
        System.out.println("***PERFIL DO CLIENTE***");
        System.out.println("Nome: "+cli.getNome());
        System.out.println("Morada: "+cli.getMorada());
        System.out.println("Email: "+cli.getEmail());
        System.out.println("Local onde se encontra: "+cli.getLocalizacao());
        if(cli.getClassificacaoC().size()>0){
        System.out.println("Classificaçao atual: "+cli.mediaClassificacao());}
        System.out.println("H:Ver Historico De Alugueres");
        String s=scan.nextLine();
        if(s.equals("h") || s.equals("H")) {showAlugures(cli.getAluguer());}
    }

    /**
     * Prints dos alugueres
     * @param la
     */
    private void showAlugures(Set<Aluguer> la){
        if( la.size()>0){
        for(Aluguer a:la){
            System.out.println("Carro: "+a.getCarro()+" Duracao: "+a.getDuracao()+ " Preco : "+a.getPreco());
        }
    }
        else {System.out.println("Nenhum aluguer realizado");}
        System.out.println();
        System.out.println("0: Sair");
    }

    /**
     * Perfil do proprietario
     * @param prop
     */
    public void showprop(Proprietario prop){
        Scanner scan = new Scanner(System.in);
        System.out.println("***Perfil Do Proprietario***");
        System.out.println("Nome:"+prop.getNome());
        System.out.println("Morada:"+prop.getMorada());
        System.out.println("Email:"+prop.getEmail());
        System.out.println("C:Ver Lista De Carros");
        if(prop.getClassificacaoP().size()>0){
        System.out.println("Classificaçao atual: "+prop.mediaClassificacao());}
        System.out.println("H:Ver Historico De Alugueres");
        String s=scan.nextLine();
        if(s.equals("h") || s.equals("H")) {showAlugures(prop.getAluguerF());}
        if(s.equals("c") || s.equals("C")) {showCarros(prop.getCarros());}
    }

    /**
     * Prints dos carros
     * @param lc
     */
    private void showCarros(List<Veiculo> lc){
        for(Veiculo c:lc){
            if(c.getClassificacaoV().size()>0){
            System.out.println("Matricula: "+c.getMatricula()+" Marca: "+c.getMarca()+ " Velocidade : "+c.getVelocidade()
                               +"Classificacao: " +c.mediaClassificacao() + "Localizacao: " +c.getLocalizacao());}
            else { System.out.println("Matricula: "+c.getMatricula()+" Marca: "+c.getMarca()+ " Velocidade : "+c.getVelocidade()
                               +"Classificacao: " + "(carro nunca alugado)" + "Localizacao: " +c.getLocalizacao());}
                            }
    
   }

    /**
     * Prints de carros
     * @param lc
     * @return
     */
    public int showC (List<Veiculo> lc){
        System.out.println("Escolha o carro");
        Scanner scan = new Scanner(System.in);
        int i=1;
         for(Veiculo c:lc){
            System.out.println(i +": "+c.getMatricula());
            i++;
        }
        int op=scan.nextInt();
        return op;
    }

    /**
     * informacoes sobre um determinado aluguer
     * @param al
     */
    public void informacoesAluguer(Aluguer al){
        System.out.println("***ViagemInfo***");
        System.out.println("Matricula do carro:" + al.getCarro());
        System.out.println("Nif Proprietario:" +al.getProprietario());
        System.out.println("Condiçoes da estrada: "+ al.getTemp() +"   Duraçao da Viagem: " + al.getDuracao());
        System.out.println("Preco da Viagem: " + al.getPreco());
        System.out.println("Distancia que devera percorrer a pe: " + al.getDPe());
        
    }

    /**
     * Ranking de clientes por distancia percorrida ou por numero de alugueres
     * @param cli
     * @param i
     */
    public void showTop10(Set<Cliente> cli,int i){
       if(i==1) System.out.println("*****10 clientes com mais km percorridos*****");
       else if (i==2) System.out.println("*****10 clientes que mais alugueres fizeram*****");
        for(Cliente c: cli) {
            System.out.println("Nome: "+c.getNome()+" Nif: "+c.getNif());
        }
        
       System.out.println();
    }
}