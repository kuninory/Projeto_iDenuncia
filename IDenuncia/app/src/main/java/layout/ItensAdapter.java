package layout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.Denuncia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ItensAdapter extends ArrayAdapter<Denuncia> {

    private Activity activity;
    private ArrayList<Denuncia> listDenuncias;
    private static LayoutInflater mInflater = null;

    public ItensAdapter(Activity activity, int textViewResourceId, ArrayList<Denuncia> _listDenuncias) {
        super(activity, textViewResourceId, _listDenuncias);
        try {
            this.activity = activity;
            this.listDenuncias = _listDenuncias;

            mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return listDenuncias.size();
    }

    public Denuncia getItem(Denuncia position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView tipDenuncia;
        public TextView qtdLike;
        public TextView dtDenuncia;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = mInflater.inflate(R.layout.item_denuncia, null);
                holder = new ViewHolder();

                holder.tipDenuncia = (TextView) vi.findViewById(R.id.tipDenuncia);
                holder.qtdLike = (TextView) vi.findViewById(R.id.qtdLike);
                holder.dtDenuncia = (TextView) vi.findViewById(R.id.dtDenuncia);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            holder.tipDenuncia.setText(listDenuncias.get(position).getNomeTipoDenuncia());
            holder.qtdLike.setText(Integer.toString(listDenuncias.get(position).getContadorDenun()));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            holder.dtDenuncia.setText(format.format(listDenuncias.get(position).getData()).toString());

        } catch (Exception e) {
        }
        return vi;
    }
}