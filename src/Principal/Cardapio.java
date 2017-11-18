package Principal;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    
    /***********************************************************************************
     * Controla os produtos presentes no sistema, mais precisamente os que poderão ser *
     * inseridos no pedido                                                             *
     ***********************************************************************************/
    
    private List<Produto> cardapio = new ArrayList<Produto>();
    
    Cardapio(){
    
    }

    public List<Produto> getCardapio() {
        return cardapio;
    }

    public void setCardapio(List<Produto> cardapio) {
        this.cardapio = cardapio;
    }
    
    public void adicionarProduto(Produto prod){
        cardapio.add(prod);
    }
}
