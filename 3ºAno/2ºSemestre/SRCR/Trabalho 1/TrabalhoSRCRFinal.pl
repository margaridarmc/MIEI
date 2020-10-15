
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - Exercicio 1

% Grupo 3

% Catarina Gil
% Margaridas Campos
% Roberto Freitas
% Tania Rocha

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais


:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: definicoes iniciais

:- op( 900,xfy,'::' ).

:- dynamic '-'/1.
:- dynamic(adjudicante/4).
:- dynamic(adjudicataria/4).
:- dynamic(contrato/10).
:- dynamic (excecao/1).



% Extensão do predicado que permite a evolucao do conhecimento
evolucao( Termo ) :-solucoes( Invariante, +Termo::Invariante,Lista ),insercao( Termo ),teste( Lista ). 

evolucao(-Termo) :- 
    solucoes(Invariante, +(-Termo)::Invariante, Lista),
    insercao(-Termo),
    teste(Lista).

% Extensão do predicado solucoes   
solucoes( X,Y,Z ) :-findall( X,Y,Z ).

insercao( Termo ) :-assert( Termo ).
insercao( Termo ) :-retract( Termo ), !,fail.
  
teste( [] ).
teste( [R|LR] ) :- R,teste( LR ).

involucao( Termo ) :-
    solucoes( Invariante,-Termo::Invariante,Lista ),
    remove( Termo ),
    teste( Lista ).

nao( Questao ) :-Questao, !, fail.
nao( Questao ).


remove(P) :- retract(P).
remove(P) :- assert(P),!,fail.


retractL([]).
retractL([X|L]):- 
        retract(X), 
        retractL(L).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extenção do predicado adjudicante: Id, Nome, Nif, Morada -> {V,F,D}

%Conhecimento Perfeito Positivo
adjudicante( 1,'Munincipio de Alto de Basto','705330336','Portugal, Braga, Alto de Basto').
adjudicante( 2,'Agrupamento de Escolas do Cerco, Porto','600078965','Portugal, Porto').
adjudicante( 3,'Munincipio de Braga','506901173','Portugal, Braga, Braga').
adjudicante( 4,'Munincipio de Amares','506797627','Portugal, Braga, Amares').
adjudicante( 5,'Cooperativa Agricola de Barcelos , CRL','500967580','Portugal, Braga, Barcelos').
adjudicante( 6,'AgdA - Aguas Publicas do Alentejo, S. A.','509133843','Portugal, Beja, Beja').
adjudicante( 7,'Uniao das Freguesias de Lomar e Arcos','510837581','Portugal').

%Conhecimento Perfeito Negativo
-adjudicante( 8,'Municipio de Vila Verde','385049328','Portugal,Braga, Vila Verde').
-adjudicante( 9,'Municipio de Barcelos','283239329', 'Portugal, Braga, Barcelos').

% Confirmar de o adjudicante não faz parte da base de Conhecimento
% tenham parâmetros diferentes do que os que estão guardados
-adjudicante(Id,N,I,M) :- nao(adjudicante(Id,N,I,M)),
                          nao(excecao(adjudicante(Id,N,I,M))).



%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado adjudicataria: Id, Nome, NIF, Morada -> {V,F,D}

% Conhecimento Perfeito Positivo
adjudicataria( 1,'Agripesca Entreposto Frigorifico Lda','501063013','Portugal').
adjudicataria( 2,'Gonaalo Leite de Campos & Associados - Sociedade de Advogados, SP, RL','513210865','Portugal').
adjudicataria( 3,'Landfound-Levantamentos Cadastrais, Lda.','506206416','Portugal').
adjudicataria( 4,'LIGALOTE, LDA.','509605540','Portugal').
adjudicataria( 5,'Buscardini Communications SRL','738570569','Belgica').
adjudicataria( 6,'PROVILOJAS','505927764','Portugal').
adjudicataria( 7,'OLMAR - Artigos de Papelaria, Lda.','508831989','Portugal').
adjudicataria( 8,'MLL - Construção Civil e Obras Publicas, Lda','502621559','Portugal').
adjudicataria( 9,'XXX - Associados - Sociedade de Advogados, SP, RL','702675112','Portugal').

% Conhecimento Perfeito Negativo
-adjudicataria( 10,'OLMAR - Artigos de Papelaria, Lda.','508831989','Portugal').
-adjudicataria( 11,'Jose Barros Moreira & Gomes Lda.','510078214','Portugal').

% Confirmar de o adjudicataria não faz parte da base de Conhecimento
% tenham parâmetros diferentes do que os que estão guardados
-adjudicataria( Id , Nome , Nif , Morada) :- nao(adjudicataria(Id,Nome,Nif,Morada)),
                                        nao(excecao(adjudicataria(Id,Nome,Nif,Morada))).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado contrato: idAd, idAda, TipoDeContrato, TipoDeProcedimento, Descricao, Custo, Prazo, Local, Data -> {V,F,D}

% Conhecimento Perfeito Positivo
contrato(1,'510837581','509605540','Empreitadas de obras publicas','Ajuste Direto Regime Geral', 'PARQUE INFANTIL - RUA DA MOUTA, LOMA', '67051.12', '90', 'Portugal, Braga, Braga','23-03-2018').
contrato(2,'510837581','502621559','Empreitadas de obras publicas','Ajuste Direto Regime Geral', 'CONSTRUCAO DE MURO DE SUPORTE - LOTEAMENTO DE SOUTO NOVAL - LOMAR', '5675.00', '30', 'Portugal, Braga, Braga','17-03-2016' ).
contrato(3,'509133843','513210865','Aquisicao de servicos','Ajuste Direto Regime Geral', '122_2017 Consultoria Juridica', '74900.00', '180', 'Portugal, Beja, Beja','17-01-2018' ).
contrato(4,'506901173','738570569','Aquisicao de servicos','Ajuste Direto Regime Geral', 'ADCM/1/2020/DCP', '40000.00', '15', 'Portugal, Braga, Braga','25-03-2020' ).
contrato(5,'506901173','505927764','Aquisicao de bens moveis','Ajuste Direto Regime Geral', 'ADG/2/18/DACPGP', '6553.00', '306', 'Portugal, Braga, Braga','01-03-2018' ).
contrato(6,'600078965','508831989','Aquisicao de bens moveis','Concurso Previa', 'CP.2019.10 Economato', '4910.12', '365', 'Portugal, Porto, Porto','30-12-2019').
contrato(7,'600078965','501063013','Aquisicao de bens moveis','Consulta Previa', 'CP.2019.06. Bens Alimentares Restauracao', '2444.04', '166', 'Portugal, Porto, Porto','23-09-2019').
contrato(8,'510878440','510078214','Empreitadas de obras públicas','Ajuste Direto Regime Geral','Reconstrução e ampliação do cemiterio de Arcozelo','92835.34','180','Portugal, Braga, Vila Verde','15-05-2018').
contrato(9,'705330336','702675112','Aquisicao de serviços','Consulta Previa','Assessoria Juridica','13599','547','Alto de Basto','11-02-2020').

