package com.hv_07.bunker.services;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.hv_07.bunker.R;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter  extends
        RecyclerView.Adapter<ServicesAdapter.ViewHolder> {


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public ToggleButton messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (ToggleButton) itemView.findViewById(R.id.message_button);

        }

    }
private List<Service> services;
public ServicesAdapter(List<Service> contacts) {
        services = contacts;
        }

@Override
public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.services, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
        }

// Involves populating data into the item through holder
@Override
public void onBindViewHolder(ServicesAdapter.ViewHolder holder, final int position) {
        // Get the data model based on position
        Service service = services.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(service.getName());
        final ToggleButton button = holder.messageButton;
        button.setTransformationMethod(null);
        button.setText("See Password");

        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                button.setTextOn(services.get(position).getPassword());
            } else {
                button.setTextOff("See Password");
            }
        }
    });

}


// Returns the total count of items in the list
@Override
public int getItemCount() {
        return services.size();
        }
}
