package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yangxiao Wang on 7/16/2019
 */
public class Print_in_Order {
	
	
	class Foo {
		
		Semaphore r2, r3;
		
		public Foo() {
			r2 = new Semaphore(0);
			r3 = new Semaphore(0);
		}
		
		public void first(Runnable printFirst) throws InterruptedException {
			
			// printFirst.run() outputs "first". Do not change or remove this line.
			printFirst.run();
			r2.release();
		}
		
		public void second(Runnable printSecond) throws InterruptedException {
			
			r2.acquire();
			// printSecond.run() outputs "second". Do not change or remove this line.
			printSecond.run();
			r3.release();
		}
		
		public void third(Runnable printThird) throws InterruptedException {
			
			r3.acquire();
			// printThird.run() outputs "third". Do not change or remove this line.
			printThird.run();
		}
	}
	
	class Foo2 {
		AtomicInteger lock;
		
		public Foo2() {
			lock = new AtomicInteger(1);
		}
		
		public void first(Runnable printFirst) throws InterruptedException {
			
			// printFirst.run() outputs "first". Do not change or remove this line.
			printFirst.run();
			lock.incrementAndGet();
		}
		
		public void second(Runnable printSecond) throws InterruptedException {
			while (true) {
				if (lock.get() > 2) break;
			}
			
			// printSecond.run() outputs "second". Do not change or remove this line.
			printSecond.run();
			lock.incrementAndGet();
		}
		
		public void third(Runnable printThird) throws InterruptedException {
			
			while (true) {
				if (lock.get() > 3) break;
			}
			// printThird.run() outputs "third". Do not change or remove this line.
			printThird.run();
		}
	}
	
	class Foo3 {
		// needless
		// CountDownLatch firstCD = new CountDownLatch(1);
		CountDownLatch secondCD = new CountDownLatch(1);
		CountDownLatch thirdCD = new CountDownLatch(1);
		
		public Foo3() {
			// same as above
			// firstCD.countDown();
		}
		
		public void first(Runnable printFirst) throws InterruptedException {
			// firstCD.await();
			// printFirst.run() outputs "first". Do not change or remove this line.
			printFirst.run();
			secondCD.countDown();
		}
		
		public void second(Runnable printSecond) throws InterruptedException {
			secondCD.await();
			// printSecond.run() outputs "second". Do not change or remove this line.
			printSecond.run();
			thirdCD.countDown();
		}
		
		public void third(Runnable printThird) throws InterruptedException {
			thirdCD.await();
			// printThird.run() outputs "third". Do not change or remove this line.
			printThird.run();
		}
	}
	
	class Foo4 {
		
		
		public Foo4() {
		
		}
		
		public void first(Runnable printFirst) throws InterruptedException {
			// firstCD.await();
			// printFirst.run() outputs "first". Do not change or remove this line.
			printFirst.run();
		}
		
		public void second(Runnable printSecond) throws InterruptedException {
			// printSecond.run() outputs "second". Do not change or remove this line.
			printSecond.run();
		}
		
		public void third(Runnable printThird) throws InterruptedException {
			// printThird.run() outputs "third". Do not change or remove this line.
			printThird.run();
		}
	}
	
}
