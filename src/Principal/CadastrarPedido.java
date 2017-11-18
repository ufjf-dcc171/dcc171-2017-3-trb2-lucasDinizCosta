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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CadastrarPedido extends JFrame {
    
    /****************************************************************************
     * Classe responsável pela interface de CadastrarPedido caso o fluxo seja 1,*
     * se for 2 será responsável por editar um pedido feito, de modo a inserir  *
     * mais produtos.                                                           *
     ****************************************************************************/

    private List<Produto> produtos = new ArrayList<>();                         //Serão exibidos na lista
    private List<Produto> produtosAuxFluxo2 = new ArrayList<>();                //Receberão os produtos no caso de o pedido ser inserido mais produtos
    //Logo deve-se guardar a quantidade de produtos que haviam na lista para
    //Não haver exclusões além
    
    private JList<Produto> listaProdutosCardapio = new JList<>(new DefaultListModel<Produto>());
    private JList<Produto> listaProdutosPedido = new JList<>(new DefaultListModel<Produto>());
    private JButton concluirPedido = new JButton();
    private JButton adicionar = new JButton();
    private JButton remover = new JButton();
    private Pedido pedido = new Pedido();
    private Color corFundoPainel = new Color(155, 215, 245);
    private Color corFundoPaineisNumericos = new Color(209, 238, 238);
    private Color corFundoBotoes = new Color(255, 198, 198);
    private List<Pedido> listaPedidosAbertos = new ArrayList<Pedido>();
    private MenuPrincipal menuPrincipal = new MenuPrincipal();
    private PedidoAberto pedidoAberto;
    private JScrollPane rolagemCardapio = new JScrollPane(listaProdutosCardapio);
    private JScrollPane rolagemPedido = new JScrollPane(listaProdutosPedido);
    private JLabel escritoCardapioTitulo = new JLabel("Cardápio");
    private JLabel escritoPedidoTitulo = new JLabel("Pedido");                  //Titulo da lista
    private JLabel escritoPedido = new JLabel("Número do pedido:");
    private JLabel numeroPedido = new JLabel("0");
    private Font fonteTitulosListas = new Font("Georgia", Font.BOLD, 18);
    private Font fonte = new Font("Georgia", Font.BOLD, 15);
    private ImageIcon imagemAdicionar = new ImageIcon("icones/adicionar.png");
    private ImageIcon imagemRemover = new ImageIcon("icones/remover.png");
    private ImageIcon imagemConcluir = new ImageIcon("icones/concluir.png");
    private JTextField campoNumeroMesa = new JTextField();
    private JLabel escritoMesa = new JLabel("Número da mesa: ");
    private Integer numeroMaxMesa = 0;                                          //Numero maximo de mesas, de modo a limitar o campo de texto e não digitar uma mesa invalida
    private Integer fluxo = 1;                                                  //A janela se comportara dependendo de acordo com 2 fluxos, fluxo 1 é responsável por adicionar um pedido novo
    //fluxo 2 é responsável por receber um pedido e adicionar produtos a ele

    public CadastrarPedido(Cardapio cardapio, List<Pedido> listaPedidosAbertos, Integer numeroMaxMesa) throws HeadlessException {
        super("Cadastrar Pedido");
        this.produtos = cardapio.getCardapio();                                 //Passando a lista do cardapio para a lista de produtos a ser exibido
        this.listaPedidosAbertos = listaPedidosAbertos;
        this.numeroMaxMesa = numeroMaxMesa;
        this.fluxo = 1;
        atributosJanela();
        atributosElementos();
        Date data = new Date();                                                      //Responsável para controlar a data e hora antes e depois de finalizar o pedido                                              
        Calendar cal = Calendar.getInstance();                                           //Captura a instancia de calendario
        cal.setTime(data);                                                      //Atribui a data a ela
        pedido.setCalendarioInicial(new Calendario(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)));
        setVisible(true);
    }

    public CadastrarPedido(Cardapio cardapio, List<Pedido> listaPedidosAbertos, Pedido pedido) throws HeadlessException {
        this.produtos = cardapio.getCardapio();                                 //Passando a lista do cardapio para a lista de produtos a ser exibido
        this.listaPedidosAbertos = listaPedidosAbertos;
        this.pedido = pedido;
        this.fluxo = 2;
        for(int i = 0; i < pedido.getProdutos().size(); i++){                   //Criando uma lista auxiliar para guardar os produtos e quantidades antes da modificação
            Produto prod = new Produto(pedido.getProdutos().get(i).getNome(),pedido.getProdutos().get(i).getPreco());
            prod.setQuantidade(pedido.getProdutos().get(i).getQuantidade());
            produtosAuxFluxo2.add(prod);
        }
        atributosJanela();
        atributosElementos();
        setVisible(true);
    }

    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        menuPrincipal.setVisible(false);
    }

    public void setPedidoAberto(PedidoAberto pedidoAberto) {
        this.pedidoAberto = pedidoAberto;
        pedidoAberto.setVisible(false);
    }

    public void atributosJanela() {
        setLayout(null);
        setSize(500, 275);
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
            if (fluxo == 1) {
                e.getWindow().dispose();
                menuPrincipal.setVisible(true);
            } else if (fluxo == 2) {
                e.getWindow().dispose();
                pedidoAberto.setVisible(true);
            }
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
        if(fluxo == 1){                     ///Vindo do menu Principal
            numeroPedido.setText((listaPedidosAbertos.size() + 1) + "");
            pedido.setNumeroPedido(listaPedidosAbertos.size() + 1);
        }
        else if(fluxo == 2){                ///Vindo do pedido aberto
            numeroPedido.setText(pedido.getNumeroPedido()+"");
            campoNumeroMesa.setText(pedido.getNumeroMesa()+"");
            campoNumeroMesa.setEditable(false);
        }
        
        escritoCardapioTitulo.setBounds(90, 18, 90, 20);
        escritoPedidoTitulo.setBounds(327, 18, 80, 20);
        rolagemCardapio.setBounds(20, 40, 225, 130);
        rolagemPedido.setBounds(244, 40, 225, 130);
        escritoPedido.setBounds(235, 185, 150, 20);
        adicionar.setBounds(20, 185, 30, 30);
        remover.setBounds(110, 185, 30, 30);
        concluirPedido.setBounds(200, 185, 30, 30);
        numeroPedido.setBounds(390, 185, 80, 20);
        escritoMesa.setBounds(248, 210, 139, 20);
        campoNumeroMesa.setBounds(390, 210, 80, 20);
        numeroPedido.setOpaque(true);
        numeroPedido.setBackground(corFundoPaineisNumericos);
        adicionar.setBackground(corFundoBotoes);
        remover.setBackground(corFundoBotoes);
        concluirPedido.setBackground(corFundoBotoes);
        adicionar.setIcon(imagemAdicionar);
        remover.setIcon(imagemRemover);
        concluirPedido.setIcon(imagemConcluir);
        campoNumeroMesa.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        rolagemCardapio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        rolagemPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        numeroPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        adicionar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        remover.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        concluirPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        escritoCardapioTitulo.setFont(fonteTitulosListas);
        escritoPedidoTitulo.setFont(fonteTitulosListas);
        escritoPedido.setFont(fonte);
        escritoMesa.setFont(fonte);
        numeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
        numeroPedido.setVerticalAlignment(SwingConstants.CENTER);
        campoNumeroMesa.setBackground(corFundoPaineisNumericos);
        listaProdutosCardapio.setModel(new ProdutoListModel(produtos));         ///Atribui o modelo da lista com base nos produtos do cardapio
        listaProdutosCardapio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProdutosCardapio.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Produto selecionado = listaProdutosCardapio.getSelectedValue();
            }
        });
        listaProdutosPedido.setModel(new ProdutoListModel(pedido.getProdutos()));///Atribui o modelo da lista com base nos produtos do pedido
        listaProdutosPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProdutosPedido.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Produto selecionado = listaProdutosPedido.getSelectedValue();

            }
        });
        adicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaProdutosCardapio.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto da lista do cardápio para inserir na lista do pedido", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Produto produto = listaProdutosCardapio.getSelectedValue();
                pedido.adicionarProduto(produto);
                listaProdutosCardapio.clearSelection();
                listaProdutosPedido.clearSelection();
                listaProdutosCardapio.updateUI();
                listaProdutosPedido.updateUI();
            }
        });
        remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaProdutosPedido.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto da lista do pedido para removê-lo da lista", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Produto produto = listaProdutosPedido.getSelectedValue();
                boolean remove = true;                                          //Se continuar true, o produto poderá ser removido
                if(fluxo == 2){                                                 //Só é feita a verificação de remoção caso a janela esteja sendo usada a partir de pedidoAberto
                    for(int i = 0; i < produtosAuxFluxo2.size(); i++){
                       if(produto.getQuantidade() == produtosAuxFluxo2.get(i).getQuantidade()){
                           
                           remove = false;
                           break;
                       }
                    }
                }
                if(remove == true){
                    pedido.removerProduto(produto);
                    listaProdutosCardapio.clearSelection();
                    listaProdutosPedido.clearSelection();
                    listaProdutosCardapio.updateUI();
                    listaProdutosPedido.updateUI();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Não é permitido remover um produto na quantidade inferior de quando o pedido foi cadastrado", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
                    
            }
        });
        concluirPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fluxo == 1){         //MenuPrincipal
                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja confirmar o pedido no valor total de " + pedido.getStringPrecoTotal() + " ?", "Opcao", JOptionPane.INFORMATION_MESSAGE);
                    switch (opcao) {
                        case 0:                         //Sim
                            try {
                                Integer mesa = Integer.parseInt(campoNumeroMesa.getText());
                                if (mesa <= numeroMaxMesa && mesa > 0) {
                                    dispose();
                                    pedido.setNumeroMesa(mesa);
                                    listaPedidosAbertos.add(pedido);
                                    ControleArquivos.atualizaArquivoPedidosAbertos(listaPedidosAbertos);
                                    menuPrincipal.setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Digite o número de uma mesa válida entre 1 e " + numeroMaxMesa, "ERRO", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException erro) {
                                JOptionPane.showMessageDialog(null, "Digite um número inteiro válido para as mesas!!!", "ERRO", JOptionPane.ERROR_MESSAGE);
                            }

                            break;
                        case 1:                         //Não
                            break;
                    }
                }
                else if(fluxo == 2){        //Pedido Aberto
                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja confirmar a alteração no pedido "+pedido.getNumeroPedido()+" ? o valor total após a alteração é de " + pedido.getStringPrecoTotal() + " ?", "Opcao", JOptionPane.INFORMATION_MESSAGE);
                    switch (opcao) {
                        case 0:                         //Sim
                            ControleArquivos.atualizaArquivoPedidosAbertos(listaPedidosAbertos);
                            dispose();
                            pedidoAberto.setVisible(true);
                            break;
                        case 1:                         //Não
                            break;
                    }
                }
                
            }
        });
        add(escritoCardapioTitulo);
        add(escritoPedidoTitulo);
        add(rolagemCardapio);
        add(rolagemPedido);
        add(escritoPedido);
        add(numeroPedido);
        add(adicionar);
        add(remover);
        add(concluirPedido);
        add(escritoMesa);
        add(campoNumeroMesa);
    }
}
