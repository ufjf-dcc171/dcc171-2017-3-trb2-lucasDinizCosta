package Principal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

interface ControleArquivos {

    /****************************************************************************
     * Interface criada com o objetivo de ler e atualizar os arquivos de textos *
     * do banco de dados e poder ter seus métodos acessados sem precisar de um  *
     * objeto instanciado.                                                      *
     * **************************************************************************/
    
    public static void lerArquivoCardapio(Cardapio cardapio) {
        try {
            Path caminhoLeitura = Paths.get("Arquivos/Cardápio.txt");						//Caminho do arquivo de texto
            Path aux = Paths.get("Arquivos");										//Caminho da pasta Arquivos
            if (aux.toFile().exists() == true) {										//A pasta Arquivos existe
                if (caminhoLeitura.toFile().exists() == false) {							//O Arquivo do Banco de dados existe
                    criaArquivoVazio("Cardápio", caminhoLeitura);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pasta de armazenamento \"Arquivos\" do Banco de dados não encontrada.");
                System.exit(1);
            }

            if (caminhoLeitura.toFile().exists() == true) {														//O Arquivo só pode ser lido se o caminho existir, caso contrário acarreta em um erro que é tratado pelo try e catch
                Charset utf8charset = Charset.forName("UTF-8");												//Tipo de codificação do Arquivo
                FileInputStream caminhoDoArquivo = new FileInputStream(caminhoLeitura.toFile().getPath());	//Lê o arquivo de texto, é obrigatório passar um caminho/path no construtor, faz a leitura de dados binários não importando a fonte
                InputStreamReader tradutorEncode = new InputStreamReader(caminhoDoArquivo, utf8charset); 	//Traduz os bytes com o encoding dado para o respectivo código, em outras palavras é um decodificador para uma codificação específica nesse caso "UTF-8" mas poderia ser "ISO-8859-1"
                //Lê bytes de um lado, converte em caracteres do outro, através do uso de uma codificação de caracteres (encoding)
                BufferedReader bufferLeitura = new BufferedReader(tradutorEncode);							//concatena os diversos chars para formar uma String através do método readLine
                String linha = bufferLeitura.readLine();													//Passa o conteúdo da primeira linha para uma variável String
                linha = bufferLeitura.readLine();
                linha = bufferLeitura.readLine();                               ///Lendo a partir da 3 linha, onde se encontra os dados importantes
                while (linha != null) {
                    if (linha != null) {
                        String nomeProduto = linha.substring(linha.indexOf("Produto: ") + 9, linha.indexOf(" Preço unitário:"));			//Separa em substrings específicas de leitura
                        String precoProduto = linha.substring(linha.indexOf("Preço unitário: R$ ") + 19, linha.length());										//Troca virgula por ponto e exportação do txt é feita com vírgula
                        cardapio.adicionarProduto(new Produto(nomeProduto, new BigDecimal(precoProduto)));
                    }
                    linha = bufferLeitura.readLine();
                }
                bufferLeitura.close();
            } else if (caminhoLeitura.toFile().exists() == false) {												//Se o Arquivo de relatório não existe, para não dar problema ele será criado como arquivo vazio
                JOptionPane.showMessageDialog(null, "Arquivo \"Cardápio.txt\" não encontrado, arquivo foi criado.");
            }
        } catch (Exception erro) {		//Tratamento de erros
            erro.printStackTrace();
            System.out.println("Erro na leitura de arquivos");
        }
    }

