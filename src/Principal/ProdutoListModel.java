package Principal;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ProdutoListModel implements ListModel<Produto> {

    private List<Produto> produtos = new ArrayList<>();
    private List<ListDataListener> dataListeners;

    public ProdutoListModel(List<Produto> produtos) {
        this.produtos = produtos;
        dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return produtos.size();
    }

    @Override
    public Produto getElementAt(int index) {
        return produtos.get(index);
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
