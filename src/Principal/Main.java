package Principal;

import javax.swing.JFrame;

/**********************************************************************************************
 *                  Trabalho de Laboratório de Programação III - 2017.3                       *
 *                                                                                            *
 * Aluno: Lucas Diniz da Costa         Matrícula:201465524A     Curso: Ciências Exatas        *
 *                                                                                            *
 * Atualizações:                                                                              *
 * -> Última atualização da parte 1:                                                          *
 *    Data:01/10/2017                        Hora: 15:53        Data de entrega:03/10/2017    *
 * -> Última atualização da parte 2:                                                          *
 *    Data:18/11/2017                        Hora: 20:04        Data de entrega:30/11/2017    *
 *                                                                                            *
 * Tema: Desenvolver um sistema de controle de pedidos de um (bar/lanchonete/restaurante)     *
 *                                                                                            *
 * Parte 1 - Projetar o sistema:                                                              *
 * -> Criação e desenvolvimento do sistema;                                                   *
 *                                                                                            *
 * Caracteristicas do sistema:                                                                *
 * -> Clientes devem poder realizar seus pedidos a partir de uma interface;                   *
 * -> O sistema deve captar a hora e data de inicio do pedido e de finalização do pedido      *
 * -> O sistema deve fornecer a gerencia um relatório dos pedidos exibindo o que foi pedido   *
 * e os horarios do mesmo;                                                                    *
 * -> O sistema deve permitir a administração do sistema cadastrar produtos em seu estoque    *
 * de modo que o cliente possa utilizar esses produtos cadastrados para fazer seu pedido;     *
 * -> O sistema não precisa apresentar persistencia de dados, portanto é possivel ser inserido*
 * os dados de forma manual fora da interface;                                                *
 *                                                                                            *  
 * Parte 2 - Implementação de persistência de dados:                                          *
 * -> Foi utilizado como persistência de dados o armazenamento em arquivos de texto(".txt");  *
 * -> Todos os arquivos do banco de dados estão armazenados no diretório "Arquivos";          *
 * -> Armazena os produtos cadastrados em "Cardápio.txt";                                     *
 * -> Armazena os pedidos abertos em "PedidosAbertos.txt";                                    *
 * -> Armazena todo os controle de caixa em "ControleCaixa.txt";                              *
 **********************************************************************************************/

public class Main {
    public static void main(String[] args) {
        MenuPrincipal mp = new MenuPrincipal();
        mp.setSize(350,380);
        mp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mp.setResizable(false);
        mp.setLocationRelativeTo(null);
        mp.setVisible(true);
    }
}
