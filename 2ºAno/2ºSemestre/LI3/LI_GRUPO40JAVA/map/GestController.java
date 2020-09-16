import java.util.*;
import java.io.*;

public class GestController implements IGestController{
    private GestModel model;
    private GestView view;
    private static final String clean = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";


    public GestModel getModel() {
        return model;
    }
    public void setModel(GestModel model) {
        this.model = model;
    }
    public GestView getView() {
        return view;
    }
    public void setView(GestView view) {
        this.view = view;
    }

    /**
     * Menu principal
     */
    public void menu() {
        System.out.print(clean);
        boolean flag = true;
        int opcao;
        List<String> opcoes = Arrays.asList(new String[]{"*******Sistema de Gestao de Vendas*******",
                "1:Ler Ficheiros",
                "2:Queries",
                "0:Sair"});

        while (flag) {
            try {
                view.showOptions(opcoes);
                opcao = Input.lerInt();
                if (opcao == 1) {
                    lerFicheiros();
                } else if (opcao == 2) {
                    queries();
                } else if (opcao == 0) {
                    flag = false;
                    System.out.println("Saindo...");
                } else {
                    System.out.println("Opcao Invalida.");
                }
            } catch (NumberFormatException | InputMismatchException a) {
                System.out.print('\u000C');
                System.out.println(a);
            }
        }
    }

    /**
     * Escolhe e lê o ficheiro de vendas pretendido
     */
    public void lerFicheiros() {

        System.out.print(clean);
        boolean flag = true;
        Crono tempo = new Crono();
        Crono tempo2 = new Crono();
        Crono tempo3 = new Crono();
        int opcao;
        List<String> opcoes = Arrays.asList(new String[]{
                "Qual o ficheiro de Vendas que deseja ler?",
                "1:Vendas1M.txt",
                "2:Vendas3M.txt",
                "3:Vendas5M.txt"});

        String t1 = model.leP();
        System.out.println("Tempo de leitura e criaçao do Catalogo de Produtos:  " + t1 + "     tamanho do catalogo : " + model.getCp().clone().getCatP().size());

        String t2 = model.leC();
        System.out.println("Tempo de leitura e criaçao do Catalogo de Clientes:  " + t2 + "     tamanho do catalogo : " + model.getCc().clone().getCatalogoC().size());

        while (flag) {
            try {
                view.showOptions(opcoes);
                opcao = Input.lerInt();

                if (opcao == 1 || opcao == 2 || opcao == 3) {
                    String t3 = model.leV(opcao);
                    System.out.println("Tempo de leitura e criaçao do Catalogo de Vendas:  " + t3 + "     tamanho do catalogo : " + model.getCv().clone().getCatVendas().size());

                    flag = false;
                }


                if (opcao == 1) {
                    System.out.println("Nº total de vendas erradas: " + (1000000 - model.getCv().clone().getCatVendas().size()));
                } else if (opcao == 2) {
                    System.out.println("Nº total de vendas erradas: " + (3000000 - model.getCv().clone().getCatVendas().size()));
                } else
                    System.out.println("Nº total de vendas erradas: " + (5000000 - model.getCv().clone().getCatVendas().size()));

            } catch (NumberFormatException a) {
                System.out.println(clean);
                System.out.println(a);
            }
        }
        model.lerFileFat();
        int size = model.querie1aux().size();
        view.imprimeSpecs(size,(model.getCp().clone().getCatP().size() - size),model.clientesCompras(),(model.getCc().clone().getCatalogoC().size() - model.clientesCompras()),model.precoZero(),model.fatTotal());
        view.imprimeTable(model.tableF(1),model.tableF(2),model.tableF(3),model.tableC(1),model.tableC(2),model.tableC(3),model.comprasMes());
        

    }