    public static void lerArquivoPedidosAbertos(List<Pedido> listaPedidosAbertos) {
        try {
            Path caminhoLeitura = Paths.get("Arquivos/PedidosAbertos.txt");						//Caminho do arquivo de texto
            Path aux = Paths.get("Arquivos");										//Caminho da pasta Arquivos
            if (aux.toFile().exists() == true) {										//A pasta Arquivos existe
                if (caminhoLeitura.toFile().exists() == false) {							//O Arquivo do Banco de dados existe
                    criaArquivoVazio("Pedidos Abertos", caminhoLeitura);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pasta de armazenamento \"Arquivos\" do Banco de dados não encontrada.");
                System.exit(1);
            }	
            if (caminhoLeitura.toFile().exists() == true) {														//O Arquivo só pode ser lido se o caminho existir, caso contrário acarreta em um erro que é tratado pelo try e catch
                Charset utf8charset = Charset.forName("UTF-8");												//Tipo de codificação do Arquivo
                FileInputStream caminhoDoArquivo = new FileInputStream(caminhoLeitura.toFile().getPath());	//Lê o arquivo de texto, é obrigatório passar um caminho/path no construtor, faz a leitura de dados binários não importando a fonte
                InputStreamReader tradutorEncode = new InputStreamReader(caminhoDoArquivo, utf8charset); 	//Traduz os bytes com o encoding dado para o respectivo código, em outras palavras é um decodificador para uma codificação específica nesse caso "UTF-8" mas poderia ser "ISO-8859-1"
                //Lê bytes de um lado, converte em caracteres do outro, através do uso de uma codificação de caracteres (encoding)
                BufferedReader bufferLeitura = new BufferedReader(tradutorEncode);							//concatena os diversos chars para formar uma String através do método readLine
                String linha = bufferLeitura.readLine();													//Passa o conteúdo da primeira linha para uma variável String
                linha = bufferLeitura.readLine();
                linha = bufferLeitura.readLine();                               ///Lendo a partir da 3 linha, onde se encontra os dados importantes
                while (linha != null) {
                    if (linha != null) {
                        Pedido pedido = new Pedido();
                        Integer numeroPedido = Integer.parseInt(linha.substring(linha.indexOf("Número do pedido: ") + 18, linha.indexOf(" Mesa:")));			//Separa em substrings específicas de leitura
                        Integer numeroMesa = Integer.parseInt(linha.substring(linha.indexOf("Mesa: ") + 6, linha.indexOf(" Entrada:")));
                        pedido.setNumeroMesa(numeroPedido);
                        pedido.setNumeroMesa(numeroMesa);
                        String entrada = linha.substring(linha.indexOf("Entrada: ") + 9, linha.indexOf(" Valor Total:"));
                        String data = entrada.substring(0, entrada.indexOf(" --"));
                        String horario = entrada.substring(entrada.indexOf("-- ") + 3, entrada.length());
                        int priOcorrencia = data.indexOf("/");                             //Primeira ocorrencia da "/"
                        int segOcorrencia = data.indexOf("/", priOcorrencia + 1);          //Segunda ocorrencia da "/"
                        Integer dia = Integer.parseInt(data.substring(0, priOcorrencia));
                        Integer mes = Integer.parseInt(data.substring(priOcorrencia + 1, segOcorrencia));
                        Integer ano = Integer.parseInt(data.substring(segOcorrencia + 1, data.length()));
                        priOcorrencia = horario.indexOf(":");                             //Primeira ocorrencia do ":"
                        segOcorrencia = horario.indexOf(":", priOcorrencia + 1);          //Segunda ocorrencia do ":"
                        Integer hora = Integer.parseInt(horario.substring(0, priOcorrencia));
                        Integer minuto = Integer.parseInt(horario.substring(priOcorrencia + 1, segOcorrencia));
                        Integer segundo = Integer.parseInt(horario.substring(segOcorrencia + 1, horario.length()));

                        pedido.getCalendarioInicial().setData(dia, mes, ano, hora, minuto, segundo);

                        Integer quantProdutos = Integer.parseInt(linha.substring(linha.indexOf("Quantidade de produtos: ") + 24, linha.indexOf(" Produto")));
                        if (quantProdutos > 1) {
                            for (int i = 1; i < quantProdutos; i++) {
                                String escritoProduto = linha.substring(linha.indexOf("Produto " + i +": ") + new String("Produto " + i +": ").length(), linha.indexOf(" Produto " + (i + 1)));
                                String numProdutos = escritoProduto.substring(escritoProduto.indexOf("(x") + 2, escritoProduto.indexOf(") "));
                                String nomeProduto = escritoProduto.substring(escritoProduto.indexOf(") ") + 2, escritoProduto.indexOf(" - "));
                                String precoProduto = escritoProduto.substring(escritoProduto.indexOf("R$ ") + 3, escritoProduto.length());
                                for (int j = 0; j < Integer.parseInt(numProdutos); j++) {     //Adiciona o produto a quantidade feita no pedido
                                    Produto p = new Produto(nomeProduto, new BigDecimal(precoProduto));
                                    pedido.adicionarProduto(p);
                                }
                            }
                        }
                        String escritoUltimoProduto = linha.substring(linha.indexOf("Produto " + quantProdutos + ": ") + new String("Produto " + quantProdutos + ": ").length(), linha.length());
                        String numUltimoProduto = escritoUltimoProduto.substring(escritoUltimoProduto.indexOf("(x") + 2, escritoUltimoProduto.indexOf(") "));
                        String nomeUltimoProduto = escritoUltimoProduto.substring(escritoUltimoProduto.indexOf(") ") + 2, escritoUltimoProduto.indexOf(" - "));
                        String precoUltimoProduto = escritoUltimoProduto.substring(escritoUltimoProduto.indexOf("Preço unitário: R$ ") + 19, escritoUltimoProduto.length());
                        for (int j = 0; j < Integer.parseInt(numUltimoProduto); j++) {     //Adiciona o produto a quantidade feita no pedido
                            Produto p = new Produto(nomeUltimoProduto, new BigDecimal(precoUltimoProduto));
                            pedido.adicionarProduto(p);
                        }

                        listaPedidosAbertos.add(pedido);
                    }
                    linha = bufferLeitura.readLine();
                }
                bufferLeitura.close();
                //System.out.println("Pedidos abertos lido com sucesso!!!");
            } else if (caminhoLeitura.toFile().exists() == false) {												//Se o Arquivo de relatório não existe, para não dar problema ele será criado como arquivo vazio
                JOptionPane.showMessageDialog(null, "Arquivo \"PedidosAbertos.txt\" não encontrado, arquivo foi criado.");
            }
        } catch (Exception erro) {		//Tratamento de erros
            erro.printStackTrace();
            System.out.println("Erro na leitura de arquivos");
        }
    }

