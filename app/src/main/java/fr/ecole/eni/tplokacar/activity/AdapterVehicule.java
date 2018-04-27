package fr.ecole.eni.tplokacar.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class AdapterVehicule extends ArrayAdapter<Vehicule>{

    private int res;


    public AdapterVehicule(@NonNull Context context, int resource, @NonNull List<Vehicule> objects) {
        super(context, resource, objects);

        res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder ;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(res,parent,false);
            holder= new ViewHolder();
            holder.marque = convertView.findViewById(R.id.marque);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Vehicule vehicule = getItem(position);
        //holder.marque.setText(vehicule.getMarque());
        //holder.modele.setText(vehicule.getModele);

        return convertView;
    }

    static class ViewHolder{
        TextView marque;
        TextView modele;
    }
}