% Conhecimento Perfeito Negativo
-contrato(10,'600078965','501169580','Aquisicao de bens moveis','Ajuste Direto Regime Geral', '133/2020 - LABESFAL', '64.00', '60', 'Portugal, Lisboa, Lisboa','06-04-2020').
-contrato(11,'600014576','509697887','Aquisicao de servicos','Concurso publico','Aquisição de Servicos de Apoio e a Direcao de Servicos de Administracao Financeira do Ministerio dos Negocios Estrangeiros (CATGest)','92835.34','1095','Portugal, Lisboa, Braga','06-04-2020').


% -------------------------------------------------------------
% Extensao do predicado registaAdjudicante : I,N,NIF,M -> {V,F}

addAdjudicante(Id,Nome,Nif,Morada) :- evolucao(adjudicante(Id,Nome,Nif,Morada)). 

% -------------------------------------------------------------
% Extensao do predicado registaAdjudicataria : I,N,NIF,M-> {V,F}

addAdjudicataria(Id,Nome,Nif,Morada) :- evolucao(adjudicataria(Id,Nome,Nif,Morada)). 


% -------------------------------------------------------------
% Extensao do predicado registaAdjudicataria : I,N,NIF,M-> {V,F}

addContrato(IdC,IdAd,IdAda,TipoC,TipoP,Des,Custo,Prazo,Loca,Data) :- evolucao(contrato(IdC,IdAd,IdAda,TipoC,TipoP,Des,Custo,Prazo,Loca,Data)).

% Invariante que garante que não existe conhecimento
% perfeito positivo repetido
+T :: (solucoes(T, T, R),
       comprimento(R, 1)).

% Invariante que garante que não existe conhecimento
% perfeito negativo repetido
+(-T) :: (solucoes(T, -T, R),
          comprimento(R, 1)).

% Invariante que não permite adicionar conhecimento
% perfeito positivo que contradiz conhecimento perfeito negativo
+T :: nao(-T).

% Invariante que não permite adicionar conhecimento
% perfeito negativo que contradiz conhecimento perfeito positivo
+(-T) :: nao(T).

% Invariante que garante que não existem excecoes repetidas
+(excecao(T)) :: (solucoes(T, excecao(T), R),
                  comprimento(R, 1)).

%- - - - - - - - - - - - - - - - - - - - - - - - - - -  -  -  -  -   -
% Invariantes adjudicante

% Invariante que nao permite dois adjudicante com o mesmo id para conhecimento perfeito positivo
+adjudicante(Id,Nome,Nif,Morada) :: (solucoes((Id),adjudicante(Id,_,_,_), L),comprimento(L, 1)).
  

% Invariante que nao permite dois adjudicante com o mesmo id para conhecimento perfeito negativo
+(-adjudicante(Id,Nome,Nif,Morada)) :: (solucoes(Id, -adjudicante(Id,_,_,_), L),comprimento(L, 1)).

% Invariante que nao permite dois adjudicante com o mesmo nif para conhecimento perfeito positivo
+adjudicante(Id,Nome,Nif,Morada) :: (solucoes((Nif), adjudicante(_,_,Nif,_), L),comprimento(L, 1)).

% Invariante que nao permite dois adjudicante com o mesmo nif para conhecimento perfeito negativo
+(-adjudicante(Id,Nome,Nif,Morada)) :: (solucoes((Nif), -adjudicante(_,_,Nif,_), L),comprimento(L, 1)).

% Invariante que garante que adjudicantes com ids diferentes têm diferente informação para conhecimento perfeito positivo
+adjudicante(Id,Nome,Nif,Morada) :: (solucoes((Nome,Nif,Morada), adjudicante(_,Nome,Nif,Morada), R),comprimento(R, 1)).

% Invariante que garante que adjudicantes com ids diferentes têm diferente informação para conhecimento perfeito negativo
+(-adjudicante(Id,Nome,Nif,Morada)) :: (solucoes((Nome,Nif,Morada), -adjudicante(_,Nome,Nif,Morada), R),comprimento(R, 1)).

% Invariante que não permite eliminar um adjudicante caso ele tenha contratos
-adjudicante(Id,Nome,Nif,Morada) :: (solucoes( (Nif),( contrato(_,Nif,_,_,_,_,_,_,_,_) ),S),comprimento( S,0)).

%- - - - - - - - - - - - - - - - - - - - - - - - - - -  -  -  -  -   -
% Invariantes adjudicataria

% Invariante que nao permite dois adjudicataria com o mesmo id para conhecimento perfeito positivo
+adjudicataria(Id,Nome,Nif,Morada) :: (solucoes((Id), adjudicataria(Id,_,_,_), L),comprimento(L, 1)).

% Invariante que nao permite dois adjudicataria com o mesmo id para conhecimento perfeito negativo
+(-adjudicataria(Id,Nome,Nif,Morada)) :: (solucoes(Id, -adjudicataria(Id,_,_,_), L),comprimento(L, 1)).

% Invariante que nao permite dois adjudicataria com o mesmo nif para conhecimento perfeito positivo
+adjudicataria(Id,Nome,Nif,Morada) :: (solucoes((Nif), adjudicataria(_,_,Nif,_), L),comprimento(L, 1)).

% Invariante que nao permite dois adjudicataria com o mesmo nif para conhecimento perfeito negativo
+(-adjudicataria(Id,Nome,Nif,Morada)) :: (solucoes((Nif), -adjudicataria(_,_,Nif,_), L),comprimento(L, 1)).

% Invariante que garante que adjudicatarias com ids diferentes têm diferente informação para conhecimento perfeito positivo
+adjudicataria(Id,Nome,Nif,Morada) :: (solucoes((Nome,Nif,Morada), adjudicataria(_,Nome,Nif,Morada), R),comprimento(R, 1)).

% Invariante que garante que adjudicatarias com ids diferentes têm diferente informação para conhecimento perfeito negativo
+(-adjudicataria(Id,Nome,Nif,Morada)) :: (solucoes((Nome,Nif,Morada), -adjudicataria(_,Nome,Nif,Morada), R),comprimento(R, 1)).

% Invariante que não permite eliminar uma adjuducataria caso tenha contratos

-adjudicataria(Id,Nome,Nif,Morada) :: (solucoes( (Nif),( contrato(_,Nif,_,_,_,_,_,_,_,_) ),S),comprimento( S,0)).

%- - - - - - - - - - - - - - - - - - - - - - - - - - -  -  -  -  -   -
% Invariantes contrato

% Invariante que nao permite dois contratos com o mesmo id para conhecimento perfeito positivo
+contrato(IdC,IdAd,IdAda,TipoC,TipoP,Des,Custo,Prazo,Loca,Data) :: (solucoes((IdC), contrato(IdC,_,_,_,_,_,_,_,_,_), L),comprimento(L, 1)).

% Invariante que nao permite dois contratos com o mesmo id para conhecimento perfeito negativo
+(-contrato(IdC,IdAd,IdAda,TipoC,TipoP,Des,Custo,Prazo,Loca,Data)) :: (solucoes(IdC, -contrato(IdC,_,_,_,_,_,_,_,_,_), L),comprimento(L, 1)).


