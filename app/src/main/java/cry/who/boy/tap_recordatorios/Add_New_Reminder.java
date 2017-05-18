package cry.who.boy.tap_recordatorios;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Add_New_Reminder extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar; //Declarar el Toolbar
    private Button btn_fecha, btn_hora, btn_cancelar, btn_ok;
    private TextView tv_fecha, tv_hora;
    private EditText et_Titulo, et_Desc;
    private int dia, mes, anio, hora, minutos;
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    Spinner Importancia;
    private DatabaseHelper myDb;
    //Variables para saber la fecha de creación del recordatorio
    private int d_act, m_act, a_act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__reminder);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);//Conectamos el Layout al objeto Toolbar
        setSupportActionBar(toolbar);//Configuración de la barra de herramientas (toolbar)
        // como la Barra de Acciones (ActionBar) con la llamada de setSupportActionBar()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.volver_blanco));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View atras) {
                Intent Atras=new Intent(Add_New_Reminder.this, MainActivity.class);
                startActivity(Atras);
                finish();
            }
        });

        //Botones
        btn_cancelar = (Button) findViewById(R.id.btn_cancel);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_fecha = (Button) findViewById(R.id.btn_fecha);
        btn_hora = (Button) findViewById(R.id.btn_hora);
        //Edit Texts
        et_Titulo = (EditText) findViewById(R.id.et_title);
        et_Desc = (EditText) findViewById(R.id.et_text);//Descripción
        btn_hora.setOnClickListener(this);
        //TextView
        tv_fecha = (TextView) findViewById(R.id.tv_fecha);
        tv_hora = (TextView) findViewById(R.id.tv_hora);

        //Base de Datos
        myDb = new DatabaseHelper(this);

        //Spinner Importancia
        Importancia = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Importancia, android.R.layout.simple_spinner_item);
        Importancia.setAdapter(adapter);

        //dia, mes y anio tienen ahora el día actual del sistema
        Calendar c = Calendar.getInstance();
        dia= c.get(Calendar.DAY_OF_MONTH);
        mes= c.get(Calendar.MONTH);
        anio= c.get(Calendar.YEAR);
        d_act= c.get(Calendar.DAY_OF_MONTH);
        m_act= c.get(Calendar.MONTH);
        a_act= c.get(Calendar.YEAR);
        mostrarFecha();
        oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            anio = year;
            mes = month;
            dia = dayOfMonth;
            mostrarFecha();
            }
        };
    }

    //Metodos para Fecha
    public void mostrarFecha(){
        tv_fecha.setText(dia+"-"+(mes+1)+"-"+anio);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return new DatePickerDialog(this, oyenteSelectorFecha, anio, mes, dia);
        }
        return null;
    }

    public void mostrarCalendario(View control){
        showDialog(TIPO_DIALOGO);
    }

    //Metodos para Hora
    public void onClick (View v){
        Calendar c = Calendar.getInstance();
        minutos=c.get(Calendar.MINUTE);
        hora=c.get(Calendar.HOUR_OF_DAY);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora=hourOfDay;
                minutos=minute;
                tv_hora.setText(hora+":"+minutos);
            }
        },hora,minutos,false);
        timePickerDialog.show();
    }

    public void MandarDatos(View vista){

        String Titulo = et_Titulo.getText().toString();
        String Fecha = tv_fecha.getText().toString();// <---
        String Hora = tv_hora.getText().toString();// <---
        String Desc = et_Desc.getText().toString();
        int Import =Importancia.getSelectedItemPosition();
        if(Hora == null
                || Hora.equals("")
                || Hora.trim().length()==0){
            Hora= "Todo el día";
        }

        if(Titulo == null || Titulo.equals("") || Titulo.trim().length()==0 ||
                Fecha == null || Fecha.equals("") || Fecha.trim().length()==0){
            Toast msn = Toast.makeText(getApplicationContext(), "No deje el Título o la Fecha vacío", Toast.LENGTH_SHORT);
            msn.show();
        }else {
            Rec rec=new Rec(Titulo, Fecha, Hora, Desc, Import);
            boolean veri=myDb.insertData(rec);
            if (veri){
                Toast msn = Toast.makeText(getApplicationContext(), "Guardado Satisfactoriamente", Toast.LENGTH_SHORT);
                msn.show();
                Intent intent = new Intent(Add_New_Reminder.this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast msn = Toast.makeText(getApplicationContext(), "Nel we", Toast.LENGTH_SHORT);
                msn.show();
            }


        }
    }

    //Método provisional
    public void Edit(View edit){
        Intent intent = new Intent(Add_New_Reminder.this, Editar.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            Intent i = new Intent(Add_New_Reminder.this, activity_Help.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
