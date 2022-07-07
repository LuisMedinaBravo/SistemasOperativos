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
        int contadorToken=0;
                            
                        
        
        public int numeroProcesos;
        public int numeroRecursos;
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
            
            /*
            while(validarProcesos ==  0){
                    
                    //numero de procesos
                    System.out.print("Ingrese numero de procesos: ");
                    
                    Procesos = sc.nextLine();
                    System.out.println();
                   
                    validarProcesos = ValidarEntrada(Procesos);
            }
            
            numeroProcesos=Integer.parseInt(Procesos);
            */
            /*
            while(validarRecursos ==  0){
                    
                    //numero de recursos
                    System.out.print("Ingrese numero de recursos: ");
                   
                    Recursos = sc.nextLine();
                    System.out.println();
                    
                  
                    validarRecursos = ValidarEntrada(Recursos);
            }
           
            numeroRecursos=Integer.parseInt(Recursos);
            System.out.print("\n");
            */
            
            necesarios = new int[numeroProcesos.size()][numeroRecursos.size()];  //inicializacion de arrays
            maximos = new int[numeroProcesos.size()][numeroRecursos.size()];
            
            asignados = new int[nSolicitados][numeroRecursos.size()];
            disponibles = new int[1][numeroRecursos.size()];
            
            
            System.out.println("Introduzca matriz de asignados -->"); ///derechs separar por tokens
            for (int i = 0; i < nSolicitados; i++) {
                for (int j = 0; j < numeroRecursos.size(); j++) {
                    StringTokenizer st = null;
                    //asignados[i][j] = sc.nextInt();  //matriz de asignados
                    for (int k = 0; k < derecha.size(); k++) {
                        st = new StringTokenizer(derecha.get(k));
                        System.out.println(st);
                        break;
                    }
                    
                    
                    while (st.hasMoreTokens()){ 
                        
                        String nextToken = st.nextToken();
                        
                        if(contadorToken==0){
                            
                            asignados[i][j]= Integer.parseInt(nextToken);
                        }
                        if(contadorToken==1){
                            asignados[i][j]= Integer.parseInt(nextToken);
                        }
                        if(contadorToken==2){
                            asignados[i][j]= Integer.parseInt(nextToken);
                        }
                        //System.out.println(st.nextToken());
                        contadorToken++;
                    }
                    
                    
                    vuelta++;          
                }
                contadorToken=0;
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
            
            System.out.println(nSolicitados);
            System.out.println("----------------------------------------------------------");
            System.out.println("Introduzca matriz disponibles -->"); //numeroRecursos
            for (int j = 0; j < numeroRecursos.size(); j++) {
                //disponibles[0][j] = sc.nextInt();  //matriz de disponibles
                disponibles[0][j] = arrayRecursos[j]; 
                System.out.println(disponibles[0][j]);
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

    public void esSeguro(int listaSolicitudes, ArrayList<Integer> numeroRecursos, ArrayList<Integer> numeroProcesos, ArrayList<String> izquierda, ArrayList<String> derecha) {
    	
        this.nSolicitados=listaSolicitudes;
        entrada(numeroRecursos, numeroProcesos, izquierda, derecha);
        calculoNecesarios();
        boolean done[] = new boolean[nSolicitados];
        int j = 0;
       
        while (j < nSolicitados) {  //hasta que todos los procesos se asignen
            boolean asignado = false;
            for (int i = 0; i < numeroProcesos.size(); i++) {
                if (!done[j] && chequear(i)) {  //intentando asignar
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
        }
        if (j == numeroProcesos.size()) //si todos los procesos estan asignados
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