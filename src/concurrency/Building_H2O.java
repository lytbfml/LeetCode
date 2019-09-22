package concurrency;

import java.util.concurrent.Semaphore;

/**
 * @author Yangxiao Wang on 7/16/2019
 */
public class Building_H2O {
	
	
	class H2O {
		
		Semaphore r1, r2;
		
		public H2O() {
			r1 = new Semaphore(2);
			r2 = new Semaphore(0);
		}
		
		public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
			r1.acquire();
			// releaseHydrogen.run() outputs "H". Do not change or remove this line.
			releaseHydrogen.run();
			if (r1.availablePermits() == 0 && r2.availablePermits() == 0) {
				r2.release();
			}
		}
		
		public void oxygen(Runnable releaseOxygen) throws InterruptedException {
			r2.acquire();
			// releaseOxygen.run() outputs "H". Do not change or remove this line.
			releaseOxygen.run();
			r1.release();
			r1.release();
		}
	}
	
	// I think this solution will print correctly, but might relase O threads in wrong sequence.
	// Example, if threads arrive as O1, O2, H1, H2, H3, H4 we should release O1 together with H1 and H2.
	// Both O1 and O2 are locked at o.acquire(2), o.release(); was called twice (H1 and H2),
	// scheduled can select either O1 or O2 to run I think.
	//
	// The Problem has this part "You must guarantee that all the threads from one molecule bond before
	// any other threads from the next molecule do", which can be read as prohibiting to swap threads
	// between molecules
	class H2O_2 {
		Semaphore h, o;
		public H2O_2() {
			h = new Semaphore(2);
			o = new Semaphore(0);
		}
		
		public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
			h.acquire();
			releaseHydrogen.run();
			o.release();
			
		}
		
		public void oxygen(Runnable releaseOxygen) throws InterruptedException {
			o.acquire(2);
			releaseOxygen.run();
			h.release(2);
		}
	}
}
