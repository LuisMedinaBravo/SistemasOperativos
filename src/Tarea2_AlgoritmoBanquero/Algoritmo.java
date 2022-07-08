package Tarea2_AlgoritmoBanquero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Algoritmo {

        
        public int necesarios[][],
        
               asignados[][],
               maximos[][],
               disponibles[][];
    
        public String
            
            procesosQueFaltanSucio[][],
            ordenProcesosFinalizados[];
    
        public String Procesos;
        public String Recursos;
        
        int vuelta=0;
                            
        public int nSolicitados;
        
        ArrayList<ArrayList<String>> procesosQueFaltan;
        
        ArrayList<String> orden = new ArrayList<String>();
        
        
        

    public void entrada(ArrayList<Integer> numeroRecursos, ArrayList<Integer> numeroProcesos, ArrayList<String> izquierda, ArrayList<String> derecha, int numProcesos) {
        
      
        int[] arrayRecursos = new int[numeroRecursos.size()];
        int[] arrayProcesos = new int[numeroProcesos.size()];
        procesosQueFaltanSucio = new String[numProcesos][nSolicitados];

       // System.out.println("Lista Recursos: ");
        for(int i = 0; i < numeroRecursos.size(); i++){ 
            arrayRecursos[i] = numeroRecursos.get(i);
            //System.out.println(arrayRecursos[i]);
        }
        //System.out.println("Lista Procesos: ");
        for(int i = 0; i < numeroProcesos.size(); i++){ 
            arrayProcesos[i] = numeroProcesos.get(i);
            //System.out.println("a "+arrayProcesos[i]);
        }
        
        //Listas de procesos que faltan
        for (int i = 0; i < numProcesos; i++) {
        	for (int j = 0; j < izquierda.size(); j++) {
        		if (izquierda.get(j).equals(""+i)) {
        			procesosQueFaltanSucio[i][j]=derecha.get(j);
				}
        		
			}
			
		}
        procesosQueFaltan = new ArrayList<>();
        for (int i = 0; i <numProcesos; i++) {
        	procesosQueFaltan.add(new ArrayList<String>());
        	for (int j = 0; j < izquierda.size(); j++) {
        		if (procesosQueFaltanSucio[i][j]!=null) {
        			//System.out.println("Falta el por asignar el " +procesosQueFaltanSucio[i][j] + " que es del proceso "+i);
        			procesosQueFaltan.get(i).add(procesosQueFaltanSucio[i][j]);
				}
        		
			}
			
		}
//        System.out.println("PROBANDO LA LISTA");
//        for (int i = 0; i < procesosQueFaltan.size(); i++) {
//			for (int j = 0; j < procesosQueFaltan.get(i).size(); j++) {
//				System.out.println("Falta el por asignar el " +procesosQueFaltan.get(i).get(j) + " que es del proceso "+i);
//			}
//		}
        
        try (Scanner sc = new Scanner(System.in)) {    
            
            
            necesarios = new int[numProcesos][numeroRecursos.size()];  //inicializacion de arrays
            maximos = new int[numProcesos][numeroRecursos.size()];
            
            asignados = new int[nSolicitados][numeroRecursos.size()];
            disponibles = new int[1][numeroRecursos.size()];
            
            
            //System.out.println("Introduzca matriz de asignados -->"); ///derechs separar por tokens
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
            //System.out.println("Introduzca matriz máxima -->"); //numeroProcesos
            for (int i = 0; i < numProcesos; i++) {
                for (int j = 0; j < numeroRecursos.size(); j++) {
                    //maximos[i][j] = sc.nextInt();  //matriz maxima
                    
                       
                        maximos[i][j] = arrayProcesos[vuelta];
                        //System.out.println(maximos[i][j]);
                        
                        vuelta++;
                        
                        
                }
                
            }
            
         
//            System.out.println("----------------------------------------------------------");
//            System.out.println("Introduzca matriz disponibles -->"); //numeroRecursos
            for (int j = 0; j < numeroRecursos.size(); j++) {
                //disponibles[0][j] = sc.nextInt();  //matriz de disponibles
                disponibles[0][j] = arrayRecursos[j]; 
                //System.out.println(disponibles[0][j]);
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

    public void esSeguro(int listaSolicitudes, ArrayList<Integer> numeroRecursos, ArrayList<Integer> numeroProcesos, 
    		ArrayList<String> izquierda, ArrayList<String> derecha, int numProcesos) {
    	
        this.nSolicitados=listaSolicitudes;
        entrada(numeroRecursos, numeroProcesos, izquierda, derecha, numProcesos);
        calculoNecesarios(numeroRecursos.size(),numProcesos);
        boolean done[] = new boolean[nSolicitados];
        int j = 0;
        
        
        try{
        
        System.out.println("Asignaciones de recursos :");
        
        while (j < nSolicitados) {  //hasta que todos los procesos se asignen
            boolean asignado = false;
            for (int i = 0; i < numProcesos; i++) {
                if (!done[j] && chequear(i,numeroRecursos.size())) {  //intentando asignar
                    for (int k = 0; k < numeroRecursos.size(); k++) {
                        disponibles[0][k] = disponibles[0][k] - necesarios[i][k] + maximos[i][k];
                    }
                    System.out.print("Proceso "+izquierda.get(j)+" asigna ");
                   
                    for (int k = 0; k < numeroRecursos.size(); k++) {
                    	System.out.print(asignados[j][k]+" ");
                    	
                    	
                    }
                    for (int k = 0; k < procesosQueFaltan.get(Integer.parseInt(izquierda.get(j))).size(); k++) {
						if ( procesosQueFaltan.get(Integer.parseInt(izquierda.get(j))).get(k).equals(derecha.get(j))) {
							procesosQueFaltan.get(Integer.parseInt(izquierda.get(j))).remove(k);
							
						}
						if ( procesosQueFaltan.get(Integer.parseInt(izquierda.get(j))).size()==0) {
							//System.out.print("Finaliz� el proceso "+Integer.parseInt(izquierda.get(j)) +" :)");
                                                        orden.add(izquierda.get(j));
							//array list con el orden de término de los procesos
						}
					}
                   
                    
                    
                    System.out.println("");
                    asignado = done[j] = true;
                    j++;
                    
                }
            }
            if (!asignado) {
                break;  //si no esta asignado
            }
            if (j == nSolicitados) //si todos los procesos estan asignados
            {
                //System.out.println("\nAsignado de forma segura");
            } else {
              // System.out.println("Todos los procesos se pueden asignar de forma segura");
            }   
        }
      
        }catch(Exception e){
        	  //System.out.println("\nTodos los procesos se pueden asignar de forma segura\n");
                  System.out.print("Orden de termino de los procesos: ");
                  for (int i = 0; i < orden.size(); i++) {
			System.out.print(orden.get(i)+" ");
		}
                  
           // System.out.println("Error");
        }
    }
}