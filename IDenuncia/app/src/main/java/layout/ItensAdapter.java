package layout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.Denuncia;

import java.util.List;

public class ItensAdapter extends BaseAdapter {
    Context context;
    List<Denuncia> item;

    ItensAdapter(Context context, List<Denuncia> item) {
        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return item.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.item_denuncia, null);
        }
        TextView txtQtdLike = (TextView) view.findViewById(R.id.qtdLike);
        TextView txtTipDenuncia = (TextView) view.findViewById(R.id.tipDenuncia);
        TextView txtDtDenuncia = (TextView) view.findViewById(R.id.dtDenuncia);

        Denuncia item_pos = item.get(position);
        txtQtdLike.setText(item_pos.getContadorDenun());
        txtTipDenuncia.setText(item_pos.getTipoDenuncia());
        txtDtDenuncia.setText(item_pos.getData().toString());
        return null;
    }
}