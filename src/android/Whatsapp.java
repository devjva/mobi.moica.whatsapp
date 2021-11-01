package mobi.moica.whatsapp;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import android.content.pm.PackageManager;

public class Whatsapp extends CordovaPlugin {
    public static final String ACTION_WHATSAPP_SEND = "send";
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
       
            if (ACTION_WHATSAPP_SEND.equals(action)) {						
						
						try{
						String numero = args.getString(0);
						String mensaje = args.getString(1);
						PackageManager packageManager = cordova.getActivity().getPackageManager();
						Intent i = new Intent(Intent.ACTION_VIEW);
						String url = "https://api.whatsapp.com/send?phone="+ numero +"&text=" + mensaje;
						i.setPackage("com.whatsapp");
						i.setData(Uri.parse(url));
						if (i.resolveActivity(packageManager) != null) {
							cordova.getActivity().startActivity(i);			
							//this.cordova.getActivity().startActivity(mIntent);
								   callbackContext.success();
								   return true;
						}else {
							Toast.makeText(cordova.getActivity(), " ERROR" , Toast.LENGTH_SHORT);
						}
						
						 
					} catch(Exception e) {
						Log.e("ERROR WHATSAPP",e.toString());
						 Toast.makeText(cordova.getActivity(), " ERROR",  Toast.LENGTH_SHORT);
						 System.err.println("Exception: " + e.getMessage());
							callbackContext.error(e.getMessage());
							return false;
					}
					
		}return false;
	}
}
