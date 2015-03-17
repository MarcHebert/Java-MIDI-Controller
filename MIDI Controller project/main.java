import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class main {

	public static void main(String[] args) 
	{
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for(int i=0;i<infos.length;i++)
		{
		    System.out.println(infos[i].getName() + " - " + infos[i].getDescription());
		}
	}

}