    /**
     * Menu das queries
     */
    public void queries() {
        System.out.print(clean);
        boolean flag = true;
        int opcao;
        List<String> opcoes = Arrays.asList(new String[]{"*******Sistema de Gestao de Vendas*******",
                "1:Lista ordenada dos produtos nunca comprados",
                "2:Numero total global de vendas e numero total de clientes que as fizeram",
                "3:Numero de compras feitas por um dado cliente e respetivo total",
                "4:Numero de vezes que um dado produto foi comprado,total faturado e numero de clientes que o compraram",
                "5:Lista de produtos mais comprados",
                "6:Numero de produtos mais vendidos e total de clientes",
                "7:Lista dos 3 maiores compradores por filial",
                "8:Lista de clientes que compraram mais produtos",
                "9:Conjunto de clienyes que mais compraram e o valor gasto",
                "10:Faturacao total de cada produto",
                "0:Sair"});

        while (flag) {
            try {
                System.out.print(clean);
                view.showOptions(opcoes);
                opcao = Input.lerInt();
                switch (opcao) {
                    case 1:
                        querie1();
                        break;
                    case 2:
                        querie2();
                        break;
                    case 3:
                        querie3();
                        break;
                    case 4:
                        querie4();
                        break;
                    case 5:
                        querie5();
                        break;
                    case 6:
                        querie6();
                        break;
                    case 7:
                        querie7();
                        break;
                    case 8:
                        querie8();
                        break;
                    case 9:
                        querie9();
                        break;
                    case 10:
                        querie10();
                        break;
                    case 0:
                        flag = false;
                        System.out.println(clean);
                        break;
                    default:
                        System.out.println("Opcao Invalida.");
                        break;
                }

            } catch (NumberFormatException | InputMismatchException a) {
                System.out.println(clean);
                System.out.println(a);
            }
        }

    }

    /**
     * querie 1
     */
    public void querie1() {

        System.out.println(clean);
        Set<String> lista = model.querie1aux();

        view.paginacao(lista, 1);

    }

    /**
     * querie 2
     */
    public void querie2() {
        int inp = 1;
        System.out.print(clean);
        System.out.println("Introduza um mes:");
        int mes = 0;
        while (mes < 1 || mes > 12) {
            try {
                mes = Input.lerInt();
                if (mes < 1 || mes > 12) {
                    System.out.println("Mês inválido, introduza um valor de 1 a 12.");
                    System.out.println("Introduza um mês válido: ");
                }

            } catch (NumberFormatException a) {
                System.out.println("Valor errado");
            }
        }

        Triplo t = model.q2P1(mes);
        System.out.println("Total de vendas realizadas: " + t.getN1());
        System.out.println("Total de clientes distintos: " + (int) t.getN2());

        int fi = 0;
        System.out.println("Indique a filial: ");
        boolean flag = true;
        while (flag) {
            try {
                fi = Input.lerInt();
                Triplo tt = model.q2P2(fi);

                System.out.println("Total de vendas realizadas na filial: " + (int) tt.getN2());
                System.out.println("Total de clientes distintos na filial: " + tt.getN1());
                flag = false;


            } catch (NaoExisteException a) {
                System.out.println(a);
            }

        }

        int ii = 1;
        System.out.println("0:Sair");
        while(ii!=0){
            try {
                ii = Input.lerInt();
            }catch(NumberFormatException a){ System.out.println("Opcao invalida");}
        }
    }


    /**
     * querie 3
     */
    public void querie3() {
        int inp = 1;
        System.out.print(clean);
        System.out.println("Introduza um Cliente:");
        int u = 1;
        while (u == 1) {
            try {
                String cliente = Input.lerString();
                Map<Integer, Set<Venda>> fatList = model.getFat().clone().getFat();
                for (Integer mes : fatList.keySet()) {
                    Triplo t = model.querie3aux(cliente, mes);
                    view.q3(t, mes);

                }
                System.out.println();
                u = 0;

            } catch (NumberFormatException | InputMismatchException | NaoExisteException a) {
                System.out.println("Nao exite cliente. Introduza novo cliente: ");
                continue;
            }
        }
        int ii = 1;
        System.out.println("0:Sair");
        while(ii!=0){
            try {
                ii = Input.lerInt();
            }catch(NumberFormatException a){ System.out.println("Opcao invalida");}
        }
    }

    /**
     * querie 4
     */
    public void querie4() {
        System.out.print(clean);
        System.out.println("Introduza um Produto:");

        int u = 1;
        while (u == 1) {
            try {
                String produto = Input.lerString();
                Map<Integer, Set<Venda>> fatList = model.getFat().clone().getFat();
                for (Integer mes : fatList.keySet()) {

                    Triplo t = model.querie4aux(produto, mes);
                    view.q4(t, mes);

                }
                System.out.println();
                u = 0;
            } catch (NumberFormatException | InputMismatchException | NaoExisteException a) {
                System.out.println("Nao exite produto. Introduza novo produto: ");
                continue;
            }

        }

        int ii = 1;
        System.out.println("0:Sair");
        while(ii!=0){
            try {
                ii = Input.lerInt();
            }catch(NumberFormatException a){ System.out.println("Opcao invalida");}
        }
    }


