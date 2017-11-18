package Principal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    
    /******************************************************************************************
     * Classe criada com o objetivo de representar as caracteristicas de um Pedido no sistema *
     ******************************************************************************************/

    private List<Produto> produtos = new ArrayList<Produto>();                  ///Armazena os produtos contido no pedido
    private Calendario calendarioInicial = new Calendario();                    ///Captura os dados de data e hora inicial e na finalização do pedido
    private Calendario calendarioFinal = new Calendario();                      ///Captura os dados de data e hora finais
    private int numeroPedido = 0;                                               ///Corresponde ao número do pedido presente na lista de pedidos, parecido com a ideia de comanda
    private int numeroMesa = 0;

    public Pedido() {

    }
    
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroDaMesa) {
        this.numeroMesa = numeroDaMesa;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
    
    public String getStringPrecoTotal(){                                              ///Retorna o valor total de todos os produtos
        BigDecimal precoTotal = new BigDecimal(0.0);
        precoTotal.setScale(2, RoundingMode.HALF_EVEN);                         ///Duas casas decimais
        for(int i = 0; i < produtos.size(); i++){                               ///Captura o preço total de tudo
            precoTotal = precoTotal.add(produtos.get(i).getPrecoTotal()).setScale(2, RoundingMode.HALF_EVEN);
        }
        String stringPreco = new String(precoTotal.toString());                                 //Converte para String
        if(stringPreco.substring(stringPreco.indexOf("."), stringPreco.length()).length()!=3)   //Se as unidades estão no formato inccorreto por exemplo: "8.2" ao invés de "8.20"
        {
            stringPreco = stringPreco+"0";                  ///Ajustando o problema da unidade
        }
        return "R$ "+stringPreco;  
    }
    
    public BigDecimal getPrecoTotal(){                                          ///Retorna o valor total de todos os produtos
        BigDecimal precoTotal = new BigDecimal(0.0);
        precoTotal.setScale(2, RoundingMode.HALF_EVEN);                         ///Duas casas decimais
        for(int i = 0; i < produtos.size(); i++){                               ///Captura o preço total de tudo
            precoTotal = precoTotal.add(produtos.get(i).getPrecoTotal()).setScale(2, RoundingMode.HALF_EVEN);
        }
        String stringPreco = new String(precoTotal.toString());                                 //Converte para String
        if(stringPreco.substring(stringPreco.indexOf("."), stringPreco.length()).length()!=3)   //Se as unidades estão no formato inccorreto por exemplo: "8.2" ao invés de "8.20"
        {
            stringPreco = stringPreco+"0";                  ///Ajustando o problema da unidade
        }
        return new BigDecimal(stringPreco);  
    }

    public void adicionarProduto(Produto prod) {
        boolean adicionado = false;                                 ///Se for adicionado ele será verdadeiro
        for (int i = 0; i < produtos.size(); i++) {                 ///Verifica se o produto está presente no arraylist
            if (produtos.get(i).getNome().equals(prod.getNome()))   ///Se estiver presente só será incrementado o contador de quantidade
            {
                produtos.get(i).adicionar();                        ///Incrementa o contador de quantidade de produtos iguais
                adicionado = true;
                break;                                              ///Sai do loop
            }
        }
        if (adicionado == false) {                                    ///Produto não encontrado, então é adicionado no arraylist e incrementado o contador de produtos
            Produto aux = new Produto(prod.getNome(), prod.getPreco());          
            ///Se coloco Produto aux = prod  ou tento inserir prod no arraylist de produtos
            ///Da problema pois ele passa o objeto presente no cardápio e quando atualizo a quantidade
            ///na lista do pedido automaticamente acaba atualizando o objeto do cardapio também
            ///Desse modo não ocorre problema pois ele cria um novo produto distinto com os dados especificados
            aux.adicionar();
            produtos.add(aux);
        }
    }

    public void removerProduto(Produto prod) {
        for (int i = 0; i < produtos.size(); i++) {                      ///Verifica se o produto está presente no arraylist
            if (produtos.get(i).getNome().equals(prod.getNome()))       ///Se estiver presente só será incrementado o contador de quantidade
            {
                if (produtos.get(i).getQuantidade() > 1)                ///Se houver mais de um item só é decrementado o contador de quantidade
                {
                    produtos.get(i).remover();
                } else if (produtos.get(i).getQuantidade() == 1) {      ///Se houver somente 1 item, o produto será apagado da lista
                    produtos.remove(produtos.get(i));
                }
                break;                                                  ///Sai do loop
            }
        }
    }

    public Calendario getCalendarioInicial() {
        return calendarioInicial;
    }

    public void setCalendarioInicial(Calendario calendarioInicial) {
        this.calendarioInicial = calendarioInicial;
    }

    public Calendario getCalendarioFinal() {
        return calendarioFinal;
    }

    public void setCalendarioFinal(Calendario calendarioFinal) {
        this.calendarioFinal = calendarioFinal;
    }

    public String toString() {
        return "Pedido "+ numeroPedido;
    }
}
