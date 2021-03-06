package services.test

import models.GameMessage
import models.history.GameHistory
import models.rules.GameRulesSet
import models.test.{Test, Tree}
import services.history.GameSeedService
import util.Logging

import scala.util.Random
import util.FutureUtils.defaultContext

object SolverTests extends Logging {
  val all = Tree(Test("solver"), GameRulesSet.all.map(x => testSolver(x.id).toTree))

  def testSolver(rules: String, seed: Option[Int] = None) = Test(s"solver-$rules", () => runSolver(rules, seed.getOrElse(Random.nextInt(1000000))))

  def runSolver(rules: String, seed: Int) = {
    val solver = GameSolver(rules, 0, seed)
    val movesPerformed = collection.mutable.ArrayBuffer.empty[GameMessage]
    while (!solver.gameWon && movesPerformed.size < GameSolver.moveLimit) {
      val move = solver.performMove()
      movesPerformed += move
    }

    if (solver.gameWon) {
      val msg = s"Won game [$rules] with seed [$seed] in [${movesPerformed.size}] moves."
      GameSeedService.onComplete(solver.getHistory(GameHistory.Status.Won)).map(_ => log.info(msg))
      msg
    } else {
      s"Unable to find a solution for [$rules] seed [$seed] in [${movesPerformed.size}] moves."
    }
  }
}
