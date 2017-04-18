package cry.who.boy.tap_recordatorios;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;

public class Add_New_Reminder extends AppCompatActivity implements View.OnClickListener {
    Button btn_fecha, btn_hora;
    EditText et_fecha, et_hora;
    private int dia, mes, anio, hora, minutos;
    Spinner Importancia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__reminder);

        btn_fecha= (Button) findViewById(R.id.btn_fecha);
        btn_hora= (Button) findViewById(R.id.btn_hora);
        et_fecha= (EditText) findViewById(R.id.et_fecha);
        et_hora= (EditText) findViewById(R.id.et_hora);
        btn_fecha.setOnClickListener(this);
        btn_hora.setOnClickListener(this);

        Importancia = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Importancia, android.R.layout.simple_spinner_item);
        Importancia.setAdapter(adapter);
    }

    public void onClick(View v){
        if(v == btn_fecha){
            final Calendar c = Calendar.getInstance();
            dia= c.get(Calendar.DAY_OF_MONTH);
            mes= c.get(Calendar.MONTH);
            anio= c.get(Calendar.YEAR_WOY);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener(){

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    et_fecha.setText(dayOfMonth+"-"+(month+1)+"-"+year);

                }
            }
            ,dia,mes,anio);
            datePickerDialog.show();
        }
        if(v == btn_hora){
            final Calendar c = Calendar.getInstance();
            hora= c.get(Calendar.HOUR_OF_DAY);
            minutos= c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    et_hora.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    }
}
