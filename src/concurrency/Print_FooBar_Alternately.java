package concurrency;

import java.util.concurrent.Semaphore;

/**
 * @author Yangxiao Wang on 7/16/2019
 */
public class Print_FooBar_Alternately {
	
	class FooBar {
		private int n;
		Semaphore r1, r2;
		
		public FooBar(int n) {
			this.n = n;
			r1 = new Semaphore(0);
			r2 = new Semaphore(0);
			r1.release();
		}
		
		public void foo(Runnable printFoo) throws InterruptedException {
			
			for (int i = 0; i < n; i++) {
				
				r1.acquire();
				// printFoo.run() outputs "foo". Do not change or remove this line.
				printFoo.run();
				r2.release();
			}
		}
		
		public void bar(Runnable printBar) throws InterruptedException {
			
			for (int i = 0; i < n; i++) {
				
				r2.acquire();
				// printBar.run() outputs "bar". Do not change or remove this line.
				printBar.run();
				r1.release();
			}
		}
	}
	
	class FooBar_alt {
		private int n;
		Semaphore r1, r2;
		
		public FooBar_alt(int n) {
			this.n = n;
			r1 = new Semaphore(1);
			r2 = new Semaphore(0);
		}
		
		public void foo(Runnable printFoo) throws InterruptedException {
			
			for (int i = 0; i < n; i++) {
				r1.acquire();
				// printFoo.run() outputs "foo". Do not change or remove this line.
				printFoo.run();
				r2.release();
			}
		}
		
		public void bar(Runnable printBar) throws InterruptedException {
			
			for (int i = 0; i < n; i++) {
				
				r2.acquire();
				// printBar.run() outputs "bar". Do not change or remove this line.
				printBar.run();
				r1.release();
			}
		}
	}
	
	class FooBar2 {
		private int n;
		volatile boolean callFoo;
		volatile boolean callBar;
		
		public FooBar2(int n) {
			this.n = n;
			callFoo = true;
			callBar = false;
		}
		
		public synchronized void foo(Runnable printFoo) throws InterruptedException {
			
			for (int i = 0; i < n; i++) {
				while (callBar) {
					wait();
				}
				// printFoo.run() outputs "foo". Do not change or remove this line.
				printFoo.run();
				callFoo = false;
				callBar = true;
				notifyAll();
			}
		}
		
		public synchronized void bar(Runnable printBar) throws InterruptedException {
			for (int i = 0; i < n; i++) {
				while (callFoo) {
					wait();
				}
				// printBar.run() outputs "bar". Do not change or remove this line.
				printBar.run();
				callBar = false;
				callFoo = true;
				notifyAll();
			}
		}
	}
}
