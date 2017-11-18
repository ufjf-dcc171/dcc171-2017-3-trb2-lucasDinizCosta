package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MenuPrincipal extends JFrame implements ActionListener {
    
    /************************************************************************
     * Classe responsável por exibir e montar a interface básica do sistema.*
     ************************************************************************/

    private Cardapio cardapio = new Cardapio();                                 //Classe responsável por armazenar a lista de produtos cadastrados
    private List<Pedido> listaPedidosAbertos = new ArrayList<Pedido>();         //Lista de pedidos abertos contidos no sistema
    private Integer numeroMesas = 1;                                            //Numero de mesas presente no estabelecimento
    private JLabel escritoMesas = new JLabel("Mesas: ");
    private JLabel escritoNumeroMesas = new JLabel(numeroMesas + "", SwingConstants.CENTER);
    private JLabel escritoMenu = new JLabel("<HTML><CENTER>Sistema de controle de pedidos</CENTER><CENTER>em uma lanchonete</CENTER></HTML>");
    private JButton pedidosAbertos = new JButton("Pedidos Abertos");
    private JButton cadastrarPedidos = new JButton("Cadastrar Pedidos");
    private JButton cadastrarProdutos = new JButton("Cadastrar Produtos");
    private JButton controleCaixa = new JButton("Controle Caixa");
    private JButton adicionar = new JButton();
    private JButton remover = new JButton();                                    //Numero de mesas
    private Font fonteTitulo = new Font("Georgia", Font.BOLD, 18);
    private Font fonte = new Font("Georgia", Font.BOLD, 15);
    private Color corFundoPainel = new Color(155, 215, 245);
    private Color corFundoTitulo = new Color(186, 253, 237);
    private Color corFundoBotoes = new Color(255, 198, 198);
    private ImageIcon imagemAdicionar = new ImageIcon("icones/adicionar.png");
    private ImageIcon imagemRemover = new ImageIcon("icones/remover.png");
    private CaixaDiario caixaDiario;                                            //Responsável por armazenar os pedidos do dia
    private FluxoCaixa fluxoCaixa = new FluxoCaixa();                           //Responsável por armazenar cada caixaDiario para ter um controle do fluxo de caixa dos pedidos relativos ao dia

    public MenuPrincipal() throws HeadlessException {              ///Construtor para passar objetos sem persistência de dados
        super("Menu Principal");
        montaElementosJanela();
        ControleArquivos.lerArquivoCardapio(cardapio);
        ControleArquivos.lerArquivoPedidosAbertos(listaPedidosAbertos);
        ControleArquivos.lerArquivoControleCaixa(fluxoCaixa);
        this.caixaDiario = new CaixaDiario();                                   //Cria uma caixaDiario com a data atual
        boolean encontrou = false;
        if (fluxoCaixa != null) {
            for (int i = 0; i < fluxoCaixa.getCaixa().size(); i++) {            //Tentativa de encontrar o caixaDiario presente
                if (fluxoCaixa.getCaixa().get(i).getCalendario().dataToString().equals(this.caixaDiario.getCalendario().dataToString())) ///Comparo se o caixaDiario com a data do dia já foi inserida, se foi inserido ele será usado no caixaDiario
                {
                    this.caixaDiario = fluxoCaixa.getCaixa().get(i);
                    encontrou = true;
                }
            }
            if(encontrou == false){
                this.fluxoCaixa.adicionar(this.caixaDiario);
            }
        } else {
            this.fluxoCaixa = new FluxoCaixa();
            this.fluxoCaixa.adicionar(this.caixaDiario);
        }
    }
    
    public void montaElementosJanela(){
        addWindowListener(windowListener);
        setLayout(null);
        escritoMenu.setFont(fonteTitulo);
        escritoMenu.setOpaque(true);
        escritoMenu.setBackground(corFundoTitulo);
        getContentPane().setBackground(corFundoPainel);
        escritoMesas.setFont(fonte);
        escritoMesas.setBounds(97, 285, 60, 20);
        escritoNumeroMesas.setFont(fonte);
        escritoNumeroMesas.setBounds(155, 285, 82, 20);
        escritoNumeroMesas.setOpaque(true);
        escritoNumeroMesas.setBackground(Color.WHITE);
        pedidosAbertos.setBackground(corFundoBotoes);
        cadastrarPedidos.setBackground(corFundoBotoes);
        cadastrarProdutos.setBackground(corFundoBotoes);
        controleCaixa.setBackground(corFundoBotoes);
        adicionar.setBackground(corFundoBotoes);
        remover.setBackground(corFundoBotoes);
        escritoMenu.setBounds(25, 15, 300, 55);
        pedidosAbertos.setBounds(97, 95, 140, 25);
        cadastrarPedidos.setBounds(97, 145, 140, 25);
        cadastrarProdutos.setBounds(97, 195, 140, 25);
        controleCaixa.setBounds(97, 245, 140, 25);
        adicionar.setBounds(155, 315, 30, 30);
        remover.setBounds(207, 315, 30, 30);
        escritoMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        escritoNumeroMesas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        pedidosAbertos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cadastrarPedidos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cadastrarProdutos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        controleCaixa.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        adicionar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        remover.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        adicionar.setIcon(imagemAdicionar);
        remover.setIcon(imagemRemover);
        pedidosAbertos.addActionListener(this);
        cadastrarPedidos.addActionListener(this);
        cadastrarProdutos.addActionListener(this);
        controleCaixa.addActionListener(this);
        adicionar.addActionListener(this);
        remover.addActionListener(this);  
        add(escritoMenu);
        add(pedidosAbertos);
        add(cadastrarPedidos);
        add(cadastrarProdutos);
        add(controleCaixa);
        add(adicionar);
        add(escritoMesas);
        add(remover);
        add(escritoNumeroMesas);    
    }
    
    private WindowListener windowListener = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            if(listaPedidosAbertos.size()>=1){
                JOptionPane.showMessageDialog(null, "Há pedidos que não foram finalizados");
            }
            else{
                System.exit(0);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pedidosAbertos) {
            PedidoAberto pedidoAberto = new PedidoAberto(cardapio, listaPedidosAbertos, caixaDiario, fluxoCaixa);
            pedidoAberto.setMenuPrincipal(this);
        } else if (e.getSource() == cadastrarPedidos) {
            CadastrarPedido cadastrarPedidos = new CadastrarPedido(cardapio, listaPedidosAbertos, numeroMesas);
            cadastrarPedidos.setMenuPrincipal(this);
        } else if (e.getSource() == cadastrarProdutos) {
            CadastrarProduto cadastrarProdutos = new CadastrarProduto(cardapio);
            cadastrarProdutos.setMenuPrincipal(this);
        } else if (e.getSource() == controleCaixa) {
            ControleCaixa controleCaixa = new ControleCaixa(fluxoCaixa, caixaDiario);
            controleCaixa.setMenuPrincipal(this);
        } else if (e.getSource() == adicionar) {
            numeroMesas++;
            escritoNumeroMesas.setText(numeroMesas.toString());
        } else if (e.getSource() == remover) {
            if (numeroMesas > 1) {
                numeroMesas--;
                escritoNumeroMesas.setText(numeroMesas.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Não há mais mesas a serem removidas, pois é necessário pelo menos uma mesa.");
            }
        }
    }

}
