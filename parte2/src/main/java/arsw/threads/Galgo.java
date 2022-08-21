package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	private boolean pausado = false;

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			enPausa();
			
			if (paso == carril.size()) {						
				carril.finish();
				synchronized (regl) {
					int ubicacion = regl.getUltimaPosicionAlcanzada();
					regl.setUltimaPosicionAlcanzada(ubicacion + 1);
					System.out.println("El galgo " + this.getName() + " llego en la posicion " + ubicacion);
					if (ubicacion == 1) {
						regl.setGanador(this.getName());
					}
				}
				
			}
		}
	}

	public synchronized void pausar(){
		pausado = true;
	}

	public synchronized void reanudar(){
		pausado = false;
		notifyAll();
	}

	public synchronized void enPausa() {
		while(pausado){
			try {
				wait();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		
		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
