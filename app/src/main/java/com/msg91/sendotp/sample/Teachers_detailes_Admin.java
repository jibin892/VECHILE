package com.msg91.sendotp.sample;

import android.app.DatePickerDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Teachers_detailes_Admin extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    String[] dance = { "Payed", "Payment Not Confirmed"};
    EditText dance_name, dance_ph, dance_email, dance_duration,vc,dn,did,sph;

    private DatePicker datePicker;

    private TextView dateView;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    Button dance_btn;
Spinner dance_techer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_detailes__admin);

        ConstraintLayout constraintLayout = findViewById(R.id.td);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        dance_name = findViewById(R.id.stnameee);
        dance_ph = findViewById(R.id.joindate);
        dance_email = findViewById(R.id.newph);
        dance_techer = findViewById(R.id.d_techer);
        dance_duration = findViewById(R.id.d_timeperiod);
        dance_btn = findViewById(R.id.d_btn);
        vc = findViewById(R.id.d_timeperiod2);
        dn = findViewById(R.id.d_timeperiod3);
        did = findViewById(R.id.d_timeperiod4);
        sph = findViewById(R.id.d_timeperiod5);




        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//        dance_ph= findViewById(R.id.dob);
        dance_ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(Teachers_detailes_Admin.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dance_ph.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                 datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dance);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        dance_techer.setAdapter(aa);
        dance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dance_name.getText().toString().isEmpty()) {


                    dance_name.setError("Enter a Value");
                }
                else if (dance_ph.getText().toString().isEmpty()) {

                    dance_ph.setError("enter a value");
                }
                else if (dance_email.getText().toString().isEmpty()) {

                    dance_email.setError("enter a value");

                }
                else if(dance_duration.getText().toString().isEmpty()){
                    dance_duration.setError("enter a value");

                }

                else if(vc.getText().toString().isEmpty()){
                    vc.setError("enter a value");

                }

                else if(dn.getText().toString().isEmpty()){
                    dn.setError("enter a value");

                }

                else if(did.getText().toString().isEmpty()){
                    did.setError("enter a value");

                }
                else if(sph.getText().toString().isEmpty()){
                    sph.setError("enter a value");

                }

                else
                    {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Vehicle_Managemen_system/confirmation_admin_tour.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    dance_name.getText().clear();
                                    dance_ph.getText().clear();
                                    dance_email.getText().clear();
                                    dance_duration.getText().clear();
                                    vc.getText().clear();
                                   dn.getText().clear();
                                   did.getText().clear();
                                   sph.getText().clear();
                                    Toast.makeText(Teachers_detailes_Admin.this, response, Toast.LENGTH_LONG).show();

                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");


                                        }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            },

                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                }

                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
//Adding parameters to request

                            params.put("tname", dance_name.getText().toString());
                            params.put("tphone", dance_ph.getText().toString());
                            params.put("temail", dance_email.getText().toString());
                            params.put("tdance", dance_techer.getSelectedItem().toString().toLowerCase());
                            params.put("texperiance", dance_duration.getText().toString());
                            params.put("vc", vc.getText().toString());
                            params.put("dn", dn.getText().toString());
                            params.put("did", did.getText().toString());
                            params.put("sph", sph.getText().toString());

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                            return params;
                        }

                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Teachers_detailes_Admin.this);
                    requestQueue.add(stringRequest);
                }
            }


        });


    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),dance[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}