import javax.sound.sampled.{AudioSystem, Mixer}

/**
  * Created by campi on 12/9/2016.
  */
object MixerUtil {

  def getMixerWithInfo(mixerInfo: Mixer.Info): Mixer = {
    AudioSystem.getMixer(mixerInfo)
  }

  def getMixerWithIndex(index: Int): Mixer = {
    AudioSystem.getMixer(AudioSystem.getMixerInfo()(index))
  }

  val allMixers: Array[Mixer.Info] = {
    AudioSystem.getMixerInfo()
  }
}
