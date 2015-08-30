// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 26
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 3 (3 decks)
 *   Related games (related): threedemons
 */
object TripleCanfield extends GameRules(
  id = "triplecanfield",
  completed = true,
  title = "Triple Canfield",
  related = Seq("threedemons"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_canfield.htm")),
  description = "An easy three-deck version of ^canfield^ invented by Thomas Warfield that has fewer tableau piles and a smaller reserve than ^threedemons^.",
  layout = "swf|r::::.t",
  deckOptions = DeckOptions(
    numDecks = 3,
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 26,
      cardsFaceDown = -1
    )
  )
)