
import java.io.Serializable;

public class Mesaj implements Serializable{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 26042021L;
		private String text;
		public int [] x= new int[5];
	    public Mesaj(String text, int[]x) {
	        this.text = text;
	        this.x=x;
	    }
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public int[] getCoord() {
			return x;
		}
		public void setCoord(int[] x) {
			this.x=x;
		}
		
	    
	}