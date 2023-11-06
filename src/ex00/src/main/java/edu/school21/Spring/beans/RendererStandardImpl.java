package edu.school21.Spring.beans;

public class RendererStandardImpl implements Renderer{
    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void rendering(String str) {
        System.out.println(preProcessor.preProcess(str));
    }
}
