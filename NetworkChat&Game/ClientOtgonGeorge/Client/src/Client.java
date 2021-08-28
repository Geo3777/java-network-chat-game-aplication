
import java.io.*;
import java.net.*;
import java.util.Arrays;



public class Client 
{
	
	public static int ct=0;
	public static int [] k= new int[5];
	public static int [] q= new int[5];

	public static String y = "ssshhhhhhhhhhh!!!!";
	public static ObjectOutputStream data_output = null;
	public static ObjectInputStream data_input = null;
	public static BufferedReader buffered_reader = null;

	public static void main(String[] args)
	{
		new Joc().run();
		//initializam variabilele de tip net(Socket) si de tip io(DataInputStream, DataOutputStream, BufferedReader)
		Socket socket = null;
		
		try
		{
			socket = new Socket("localhost", 5020);//conectam socketul la localhost(versiunea numerica) si la portul stabilit
			OutputStream outputStream = socket.getOutputStream();
			data_output = new ObjectOutputStream(outputStream);//outputul clientului
			data_input = new ObjectInputStream(socket.getInputStream());//inputul clientului
			buffered_reader = new BufferedReader(new InputStreamReader(System.in));//citeste caracterele de la input
			Mesaj m= new Mesaj("aleluia",k);
			String server_input = "", server_output = "";
			try
			{
				while (!E_D.decriptare(server_output, y).equals("terminat"))//pana cand serverul nu spune stop
				{
					ct=0;
					//System.out.println(Arrays.toString(Joc.x));
					server_input=buffered_reader.readLine();//output-ul de la client va fi luat ca si input la server 
					m.setText(E_D.criptare(server_input,y));
					m.setCoord(q);
					m.setCoord(Joc.x);
					data_output.writeObject(m);//output-ul de la client va fi luat ca si input la s
					data_output.flush();
					
					ct=1;
				    m = (Mesaj) data_input.readObject();
				    k=m.getCoord();
				    //System.out.println(Arrays.toString(k));
	        		Joc.panel.repaint();
					server_output = m.getText();//output-ul de la server va fi luat ca si input la client
					System.out.println("Serverul spune: " + E_D.decriptare(server_output, y));
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