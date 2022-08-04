package com.store.app.bl.storage;

import com.store.app.bl.Connectable;
import com.store.app.bl.Response;


public abstract class AbstractDispatcher implements Connectable {

    private boolean process = false;
    protected enum Steps {DEFAULT, COMMAND, EXECUTE}
    private Steps currentStep = Steps.DEFAULT;

    protected abstract String defaultStep(String s);

    protected abstract String command(String request);

    protected abstract String execute(String request);


    @Override
    public Response connect(String request) {

        String result = "";

        if (Steps.DEFAULT == currentStep) {

            result = this.defaultStep("");
        }else if (Steps.COMMAND == currentStep) {

            result = this.command(request);
        }else if (Steps.EXECUTE == currentStep) {

            result = this.execute(request);
        }

        return this.response(result);
    }

    private Response response(String response){
        return (new Response(this.process, response));
    }

    public boolean isProcess() {
        return process;
    }

    public void setProcess(boolean process) {
        this.process = process;
    }

    public Steps getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Steps currentStep) {
        this.currentStep = currentStep;
    }

    protected void start(){
        this.process = true;
        this.currentStep = Steps.COMMAND;
    }

    protected void finish(){
        this.process = false;
        this.currentStep = Steps.DEFAULT;
    }
}