    public static void lerArquivoControleCaixa(FluxoCaixa fluxoCaixa) {
        try {
            Path caminhoLeitura = Paths.get("Arquivos/ControleCaixa.txt");						//Caminho do arquivo de texto
            Path aux = Paths.get("Arquivos");										//Caminho da pasta Arquivos
            if (aux.toFile().exists() == true) {										//A pasta Arquivos existe
                if (caminhoLeitura.toFile().exists() == false) {							//O Arquivo do Banco de dados existe
                    criaArquivoVazio("Controle de Caixa", caminhoLeitura);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pasta de armazenamento \"Arquivos\" do Banco de dados não encontrada.");
                System.exit(1);
            }	
            if (caminhoLeitura.toFile().exists() == true) {														//O Arquivo só pode ser lido se o caminho existir, caso contrário acarreta em um erro que é tratado pelo try e catch
                Charset utf8charset = Charset.forName("UTF-8");												//Tipo de codificação do Arquivo
                FileInputStream caminhoDoArquivo = new FileInputStream(caminhoLeitura.toFile().getPath());	//Lê o arquivo de texto, é obrigatório passar um caminho/path no construtor, faz a leitura de dados binários não importando a fonte
                InputStreamReader tradutorEncode = new InputStreamReader(caminhoDoArquivo, utf8charset); 	//Traduz os bytes com o encoding dado para o respectivo código, em outras palavras é um decodificador para uma codificação específica nesse caso "UTF-8" mas poderia ser "ISO-8859-1"
                //Lê bytes de um lado, converte em caracteres do outro, através do uso de uma codificação de caracteres (encoding)
                BufferedReader bufferLeitura = new BufferedReader(tradutorEncode);							//concatena os diversos chars para formar uma String através do método readLine
                String linha = bufferLeitura.readLine();													//Passa o conteúdo da primeira linha para uma variável String
                linha = bufferLeitura.readLine();
                linha = bufferLeitura.readLine();                               ///Lendo a partir da 3 linha, onde se encontra os dados importantes

                CaixaDiario caixaDiarioAuxiliar = new CaixaDiario();
                while (linha != null) {
                    if (linha != null) {
                        if (linha.length() > 35) {      ///Linha de pedidos
                            Pedido pedido = new Pedido();
                            Integer numeroPedido = Integer.parseInt(linha.substring(linha.indexOf("Número do pedido: ") + 18, linha.indexOf(" Mesa:")));			//Separa em substrings específicas de leitura
                            Integer numeroMesa = Integer.parseInt(linha.substring(linha.indexOf("Mesa: ") + 6, linha.indexOf(" Entrada:")));
                            pedido.setNumeroPedido(numeroPedido);
                            pedido.setNumeroMesa(numeroMesa);

                            /*************************************************
                             * Capturando os dados da data e hora de entrada.*
                             ************************************************/
                            
                            String entrada = linha.substring(linha.indexOf("Entrada: ") + 9, linha.indexOf(" Saída:"));
                            String data = entrada.substring(0, entrada.indexOf(" --"));
                            String horario = entrada.substring(entrada.indexOf("-- ") + 3, entrada.length());
                            int priOcorrencia = data.indexOf("/");                             //Primeira ocorrencia da "/"
                            int segOcorrencia = data.indexOf("/", priOcorrencia + 1);          //Segunda ocorrencia da "/"
                            Integer diaEntrada = Integer.parseInt(data.substring(0, priOcorrencia));
                            Integer mesEntrada = Integer.parseInt(data.substring(priOcorrencia + 1, segOcorrencia));
                            Integer anoEntrada = Integer.parseInt(data.substring(segOcorrencia + 1, data.length()));
                            priOcorrencia = horario.indexOf(":");                             //Primeira ocorrencia do ":"
                            segOcorrencia = horario.indexOf(":", priOcorrencia + 1);          //Segunda ocorrencia do ":"
                            Integer horaEntrada = Integer.parseInt(horario.substring(0, priOcorrencia));
                            Integer minutoEntrada = Integer.parseInt(horario.substring(priOcorrencia + 1, segOcorrencia));
                            Integer segundoEntrada = Integer.parseInt(horario.substring(segOcorrencia + 1, horario.length()));

                            pedido.getCalendarioInicial().setData(diaEntrada, mesEntrada, anoEntrada, horaEntrada, minutoEntrada, segundoEntrada);

                            /*************************************************
                             * Capturando os dados da data e hora de saída.  *
                             * ***********************************************/
                            
                            String saida = linha.substring(linha.indexOf("Saída: ") + 7, linha.indexOf(" Valor Recebido:"));
                            data = saida.substring(0, saida.indexOf(" --"));
                            horario = saida.substring(saida.indexOf("-- ") + 3, saida.length());
                            priOcorrencia = data.indexOf("/");                             //Primeira ocorrencia da "/"
                            segOcorrencia = data.indexOf("/", priOcorrencia + 1);          //Segunda ocorrencia da "/"
                            Integer diaSaida = Integer.parseInt(data.substring(0, priOcorrencia));
                            Integer mesSaida = Integer.parseInt(data.substring(priOcorrencia + 1, segOcorrencia));
                            Integer anoSaida = Integer.parseInt(data.substring(segOcorrencia + 1, data.length()));
                            priOcorrencia = horario.indexOf(":");                             //Primeira ocorrencia do ":"
                            segOcorrencia = horario.indexOf(":", priOcorrencia + 1);          //Segunda ocorrencia do ":"
                            Integer horaSaida = Integer.parseInt(horario.substring(0, priOcorrencia));
                            Integer minutoSaida = Integer.parseInt(horario.substring(priOcorrencia + 1, segOcorrencia));
                            Integer segundoSaida = Integer.parseInt(horario.substring(segOcorrencia + 1, horario.length()));

                            pedido.getCalendarioFinal().setData(diaSaida, mesSaida, anoSaida, horaSaida, minutoSaida, segundoSaida);

                            Integer quantProdutos = Integer.parseInt(linha.substring(linha.indexOf("Quantidade de produtos: ") + 24, linha.indexOf(" Produto")));
                            
                            if (quantProdutos > 1) {
                                for (int i = 1; i < quantProdutos; i++) {
                                    String escritoProduto = linha.substring(linha.indexOf("Produto " + i + ": ") + new String("Produto " + i + ": ").length(), linha.indexOf(" Produto " + (i + 1)));
                                    String numProdutos = escritoProduto.substring(escritoProduto.indexOf("(x") + 2, escritoProduto.indexOf(") "));
                                    String nomeProduto = escritoProduto.substring(escritoProduto.indexOf(") ") + 2, escritoProduto.indexOf(" - "));
                                    String precoProduto = escritoProduto.substring(escritoProduto.indexOf("R$ ") + 3, escritoProduto.length());
                                    /*for (int j = 0; j < Integer.parseInt(numProdutos); j++) {     //Adiciona o produto a quantidade feita no pedido
                                        Produto p = new Produto(nomeProduto, new BigDecimal(precoProduto));
                                        pedido.adicionarProduto(p);
                                    }*/
                                    Produto p = new Produto(nomeProduto, new BigDecimal(precoProduto));
                                    p.setQuantidade(Integer.parseInt(numProdutos));
                                    pedido.adicionarProduto(p);
                                }
                            }
                            String escritoUltimoProduto = linha.substring(linha.indexOf("Produto " + quantProdutos + ": ") + new String("Produto " + quantProdutos + ": ").length(), linha.length());
                            String numUltimoProduto = escritoUltimoProduto.substring(escritoUltimoProduto.indexOf("(x") + 2, escritoUltimoProduto.indexOf(") "));
                            String nomeUltimoProduto = escritoUltimoProduto.substring(escritoUltimoProduto.indexOf(") ") + 2, escritoUltimoProduto.indexOf(" - "));
                            String precoUltimoProduto = escritoUltimoProduto.substring(escritoUltimoProduto.indexOf("Preço unitário: R$ ") + 19, escritoUltimoProduto.length());
                            for (int j = 0; j < Integer.parseInt(numUltimoProduto); j++) {     //Adiciona o produto a quantidade feita no pedido
                                Produto p = new Produto(nomeUltimoProduto, new BigDecimal(precoUltimoProduto));
                                pedido.adicionarProduto(p);
                            }

                            caixaDiarioAuxiliar.adicionar(pedido);
                        } else {       ///Linha das datas dos CaixaDiario ou vazias
                            if (linha.length() > 3) {                 ///Linha do caixaDiario
                                String data = linha.substring(linha.indexOf("Caixa Diário: (") + 15, linha.indexOf(")"));
                                ///Armazenando data
                                int priOcorrencia = data.indexOf("/");                             //Primeira ocorrencia da "/"
                                int segOcorrencia = data.indexOf("/", priOcorrencia + 1);          //Segunda ocorrencia da "/"
                                Integer dia = Integer.parseInt(data.substring(0, priOcorrencia));
                                Integer mes = Integer.parseInt(data.substring(priOcorrencia + 1, segOcorrencia));
                                Integer ano = Integer.parseInt(data.substring(segOcorrencia + 1, data.length()));

                                caixaDiarioAuxiliar = new CaixaDiario();
                                caixaDiarioAuxiliar.getCalendario().setDia(dia);
                                caixaDiarioAuxiliar.getCalendario().setMes(mes);
                                caixaDiarioAuxiliar.getCalendario().setAno(ano);
                                fluxoCaixa.adicionar(caixaDiarioAuxiliar);
                            } else {                                   ///Linha vazia

                            }
                        }
                    }
                    linha = bufferLeitura.readLine();
                }
                bufferLeitura.close();
            } else if (caminhoLeitura.toFile().exists() == false) {												//Se o Arquivo de relatório não existe, para não dar problema ele será criado como arquivo vazio
                JOptionPane.showMessageDialog(null, "Arquivo \"ControleCaixa.txt\" não encontrado, arquivo foi criado.");
            }
        } catch (Exception erro) {		//Tratamento de erros
            erro.printStackTrace();
            System.out.println("Erro na leitura de arquivos");
        }
    }

