# renaissance
---

###What is it?

renaissance allows people to control their computers by playing musical notes. Check out a sample of what renaissance can do here. 

It is currently in the very early stages of production. To get it to work, you must change the config file called "application.conf" and add your custom thresholds or create your own config file and add a command line argument. I'll create a guide to this soon.. 

<a href="http://www.youtube.com/watch?feature=player_embedded&v=1GcoryCX5a8" target="_blank"><img src="http://img.youtube.com/vi/1GcoryCX5a8/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

###Explanation
renaissance employs a library known as TarsosDSP to detect a pitch played into the microphone in real time. TarsosDSP uses something known as a "Fast-Fourier Transform" that allows a computer to figure out what pitch a sound is. To read more about fast fourier transforms, check out the [wikipedia article](https://en.wikipedia.org/wiki/Fast_Fourier_transform). After finding the pitch of a sound, it presses a cooresponding key on the keyboard or moves the mouse in a certain way. 

###Contributing
To contribute to renaissance, check out the issues on Github. If you want to work on something larger, go to my Github profile @coldsauce find my email, and we can talk over email.

### Direction
This project, in its release, will become a desktop application. The main goal is to get it so that people can create frequency ranges for their instruments and customize what each note does. That way, they can control their computer the way they want to.

I don't really like how the config file works right now and it will definitely change in the future.

All of the code will be 100% Scala from now on.
