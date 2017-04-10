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

        //Save the current processPosition
        int processPosition;

        if (convertView == null){
            convertView = myInflater.inflate(R.layout.client_list_view,null);
            holder = new ViewHolder();
            holder.attendClientButton = (Button)convertView.findViewById(R.id.btnAttendClient);
            holder.releaseClientButton = (Button)convertView.findViewById(R.id.btnReleaseClient);
            holder.clientDismissedSwitch = (Switch)convertView.findViewById(R.id.clientDismissedSwitch);
            holder.clientTitleTextView = (TextView)convertView.findViewById(R.id.clientTitle);
            holder.finishedProcesses = (TextView)convertView.findViewById(R.id.processesStates);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        final Client client = getItem(position);
        final ArrayList<Process> clientProcesses = client.getClientProcessesData();

        final String successState = parent.getResources().getString(R.string.clientStateSuccess);
        final String failState = parent.getResources().getString(R.string.clientStateFail);
        String queueClientMsg = parent.getResources().getString(R.string.queueClient);

        holder.clientTitleTextView.setText(+ client.getId() + ": " + queueClientMsg);
        //Attend client in one process
        holder.attendClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(client.getAttendClicksCounter() < (clientProcesses.size()-1)){
                    Process currentProcess = client.getClientProcess(client.getAttendClicksCounter());
                    currentProcess.setWaitTime(calculateWaitingTime(currentProcess));
                    //Write the moment when start the service.
                    currentProcess.setStartServiceTime(new Date().getTime());
                    Toast.makeText(context, currentProcess.getDescription() + ": Tiempo de espera: " + currentProcess.getWaitTime(), Toast.LENGTH_SHORT).show();
                    client.setAttendClicksCounter(client.getAttendClicksCounter() + 1);
                    notifyDataSetChanged();
                }else{
                    Process currentProcess = client.getClientProcess(client.getAttendClicksCounter());
                    currentProcess.setWaitTime(calculateWaitingTime(currentProcess));
                    Toast.makeText(context, currentProcess.getDescription() + ": Tiempo de espera: " + currentProcess.getWaitTime(), Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            }
        });

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


        //Service time Finished of one process
        holder.releaseClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(client.getReleaseClicksCounter() < (clientProcesses.size()-1)){
                    Process currentProcess = client.getClientProcess(client.getReleaseClicksCounter());
                    Process nextProcess = client.getClientProcess(client.getReleaseClicksCounter() + 1);
                    currentProcess.setServiceTime(calculateServiceTime(currentProcess));
                    //Stamp the time when starts counting de attending time of the next process.
                    nextProcess.setArrivalTime(new Date().getTime());
                    //Write the process finished in a text view
                    String finishedProcesses = client.getFinishedProcesses() + "P" + currentProcess.getId() + ": " + successState + "\n";
                    client.setFinishedProcesses(finishedProcesses);
                    Toast.makeText(context, currentProcess.getDescription() + ": Tiempo de servicio: " + currentProcess.getServiceTime(), Toast.LENGTH_SHORT).show();
                    client.setReleaseClicksCounter(client.getReleaseClicksCounter() + 1);
                    notifyDataSetChanged();
                }else{
                    Process currentProcess = client.getClientProcess(client.getReleaseClicksCounter());
                    currentProcess.setServiceTime(calculateWaitingTime(currentProcess));
                    String finishedProcesses = client.getFinishedProcesses() + "P" + currentProcess.getId() + ": " + successState + "\n";
                    client.setFinishedProcesses(finishedProcesses);
                    client.setState(successState);
                    Toast.makeText(context, currentProcess.getDescription() + ": Tiempo de servicio: " + currentProcess.getServiceTime(), Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
                notifyDataSetChanged();
            }
        });
        holder.finishedProcesses.setText(client.getFinishedProcesses());
        String clientState = client.getState();

        if(clientState != ""){
            holder.clientTitleTextView.setText(client.getState()+ " : " + client.getId());
            if(clientState != failState){
                holder.clientDismissedSwitch.setEnabled(false);
                convertView.setBackgroundColor(Color.parseColor("#d8ff84"));
            }else{
                holder.clientDismissedSwitch.setChecked(true);
                convertView.setBackgroundColor(Color.parseColor("#ffb2fb"));
            }
            holder.attendClientButton.setEnabled(false);
            holder.releaseClientButton.setEnabled(false);
        }else{
            holder.clientDismissedSwitch.setChecked(false);
            holder.clientDismissedSwitch.setEnabled(true);
            holder.attendClientButton.setEnabled(true);
            holder.releaseClientButton.setEnabled(true);
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }

    static class ViewHolder{
        TextView clientTitleTextView;
        TextView finishedProcesses;
        Button releaseClientButton;
        Button attendClientButton;
        Switch clientDismissedSwitch;
    }

    private String calculateWaitingTime(Process currentProcess){
        long attendTime = new Date().getTime();
        long waitTime = attendTime - currentProcess.getArrivalTime();
        String waitTimeToString = Process.TimeConverter(waitTime);
        return waitTimeToString;
    }

    private String calculateServiceTime(Process currentProcess){
        long finishServiceTime = new Date().getTime();
        long serviceTime = finishServiceTime - currentProcess.getStartServiceTime();
        String serviceTimeToString = Process.TimeConverter(serviceTime);
        return serviceTimeToString;
    }

}
