package com.msg91.sendotp.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Empid extends AppCompatActivity {
EditText idd;
Button id_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empid);
        idd=findViewById(R.id.empid);
        id_btn=findViewById(R.id.empbtn);
//ph=findViewById(R.id.empidph);

        id_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idd.getText().toString().isEmpty()){

                    idd.setError("enter a valid id");
                }

                else {



                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Vehicle_Managemen_system/ID_number_emo.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    Toast.makeText(Empid.this,response,Toast.LENGTH_LONG).show();

//                                    id.getText().clear();
//                                    admin_uname.getText().clear();
                                    if(response.contains("success"))
                                    {

                                        Intent in=new Intent(Empid.this, Admin_home.class);
                                        startActivity(in);
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
                            params.put("id",idd.getText().toString());
//                            params.put("apass",admin_pass.getText().toString());

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                            return params;
                        }

                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Empid.this);
                    requestQueue.add(stringRequest);
                }
                }

        });



    }
}
