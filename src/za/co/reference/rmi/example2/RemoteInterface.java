package za.co.reference.rmi.example2;

import java.rmi.Remote;

public interface RemoteInterface extends Remote{
	
	public int add(int x,int y) throws Exception;
	
} 