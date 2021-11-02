package ru.vsu.csf;

import java.awt.*;

public class Ball {

    public final Color color;

    public Ball(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "color=" + color +
                '}';
    }
}