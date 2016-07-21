package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.Denuncia;

import java.util.ArrayList;
import java.util.Date;

public class DenunciasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<Denuncia> lstDenuncia = getListDenuncia();
/*
        ListView lv = (ListView)container.findViewById(R.id.listDenuncias);
        lv.setAdapter(new ItensAdapter(getActivity(), lstDenuncia));
*/
        return inflater.inflate(R.layout.fragment_denuncias, null, false);
    }

    private ArrayList<Denuncia> getListDenuncia(){
        ArrayList<Denuncia> denunciaList = new ArrayList<Denuncia>();
        Date data = new Date();
        Denuncia denuncia = new Denuncia();
        denuncia.setContadorDenun(1);
        denuncia.setData(data);
        denuncia.setDescricao("O médico faltou");
        denuncia.setTipoDenuncia(1);
        denunciaList.add(denuncia);
        return denunciaList;
    }
}