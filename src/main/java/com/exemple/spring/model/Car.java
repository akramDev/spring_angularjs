package com.exemple.spring.model;

public class Car {
	
	private Engine engine;
	
	private String warnMessage;
	
	public Car(Engine engine) {
		this.engine = engine;
	}
	
	public void accelerate(){
		engine.increaseRpm();
		
		if(engine.getRpm() > 5000 ){
			this.warnMessage = "slow down!";
		}
	}

	public String getWarnMessage() {
		return warnMessage;
	}

	public void setWarnMessage(String warnMessage) {
		this.warnMessage = warnMessage;
	}
	
}
