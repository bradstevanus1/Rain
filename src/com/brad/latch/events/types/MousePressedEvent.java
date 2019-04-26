package com.brad.latch.events.types;

import com.brad.latch.events.Event;

public class MousePressedEvent extends MouseButtonEvent {

    public MousePressedEvent(int button, int x, int y) {
        super(button, x, y, Type.MOUSE_PRESSED);
    }

}
