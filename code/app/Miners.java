package app;
import business.*;
import java.util.Date;
import java.util.List;
public class Miners implements Runnable{
	private Block b;
	private List<Block> blockchain;
	
	public List<Block> getBlockchain() {
		return blockchain;
	}
	public void setBlockchain(List<Block> blockchain) {
		this.blockchain = blockchain;
	}
	public Block getB() {
		return b;
	}
	public void setB(Block b) {
		this.b = b;
	}
	public Block newBlock() {
		String hash ="00";
		if(!blockchain.isEmpty()) {
			hash = blockchain.get(blockchain.size()-1).getHash();
		}
		var block = new Block("data",hash,new Date().getTime());
		return block;
	}
	@Override
	public void run() {
		System.out.println("Begining: "+Thread.currentThread().getName());
		var phash =this.b.mineBlock(4);
		if(!phash.equals("0")) {
			System.out.println("Mining block: "+Thread.currentThread().getName()+"/t"+ b.toString());
			this.blockchain.add(b);
			
			System.out.println("Ending: "+Thread.currentThread().getName());
		}else {
			System.out.println("Ending: "+Thread.currentThread().getName());
		}

	}
	public Miners(Block b,List<Block> blockchain) {
		this.setB(b);
		this.setBlockchain(blockchain);
	}

}
