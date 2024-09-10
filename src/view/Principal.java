package view;

import java.util.concurrent.Semaphore;
import banco.ControllerBanco;

public class Principal {
	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		for(int x = 0; x < 21; x++) {
			ControllerBanco banco = new ControllerBanco(x, semaforo);
			banco.start();
		}
	}
}
