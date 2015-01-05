# renaissance
---

###What is it?

renaissance allows people to control their computers by playing musical notes. Check out a sample of what renaissance can do here. 

It is currently in the very early stages of production. To get it to even work, you must change some code depending on your instrument. Once the GUI is finished, it will be a piece of cake to get your instrument working!

<a href="http://www.youtube.com/watch?feature=player_embedded&v=1GcoryCX5a8" target="_blank"><img src="http://img.youtube.com/vi/1GcoryCX5a8/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

###Explanation
renaissance employs a library known as TarsosDSP to detect a pitch played into the microphone in real time. TarsosDSP uses something known as a "Fast-Fourier Transform" that allows a computer to figure out what pitch a sound is. To read more about fast fourier transforms, check out the [wikipedia article](https://en.wikipedia.org/wiki/Fast_Fourier_transform). After finding the pitch of a sound, it presses a cooresponding key on the keyboard or moves the mouse in a certain way. 

###Contributing
To contribute to renaissance, join our IRC channel! #renaissance on freenode. 

Currently, this requires a GUI so that a person can specify at what frequency their notes are at.

The GUI should have the ability to create a mapping of notes to keys. Such that when a person plays a note, a corresponding key is pressed. It should have the capability of allowing a user to set what frequency each note is on their instrument as different intruments have different frequencies for the same notes. It should have the ability to allow a person to chose what Mixer to use. 

To make things more accurate, there should be some learning algorithm created such that the user can teach the computer what each note's frequency is in the beginning. 

There are TODOs in the project. If you find one, you should definitely work on it! However, post on the IRC that you are working on it so that two people aren't working on the same thing at the same time.

###Sidenote
I am probably going to work on an Android app for this so that it is more accessible to people since desktop apps are virtually dead now.
