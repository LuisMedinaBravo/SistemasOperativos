package Tarea2_AlgoritmoBanquero;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Algoritmo {

    public int necesarios[][],
        
            asignados[][],
            maximos[][],
            disponibles[][];
        public String Procesos;
        public String Recursos;
        
        int vuelta=0;
                            
        public int nSolicitados;

    public void entrada(ArrayList<Integer> numeroRecursos, ArrayList<Integer> numeroProcesos, ArrayList<String> izquierda, ArrayList<String> derecha) {
        
      
        int[] arrayRecursos = new int[numeroRecursos.size()];
        int[] arrayProcesos = new int[numeroProcesos.size()];
        
        System.out.println("Lista Recursos: ");
        for(int i = 0; i < numeroRecursos.size(); i++){ 
            arrayRecursos[i] = numeroRecursos.get(i);
            //System.out.println(arrayRecursos[i]);
        }
        System.out.println("Lista Procesos: ");
        for(int i = 0; i < numeroProcesos.size(); i++){ 
            arrayProcesos[i] = numeroProcesos.get(i);
            //System.out.println(arrayProcesos[i]);
        }
        
        
        try (Scanner sc = new Scanner(System.in)) {    
            
            
            necesarios = new int[numeroProcesos.size()/3][numeroRecursos.size()];  //inicializacion de arrays
            maximos = new int[numeroProcesos.size()/3][numeroRecursos.size()];
            
            asignados = new int[nSolicitados][numeroRecursos.size()];
            disponibles = new int[1][numeroRecursos.size()];
            
            
            System.out.println("Introduzca matriz de asignados -->"); ///derechs separar por tokens
            for (int i = 0; i < nSolicitados; i++) {
                for (int j = 0; j < numeroRecursos.size(); j++) {
                    
                    
                    //asignados[i][j] = sc.nextInt();  //matriz de asignados
                        
                        String[] parts = derecha.get(vuelta).split(" ");
                        
                        String part1 = parts[0];
                        
                        int parte1 = Integer.parseInt(part1);
                        
                        String part2 = parts[1]; 
                        
                        int parte2 = Integer.parseInt(part2);
                        
                        String part3 = parts[2]; 
                        
                        int parte3 = Integer.parseInt(part3);
                        
                        //System.out.println(part1);
                        //System.out.println(part2);
                        //System.out.println(part3);
                        
                        if(j==0){
                            asignados[i][j] = parte1;
                        }else if(j==1){
                            asignados[i][j] = parte2;
                        }else if(j==2){
                            asignados[i][j] = parte3;
                        }
                }
                vuelta++;
            }
            
            
            vuelta=0;
            System.out.println("Introduzca matriz máxima -->"); //numeroProcesos
            for (int i = 0; i < numeroProcesos.size()/3; i++) {
                for (int j = 0; j < numeroRecursos.size(); j++) {
                    //maximos[i][j] = sc.nextInt();  //matriz maxima
                    
                       
                        maximos[i][j] = arrayProcesos[vuelta];
                        //System.out.println(maximos[i][j]);
                        
                        vuelta++;
                        
                        
                }
                
            }
            
         
            System.out.println("----------------------------------------------------------");
            System.out.println("Introduzca matriz disponibles -->"); //numeroRecursos
            for (int j = 0; j < numeroRecursos.size(); j++) {
                //disponibles[0][j] = sc.nextInt();  //matriz de disponibles
                disponibles[0][j] = arrayRecursos[j]; 
                System.out.println(disponibles[0][j]);
            }
        }
    }

    private int[][] calculoNecesarios(int numRecursos, int numProcesos) {
        for (int i = 0; i < numProcesos; i++) {
            for (int j = 0; j < numRecursos; j++) //calculando matriz de necesarios
            {
                necesarios[i][j] = maximos[i][j] - asignados[i][j];
            }
        }

        return necesarios;
    }

    private boolean chequear(int i, int numRecursos) {
        //chequeando si todos los recursos para el proceso pueden ser asignados
        for (int j = 0; j < numRecursos; j++) {
            if (disponibles[0][j] < necesarios[i][j]) {
                return false;
            }
        }

        return true;
    }

    public void esSeguro(int listaSolicitudes, ArrayList<Integer> numeroRecursos, ArrayList<Integer> numeroProcesos, ArrayList<String> izquierda, ArrayList<String> derecha) {
    	
        this.nSolicitados=listaSolicitudes;
        entrada(numeroRecursos, numeroProcesos, izquierda, derecha);
        calculoNecesarios(numeroRecursos.size(),numeroProcesos.size()/3);
        boolean done[] = new boolean[nSolicitados];
        int j = 0;
  
        
        try{
        
        
        while (j < nSolicitados) {  //hasta que todos los procesos se asignen
            boolean asignado = false;
            for (int i = 0; i < numeroProcesos.size()/3; i++) {
                if (!done[j] && chequear(i,numeroRecursos.size())) {  //intentando asignar
                    for (int k = 0; k < numeroRecursos.size(); k++) {
                        disponibles[0][k] = disponibles[0][k] - necesarios[i][k] + maximos[i][k];
                    }
                    System.out.println("Proceso asignado : " + i);
                    System.out.print("Proceso "+i+" asigna:");
                    for (int k = 0; k < numeroRecursos.size(); k++) {
                    	System.out.print(asignados[j][k]);
                    }
                    System.out.println("");
                    asignado = done[j] = true;
                    j++;
                    
                }
            }
            if (!asignado) {
                break;  //si no esta asignado
            }
            if (j == numeroProcesos.size()/3) //si todos los procesos estan asignados
            {
                System.out.println("\nAsignado de forma segura");
            } else {
                System.out.println("Todos los procesos se pueden asignar de forma segura");
            }   
        }
        
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    
    public int ValidarEntrada(String Entrada){
            
            
            ///es numero?
            try{
                //si es numero
                int esNumero=Integer.parseInt(Entrada);
                
                if((esNumero>0)){
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