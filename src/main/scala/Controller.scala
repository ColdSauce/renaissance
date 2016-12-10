import java.awt.{MouseInfo, Robot}

import be.tarsos.dsp.pitch.PitchDetectionResult
import config.{MovementConfig, RenConfig}

/**
  * Created by campi on 12/10/2016.
  */
case class Controller(movementConfig: MovementConfig) {
  val robot = new Robot()

  private def moveMouseAccordingToPitch(pitch: Float): Unit = {
    import MathUtil.FloatImprovement
    val directions = List(movementConfig.up, movementConfig.right, movementConfig.down, movementConfig.left)
    directions.foreach(direction => {
      if(pitch.isBetween(direction.lowerbound, direction.upperbound)) {
        val mouseX = MouseInfo.getPointerInfo().getLocation().getX().toInt
        val mouseY = MouseInfo.getPointerInfo().getLocation().getY().toInt
        robot.mouseMove(mouseX + direction.deltaX, mouseY + direction.deltaY)
      }
    })
  }

  def actBasedOnPitch(pitchDetectionResult: PitchDetectionResult): Unit = {
    val pitchDetected = pitchDetectionResult.getPitch

    if(pitchDetectionResult.getProbability > movementConfig.threshold && pitchDetected != -1) {
      moveMouseAccordingToPitch(pitchDetected)
    }
  }
}
