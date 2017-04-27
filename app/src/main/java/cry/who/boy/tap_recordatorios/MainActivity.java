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


}
