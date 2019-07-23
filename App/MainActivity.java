package com.example.bateria;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import java.util.ArrayList;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    EditText editID;
 //   EditText editPuntaje;
    Button btnIngresar;
    TextView editNombre, editApellido;
    ListView listDatos;
    ListView listDatos2;
    AsyncHttpClient cliente;
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editID = (EditText) findViewById(R.id.editID);
      //  editPuntaje=(EditText) findViewById(R.id.editPuntaje);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        editNombre=(TextView)findViewById(R.id.editNombre);
        editApellido=(TextView)findViewById(R.id.editApellido);
        listDatos=(ListView)findViewById(R.id.listDatos);
        listDatos2=(ListView)findViewById(R.id.listDatos2);
        cliente= new AsyncHttpClient();
        obtenerDato1();
        obtenerDato2();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    buscarDato("http://192.168.4.1/bateria/login.php?ID="+editID.getText()+"");
                obtenerDato1();
                obtenerDato2();

            }
        });

    }




    private void buscarDato(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        editNombre.setText(jsonObject.getString("Nombre"));
                        editApellido.setText(jsonObject.getString("Apellido"));


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }





    private void obtenerDato1(){
        String url="http://192.168.4.1/bateria/login2.php?ID="+editID.getText()+"";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    listarDatos(new String (responseBody));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    private void obtenerDato2(){
        String url="http://192.168.4.1/bateria/login2.php?ID="+editID.getText()+"";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    listarDatos2(new String(responseBody));

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarDatos(String respuesta){
        ArrayList<usuario> lista = new ArrayList<usuario>();
        try{
            JSONArray jsonArray = new JSONArray(respuesta);
            for(int i=0; i<jsonArray.length(); i++){
                usuario p = new usuario();
                p.setPuntaje(jsonArray.getJSONObject(i).getString("Puntaje"));
                lista.add(p);
            }
            ArrayAdapter <usuario> a = new ArrayAdapter(this, android.R.layout.simple_list_item_1  ,lista);
            listDatos.setAdapter(a);

        }catch (Exception el){
            el.printStackTrace();
        }

    }

    private void listarDatos2(String respuesta){
        ArrayList<usuario2> lista = new ArrayList<usuario2>();
        try{
            JSONArray jsonArray = new JSONArray(respuesta);
            for(int i=0; i<jsonArray.length(); i++){
                usuario2 p = new usuario2();
                p.setFecha(jsonArray.getJSONObject(i).getString("Fecha"));
                lista.add(p);
            }
            ArrayAdapter <usuario2> b = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
            listDatos2.setAdapter(b);

        }catch (Exception el){
            el.printStackTrace();
        }

    }
public void Siguiente(View view){
    Intent siguiente = new Intent(this, Add.class);
    startActivity(siguiente);
}




}

