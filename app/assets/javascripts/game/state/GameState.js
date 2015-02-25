define(function () {
  "use strict";

  function GameState(game) {
    Phaser.State.call(this, game);
  }

  GameState.prototype = Object.create(Phaser.State.prototype);
  GameState.prototype.constructor = GameState;

  GameState.prototype.create = function() {
    console.log(this.constructor.name + " created.");
  };

  GameState.prototype.update = function() {
    this.game.statusPanel.setFps(this.game.time.fps);
  };

  GameState.prototype.onMessage = function(c, v) {
    switch(c) {
      case "MessageSet":
        for(var messageIndex in v.messages.reverse()) {
          var message = v.messages[messageIndex];
          this.onMessage(message.c, message.v);
        }
        break;
      default:
        console.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
    }
  };

  GameState.prototype.resize = function(c, v) {
    if(this.game.statusPanel !== undefined) {
      this.game.statusPanel.resize();
    }
  };

  return GameState;
});
