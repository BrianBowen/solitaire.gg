import java.util.UUID

import models._

trait SolitaireMoveHelper extends SolitaireVictoryHelper {
  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean = true): Unit
  protected def getResult: GameResult

  protected[this] def handleSelectCard(userId: UUID, cardId: UUID, pileId: String) = {
    val card = gameState.cardsById(cardId)
    val pile = gameState.pilesById(pileId)
    if (!pile.cards.contains(card)) {
      throw new IllegalStateException(s"SelectCard for game [$gameId]: Card [${card.toString}] is not part of the [$pileId] pile.")
    }
    if (pile.canSelectCard(card, gameState)) {
      val messages = pile.onSelectCard(card, gameState)
      send(MessageSet(messages))
    }
    registerMove()
  }

  protected[this] def handleSelectPile(userId: UUID, pileId: String) = {
    val pile = gameState.pilesById(pileId)
    if (pile.cards.nonEmpty) {
      throw new IllegalStateException(s"SelectPile [$pileId] called on a non-empty deck.")
    }
    val messages = if (pile.canSelectPile(gameState)) { pile.onSelectPile(gameState) } else { Nil }
    send(MessageSet(messages))
    registerMove()
  }

  protected[this] def handleMoveCards(userId: UUID, cardIds: Seq[UUID], source: String, target: String) = {
    val cards = cardIds.map(gameState.cardsById)
    val sourcePile = gameState.pilesById(source)
    val targetPile = gameState.pilesById(target)
    for (c <- cards) {
      if (!sourcePile.cards.contains(c)) {
        throw new IllegalArgumentException(s"Card [$c] is not a part of source pile [${sourcePile.id}].")
      }
    }
    if (sourcePile.canDragFrom(cards, gameState)) {
      if (targetPile.canDragTo(sourcePile, cards, gameState)) {
        val messages = targetPile.onDragTo(sourcePile, cards, gameState)
        send(MessageSet(messages))
        registerMove()
      } else {
        send(CardMoveCancelled(cardIds, source))
      }
    } else {
      send(CardMoveCancelled(cardIds, source))
    }
  }
}