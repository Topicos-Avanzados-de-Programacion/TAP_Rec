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

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private ListView lista=null;
    ArrayList<Rec> arrayRec=null;
    ArrayList<Rec> arrayRec0=null;
    ArrayList<Rec> arrayRec1=null;
    ArrayList<Rec> arrayRec2=null;
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
        while (!arrayRec.isEmpty()){
            switch (arrayRec.get(0).getImportancia()){
                case 0:
                    arrayRec0.add(arrayRec.get(0));
                    arrayRec.remove(0);
                    break;
                case 1:
                    arrayRec1.add(arrayRec.get(0));
                    arrayRec.remove(0);
                    break;
                case 2:
                    arrayRec2.add(arrayRec.get(0));
                    arrayRec.remove(0);
                    break;
            }

        }
        if (arrayRec.isEmpty()){
            while(!arrayRec0.isEmpty()){
                arrayRec.add(arrayRec0.get(0));
                arrayRec0.remove(0);
            }
            while(!arrayRec1.isEmpty()){
                arrayRec.add(arrayRec0.get(0));
                arrayRec1.remove(0);
            }
            while(!arrayRec2.isEmpty()){
                arrayRec.add(arrayRec0.get(0));
                arrayRec2.remove(0);
            }
        }
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

}
