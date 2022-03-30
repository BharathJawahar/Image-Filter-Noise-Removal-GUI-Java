import java.util.Scanner;
import java.io.*; 
import java.io.IOException; 
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.sql.*;


class ImageProcessing implements ItemListener, ActionListener{
	

	DataInputStream cin = new DataInputStream(System.in);
	Scanner Cin = new Scanner(System.in);

	BufferedImage image;
	File d;

	static JFrame f;
	static JPanel p;
	static Choice c;
	static Button b;

	ImageProcessing()throws Exception{
		
	}

	public static void main(String args[])throws Exception{ 

		ImageProcessing thisClass = new ImageProcessing();

		f = new JFrame("Image Processing");
		p = new JPanel();
		c = new Choice();
		b = new Button("Next");
		b.setFont(new Font("Lucida",Font.PLAIN,24));
	
		Label h = new Label("Digital Image Processing");		
		h.setFont(new Font("Lucida",Font.BOLD,50));
		
		c.add("Select\t\t\t");
		c.add("Filter");
		c.add("Edit");
		c.setFont(new Font("Lucida",Font.PLAIN,24));
		
		c.addItemListener(thisClass);
		b.addActionListener(thisClass);

		p.add(h);
		p.add(c);
		p.add(b);
		
		f.add(p); 
		f.show(); 
		f.setSize(700, 300);

	}

	public void itemStateChanged(ItemEvent e){ 
	} 

	public void actionPerformed(ActionEvent e){
		try{
			if(c.getSelectedIndex()==1){
				Filter filter = new Filter();
				filter.disp();
			}
			else if(c.getSelectedIndex()==2){
				Edit edit = new Edit();
				edit.disp();
			}
		}
		catch(Exception E){
			System.out.println("Warning!!\n"+E);
		}
	} 

}






class Filter implements ItemListener, ActionListener{

	DataInputStream cin = new DataInputStream(System.in);
	Scanner Cin = new Scanner(System.in);
	BufferedImage image;
	File d;

	static JFrame f1;
	static JPanel p1;
	static JLabel sample;
	static Choice c1;
	static Button b1;
	static TextField text;

	Filter()throws Exception{
		
		BufferedImage image = null;
		File d= null;
		d=new File("Inp.jpg");
		image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		image = ImageIO.read(d);
	}


	public static void disp()throws Exception{ 

		Filter thisClass = new Filter();

		f1 = new JFrame("Filter");
		p1 = new JPanel();
		c1 = new Choice();
		b1 = new Button("OK");
		b1.setFont(new Font("Lucida",Font.PLAIN,24));
		sample = new JLabel();
		text = new TextField("Inp",15);
		text.setFont(new Font("Lucida",Font.PLAIN,24));
		Label h1 = new Label("Filter");
		h1.setFont(new Font("Lucida",Font.BOLD,50));
		
		c1.add("Plain");
		c1.add("Greyscale");
		c1.add("Negative");
		c1.add("Sepia");
		c1.add("Reddish");
		c1.add("Bluish");
		c1.add("Greenish");
		c1.add("Black and White");
		c1.add("Remove Noise");
		c1.setFont(new Font("Lucida",Font.PLAIN,24));
		sample.setIcon(new ImageIcon("Inp.jpg"));
		//sample.setPreferredSize(new Dimension(50, 75)); 

		p1.add(h1);
		p1.add(c1);
		p1.add(text);
		p1.add(b1);
		p1.add(sample);
		
		c1.addItemListener(thisClass);
		b1.addActionListener(thisClass);
		f1.add(p1);
		f1.show();
		f1.setSize(500,700); 	
		
		
	}


