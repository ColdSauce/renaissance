import javax.sound.sampled.Mixer

import be.tarsos.dsp.{AudioDispatcher, AudioEvent}
import be.tarsos.dsp.io.jvm.JVMAudioInputStream
import be.tarsos.dsp.pitch.{PitchDetectionHandler, PitchDetectionResult, PitchProcessor}
import config.RenConfig

/**
  * Created by campi on 12/9/2016.
  */
case class PitchDetector(mixerToUser: Mixer, renConfig: RenConfig) {

  private val audioConfig = renConfig.audioConfig
  private val pitchConfig = renConfig.pitchConfig

  private def getPitchProcessor(callback: (PitchDetectionResult, AudioEvent) => Unit) : PitchProcessor = {
    new PitchProcessor(pitchConfig.algorithmType, audioConfig.sampleRate, audioConfig.bufferSize,
      (pitchDetectionResult: PitchDetectionResult, audioEvent: AudioEvent) => callback(pitchDetectionResult, audioEvent)
    )
  }

  /*
  TODO: Make this have no side effects.
   */
  def start(audioStream: JVMAudioInputStream, callback: (PitchDetectionResult, AudioEvent) => Unit): Unit = {
    val dispatcher = new AudioDispatcher(audioStream, audioConfig.bufferSize,
      audioConfig.overlap)
    val pitchProcessor = getPitchProcessor(callback)
    dispatcher.addAudioProcessor(pitchProcessor)
    new Thread(dispatcher, "Audio dispatching").start()
  }

}
