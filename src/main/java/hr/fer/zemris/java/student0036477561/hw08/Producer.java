package hr.fer.zemris.java.student0036477561.hw08;

import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * Class that produces an image representing Mandelbrots fractal.
 * 
 * @author Vilim Staroveški
 *
 */
public class Producer implements IFractalProducer {

	/**
	 * {@link ExecutorService} represents a pool with threads in it.
	 */
	private ExecutorService pool;

	/**
	 * Number of available processors on this computer.
	 */
	private int availableProcessors;

	/**
	 * Creates new {@link Producer}. Sets as many threads in the pool as this
	 * computer has processors.
	 */
	public Producer() {

		availableProcessors = Runtime.getRuntime().availableProcessors();
		pool = Executors.newFixedThreadPool(availableProcessors, new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				Thread newOne = new Thread(r);
				newOne.setDaemon(true);
				return newOne;
			}
		});
	}

	@Override
	public void produce(double reMin, double reMax, double imMin, double imMax,
			int width, int height, long requestNo,
			IFractalResultObserver observer) {

		class Job implements Callable<Integer> {
			
			private short[] data;

			private int yminForThis;

			private int ymaxForThis;

			public Job(int yminForThis, int ymaxForThis, short [] data) {

				this.data = data;
				this.ymaxForThis = ymaxForThis;
				this.yminForThis = yminForThis;
			}

			@Override
			public Integer call() throws Exception {

				MyMandelbrot.calculate(reMin, reMax, imMin, imMax, width,
						height, Newton.ITERATIONS, yminForThis, ymaxForThis,
						this.data);
				return 1;
			}
		}

		System.out.println("Započinjem izračune...");
		List<Future<Integer>> results = new ArrayList<Future<Integer>>();
		
		short[] data = new short[width*height];
		
		int numberOfWorkers = 8 * availableProcessors;
		for (int i = 0; i < numberOfWorkers; i++) {
			Job job = new Job(i * height / numberOfWorkers, 
						((i + 1) * height / numberOfWorkers),
						data);
			results.add(pool.submit(job));
		}
		for (Future<?> worker : results) {
			while (true) {
				try {
					
					worker.get();
					break;
					
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Izračuni gotovi...");
		observer.acceptResult(data, (short) (Newton.polynomial.order() + 1), requestNo);
		System.out.println("Dojava gotova...");
	}

}