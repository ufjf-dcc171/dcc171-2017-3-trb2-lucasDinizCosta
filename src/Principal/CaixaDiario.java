package Principal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CaixaDiario {
    /*************************************************************************
     * Classe responsável por armazenar os pedidos iniciados no dia, será    *
     * auxiliar para retornar o valor arrecadado em um determinado dia.       *
     *************************************************************************/
    
    private List<Pedido> controleCaixa = new ArrayList<Pedido>();
    private Calendario calendario;                                              //Armazena a data
   
    public CaixaDiario(){
        Date data = new Date();                                                 //Responsável para controlar a data do caixa
        Calendar cal;
        cal = Calendar.getInstance();                                           //Captura a instancia de calendario
        cal.setTime(data);                                                      //Atribui a data a ela
        calendario = new Calendario(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
    }
    
    public String calcularValorArrecadado(){                                    //Retorna uma string com o calculo de valor arrecadado no dia
        BigDecimal precoTotal = new BigDecimal(0.0);
        precoTotal.setScale(2, RoundingMode.HALF_EVEN);                         //Duas casas decimais
        for(int i = 0; i < controleCaixa.size(); i++){                          //Captura o preço total de tudo
            precoTotal = precoTotal.add(controleCaixa.get(i).getPrecoTotal()).setScale(2, RoundingMode.HALF_EVEN);
        }
        String stringPreco = new String(precoTotal.toString());                                 //Converte para String
        if(stringPreco.substring(stringPreco.indexOf("."), stringPreco.length()).length()!=3)   //Se as unidades estão no formato inccorreto por exemplo: "8.2" ao invés de "8.20"
        {
            stringPreco = stringPreco+"0";                                      ///Ajustando o problema da unidade
        }
        return "R$ "+stringPreco;  
    }

    public List<Pedido> getPedidos() {
        return controleCaixa;
    }

    public void setControleCaixa(List<Pedido> controleCaixa) {
        this.controleCaixa = controleCaixa;
    }

    public void adicionar(Pedido pedido){
        controleCaixa.add(pedido);
    }
    
    public Integer getQuantidadePedidos(){
        return controleCaixa.size();
    }
    
    public void remover(Pedido pedido){
        for (int i = 0; i < controleCaixa.size(); i++) {                              ///Verifica se o produto está presente no arraylist
            if (controleCaixa.get(i).getNumeroPedido() == (pedido.getNumeroPedido())) ///Se estiver presente só será incrementado o contador de quantidade
            {
                controleCaixa.remove(i);
                break;                                                                ///Sai do loop
            }
        }
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }
    
    public String toString(){
        return "("+calendario.dataToString()+")";
    }
}
