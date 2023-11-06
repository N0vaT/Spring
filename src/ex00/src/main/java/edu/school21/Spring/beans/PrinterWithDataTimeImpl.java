package edu.school21.Spring.beans;

import java.time.LocalDateTime;

public class PrinterWithDataTimeImpl implements Printer{
    private final Renderer renderer;

    public PrinterWithDataTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void print(String str) {
        renderer.rendering(LocalDateTime.now().toString());
    }
}
