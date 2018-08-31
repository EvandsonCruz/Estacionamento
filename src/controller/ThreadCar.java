package controller;

import java.util.concurrent.Semaphore;

public class ThreadCar extends Thread {

	private int idCarro;
	private int distanciaTotal;
	private int distanciaPerc;
	private static int posicaoChegada;
	private static int posicaoSaida;
	private Semaphore semaforo;

	public ThreadCar(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	public void run() {
		carroAndando();
		try {
			semaforo.acquire();
			carroParando();
			carroSaindo();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	public void carroAndando() {
		distanciaTotal = (int) (Math.random() * 1001) + 2000;
		int tempo = 100;
		int deslocamento = 30;
		distanciaPerc = 0;
		while (distanciaPerc < distanciaTotal) {
			distanciaPerc += deslocamento;
			System.out.println("Carro #" + idCarro + " já andou "
					+ distanciaPerc + "m");
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		posicaoChegada++;
		System.out.println("Carro #" + idCarro + " foi o " + posicaoChegada
				+ "o.");
	}

	public void carroParando() {
		System.out.println("Carro #" + idCarro + " parou");
		int tempoParado = (int) (Math.random() * 2001) + 1000;
		try {
			Thread.sleep(tempoParado);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void carroSaindo() {
		posicaoSaida++;
		System.out.println("Carro #" + idCarro + " foi o " + posicaoSaida
				+ "o. a sair");
	}

}
