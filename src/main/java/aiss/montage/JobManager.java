package aiss.montage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class JobManager {

	private static Queue<MontageJob> queue;
	
	public static void initialize() 
	{
		queue = new LinkedList<>();
		Runnable runnable = () -> {
		    queueLoop();
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}
	
	private static void queueLoop()
	{
		while (true)
		{
			if (queue.isEmpty())
			{
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) { }
				continue;
			}
			
			MontageJob currentJob = queue.poll();
			// TODO: Do relevant operations on currentJob
		}
	}
	
	public static void enqueueJob(MontageJob job)
	{
		if (queue == null)
			initialize();
		
		queue.add(job);
	}
	
	
	
}
