package com.example.demope;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demope.DatabaseHelper.BookingDatabaseHelper;
import com.example.demope.Model.Booking;

public class CreateBookingActivity extends AppCompatActivity {

    private EditText etCustomerName, etServiceName, etPrice, etQuantity;
    private Button btnSaveBooking;
    private BookingDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);

        // Initialize UI components
        etCustomerName = findViewById(R.id.etCustomerName);
        etServiceName = findViewById(R.id.etServiceName);
        etPrice = findViewById(R.id.etPrice);
        etQuantity = findViewById(R.id.etQuantity);
        btnSaveBooking = findViewById(R.id.btnSaveBooking);
        db = new BookingDatabaseHelper(this);

        // Set click listener for saving booking
        btnSaveBooking.setOnClickListener(v -> {
            String customerName = etCustomerName.getText().toString();
            String serviceName = etServiceName.getText().toString();
            double price = Double.parseDouble(etPrice.getText().toString());
            int quantity = Integer.parseInt(etQuantity.getText().toString());
            double totalValue = price * quantity;

            // Create a new booking and insert it into the database
            Booking booking = new Booking(customerName, serviceName, price, quantity, totalValue);
            db.insertBooking(booking);
            finish(); // Close activity after saving
        });

        // Enable EdgeToEdge immersive experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
