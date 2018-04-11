package model;

public class Util {
	
	public static String anadeCeros(String cadena) {
		String construccion = null;
		switch(cadena.length()) {
		case 1:
			construccion = "000000000000"+cadena;
			break;
			
		case 2:
			construccion = "000000000000"+cadena;
			break;
			
		case 3:
			construccion = "00000000000"+cadena;
			break;
			
		case 4:
			construccion = "0000000000"+cadena;
			break;
			
		case 5:
			construccion = "000000000"+cadena;
			break;
			
		case 6:
			construccion = "00000000"+cadena;
			break;
			
		case 7:
			construccion = "0000000"+cadena;
			break;
			
		case 8:
			construccion = "000000"+cadena;
			break;
			
		case 9:
			construccion = "00000"+cadena;
			break;
			
		case 10:
			construccion = "0000"+cadena;
			break;
			
		case 11:
			construccion = "000"+cadena;
			break;
			
		case 12:
			construccion = "00"+cadena;
			break;
			
		case 13:
			construccion = "0"+cadena;
			break;		
			
			
			default:
				construccion = cadena;
				break;
				
		
		}
		return construccion;
		
	}

}