    public static void atualizaArquivoCardapio(Cardapio cardapio) {
        try {
            Path caminhoEscrita = Paths.get("Arquivos/Cardápio.txt");			//Caminho do arquivo em que será salvo
            Path aux = Paths.get("Arquivos");										//Caminho da pasta Arquivos
            if (aux.toFile().exists() == true) {										//A pasta Arquivos existe
                if (caminhoEscrita.toFile().exists() == false) {							//O Arquivo do Banco de dados existe
                    criaArquivoVazio("Cardápio", caminhoEscrita);
                }
                else{
                    PrintWriter printWriter = new PrintWriter(caminhoEscrita.toFile());
                    printWriter.print("");
                    printWriter.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pasta de armazenamento \"Arquivos\" do Banco de dados não encontrada.");
                System.exit(1);
            }																		//É necessário as autorizações abaixo pois se isso não for feito, 
            //caso você queira excluir manualmente pode não conseguir e o SO
            //exibir uma mensagem falando que você não tem acesso e precisará reiniciar o computador pra poder excluí-lo
            caminhoEscrita.toFile().setWritable(true, false);						//Autoriza a "escrita" a qualquer usuário do sistema operacional
            caminhoEscrita.toFile().setReadable(true, false);						//Autoriza a "leitura" a qualquer usuário do sistema operacional
            caminhoEscrita.toFile().setExecutable(true, false);						//Autoriza a "execução" a qualquer usuário do sistema operacional
            String quebraLinha = System.lineSeparator();							//Como alguns leitores não reconhecem o "\n" para quebra de linha então é bom usar
            OutputStreamWriter bufferOut = new OutputStreamWriter(new FileOutputStream(caminhoEscrita.toString()), "UTF-8");	//Auxiliar que ajuda a escrever no arquivo de texto
            bufferOut.write("");                                                                                                //Cria um documento vazio
            bufferOut.write("\t[Cardápio]" + quebraLinha + quebraLinha);                                        //"cabeçalho" do arquivo txt
            for (int i = 0; i < cardapio.getCardapio().size() - 1; i++) {
                bufferOut.write("Produto: " + cardapio.getCardapio().get(i).getNome() + " Preço unitário: " + cardapio.getCardapio().get(i).getStringPreco() + quebraLinha);
            }
            bufferOut.write("Produto: " + cardapio.getCardapio().get(cardapio.getCardapio().size() - 1).getNome() + " Preço unitário: " + cardapio.getCardapio().get(cardapio.getCardapio().size() - 1).getStringPreco());       ///Pega o ultimo produto em separado para não gerar uma linha vazia no arquivo
            bufferOut.close();
        } catch (Exception erro) {													//Tratamento de erros
            erro.printStackTrace();
            System.out.println("Erro na escrita de arquivos");
        }
    }

