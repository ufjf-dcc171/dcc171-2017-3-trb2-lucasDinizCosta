package Principal;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class CaixaDiarioListModel implements ListModel<CaixaDiario> {

    private List<CaixaDiario> caixasDiario = new ArrayList<>();
    private List<ListDataListener> dataListeners;

    public CaixaDiarioListModel(List<CaixaDiario> caixasDiario) {
        this.caixasDiario = caixasDiario;
        dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return caixasDiario.size();
    }

    @Override
    public CaixaDiario getElementAt(int index) {
        return caixasDiario.get(index);
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
