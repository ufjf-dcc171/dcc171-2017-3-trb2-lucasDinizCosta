package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CadastrarProduto extends JFrame{

    private JList<Produto> listaProdutos = new JList<>(new DefaultListModel<Produto>());
    private JButton adicionar = new JButton();
    private JButton remover = new JButton();
    private JButton modificar = new JButton();
    private JLabel escritoNome = new JLabel("Nome:");
    private JLabel escritoPreco = new JLabel("Preço(R$):");
    private JLabel escritoTituloTabela = new JLabel("Produtos:");
    private JTextField caixaNome = new JTextField(30);
    private JTextField caixaPreco = new JTextField(30);
    private MenuPrincipal menuPrincipal;
    private Color corFundoPainel = new Color(155, 215, 245);
    private Color corFundoTitulo = new Color(186, 253, 237);
    private Color corFundoBotoes = new Color(255, 198, 198);
    private JScrollPane painelRolagemLista = new JScrollPane(listaProdutos);
    private ImageIcon imagemAdicionar = new ImageIcon("icones/adicionar.png");
    private ImageIcon imagemRemover = new ImageIcon("icones/remover.png");
    private ImageIcon imagemModificar = new ImageIcon("icones/modificar.png");
    private Font fonte = new Font("Georgia", Font.BOLD, 18);
    private Cardapio cardapio = new Cardapio();
    
    public CadastrarProduto(Cardapio cardapio) throws HeadlessException{
        super("Cadastrar Produto");
        this.cardapio = cardapio;
        atributosJanela();
        atributosElementos();
        setVisible(true);
    }

    public void atributosJanela() {
        setLayout(null);
        setSize(350, 320);
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
        escritoNome.setBounds(45, 20, 65, 25);
        caixaNome.setBounds(115, 20, 200, 25);
        escritoPreco.setBounds(5, 50, 105, 25);
        caixaPreco.setBounds(115, 50, 200, 25);
        escritoTituloTabela.setBounds(15, 80, 95, 20);
        painelRolagemLista.setBounds(115, 80, 200, 130);
        adicionar.setBounds(115, 220, 30, 30);
        remover.setBounds(200, 220, 30, 30);
        modificar.setBounds(285, 220, 30, 30);
        adicionar.setBackground(corFundoBotoes);
        remover.setBackground(corFundoBotoes);
        modificar.setBackground(corFundoBotoes);
        caixaNome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        caixaPreco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        painelRolagemLista.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        adicionar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        remover.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        modificar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        escritoNome.setFont(fonte);
        escritoPreco.setFont(fonte);
        escritoTituloTabela.setFont(fonte);
        adicionar.setIcon(imagemAdicionar);
        remover.setIcon(imagemRemover);
        modificar.setIcon(imagemModificar);
        adicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!caixaNome.getText().isEmpty() && !caixaPreco.getText().isEmpty()) {            //Verifica se os campos não são vazio
                    if (caixaPreco.getText().substring(caixaPreco.getText().indexOf("."), caixaPreco.getText().length()).length() == 3) {                 ///Se o campo preço está digitado corretamente .00
                        Produto prod = new Produto(caixaNome.getText(), new BigDecimal(caixaPreco.getText()));
                        boolean verificacao = false;                                            ///Verifica se o produto já está presente
                        for (int i = 0; i < cardapio.getCardapio().size(); i++) {
                            if (cardapio.getCardapio().get(i).getNome().equals(caixaNome.getText())) {
                                verificacao = true;
                                break;
                            }
                        }
                        if (verificacao == true) {
                            JOptionPane.showMessageDialog(null, "Há um produto cadastrado com este nome.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            cardapio.getCardapio().add(prod);                     ///Adiciona o produto na lista e atualiza a interface
                            listaProdutos.updateUI();
                            ControleArquivos.atualizaArquivoCardapio(cardapio);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "<HTML>Preencha o campo de preco usando ponto e duas casas após o ponto</HTML>", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    if (caixaNome.getText().isEmpty()) {
                        caixaNome.requestFocus();
                    } else {
                        caixaPreco.requestFocus();
                    }
                    JOptionPane.showConfirmDialog(null, "<HTML>Preencha os campos de nome e preço para<br>poder adicionar um produto.</HTML>", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
                listaProdutos.clearSelection();
            }
        });
        remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaProdutos.isSelectionEmpty()) {
                    return;
                }
                cardapio.getCardapio().remove(listaProdutos.getSelectedValue());
                listaProdutos.clearSelection();
                listaProdutos.updateUI();
                ControleArquivos.atualizaArquivoCardapio(cardapio);
            }
        });
        modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaProdutos.isSelectionEmpty()) {
                    return;
                }
                if (!caixaNome.getText().isEmpty() && !caixaPreco.getText().isEmpty()) {            //Verifica se os campos não são vazio
                    if (caixaPreco.getText().substring(caixaPreco.getText().indexOf("."), caixaPreco.getText().length()).length() == 3) {                 ///Se o campo preço está digitado corretamente .00
                        Produto prod = new Produto(caixaNome.getText(), new BigDecimal(caixaPreco.getText()));
                        int opcao = JOptionPane.showConfirmDialog(null, "Você deseja modificar o produto selecionado?", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                        if (opcao == 0) {
                            cardapio.getCardapio().set(listaProdutos.getSelectedIndex(), prod);                     ///Adiciona o produto modificado na posicao do antigo
                        } else {

                        }
                        listaProdutos.updateUI();
                        ControleArquivos.atualizaArquivoCardapio(cardapio);
                    } else {
                        JOptionPane.showMessageDialog(null, "<HTML>Preencha o campo de preco usando ponto e duas casas após o ponto</HTML>", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    if (caixaNome.getText().isEmpty()) {
                        caixaNome.requestFocus();
                    } else {
                        caixaPreco.requestFocus();
                    }
                    JOptionPane.showConfirmDialog(null, "<HTML>Preencha os campos de nome e preço para<br>poder adicionar um produto.</HTML>", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
                listaProdutos.clearSelection();
            }
        });
        listaProdutos.setModel(new ProdutoListModel(cardapio.getCardapio()));
        listaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProdutos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Produto selecionado = listaProdutos.getSelectedValue();
                if (selecionado != null) {
                    caixaNome.setText(selecionado.getNome());
                    caixaPreco.setText(selecionado.getPreco() + "");
                } else {
                    caixaNome.setText("");
                    caixaPreco.setText("");
                }

            }
        });
        add(escritoNome);
        add(caixaNome);
        add(escritoPreco);
        add(caixaPreco);
        add(escritoTituloTabela);
        add(painelRolagemLista);
        add(adicionar);
        add(remover);
        add(modificar);
    }

    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        menuPrincipal.setVisible(false);
    }
}
