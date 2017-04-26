package cry.who.boy.tap_recordatorios;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private ListView lista=null;
    ArrayList<Rec> arrayRec=null;
    ListViewAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);
        arrayRec=new ArrayList<>();

        Intent intent=getIntent();
        Bundle extras=intent.getExtras();

        if (extras!=null){
            guardarDatos(extras);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        lista=(ListView)(findViewById(R.id.Lista));
        cargarLista();
    }
    private void cargarLista(){
        arrayRec.add(new Rec(R.mipmap.ic_launcher,"recordatorio 1","20/04/17","10:42 p.m.","Este es un recordatorio"));
        arrayRec.add(new Rec(R.mipmap.ic_launcher,"recordatorio 2","20/04/17","10:42 p.m.","Este es un recordatorio"));
        arrayRec.add(new Rec(R.mipmap.ic_launcher,"recordatorio 3","20/04/17","10:42 p.m.","Este es un recordatorio"));
        arrayRec.add(new Rec(R.mipmap.ic_launcher,"recordatorio 4","20/04/17","10:42 p.m.","Este es un recordatorio"));

        adapter=new ListViewAdapter(arrayRec,this);
        lista.setAdapter(adapter);
    }
    public void onClick(View view){
        Intent Buscar=new Intent(this, Add_New_Reminder.class);
        startActivity(Buscar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void guardarDatos(Bundle extras){
        String titulo=extras.getString("Titulo");
        String fecha=extras.getString("Fecha");
        String hora=extras.getString("Hora");
        String descripcion=extras.getString("Descripcion");
        String importancia=extras.getString("Importancia");
        int image = 0;
        switch (importancia){
            case "Alta":
                image=R.drawable.ic_rojo;
                break;
            case "Normal":
                image=R.drawable.ic_amarillo;
                break;
            case "Baja":
                image=R.drawable.ic_verde;
                break;
        }
        boolean guardado=myDb.insertData(titulo,fecha,hora,descripcion,importancia);
        arrayRec.add(new Rec(image,titulo,fecha,hora,descripcion));
    }
}
