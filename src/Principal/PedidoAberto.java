package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PedidoAberto extends JFrame {

    /**
     * **************************************************************************************
     * Classe responsável por exibir uma interface de pedidos que estão abertos
     * no sistema. *
     * *************************************************************************************
     */
    private List<Pedido> pedidos = new ArrayList<>();
    private JList<Pedido> listaPedidos = new JList<>(new DefaultListModel<Pedido>());
    private JList<Produto> listaProdutosPedido = new JList<>(new DefaultListModel<Produto>());
    private Color corFundoPainel = new Color(155, 215, 245);
    private MenuPrincipal menuPrincipal = new MenuPrincipal();
    private JScrollPane rolagemPedidos = new JScrollPane(listaPedidos);
    private JScrollPane rolagemProdutosPedido = new JScrollPane(listaProdutosPedido);
    private JLabel escritoProdutoTitulo = new JLabel("Produtos");
    private JLabel escritoPedidoTitulo = new JLabel("Pedidos");                 //Titulo da lista
    private JLabel escritoEntrada = new JLabel("Entrada:");
    private JLabel escritoValorPedido = new JLabel("Valor Total:");
    private JLabel escritoMesa = new JLabel("Mesa:");
    private JLabel valorPedido = new JLabel("", SwingConstants.CENTER);
    private JLabel entrada = new JLabel("", SwingConstants.CENTER);
    private JLabel mesa = new JLabel("", SwingConstants.CENTER);
    private Font fonteEscritos = new Font("Georgia", Font.BOLD, 15);
    private Font fonteTitulosListas = new Font("Georgia", Font.BOLD, 18);
    private JButton finalizarPedido = new JButton("Finalizar pedido");
    private JButton editarPedido = new JButton("Editar pedido");
    private Color corFundoBotoes = new Color(255, 198, 198);
    private Color corFundoPaineisNumericos = new Color(209, 238, 238);
    private Cardapio cardapio;
    private CadastrarPedido cadastrarPedido;
    private CaixaDiario caixaDiario = new CaixaDiario();                        //Atributo responsável por armazenar todos os pedidos do mesmo dia, após ser finalizado
    private FluxoCaixa fluxoCaixa = new FluxoCaixa();

    public PedidoAberto(Cardapio cardapio, List<Pedido> pedidos, CaixaDiario caixaDiario, FluxoCaixa fluxoCaixa) throws HeadlessException {
        super("Pedidos Abertos");
        this.cardapio = cardapio;
        this.pedidos = pedidos;
        this.caixaDiario = caixaDiario;
        this.fluxoCaixa = fluxoCaixa;
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
        setSize(550, 270);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
        escritoPedidoTitulo.setBounds(121, 18, 80, 20);
        escritoProdutoTitulo.setBounds(358, 18, 90, 20);
        rolagemPedidos.setBounds(20, 40, 256, 130);
        rolagemProdutosPedido.setBounds(280, 40, 250, 130);
        finalizarPedido.setBounds(160, 200, 116, 20);
        editarPedido.setBounds(380, 200, 150, 20);
        escritoEntrada.setBounds(20, 175, 70, 20);
        escritoMesa.setBounds(45, 200, 45, 20);
        mesa.setBounds(95, 200, 60, 20);
        entrada.setBounds(95, 175, 181, 20);
        escritoValorPedido.setBounds(280, 175, 95, 20);
        valorPedido.setBounds(380, 175, 150, 20);
        valorPedido.setOpaque(true);
        entrada.setOpaque(true);
        mesa.setOpaque(true);
        valorPedido.setBackground(corFundoPaineisNumericos);
        entrada.setBackground(corFundoPaineisNumericos);
        mesa.setBackground(corFundoPaineisNumericos);
        finalizarPedido.setBackground(corFundoBotoes);
        editarPedido.setBackground(corFundoBotoes);
        finalizarPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        editarPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        entrada.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        valorPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mesa.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        escritoProdutoTitulo.setFont(fonteTitulosListas);
        escritoPedidoTitulo.setFont(fonteTitulosListas);
        escritoEntrada.setFont(fonteEscritos);
        entrada.setFont(fonteEscritos);
        escritoValorPedido.setFont(fonteEscritos);
        valorPedido.setFont(fonteEscritos);
        escritoMesa.setFont(fonteEscritos);
        listaPedidos.setModel(new PedidoListModel(pedidos));         ///Atribui o modelo da lista com base nos produtos do cardapio
        listaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPedidos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Pedido selecionado = listaPedidos.getSelectedValue();
                if (selecionado != null) {
                    listaProdutosPedido.setModel(new ProdutoListModel(selecionado.getProdutos()));
                    entrada.setText(selecionado.getCalendarioInicial().dataToString()+" -- "+selecionado.getCalendarioInicial().hourToString());
                    valorPedido.setText(selecionado.getStringPrecoTotal());
                    mesa.setText(selecionado.getNumeroMesa()+"");
                } else {
                    listaProdutosPedido.setModel(new DefaultListModel<Produto>());
                    entrada.setText("");
                    valorPedido.setText("");
                    mesa.setText("");
                }
            }
        });
        finalizarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaPedidos.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um pedido a ser finalizado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Finalizar pedido no valor de " + listaPedidos.getSelectedValue().getStringPrecoTotal(), "Finalizar", JOptionPane.INFORMATION_MESSAGE);
                    if (opcao == 0) {
                        Pedido pedido = listaPedidos.getSelectedValue();
                        Date data = new Date();                                                 //Responsável para controlar a data e hora antes e depois de finalizar o pedido                                              
                        Calendar cal = Calendar.getInstance();                                  //Captura a instancia de calendario
                        cal.setTime(data);                                                      //Atribui a data a ela
                        pedido.setCalendarioFinal(new Calendario(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)));
                        pedidos.remove(listaPedidos.getSelectedValue());
                        pedido.setNumeroPedido(caixaDiario.getPedidos().size() + 1);
                        caixaDiario.adicionar(pedido);
                        listaProdutosPedido.setModel(new DefaultListModel<Produto>());
                        listaPedidos.clearSelection();
                        listaPedidos.updateUI();
                        ControleArquivos.atualizaArquivoPedidosAbertos(pedidos);
                        ControleArquivos.atualizarArquivoControleCaixa(fluxoCaixa);
                    }
                }
            }
        });
        editarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaPedidos.isSelectionEmpty()) {
                    return;
                }
                Pedido pedido = listaPedidos.getSelectedValue();
                listaPedidos.clearSelection();
                listaPedidos.updateUI();
                cadastrarPedido = new CadastrarPedido(cardapio, pedidos, pedido);
                cadastrarPedido.setPedidoAberto(PedidoAberto.this);
            }
        });
        add(escritoProdutoTitulo);
        add(escritoPedidoTitulo);
        add(rolagemPedidos);
        add(rolagemProdutosPedido);
        add(finalizarPedido);
        add(editarPedido);
        add(escritoEntrada);
        add(entrada);
        add(escritoValorPedido);
        add(valorPedido);
        add(escritoMesa);
        add(mesa);
    }

}
