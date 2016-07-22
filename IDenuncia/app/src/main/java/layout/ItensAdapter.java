package layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.Denuncia;

import java.util.ArrayList;

public class ItensAdapter extends BaseAdapter {
    private static ArrayList<Denuncia> listDenuncias;
    private LayoutInflater mInflater;

    public ItensAdapter(Context ctx, ArrayList<Denuncia> results) {
        listDenuncias = results;
        mInflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listDenuncias.size();
    }

    @Override
    public Object getItem(int i) {
        return listDenuncias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        try {
            if (view == null) {
                view = mInflater.inflate(R.layout.item_denuncia, null);
                holder = new ViewHolder();
                holder.txtTipDenuncia = (TextView) viewGroup.findViewById(R.id.tipDenuncia);
                holder.txtQtdLike = (TextView) viewGroup.findViewById(R.id.qtdLike);
                holder.txtDtDenuncia = (TextView) viewGroup.findViewById(R.id.dtDenuncia);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.txtTipDenuncia.setText(listDenuncias.get(position).getDescricao());
            holder.txtQtdLike.setText(listDenuncias.get(position).getContadorDenun());
            holder.txtDtDenuncia.setText(listDenuncias.get(position).getData().toString());
        } catch (Exception e) {

        }

        return view;
    }

    static class ViewHolder {
        TextView txtTipDenuncia, txtQtdLike, txtDtDenuncia;
    }
}