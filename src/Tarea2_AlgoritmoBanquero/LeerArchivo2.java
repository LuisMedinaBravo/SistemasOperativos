package Tarea2_AlgoritmoBanquero;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import Tarea1_Threads.Main;

public class LeerArchivo2 {
	ArrayList<Integer> listaRecursos = new ArrayList<Integer>();
	ArrayList<Integer> listaProcesos = new ArrayList<Integer>();
        
        ArrayList<String> izquierda = new ArrayList<String>();
        ArrayList<String> derecha = new ArrayList<String>();
        
	static ArrayList<String> listaSolicitudes = new ArrayList<String>();
	int contLineas = 0;
        int cont= 0;
	String nextToken;
        String primero,segundo,tercero,total;

	public LeerArchivo2() {

		String file = "prueba.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			String line;
			
			contLineas=0;
			while ((line = br.readLine()) != null) {
				
				StringTokenizer str_arr = new StringTokenizer(line);
				
				nextToken = str_arr.nextToken();
				if (contLineas == 1) {
					

					listaRecursos.add(Integer.parseInt(nextToken));
				} else if (contLineas > 2 && nextToken.equals("proceso") == false) {
					listaProcesos.add(Integer.parseInt(nextToken));
				}
				if (nextToken.equals("proceso") == false) {
					while (str_arr.hasMoreTokens()) {
						nextToken = str_arr.nextToken();
						if (contLineas == 1) {
							

							listaRecursos.add(Integer.parseInt(nextToken));
						} else if (contLineas > 2) {
							listaProcesos.add(Integer.parseInt(nextToken));
						}
						
						
					}
				} else if(nextToken.equals("proceso") == true){
					//listaSolicitudes.add(line);
                                        
                                        while (str_arr.hasMoreTokens()) {
						nextToken = str_arr.nextToken();
						if (cont == 0) {
							
                                                    izquierda.add((nextToken));
							
						} 
                                                
                                                if (cont == 2) {
							
                                                    primero=nextToken;
							
						} 
                                                 if (cont == 3) {
							
                                                    segundo=nextToken;
							
						} 
                                                  if (cont == 4) {
							
                                                    tercero=nextToken;
							
						} 
                                                
                                                
						cont++;
						
					}
                                        
                                        total= primero+" "+segundo+" "+tercero;
                                        derecha.add((total));
				}

				contLineas++;
                                cont=0;
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		
		for (int i = 0; i < listaRecursos.size(); i++) {
			//System.out.println(listaRecursos.get(i));
		}
		
		for (int i = 0; i < listaProcesos.size(); i++) {
			//System.out.println(listaProcesos.get(i));
		}
		//System.out.println("Izquierda: ");
		for (int i = 0; i < izquierda.size(); i++) {
			//System.out.println(izquierda.get(i));
		}
            //    System.out.println("Derecha: ");
		for (int i = 0; i < derecha.size(); i++) {
			//System.out.println(derecha.get(i));
		}
                
                
                //llamada a la clase Algoritmo
                Algoritmo obj = new Algoritmo();
                obj.esSeguro(izquierda.size(),listaRecursos, listaProcesos, izquierda, derecha);
                
	}
}
