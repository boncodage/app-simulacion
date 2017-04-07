package com.imdb.SimulacionCarnet;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.icu.text.AlphabeticIndex;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.zip.Inflater;

/**
 * Created by martin-ubuntu on 05/04/17.
 */

public class ClientAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Client> clientsQueue = new ArrayList<>();
    private Context context;
    private LayoutInflater myInflater;

    public ClientAdapter(ArrayList<Client> clientsQueue, Context context) {
        this.clientsQueue = clientsQueue;
        this.context = context;
        this.myInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return clientsQueue.size();
    }

    @Override
    public Client getItem(int position) {
        return clientsQueue.get(position);
    }

    @Override
    public long getItemId(int position) {
        return clientsQueue.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View viewItem = convertView;

        if (viewItem == null){
            viewItem = myInflater.inflate(R.layout.client_list_view,null);
        }

        final TextView clientStateTxtView = (TextView)viewItem.findViewById(R.id.clientState);
        TextView clientTitleTxtView = (TextView)viewItem.findViewById(R.id.clientTitle);
        Button btnReleaseClient = (Button)viewItem.findViewById(R.id.btnReleaseClient);
        final Client client = getItem(position);
        int clientNumber = position + 1;
        clientTitleTxtView.setText("Cliente Nº: "+ clientNumber);

        final String successState = parent.getResources().getString(R.string.clientStateSuccess);
        final String failState = parent.getResources().getString(R.string.clientStateFail);

        btnReleaseClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Guardar tiempo de espera
                long currentTimeInMillis = new Date().getTime();
                long elapsedTimeInMillis = currentTimeInMillis - client.getClientArrivalTime();
                client.setElapsedTime(elapsedTimeInMillis);
                Toast.makeText(context, client.getElapsedTime(), Toast.LENGTH_LONG).show();

                client.setState(successState + ": " + client.getElapsedTime() + " hs.");
                clientStateTxtView.setVisibility(View.VISIBLE);
                notifyDataSetChanged();
            }
        });

        clientStateTxtView.setText(client.getState());

        return viewItem;
    }

}
