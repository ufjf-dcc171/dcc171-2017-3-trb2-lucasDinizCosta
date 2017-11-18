package Principal;

import java.util.ArrayList;
import java.util.List;

public class FluxoCaixa {
    
    /***********************************************************************************
     * Classe criada com o objetivo de armazenar todos os caixas diários finalizados de*
     * cada dia no sistema, ou seja, todos os pedido feitos e finalizados.              *
     ***********************************************************************************/
    
    private List<CaixaDiario> caixa = new ArrayList<CaixaDiario>();
    
    //Rendimento diario
    
    public FluxoCaixa(){
    
    }

    public List<CaixaDiario> getCaixa() {
        return caixa;
    }

    public void setCaixa(List<CaixaDiario> caixa) {
        this.caixa = caixa;
    }
    
    public void adicionar(CaixaDiario caixaDiario){
        caixa.add(caixaDiario);
    }
    
    public void remover(CaixaDiario caixaDiario){
        caixa.remove(caixaDiario);
    }

}
