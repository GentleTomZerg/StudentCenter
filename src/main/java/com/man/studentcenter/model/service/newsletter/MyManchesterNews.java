package com.man.studentcenter.model.service.newsletter;


public class MyManchesterNews extends AbstractNewsletter{
    private static MyManchesterNews instance;

    private MyManchesterNews(){
        name = "My Manchester News";
    };

    public static MyManchesterNews getInstance(){
        if(instance == null){
            instance = new MyManchesterNews();
        }
        return instance;
    }



    @Override
    public void notifySubscribers() {
        for (Observer subscriber : subscribers) {
            setNews("Manchester Latest News!!!");
            subscriber.update();
        }
    }
}
