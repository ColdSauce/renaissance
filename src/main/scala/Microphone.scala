import javax.sound.sampled._

import be.tarsos.dsp.AudioDispatcher
import be.tarsos.dsp.io.jvm.JVMAudioInputStream
import be.tarsos.dsp.pitch.PitchProcessor
import config.RenConfig

/**
  * Created by campi on 12/9/2016.
  */
case class Microphone(mixer: Mixer, config: RenConfig) {
  val audioConfig = config.audioConfig
  val pitchConfig = config.pitchConfig

  val audioStream: JVMAudioInputStream = {
    //A common sample rate is 44100 and a common bufferSize is 1024.
    //That's why they are used here
    val format = new AudioFormat(audioConfig.sampleRate, audioConfig.sampleInBits, audioConfig.channels, audioConfig.signed,
      audioConfig.bigEndian)

    val dataLineInfo = new DataLine.Info(classOf[TargetDataLine], format)

    val line = mixer.getLine(dataLineInfo).asInstanceOf[TargetDataLine]

    val numberOfSamples = audioConfig.bufferSize
    line.open(format, numberOfSamples)
    line.start()

    val stream = new AudioInputStream(line)

    val audioStream = new JVMAudioInputStream(stream)
    audioStream
  }
}
