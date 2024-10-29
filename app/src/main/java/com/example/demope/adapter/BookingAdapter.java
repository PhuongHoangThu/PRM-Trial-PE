package com.example.demope.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demope.Model.Booking;
import com.example.demope.R;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private final List<Booking> bookingList;  // Make list final to ensure immutability
    private final OnBookingClickListener listener;

    public BookingAdapter(List<Booking> bookingList, OnBookingClickListener listener) {
        this.bookingList = bookingList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);

        if (booking != null) {
            holder.tvCustomerName.setText(booking.getCustomerName() != null ? booking.getCustomerName() : "N/A");
            holder.tvServiceName.setText(booking.getServiceName() != null ? booking.getServiceName() : "N/A");
            holder.tvTotalValue.setText(String.valueOf(booking.getTotalValue()));

            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBookingClick(booking);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bookingList != null ? bookingList.size() : 0;
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView tvCustomerName, tvServiceName, tvTotalValue;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvTotalValue = itemView.findViewById(R.id.tvTotalValue);
        }
    }

    public interface OnBookingClickListener {
        void onBookingClick(Booking booking);
    }
}