	public void itemStateChanged(ItemEvent e) 
	{
		try{
			d=new File(text.getText()+".jpg");
			image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(d);
			
			switch (c1.getSelectedIndex())
			{
				
				case 0 : File outputImage = new File(text.getText()+"0.jpg");
					 ImageIO.write(image, "jpg", outputImage);
					 System.out.println("Created Plain Image");
					 break;
				case 1 : greyscale(image);
					 break;
				case 2 : negative(image);
					 break;
				case 3 : sepia(image);
					 break;
				case 4 : reddish(image);
					 break;
				case 5 : bluish(image);
					 break;
				case 6 : greenish(image);
					 break;
				case 7 : blackwhite(image);
					 break;
				case 8 : noise(image);
					 break;
			}

			sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
		}
		catch(Exception E){
			
		}
	} 

	public void actionPerformed(ActionEvent e) 
	{
		try{
			d=new File(text.getText()+".jpg");
			image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(d);
			
			switch (c1.getSelectedIndex())
			{
				case 0 : File outputImage = new File(text.getText()+"0.jpg");
					 ImageIO.write(image, "jpg", outputImage);
					 System.out.println("Created Plain Image");
				case 1 : greyscale(image);
					 break;
				case 2 : negative(image);
					 break;
				case 3 : sepia(image);
					 break;
				case 4 : reddish(image);
					 break;
				case 5 : bluish(image);
					 break;
				case 6 : greenish(image);
					 break;
				case 7 : blackwhite(image);
					 break;
				case 8 : noise(image);
					 break;
			}
			sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
		
		sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
	}
		catch(Exception E){
			System.out.println(E);
		}
	}

	void greyscale(BufferedImage image)throws IOException{
		
		int width = image.getWidth(); 
	        int height = image.getHeight();
		for (int y = 0; y < height; y++) 
        	{ 
            		for (int x = 0; x < width; x++) 
            		{ 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int r = (p>>16)&0xff; 
				int g = (p>>8)&0xff; 
				int b = p&0xff; 
		  
				// calculate average 
				int avg = (r+g+b)/3; 
		  
				// replace RGB value with avg 
				p = (a<<24) | (avg<<16) | (avg<<8) | avg; 
		  
				image.setRGB(x, y, p); 
			} 
		}

		File outputImage = new File(text.getText()+"1.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created GreyScale Image");
		sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
	}
	
	void blackwhite(BufferedImage image)throws Exception{
		
		int width = image.getWidth(); 
	        int height = image.getHeight();
		for (int y = 0; y < height; y++) { 
            		for (int x = 0; x < width; x++) { 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int r = (p>>16)&0xff; 
				int g = (p>>8)&0xff; 
				int b = p&0xff; 
		 
				int avg = (r+g+b)/3; 
		  		
				if(avg>=128)		avg=255;
				else			avg=0;
				
				p = (avg<<24) | (avg<<16) | (avg<<8) | avg; 
		  
				image.setRGB(x, y, p); 
            		} 
		}

		File outputImage = new File(text.getText()+"7.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Black & White Image");
		sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
	}

