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
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class AdapterClient extends ArrayAdapter<Client> {

    private int res;

    public AdapterClient(@NonNull Context context, int resource, @NonNull List<Client> objects) {
        super(context, resource, objects);

        this.res= resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        AdapterClient.ViewHolder holder ;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(res,parent,false);
            holder= new AdapterClient.ViewHolder();
            holder.nom = convertView.findViewById(R.id.nomClient);
            holder.prenom = convertView.findViewById(R.id.prenomClient);

            convertView.setTag(holder);
        }else{
            holder = (AdapterClient.ViewHolder) convertView.getTag();
        }

        Client client = getItem(position);
        holder.nom.setText(client.getNom());
        holder.prenom.setText(client.getPrenom());

        return convertView;
    }

    static class ViewHolder{
        TextView nom;
        TextView prenom;
    }

}