%garante que os NIFs associados ao adjudicante e à adjudicataria existem 
+contrato(IdC,IdAd,IdAda,TipoC,TipoP,Des,Custo,Prazo,Loca,Data) :: (adjudicante(_,_,IdAd,_),adjudicataria(_,_,IdAda,_)).


tipoP('Ajuste Direto').
tipoP('Consulta Previa').
tipoP('Concurso Publico').

%garante que o tipo de Procidemento pertence a : {'Ajuste Direto','Consulta Previa','Concurso Publico'}
+contrato(IdC,IdAd,IdAda,TipoC,TipoP,Des,Custo,Prazo,Loca,Data):: tipoP(TipoP).


custoValido(C) :- atom_codes(C,C1), number_codes(C2,C1)  ,C2 >=0.
custoAjDiretoVALIDO(C) :- atom_codes(C,C1), number_codes(C2,C1),C2=<5000 , C2>=0.
prazoV(P) :-atom_codes(P,C1), number_codes(C2,C1),C2<365.

%garante que os custos associados aos contratos são válidos
+contrato(IdC,IdAd,IdAda,TipoC,'Consulta Previa',Des,Custo,Prazo,Loca,Data) :: custoValido(Custo).
+contrato(IdC,IdAd,IdAda,TipoC,'Concurso Publico',Des,Custo,Prazo,Loca,Data) :: custoValido(Custo).
+contrato(IdC,IdAd,IdAda,TipoC,'Ajuste Direto',Des,Custo,Prazo,Loca,Data) :: custoAjDiretoVALIDO(Custo).

tipoC('Aquisicao de servicos').
tipoC('Aquisicao de bens moveis').
tipoC('Locacao de bens moveis').


%garante que o tipo de Contrato pertence a : {'Aquisicao de servicos','Aquisicao de bens moveis','Locacao de bens moveis'}
+contrato(IdC,IdCIdAd,IdAda,TipoC,'Ajuste Direto',Des,Custo,Prazo,Loca,Data) :: tipoC(TipoC).

%garante que o prazo é menor ou igual a um ano
+contrato(IdC,IdCIdAd,IdAda,TipoC,'Ajuste Direto',Des,Custo,Prazo,Loca,Data) :: prazoV(Prazo).


+contrato(IdC,IdAd,IdAda,TipoC,TipoP,Des,Custo,Prazo,Loca,Data) :: (solucoes(contrato(A,IdAd,B,C,D,E,F,G,H,I), contrato(A,IdAd,B,C,D,E,F,G,H,I), L),auxx(L,L2),custo(L2, P), P<75000). 
% recebe uma lista de char, e retira os hifens, sabemos que data é coposta por 4/2/2
retirahifen([D1,D2,H1,M1,M2,H2,A1,A2,A3,A4],[D1,D2,M1,M2,A1,A2,A3,A4]).

% devolve o dia numa lista de chars sem hifen
devolveDia([D1,D2,M1,M2,A1,A2,A3,A4],[D1,D2]).

% devolve o Mes numa lista de chars sem hifen
devolveMes([D1,D2,M1,M2,A1,A2,A3,A4],[M1,M2]).

% devolve o Ano numa lista de chars sem hifen
devolveAno([D1,D2,M1,M2,A1,A2,A3,A4],[A1,A2,A3,A4]).

% converte um atmo para uma lista de chars
converteD(Data,DataConv) :-
    atom_chars(Data,DataConv).

% converte uma lista de chars de uma data sem hifen e devolve o dia
converteDia(L,Dia) :-
    devolveDia(L,Aux),converteD(LL,Aux),atom_codes(LL,R),number_codes(Dia,R).

% converte uma lista de chars de uma data sem hifen e devolve o Mes
converteMes(L,Mes) :-
    devolveMes(L,Aux),converteD(LL,Aux),atom_codes(LL,R),number_codes(Mes,R).    

% converte uma lista de chars de uma data sem hifen e devolve o Ano
converteAno(L,Ano) :-
    devolveAno(L,Aux),converteD(LL,Aux),atom_codes(LL,R),number_codes(Ano,R).

% converte uma data em formato 'dd-mm-aaa' para dia, mes e ano em inteiros
converteData(Data,Dia,Mes,Ano):- 
    converteD(Data,DataConv),retirahifen(DataConv,DataSemHif),
    converteDia(DataSemHif,Dia),converteMes(DataSemHif,Mes),converteAno(DataSemHif,Ano).


auxx([],[]).
auxx([contrato(IdC,IdAd,IdAda,TipoC,TipoP,Des,Custo,Prazo,Loca,Data)|Rest],T) :- converteData(Data,D,M,A),(A=2020;A=2019;A=2018),adicionar(contrato(IdC,IdAd,IdAda,TipoC,TipoP,Des,Custo,Prazo,Loca,Data),Rest,T).

custo([],0).
custo([contrato(_,_,_,_,_,_,Custo,_,_,_)| Rest],R) :-
    custo(Rest, R2), atom_codes(Custo,C1), number_codes(C2,C1),R is C2 + R2 .

adicionar(X,[],[X|[]]).
adicionar(X,[X|L],[X|L]).
adicionar(X, [Y|L],[Y|R]) :- adicionar(X,L,R).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Funcoes Auxiliares