    public static void atualizaArquivoPedidosAbertos(List<Pedido> listaPedidosAbertos) {
        try {
            Path caminhoEscrita = Paths.get("Arquivos/PedidosAbertos.txt");			//Caminho do arquivo em que será salvo
            Path aux = Paths.get("Arquivos");										//Caminho da pasta Arquivos
            if (aux.toFile().exists() == true) {										//A pasta Arquivos existe
                if (caminhoEscrita.toFile().exists() == false) {							//O Arquivo do Banco de dados existe
                    criaArquivoVazio("Pedidos Abertos", caminhoEscrita);
                }
                else{
                    PrintWriter printWriter = new PrintWriter(caminhoEscrita.toFile());
                    printWriter.print("");
                    printWriter.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pasta de armazenamento \"Arquivos\" do Banco de dados não encontrada.");
                System.exit(1);
            }																		//É necessário as autorizações abaixo pois se isso não for feito, 
            //caso você queira excluir manualmente pode não conseguir e o SO
            //exibir uma mensagem falando que você não tem acesso e precisará reiniciar o computador pra poder excluí-lo
            caminhoEscrita.toFile().setWritable(true, false);						//Autoriza a "escrita" a qualquer usuário do sistema operacional
            caminhoEscrita.toFile().setReadable(true, false);						//Autoriza a "leitura" a qualquer usuário do sistema operacional
            caminhoEscrita.toFile().setExecutable(true, false);						//Autoriza a "execução" a qualquer usuário do sistema operacional
            String quebraLinha = System.lineSeparator();							//Como alguns leitores não reconhecem o "\n" para quebra de linha então é bom usar
            OutputStreamWriter bufferOut = new OutputStreamWriter(new FileOutputStream(caminhoEscrita.toString()), "UTF-8");	//Auxiliar que ajuda a escrever no arquivo de texto
            bufferOut.write("");                                                                                                //Cria um documento vazio
            bufferOut.write("\t[Pedidos Abertos]" + quebraLinha + quebraLinha);                                        //"cabeçalho" do arquivo txt
            if(listaPedidosAbertos.size() > 0){
                for (int i = 0; i < listaPedidosAbertos.size() - 1; i++) {
                    Pedido pedido = listaPedidosAbertos.get(i);
                    
                    bufferOut.write("Número do pedido: " + pedido.getNumeroPedido() + " Mesa: " + pedido.getNumeroMesa()
                            + " Entrada: " + pedido.getCalendarioInicial().dataToString() + " -- " + pedido.getCalendarioInicial().hourToString()
                            + " Valor Total: R$ " + pedido.getPrecoTotal() + " Quantidade de produtos: " + pedido.getProdutos().size());

                    /**************************
                     * Atribuindo os produtos * 
                     *************************/

                    for (int j = 0; j < pedido.getProdutos().size(); j++) {
                        bufferOut.write(" Produto " + (j + 1) + ": (x" + pedido.getProdutos().get(j).getQuantidade() + ") " + pedido.getProdutos().get(j).getNome()
                                + " - Preço unitário: " + listaPedidosAbertos.get(i).getProdutos().get(j).getStringPreco());
                    }
                    bufferOut.write(quebraLinha);
                }
            
                /************************************************************************
                * Atribui o ultimo elemento separado para não gerar uma linha vazia    *
                * no arquivo de texto.                                                 *
                * **********************************************************************/
   
                Pedido ultimoPedido = listaPedidosAbertos.get(listaPedidosAbertos.size() - 1);
                
                bufferOut.write("Número do pedido: " + ultimoPedido.getNumeroPedido() + " Mesa: " + ultimoPedido.getNumeroMesa()
                        + " Entrada: " + ultimoPedido.getCalendarioInicial().dataToString() + " -- " + ultimoPedido.getCalendarioInicial().hourToString()
                        + " Valor Total: R$ " + ultimoPedido.getPrecoTotal() + " Quantidade de produtos: " + ultimoPedido.getProdutos().size());
            
                /**************************
                * Atribuindo os produtos * 
                *************************/
            
                for (int j = 0; j < ultimoPedido.getProdutos().size(); j++) {
                    bufferOut.write(" Produto " + (j + 1) + ": (x" + ultimoPedido.getProdutos().get(j).getQuantidade() + ") " + ultimoPedido.getProdutos().get(j).getNome()
                            + " - Preço unitário: " + ultimoPedido.getProdutos().get(j).getStringPreco());
                }
                
            }
            bufferOut.close();
        } catch (Exception erro) {													//Tratamento de erros
            erro.printStackTrace();
            System.out.println("Erro na escrita de arquivos");
        }
    }

