package Tarea1_Threads;

import java.util.Scanner;

public class Main {
    
	public static int contadorArchivos=0;
	public static String[] cadenadeArchivos;
	public static int saltoArchivo;
	public static boolean[] estaOcupado;
        
	
	public static void main(String[] args) {

       
		contadorArchivos = 0;
		String hiloEntrada="";
		int validar=0;		                
	        //String cadenaArchivos2[] = new String[3];
		//cadenadeArchivos = cadenaArchivos2;
			
		// Llamar a RellenarCadenaArchivos()
			                
		LeerArchivo obj = new LeerArchivo();
		cadenadeArchivos = obj.RellenarCadenaArchivos();
		contadorArchivos = obj.contArchivos;
                estaOcupado = new boolean[contadorArchivos];
                Scanner sc = new Scanner(System.in);
                
                while(validar ==  0){
                    
                    System.out.print("Ingrese cantidad de hilos: ");
                    // entrada de hilos por parte del usuario
                    
                    hiloEntrada = sc.nextLine();
                    System.out.println();
                    
                    //valida entrada de hilos
                    validar = ValidarEntrada(hiloEntrada,contadorArchivos);
                }
                
                saltoArchivo=Integer.parseInt(hiloEntrada);
                //System.out.println("SALTAMOS DE "+saltoArchivo);
                if(contadorArchivos>1){
                    
                    System.out.println("\nSe leyeron " + contadorArchivos + " archivos:\n");
                    
                }else{
                    System.out.println("\nSe leyó " + contadorArchivos + " archivo:\n");
                }
                
             
        for (int j = 0; j < contadorArchivos; j++) {
            
        	if (j < contadorArchivos-1) {
    			System.out.println(cadenadeArchivos[j] + ".txt");
    		} else {
    			System.out.println(cadenadeArchivos[j] + ".txt\n");
    		}
        	estaOcupado[j]=false;
		}
        
        int i = 0;
		//Integer.parseInt(hiloEntrada) = Cantidad de HILOS
        while ( i < Integer.parseInt(hiloEntrada)) {
			if (cadenadeArchivos[i] != null) {
				CreacionHilos(cadenadeArchivos[i], i);
				
			}
			i++;
		}
		
	}

	public static void CreacionHilos(String archivo, int id) {
	
        System.out.println();
		EjecutarHilo d2 = new EjecutarHilo(archivo, id);
		Thread hilo2 = new Thread(d2);
		hilo2.start();
		
	}
        public static int posicionDondeEstoy(String letra) {
        	for (int i = 0; i < cadenadeArchivos.length; i++) {
				if (letra.equals(cadenadeArchivos[i])) {
					return i;
				}
			}
        	//System.out.println("VOY A MORIR");
        	return 0;
        }
        public static int ValidarEntrada(String hiloEntrada, int contadorArchivos){
            
            
            ///es numero?
            try{
                //si es numero
                int esNumero=Integer.parseInt(hiloEntrada);
                
                if((esNumero>0)&&(esNumero<=contadorArchivos)){
                    return 1;
                }
                else{
                    return 0;
                }   
                
                
            }catch(Exception e){
                //no es numero
                return 0;
            }
            
        }
}