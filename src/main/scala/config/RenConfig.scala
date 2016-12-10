package config

import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm
import com.typesafe.config.ConfigFactory

/**
  * Created by coldsauce on 12/9/2016.
  */
case class RenConfig(pitchConfig: PitchConfig, audioConfig: AudioConfig)

object RenConfig {
  def createFromConfigFile(configFileName: String): RenConfig = {
    val pitchConfig = PitchConfig.createFromConfigFile(configFileName)
    val audioConfig = AudioConfig.createFromConfigFile(configFileName)

    RenConfig(pitchConfig, audioConfig)
  }
}

case class PitchConfig(algorithmType: PitchEstimationAlgorithm)

object PitchConfig {
  def createFromConfigFile(configFileName: String): PitchConfig = {
    val configToUse = ConfigFactory.load(configFileName)
    val algoToUse = configToUse.getString("pitch.algorithm") match {
      case "YIN" => PitchEstimationAlgorithm.YIN
      case "MPM" => PitchEstimationAlgorithm.MPM
      case "FFT_YIN" => PitchEstimationAlgorithm.  FFT_YIN
      case "DYNAMIC_WAVELET" => PitchEstimationAlgorithm.DYNAMIC_WAVELET
      case "FFT_PITCH" => PitchEstimationAlgorithm.FFT_PITCH
      case "AMDF" => PitchEstimationAlgorithm.AMDF
    }
    PitchConfig(algoToUse)
  }
}

case class AudioConfig(sampleRate: Int, bufferSize: Int, overlap: Int, sampleInBits: Int, channels: Int,
                       signed: Boolean, bigEndian: Boolean)

object AudioConfig {
  def createFromConfigFile(configFileName: String): AudioConfig = {
    val configToUse = ConfigFactory.load(configFileName)
    val sampleRate = configToUse.getInt("audio.sampleRate")
    val bufferSize = configToUse.getInt("audio.bufferSize")
    val overlap = configToUse.getInt("audio.overlap")
    val sampleInBits = configToUse.getInt("audio.sampleInBits")
    val channels = configToUse.getInt("audio.channels")
    val signed = configToUse.getBoolean("audio.signed")
    val bigEndian = configToUse.getBoolean("audio.bigEndian")
    AudioConfig(sampleRate, bufferSize, overlap, sampleInBits, channels, signed, bigEndian)
  }
}

case class MovementConfig(up: Direction, right: Direction, down: Direction, left: Direction, threshold: Double)


// These are all their own case classes because I wanted to preserve as much type saftey as possible.
case class Direction(lowerbound: Double, upperbound: Double, deltaX: Int, deltaY: Int)

object MovementConfig {
  def createFromConfigFile(configFileName: String): MovementConfig = {
    val configToUse = ConfigFactory.load(configFileName)
    val upUpperbound = configToUse.getDouble("movement.up.upperbound")
    val upLowerbound = configToUse.getDouble("movement.up.lowerbound")

    val rightUpperbound = configToUse.getDouble("movement.right.upperbound")
    val rightLowerbound = configToUse.getDouble("movement.right.lowerbound")

    val downUpperbound = configToUse.getDouble("movement.down.upperbound")
    val downLowerbound = configToUse.getDouble("movement.down.lowerbound")

    val leftUpperbound = configToUse.getDouble("movement.left.upperbound")
    val leftLowerbound = configToUse.getDouble("movement.left.lowerbound")

    val threshold = configToUse.getDouble("movement.threshold")

    MovementConfig(
      Direction(upLowerbound, upUpperbound, 0, -1),
      Direction(rightLowerbound, rightUpperbound, 1, 0),
      Direction(downLowerbound, downUpperbound, 0, 1),
      Direction(leftLowerbound, leftUpperbound, -1, 0),
      threshold
    )
  }
}