    public static void atualizarArquivoControleCaixa(FluxoCaixa fluxoCaixa) {
        try {
            Path caminhoEscrita = Paths.get("Arquivos/ControleCaixa.txt");			//Caminho do arquivo em que será salvo
            Path aux = Paths.get("Arquivos");										//Caminho da pasta Arquivos
            if (aux.toFile().exists() == true) {										//A pasta Arquivos existe
                if (caminhoEscrita.toFile().exists() == false) {							//O Arquivo do Banco de dados existe
                    criaArquivoVazio("Controle de Caixa", caminhoEscrita);
                }
                else{
                    PrintWriter printWriter = new PrintWriter(caminhoEscrita.toFile());
                    printWriter.print("");
                    printWriter.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pasta de armazenamento \"Arquivos\" do Banco de dados não encontrada.");
                System.exit(1);
            }																		//É necessário as autorizações abaixo pois se isso não for feito, 
            //caso você queira excluir manualmente pode não conseguir e o SO
            //exibir uma mensagem falando que você não tem acesso e precisará reiniciar o computador pra poder excluí-lo
            caminhoEscrita.toFile().setWritable(true, false);						//Autoriza a "escrita" a qualquer usuário do sistema operacional
            caminhoEscrita.toFile().setReadable(true, false);						//Autoriza a "leitura" a qualquer usuário do sistema operacional
            caminhoEscrita.toFile().setExecutable(true, false);						//Autoriza a "execução" a qualquer usuário do sistema operacional
            String quebraLinha = System.lineSeparator();							//Como alguns leitores não reconhecem o "\n" para quebra de linha então é bom usar
            OutputStreamWriter bufferOut = new OutputStreamWriter(new FileOutputStream(caminhoEscrita.toString()), "UTF-8");	//Auxiliar que ajuda a escrever no arquivo de texto
            bufferOut.write("");                                                                                                //Cria um documento vazio
            bufferOut.write("\t[Controle de Caixa]" + quebraLinha + quebraLinha);                                        //"cabeçalho" do arquivo txt
            
            for (int i = 0; i < fluxoCaixa.getCaixa().size(); i++) {                              ///CaixasDiarios
                CaixaDiario caixaDiarioAuxiliar = fluxoCaixa.getCaixa().get(i);
                bufferOut.write("\tCaixa Diário: (" + caixaDiarioAuxiliar.getCalendario().dataToString() + ")" + quebraLinha + quebraLinha);
                System.out.println(caixaDiarioAuxiliar.getQuantidadePedidos());
                
                for (int j = 0; j < caixaDiarioAuxiliar.getQuantidadePedidos(); j++) {              ///Pedidos
                    
                    Pedido pedido = caixaDiarioAuxiliar.getPedidos().get(j);
                    
                    bufferOut.write("Número do pedido: " + pedido.getNumeroPedido() + " Mesa: " + pedido.getNumeroMesa()
                            + " Entrada: " + pedido.getCalendarioInicial().dataToString() + " -- " + pedido.getCalendarioInicial().hourToString()
                            + " Saída: " + pedido.getCalendarioFinal().dataToString() + " -- " + pedido.getCalendarioFinal().hourToString()
                            + " Valor Recebido: R$ " + pedido.getPrecoTotal() + " Quantidade de produtos: " + pedido.getProdutos().size());

                    /**************************
                     * Atribuindo os produtos * 
                     *************************/
                    
                    System.out.println("Teste quantidade de produtos: " + pedido.getProdutos().size());
                    for (int k = 0; k < pedido.getProdutos().size(); k++) {        ///Produtos
                        bufferOut.write(" Produto " + (k + 1) + ": (x" + pedido.getProdutos().get(k).getQuantidade() + ") " + pedido.getProdutos().get(k).getNome() + " - "
                                + "Preço unitário: " + pedido.getProdutos().get(k).getStringPreco());
                    }
                    bufferOut.write(quebraLinha);
                }
                bufferOut.write(quebraLinha);
            }

            bufferOut.close();
        } catch (Exception erro) {													//Tratamento de erros
            erro.printStackTrace();
            System.out.println("Erro na escrita de arquivos");
        }
    }

