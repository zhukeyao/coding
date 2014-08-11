package com.keyao.test.concurrent;

import java.lang.String;
import java.lang.Runnable;
import java.util.concurrent.Callable;

/**
 * Basic test example for java threading.
 * 1. Runnable
 * 2. Callable  
 * 3. Basic thread
 * 
 * @author keyao
 *
 */
public class BasicThreading {

	/**
	 * 1. Runnable is an interface.
	 * 2. Need import java.lang.Runnable.
	 * 3. A runnable instances need to Override runnable object.
	 * @author keyao
	 *
	 */
	public class TestRunnable implements Runnable {
	    @Override 
		public void run() {
	    	BasicThreading.this.printThreadInfo();
			System.out.println("Test java runnable!");
		}
	}

	/**
	 * 1. Callable is a interface, but it need need to import java.util.concurrent
	 * 2. Do not define the class as 
	 *    TestCallable<String> implements Callable<String>
	 * @author keyao
	 *
	 */
	public class TestCallable implements Callable<String> {
		@Override
		public String call() throws Exception {
			System.out.println("Test java callable!");
			String ret = "test";
			BasicThreading.this.printThreadInfo();
			return ret;
		}		
	}
	
	/**
	 * 1. We can create a thread object by extend java.lang.Thread
	 * 2. Thread extends java.lang.object and implements Runnable interface
	 * 3. overwrite run method to do some really work
	 * 4. call start method to start the thread. 
	 * @author keyao
	 *
	 */
	public class TestThread extends Thread {
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.format("Starting thread %s\n", name);
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				System.out.format("thread %s is waked up\n", name);
			}
			System.out.println("Thread waked up, reporting ...");
			
			/**
			 * This is an example of how to call outer class's method
			 */
			BasicThreading.this.printThreadInfo();
			
		}
	}
	
	/**
	 * How to call a outter class method from inner class
	 * If we want this method to be called by a inner class method, in inner class method,
	 * do "outclass.this.printThreadInfo()"
	 */
	public void printThreadInfo() {
		Thread currentThread = Thread.currentThread();
		String name = currentThread.getName();
		int priority = currentThread.getPriority();
		StackTraceElement[] stackTraces = currentThread.getStackTrace();
		System.out.format("Thread name = %s\n", name);
		System.out.format("Thread priority\n", priority);
		for (int i=0; i<stackTraces.length; i++) {
			StackTraceElement element = stackTraces[i];
			String stackTraceString = element.toString();
			System.out.println(stackTraceString);
		}
	}
	
	public void startSimpleThreadTest() throws InterruptedException {
	
		/** test direct thread launch **/
		TestThread testThread = new TestThread();
		testThread.start();
		testThread.join();
		
		/** test thread launch a runnable**/
		Thread runnableThread = new Thread(new TestRunnable());
		runnableThread.start();
		runnableThread.join();
		
		/** test executor service, callable and future ***/
	
	}
	
	public static void main(String[] arg) throws Exception {
		
		BasicThreading basicThreadTest = new BasicThreading();
		basicThreadTest.startSimpleThreadTest();
		
		
		
	}
}
