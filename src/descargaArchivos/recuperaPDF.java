package descargaArchivos;

/**
 * @author Eduardo Carbajal Reyes
 *
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class recuperaPDF {

	public static void getPDF(String serie, String folio) {
		try {
//			URL urlPDF = new URL("http://150.23.47.36/iwcfdWebCustom/getPdf?id="+serie+"-"+Integer.valueOf(folio)+"&x=0&estatus=1");//Desarrollo
			
			URL urlPDF = new URL("http://150.23.47.119/iwcfdWebCustom/getPdf?id="+serie+"-"+Integer.valueOf(folio)+"&x=0&estatus=1");//Produccion
			
			URLConnection conexion = urlPDF.openConnection();
			
			InputStream is = conexion.getInputStream();
			
			FileOutputStream fos = new FileOutputStream("C:\\PDF_XML\\PDF\\"+serie+"-"+Integer.valueOf(folio)+".pdf");
			
			byte[] arreglo = new byte[1000];
			int leido = is.read(arreglo);
			
			while (leido>0) {
				fos.write(arreglo,0,leido);
				leido = is.read(arreglo);
			}
			
			System.out.println("["+serie+"]["+Integer.valueOf(folio)+"] Archivo PDF descargado");
			fos.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
