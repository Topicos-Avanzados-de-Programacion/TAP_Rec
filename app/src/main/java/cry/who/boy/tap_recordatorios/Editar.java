package cry.who.boy.tap_recordatorios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Editar extends AppCompatActivity {
    private Toolbar toolbar; //Declarar el Toolbar
    Spinner Importancia;

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

        //Spinner Importancia
        Importancia = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Importancia, android.R.layout.simple_spinner_item);
        Importancia.setAdapter(adapter);

    }
}
