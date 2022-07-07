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
	static ArrayList<String> listaSolicitudes = new ArrayList<String>();
	int contLineas = 0;
	String nextToken;

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
				} else {
					listaSolicitudes.add(line);
				}

				contLineas++;

			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		System.out.println("Lista Recursos: ");
		for (int i = 0; i < listaRecursos.size(); i++) {
			System.out.println(listaRecursos.get(i));
		}
		System.out.println("Lista Procesos: ");
		for (int i = 0; i < listaProcesos.size(); i++) {
			System.out.println(listaProcesos.get(i));
		}
		System.out.println("Lista Solicitudes: ");
		for (int i = 0; i < listaSolicitudes.size(); i++) {
			System.out.println(listaSolicitudes.get(i));
		}
	}
}
