package com.javatrade.SpringtradeBot.main;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("main")
public class Main {
	@Autowired
	private Start start;

	public void start() {
		Timer timer = new Timer();
		TimerTask task = start;
		timer.schedule(task, 0, 50000);
	}
}
