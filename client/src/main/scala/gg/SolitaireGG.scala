package gg

import gg.navigation.NavigationService
import gg.network.{MessageHandler, NetworkService}
import gg.phaser.PhaserGame
import utils.Logging

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("SolitaireGG")
object SolitaireGG {
  private[this] var active: Option[SolitaireGG] = None

  @JSExport
  def go(debug: Boolean): Unit = active match {
    case None => active = Some(new SolitaireGG(debug))
    case _ => throw new IllegalStateException("Already initialized.")
  }
}

class SolitaireGG(val debug: Boolean) {
  val navigation = new NavigationService()
  val network = new NetworkService()
  val messageHandler = new MessageHandler()
  val game = new PhaserGame()

  init()

  private[this] def init() = {
    utils.Logging.info("Solitaire.gg, v2.0.0")
    Logging.installErrorHandler()
    js.Dynamic.global.PhaserGlobal = js.Dynamic.literal("hideBanner" -> true)

    testbed()
  }

  private[this] def testbed() = {
  }
}