% Extensão do predicado comprimento : L , R -> {V,R} 
comprimento( S,N ) :-
    length( S,N ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Evolução de conhecimento imperfeito incerto

%--- Adjudicante

% Insere conhecimento imperfeito incerto na base de conhecimento

% no caso de um Adjudicante com um nome desconhecido
evolucao(adjudicante(Id,Nome_desconhecido,N,M), nome_incerto) :-
    evolucao(adjudicante(Id,Nome_desconhecido,N,M)),
    insercao((excecao(adjudicante(Id,Nome,Ni,Morada)) :-
                    adjudicante(Id,Nome_desconhecido,Ni,Morada))).

% se mais tarde for necessária a incersão do nome que não era conhecido antes
evolucaoNomeIncerto(adjudicante(Id,N,Ni,M)) :-
    demo(adjudicante(Id,N,Ni,M),R),
    R = desconhecido,
    solucoes((excecao(adjudicante(Id,N,Ni,M)) :- adjudicante(Id,X,Ni,M)), (adjudicante(Id,X,Ni,M), nao(nulo(X))),L),
    retractL(L),
    remove(adjudicante(Id,X,Ni,M)),
    evolucao(adjudicante(Id,N,Ni,M)).


% no caso de um Adjudicante com um nif desconhecido
evolucao(adjudicante(Id,N,Nif_desconhecido,M), nif_incerto) :-
    evolucao(adjudicante(Id,N,Nif_desconhecido,M)),
    insercao((excecao(adjudicante(Id,N,Nif,Morada)) :-
                    adjudicante(Id,N,Nif_desconhecido,Morada))).


% se mais tarde for necessária a incersão do nif que não era conhecido antes
evolucaoNifIncerto(adjudicante(Id,N,Ni,M)) :-
    demo(adjudicante(Id,N,Ni,M),R),
    R = desconhecido,
    solucoes((excecao(adjudicante(Id,N,Ni,M)) :- adjudicante(Id,N,X,M)), (adjudicante(Id,N,X,M), nao(nulo(X))),L),
    retractL(L),
    remove(adjudicante(Id,N,X,M)),
    evolucao(adjudicante(Id,N,Ni,M)).

% no caso de um Adjudicante com uma Morada desconhecidado
evolucao(adjudicante(Id,N,Ni,Morada_desconhecida), morada_incerta) :-
    evolucao(adjudicante(Id,N,Ni,Morada_desconhecida)),
    insercao((excecao(adjudicante(Id,N,Ni,Morada)) :-
                    adjudicante(Id,N,Ni,Morada_desconhecida))).


% se mais tarde for necessária a incersão da morada que não era conhecido antes
evolucaoMoradaIncerta(adjudicante(Id,N,Ni,M)) :-
    demo(adjudicante(Id,N,Ni,M),R),
    R = desconhecido,
    solucoes((excecao(adjudicante(Id,N,Ni,M)) :- adjudicante(Id,N,Ni,X)), (adjudicante(Id,N,Ni,X), nao(nulo(X))),L),
    retractL(L),
    remove(adjudicante(Id,N,Ni,X)),
    evolucao(adjudicante(Id,N,Ni,M)).

%--- Adjudicataria

% Insere conhecimento imperfeito incerto na base de conhecimento

% no caso de um Adjudicataria com um nome desconhecido
evolucao(adjudicataria(Id,Nome_desconhecido,N,M), nome_incerto) :-
    evolucao(adjudicataria(Id,Nome_desconhecido,N,M)),
    insercao((excecao(adjudicataria(Id,Nome,Ni,Morada)) :-
                    adjudicataria(Id,Nome_desconhecido,Ni,Morada))).

% se mais tarde for necessária a incersão do nome que não era conhecido antes
evolucaoNomeIncerto(adjudicataria(Id,N,Ni,M)) :-
    demo(adjudicataria(Id,N,Ni,M),R),
    R = desconhecido,
    solucoes((excecao(adjudicataria(Id,N,Ni,M)) :- adjudicataria(Id,X,Ni,M)), (adjudicataria(Id,X,Ni,M), nao(nulo(X))),L),
    retractL(L),
    remove(adjudicataria(Id,X,Ni,M)),
    evolucao(adjudicataria(Id,N,Ni,M)).


% no caso de um adjudicataria com um nif desconhecido
evolucao(adjudicataria(Id,N,Nif_desconhecido,M), nif_incerto) :-
    evolucao(adjudicataria(Id,N,Nif_desconhecido,M)),
    insercao((excecao(adjudicataria(Id,N,Nif,Morada)) :-
                    adjudicataria(Id,N,Nif_desconhecido,Morada))).


% se mais tarde for necessária a incersão do nif que não era conhecido antes
evolucaoNifIncerto(adjudicataria(Id,N,Ni,M)) :-
    demo(adjudicataria(Id,N,Ni,M),R),
    R = desconhecido,
    solucoes((excecao(adjudicataria(Id,N,Ni,M)) :- adjudicataria(Id,N,X,M)), (adjudicataria(Id,N,X,M), nao(nulo(X))),L),
    retractL(L),
    remove(adjudicataria(Id,N,X,M)),
    evolucao(adjudicataria(Id,N,Ni,M)).

% no caso de um adjudicataria com uma Morada desconhecidado
evolucao(adjudicataria(Id,N,Ni,Morada_desconhecida), morada_incerta) :-
    evolucao(adjudicataria(Id,N,Ni,Morada_desconhecida)),
    insercao((excecao(adjudicataria(Id,N,Ni,Morada)) :-
                    adjudicataria(Id,N,Ni,Morada_desconhecida))).


% se mais tarde for necessária a incersão do nif que não era conhecido antes
evolucaoNifIncerto(adjudicataria(Id,N,Ni,M)) :-
    demo(adjudicataria(Id,N,Ni,M),R),
    R = desconhecido,
    solucoes((excecao(adjudicataria(Id,N,Ni,M)) :- adjudicataria(Id,N,Ni,X)), (adjudicataria(Id,N,Ni,X), nao(nulo(X))),L),
    retractL(L),
    remove(adjudicataria(Id,N,Ni,X)),
    evolucao(adjudicataria(Id,N,Ni,M)).


%--- Contrato


% no caso de o id do Adjudicante for desconhecido no contrato
evolucao(contrato(IdC,Id_desconhecido,IDa,TC,TP,D,C,P,L,Da), idAdjudicante_incerto) :-
    evolucao(contrato(IdC,Id_desconhecido,IDa,TC,TP,D,C,P,L,Da)),
    insercao((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(IdC,Id_desconhecido,IDa,TC,TP,D,C,P,L,Da))).

% se mais tarde for necessária a incersão do id que não era conhecido antes
evolucaoIdIncerto(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
    demo(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da),R),
    R = desconhecido,
    solucoes((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :- contrato(IdC,X,IDa,TC,TP,D,C,P,L,Da)), (contrato(IdC,X,IDa,TC,TP,D,C,P,L,Da), nao(nulo(X))),Lista),
    retractL(Lista),
    remove(contrato(IdC,X,IDa,TC,TP,D,C,P,L,Da)),
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)).


% no caso de o id da Adjudicataria for desconhecido no contrato
evolucao(contrato(IdC,ID,IdAd_desconhecido,TC,TP,D,C,P,L,Da), idAdjudicataria_incerto) :-
    evolucao(contrato(IdC,ID,IdAd_desconhecido,TC,TP,D,C,P,L,Da)),
    insercao((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(IdC,ID,IdAd_desconhecido,TC,TP,D,C,P,L,Da))).


% se mais tarde for necessária a incersão do id que não era conhecido antes
evolucaoIdAIncerto(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
    demo(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da),R),
    R = desconhecido,
    solucoes((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :- contrato(IdC,ID,X,TC,TP,D,C,P,L,Da)), (contrato(IdC,ID,X,TC,TP,D,C,P,L,Da), nao(nulo(X))),Lista),
    retractL(Lista),
    remove(contrato(IdC,ID,X,TC,TP,D,C,P,L,Da)),
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)).

% no caso de um tipo de contrato for desconhecido no contrato
evolucao(contrato(IdC,ID,IDa,TC_desconhecido,TP,D,C,P,L,Da), tipoDeContrato_incerto) :-
    evolucao(contrato(IdC,ID,IDa,TC_desconhecido,TP,D,C,P,L,Da)),
    insercao((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(IdC,ID,IDa,TC_desconhecido,TP,D,C,P,L,Da))).

% se mais tarde for necessária a incersão do id que não era conhecido antes
evolucaoTCIncerto(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
    demo(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da),R),
    R = desconhecido,
    solucoes((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :- contrato(IdC,ID,IDa,X,TP,D,C,P,L,Da)), (contrato(IdC,ID,IDa,X,TP,D,C,P,L,Da), nao(nulo(X))),Lista),
    retractL(Lista),
    remove(contrato(IdC,ID,IDa,X,TP,D,C,P,L,Da)),
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)).

