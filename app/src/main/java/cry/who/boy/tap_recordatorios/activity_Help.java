package cry.who.boy.tap_recordatorios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class activity_Help extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__help);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);//Conectamos el Layout al objeto Toolbar
        setSupportActionBar(toolbar);//Configuración de la barra de herramientas (toolbar)
        // como la Barra de Acciones (ActionBar) con la llamada de setSupportActionBar()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Con esto aparece la flecha de atrás en la action bar
        //Así se pone el título que ya había creado en Values/strings llamado title_activity_help
        getSupportActionBar().setTitle(R.string.title_activity__help);
        //Se pone un subtítulo
        toolbar.setSubtitle("Más inf. al correo colima_97@hotmail.com");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.volver_blanco));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View atras) {
                onBackPressed();
                finish();
            }
        });
    }

    //Este método nos permite usar el botón atrás
    // que ya está presente en Android en la parte baja
    // de nuestra aplicación
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
