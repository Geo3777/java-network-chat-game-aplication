import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;  
import java.io.*;
import java.awt.image.BufferedImage;
public class Joc extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static BufferedImage image;
	public Joc()
	{
		try {                
	          image = ImageIO.read(new File("src/jocc.png"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
	}
	public static Graphics2D g2;
	public static Joc panel = new Joc();
	public static int [] x= new int[5];
	public static int ok=0;
	public static int a=0;;
	public static int [] y= new int[5];
	public Mesaj m= new Mesaj("aleluia",x);
	@Override
	public void paintComponent(Graphics g) { 
		 g2 = (Graphics2D) g;
		if(a==1)
		{
			g2.drawImage(image, 120, 50, this); 
			a++;
		}
		
		if(Client.ct==0)
			paintComponent1(g2);
		else
			paintComponent2(g2);
		
	}
	public void paintComponent1(Graphics2D g2) { 
		 	
		if(ok>1)
		{
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.blue);
		    g2.drawLine(x[0], x[1], x[2], x[3]);
		    ok=0;
		}
	}
public void paintComponent2(Graphics2D g2) { 
	
	g2.setStroke(new BasicStroke(5));
	g2.setColor(Color.red);
    g2.drawLine(Client.k[0], Client.k[1], Client.k[2], Client.k[3]);
	}
	public void run()
	{
		
		JFrame frame = new JFrame("Joc Client");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(700, 500);
        //Joc panel = new Joc();
        
        frame.add(panel);
        
        JLabel text1 = new JLabel();
        text1.setText("Reguli joc:Primul jucator care creaza un triunghi pierde, a se desena doar pe linile negre.");
        text1.setLocation(100, 100);
        JLabel text2 = new JLabel();
        text2.setText("Pentru a a desena o linie trebuie dat click pe ambele capete ale liniei dorite");
        text2.setLocation(0, 30);
        Button b=new Button("Click pentru a incepe jocul");  
        b.setBounds(50,100,60,30); 
        
        panel.add(b);
        panel.add(text1);
        panel.add(text2);
   
        frame.setVisible(true);
        

        panel.addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent e) {
        		
        		if(ok==0)
        		{
        			x[0]=e.getX();
            		x[1]=e.getY();
            		ok++;
            	    a++;
            		
        		}
        		else
        		{
        			x[2]=e.getX();
            		x[3]=e.getY();
            		ok++;
            		a++;
            		
        		}
        		if(ok>1)
        		{
        			m.setCoord(x);
            		panel.revalidate();
            		panel.repaint();
        		}
        	}
        });
        b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    a++; 
                    panel.revalidate();
            		panel.repaint();
                }  
            });  
    }
	
}


