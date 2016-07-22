package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.Denuncia;

import java.util.ArrayList;
import java.util.Date;

public class DenunciasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_denuncias, container, false);

        ArrayList<Denuncia> listContact = getListDenuncia();
        ListView lv = (ListView)rootView.findViewById(R.id.listDenuncias);
        lv.setAdapter(new ItensAdapter(getActivity(), listContact));

        return rootView;
    }

    private ArrayList<Denuncia> getListDenuncia(){
        ArrayList<Denuncia> denunciaList = new ArrayList<Denuncia>();
        Date data = new Date();
            Denuncia denuncia = new Denuncia();
            denuncia.setContadorDenun(1);
            denuncia.setData(data);
            denuncia.setDescricao("O médico faltou");
            denuncia.setTipoDenuncia(1);

            Denuncia denuncia2 = new Denuncia();
            denuncia2.setContadorDenun(1);
            denuncia2.setData(data);
            denuncia2.setDescricao("O médico faltou");
            denuncia2.setTipoDenuncia(1);

            Denuncia denuncia3 = new Denuncia();
            denuncia3.setContadorDenun(1);
            denuncia3.setData(data);
            denuncia3.setDescricao("O médico faltou");
            denuncia3.setTipoDenuncia(1);

        denunciaList.add(denuncia);
        denunciaList.add(denuncia2);
        denunciaList.add(denuncia3);
        return denunciaList;
    }
}