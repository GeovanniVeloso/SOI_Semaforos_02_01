package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransa��es extends Thread {

	private int idTransa��o;
	private Semaphore semaforo;

	public ThreadTransa��es(int idTransa��o, Semaphore semaforo) {
		this.idTransa��o = idTransa��o;
		this.semaforo = semaforo;
	}

	public void run() {
		int resto = idTransa��o % 3;

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
		System.out.println("Transa��o #" + idTransa��o + " demorou 0," + random + " segundo para ser concluida.");
	}

	public void Calc02() {
		int random = (int) (Math.random() * 800) + 200;
		try {
			sleep(random);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		System.out.println("Transa��o #" + idTransa��o + " demorou 0," + random + " segundo para ser concluida.");
	}

	public void Calc03() {
		int random = (int) (Math.random() * 500) + 1000;
		try {
			sleep(random);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		System.out.println("Transa��o #" + idTransa��o + " demorou 0," + random + " segundo para ser concluida.");
	}

	public void BD() {
		try {
			semaforo.acquire();
			if (idTransa��o % 3 == 1) {
				try {
					sleep(1000);
					System.out.println(" A transa��o #"+idTransa��o+" foi armazenada no banco de dados");
				} catch (InterruptedException e) {
					System.err.println(e);
				}
			} else {
				try {
					sleep(1500);
					System.out.println(" A transa��o #"+idTransa��o+" foi armazenada no banco de dados");
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
