package business;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
    private boolean added;
 
 
    public String calculateBlockHash() {
        var dataToHash = previousHash 
          + Long.toString(timeStamp) 
          + Integer.toString(nonce) 
          + data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
        	Logger logger = Logger.getLogger(Block.class.getName());
			logger.log(Level.SEVERE, ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }
    
    public String mineBlock(int prefix) {
    	var res = "0";
        var prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString) && !this.isAdded()) {
            this.nonce++;
            this.setHash(calculateBlockHash());
        }
        if(!this.isAdded()) {
        	this.setAdded(true);
        	res = this.hash;
        } 
        return res;
    }
    
    public Block(String data, String previousHash, long timeStamp) {
        this.setData(data);
        this.setPreviousHash(previousHash);
        this.setTimeStamp(timeStamp);
        this.setHash(calculateBlockHash());
        this.setAdded(false);
    }

	public String getHash() {
		return hash;
	}

	public boolean isAdded() {
		return added;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	@Override
	public String toString() {
		return "Block [hash=" + hash + ", previousHash=" + previousHash + ", data=" + data + ", timeStamp=" + timeStamp
				+ ", nonce=" + nonce + ", added=" + added + "]";
	}
	
    
}
