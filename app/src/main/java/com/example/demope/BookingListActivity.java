package com.example.demope;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demope.DatabaseHelper.BookingDatabaseHelper;
import com.example.demope.Model.Booking;
import com.example.demope.adapter.BookingAdapter;

import java.util.List;

public class BookingListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private List<Booking> bookingList;
    private BookingDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        // Initialize database helper and get all bookings
        db = new BookingDatabaseHelper(this);
        bookingList = db.getAllBookings();

        // Initialize RecyclerView and set layout manager
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize BookingAdapter and handle item click events
        bookingAdapter = new BookingAdapter(bookingList, booking -> {
            Intent intent = new Intent(BookingListActivity.this, BookingDetailActivity.class);
            intent.putExtra("bookingId", booking.getId());
            startActivity(intent);
        });

        recyclerView.setAdapter(bookingAdapter);

        // Set up create booking button
        Button btnCreateBooking = findViewById(R.id.btnCreateBooking);
        btnCreateBooking.setOnClickListener(v -> {
            Intent intent = new Intent(BookingListActivity.this, CreateBookingActivity.class);
            startActivity(intent);
        });

        // Enable EdgeToEdge for immersive UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh booking list when resuming activity
        bookingList = db.getAllBookings();
        bookingAdapter.notifyDataSetChanged();
    }
}