    /********************************************************************************
     * Caso ocorra algum problema de não encontrar o arquivo, ele será criado       *
     * vazio.                                                                       *
     ********************************************************************************/
    
    public static void criaArquivoVazio(String titulo, Path caminhoEscrita) {
        try {
            caminhoEscrita.toFile().setWritable(true, false);						//Autoriza a "escrita" a qualquer usuário do sistema operacional
            caminhoEscrita.toFile().setReadable(true, false);						//Autoriza a "leitura" a qualquer usuário do sistema operacional
            caminhoEscrita.toFile().setExecutable(true, false);						//Autoriza a "execução" a qualquer usuário do sistema operacional
            String quebraLinha = System.lineSeparator();							//Como alguns leitores não reconhecem o "\n" para quebra de linha então é bom usar
            OutputStreamWriter bufferOut;
            bufferOut = new OutputStreamWriter(new FileOutputStream(caminhoEscrita.toString()), "UTF-8"); //Auxiliar que ajuda a escrever no arquivo de texto
            bufferOut.write("");                                                                                                //Cria um documento vazio
            bufferOut.write("\t[" + titulo + "]" + quebraLinha + quebraLinha);
            bufferOut.close();
        } catch (Exception error) {
            Logger.getLogger(ControleArquivos.class.getName()).log(Level.SEVERE, null, error);
            error.printStackTrace();
            System.out.println("Problema em criar um arquivo vazio do \"" + titulo + "\"");
        }

    }
}
