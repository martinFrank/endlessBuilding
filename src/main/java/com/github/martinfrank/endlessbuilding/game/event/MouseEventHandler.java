package com.github.martinfrank.endlessbuilding.game.event;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.game.GameEvent;
import com.github.martinfrank.endlessbuilding.gui.MouseSelection;

public abstract class MouseEventHandler {

    public final Game game;

    public MouseEventHandler(Game game) {
        this.game = game;
    }

    public abstract GameEvent handle(MouseSelection selection);
}
