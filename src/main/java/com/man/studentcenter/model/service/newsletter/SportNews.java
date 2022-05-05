package com.man.studentcenter.model.service.newsletter;


public class SportNews extends AbstractNewsletter{
    private static SportNews instance;

    private SportNews(){
        name = "Sport News";
    }

    public static SportNews getInstance(){
        if(instance == null){
            instance = new SportNews();
        }
        return instance;
    }



    @Override
    public void notifySubscribers() {
        for (Observer subscriber : subscribers) {
            setNews("Sport News Latest News!!!");
            subscriber.update();
        }
    }
}
