package game

import help.HelpService
import menu.MenuService
import org.scalajs.jquery.{jQuery => $}
import util.TemplateUtils

object GameListService {
  private[this] var initialized = false

  def update(ag: Option[ActiveGame], onNewGame: Seq[String] => Unit, menu: MenuService) = {
    val panel = $("#panel-list-games .content")

    menu.options.setOptionsForGameList()

    if (!initialized) {
      panel.html(GameListTemplate.panelContent().toString)

      TemplateUtils.clickHandler($(".rules-link", panel), jq => onNewGame(Seq(jq.data("rules").toString)))
      TemplateUtils.clickHandler($(".help-link", panel), jq => HelpService.show(Some(jq.data("rules").toString), menu))

      initialized = true
    }

    val optionsEl = $(".options-el", panel)

    ag match {
      case Some(activeGame) =>
        optionsEl.html(GameListTemplate.optionsContent(activeGame).toString)
        TemplateUtils.clickHandler($(".btn-resume", optionsEl), _ => {
          menu.navigation.resume(activeGame)
        })
        TemplateUtils.clickHandler($(".btn-resign", optionsEl), _ => onNewGame(Seq("resign")))
        TemplateUtils.clickHandler($(".btn-redeal", optionsEl), _ => onNewGame(Seq(activeGame.rulesId)))
      case None =>
        optionsEl.html($("#home-content").html())
        TemplateUtils.clickHandler($(".home-link-help", optionsEl), _ => menu.navigation.generalHelp())
        TemplateUtils.clickHandler($(".home-link-play", optionsEl), _ => onNewGame(Nil))
    }
  }
}