	void noise(BufferedImage image)throws Exception{
		
		int width = image.getWidth(); 
		int height = image.getHeight();
		for (int y = 1; y < height-1; y++) { 
            		for (int x = 1; x < width-1; x++) { 
				
				int p1 = image.getRGB(x,y); 
		  		
				int a1 = (p1>>24)&0xff; 
				int r1 = (p1>>16)&0xff; 
				int g1 = (p1>>8)&0xff; 
				int b1 = p1&0xff; 
		  
				int p[] = new int[10];
				int a[] = new int[10];
				int r[] = new int[10];
				int g[] = new int[10];
				int b[] = new int[10];
	
				a[9]=a1;
				r[9]=r1;
				g[9]=g1;
				b[9]=b1;
				
				//if(a==0 || a==255){

				p[1] = image.getRGB(x-1,y-1);
				p[2] = image.getRGB(x,y-1);
				p[3] = image.getRGB(x+1,y-1);
				p[4] = image.getRGB(x-1,y);
				p[5] = image.getRGB(x+1,y);
				p[6] = image.getRGB(x-1,y+1);
				p[7] = image.getRGB(x,y+1);
				p[8] = image.getRGB(x+1,y+1);

				a[1] = (p[1]>>24)&0xff; 
				a[2] = (p[2]>>24)&0xff; 
				a[3] = (p[3]>>24)&0xff; 
				a[4] = (p[4]>>24)&0xff; 
				a[5] = (p[5]>>24)&0xff; 
				a[6] = (p[6]>>24)&0xff; 
				a[7] = (p[7]>>24)&0xff; 
				a[8] = (p[8]>>24)&0xff; 

				r[1] = (p[1]>>16)&0xff; 
				r[2] = (p[2]>>16)&0xff; 
				r[3] = (p[3]>>16)&0xff; 
				r[4] = (p[4]>>16)&0xff; 
				r[5] = (p[5]>>16)&0xff; 
				r[6] = (p[6]>>16)&0xff; 
				r[7] = (p[7]>>16)&0xff; 
				r[8] = (p[8]>>16)&0xff; 


				g[1] = (p[1]>>8)&0xff; 
				g[2] = (p[2]>>8)&0xff; 
				g[3] = (p[3]>>8)&0xff; 
				g[4] = (p[4]>>8)&0xff; 
				g[5] = (p[5]>>8)&0xff; 
				g[6] = (p[6]>>8)&0xff; 
				g[7] = (p[7]>>8)&0xff; 
				g[8] = (p[8]>>8)&0xff; 

				b[1] = (p[1])&0xff; 
				b[2] = (p[2])&0xff; 
				b[3] = (p[3])&0xff; 
				b[4] = (p[4])&0xff; 
				b[5] = (p[5])&0xff; 
				b[6] = (p[6])&0xff; 
				b[7] = (p[7])&0xff; 
				b[8] = (p[8])&0xff; 

				for(int k = 1; k< 10;k++){
					for( int l = k+1; l<10; l++){
						if(p[k]>p[l]){
							int t = p[k];
							p[k] = p[l];
							p[l] = t;
						}
						if(a[k]>a[l]){
							int t = a[k];
							a[k] = a[l];
							a[l] = t;
						}
						if(r[k]>r[l]){
							int t = r[k];
							r[k] = r[l];
							r[l] = t;
						}
						if(g[k]>g[l]){
							int t = g[k];
							g[k] = g[l];
							g[l] = t;
						}

						if(b[k]>b[l]){
							int t = b[k];
							b[k] = b[l];
							b[l] = t;
						}
					}
				}
				// calculate average 
				//int pp = (p1+p2+p3+p4+p5+p6+p7+p8)/8; 
		  		//int aa = (a1+a2+a3+a4+a5+a6+a7+a8)/8;
				//int rr = (r1+r2+r3+r4+r5+r6+r7+r8)/8;
				//int gg = (g1+g2+g3+g4+g5+g6+g7+g8)/8;
				//int bb = (b1+b2+b3+b4+b5+b6+b7+b8)/8;
				// replace RGB value with avg 
				int pp = (a[5]<<24) | (r[5]<<16) | (g[5]<<8) | b[5];
				image.setRGB(x, y, pp);
				 
            		} 
		}

		File outputImage = new File(text.getText()+"8.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Noise removed Image");
		sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
	}

	void negative(BufferedImage image)throws IOException{
		
		int width = image.getWidth(); 
	        int height = image.getHeight();
		for (int y = 0; y < height; y++) 
        	{ 
            		for (int x = 0; x < width; x++) 
            		{ 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int r = (p>>16)&0xff; 
				int g = (p>>8)&0xff; 
				int b = p&0xff; 
		  
				r = 255 - r; 
                		g = 255 - g; 
               		 	b = 255 - b; 
		  
				// replace RGB value with avg 
				p = (a<<24) | (r<<16) | (g<<8) | b; 
		  
				image.setRGB(x, y, p); 
            		} 
		}

		File outputImage = new File(text.getText()+"2.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Negative Image");
		sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
	}

