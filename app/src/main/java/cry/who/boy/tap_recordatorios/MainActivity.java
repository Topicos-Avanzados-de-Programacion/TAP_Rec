package cry.who.boy.tap_recordatorios;

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
    ListViewAdapter adapter=null;

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
                String importancia="";
                switch (obj.getColor()){
                    case R.drawable.ic_rojo:
                        importancia="Alta";
                        break;
                    case R.drawable.ic_amarillo:
                        importancia="Normal";
                        break;
                    case R.drawable.ic_verde:
                        importancia="Baja";
                        break;
                }
                Intent editar=new Intent(MainActivity.this,Add_New_Reminder.class);
                editar.putExtra("titulo",obj.getTitulo());
                editar.putExtra("fecha",obj.getFecha());
                editar.putExtra("hora",obj.getHora());
                editar.putExtra("descripcion",obj.getDescripcion());
                editar.putExtra("importancia",importancia);
                startActivity(editar);
            return true;
            }
        });
    }
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Rec elemento = (Rec) adapter.getItem(position);
        Intent intent;

    }
    private void cargarLista(){
        Cursor res=myDb.getAllData();
        if (res.getCount()==0){
            return;
        }
        while(res.moveToNext()){
            arrayRec.add(new Rec(res.getInt(4),res.getString(0),res.getString(1),res.getString(2),res.getString(3)));
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
