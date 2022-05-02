package com.man.studentcenter.model.service.newsletter;


public class Stellify extends AbstractNewsletter{
    private static Stellify instance;

    private Stellify(){
        name = "Stellify";
    }

    public static Stellify getInstance(){
        if(instance == null){
            instance = new Stellify();
        }
        return instance;
    }



    @Override
    public void notifySubscribers() {
        for (Observer subscriber : subscribers) {
            setNews("Stellify Latest News!!!");
            subscriber.update();
        }
    }
}