	void sepia(BufferedImage image)throws IOException{
		
		int width = image.getWidth(); 
	        int height = image.getHeight();
		for (int y = 0; y < height; y++) 
        	{ 
            		for (int x = 0; x < width; x++) 
            		{ 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int r = (p>>16)&0xff; 
				int g = (p>>8)&0xff; 
				int b = p&0xff; 
		  
				int newRed = (int)(0.393*r + 0.769*g + 0.189*b); 
				int newGreen = (int)(0.349*r + 0.686*g + 0.168*b); 
				int newBlue = (int)(0.272*r + 0.534*g + 0.131*b); 

		     	        if (newRed > 255) 
					r = 255; 
				else
	                    		r = newRed; 
	  
	                	if (newGreen > 255) 
	                    		g = 255; 
	               	 	else
	                    		g = newGreen; 
	  
	                	if (newBlue > 255) 
	                    		b = 255; 
	               	 	else
	                    		b = newBlue; 

        	        	p = (a<<24) | (r<<16) | (g<<8) | b;  
		  
				image.setRGB(x, y, p); 
            		} 
		}

		File outputImage = new File(text.getText()+"3.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Sepia Image");
		sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
	}

	void reddish(BufferedImage image)throws IOException{
		
		int width = image.getWidth(); 
	        int height = image.getHeight();
		for (int y = 0; y < height; y++) 
        	{ 
            		for (int x = 0; x < width; x++) 
            		{ 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int r = (p>>16)&0xff; 
				
				p = (a<<24) | (r<<16) | (0<<8) | 0; 
		  
				image.setRGB(x, y, p); 
            		} 
		}

		File outputImage = new File(text.getText()+"4.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Reddish Image");
		sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
	}

	void bluish(BufferedImage image)throws IOException{
		
		int width = image.getWidth(); 
	        int height = image.getHeight();
		for (int y = 0; y < height; y++) 
        	{ 
            		for (int x = 0; x < width; x++) 
            		{ 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int b = p&0xff; 
				
				p = (a<<24) | (0<<16) | (0<<8) | b; 
		  
				image.setRGB(x, y, p); 
            		} 
		}

		File outputImage = new File(text.getText()+"5.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Bluish Image");
		sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
	}

	void greenish(BufferedImage image)throws IOException{
		
		int width = image.getWidth(); 
	        int height = image.getHeight();
		for (int y = 0; y < height; y++) 
        	{ 
            		for (int x = 0; x < width; x++) 
            		{ 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int g = (p>>8)&0xff; 
				
				p = (a<<24) | (0<<16) | (g<<8) | 0; 
		  
				image.setRGB(x, y, p); 
            		} 
		}

		File outputImage = new File(text.getText()+"6.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Greenish Image");
		sample.setIcon(new ImageIcon(text.getText()+c1.getSelectedIndex()+".jpg"));
	}
} 



class Edit implements ItemListener, ActionListener{
	

	DataInputStream cin = new DataInputStream(System.in);
	Scanner Cin = new Scanner(System.in);

	BufferedImage image;
	File d;

	JFrame f2;
	TextField text, X1,Y1,X2,Y2;
	JPanel p2;
	Choice c2,cflip;
	Button b2,bcrop,bflip,brotate;
	JLabel sample;
	int x1,y1,x2,y2;
	

	public void disp()throws Exception{ 
	f2 = new JFrame("Edit");
		p2 = new JPanel();
		c2 = new Choice();
		b2 = new Button("OK");
		b2.setFont(new Font("Lucida",Font.PLAIN,24));
		text = new TextField("Inp",15);
		text.setFont(new Font("Ludcida",Font.PLAIN,24));
		Label h2 = new Label("Edit");
		h2.setFont(new Font("Lucida",Font.BOLD,50));
		
		c2.add("Select");
		c2.add("Crop");
		c2.add("Flip");
		//c2.add("Rotate");
		c2.setFont(new Font("Lucida",Font.PLAIN,24));
		
		c2.addItemListener(this);
		b2.addActionListener(this);
		
		p2.add(h2);
		p2.add(c2);
		p2.add(b2);
		p2.add(text);
		
		f2.add(p2); 
		f2.show(); 
		f2.setSize(300, 200);
	
	}


