package descargaArchivos;

/**
 * @author Eduardo Carbajal Reyes
 *
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import model.Util;

public class recuperaXML {

	public static void getXML(String serie, String folio) {
		try {
			
			URL urlXML = new URL("http://150.23.47.119/iwcfdWebCustom/getComprobante?id="+serie+"-"+folio);//Produccion
//			URL urlXML = new URL("http://150.23.47.36/iwcfdWebCustom/getComprobante?id="+serie+"-"+folio);//Produccion
						
			URLConnection conexion = urlXML.openConnection();
			String noPoliza = null;
			
			InputStream is = conexion.getInputStream();
			FileOutputStream fos = null;

			StringWriter writer = new StringWriter();
			org.apache.commons.io.IOUtils.copy(is, writer, "UTF-8");
			String cadenaXML = writer.toString();
			noPoliza = (cadenaXML.indexOf("NumPoliza=") != -1) ? cadenaXML.substring(cadenaXML.indexOf("NumPoliza=")+11, cadenaXML.indexOf("IdTransaccion=")-2) : "";
//			System.out.println(noPoliza); //Imprime no de poliza
			
			//System.out.println(cadenaXML);//Imprimir valor del XML
			
			System.out.println("*************************************************"
					+ "\n["+serie+"]["+Integer.valueOf(folio)+"] Procesando...");
			
			String status = (cadenaXML.contains("error") || cadenaXML.contains("ERROR")) ? "Error" : "Correcto";
			
				System.out.println("["+serie+"]["+Integer.valueOf(folio)+"] "+status);
				
				is = urlXML.openConnection().getInputStream();
				
				fos = new FileOutputStream("C:\\PDF_XML\\XML\\"+Util.anadeCeros(noPoliza)+"_"+serie+"-"+Integer.valueOf(folio)+".XML");
				
				byte[] arreglo = new byte[1000];
				int leido = is.read(arreglo);
				
				while (leido>0) {
					fos.write(arreglo,0,leido);
					leido = is.read(arreglo);
				}
				
				System.out.println("["+serie+"]["+Integer.valueOf(folio)+"] Archivo XML descargado");
				fos.close();
				

				recuperaPDF.getPDF(noPoliza, serie, folio);
			

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	
}
