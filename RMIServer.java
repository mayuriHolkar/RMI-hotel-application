	import java.rmi.*;
	import java.lang.*;
	import java.sql.*;

public interface RMIServer extends Remote
{

    public int sum(int a,int b) throws RemoteException;
	public boolean login(String uname,String pass) throws RemoteException;
    public void disp() throws RemoteException;
    public boolean	insert(String name,String add,String phoneno,String checkin,String checkout,String roomno)throws RemoteException;
	public boolean delete(String roomno)throws RemoteException;
	public boolean	add(String user,String pass)throws RemoteException;
	 public void disp1() throws RemoteException;
	 public boolean	insert_order(String item_name,String quantity)throws RemoteException;
}

