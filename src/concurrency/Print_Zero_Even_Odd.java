package concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author Yangxiao Wang on 2019-07-18
 */
public class Print_Zero_Even_Odd {
	
	class ZeroEvenOdd {
		private int n;
		private Semaphore smEven;
		private Semaphore smOdd;
		private Semaphore smZero;
		
		public ZeroEvenOdd(int n) {
			this.n = n;
			smEven = new Semaphore(0);
			smOdd = new Semaphore(0);
			smZero = new Semaphore(1);
		}
		
		// printNumber.accept(x) outputs "x", where x is an integer.
		public void zero(IntConsumer printNumber) throws InterruptedException {
			for (int i = 0; i < n; i++) {
				smZero.acquire();
				printNumber.accept(0);
				if ((i & 1) == 1) {
					smOdd.release();
				} else {
					smEven.release();
				}
			}
		}
		
		public void even(IntConsumer printNumber) throws InterruptedException {
			for (int i = 0; i <= n; i += 2) {
				smEven.acquire();
				printNumber.accept(i);
				smZero.release();
			}
		}
		
		public void odd(IntConsumer printNumber) throws InterruptedException {
			for (int i = 1; i <= n; i += 2) {
				smOdd.acquire();
				printNumber.accept(i);
				smZero.release();
			}
		}
	}
	
	
	class ZeroEvenOdd_wait {
		private int n;
		boolean p0, pe;
		
		public ZeroEvenOdd_wait(int n) {
			this.n = n;
			this.p0 = true;
		}
		
		// printNumber.accept(x) outputs "x", where x is an integer.
		public void zero(IntConsumer printNumber) throws InterruptedException {
			synchronized (this) {
				for (int i = 0; i < n; i++) {
					while (!p0) {
						wait();
					}
					p0 = false;
					printNumber.accept(0);
					notifyAll();
				}
			}
		}
		
		public void even(IntConsumer printNumber) throws InterruptedException {
			synchronized (this) {
				for (int i = 2; i <= n; i += 2) {
					while (!pe || p0) {
						wait();
					}
					pe = false;
					p0 = true;
					printNumber.accept(i);
					notifyAll();
				}
			}
		}
		
		public void odd(IntConsumer printNumber) throws InterruptedException {
			synchronized (this) {
				for (int i = 1; i <= n; i += 2) {
					while (pe || p0) {
						wait();
					}
					pe = true;
					p0 = true;
					printNumber.accept(i);
					notifyAll();
				}
			}
		}
	}
}
