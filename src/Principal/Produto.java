package Principal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Produto {
    
    /********************************************************
     * Classe criada para representar um produto no sistema.*
     ********************************************************/
    private String nome;
    private Integer quantidade = 0;
    private BigDecimal preco;                                   ///BigDecimal é usado para trabalhar melhor com dinheiro
    
    public Produto(String nome, BigDecimal preco){
        preco.setScale(2, RoundingMode.HALF_EVEN);
        this.preco = preco;
        this.nome = nome;
    }

    public BigDecimal getPreco() { 
        return preco;
    }
    
    public BigDecimal getPrecoTotal() {                  ///Retorna o preço total da quantidade dos produtos     
        return preco.multiply(new BigDecimal(quantidade)).setScale(2, RoundingMode.HALF_EVEN);
    }
    
    public String getStringPreco() {                           ///Retorna o preço formatado para R$
        String stringPreco = new String(preco.toString());
        if(stringPreco.substring(stringPreco.indexOf("."), stringPreco.length()).length()!=3)   //Se as unidades estão no formato inccorreto por exemplo: "8.2" ao invés de "8.20"
        {
            stringPreco = stringPreco+"0";                  ///Ajustando o problema da unidade
        }
        return "R$ "+stringPreco; 
    }
    
    public String getStringPrecoTotal() {                  ///Retorna o preço total formatado para R$  
        String stringPreco = new String(preco.multiply(new BigDecimal(quantidade)).setScale(2, RoundingMode.HALF_EVEN).toString());
        if(stringPreco.substring(stringPreco.indexOf("."), stringPreco.length()).length()!=3)   //Se as unidades estão no formato inccorreto por exemplo: "8.2" ao invés de "8.20"
        {
            stringPreco = stringPreco+"0";                  ///Ajustando o problema da unidade
        }
        return "R$ "+stringPreco; 
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void adicionar() {
        this.quantidade = this.quantidade + 1;
    }
    
    public void remover() {
        this.quantidade = this.quantidade - 1;
    }
    
    public String toString(){
        if(quantidade >= 1)                                         //Voltado para imprimir a lista de produtos contidos no Pedido
            return "(x "+quantidade+") "+nome+" - Preço unitario: "+ getStringPreco()+ " - Preço total: "+ getStringPrecoTotal();
        else                                                        //Voltado para imprimir a lista de produtos do cardápio sem levar em conta a quantidade
            return nome+" - Preço unitario: "+ getStringPreco()+ " ";
    }
}
