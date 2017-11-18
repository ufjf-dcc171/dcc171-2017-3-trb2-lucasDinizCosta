package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControleCaixa extends JFrame {

    /*********************************************************************************************
     * Classe responsável pela interface gráfica de controle do caixa. Ela tem como objetivo     *
     * exibir as listas de caixaDiario, pedidos e produtos, além de apresentar Labels exibindo   *
     * as informações do pedido selecionado na lista.                                            *
     *********************************************************************************************/
    
    private JList<CaixaDiario> listaCaixaDiario = new JList<>(new DefaultListModel<CaixaDiario>());
    private JList<Pedido> listaPedidos = new JList<>(new DefaultListModel<Pedido>());
    private JList<Produto> listaProdutos = new JList<>(new DefaultListModel<Produto>());
    private Color corFundoPainel = new Color(155, 215, 245);
    private Color corFundoBotoes = new Color(255, 198, 198);
    private MenuPrincipal menuPrincipal = new MenuPrincipal();
    private JScrollPane rolagemPedidos = new JScrollPane(listaPedidos);
    private JScrollPane rolagemProdutos = new JScrollPane(listaProdutos);
    private JScrollPane rolagemCaixaDiario = new JScrollPane(listaCaixaDiario);
    private JLabel escritoCaixaDiarioTitulo = new JLabel("Caixa Diário");
    private JLabel escritoPedidoTitulo = new JLabel("Pedidos");                 //Titulo da lista
    private JLabel escritoProdutoTitulo = new JLabel("Produtos");
    private JLabel escritoPedido = new JLabel("Pedido:");
    private JLabel escritoMesa = new JLabel("Mesa:");
    private JLabel escritoEntrada = new JLabel("Entrada:");
    private JLabel escritoSaida = new JLabel("Saída:");
    private JLabel escritoValorPedido = new JLabel("Val. Recebido:");
    private JLabel escritoValorTotalDiario = new JLabel("Valor total recebido no dia:");
    private JLabel pedido = new JLabel("", SwingConstants.CENTER);
    private JLabel mesa = new JLabel("", SwingConstants.CENTER);
    private JLabel entrada = new JLabel("", SwingConstants.CENTER);
    private JLabel saida = new JLabel("", SwingConstants.CENTER);
    private JLabel valorPedido = new JLabel("", SwingConstants.CENTER);
    private JLabel valorTotalDiario = new JLabel("", SwingConstants.CENTER);
    private Font fonteTitulosListas = new Font("Georgia", Font.BOLD, 18);
    private Font fonteEscritos = new Font("Georgia", Font.BOLD, 15);
    private JButton finalizarPedido = new JButton("Finalizar pedido");
    private FluxoCaixa fluxoCaixa;
    private Color corFundoPaineisNumericos = new Color(209, 238, 238);
    private JButton remover = new JButton("Remover");
    private CaixaDiario caixaDiario;                                            //Atributo para não ser possível remover o dia atual

    public ControleCaixa(FluxoCaixa fluxoCaixa, CaixaDiario caixaDiario) {
        super("Controle do caixa");
        this.fluxoCaixa = fluxoCaixa;
        this.caixaDiario = caixaDiario;
        atributosJanela();
        atributosElementos();
        setVisible(true);
    }

    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        menuPrincipal.setVisible(false);
    }

    public void atributosJanela() {
        setLayout(null);
        setSize(605, 305);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(corFundoPainel);
        addWindowListener(windowListener);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private WindowListener windowListener = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().dispose();
            menuPrincipal.setVisible(true);
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    };

    public void atributosElementos() {
        escritoPedido.setFont(fonteEscritos);
        escritoMesa.setFont(fonteEscritos);
        escritoEntrada.setFont(fonteEscritos);
        escritoSaida.setFont(fonteEscritos);
        escritoValorPedido.setFont(fonteEscritos);
        escritoValorTotalDiario.setFont(fonteEscritos);
        entrada.setFont(fonteEscritos);
        pedido.setFont(fonteEscritos);
        mesa.setFont(fonteEscritos);
        entrada.setFont(fonteEscritos);
        saida.setFont(fonteEscritos);
        valorPedido.setFont(fonteEscritos);
        valorTotalDiario.setFont(fonteEscritos);
        escritoCaixaDiarioTitulo.setFont(fonteTitulosListas);
        escritoPedidoTitulo.setFont(fonteTitulosListas);
        escritoProdutoTitulo.setFont(fonteTitulosListas);
        entrada.setOpaque(true);
        pedido.setOpaque(true);
        mesa.setOpaque(true);
        entrada.setOpaque(true);
        saida.setOpaque(true);
        valorPedido.setOpaque(true);
        valorTotalDiario.setOpaque(true);
        entrada.setBackground(corFundoPaineisNumericos);
        pedido.setBackground(corFundoPaineisNumericos);
        mesa.setBackground(corFundoPaineisNumericos);
        entrada.setBackground(corFundoPaineisNumericos);
        saida.setBackground(corFundoPaineisNumericos);
        valorPedido.setBackground(corFundoPaineisNumericos);
        valorTotalDiario.setBackground(corFundoPaineisNumericos);
        remover.setBackground(corFundoBotoes);
        escritoPedido.setBounds(20, 180, 60, 20);
        escritoMesa.setBounds(195, 180, 45, 20);
        escritoEntrada.setBounds(20, 205, 70, 20);
        escritoSaida.setBounds(320, 205, 50, 20);
        escritoValorPedido.setBounds(290, 180, 110, 20);
        escritoValorTotalDiario.setBounds(20, 230, 210, 20);
        pedido.setBounds(85, 180, 100, 20);
        mesa.setBounds(245, 180, 40, 20);
        entrada.setBounds(95, 205, 220, 20);
        saida.setBounds(375, 205, 200, 20);
        valorPedido.setBounds(405, 180, 170, 20);
        valorTotalDiario.setBounds(235, 230, 245, 20);
        remover.setBounds(485, 230, 90, 20);
        escritoCaixaDiarioTitulo.setBounds(50, 15, 120, 20);
        escritoPedidoTitulo.setBounds(254, 15, 80, 20);
        escritoProdutoTitulo.setBounds(436, 15, 90, 20);
        rolagemCaixaDiario.setBounds(20, 40, 180, 130);
        rolagemPedidos.setBounds(201, 40, 186, 130);
        rolagemProdutos.setBounds(388, 40, 186, 130);
        entrada.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        pedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mesa.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        entrada.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        saida.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        valorPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        valorTotalDiario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        rolagemCaixaDiario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        rolagemPedidos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        rolagemProdutos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        remover.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        listaCaixaDiario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaCaixaDiario.setModel(new CaixaDiarioListModel(fluxoCaixa.getCaixa()));
        listaCaixaDiario.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (listaCaixaDiario.isSelectionEmpty()) {
                    listaPedidos.setModel(new DefaultListModel<Pedido>());
                    listaProdutos.setModel(new DefaultListModel<Produto>());
                    pedido.setText("");
                    mesa.setText("");
                    valorPedido.setText("");
                    entrada.setText("");
                    saida.setText("");
                    valorTotalDiario.setText("");
                    return;
                }
                CaixaDiario selecionado = listaCaixaDiario.getSelectedValue();
                if (selecionado != null) {
                    listaPedidos.setModel(new PedidoListModel(selecionado.getPedidos()));
                    if (selecionado.getQuantidadePedidos() != 0) ///Para não ocorrer o nullPointerException quantidade tem que ser diferente de zero
                    {
                        valorTotalDiario.setText(selecionado.calcularValorArrecadado());
                    } else {
                        pedido.setText("");
                        mesa.setText("");
                        valorPedido.setText("");
                        entrada.setText("");
                        saida.setText("");
                        valorTotalDiario.setText("");
                    }

                }
            }
        });
        listaPedidos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (listaPedidos.isSelectionEmpty()) {
                    listaProdutos.setModel(new DefaultListModel<Produto>());
                    pedido.setText("");
                    mesa.setText("");
                    valorPedido.setText("");
                    entrada.setText("");
                    saida.setText("");
                    if (listaCaixaDiario.getSelectedValue().getQuantidadePedidos() != 0) {
                        valorTotalDiario.setText(listaCaixaDiario.getSelectedValue().calcularValorArrecadado());
                    }
                    return;
                }
                Pedido selecionado = listaPedidos.getSelectedValue();
                if (selecionado != null) {
                    listaProdutos.setModel(new ProdutoListModel(selecionado.getProdutos()));
                    pedido.setText(selecionado.getNumeroPedido() + "");
                    mesa.setText(selecionado.getNumeroMesa() + "");
                    valorPedido.setText(selecionado.getStringPrecoTotal());
                    entrada.setText(selecionado.getCalendarioInicial().dataToString() + " -- " + selecionado.getCalendarioInicial().hourToString());
                    saida.setText(selecionado.getCalendarioFinal().dataToString() + " -- " + selecionado.getCalendarioFinal().hourToString());
                    valorTotalDiario.setText(listaCaixaDiario.getSelectedValue().calcularValorArrecadado());
                }
            }
        });
        remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaCaixaDiario.isSelectionEmpty() == false) {
                    CaixaDiario caixa = listaCaixaDiario.getSelectedValue();
                    Pedido ped = listaPedidos.getSelectedValue();
                    if(ped == null){                                            //Se não foi selecionado nenhum pedido
                        int opcao = JOptionPane.showConfirmDialog(null, "<HTML><CENTER>Deseja realmente remover os dados do dia " + caixaDiario.toString() + " ?</CENTER><br>(AVISO: ISTO PODE COMPROMETER A CONTABILIZAÇÃO DAS CONTAS NO FINAL DO MÊS).</HTML>", "Remover caixa diario", JOptionPane.INFORMATION_MESSAGE);
                        if (opcao == 0) {
                            if(caixa.getCalendario().dataToString().equals(caixaDiario.getCalendario().dataToString())==false){            //Se não for a data de hoje
                                fluxoCaixa.remover(caixa);
                                listaCaixaDiario.clearSelection();
                                listaPedidos.clearSelection();
                                listaProdutos.clearSelection();
                                listaPedidos.setModel(new DefaultListModel<Pedido>());
                                listaProdutos.setModel(new DefaultListModel<Produto>());
                                listaCaixaDiario.updateUI();
                                ControleArquivos.atualizarArquivoControleCaixa(fluxoCaixa);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "<HTML><CENTER>Não se pode remover os dados do dia atual pois pode prejudicar o sistema.</CENTER></HTML>", "Remover caixa diario", JOptionPane.ERROR_MESSAGE);
                            }
                        }    
                    }
                    else{
                        int opcao = JOptionPane.showConfirmDialog(null, "<HTML><CENTER>Deseja realmente remover o pedido " + ped.getNumeroPedido() + " ?</CENTER><br>(AVISO: ISTO PODE COMPROMETER A CONTABILIZAÇÃO DAS CONTAS NO FINAL DO MÊS).</HTML>", "Remover pedido", JOptionPane.INFORMATION_MESSAGE);
                        if (opcao == 0) {
                            if(caixa.getCalendario().dataToString().equals(caixaDiario.getCalendario().dataToString())==true){            //Se for a data de hoje
                                caixaDiario.remover(ped);
                                for(int i = 0; i < caixaDiario.getQuantidadePedidos(); i++){            ///Ajustando o número dos pedidos devido a remoção
                                    caixaDiario.getPedidos().get(i).setNumeroPedido(i+1);
                                }
                                listaPedidos.clearSelection();
                                listaProdutos.clearSelection();
                                listaProdutos.setModel(new DefaultListModel<Produto>());
                                listaCaixaDiario.updateUI();
                                listaPedidos.updateUI();
                                valorTotalDiario.setText("");
                                ControleArquivos.atualizarArquivoControleCaixa(fluxoCaixa);
                            }
                            else{                                               //Se não for a data de hoje
                                JOptionPane.showMessageDialog(null, "<HTML><CENTER>Não se pode remover um pedido que não seja do dia atual pois pode prejudicar o sistema.</CENTER></HTML>", "Remover pedido", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });

        add(escritoPedido);
        add(escritoMesa);
        add(escritoEntrada);
        add(escritoSaida);
        add(escritoValorPedido);
        add(escritoValorTotalDiario);
        add(entrada);
        add(pedido);
        add(mesa);
        add(entrada);
        add(saida);
        add(valorPedido);
        add(valorTotalDiario);
        add(escritoCaixaDiarioTitulo);
        add(escritoPedidoTitulo);
        add(escritoProdutoTitulo);
        add(rolagemCaixaDiario);
        add(rolagemProdutos);
        add(rolagemPedidos);
        add(remover);
    }

}
