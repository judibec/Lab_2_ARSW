package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		LinkedList<PrimeFinderThread> primeFinderThreads= new LinkedList<>();
		
		PrimeFinderThread pft=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft1=new PrimeFinderThread(10000000, 20000000);
		PrimeFinderThread pft2=new PrimeFinderThread(20000000, 30000000);
		primeFinderThreads.add(pft);
		primeFinderThreads.add(pft1);
		primeFinderThreads.add(pft2);

		//corre los threads
		for(PrimeFinderThread thread:primeFinderThreads){
			thread.start();
		}

		//empieza a contar 5 seg
		long startTime = System.nanoTime();
		TimeUnit.SECONDS.sleep(5);
		long endTime = System.nanoTime();
		long timeElapse = endTime-startTime;

		//suspende los hilos
		for(PrimeFinderThread thread:primeFinderThreads){
			thread.suspender();
		}

		//pide enter como entrada
		Scanner scanner = new Scanner(System.in);
		System.out.println("Presione enter para continuar");
		String input = scanner.nextLine();

		//reanuda los hilos
		if(Objects.equals(input, "")){
			for(PrimeFinderThread thread:primeFinderThreads){
				thread.reanudar();
			}
		}

//		System.out.println(timeElapse/1000000);

	}
	
}
