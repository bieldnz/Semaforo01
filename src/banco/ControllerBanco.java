package banco;

import java.util.concurrent.Semaphore;

public class ControllerBanco extends Thread{
	
	private int idThread;
	private Semaphore semaforo;
	
	public ControllerBanco(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		if(idThread%3 == 1) {
			calculo(((int)(Math.random()*801)+200));
			transacao((int)(Math.random()*1001));
			calculo((int)(Math.random()*801)+200);
			transacao((int)(Math.random()*1001));
		}else if(idThread%3==2) {
			calculo(((int)(Math.random()*1001)+500));
			transacao((int)(Math.random()*1501));
			calculo(((int)(Math.random()*1001)+500));
			transacao((int)(Math.random()*1501));
			calculo(((int)(Math.random()*1001)+500));
			transacao((int)(Math.random()*1501));
		}else {
			calculo(((int)(Math.random()*1001)+1000));
			transacao((int)(Math.random()*1501));
			calculo(((int)(Math.random()*1001)+1000));
			transacao((int)(Math.random()*1501));
			calculo(((int)(Math.random()*1001)+1000));
			transacao((int)(Math.random()*1501));
		}
	}
	
	private void calculo(int tempo) {
		System.out.println("#"+idThread+" esta realizando o calculo");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void transacao(int tempo) {
		System.out.println("#"+idThread+" esta realizando uma transacao");
		try {
			sleep(tempo);
			semaforo.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
	
}