import javax.sound.midi.*;

import java.util.*;
import java.io.*;
import java.awt.event.*;

public class main{
	 
	private static final char STOPKEY_ = '\r';
	public static void main(String[] args) throws InvalidMidiDataException 
	{
		//STDIN
		Scanner in = new Scanner(System.in);
		
		//acquire all connected MIDI devices and print them
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for(int i=0;i<infos.length;i++)
		{
		    System.out.println("Device index:"+i+" "+infos[i].getName() + " - " + infos[i].getDescription());
		}
		
		Sequencer sequencer=null;
		try {
			sequencer = MidiSystem.getSequencer();
		} catch (MidiUnavailableException e1) {
			System.out.println("Sequencer unavailable");
			e1.printStackTrace();
		}
		Transmitter transmitter = null;
		Receiver receiver = null;
		
		//device selection
		int input, output = 1000;
		MidiDevice inputDevice = null;
		MidiDevice outputDevice = null;
		
		System.out.println("Select input device: ");
		input = in.nextInt();
		try {
			inputDevice = MidiSystem.getMidiDevice(infos[input]);
		} catch (MidiUnavailableException e) {
			System.out.println("Input device selected is unavailable");
			e.printStackTrace();
		}
		
		System.out.println("Select output device: ");
		output = in.nextInt();
		try {
			outputDevice = MidiSystem.getMidiDevice(infos[output]);
		} catch (MidiUnavailableException e) {
			System.out.println("Output device selected is unavailable");
			e.printStackTrace();
		}
		
		// Open a connection to inputDevice
		try {
			inputDevice.open();
		} catch (MidiUnavailableException e) {
			System.out.println("Cannot open selected input device");
			e.printStackTrace();
		}
		
		// Open default sequencer specified by MidiSystem
		try {
			sequencer.open();
		} catch (MidiUnavailableException e) {
			System.out.println("Cannot open sequencer");
			e.printStackTrace();
		}
		
		// Get the transmitter class inputDevice
		try {
			transmitter = inputDevice.getTransmitter();
		} catch (MidiUnavailableException e) {
			System.out.println("Cannot acquire input device transmitter");
			e.printStackTrace();
		}
		
		// Get the receiver class from sequencer
		try {
			receiver = sequencer.getReceiver();
		} catch (MidiUnavailableException e) {
			System.out.println("Cannot acquire sequencer reciever");
			e.printStackTrace();
		}
		
		// Bind transmitter to receiver so receiver gets input from transmitter
		transmitter.setReceiver(receiver);
		
		
		//ATTENTION: THROWING InvalidMidiDataException. 
		// Create a new sequence
		Sequence seq = new Sequence(Sequence.PPQ, 24);
		// And of course a track to record the input on
		Track currentTrack = seq.createTrack();
		// Do some sequencer settings
		sequencer.setSequence(seq);
		sequencer.setTickPosition(0);
		sequencer.recordEnable(currentTrack, -1);
		// And start recording
		sequencer.startRecording();
		System.out.println("Recording..\n Press enter to stop");
		
		//record until enter is pressed
		//while(in.nextLine()!=);
		try {
			System.in.read();
		} catch (IOException e1) {
			System.out.println("I/O issues");
			e1.printStackTrace();
		}
		System.out.println("Recording stopped");
		//on key press stop recording
		if(sequencer.isRecording())
		{
		    // Tell sequencer to stop recording
		    sequencer.stopRecording();

		    // Retrieve the sequence containing the stuff you played on the MIDI instrument
		    Sequence tmp = sequencer.getSequence();
		    
		    System.out.println("Playing back recorded notes");
		    
		    //playback from start of sequence
		    sequencer.setSequence(tmp);
		    sequencer.start();
		    
		    // Save to file
		    try {
				MidiSystem.write(tmp, 0, new File("MyMidiFile.mid"));
			} catch (IOException e) {
				System.out.println("Unable to write to midi file");
				e.printStackTrace();
			}
		}
		
		
		inputDevice.close();
		sequencer.close();
		
	}

}
