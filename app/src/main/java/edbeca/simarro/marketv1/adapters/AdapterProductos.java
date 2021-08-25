package edbeca.simarro.marketv1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Producto;

public class AdapterProductos<T> extends ArrayAdapter<T> {

    public AdapterProductos(Fragment context, List<T> objects) {
        super(context.getActivity(), 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listItemView = convertView;

        if (null == convertView){
            listItemView = inflater.inflate(R.layout.element_productos_list,parent,false);
        }

        TextView nombre = (TextView)listItemView.findViewById(R.id.txtNombre);

        Producto item = (Producto)getItem(position);
        nombre.setText(item.getNombre());

        return listItemView;

    }
}
