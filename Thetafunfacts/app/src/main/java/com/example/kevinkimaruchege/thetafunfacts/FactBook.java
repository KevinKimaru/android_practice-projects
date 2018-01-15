package com.example.kevinkimaruchege.thetafunfacts;

import java.util.Random;

public class FactBook {
    //Fields(member variables) - Properties about the object
    private String[] mFacts = {
            "Ants Stretch when they wake up in the morning.",
            "Ostritches can run faster than horses.",
            "Olympic gold medals are actually made mostly of silver.",
            "You were born with 300 bones, by the time you are an adult you will have 206.",
            "Ii takes about 8 minutes for light from the sun to reach Earth.",
            "Some bamboo plants can grow almost a meter in one day.",
            "The state of Florida is bigger than London.",
            "Some penguins can leap 2-3 metres out of water.",
            "On average, it takes 66 days to form  new habit.",
            "Mammoths still walked the earth when the great pyramid was built."
    };
    //Methods - actions the object can take

    public String getFacts(){
       
        String fact = "";
        //Randomly select a fact
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mFacts.length);
        fact = mFacts[randomNumber];

        return fact;
    }

}
