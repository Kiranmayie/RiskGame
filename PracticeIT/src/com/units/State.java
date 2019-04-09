package com.units;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import org.codehaus.jackson.annotate.JsonAutoDetect;
import com.main.StartSavedGame;
//@JsonAutoDetect
public class State{
		
	public final void writeObject(StartSavedGame gamePlay) throws IOException{
	      try {
	          FileOutputStream fileOut =
	          new FileOutputStream("player.ser");
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(gamePlay);
	          out.close();
	          fileOut.close();
	          System.out.printf("Serialized data is saved in player.ser");
	       } catch (IOException i) {
	          i.printStackTrace();
	       }
	}
	
	
	public final StartSavedGame readObject() {
		StartSavedGame e = null;
	      try {
	         FileInputStream fileIn = new FileInputStream("player.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (StartSavedGame) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (Exception i) {
	         i.printStackTrace();
	      }
	      return e;
	}
}