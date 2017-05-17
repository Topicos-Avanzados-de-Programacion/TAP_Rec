package cry.who.boy.tap_recordatorios;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Editar extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar; //Declarar el Toolbar
    private Button btn_fecha_2, btn_hora_2, btn_cancelar_2, btn_ok_2;
    private TextView tv_fecha_2, tv_hora_2;
    private EditText et_Titulo_2, et_Desc_2;
    private int dia, mes, anio, hora, minutos;
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    Spinner Importancia;
    //Variables para saber la fecha de creación del recordatorio
    private int d_act, m_act, a_act;
    private DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);//Conectamos el Layout al objeto Toolbar
        setSupportActionBar(toolbar);//Configuración de la barra de herramientas (toolbar)

        // como la Barra de Acciones (ActionBar) con la llamada de setSupportActionBar()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Así se pone el título que ya había creado en Values/strings llamado title_activity_help
        getSupportActionBar().setTitle(R.string.title_activity_editar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.volver_blanco));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View atras) {
                Intent Atras=new Intent(Editar.this, MainActivity.class);
                startActivity(Atras);
                finish();
            }
        });

       //Botones
        btn_cancelar_2 = (Button) findViewById(R.id.btn_cancel_2);
        btn_ok_2 = (Button) findViewById(R.id.btn_ok_2);
        btn_fecha_2 = (Button) findViewById(R.id.btn_fecha_2);
        btn_hora_2 = (Button) findViewById(R.id.btn_hora_2);
        //Edit Texts
        et_Titulo_2 = (EditText) findViewById(R.id.et_title_2);
        et_Desc_2 = (EditText) findViewById(R.id.et_text_2);//Descripción
        btn_hora_2.setOnClickListener(this);
        //TextView
        tv_fecha_2 = (TextView) findViewById(R.id.tv_fecha_2);
        tv_hora_2 = (TextView) findViewById(R.id.tv_hora_2);
        //Spinner Importancia
        Importancia = (Spinner) findViewById(R.id.spinner_2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Importancia, android.R.layout.simple_spinner_item);
        Importancia.setAdapter(adapter);
        myDb = new DatabaseHelper(this);
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
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        if (extras!=null){
            String titulo=extras.getString("titulo");
            String fecha=extras.getString("fecha");
            String hora=extras.getString("hora");
            String descripcion=extras.getString("descripcion");
            int importancia=extras.getInt("importancia");

            et_Titulo_2.setText(titulo);
            tv_fecha_2.setText(fecha);
            tv_hora_2.setText(hora);
            et_Desc_2.setText(descripcion);
            Importancia.setSelection(importancia);

        }
    }

    //Metodos para Fecha
    public void mostrarFecha(){
        tv_fecha_2.setText(dia+"-"+(mes+1)+"-"+anio);
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
                tv_hora_2.setText(hora+":"+minutos);
            }
        },hora,minutos,false);
        timePickerDialog.show();
    }
    public void Cancelar(View view){
        Intent intent = new Intent(Editar.this, MainActivity.class);
        startActivity(intent);
    }
    public void MandarDatos(View vista){

        String Titulo = et_Titulo_2.getText().toString();
        String Fecha = tv_fecha_2.getText().toString();// <---
        String Hora = tv_hora_2.getText().toString();// <---
        String Desc = et_Desc_2.getText().toString();
        String Import =Importancia.getSelectedItem().toString();
        int Imagen = 0;
        switch (Import){
            case "Alta":
                Imagen=R.drawable.ic_rojo;
                break;
            case "Normal":
                Imagen=R.drawable.ic_amarillo;
                break;
            case "Baja":
                Imagen=R.drawable.ic_verde;
                break;
        }
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
            Rec rec=new Rec(Imagen,Titulo, Fecha, Hora, Desc);
            boolean veri=myDb.insertData(rec);
            if (veri){
                Toast msn = Toast.makeText(getApplicationContext(), "Guardado Satisfactoriamente", Toast.LENGTH_SHORT);
                msn.show();
                Intent intent = new Intent(Editar.this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast msn = Toast.makeText(getApplicationContext(), "Nel we", Toast.LENGTH_SHORT);
                msn.show();
            }


        }
    }
}