% no caso de um tipo de procedimento for desconhecido no contrato
evolucao(contrato(IdC,ID,IDa,TC,TP_desconhecido,D,C,P,L,Da),tipoDeProcedimento_incerto) :-
    evolucao(contrato(IdC,ID,IDa,TC,TP_desconhecido,D,C,P,L,Da)),
    insercao((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(IdC,ID,IDa,TC,TP_desconhecido,D,C,P,L,Da))).

% se mais tarde for necessária a incersão do tipo de procedimento que não era conhecido antes
evolucaoTPIncerto(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
    demo(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da),R),
    R = desconhecido,
    solucoes((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :- contrato(IdC,ID,IDa,TC,X,D,C,P,L,Da)), (contrato(IdC,ID,IDa,TC,X,D,C,P,L,Da), nao(nulo(X))),Lista),
    retractL(Lista),
    remove(contrato(IdC,ID,IDa,TC,X,D,C,P,L,Da)),
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)).

% no caso de uma descrição desconhecida no contrato
evolucao(contrato(IdC,ID,IDa,TC,TP,D_desconhecida,C,P,L,Da), descricao_incerta) :-
    evolucao(contrato(IdC,ID,IDa,TC,TP,D_desconhecida,C,P,L,Da)),
    insercao((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(IdC,ID,IDa,TC,TP,D_desconhecida,C,P,L,Da))).

% se mais tarde for necessária a incersão da descrição que não era conhecido antes
evolucaoDescricaoIncerta(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
    demo(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da),R),
    R = desconhecido,
    solucoes((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :- contrato(IdC,ID,IDa,TC,TP,X,C,P,L,Da)), (contrato(IdC,ID,IDa,TC,TP,X,C,P,L,Da), nao(nulo(X))),Lista),
    retractL(Lista),
    remove(contrato(IdC,ID,IDa,TC,TP,X,C,P,L,Da)),
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)).

% no caso de um custo for desconhecido no contrato
evolucao(contrato(IdC,ID,IDa,TC,TP,D,C_desconhecido,P,L,Da), custo_incerto) :-
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C_desconhecido,P,L,Da)),
    insercao((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(IdC,ID,IDa,TC,TP,D,C_desconhecido,P,L,Da))).

% se mais tarde for necessária a incersão do custo que não era conhecido antes
evolucaoCustoIncerto(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
    demo(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da),R),
    R = desconhecido,
    solucoes((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :- contrato(IdC,ID,IDa,TC,TP,D,X,P,L,Da)), (contrato(IdC,ID,IDa,TC,TP,D,X,P,L,Da), nao(nulo(X))),Lista),
    retractL(Lista),
    remove(contrato(IdC,ID,IDa,TC,TP,D,X,P,L,Da)),
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)).

% no caso de um Prazo for desconhecido no contrato
evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P_desconhecido,L,Da),prazo_incerto) :-
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P_desconhecido,L,Da), positivo),
    insercao((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(IdC,ID,IDa,TC,TP,D,C,P_desconhecido,L,Da))).

% se mais tarde for necessária a incersão do prazo que não era conhecido antes
evolucaoPrazoIncerto(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
    demo(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da),R),
    R = desconhecido,
    solucoes((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :- contrato(IdC,ID,IDa,TC,TP,D,C,X,L,Da)), (contrato(IdC,ID,IDa,TC,TP,D,C,X,L,Da), nao(nulo(X))),Lista),
    retractL(Lista),
    remove(contrato(IdC,ID,IDa,TC,TP,D,C,X,L,Da)),
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)).

% no caso de um local for desconhecido no contrato
evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L_desconhecido,Da), local_incerto) :-
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L_desconhecido,Da)),
    insercao((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(IdC,ID,IDa,TC,TP,D,C,P,L_desconhecido,Da))).

% se mais tarde for necessária a incersão do local que não era conhecido antes
evolucaoLocalIncerto(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
    demo(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da),R),
    R = desconhecido,
    solucoes((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :- contrato(IdC,ID,IDa,TC,TP,D,C,P,X,Da)), (contrato(IdC,ID,IDa,TC,TP,D,C,P,X,Da), nao(nulo(X))),Lista),
    retractL(Lista),
    remove(contrato(IdC,ID,IDa,TC,TP,D,C,P,X,Da)),
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)).


% no caso de uma data for desconhecido no contrato
evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da_desconhecida),data_incerta) :-
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da_desconhecida)),
    insercao((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da_desconhecida))).

% se mais tarde for necessária a incersão do id que não era conhecido antes
evolucaoDataIncerta(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :-
    demo(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da),R),
    R = desconhecido,
    solucoes((excecao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)) :- contrato(IdC,ID,IDa,TC,TP,D,C,P,L,X)), (contrato(IdC,ID,IDa,TC,TP,D,C,P,L,X), nao(nulo(X))),Lista),
    retractL(Lista),
    remove(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,X)),
    evolucao(contrato(IdC,ID,IDa,TC,TP,D,C,P,L,Da)).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Evolução de conhecimento imperfeito impreciso

% Insere conhecimento imperfeito impreciso na base de conhecimento

% Insere em exeções ou adjudicante ou adjudicataria ou contrato
evolucao(T, impreciso) :-
    solucoes(I, +(excecao(T))::I, Lint),
    insercao(excecao(T)),
    teste(Lint). 

%Caso já se saiba qual o parâmetro correto pode-se adicionar a partir das seguintes funções:
%Adjudicante
evolucaoImprecisoAdjudicante(adjudicante(ID,Nome,Nif,Morada)):-
        demo(adjudicante(ID,Nome,Nif,Morada),R),
        R = desconhecido,
        solucoes(excecao(adjudicante(ID,N,NI,M)), excecao(adjudicante(ID,N,NI,M)),Lista),
        retractL(Lista),
        evolucao(adjudicante(ID,Nome,Nif,Morada)).

%Adjudicataria
evolucaoImprecisoAdjudicataria(adjudicataria(ID,Nome,Nif,Morada)):-
        demo(adjudicataria(ID,Nome,Nif,Morada),R),
        R = desconhecido,
        solucoes(excecao(adjudicataria(ID,N,NI,M)), excecao(adjudicataria(ID,N,NI,M)),Lista),
        retractL(Lista),
        evolucao(adjudicataria(ID,Nome,Nif,Morada)).

%Contrato
evolucaoImprecisoContrato(contrato(IDC,ID,IDA,TipoDeContrato,TipoDeProcedimento,Descricao,Custo,Prazo,Local,Data)):-
        demo(contrato(IDC,ID,IDA,TipoDeContrato,TipoDeProcedimento,Descricao,Custo,Prazo,Local,Data),R),
        R = desconhecido,
        solucoes(excecao(contrato(IDC,ID,IDA,TC,TP,D,C,P,L,DA)), excecao(contrato(IDC,ID,IDA,TC,TP,D,C,P,L,DA)),Lista),
        retractL(Lista),
        evolucao(contrato(IDC,ID,IDA,TipoDeContrato,TipoDeProcedimento,Descricao,Custo,Prazo,Local,Data)).



