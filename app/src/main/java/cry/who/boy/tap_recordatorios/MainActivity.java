package cry.who.boy.tap_recordatorios;

import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private ListView lista=null;
    ArrayList<Rec> arrayRec=null;
    ListViewAdapter adapter=null;
    private NotificationManager notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);
        arrayRec=new ArrayList<>();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        lista=(ListView)(findViewById(R.id.Lista));
        cargarLista();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Deje presionado un elemento para Editarlo o Eliminarlo ",
                        Toast.LENGTH_SHORT).show();
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                Rec obj=arrayRec.get(position);

                Intent editar=new Intent(MainActivity.this,Editar.class);
                editar.putExtra("titulo",obj.getTitulo());
                editar.putExtra("fecha",obj.getFecha());
                editar.putExtra("hora",obj.getHora());
                editar.putExtra("descripcion",obj.getDescripcion());
                editar.putExtra("importancia",obj.getImportancia());
                startActivity(editar);
                return true;
            }
        });

    }

    private void cargarLista(){
        Cursor res=myDb.getAllData();
        if (res.getCount()==0){
            return;
        }
        while(res.moveToNext()){
            arrayRec.add(new Rec(res.getString(0),res.getString(1),res.getString(2),res.getString(3),res.getInt(4)));
        }

        Collections.sort(arrayRec);
        
        adapter=new ListViewAdapter(arrayRec,this);
        lista.setAdapter(adapter);

    }

    public void onClick(View view){
        Intent Buscar=new Intent(this, Add_New_Reminder.class);
        startActivity(Buscar);
    }

}
