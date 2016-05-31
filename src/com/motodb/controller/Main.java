package com.motodb.controller;

import com.motodb.view.GUI;

public class Main {

	private final GUI firstFrame;

	public Main() {
		
		
		this.firstFrame = new GUI();
		this.firstFrame.launcher(new String[] {});
	}

	public static void main(String[] args) {
		new Main();
	}
}
