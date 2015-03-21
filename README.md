# Java-MIDI-Controller
My attempts to use the Java MIDI API with my Korg PadKontrol MIDI controller.

Currently, the program scans for available MIDI Devices. The user is then able to pick an input device and output device. MIDI Events are recorded from the input device (in my case a Korg PadKontrol). After recording is stopped, recorded MIDI events are dumped in their bytecode equivalent to a .midi file.

![Work setup](https://dl-web.dropbox.com/get/Screenshots/Screenshot%202015-03-20%2020.12.07.png?_subject_uid=63310308&w=AACnbpnWQbuk0Ao5gptLzTLtbY-mHCzoyDrxFdz6JGMiVw)
