package Tarea1_Threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LeerArchivo {
    
    int contArchivos=0;
    
    public LeerArchivo(){
        
        this.contArchivos=contArchivos;
        
    }
    
     public String leerArchivos(int num) throws FileNotFoundException{

        char nombreTexto = convertir(num);
        String charToString = String.valueOf(nombreTexto);
        //System.out.println(charToString);
        
        FileReader fr;
        
        try{
            //Si existe el archivo
            fr = new FileReader(charToString+".txt");
            return charToString;
        
        }catch(Exception e){
            //System.out.println("No existe\n");
            //No existe el archivo
            return "";
        }   
    }
     
      public static char convertir(int numero){
     
        char convertedChar = (char)numero;
        //System.out.println(convertedChar);
        
        return convertedChar;
     
 }    
      
    public String[] RellenarCadenaArchivos(){
          
      String cadenaArchivos[] = new String[27];
      
      int num=97;
      int posicionArray=0;
      
      while(num < 123){
   
            LeerArchivo obj = new LeerArchivo();
      
            try{
                String nombreReal = obj.leerArchivos(num);
            
                if(nombreReal == ""){
                
                }
                else{
          
                    FileReader fr;
                    fr = new FileReader(nombreReal+".txt");
                    int caract = fr.read();
                    int count=0;
                    
                    while (caract != -1) {

                        caract = fr.read();
                        count++;

                    }
                    
                    if((count>=19)&&(count<100)){
                        
                        cadenaArchivos[posicionArray]=nombreReal;
	                posicionArray++;
          
                    }
                }
      
        num++;
       }catch (FileNotFoundException ex) {
              Logger.getLogger(EjecutarHilo.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(EjecutarHilo.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
      
      for(int i =0; i < cadenaArchivos.length; i++){
          
          
          if(cadenaArchivos[i]!=null){
            contArchivos++;
          }
      }      
        return cadenaArchivos;
      }   
}
