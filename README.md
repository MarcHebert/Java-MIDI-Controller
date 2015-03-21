# Java-MIDI-Controller
My attempts to use the Java MIDI API with my Korg PadKontrol MIDI controller.

Currently, the program scans for available MIDI Devices. The user is then able to pick an input device and output device. MIDI Events are recorded from the input device (in my case a Korg PadKontrol). After recording is stopped, recorded MIDI events are dumped in their bytecode equivalent to a .midi file.

![Work setup](https://www.dropbox.com/s/fpu4k1flm2yxa8v/Screenshot%202015-03-20%2020.12.07.png?dl=0)
