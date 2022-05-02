package com.man.studentcenter.model.service.newsletter;


public class TheCareersNewsletter extends AbstractNewsletter{
    private static TheCareersNewsletter instance;

    private TheCareersNewsletter(){
        name = "The Careers News";
    }

    public static TheCareersNewsletter getInstance(){
        if(instance == null){
            instance = new TheCareersNewsletter();
        }
        return instance;
    }



    @Override
    public void notifySubscribers() {
        for (Observer subscriber : subscribers) {
            setNews("The Careers News Latest News!!!");
            subscriber.update();
        }
    }
}
