
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	static Semaphore semaphore = new Semaphore(2);

	static class MyATMThread extends Thread {

		String name = "";

		MyATMThread(String name) {
			this.name = name;
		}

		public void run() {

			try {

				
				System.out.println(name + " : Verificando permissoes...");
				System.out.println(name + " : permissoes disponiveis: " 
								+ semaphore.availablePermits());
				
				semaphore.acquire();
				System.out.println(name + " : usando uma permissao");

				try {

					for (int i = 1; i <= 5; i++) {

						System.out.println(name + " : executando " + i 
								+ ", permissoes disponiveis : "
								+ semaphore.availablePermits());

						Thread.sleep(1000);

					}

				} finally {

					System.out.println(name + " : liberando permissao...");
					semaphore.release();
					System.out.println(name + " : total de permissoes disponiveis agora: " 
								+ semaphore.availablePermits());

				}

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}

	}

	public static void main(String[] args) {

		System.out.println("Total de permissoes : " 
				+ semaphore.availablePermits());
	
		MyATMThread t1 = new MyATMThread("A");
		t1.start();
		
		MyATMThread t2 = new MyATMThread("B");
		t2.start();

		MyATMThread t3 = new MyATMThread("C");
		t3.start();
		
		MyATMThread t4 = new MyATMThread("D");
		t4.start();
		
		MyATMThread t5 = new MyATMThread("E");
		t5.start();
		
	}
}