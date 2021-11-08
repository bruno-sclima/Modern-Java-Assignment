package app;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;


import business.*;
public class App {

	public static void main(String[] args) {
		List<Block> blockchain = new ArrayList<>();
		var block = new Block("first block", "", new Date().getTime());
		for(var i=0;i<5;i++) {
			var miner = new Miners(block,blockchain);
			ExecutorService exe = Executors.newFixedThreadPool(3);
			try {
			
			exe.execute(miner);
			exe.execute(miner);
			exe.execute(miner);
			exe.shutdown();
			
				exe.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				exe.shutdownNow();
				block= miner.newBlock();
			}
		}
		System.out.println("Final result of the blockchain:");
		blockchain.forEach(b ->System.out.println(b.toString()));
		
		
		
	}

	

}

