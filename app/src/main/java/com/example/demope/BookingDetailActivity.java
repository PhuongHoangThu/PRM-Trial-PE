package com.example.demope;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demope.DatabaseHelper.BookingDatabaseHelper;
import com.example.demope.Model.Booking;

public class BookingDetailActivity extends AppCompatActivity {

    private TextView tvDetailCustomerName, tvDetailServiceName, tvDetailTotalValue;
    private BookingDatabaseHelper db;
    private int bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        // Initialize views
        tvDetailCustomerName = findViewById(R.id.tvDetailCustomerName);
        tvDetailServiceName = findViewById(R.id.tvDetailServiceName);
        tvDetailTotalValue = findViewById(R.id.tvDetailTotalValue);

        // Initialize database helper
        db = new BookingDatabaseHelper(this);

        // Get booking ID passed from previous activity
        bookingId = getIntent().getIntExtra("bookingId", -1);

        // Fetch and display booking details if the ID is valid
        if (bookingId != -1) {
            Booking booking = db.getBookingById(bookingId);
            if (booking != null) {
                tvDetailCustomerName.setText(booking.getCustomerName());
                tvDetailServiceName.setText(booking.getServiceName());
                tvDetailTotalValue.setText(String.valueOf(booking.getTotalValue()));
            }
        }

        // Enable EdgeToEdge for immersive UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
