package com.alenva.lennkentest.Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alenva.lennkentest.R;

import java.text.MessageFormat;

public class Info_Gas extends AppCompatActivity {
      private Button close_button, send_button;
      private TextView name_text, street_text, pc_text, rfc_text, regular_text, premium_text, lon_text, lat_text;
      private String name, street, pc, rfc, regular, premium, lon, lat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__gas);

        close_button= findViewById(R.id.info_close_button);
        send_button = findViewById(R.id.info_send_button);
        name_text= findViewById(R.id.name_text_info);
        street_text= findViewById(R.id.street_text_info);
        pc_text= findViewById(R.id.pc_text_info);
        rfc_text= findViewById(R.id.rfc_text_info);
        regular_text = findViewById(R.id.regular_text_info);
        premium_text = findViewById(R.id.premium_text_info);
        lat_text = findViewById(R.id.lat_text_info);
        lon_text = findViewById(R.id.lon_text_info);
        final Bundle extra = getIntent().getExtras();

        if (extra != null){
            name=extra.getString("name");
            street=extra.getString("street");
            pc= extra.getString("postalcode");
            rfc= extra.getString("rfc");
            regular=extra.getString("regular");
            premium = extra.getString("premium");
            lat=extra.getString("lat");
            lon=extra.getString("lon");
        }

        name_text.setText(name);
        street_text.setText(street);
        pc_text.setText(pc);
        rfc_text.setText(rfc);
        regular_text.setText(MessageFormat.format("${0}", regular));
        premium_text.setText(MessageFormat.format("${0}",premium));
        lat_text.setText(lat);
        lon_text.setText(lon);

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Nombre: " + name + "\n" + "Dirección: " + street + "\n" + "Código Postal: " + pc + "\n"
                        + "RFC: " + rfc +"\n"  + "Magna: " + regular + "\n"+
                        "Premium : " + premium + "\n" + "Latitud: " + lat + "\n"
                        + "Longitud: " + lon + "\n";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, message);


                intent.putExtra(Intent.EXTRA_SUBJECT,"Información Gasolinera");
                startActivity(intent);
            }
        });

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
