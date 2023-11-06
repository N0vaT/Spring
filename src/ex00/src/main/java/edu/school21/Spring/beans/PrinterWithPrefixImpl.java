package edu.school21.Spring.beans;

public class PrinterWithPrefixImpl implements Printer{
    private String prefix = "";
    private final Renderer renderer;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String str) {
        if(prefix.equals("")){
            renderer.rendering(str);
        }else{
            renderer.rendering(prefix + " " + str);
        }
    }
}
