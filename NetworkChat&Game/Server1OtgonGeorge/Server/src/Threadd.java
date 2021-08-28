
import java.io.*;
import java.net.*;
import java.util.Arrays;



public class Threadd extends Thread {
   
	protected Socket socket;
	public Threadd(Socket clientSocket) {
		
	    this.socket = clientSocket;
	}
	public static int ct=0;
	public static int [] k= new int[5];
	public static int [] q= new int[5];
	public static String y = "ssshhhhhhhhhhh!!!!";
	public static ObjectOutputStream data_output = null;
	public static ObjectInputStream data_input = null;
    public static BufferedReader buffered_reader = null;
	public void run() {
	    try
		{
	    	//initializam variabilele de tip net(ServerSocket) si de tip io(DataInputStream, DataOutputStream, BufferedReader)
			OutputStream outputStream = socket.getOutputStream();
			data_output = new ObjectOutputStream(outputStream);//outputul serverului
			data_input = new ObjectInputStream(socket.getInputStream());//inputul serverului
			buffered_reader = new BufferedReader(new InputStreamReader(System.in));//citeste caracterele de la input
			String client_output = "", client_input = "";
			Mesaj m= new Mesaj("aleluia",k);
			try
			{
				while (!E_D.decriptare(client_output, y).equals("terminat"))//chatul functioneaza pana clientul spune terminat
				{
					ct=0;
				    m = (Mesaj) data_input.readObject();
				    k=m.getCoord();
	        		Joc.panel.repaint();
					client_output = m.getText();//output-ul de la client va fi luat ca si input la server 
					System.out.println("Clientul spune: " + E_D.decriptare(client_output, y));
					//System.out.println(Arrays.toString(k));
					
					
					ct=1;
					//System.out.println(Arrays.toString(Joc.x));
					client_input=buffered_reader.readLine();
					m.setText(E_D.criptare(client_input, y));
					m.setCoord(q);
					m.setCoord(Joc.x);
					data_output.writeObject(m);//output-ul de la server va fi luat ca si input la client
					data_output.flush();
				}
			}
			catch (ClassNotFoundException c) {
		         System.out.println("Mesaj class not found");
			}
			
		}
			
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
	    
	 }
	
	
}

