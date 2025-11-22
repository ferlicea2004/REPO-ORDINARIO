package com.example.consumeapi;

import android.os.Bundle;
import android.util.Log; // Importante para ver errores en Logcat
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue; // Importar RequestQueue
import com.android.volley.Response;
import com.android.volley.VolleyError; // Importar VolleyError
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley; // Importar Volley

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
  TextView data;
  String uri;
  RequestQueue queue; // Declarar la cola de peticiones

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    data = findViewById(R.id.texto);

    // 1. Inicializar la RequestQueue
    queue = Volley.newRequestQueue(this);

    uri = "https://jsonplaceholder.typicode.com/users/1";

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject jsonObject) {
          try {
            String nombre = jsonObject.getString("name");
            data.setText(nombre);
          } catch (JSONException e) {}
        }
      },
      //
      new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
          data.setText("Error" + volleyError.getMessage());
          Log.e("Volley", "Error en la petición", volleyError);
        }
      }
    );

    Volley.newRequestQueue(this).add(request);
  }
}
