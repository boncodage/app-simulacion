package com.imdb.SimulacionCarnet;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.AlphabeticIndex;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
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
        //A ViewHolder stands for improve the performance of the listView
        final ViewHolder holder;

        if (convertView == null){
            convertView = myInflater.inflate(R.layout.client_list_view,null);
            holder = new ViewHolder();
            holder.attendClientButton = (Button)convertView.findViewById(R.id.btnAttendClient);
            holder.releaseClientButton = (Button)convertView.findViewById(R.id.btnReleaseClient);
            holder.clientDismissedSwitch = (Switch)convertView.findViewById(R.id.clientDismissedSwitch);
            holder.clientTitleTextView = (TextView)convertView.findViewById(R.id.clientTitle);
            holder.finishedImageView = (ImageView)convertView.findViewById(R.id.imageViewFinish);
            holder.canceledImageView = (ImageView)convertView.findViewById(R.id.imageViewCancel);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        final Client client = getItem(position);

        final String successState = parent.getResources().getString(R.string.clientStateSuccess);
        final String failState = parent.getResources().getString(R.string.clientStateFail);

        holder.clientTitleTextView.setText("Cliente Nº: " + client.getId());

        //Client out of the Queue
        holder.clientDismissedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    client.setState(failState);
                    notifyDataSetChanged();
                }
            }
        });
        //Service time Finished
       /* holder.releaseClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTimeInMillis = new Date().getTime();
                long elapsedTimeInMillis = currentTimeInMillis - client.getClientArrivalTime();
                client.setElapsedTime(elapsedTimeInMillis);
                client.setState(successState);
                notifyDataSetChanged();
            }
        });*/
        String clientState = client.getState();

        if(clientState != ""){
            holder.clientTitleTextView.setText(client.getState()+ " : " + client.getId());
            if(clientState != failState){
                holder.clientDismissedSwitch.setChecked(false);
                holder.finishedImageView.setVisibility(View.VISIBLE);
                holder.canceledImageView.setVisibility(View.INVISIBLE);
            }else{
                holder.clientDismissedSwitch.setChecked(true);
                holder.canceledImageView.setVisibility(View.VISIBLE);
                holder.finishedImageView.setVisibility(View.INVISIBLE);
            }
        }else{
            holder.clientDismissedSwitch.setChecked(false);
            holder.canceledImageView.setVisibility(View.INVISIBLE);
            holder.finishedImageView.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    static class ViewHolder{
        TextView clientTitleTextView;
        ImageView finishedImageView;
        ImageView canceledImageView;
        Button releaseClientButton;
        Button attendClientButton;
        Switch clientDismissedSwitch;
    }
}
