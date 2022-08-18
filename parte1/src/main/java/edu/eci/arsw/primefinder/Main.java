package edu.eci.arsw.primefinder;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) {
		
		PrimeFinderThread pft=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft1=new PrimeFinderThread(10000000, 20000000);
		PrimeFinderThread pft2=new PrimeFinderThread(20000000, 30000000);


		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("hola");
			}
		}, 0, 5000);
		timer.cancel();

//		LocalDateTime date = LocalDateTime.now();
//		int seconds = date.toLocalTime().toSecondOfDay();
//		if (seconds == 5000){
//			System.out.println("hola");
//		}
//		pft.start();
//		pft1.start();
//		pft2.start();
		
	}
	
}
