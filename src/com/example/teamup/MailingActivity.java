package com.example.teamup;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.teamup.bean.Equipo;
import com.example.teamup.bean.Evento;

public class MailingActivity extends Activity {
	
	private TextView Text;
	private Evento evento;
	private int igualdad;
	private int iteraciones;
	CustomAutoComplete autoCompleteTextView;
	public ArrayList<String> list_Mail = new ArrayList<String>();
	public ArrayList<String> list_Blank = new ArrayList<String>();
	String[] mail_Val = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      try{
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_mailing);
    	
    	final Button btnSend = (Button)findViewById(R.id.btnSendMail); // Boton de envio de mail
    	Bundle bundle = this.getIntent().getExtras(); //Contiene la información pasada en el intent
    	//
    	evento = (Evento) bundle.getSerializable("EVENTO");   
        igualdad = bundle.getInt("IGUALDAD");
        iteraciones = bundle.getInt("ITERACIONES");  
    	//
    	// Inicializamos el Autocomplete para que busque los mails de los contactos
    	// 
        autoCompleteTextView = (CustomAutoComplete)findViewById(R.id.etEmail);
		//
    	// Si en la configuracion del programa se ha establecido un mail por defecto, lo 
    	// asignamos al campo destinatario
    	//
    	SharedPreferences pref = getSharedPreferences("com.example.teamup_preferences", MODE_PRIVATE);
		String dir_mail_envio = String.valueOf(pref.getString("dir_mail_envio", ""));
		autoCompleteTextView.setText(dir_mail_envio);
		//
		// Como obtener el listado de contactos puede tardar, lo hacemos en 
		// un nuevo hilo
		//
		new Thread(new Runnable() {
		    public void run() {
		    	list_Mail = ObtenerMails();
		    	autoCompleteTextView.post(new Runnable() {
		            public void run() {
		            	autoCompleteTextView.setAdapter(new ArrayAdapter<String>(MailingActivity.this, R.layout.list_autocomplet_item, list_Mail));
		                }
		            });

		    }
		}).start();
		//
		// Establecemos el adaptador inicial del autocomplete
		//
		list_Blank.add(" ");
		autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_autocomplet_item, list_Blank));
		//autoCompleteTextView.setThreshold(2);
		//
        TextView txtAsunto = (TextView)findViewById(R.id.etSubject);
        txtAsunto.setText("Se ha generado un nuevo Evento en el que participas");
        //
        // Jacin (13/02/2013): Nuevo envio de mail TRASPARENTE para el 
        // usuario utilizando el API de GMAIL
        //
        btnSend.setOnClickListener(new View.OnClickListener() { 

        	@Override
            public void onClick(View v) {
                try { 
                	// 
                	// Montamos el progressDialog que se muestra mientras 
                	// se envia el mail
                	//
                	final ProgressDialog pd = ProgressDialog.show(MailingActivity.this, "Enviando el mail", "Espere unos segundos...", true, false);
                    //
                    // Lanzamos en otro hilo el envio de mail
                    //
                    new Thread(new Runnable()
                    {
                    	public void run()
                    	{
                    		EnvioMail();
                            //
                            // Finalmente eliminamos el Procesando y Generamos el 
                            //mensaje de confirmacion y volvemos a la pantalla home
                            //
                            if (pd != null) {
                                pd.dismiss();
                            }
                            LanzarActivityResult();
                            setResult(1);
                            finish();
                    	}
                    }).start();  
                    //
                } catch (Exception e) {   
                    Log.e("SendMail", e.getMessage(), e);   
                } 

            }
        });
        
      }
      catch(Error e){
            Log.e("JACK", e.getMessage(), e);
            Text.append("Ups, que incomodidad! se ha producido un error");
      }

    }
 
    private void EnvioMail()
    {
    	try 
    	{ 
    		String vHtml;
    		// obtenemos los datos para el envío del correo 
    		TextView etEmail = (TextView) findViewById(R.id.etEmail);
    		TextView etSubject = (TextView) findViewById(R.id.etSubject);
    		//
    		// Montamos el cuerpo del mail
    		//
    		vHtml = MontarHtmlMail();
    		// 		
    		GMailSender sender = new GMailSender("soporte.teamup@gmail.com", "subnormales");
    		sender.sendMail(etSubject.getText().toString(),   
    						vHtml, 
    						"soporte.teamup@gmail.com",  
    						etEmail.getText().toString()   
            				); 
    	} catch (Exception e) 
    	{   
    		Log.e("SendMail", e.getMessage(), e);   
    	} 
    }
    
    private void LanzarActivityResult()
    {
    	try 
    	{ 
    		Bundle b = new Bundle();
    		b.putSerializable("EVENTO",(Serializable) evento);
            b.putInt("IGUALDAD",igualdad);
            b.putInt("ITERACIONES",iteraciones);
    		Intent intent = new Intent(MailingActivity.this, ResultSorteoActivity.class);  
    		intent.putExtras(b); 
    		startActivity(intent);
    	} catch (Exception e) 
    	{   
    		Log.e("LanzarActivityResult", e.getMessage(), e);   
    	} 
    }
    
    private String MontarHtmlMail(){
    	String Result;
    	SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy, 'a las' HH:mm'h'");
    	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    	String fecha_hora = "";
    	try{
    		fecha_hora = sdf.format(evento.getFechaYhora().getTime());
    	}catch (Exception e){
    		fecha_hora = sdf2.format(evento.getFechaYhora().getTime());
    	}
    	//
    	Result = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>";
		Result += "<body style='text-align: left; font-family: Tahoma, Arial, Geneva, sans-serif; color:#651616;'>";
		Result += "<table>";
		Result += "<tr><td>";
		Result += "<div style='width:500px;'>";
		Result += "<h2 style='font-size: 20px; color: #B72727; font-weight: normal'>NUEVO SORTEO!!! <span style='font-size: 20px; color: #000000'><br>Se ha creado un evento en el que participas</span></h2><br>";
		Result += "<div style='font-size: 15px; color: #000000; font-weight: normal'><span style='font-size: 20px;width:100px;'>Nombre: </span>"+evento.getNombre()+"</div>";
		Result += "<div style='font-size: 15px; color: #000000; font-weight: normal'><span style='font-size: 20px;width:100px;'>Lugar: </span>"+evento.getLugar()+"</div>";
		Result += "<div style='font-size: 15px; color: #000000; font-weight: normal'><span style='font-size: 20px;width:100px;'>Fecha: </span>"+fecha_hora+"</div>";
		Result += "</div>";
		Result += "</td><td>";
		Result += "<div style='width:150px;text-align: center;'>";
		Result += "<div><img src='https://lh6.googleusercontent.com/-LkMD7puhZtM/USUebwMltDI/AAAAAAAAAAo/HYXmKLFcOiI/s144/ic_launcher.png' height='96' width='96' /><br>";
		Result += "<h2 style='margin-top:0px;'>TEAM UP</h2></div>";
		Result += "</div>";
		Result += "</td></tr></table>";
		Result += "<div>";
		Result += "<table>";
		//
    	// Recorremos el mapa de equipos para pintarlos
		//
		for (int i=0; i<evento.getEquipos().size(); i++) {
			// Pintamos el titulo del equipo
			if ((i) % 3 == 0){
				Result += "<tr>";
			}
			Result += "<td>";
			Result += "<div style='margin-right:50px; color: #000000'>";
			Result += "<h1 style='text-transform: uppercase; text-decoration: underline; margin-bottom:5px;'>"+evento.getEquipos().get(i).getNombre()+"&nbsp;</h1>";
			Result += "<h3 style='text-transform: uppercase; margin-top:0px; margin-bottom:10px;'>"+evento.getEquipos().get(i).getColor()+"&nbsp;</h3>";
			
			// Recorremos la lista de miembros del equipo para pintarlos
			Result += "<ul style='list-style-position: inside; margin-top: 0px; padding-left: 5px;'>";
			for(int j=0;j<evento.getEquipos().get(i).getMiembros().size();j++){
        		Result += "<li>" + evento.getEquipos().get(i).getMiembros().get(j).getNombre() + "</li>";
			}
        	Result += "</ul></div></td>";
			if ((i+1) % 3 == 0){
				Result += "</tr>";
			}
		}
		Result += "</table>";
		Result += "</div>";
		Result += "<div style='width: 600px; margin-top: 50px; margin-bottom: 20px'>";
		Result += "<h2 style='font-size: 20px; color: #B72727; font-weight: normal'><span style='color: #B72727'>La mejor aplicacion Android para el sorteo de equipos</span></h2>";
		Result += "<p style='font-size: 13px;'>Con Team Up podras gestionar encuentros deportivos con tus amigos, realizar un seguimiento de tu evolucion y asegurarte de que los sorteos siempre seran lo mas justos posibles.</p>";
		Result += "<p style='font-size: 13px;'>Estamos Naciendo...</p>";
		Result += "</div>";
		Result += "</div><a style = 'font-size: 16px; color: #285BE6; text-decoration: none' href='mailto:soporte.teamup@gmail.com'>Para cualquier duda o comentario contacte con el equipo de desarrollo</a>";
		Result += "</body></html>";
		//
		return Result;
    }
    
    // Devuelve un string con la informacion de los equipos
    public String pintarEquipos(ArrayList<Equipo> equipos){
    	String aux = "";
    	DecimalFormat df = new DecimalFormat("#.###");
    	// Recorremos los equipos
		for (int i=0; i<equipos.size(); i++) {
			aux += equipos.get(i).getNombre() + " (" + equipos.get(i).getColor() + ") " + "[" + df.format(equipos.get(i).getTotalPuntEquipo()) + "; " + df.format(equipos.get(i).getMediaEquipo()) + "]:\n";
			// Recorremos los miembros del equipo
			for(int j=0; j<equipos.get(i).getMiembros().size(); j++){
				aux += equipos.get(i).getMiembros().get(j).getNombre() + "\n";
			}
			aux += "\n\n";
		}
		return aux;
    }

    // Devuelve un string con la lista de contactos de la agenda
    public ArrayList<String> ObtenerMails(){

       ArrayList<String> list_aux = new ArrayList<String>();
	   Cursor cur_Contactos = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
	   while(cur_Contactos.moveToNext()) {
	      int nameFieldColumnIndex = cur_Contactos.getColumnIndex(PhoneLookup.DISPLAY_NAME);
	      String nombre = cur_Contactos.getString(nameFieldColumnIndex);
	      //
	      // Obtenemos el identificador del contacto
	      //
	      String contactId = cur_Contactos.getString(cur_Contactos.getColumnIndex(ContactsContract.Contacts._ID)); 
	      //
	      // Obtenemos los diferentes mails que pueda tener el contacto
	      //
	      Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null); 

	      while (emails.moveToNext()) { 
	         // 
	   	     // Obtenemos el mail y lo concatenamos con el nombre para añadirlo al array
		     //
	         String emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
		     //
		     // Solo pasamos el dato al array si no esta vacio
		     //
		     if((emailAddress != " " || emailAddress != null) )
		     {
		    	 list_aux.add(nombre+" ("+emailAddress+")");
		     }
	      } 
	      emails.close();
	   }
	   cur_Contactos.close();
	   return list_aux;

    }
	
}

