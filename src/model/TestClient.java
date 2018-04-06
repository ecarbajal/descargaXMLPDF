package model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.*;

import descargaArchivos.recuperaPDF;
import descargaArchivos.recuperaXML;



public class TestClient {
	private String archivoSalida="\\Informacion-Fiscal\\Autos\\FacmanUUID\\Cifras_UUID_PDF_XML.txt";
	
	private String archivoEntrada = "C:\\DPFT\\DNO.EOCGDP00.txt";
	private String archivoPaso = "C:\\ConectorREST\\dataIWCFD\\Cifras_UUID_PDF_XML.txt";
	private String server="150.23.1.10";
	private String userServ="ftprseguro";
	private String passServ="frseguro";
	private String host="150.23.1.13";
	private String userHost="tv6jcg";
	private String passHost="mexico15";
	private String remoteFile1 = "\'DNO.EOCGDP00'"; // PRODUCCION


	public void Consulta() throws IOException{
		String serie="", folio="",cadena="";
		int cont=1;
//		boolean validaCon = descarga(host, userHost, passHost, archivoEntrada, remoteFile1);
		boolean validaCon = true;
		if(validaCon) {
			FileReader f = new FileReader(archivoEntrada);
			BufferedReader b = new BufferedReader(f);
			//descarga(server, userServ, passServ, archivoPaso,archivoSalida);
			
			while((cadena = b.readLine())!=null) {
				serie = cadena.substring(39, 43);
				folio = cadena.substring(44, cadena.length()).trim();
				recuperaXML.getXML(serie, folio);
				recuperaPDF.getPDF(serie, folio);
				System.out.println("Registro: " + cont);
				cont++;
			}
			b.close();
			System.out.println("Descarga de archivos finalizada.");
			
		}else {
			System.out.println("Error al descargar el archivo de entrada");
		}
	}

	private boolean descarga(String server, String user, String pass, String archivo,String remoteFile1){
		boolean ind=false;
		FTPClient cliente = new FTPClient();		
		try {
			cliente.connect(server);
			int reply = cliente.getReplyCode();
			if (reply == 220) {
				if(cliente.login(user,pass)){
					System.out.println("Inicio de sesion correcto");
					File downloadFile1 = new File(archivo);
					BufferedOutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));				
					ind = cliente.retrieveFile(remoteFile1, outputStream1);
//					cliente.deleteFile(remoteFile1);
					outputStream1.close();
					//ind=true;
					
					System.out.println(ind ? "Descarga completa" : "No descargo");
					
				}else {
					System.out.println("Usuario o contrasena incorrectos.");
				}
			}else {
				System.out.println("Error conexión");
			}
			
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return ind;
	}



}
