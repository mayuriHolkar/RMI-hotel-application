import java.net.MalformedURLException;
import java.rmi.server.*;
import java.rmi.*;
import java.sql.*;
import java.rmi.registry.*;
import java.lang.*;


public class RMIServerImpl extends UnicastRemoteObject implements RMIServer
{
	
	Connection con;
	Statement stmt;
	ResultSet rs;
    public RMIServerImpl() throws RemoteException
    {        super();     }
    public int sum(int a,int b) throws RemoteException
    {   return(a+b)    ;    }

	public boolean login(String uname,String pass) throws RemoteException
	{
		String u=null,p=null;
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:db_hotel1","","");
		
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT* FROM tbl_login where username='"+uname+"' AND password='"+pass+"'");
			while (rs.next())
			{
				u=rs.getString("username");
				p=rs.getString("password");
			}
			if(u.equals(uname) && p.equals(pass))				
					return true;				
			else				
					return false;					
		}catch(Exception e){System.out.println(e);}
			return false;
	}
//display customer details
	  public void disp() throws RemoteException
        {
            try{
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("jdbc:odbc:db_hotel1","","");
		

			
			stmt = con.createStatement();
                        rs=stmt.executeQuery("SELECT * FROM tbl_cust");//System.out.print("into fun");
						 while(rs.next())
							{
							   System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7));
							}
            }catch(Exception e){}
           // return rs;
        }

//display food menu
 public void disp1() throws RemoteException
        {
            try{
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("jdbc:odbc:db_hotel1","","");
		

			
			stmt = con.createStatement();
                        rs=stmt.executeQuery("SELECT * FROM tbl_menu");//System.out.print("into fun");
						 while(rs.next())
							{
							   System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
							}
            }catch(Exception e){}
           // return rs;
        }



//insert customer details:-
	public boolean	insert(String name,String add,String phone,String checkin,String checkout,String roomno)throws RemoteException{
			 try{

                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("jdbc:odbc:db_hotel1","","");
				stmt = con.createStatement();
				String name1,add1,phone1,checkin1,checkout1,rent1,roomno1;
				name1=name;
				add1=add;
				phone1=phone;
				checkin1=checkin;
				checkout1=checkout;
				roomno1=roomno;
								System.out.println(name1+add1+phone1+checkin1+checkout1+roomno1);
                stmt.executeQuery("INSERT INTO cust (name,add,phone,checkin,checkout,roomno) VALUES('"+name1+"','"+add1+"','"+phone1+"','"+checkin1+"','"+checkout1+"','"+roomno1+"')");
				return false;
            }catch(Exception e){System.out.println(e);return true;}
	}


//insert order:-
public boolean	insert_order(String item_name,String quantity)throws RemoteException
	{
			 try{

                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("jdbc:odbc:db_hotel1","","");
				stmt = con.createStatement();
				String item_name1,quantity1 ;
				item_name1=item_name;
				quantity1=quantity;
								System.out.println(item_name+quantity);
                stmt.executeQuery ("INSERT INTO tbl_order(item_name,quantity) VALUES('"+item_name+"','"+quantity+"')");
				return false;
            }catch(Exception e){System.out.println(e);return true;}
	}

//create new admin username and password:-
public boolean	add(String user,String pass)throws RemoteException
	{
			 try{

                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("jdbc:odbc:db_hotel1","","");
				stmt = con.createStatement();
				String user1,pass1;
				user1=user;
				pass1=pass;
                stmt.executeQuery("INSERT INTO login (username,password) VALUES('"+user1+"','"+pass1+"')");
				return false;
            }catch(Exception e){return true;}
	}

	//Delete record :-
	public boolean delete(String roomno)throws RemoteException
	{
		 try{
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("jdbc:odbc:db_hotel1","","");
				stmt = con.createStatement();
				String roomno1;
				roomno1=roomno;
		//						System.out.println(name1+add1+phone1+checkin1+checkout1+roomno1);
                stmt.executeQuery("DELETE FROM cust WHERE roomno='"+roomno1+"'");
				return false;
            }catch(Exception e){return true;}
	}
    public static void main(String args[]){
        try
        {
            RMIServerImpl sObj = new RMIServerImpl();
			
            Naming.rebind("myserver", sObj);	
			//sObj.disp();
			/*ResultSet rs1=sObj.disp();
						   while(rs1.next())
							{
							   //System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getString(4)+"\t"+rs1.getDate(5)+"\t"+rs1.getDate(6)+"\t"+rs1.getString(7));
							   
							}*/
            System.out.println("Object Registered...");
			 
						   
        }
        catch(java.net.MalformedURLException me){}
        catch(RemoteException re){}
		catch(Exception e){}
    }
}
