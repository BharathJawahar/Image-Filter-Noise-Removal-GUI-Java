import java.util.Scanner;
import java.io.*; 
import java.io.IOException; 
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;

class filters{

	void option()throws IOException{

		DataInputStream cin = new DataInputStream(System.in);
		Scanner Cin = new Scanner(System.in);

		BufferedImage image = null;
		File d= null;
		d=new File("Inp.jpg");
		image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		image = ImageIO.read(d);
				
		int option=0;

		System.out.println("\n***Filters***\n\n");
		System.out.println("1.Greyscale");
		System.out.println("2.Negative");
		System.out.println("3.Sepia");
		System.out.println("4.Reddish");
		System.out.println("5.Bluish");
		System.out.println("6.Greenish");
		System.out.println("7.Black and White");
		
		option=Cin.nextInt();
		
		switch (option)
		{
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
		File outputImage = new File("GreyScale.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Writing complete.");
		
	}

	void blackwhite(BufferedImage image)throws IOException{
		
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
		  		
				if(avg>=128)
					avg=255;
				else
					avg=0;
				
				// replace RGB value with avg 
				p = (avg<<24) | (avg<<16) | (avg<<8) | avg; 
		  
				image.setRGB(x, y, p); 
            		} 
		}
		File outputImage = new File("BlackAndWhite.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Writing complete.");
		
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
		File outputImage = new File("Negative.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Writing complete.");
		
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
		File outputImage = new File("Sepia.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Writing complete.");
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
		File outputImage = new File("Reddish.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Writing complete.");
		
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
		File outputImage = new File("Bluish.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Writing complete.");
		
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
		File outputImage = new File("greenish.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Writing complete.");
		
	}


}


class edith{
	void option()throws IOException{

		DataInputStream cin = new DataInputStream(System.in);
		Scanner Cin = new Scanner(System.in);

		BufferedImage image = null;
		File d= null;
		d=new File("Inp.jpg");
		image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		image = ImageIO.read(d);
				
		int option=0;

		System.out.println("\n***Edith***\n\n");
		System.out.println("1.Crop");
/*
		System.out.println("2.Negative");
		System.out.println("3.Sepia");
		System.out.println("4.Reddish");
		System.out.println("5.Bluish");
		System.out.println("6.Greenish");
		System.out.println("7.Black and White");
*/
		option=Cin.nextInt();
		
		switch (option)
		{
			case 1 : crop(image);
				 break;
/*
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
*/
		}
	}
	
	void crop(BufferedImage image)throws IOException{
		BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);
	}
}


class alpha{
	
	public static void main(String args[])throws IOException{ 

		filters f = new filters();
		Edit e = new Edit();

		DataInputStream cin = new DataInputStream(System.in);
		Scanner Cin = new Scanner(System.in);

		int option=0;

		System.out.println("\n*******Digital Image Processing*******");
		System.out.println("\n\nNote : Your input image should be saved in the same folder and should be bearing the the name Input.jpg\n\n");
		System.out.print("1.Filters\n2.Edit\n\nEnter your Option : ");
		option=Cin.nextInt();

		switch (option)
		{
			case 1 : f.option();
				 break;
		}

	}
}
