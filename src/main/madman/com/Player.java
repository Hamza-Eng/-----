package main.madman.com;

import java.util.UUID;

public class Player {
	String index;
	String choise;

	public Player(String choise) {
		this.choise = choise;
		index = UUID.randomUUID().toString();
	}

	@Override
	public String toString() {
		return "Player [ID=" + index.substring(2) + ", choise=" + choise + "]";
	}

}
