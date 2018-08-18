package com.bryansinqadu.eparking_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingsViewHolder>  {

    private Context mCtx;


    private List<Bookings>bookingsList;


    public BookingsAdapter(Context mCtx, List<Bookings> bookingsList) {
        this.bookingsList = bookingsList;
        this.mCtx = mCtx;

    }


    @Override
    public BookingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.bookings_layout,parent,false );
        return new BookingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookingsViewHolder holder, int position) {

        Bookings bookings = bookingsList.get(position);


        holder.name.setText(bookings.getName());
        holder.bookingid.setText("#"+bookings.getBookingId());
        holder.email.setText(String.valueOf(bookings.getEmail()));
        holder.department.setText(String.valueOf(bookings.getDepartment()));
        holder.slot.setText(String.valueOf(bookings.getSlot()));
        holder.date.setText(String.valueOf(bookings.getDate()));
        holder.time.setText(String.valueOf(bookings.getTime()));
        holder.status.setText(String.valueOf(bookings.getStatus()));

        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    @Override
    public int getItemCount() {
        return bookingsList.size();
    }

    class BookingsViewHolder extends RecyclerView.ViewHolder {

        TextView name, bookingid, email, department, slot, status,date,time;
        Button action;

        public BookingsViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            bookingid = itemView.findViewById(R.id.tvBookingId);
            email = itemView.findViewById(R.id.tvEmail);
            department = itemView.findViewById(R.id.tvDepartment);
            slot = itemView.findViewById(R.id.tvSlot);
            date = itemView.findViewById(R.id.tvDate);
            time = itemView.findViewById(R.id.tvTime);
            status = itemView.findViewById(R.id.tvStatus);
            action = itemView.findViewById(R.id.btnAction);
        }
    }
}