% Involução de conhecimento imperfeito incerto na base de conhecimento

%---Adjudicante

% no caso de um Adjudicante com nome desconhecido
involucao(adjudicante(Id,Nome_desconhecido,N,M), nome_incerto) :-
    involucao(adjudicante(Id,Nome_desconhecido,N,M)),
    remove((excecao(adjudicante(Id,Nome,Nif,Morada)) :-
                    adjudicante(Id,Nome_desconhecido,Nif,Morada))).


% no caso de um Adjudicante com um nif desconhecido
involucao(adjudicante(Id,N,Nif_desconhecido,M), adjudicante, nif_incerto) :-
    involucao(adjudicante(Id,N,Nif_desconhecido,M)),
    remove((excecao(adjudicante(Id,Nome,Nif,Morada)) :-
                    adjudicante(Id,Nome,Nif_desconhecido,Morada))).

% no caso de um Adjudicante com uma Morada desconhecida
involucao(adjudicante(Id,Nome,Nif,Morada_desconhecida),morada_incerta) :-
    involucao(adjudicante(Id,Nome,Nif_desconhecido,Morada_desconhecida)),
    remove((excecao(adjudicante(Id,Nome,Nif,Morada)) :-
                    adjudicante(Id,Nome,Nif,Morada_desconhecida))).

%--- Adjudicataria

% no caso de um Adjudicataria com nome desconhecido

involucao(adjudicataria(Id,Nome_desconhecido,N,M), nome_incerto) :-
    involucao(adjudicataria(Id,Nome_desconhecido,N,M)),
    ((excecao(adjudicataria(Id,Nome,Nif,Morada)) :-
                    adjudicataria(Id,Nome_desconhecido,Nif,Morada))).


% no caso de um Adjudicataria com um nif desconhecido
involucao(adjudicataria(Id,N,Nif_desconhecido,M), nif_incerto) :-
    involucao(adjudicataria(Id,N,Nif_desconhecido,M)),
    remove((excecao(adjudicataria(Id,Nome,Nif,Morada)) :-
                    adjudicataria(Id,Nome,Nif_desconhecido,Morada))).

% no caso de um Adjudicataria com uma Morada desconhecida
involucao(adjudicataria(Id,Nome,Nif,Morada_desconhecida), morada_incerta) :-
    involucao(adjudicataria(Id,Nome,Nif_desconhecido,Morada_desconhecida), positivo),
    remove((excecao(adjudicataria(Id,Nome,Nif,Morada)) :-
                    adjudicataria(Id,Nome,Nif,Morada_desconhecida))).


%--- Contrato


% no caso de o id do Adjudicante for desconhecido no contrato
involucao(contrato(idAd_desconhecido,ID,TC,TP,D,C,P,L,Da), idAdjudicante_incerto) :-
    involucao(contrato(idAd_desconhecido,ID,TC,TP,D,C,P,L,Da)),
    remove((excecao(contrato(idAd,ID,TC,TP,D,C,P,L,Da)) :-
                    contrato(idAd_desconhecido,ID,TC,TP,D,C,P,L,Da))).

% no caso de o id da Adjudicataria for desconhecido no contrato
involucao(contrato(ID,idAda_desconhecido,TC,TP,D,C,P,L,Da), idAdjudicataria_incerto) :-
    involucao(contrato(ID,idAda_desconhecido,TC,TP,D,C,P,L,Da)),
    remove((excecao(contrato(ID,idAda,TC,TP,D,C,P,L,Da)) :-
                    contrato(ID,idAda_desconhecido,TC,TP,D,C,P,L,Da))).

