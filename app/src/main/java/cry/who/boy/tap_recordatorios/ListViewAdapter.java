package cry.who.boy.tap_recordatorios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Luis J. Ortiz on 20/04/2017.
 */

public class ListViewAdapter extends BaseAdapter {
    ArrayList<Rec> arrayRec;
    private Context context;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(ArrayList<Rec> arrayRec, Context context) {
        this.arrayRec = arrayRec;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayRec.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayRec.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater=(LayoutInflater) (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        View vistaRec=layoutInflater.inflate(R.layout.layout_rec, parent, false);

        ImageView imagen=(ImageView) (vistaRec.findViewById(R.id.loColorImportancia));
        TextView titulo=(TextView)(vistaRec.findViewById(R.id.loTitulo));
        TextView fecha=(TextView)(vistaRec.findViewById(R.id.loFecha));
        TextView hora=(TextView)(vistaRec.findViewById(R.id.loHora));
        TextView descripcion=(TextView)(vistaRec.findViewById(R.id.loDescripcion));
        int color=0;
        switch (arrayRec.get(position).getImportancia()){
            case 0:
                color=R.drawable.ic_rojo;
                break;
            case 1:
                color=R.drawable.ic_amarillo;
                break;
            case 2:
                color=R.drawable.ic_verde;
                break;
        }
        imagen.setImageResource(color);
        titulo.setText(arrayRec.get(position).getTitulo());
        fecha.setText(arrayRec.get(position).getFecha());
        hora.setText(arrayRec.get(position).getHora());
        descripcion.setText(arrayRec.get(position).getDescripcion());
        return vistaRec;
    }
}
