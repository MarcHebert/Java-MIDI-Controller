import javax.sound.midi.*;

import java.util.*;
import java.io.*;

public class main {
	 
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Open default sequencer specified by MidiSystem
		try {
			sequencer.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get the transmitter class inputDevice
		try {
			transmitter = inputDevice.getTransmitter();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get the receiver class from sequencer
		try {
			receiver = sequencer.getReceiver();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Bind the transmitter to the receiver so the receiver gets input from the transmitter
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
		
		
	}

}
