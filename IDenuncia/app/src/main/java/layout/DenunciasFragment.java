package layout;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.Denuncia;

import java.util.List;

public class DenunciasFragment extends ListFragment{
    ItensAdapter adapter;

    /*
    Teste
     */
    List<Denuncia> itens;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_denuncias, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ItensAdapter(getActivity(), itens);
        setListAdapter(adapter);
        getListView().setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }
}