% no caso de um tipo de contrato for desconhecido no contrato
involucao(contrato(ID,IDa,TC_desconhecido,TP,D,C,P,L,Da), tipoDeContrato_incerto) :-
    involucao(contrato(ID,IDa,TC_desconhecido,TP,D,C,P,L,Da), positivo),
    remove((excecao(contrato(ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(ID,IDa,TC_desconhecido,TP,D,C,P,L,Da))).

% no caso de um tipo de procedimento for desconhecido no contrato
involucao(contrato(ID,IDa,TC,TP_desconhecido,D,C,P,L,Da), tipoDeProcedimento_incerto) :-
    involucao(contrato(ID,IDa,TC,TP_desconhecido,D,C,P,L,Da), positivo),
    remove((excecao(contrato(ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(ID,IDa,TC,TP_desconhecido,D,C,P,L,Da))).

% no caso de uma descrição desconhecida no contrato
involucao(contrato(ID,IDa,TC,TP,D_desconhecida,C,P,L,Da),descricao_incerta) :-
    involucao(contrato(ID,IDa,TC,TP,D_desconhecida,C,P,L,Da), positivo),
    remove((excecao(contrato(ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(ID,IDa,TC,TP,D_desconhecida,C,P,L,Da))).

% no caso de um custo for desconhecido no contrato
involucao(contrato(ID,IDa,TC,TP,D,C_desconhecido,P,L,Da),custo_incerto) :-
    involucao(contrato(ID,IDa,TC,TP,D,C_desconhecido,P,L,Da), positivo),
    remove((excecao(contrato(ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(ID,IDa,TC,TP,D,C_desconhecido,P,L,Da))).

% no caso de um Prazo for desconhecido no contrato
involucao(contrato(ID,IDa,TC,TP,D,C,P_desconhecido,L,Da),prazo_incerto) :-
    involucao(contrato(ID,IDa,TC,TP,D,C,P_desconhecido,L,Da), positivo),
    remove((excecao(contrato(ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(ID,IDa,TC,TP,D,C,P_desconhecido,L,Da))).

% no caso de um local for desconhecido no contrato
involucao(contrato(ID,IDa,TC,TP,D,C,P,L_desconhecido,Da),local_incerto) :-
    involucao(contrato(ID,IDa,TC,TP,D,C,P,L_desconhecido,Da), positivo),
    remove((excecao(contrato(ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(ID,IDa,TC,TP,D,C,P,L_desconhecido,Da))).

% no caso de uma data for desconhecido no contrato
involucao(contrato(ID,IDa,TC,TP,D,C,P,L,Da_desconhecida),data_incerta) :-
    involucao(contrato(ID,IDa,TC,TP,D,C,P,L,Da_desconhecida), positivo),
    remove((excecao(contrato(ID,IDa,TC,TP,D,C,P,L,Da)) :-
                    contrato(ID,IDa,TC,TP,D,C,P,L,Da_desconhecida))).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Involução de conhecimento imperfeito impreciso

% Retira conhecimento imperfeito impreciso na base de conhecimento

% seja adjudicante, adjudicataria ou contrato
involucao(Termo, impreciso) :-
    solucoes(Invariante, -(excecao(Termo))::Invariante, Lista),
    remove(excecao(Termo)),
    teste(Lista).

involucao(-Termo) :-
    solucoes(Invariante, -(-Termo)::Invariante, Lista),
    remove(-Termo),
    teste(Lista).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Conhecimento Imperfeito
% -------------------------------
% Tipo 1 - Conhecimento Incerto
% -------------------------------
%N Devido a troca de instalaços, o adjudicante com id 15 ainda nao tornou publico a sua nova localizaçao, assim ainda ninguem
%  conhece a sua nova morada.

adjudicante(15,'Munincipio de Vizela','705330343',morada_desconhecida).
excecao(adjudicante(Id,NomeMuni,Nif,Morada)) :- 
    adjudicante(Id,NomeMuni,Nif,morada_desconhecida).

%N Devido a troca de nome, a adjudicataria com id 25 mudou de Nif, mas esse ainda é desconhecido.

adjudicataria( 25,'Trolitop',nifDesc,'Portugal').
excecao(adjudicataria(Id,Nome,Nif,Morada)) :- 
    adjudicataria(Id,Nome,nifDesc,Morada).

% o contrato com id 34 nao se conhece o valor 

contrato(36,'705330336','702675112','Aquisicao de serviços','Consulta Previa','Assessoria Juridica',valorDesc,'254','Alto de Basto','22-01-2020').
excecao(contrato(Id, IdAnte,IdAtaria,TC,TP,D,C,P,L,D ) ) :- 
    contrato( Id,IdAnte,IdAtaria,TC,TP,D,C,valorDesc,L,D ).

% -------------------------------
% Tipo 2 - Conhecimento Impreciso
% -------------------------------
% Com a troca de morada do adjudicante com id 7 inicou se a troca da mesma, mas foi add duas moradas Esposende ou vizela, nao sabendo
% qual a certa.
excecao(adjudicante(16,'Munincipio de Vizela','705330344','Portugal,Braga, Esposende')).
excecao(adjudicante(16,'Munincipio de Vizela','705330344','Portugal,Braga, Vizela')).


% N Nao se sabe se a adjudicataria com id 26 tem morada em Portugal ou na Belgica.
excecao(adjudicataria( 26,'PortSeg','506785468','Portugal')).
exceccao(adjudicataria( 26,'PortSeg','506785468','Belgica')).

% No contrato com id 35 nao se sabe se o valor foi de 5999 ou de 6999.

excecao(contrato(35,'705330336','702675112','Aquisicao de serviços','Consulta Previa','Assessoria Juridica','5999','500','Alto de Basto','12-02-2020')).
excecao(contrato(35,'705330336','702675112','Aquisicao de serviços','Consulta Previa','Assessoria Juridica','6999','500','Alto de Basto','12-02-2020')).


% -------------------------------
% Tipo 3 - Conhecimento Interdito
% -------------------------------
% O adjudicante com id 17 VIP, pelo que não
% permite que nao se saiba a morada.

adjudicante( 17,'Munincipio de Vizela','705330345',moradaInterdita).
excecao(adjudicante(Id,Nome,Nif,Morada)) :- 
    adjudicante(Id,Nome,Nif,moradaInterdita).
nulo(moradaInterdita).

+adjudicante(Id, Nome, Nif, Morada) :: (solucoes(Mr, ((adjudicante( 17,'Munincipio de Vizela','705330345',Mr)),nao(nulo(Mr))),L),
                comprimento(L,R),
                R == 0).

% -------------------------------
% A adjudicataria com id 25 VIP, pelo que não
% permite que nao se saiba a morada.

adjudicataria( 27,'Camberr','503649825',moradaInterdita2).
excecao(adjudicataria(Id,Nome,Nif,Morada)) :- 
    adjudicataria(Id,Nome,Nif,moradaInterdita2).
nulo(moradaInterdita2).

+adjudicataria(Id, Nome, Nif, Morada) :: (solucoes(Mr, ((adjudicataria( 27,'Camberr','503649825',Mr)),nao(nulo(Mr))),L),
                comprimento(L,R),
                R == 0).

% o contrato com id 34 nao se conhece o valor pa

contrato(34,'705330336','702675112','Aquisicao de serviços','Consulta Previa','Assessoria Juridica',valorDesc,'254','Alto de Basto','22-01-2020').
excecao(contrato(Id, IdAnte,IdAtaria,TC,TP,D,C,P,L,D ) ) :- 
    contrato( Id,IdAnte,IdAtaria,TC,TP,D,C,valorDesc,L,D ).
nulo(valorDesc).

+(contrato(Id, IdAnte,IdAtaria,TC,TP,D,C,P,L,D )) :: 
                (solucoes(VD, ((contrato(34,'705330336','702675112','Aquisicao de serviços','Consulta Previa','Assessoria Juridica',VD,'254','Alto de Basto','22-01-2020')),
                nao(nulo(VD)),LL)),
                comprimento(LL,R),
                R == 0).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Sistema de Inferência

%- - - - - - - - - - - - - - - - - - - - - - - - - - -  -  -  -  -   -
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demo: Questao,Resposta -> {V,F}
%                            Resposta = { verdadeiro,falso,desconhecido }

demo( Questao,verdadeiro ) :-
    Questao.
demo( Questao,falso ) :-
    -Questao.
demo( Questao,desconhecido ) :-
    nao( Questao ),
    nao( -Questao ).

%- - - - - - - - - - - - - - - - - - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado demoLista: [Questao],[Resposta] -> {V,F,D}
% Responde a uma lista de questões


demoLista([],[]).
demoLista([Questao|Qs],[R|Rs]) :- demo(Questao,R),
                          demoLista(Qs,Rs).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado demo: Questao1, Tipo, Questao2, Flag -> {V, F, D}

demo(Q1, eq, Q2, F) :- demo(Q1, F1), 
                       demo(Q2, F2), 
                       equivalencia(F1, F2, F).

demo(Q1, ou, Q2, F) :- demo(Q1, F1), 
                       demo(Q2, F2), 
                       disjuncao(F1, F2, F).
                       
demo(Q1, e, Q2, F) :- demo(Q1, F1), 
                      demo(Q2, F2), 
                      conjuncao(F1, F2, F). 

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado equivalencia: X,Y -> {V,F,D}

equivalencia(X, X, verdadeiro).
equivalencia(desconhecido, Y, desconhecido).
equivalencia(X, desconhecido, desconhecido).
equivalencia(X, Y, falso) :- X \= Y.

% Extensao do predicado conjuncao: X,Y -> {V,F,D}
conjuncao(verdadeiro,verdadeiro,verdadeiro).
conjuncao(verdadeiro,desconhecido,desconhecido).
conjuncao(desconhecido,verdadeiro,desconhecido).
conjuncao(desconhecido,desconhecido,desconhecido).
conjuncao(falso,_,falso).
conjuncao(_,falso,falso).

% Extensao do predicado disjuncao: X,Y -> {V,F,D}
disjuncao(verdadeiro,_,verdadeiro).
disjuncao(_,verdadeiro,verdadeiro).
disjuncao(falso,falso,falso).
disjuncao(falso,desconhecido,desconhecido).
disjuncao(desconhecido,falso,desconhecido).
disjuncao(desconhecido,desconhecido,desconhecido).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
%Extras

somaLista([],0).
somaLista([X|Y],N) :- somaLista(Y,R), atom_codes(X,C1), number_codes(C2,C1), N is C2+R.

%Predicado que devolve o total de cada entidade
%total de adjudicantes
numero_adjudicantes(R) :- solucoes(Id, adjudicante(Id,_,_,_),L), comprimento(L,R).

%total de adjudicatarias
numero_adjudicatarias(R) :- solucoes(Id, adjudicataria(Id,_,_,_),L), 
                        comprimento(L,R).
%total de contratos
numero_contratos(R) :- solucoes(Id, contrato(Id,_,_,_,_,_,_,_,_,_),L), 
                        comprimento(L,R).

%Dado um adjudicante, devolve a lista de adjudicatarias com quem já efetuou contratos
listaDeAdjudicatarios(NIF,R) :- solucoes(adjudicataria(_,_,NIF2,_),
      contrato(_,NIF,NIF2,_,_,_,_,_,_,_),R).

%Dado uma adjudicataria, devolve a lista de adjudicantes com quem já efetuou contratos
listaDeAdjudicantes(NIF,R) :- solucoes(adjudicante(_,_,NIF2,_),
      contrato(_,NIF2,NIF,_,_,_,_,_,_,_),R).

%Valor total que um adjudicante já pagou
totalPagoAdjudicante(Adjudicante,T) :- solucoes(Custo, contrato(_,Adjudicante,_,_,_,_,Custo,_,_,_),Lista), somaLista(Lista,T).

%Valor total que uma adjudicataria já recebeu
totalGanhoAdjudicataria(Adjudicataria,T) :- solucoes(Custo, contrato(_,_,Adjudicataria,_,_,_,Custo,_,_,_),Lista), somaLista(Lista,T).


%dado uma data no formato dd-mm-aaaa devolve os contratos realizados nesse dia
contratosData(Data,T) :-
    solucoes(contrato(Id,A,H,B,C,D,Custo,E,F,Data), contrato(Id,A,H,B,C,D,Custo,E,F,Data),T).

%dado uma data no formato mm-aaaa devolve os contratos realizados nesse mes
contratosDataMes(Data,T) :-
   solucoes(contrato(Id,A,H,B,C,D,Custo,E,F,Da), (contrato(Id,A,H,B,C,D,Custo,E,F,Da),compareDatasMA(Da,Data)),T).

%dado uma data no formato aaaa devolve os contratos realizados nesse ano
contratosDataAno(Data,T) :-
   solucoes(contrato(Id,A,H,B,C,D,Custo,E,F,Da), (contrato(Id,A,H,B,C,D,Custo,E,F,Da),compareDatasA(Da,Data)),T).

%dado uma data no formato dd-mm-aaaa devolve o nr de contratos realizados nesse dia
nrContratosData(Data,T) :-
   solucoes(Data, contrato(_,_,_,_,_,_,_,_,_,Data),List),comprimento(List,T).

%dado uma data no formato mm-aaaa devolve o nr de contratos realizados nesse mes
nrContratosDataMes(Data,T) :-
  solucoes(Da, ( contrato(_,_,_,_,_,_,_,_,_,Da),compareDatasMA(Da,Data)),List),comprimento(List,T).

%dado uma data no formato aaaa devolve o nr de contratos realizados nesse ano
nrContratosDataAno(Data,T) :-
  solucoes(Da, ( contrato(_,_,_,_,_,_,_,_,_,Da),compareDatasA(Da,Data)),List),comprimento(List,T).

%dado uma data no formato dd-mm-aaaa devolve custo total de contratos realizados nesse dia
custosContratosData(Data,T) :-
   solucoes(Custo, (contrato(_,_,_,_,_,_,Custo,_,_,Data),nao(nulo(Custo))),List),somaLista(List,T).

%dado uma data no formato mm-aaaa devolve custo total de contratos realizados nesse mes
custosContratosDataMes(Data,T) :-
  solucoes(Custo, ( contrato(_,_,_,_,_,_,Custo,_,_,Da),compareDatasMA(Da,Data),nao(nulo(Custo))),List),somaLista(List,T).

%dado uma data no formato aaaa devolve custo total de contratos realizados nesse ano
custosContratosDataAno(Data,T) :-
  solucoes(Custo, ( contrato(_,_,_,_,_,_,Custo,_,_,Da),compareDatasA(Da,Data),nao(nulo(Custo))),List),somaLista(List,T).

%Dado uma data, mmaaaa sem hifen, em lista de chars, devolve uma lista de chars do mes mm
devolveMMA([M1,M2,A1,A2,A3,A4],[M1,M2]).

%Dado uma data, mmaaaa sem hifen, em lista de chars, devolve uma lista de chars do ano aaaa
devolveAMA([M1,M2,A1,A2,A3,A4],[A1,A2,A3,A4]).

%Dado uma data, mmaaaa com hifen, em lista de chars, devolve uma lista de chars sem o mes
retirahifenMA([M1,M2,H,A1,A2,A3,A4],[M1,M2,A1,A2,A3,A4]).

% converte uma lista de chars mmaaaa de uma data sem hifen e devolve o Mes em formato int
converteMMA(L,Mes) :-
   devolveMMA(L,Aux),converteD(LL,Aux),atom_codes(LL,R),number_codes(Mes,R).    

% converte uma lista de chars mmaaaa de uma data sem hifen e devolve o Ano em formato int
converteAMA(L,Ano) :-
   devolveAMA(L,Aux),converteD(LL,Aux),atom_codes(LL,R),number_codes(Ano,R).

%Dado uma data no formato 'mm-aaaa' devolve o mes e o ano no formato int
converteDataMA(Data,Mes,Ano) :-
   converteD(Data,DataConv),retirahifenMA(DataConv,DataSemHif),
   converteMMA(DataSemHif,Mes),converteAMA(DataSemHif,Ano).

%Dado uma data no formato 'aaaa' devolve o ano no formato int
converteDataA(Data,Ano) :-
   converteD(Data,DataConv),converteD(LL,DataConv),atom_codes(LL,R),number_codes(Ano,R).

%dado uma Data 'dd-mm-aaaa' e uma datMes 'mm-aaaa' india se a primeira pertence a segunda
compareDatasMA(Data,DatMes) :-
   converteData(Data,D,M,A),converteDataMA(DatMes,M2,A2), M==M2,A==A2.

%dado uma Data 'dd-mm-aaaa' e uma datAno 'aaaa' india se a primeira pertence a segunda
compareDatasA(Data,DatAno) :-
   converteData(Data,D,M,A),converteDataA(DatAno,A2),A==A2.


nContratosAdjudicante(IdAd,R) :- (solucoes(IdAd, contrato(_,IdAd,_,_,_,_,_,_,_,_), L),comprimento(L,R)). 
nContratosAdjudicataria(IdAda,R) :- (solucoes(IdAda, contrato(_,_,IdAda,_,_,_,_,_,_,_), L),comprimento(L,R)).