	public void itemStateChanged(ItemEvent e) 
	{
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==b2){
			try{
				switch (c2.getSelectedIndex()){
					case 1 : crop();
						 //System.out.println("Croped");
						 break;
					case 2 : flip();
						 //System.out.println("Fliped");
						 break;
					case 3 : rotate();
						 //System.out.println("Rotate");
						 break;
				}
			}
			catch(Exception E){
				System.out.print(E);
			}
		}
		if(e.getSource()==bcrop){
			try{
				x1 = Integer.parseInt(X1.getText());
				y1 = Integer.parseInt(Y1.getText());
				x2 = Integer.parseInt(X2.getText());
				y2 = Integer.parseInt(Y2.getText());
				System.out.println(x1);
				System.out.println(y1);
				System.out.println(x2);
				System.out.println(y2);

				BufferedImage image = null;
				File d= null;
				d=new File(text.getText()+".jpg");
				image = ImageIO.read(d);
				// 	image = new BufferedImage(x2,y2,BufferedImage.TYPE_INT_ARGB);				
				int width = image.getWidth(); 
			        int height = image.getHeight();

				BufferedImage image1 = new BufferedImage(x2,y2,BufferedImage.TYPE_INT_ARGB);
				for (int y = 0; y < (y2-y1); y++) 
        			{ 
		            		for (int x = 0; x < (x2-x1); x++) 
            				{ 
						int p = image.getRGB(x+x1,y+y1);
						image.setRGB(x, y, p); 
            				} 
				}

				for (int y = y2;y< height;y++) 
        			{ 
		            		for (int x=0;x<width;x++) 
            				{ 
						//int p = image.getRGB(x+x1,y+y1);
						image.setRGB(x, y, 0); 
            				} 
				}

				for (int y = 0;y< height;y++) 
        			{ 
		            		for (int x=x2;x<width;x++) 
            				{ 
						//int p = image.getRGB(x+x1,y+y1);
						image.setRGB(x, y, 0); 
            				} 
				}
				File outputImage = new File(text.getText()+"Crop.jpg");
				ImageIO.write(image, "jpg", outputImage);
				
				System.out.println("Writing complete.");
				
			}
			catch(Exception E){
				System.out.print(E);
			}
			
			
		}
		if(e.getSource()==bflip){
			if(cflip.getSelectedIndex()==1){
				try{
					BufferedImage image = null;
					File d= null;
					d=new File(text.getText()+".jpg");
					image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(d);
	
					int width = image.getWidth(); 
			        	int height = image.getHeight();
					for (int y = 0; y < height; y++) { 	
			            		for (int x = 0; x < width/2; x++) { 
							int p = image.getRGB(x,y);
							int p1 = image.getRGB(width-x-1,y);
							
							image.setRGB(x, y, p1);
							image.setRGB(width-x-1,y,p); 
	            				} 
					}
					File outputImage = new File("Fliped.jpg");
					ImageIO.write(image, "jpg", outputImage);
					System.out.println("Writing complete.11");
				
				}
				catch(Exception E){
					System.out.print(E);
				}
			
			}
			if(cflip.getSelectedIndex()==2){
				try{
					BufferedImage image = null;
					File d= null;
					d=new File(text.getText()+".jpg");
					image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(d);
	
					int width = image.getWidth(); 
			        	int height = image.getHeight();
					for (int y = 0; y < height/2; y++) { 	
			            		for (int x = 0; x < width; x++) { 
							int p = image.getRGB(x,y);
							int p1 = image.getRGB(x,height-y-1);
							int  t= p;
							image.setRGB(x, y, p1);
							image.setRGB(x,height-y-1,p);
	            				} 
					}
					File outputImage = new File("Fliped.jpg");
					ImageIO.write(image, "jpg", outputImage);
					System.out.println("Writing complete.22");
				
				}
				catch(Exception E){
					System.out.print(E);
				}
			
			}
		}
		if(e.getSource()==brotate){
			try{
				BufferedImage image = null;
				File d= null;
				d=new File(text.getText()+".jpg");
				image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
				image = ImageIO.read(d);
				int width = image.getWidth(); 
				int height = image.getHeight();
		        	//BufferedImage image1 = new BufferedImage(height,width,BufferedImage.TYPE_INT_ARGB);
				for (int y = 0; y < height; y++) { 	
		            		for (int x = 0; x < width; x++) { 
						int p = image.getRGB(x,y);
						image.setRGB(y,x,p);
            				} 
				}
				File outputImage = new File("Rotated.jpg");
				ImageIO.write(image, "jpg", outputImage);
				System.out.println("Writing complete.22");
				
			}
			catch(Exception E){
				System.out.print(E);
			}
		
		}	
	}
	
	
	void crop()throws Exception{
	
		JFrame fcrop = new JFrame("CROP");
		JPanel pcrop = new JPanel();
		Label hcrop = new Label("CROP");
		hcrop.setFont(new Font("Lucida",Font.PLAIN,50));
		bcrop = new Button("Crop");
		bcrop.setFont(new Font("Lucida",Font.PLAIN,24));
		X1 = new TextField("0",5);
		X1.setFont(new Font("Lucida",Font.PLAIN,24));
		Y1 = new TextField("0",5);
		Y1.setFont(new Font("Lucida",Font.PLAIN,24));
		X2 = new TextField("100",5);
		X2.setFont(new Font("Lucida",Font.PLAIN,24));
		Y2 = new TextField("100",5);
		Y2.setFont(new Font("Lucida",Font.PLAIN,24));

		Label l1 = new Label("x1");
		l1.setFont(new Font("Lucida",Font.PLAIN,24));
		Label l2 = new Label("x2");
		l2.setFont(new Font("Lucida",Font.PLAIN,24));
		Label l3 = new Label("y1");
		l3.setFont(new Font("Lucida",Font.PLAIN,24));
		Label l4 = new Label("y2");
		l4.setFont(new Font("Lucida",Font.PLAIN,24));
		
		pcrop.add(l1);
		pcrop.add(X1);
		pcrop.add(l2);
		pcrop.add(Y1);
		pcrop.add(l3);
		pcrop.add(X2);
		pcrop.add(l4);
		pcrop.add(Y2);
		pcrop.add(bcrop);

		bcrop.addActionListener(this);
		
		fcrop.add(pcrop);
		fcrop.show();
		fcrop.setSize(300,300);
	}

	void flip()throws Exception{
		
		JFrame fflip = new JFrame("FLIP");
		JPanel pflip = new JPanel();
		bflip = new Button("Flip");
		bflip.setFont(new Font("Lucida",Font.PLAIN,24));
		cflip = new Choice();
		cflip.setFont(new Font("Lucida",Font.PLAIN,24));
		cflip.add("Select");
		cflip.add("Horizontal");
		cflip.add("Vertical");
		Label hflip = new Label("FLIP");
		hflip.setFont(new Font("Lucida",Font.BOLD,50));

		pflip.add(hflip);
		pflip.add(cflip);
		pflip.add(bflip);
		
		bflip.addActionListener(this);
		
		fflip.add(pflip);
		fflip.show();
		fflip.setSize(300,200);
	}


	void rotate()throws Exception{
		
		JFrame frotate = new JFrame("ROTATAE");
		JPanel protate = new JPanel();
		brotate = new Button("Rotate");
		brotate.setFont(new Font("Lucida",Font.PLAIN,24));
		Label hrotate = new Label("ROTATE");
		hrotate.setFont(new Font("Lucida",Font.BOLD,50));

		protate.add(hrotate);
		protate.add(brotate);
		
		brotate.addActionListener(this);
		
		frotate.add(protate);
		frotate.show();
		frotate.setSize(400,300);
	}
}