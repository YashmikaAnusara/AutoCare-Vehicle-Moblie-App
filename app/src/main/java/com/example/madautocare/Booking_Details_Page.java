package com.example.madautocare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Booking_Details_Page extends AppCompatActivity {
    Context context;
    private DbHandler dbHandler;
    private List< booking> book;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details_page);
        context=this;
       dbHandler = new DbHandler(context);
        listView=findViewById(R.id.bookingList);

        book=new ArrayList<>();
        book=dbHandler.getBookDetails();

         bookingAdapter adapter=new  bookingAdapter(context,R.layout.single_booking,book);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                 booking booking=book.get(position);

                String id=booking.getVehicle_ID();
                String type=booking.getVehicle_Type() ;
                String km=booking.getKm_per_day() ;
                String uname=booking.getUserName() ;
                String mail=booking.getEmail() ;
                String date=booking.getDate() ;

                Intent send = new Intent( Booking_Details_Page.this,  Booking_View_Page.class );
                send.putExtra("id",id);
                send.putExtra("type",type);
                send.putExtra("km",km);
                send.putExtra("uname",uname);
                send.putExtra("mail",mail);
                send.putExtra("date",date);

                startActivity(send);

            }
        });
    }
}