import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.awt.Point;

/**
 * calss conntroller
 */
 public class Controller{
     /**
      * Atributos
      */
    private Model model;
    private View view;
    
    /**
     * getters e setters
     * 
     */
    public Model getModel() {
        return model;
    }
    
    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
    
    
    /**
     * Inicia o primeiro menu onde executa todo o programa
     * @throws NoSuchElementException
     * @throws PasswordErradaException
     * @throws ElemNotFoundException
     * @throws IOException 
     */
    public void controllerInit() throws NoSuchElementException,PasswordErradaException , ElemNotFoundException ,IOException { 
        System.out.print('\u000C');
        boolean flag = true;
        int opcao;
        try (Scanner scan = new Scanner(System.in)) {
            ReadWriteFichBinario emp = new ReadWriteFichBinario();
            List<String> opcoes = Arrays.asList(new String[]{
                "1:Ler Carregamento inicial ",
                "2:Ler ultimo estado",
                "3:Ir para a App",
                "0:Sair e guardar o atual estado"});
            
            while (flag) {
                try{
                    view.showOptions(opcoes);
                    opcao = Integer.parseInt(scan.nextLine());
                    
                    System.out.print('\u000C');
                    switch (opcao) {
                        case 1:
                            model.initModel();
                            break;
                        case 2:
                            this.model= (Model)emp.lerArquivoBinario("binario.txt");
                            break;
                        case 3:
                            MenuPrincipal();
                            break;
                        case 0:
                            emp.gravarArquivoBinario(this.model,"binario.txt");
                            flag=false;
                            break;
                        default:
                            break;
                    }
                }
                catch(NoSuchElementException|NumberFormatException e){
                    System.out.print('\u000C');
                    System.out.println("Input invalido");
                    
                }
            }   }
   }
    /**
     * Segundo menu
     * @throws PasswordErradaException
     * @throws ElemNotFoundException
     * @throws IOException 
     */
   
   public void MenuPrincipal() throws PasswordErradaException , ElemNotFoundException ,IOException  {
        //System.out.print('\u000C');
        //view.logotipo();
        boolean flag = true;
        int opcao;
        Scanner scan = new Scanner(System.in);
        List<String> opcoes = Arrays.asList(new String[]{ "******UMCarroJa******","1:Iniciar Sessao ", "2:Criar Conta","3:Rankings", "0:Sair"});
        while (flag) {
            
            try{
                view.showOptions(opcoes);
                opcao = Integer.parseInt(scan.nextLine());
                System.out.print('\u000C');
            switch (opcao) {
                case 1:
                    IniciarSessao();
                    break;
                case 2:
                    CriarConta();
                    break;
                case 3:
                    view.showTop10(model.top10(),1);
                    view.showTop10(model.top102(),2);
                    break;
                case 0:
                    flag = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao Invalida.");
                    break;
            }
        }
        catch(NumberFormatException a){
            System.out.print('\u000C');
            System.out.println("Input Errado!");
        }
        catch (ElemJaExisteException a){
            System.out.print('\u000C');
            System.out.println(a);
        }
        }
        scan.close();
    }
   
   /**
    * menu onde se inicia sessão
    * @throws IOException
    * @throws ElemJaExisteException 
    */
     public void IniciarSessao() throws IOException,ElemJaExisteException{
        System.out.print('\u000C');
       
        Utilizador user=null; 
        try(Scanner scan = new Scanner(System.in)) {
            try{
                System.out.println("Email");
                String email = scan.next();
                System.out.println("Password");
                String password = scan.next();
                
                user= model.iniciarSessao(email,password);
                if(user!=null){
                    if (user instanceof Cliente){System.out.print('\u000C'); MenuCliente((Cliente) user);}
                    else if (user instanceof Proprietario) MenuProprietario((Proprietario) user);
                    else System.out.println("Erro");
                }
                else System.out.println("Voce nao esta registado!");
            }
            catch (PasswordErradaException | ElemNotFoundException | NumberFormatException a){
                System.out.print('\u000C');
                System.out.println(a);
                
            }
       }
        }
      
       /**
        * menu de um determinado cliente após o inicio de sessão
        * @param cliente
        * @throws NumberFormatException
        * @throws ElemNotFoundException 
        */
    public void MenuCliente(Cliente cliente) throws NumberFormatException,ElemNotFoundException{
        System.out.print('\u000C');
        int opcao = 0;
        int opcao2=0;
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        //System.out.println(cliente.getAluguer().size());
        List<String> cli = Arrays.asList(new String[]{"***MenuCliente***", "1:Perfil","2.Alugar Carro","0:Logout"});
        while (flag) {
            try{
                view.showOptions(cli);
                opcao = Integer.parseInt(input.nextLine());
                System.out.print('\u000C');
                if (opcao == 1) {   view.showCli(cliente);
                                    opcao2 = Integer.parseInt(input.nextLine());                    
                                    //if(opcao2== 3) cliente.hist.detalhesHistorico();
                                } 
                else if (opcao==2 || opcao2==2) { alugar(cliente);}
                else if (opcao == 0 || opcao2==0) {flag=false;}
                else {System.out.println("Opcao Invalida!");   }
            }
            catch(NumberFormatException f) {
                System.out.print('\u000C');
                System.out.println("Input Invalido");
            }
        }
    }
    
   
    /**
     * menu de um determinado proprietario após o inicio de sessão
     * @param prop
     * @throws NumberFormatException
     * @throws ElemJaExisteException 
     */
    public void MenuProprietario(Proprietario prop) throws NumberFormatException,ElemJaExisteException{
           
        System.out.print('\u000C');
        //System.out.println("tamanho: " +prop.getCarros().size());
        int opcao = 0;
        int opcao2 =0;
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        List<String> propMenu = Arrays.asList(new String[]{"***MenuProprietario***", "1:Perfil","2:Registar novo carro","3:Ver alugueres propostos","4:Alterar preço de um carro" 
            ,"0:Logout"});
        while (flag) {
            try{
                view.showOptions(propMenu);
                opcao = Integer.parseInt(input.nextLine());
                System.out.print('\u000C');
                if (opcao == 1) {   view.showprop(prop);
                                    opcao2 = Integer.parseInt(input.nextLine());
                                } 
                                
                else if(opcao==2 || opcao2==2) { regCarro(prop);}
                else if(opcao==3){
                    
                    System.out.println("Clique em S para aceitar e N para recusar");
                    model.aceitarAluguer(prop.clone());
                    
                    System.out.println("Nao existem propostas de algueres");
            }
                else if(opcao == 4){  
                        int c=view.showC(prop.getCarros());
                        System.out.println("Digite o novo preco: ");
                        double p = scan.nextDouble();
                        model.alteraPreco(prop.getCarros().get(c-1).getMatricula(),p,prop.getNif(),c-1);
                    }
                   
                
                else if (opcao == 0 || opcao2==0) {flag=false;} 
                else {System.out.println("Opcao Invalida!");   }
            }
            catch(NumberFormatException f) {
                System.out.print('\u000C');
                System.out.println("Input invalido");
            }
        }
    }
    
    /**
     * menu onde se efetuam os alugueres e onde se verifica as suas condições
     * @param cli
     * @throws ElemNotFoundException 
     */
    public void alugar(Cliente cli)throws ElemNotFoundException{
        System.out.print('\u000C');
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        int opcao=0;
        System.out.println("******MenuAlugarVeiculo******");
        
        System.out.println("Coordenadas x e y para onde vai:");
        try{
        double x2=input.nextDouble(); double y2=input.nextDouble();
        Point.Double fim= new Point.Double(x2,y2);
        
        List<String> filtros = Arrays.asList(new String[]{" ","*Escolher filtros:*","1:Carro mais barato","2: Carro mais barato dentro de uma determinada distancia", "3: Carro mais proximo",
                                                          "4:Escolher tipo de Carro","5:Carro em especifico","0:Sair"});
        view.showOptions(filtros);
        opcao = input.nextInt();
        boolean b=false;
        Aluguer a=new Aluguer();
       // Proprietario p = new Proprietario();
            switch (opcao) {
                case 1:
                    a=model.carroB(cli,fim);
                    break;
                case 2:
                    System.out.println("Qual e a distancia maxima que esta disposto/a a percorrer a pe? ");
                    int dist=input.nextInt();
                    a=model.carroBD(cli,fim,dist);
                    break;
                case 3:
                    a=model.carroP(cli,fim);
                    break;
                case 4:
                    List<String> carros = Arrays.asList(new String[]{" ","*Escolha o tipo de carro:*","1:Gasolina", "2:Hibrido","3:Eletrico"});
                    view.showOptions(carros);
                    int op=input.nextInt();
                    a=model.carroA(op,cli,fim);
                    break;
                case 5:
                    Scanner i= new Scanner(System.in);
                    System.out.println("Digite a matricula do carro que pretende alugar: ");
                    String mat=i.nextLine();
                    a=model.carroE(mat,cli,fim);
                    i.close();
                    break;
                default:
                    break;
            }
                       
        input.close();
        if(!(a.getCarro().equals("n/a"))){
            try (Scanner in = new Scanner(System.in)) {
                System.out.print('\u000C');
                view.informacoesAluguer(a);
                System.out.println("Quer solicitar o aluguer? [S/N] ");
                String s = "n/a";
                s=in.nextLine();
                
                if(s.equals("s") || s.equals("S") ) {
                    String n =model.solicitaA(a);
                    //System.out.println("nif Proprietario :"+ n);
                }
            }
        }
        
    }catch(ElemNotFoundException b) { System.out.print('\u000C');System.out.println(b);}
    }
    
    /**
     * menu onde se pode criar uma conta tanto de proprietario como de cliente tendo como requesito este já não estar no sistema
     * @throws PasswordErradaException
     * @throws ElemNotFoundException
     * @throws IOException
     * @throws ElemJaExisteException 
     */
    public void CriarConta() throws PasswordErradaException,ElemNotFoundException, IOException,ElemJaExisteException{
        System.out.print('\u000C');
        int opção;
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        List<String> linhas = Arrays.asList(new String[]{"******UMCarroJa******", "1:Conta Cliente", "2:Conta Propritario","0:Sair"});
        
        while (flag) {
            try{
                view.showOptions(linhas);
                opção = Integer.parseInt(input.nextLine());
                System.out.print('\u000C');
                switch (opção) {
                    case 1:
                        criarContaCliente();
                        flag = false;
                        break;
                    case 2:
                        criarContaProprietario();
                        flag = false;
                        break;
                    case 0:
                        flag = false;
                        break;
                    default:
                        System.out.println("Opçao invalida");
                        break;
                }
            }
            catch(NumberFormatException e) {
                System.out.println("Input errado");
            }
             catch (ElemJaExisteException a){
            System.out.print('\u000C');
            System.out.println(a);
        }
        }

    }
    
    /**
     * cria a conta cliente
     * @throws PasswordErradaException
     * @throws ElemNotFoundException
     * @throws IOException
     * @throws ElemJaExisteException 
     */
    public void criarContaCliente() throws PasswordErradaException, ElemNotFoundException ,IOException,ElemJaExisteException{
        try{
         System.out.print('\u000C');
         Scanner scan = new Scanner(System.in);
         
         System.out.print("Digite o seu nome: ");
         String nome=scan.nextLine();
         
         System.out.print("Digite o seu email: ");
         String email=scan.nextLine();
         
         System.out.print("Digite o seu nif: ");
         String nif =scan.nextLine();
         
         System.out.print("Digite o seu password: ");
         String pass =scan.nextLine();
         
         System.out.print("Digite a sua morada: ");
         String morada=scan.nextLine();
         System.out.print("Inidique a sua localizaçao atual: (x e y)");
         double x = scan.nextDouble();
         double y = scan.nextDouble();
         
          model.addCli(nome,nif,email,morada,x,y,pass);
         System.out.print('\u000C');
         MenuPrincipal();
        }catch(InputMismatchException | NumberFormatException e){
            System.out.print('\u000C');
            System.out.println("Input errado");
        }
      }
      
    /**
     * cria a conta proprietario
     * @throws PasswordErradaException
     * @throws ElemNotFoundException
     * @throws IOException
     * @throws ElemJaExisteException 
     */
     public void criarContaProprietario() throws PasswordErradaException,ElemNotFoundException,IOException,ElemJaExisteException{
         try{
         System.out.print('\u000C');
         Scanner scan = new Scanner(System.in);
         
         System.out.print("Digite o seu nome: ");
         String nome=scan.nextLine();
         
         System.out.print("Digite o seu email: ");
         String email=scan.nextLine();
         
         System.out.print("Digite o seu nif: ");
         String nif =scan.nextLine();
         
         System.out.print("Digite a sua password: ");
         String pass =scan.nextLine();
         
         System.out.print("Digite a sua morada: ");
         String morada=scan.nextLine();
         
         
         model.addProp(nome,nif,email,morada,pass);
         System.out.println("Registo efetuado com sucesso!");
         System.out.print('\u000C');
         MenuPrincipal();
        }catch(InputMismatchException | NumberFormatException e){
            System.out.print('\u000C');
            System.out.println("Input errado");
        }
        catch(ElemJaExisteException e){
            System.out.print('\u000C');
            System.out.println(e);
        }
     }
        
     /**
      * Metodo que apresenta os meios a registar um novo carro no sistema desde que este já lá não esteja presente
      * @param prop
      * @throws ElemJaExisteException 
      */
     private void regCarro (Proprietario prop) throws ElemJaExisteException {
        System.out.print('\u000C');
        Scanner scan = new Scanner(System.in);
        int opcao;
        boolean flag = true;
        System.out.println("Qual o seu tipo de carro?");
        List<String> linhas = Arrays.asList(new String[]{"1:Carro Gasolina", "2:Carro Eletrico","3:Carro Hibrido","0:Sair"});
        
        view.showOptions(linhas);
        
        OUTER:
       while (flag) {
            try {
                opcao = Integer.parseInt(scan.nextLine());
                System.out.print('\u000C');
                switch (opcao) {
                    case 0:
                        flag=false;
                        break;
                    case 1:
                    case 2:
                    case 3:
                        System.out.println("Indique a marca do veiculo: ");
                        String marca = scan.nextLine();
                        System.out.println("Indique a matricula do veiculo: ");
                        String matricula = scan.nextLine();
                        System.out.println("Indique a velocidade media: ");
                        double velocidade = scan.nextDouble();
                        flag=true;
                        System.out.println("Indique o preço que quer cobrar por kilometro: ");
                        double preco = scan.nextDouble();
                        System.out.println("Indique as coordenadas do seu veiculo: (x e y) ");
                        double x = scan.nextDouble();
                        double y = scan.nextDouble();
                        System.out.println("Indique a autonomia do seu veiculo");
                        double autonomia = scan.nextDouble();
                        System.out.println("Indique o consumo medio do seu veiculo");
                        double consumo = scan.nextDouble();
                        model.addCarro( opcao,marca, matricula, velocidade,preco, x, y,autonomia,consumo,prop.getNif());
                        break OUTER;
                    default:
                        System.out.println("opcao Invalida");
                        break;
                }
            }catch (NumberFormatException| InputMismatchException a) {System.out.print('\u000C'); System.out.println("InputInvalido");}catch(ElemJaExisteException e){
                System.out.print('\u000C');
                System.out.println(e);
            }
        }
    }

}
