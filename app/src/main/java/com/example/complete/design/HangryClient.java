package com.example.complete.design;

public class HangryClient {

    public void eat() {
        new BuyVegetable().buy();
        new WashVegetable().wash();
        new FryVegetable().fry();
        new EatVegetable().eat();
    }

}
