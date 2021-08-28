import java.io.*;
import java.net.*;
public class Server
{
	

	public static void main(String[] args)
	{
		new Joc().run();
		ServerSocket serverSocket = null;
		Socket socket = null;
		try
		{
			serverSocket = new ServerSocket(5020);//Cream un server socket care asteapta clientul
			System.out.println("Serverul asteapta... ");
		}
		
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
		
		while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            
            new Threadd(socket).run();
		}
		
	}
}
//special thanks to Ram N Java Tutorial from youtube for his explanations
//a se folosi o conectiune de tip NAT cu masina virtuala