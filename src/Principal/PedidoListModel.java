package Principal;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class PedidoListModel implements ListModel<Pedido>{
    
    private List<Pedido> pedidos;
    private List<ListDataListener> dataListeners;
    
    public PedidoListModel(List<Pedido> pedidos){
        this.pedidos = pedidos;
        dataListeners = new ArrayList<>();
    }
    
    @Override
    public int getSize() {
        return pedidos.size();
    }

    @Override
    public Pedido getElementAt(int index) {
        return pedidos.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.dataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.dataListeners.remove(l);
    } 
}
