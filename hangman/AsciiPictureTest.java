package hangman;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/*
Assignment number : 3
File Name : AsciiPictureTest.java
Name : Ilay Serr
Email : ilay92@gmail.com
*/

public class AsciiPictureTest {
	AsciiPicture picture;
	
	@Before
	public void setUp() throws Exception {
		char[][] hangman = { 
				   "  _________     ".toCharArray(),
				   " |         |    ".toCharArray(),
				   " |         0    ".toCharArray(),
				   " |        /|\\   ".toCharArray(),
				   " |        / \\   ".toCharArray(),
				   " |              ".toCharArray(),
				   " |              ".toCharArray() };
		this.picture = new AsciiPicture(hangman[0].length,
								hangman.length, 0, 0, hangman);
	}

	@Test
	public void testGet() { 
		assertEquals(' ' , this.picture.get(0 , 0)); 
		assertEquals('0' , this.picture.get(11 , 2));
		assertEquals('\\' , this.picture.get(12 , 4));
		assertEquals(' ' , this.picture.get(15 , 6));
	}

	@Test
	public void testSet() {
		this.picture.set(2, 3, 'x');
		assertEquals('x' , this.picture.get(2 , 3));
		this.picture.set(0, 4, '2');
		assertEquals('2' , this.picture.get(0 , 4));
		
		//replacing the '|' char in a 'g'
		this.picture.set(11, 3, 'g');
		assertNotEquals('|' , this.picture.get(11 , 3));
		
	}

	@Test
	public void testAsciiPictureIntIntIntIntCharArrayArray() {
		assertEquals(16 , picture.width);
		assertEquals(7 , picture.height);
		assertEquals(0 , picture.leftX);
		assertEquals(0 , picture.topY);
		char[][] hangman = { 
				   "  _________     ".toCharArray(),
				   " |         |    ".toCharArray(),
				   " |         0    ".toCharArray(),
				   " |        /|\\   ".toCharArray(),
				   " |        / \\   ".toCharArray(),
				   " |              ".toCharArray(),
				   " |              ".toCharArray() };
		for(int i = 0; i < hangman.length ; i++) {
			assertArrayEquals(hangman[i], this.picture.picture[i]);	
		}
		
	}

	@Test
	public void testAsciiPictureIntIntChar() {
		AsciiPicture hangi = new AsciiPicture(16, 7, '_');
		assertEquals(16 , hangi.width); 
		assertEquals(7 , hangi.height); 
		for(int i = 0; i < hangi.height ; ++i) {
			for (int j = 0; j < hangi.width; j++) {
				assertEquals('_', hangi.picture[i][j]);
			}	
		}
	}

	@Test
	public void testOverlay1() {
		AsciiPicture basePic = new AsciiPicture(4, 3, 'p');
		char [][] sec = { 
			"1000".toCharArray(), 
			"0**0".toCharArray(),
			"0000".toCharArray() };
		AsciiPicture secPic = new AsciiPicture(4, 3, 0, 0, sec);
		basePic.overlay(secPic , 0, 0, '*');
		char [][] result = {
			"1000".toCharArray(), 
			"0pp0".toCharArray(),
			"0000".toCharArray() };
		AsciiPicture resultPic = new AsciiPicture(4, 3, 0, 0, result);
		for (int i = 0; i < resultPic.height; i++) {
			assertArrayEquals(resultPic.picture[i], basePic.picture[i]);
		}
	}
	
	@Test
	public void testOverlay2() {
		char [][] base = {
			"0000".toCharArray(), 
			"0000".toCharArray(),
			"00pp".toCharArray() };
		AsciiPicture basePic = new AsciiPicture(4, 3, 2, 2, base);
		char [][] sec = {
			"*0".toCharArray() };
		AsciiPicture secPic = new AsciiPicture(2, 1, 0, 0, sec);
		basePic.overlay(secPic , 0, 0, '*');
		char [][] result = {
			"0000".toCharArray(), 
			"0000".toCharArray(), 
			"00p0".toCharArray() };
		AsciiPicture resultPic = new AsciiPicture(4, 3, 0, 0, result);
		for (int i = 0; i < resultPic.height; i++) {
			assertArrayEquals(resultPic.picture[i], basePic.picture[i]);
		}
	}
	
	@Test
	public void testOverlay3() {
		char [][] base = {
			"123".toCharArray(), 
			"456".toCharArray(),
			"789".toCharArray() };
		AsciiPicture basePic = new AsciiPicture(3, 3, 1, 1, base);
		AsciiPicture secPic = new AsciiPicture(3, 3, '0');
		basePic.overlay(secPic, -1, -1, 'N');
		char [][] result = {
			"123".toCharArray(),  
			"400".toCharArray(), 
			"700".toCharArray() };
		AsciiPicture resultPic = new AsciiPicture(3, 3, 0, 0, result);
		for (int i = 0; i < resultPic.height; i++) {
			assertArrayEquals(resultPic.picture[i], basePic.picture[i]);
		}
	}
	
	@Test
	public void testOverlay4() {
		char [][] base = {
			"vvvvv".toCharArray(), 
			"Xvvvv".toCharArray(), 
			"vvvvv".toCharArray(),
			"vvvvv".toCharArray() };
		AsciiPicture basePic = new AsciiPicture(5, 4, 0, 0, base);
		char [][] sec = {
			"000X0".toCharArray(), 
			"X0X00".toCharArray(),
			"0X000".toCharArray(), 
			"XXXXX".toCharArray() }; 
		AsciiPicture secPic = new AsciiPicture(5, 4, 0, 0, sec);
		basePic.overlay(secPic, 1, 1, '0');
		char [][] result = {
			"vvvvv".toCharArray(), 
			"XvvvX".toCharArray(), 
			"vXvXv".toCharArray(),
			"vvXvv".toCharArray() }; 
		AsciiPicture resultPic = new AsciiPicture(5, 4, 0, 0, result);
		for (int i = 0; i < resultPic.height; i++) {
			assertArrayEquals(resultPic.picture[i], basePic.picture[i]);
		}
	}

	@Test
	public void testPrint() throws IOException {
		ByteArrayOutputStream pic = new ByteArrayOutputStream();
		PrintStream temp = new PrintStream(pic);
		picture.print(temp);
		assertEquals("  _________     \n"
					+ " |         |    \n"
					+ " |         0    \n"
					+ " |        /|\\   \n"
					+ " |        / \\   \n"
					+ " |              \n"
					+ " |              \n", pic.toString());
		temp.close();
	}	
}
