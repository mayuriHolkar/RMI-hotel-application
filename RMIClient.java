import java.rmi.*;
import java.rmi.registry.*;
import java.lang.*;
import java.util.*;
import java.sql.*;

public class RMIClient
{
    public static void main(String args[])
    {
		boolean f;
         try
        {
            Scanner sc =new Scanner(System.in);
           RMIServer cobj = (RMIServer) Naming.lookup("//localhost/myserver");
	   
           
           int ch;
		   while(true)
			{
			   System.out.println("\n***********Select user***********");
           System.out.print("\n1:Admin\n2:Customer\n3:New user\n");
           ch=sc.nextInt();
           
           switch(ch)
           {
               case 1:
				   System.out.print("\nEnter the Username & password\n");
		           String user=sc.next();
				   String pass=sc.next();
		           f=cobj.login(user,pass);
		           if(f==true)
					   {
							System.out.println("\n***********Select Operation***********");
		                   System.out.print("\n1: customers Details\n2:Delete record\n");
		                   int ch1=sc.nextInt();
			               switch(ch){
								case 1:
									try{
										//cobj.disp();
										RMIServerImpl r=new RMIServerImpl();				
				                        r.disp();
										break;
												
										}catch(Exception e){System.out.print(e);}
								break;
								case 2:				   
									System.out.print("\nEnter the Room no to cancle=");
									String roomno=sc.next();
									f=cobj.delete(roomno);
									if(f==true)
										System.out.println("\nRecord Deleted");
									else
										System.out.println("\nFail to Delete");
									break;
									}	  
							}
							else
								System.out.println("\nWrong username or password\n");
							
							break;
						case 2:

							String name,add,phone,checkin,checkout,rent,roomno;
						System.out.println("\n***********Select Operation***********");
						System.out.println("\n1.Room \n2.food");
						int ch2=sc.nextInt();
						switch(ch2)
						{


							case 1:	System.out.print("\n1:Room booking\n2:delete Booking\n3:Avaliable Rooms\n4:Exit\n");
							int ch3=sc.nextInt();
							   switch(ch3)
								{
								  case 1:
									System.out.println("\n-----------------------------\n\t\tBook Room\n-----------------------------");
									System.out.println("\nEnter the Name=");
												name=sc.next();
									System.out.println("\nEnter the Address=");
											add=sc.next();
									System.out.println("\nEnter the PhoneNo=");
											phone=sc.next();
									System.out.println("\nEnter the Check in=");
											checkin=sc.next();
									System.out.println("\nEnter the check out=");
											checkout=sc.next();
									System.out.println("\nEnter the room no=");
											roomno=sc.next();
									f=cobj.insert(name,add,phone,checkin,checkout,roomno);
										if(f==true)
										System.out.println("\nRecord Inserted");
									else
										System.out.println("\nFail to insert");
									break;
								case 2:
									System.out.print("\nEnter the Room no to cancle");
									roomno=sc.next();
									f=cobj.delete(roomno);
									if(f==true)
										System.out.println("\nRecord Deleted");
									else
										System.out.println("\nFail to Delete");
										break;
								case 3:RMIServerImpl r=new RMIServerImpl();				
										r.disp();
										break;
								}
								break;

							case 2:System.out.print("\n1:menu \n2.place order \n:Exit\n");
								int ch4=sc.nextInt();
								switch(ch4)
								{
									case 1:RMIServerImpl r=new RMIServerImpl();				
											r.disp1();
											break;
									case 2: 
											System.out.print("\n enter Item name:");
										String item_name=sc.next();
										System.out.print("\n no. of quantity:");
										String quantity=sc.next();
										f=cobj.insert_order(item_name,quantity);
										if(f==true)
										System.out.println("\nRecord Inserted");
									else
										System.out.println("\nFail to insert");
									break;
								}
						}
					  
							break;
					case 3:
						System.out.println("\ncreate new admin account ");
						System.out.println("\nTo Enter the Username & Password\n");
						System.out.println("\nEnter the Username=");
							user=sc.next();
						System.out.println("\nEnter the Password=");
							pass=sc.next();
						f=cobj.add(user,pass);
							if(f==true)
								System.out.println("\nRecord Inserted");
							else
								System.out.println("\nFail to insert");
							break;
					case 4:
						System.exit(0);
				  }
        }
		}
        catch(java.net.MalformedURLException me) {}
        catch(RemoteException re) {}
        catch(java.rmi.NotBoundException nb) {}
     }
}