    /**
     * querie 5
     */
    public void querie5() {
        System.out.print(clean);
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Introduza um Cliente:");
                String cliente = Input.lerString();


                Map<String, Integer> list = model.querie5aux(cliente);
                list.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

                view.q5(list);
                flag = false;
            } catch (NumberFormatException | InputMismatchException | NaoExisteException a) {
                System.out.println("Nao exite cliente. Introduza novo cliente: ");
            }
        }
        int ii = 1;
        System.out.println("0:Sair");
        while(ii!=0){
            try {
                ii = Input.lerInt();
            }catch(NumberFormatException a){ System.out.println("Opcao invalida");}
        }
    }

    /**
     * querie 6
     */
    public void querie6() {

        System.out.print(clean);
        System.out.println("Numero de produtos: ");
        int f = Input.lerInt();
        Set<String> mmap = model.q6Aux(f);
        view.q6(mmap);
        int ii = 1;
        System.out.println("0:Sair");
        while(ii!=0){
            try {
                ii = Input.lerInt();
            }catch(NumberFormatException a){ System.out.println("Opcao invalida");}
        }
    }


    /**
     * querie 7
     */
    public void querie7() {
        boolean flag = true;
        System.out.print(clean);
        System.out.println("Escolha a Filial:");

        while (flag) {
            int f = Input.lerInt();
            try {

                Map<String, Double> cli = model.querie7aux(f);
                view.q7(cli);


                flag = false;
            } catch (NumberFormatException | NaoExisteException a) {
                System.out.println(a);
            }
        }

        int ii = 1;
        System.out.println("0:Sair");
        while(ii!=0){
            try {
                ii = Input.lerInt();
            }catch(NumberFormatException a){ System.out.println("Opcao invalida");}
        }
    }


    /**
     * querie 8
     */
    public void querie8() {
        System.out.print(clean);
        System.out.println("Numero de produtos: ");
        int f = Input.lerInt();

        Map<String, Integer> mmap = model.q8Aux(f);
        view.q8(mmap);

        int ii = 1;
        System.out.println("0:Sair");
        while(ii!=0){
            try {
                ii = Input.lerInt();
            }catch(NumberFormatException a){ System.out.println("Opcao invalida");}
        }
    }


    /**
     * querie 9
     */
    public void querie9() {
        System.out.print(clean);
        int u = 1;
        while (u == 1) {
            try {
                System.out.println("Insira o Codigo de produto:");

                String prodDesejado = Input.lerString();
                System.out.println("Insira o numero de clientes:");
                int numeroCli = Input.lerInt();

                Map<String, Double> sorted = model.q9Aux(prodDesejado, numeroCli);
                view.q9(sorted);
                u = 0;
            } catch (NumberFormatException | InputMismatchException | NaoExisteException a) {
                System.out.println("Nao exite produto.");
            }
        }

        int ii = 1;
        System.out.println("0:Sair");
        while(ii!=0){
            try {
                ii = Input.lerInt();
            }catch(NumberFormatException a){ System.out.println("Opcao invalida");}
        }
    }

    /**
     * querie 10
     */
    public void querie10() {
        System.out.print(clean);
        System.out.println("Insira o codigo do produto:");
        int u = 1;
        while (u == 1) {
            try {
                String prodDesejado = Input.lerString();

                Map<Integer, Map<Integer, Double>> sorted = model.q10Aux(prodDesejado);

                view.q10(prodDesejado, sorted);
                u = 0;
            } catch (NumberFormatException | InputMismatchException | NaoExisteException a) {
                System.out.println("Nao exite produto. Insira um novo produto:");
            }
        }


        int ii = 1;
        System.out.println("0:Sair");
        while(ii!=0){
            try {
                ii = Input.lerInt();
            }catch(NumberFormatException a){ System.out.println("Opcao invalida");}
        }
    }
    }



