package Tarea1_Threads;

import java.io.FileReader;


public class EjecutarHilo implements Runnable {
    
    String archivo;
    int id;
    
    public EjecutarHilo(String name, int n){
        
        this.archivo=name;
        this.id=n;
 
    }

    @Override
    public void run(){
    	//System.out.println("lala "+Main.cadenadeArchivos[0]);
    	//System.out.println("lala "+Main.cadenadeArchivos[1]);
    	//System.out.println("lala "+Main.cadenadeArchivos[2]);
    	int salto=Main.posicionDondeEstoy(archivo);
        try {
   
            FileReader fr;
            boolean sigueCorriendo=true;
            fr = new FileReader(this.archivo+".txt");
            Main.estaOcupado[salto]=true;
            while (Main.contadorArchivos>0) {
            	
            	int caract = fr.read();
                
              if(caract!=-1){
                  System.out.print((char)caract+"");
                  Thread.sleep(1000);
              }
              
                while (caract != -1 && caract != 63) {
                    
                    //se leen los caracteres de los archivos
                    caract = fr.read();
                    
                    if(caract != -1){
                    
                        System.out.print((char)caract+"");
                        Thread.sleep(1000);
                    }
                    
                }
                if (sigueCorriendo){
                	
                    System.out.print(" Se terminó la descarga del Archivo "+this.archivo+".txt \n"); 
                    Main.contadorArchivos=Main.contadorArchivos-1;
                    
                    //System.out.println("Nos quedan "+Main.contadorArchivos+" archivos");
                    if(Main.contadorArchivos==0){
                        Thread.currentThread().interrupt();
                        System.exit(0);
                    }
                }
                sigueCorriendo=false;
              
                
                salto=1+Main.posicionDondeEstoy(archivo);
                //System.out.println("\nSoy el hilo "+(this.id+1)+" Voy intentar saltar a la posicion "+salto+" desde la posicion "+Main.posicionDondeEstoy(archivo));
                if (salto < Main.estaOcupado.length) {
                    if ( Main.estaOcupado[salto]==false) {
                    	
                    	//System.out.println("\nEstoy en la letra "+this.archivo + " y en el hilo "+ (this.id+1) +" y salte al espacio "+ salto +" con la letra "+Main.cadenadeArchivos[salto]);
                    	Main.estaOcupado[salto]=true;
                    	 this.archivo=Main.cadenadeArchivos[salto];
                    	 fr = new FileReader(this.archivo+".txt");
                    	 sigueCorriendo=true;
                    	
    				}
                    else {
                    
    			//System.out.println("\nSoy el hilo "+(this.id+1)+ " manejando el archivo "+this.archivo+ " y no pude meterme al "+Main.cadenadeArchivos[salto]+" porque estaba ocupado por otro "
    			//				+ " hilo :( ");
    			this.archivo=Main.cadenadeArchivos[salto];
    					
    				
                    }
		    }else {
			//System.out.println("\nSoy el hilo "+(this.id+1)+" manejando el archivo "+this.archivo+ " y no pude saltar porque estoy en el último"
			//		+ " archivo :( ");
					
                    }
                //System.out.println("lala "+Main.cadenadeArchivos[Main.saltoArchivo]);
            }
            
         fr.close();
         }catch (Exception e) {
             System.out.println("\nHubo un error al leer los archivos");
               
         }
   }
}