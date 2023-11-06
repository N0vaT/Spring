package edu.school21.Spring.beans;

public class RendererErrImpl implements Renderer{
    private final PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void rendering(String str){
        System.err.println(preProcessor.preProcess(str));
    }
}
