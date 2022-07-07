package Tarea2_AlgoritmoBanquero;

import java.util.ArrayList;
import java.util.Scanner;


public class Algoritmo {

    public int necesarios[][],
        
            asignados[][],
            maximos[][],
            disponibles[][];
        public String Procesos;
        public String Recursos;
        
        public int numeroProcesos;
        public int numeroRecursos;
        public int nSolicitados;

    private void entrada() {
        
        int validarProcesos=0;
        int validarRecursos=0;
        
        try (Scanner sc = new Scanner(System.in)) {
            
            
            
            while(validarProcesos ==  0){
                    
                    //numero de procesos
                    System.out.print("Ingrese numero de procesos: ");
                    
                    Procesos = sc.nextLine();
                    System.out.println();
                   
                    validarProcesos = ValidarEntrada(Procesos);
            }
            
            numeroProcesos=Integer.parseInt(Procesos);
            
            while(validarRecursos ==  0){
                    
                    //numero de recursos
                    System.out.print("Ingrese numero de recursos: ");
                   
                    Recursos = sc.nextLine();
                    System.out.println();
                    
                  
                    validarRecursos = ValidarEntrada(Recursos);
            }
           
            numeroRecursos=Integer.parseInt(Recursos);
            System.out.print("\n");
            
            necesarios = new int[numeroProcesos][numeroRecursos];  //inicializacion de arrays
            maximos = new int[numeroProcesos][numeroRecursos];
            
            asignados = new int[nSolicitados][numeroRecursos];
            disponibles = new int[1][numeroRecursos];
            
            System.out.println("Introduzca matriz de asignados -->");
            for (int i = 0; i < nSolicitados; i++) {
                for (int j = 0; j < numeroRecursos; j++) {
                    asignados[i][j] = sc.nextInt();  //matriz de asignados
                }
            }
            System.out.println("Introduzca matriz máxima -->");
            for (int i = 0; i < numeroProcesos; i++) {
                for (int j = 0; j < numeroRecursos; j++) {
                    maximos[i][j] = sc.nextInt();  //matriz maxima
                }
            }
            System.out.println("Introduzca matriz disponibles -->");
            for (int j = 0; j < numeroRecursos; j++) {
                disponibles[0][j] = sc.nextInt();  //matriz de disponibles
            }
        }
    }

    private int[][] calculoNecesarios() {
        for (int i = 0; i < numeroProcesos; i++) {
            for (int j = 0; j < numeroRecursos; j++) //calculando matriz de necesarios
            {
                necesarios[i][j] = maximos[i][j] - asignados[i][j];
            }
        }

        return necesarios;
    }

    private boolean chequear(int i) {
        //chequeando si todos los recursos para el proceso pueden ser asignados
        for (int j = 0; j < numeroRecursos; j++) {
            if (disponibles[0][j] < necesarios[i][j]) {
                return false;
            }
        }

        return true;
    }

    public void esSeguro(int listaSolicitudes) {
    	this.nSolicitados=listaSolicitudes;
        entrada();
        calculoNecesarios();
        boolean done[] = new boolean[nSolicitados];
        int j = 0;
       
        while (j < nSolicitados) {  //hasta que todos los procesos se asignen
            boolean asignado = false;
            for (int i = 0; i < numeroProcesos; i++) {
                if (!done[j] && chequear(i)) {  //intentando asignar
                    for (int k = 0; k < numeroRecursos; k++) {
                        disponibles[0][k] = disponibles[0][k] - necesarios[i][k] + maximos[i][k];
                    }
                    System.out.println("Proceso asignado : " + i);
                    System.out.print("Proceso "+i+" asigna:");
                    for (int k = 0; k < numeroRecursos; k++) {
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
        }
        if (j == numeroProcesos) //si todos los procesos estan asignados
        {
            System.out.println("\nAsignado de forma segura");
        } else {
            System.out.println("Todos los procesos se pueden asignar de forma segura");
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