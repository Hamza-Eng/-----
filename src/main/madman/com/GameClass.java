package main.madman.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameClass {
	List<String> choices = Stream.of("hajara", "wara9a", "mi9as").collect(Collectors.toList());
	List<Player> ansers = Stream
			.of(new Player(getrandom()), new Player(getrandom()), new Player(getrandom()), new Player(getrandom()))
			.collect(Collectors.toList());

	String getrandom() {
		Random random = new Random();
//		return null;
		return choices.get(random.nextInt(choices.size()));

	}

	public void play() {

//	SortedPlayer(ansers).forEach(System.out::println);
		CheckPlayers(ansers);
	}

	// m9as => wr9a => 7ajra =>
	private boolean checkIfIsNull(List<Player> list) {
		if (list.size() == 0) {
			return true;
		}
		return false;
	}

	private void CheckPlayers(List<Player> list) {
//		"hajara", "wara9a", "mi9as"
		if ((chercher(list, "hajara").size() > 0 && chercher(list, "wara9a").size() > 0
				&& chercher(list, "mi9as").size() > 0)
				|| (chercher(list, "hajara").size() == ansers.size() || chercher(list, "wara9a").size() == ansers.size()
						|| chercher(list, "mi9as").size() == ansers.size())) {
			System.out.println("No win No lose ! try again.");
		} else {
			// group plyers
//			checkIfIsNull(chercher(list, "hajara"));
//			checkIfIsNull(chercher(list, "mi9as"));
//			checkIfIsNull(chercher(list, "wara9a"));
			if (checkIfIsNull(chercher(list, "mi9as"))) {
				System.out.println("player win : " + chercher(list, "wara9a").toString() + "players lose : "
						+ chercher(list, "hajara").toString());

			}
			if (checkIfIsNull(chercher(list, "wara9a"))) {
				System.out.println("player win : " + chercher(list, "hajara").toString() + "players lose : "
						+ chercher(list, "mi9as").toString());

			}
			if (checkIfIsNull(chercher(list, "hajara"))) {
				System.out.println("player win : " + chercher(list, "mi9as").toString() + "players lose : "
						+ chercher(list, "wara9a").toString());

			}
//			nullpart
		}

		List<Player> SortedList = new ArrayList<>();
		SortedList.addAll(chercher(list, "mi9as"));
		SortedList.addAll(chercher(list, "wara9a"));
		SortedList.addAll(chercher(list, "hajara"));
System.out.println();
		SortedList.forEach(System.out::println);
		;
	}

	private List<Player> chercher(List<Player> list, String choise) {
		return list.stream().filter(x -> x.choise == choise).collect(Collectors.toList());

	}

	public static String clavier() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

	private void afficher() {

	}
}
