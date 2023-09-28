package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransações extends Thread {

	private int idTransação;
	private Semaphore semaforo;

	public ThreadTransações(int idTransação, Semaphore semaforo) {
		this.idTransação = idTransação;
		this.semaforo = semaforo;
	}

	public void run() {
		int resto = idTransação % 3;

		switch (resto) {
		case 0:
			Calc01();
			BD();
			Calc01();
			BD();
			break;
		case 1:
			Calc02();
			BD();
			Calc02();
			BD();
			break;
		case 2:
			Calc03();
			BD();
			Calc03();
			BD();
			break;

		}
	}

	public void Calc01() {
		int random = (int) (Math.random() * 1000) + 1000;
		try {
			sleep(random);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		System.out.println("Transação #" + idTransação + " demorou 0," + random + " segundo para ser concluida.");
	}

	public void Calc02() {
		int random = (int) (Math.random() * 800) + 200;
		try {
			sleep(random);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		System.out.println("Transação #" + idTransação + " demorou 0," + random + " segundo para ser concluida.");
	}

	public void Calc03() {
		int random = (int) (Math.random() * 500) + 1000;
		try {
			sleep(random);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		System.out.println("Transação #" + idTransação + " demorou 0," + random + " segundo para ser concluida.");
	}

	public void BD() {
		try {
			semaforo.acquire();
			if (idTransação % 3 == 1) {
				try {
					sleep(1000);
					System.out.println(" A transação #"+idTransação+" foi armazenada no banco de dados");
				} catch (InterruptedException e) {
					System.err.println(e);
				}
			} else {
				try {
					sleep(1500);
					System.out.println(" A transação #"+idTransação+" foi armazenada no banco de dados");
				} catch (InterruptedException e) {
					System.err.println(e);
				}
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		} finally {
			semaforo.release();
		}
	}
}
