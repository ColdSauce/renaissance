import java.util.Scanner
import javax.sound.sampled.{Mixer, TargetDataLine}

import be.tarsos.dsp.AudioEvent
import be.tarsos.dsp.pitch.PitchDetectionResult
import com.typesafe.config.Config
import config.{MovementConfig, RenConfig}

/**
  * Created by coldsauce on 12/9/2016.
  */
object Launcher {
  def main(args: Array[String]): Unit = {
    val (renConfig, movementConfig) = if(args.size == 1) {
      (RenConfig.createFromConfigFile(args(0)), MovementConfig.createFromConfigFile(args(0)))
    } else {
      (RenConfig.createFromConfigFile("application.conf"), MovementConfig.createFromConfigFile("application.conf"))
    }

    val controller = new Controller(movementConfig)
    val mixerToUse = getMixerToUse()
    val pitchDetector = PitchDetector(mixerToUse, renConfig)
    val mic = Microphone(mixerToUse, renConfig)
    pitchDetector.start(mic.audioStream, (somePitch, _) => {
      controller.actBasedOnPitch(somePitch)
    })
  }

  def printUsageInstructions(): Unit = {
    println("usage: sbt renaissance/run <optional custom config file name>")
  }

  /*
  TODO: Make this not use side effects. (Free monad + interpreter and all)
   */
  def getMixerToUse(): Mixer = {
    println("These are the mixers your computer has: ")
    println()
    val mixers = MixerUtil.allMixers
    mixers.zipWithIndex.foreach(someMixer => {
      val ONE_INDEX_OFFSET = 1
      val toPrint = s"[${someMixer._2 + ONE_INDEX_OFFSET}] ${someMixer._1}"
      println(toPrint)
    })
    val in = new Scanner(System.in)
    println()

    println("Please enter the mixer you want to use.")
    val userInput = in.nextLine()
    userInput match {
      case "" => MixerUtil.getMixerWithIndex(0)
      case x =>  MixerUtil.getMixerWithIndex(x.toInt - 1)
    }
  }
}
