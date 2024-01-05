package main.madman.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameClass {
	List<String> choices = Stream.of("hajara", "wara9a", "mi9as").collect(Collectors.toList());
	List<Player> ansers = new ArrayList<>();
	int nbr = 11;

	String getrandom() {
		Random random = new Random();

		return choices.get(random.nextInt(choices.size()));

	}

	private List<Player> createPlayers(int nbr) {
		List<Player> rtn = new ArrayList<>();
		for (int i = 0; i < nbr; i++) {
			rtn.add(new Player(getrandom()));
		}
		this.ansers = rtn;
		return rtn;

	}

	public void play() {
int i=1;
		List<Player> list = createPlayers(nbr);
		while (!CheckPlayers(list)) {
			list = createPlayers(nbr);
			i++;
		}
		System.out.println("number of try : "+i);
	}

	// m9as => wr9a => 7ajra =>
	private boolean checkIfIsNull(List<Player> list) {
		if (list.size() == 0) {
			return true;
		}
		return false;
	}

	private boolean CheckPlayers(List<Player> list) {
//		"hajara", "wara9a", "mi9as"
		System.out.println("=======================================================================");
		if ((chercher(list, "hajara").size() > 0 && chercher(list, "wara9a").size() > 0
				&& chercher(list, "mi9as").size() > 0)
				|| (chercher(list, "hajara").size() == ansers.size() || chercher(list, "wara9a").size() == ansers.size()
						|| chercher(list, "mi9as").size() == ansers.size())) {
			System.out.println("No win No lose ! try again.");
			afficher(list);
			System.out.println("=======================================================================");
			return false;
		} else {
			// group plyers
			if (checkIfIsNull(chercher(list, "mi9as"))) {
				System.out.println("player win : " + chercher(list, "wara9a").toString());
				System.out.println("players lose : " + chercher(list, "hajara").toString());

			}
			if (checkIfIsNull(chercher(list, "wara9a"))) {
				System.out.println("player win : " + chercher(list, "hajara").toString() );
				System.out.println("players lose : " + chercher(list, "mi9as").toString());
			}
			if (checkIfIsNull(chercher(list, "hajara"))) {
				System.out.println("player win : " + chercher(list, "mi9as").toString() );
				System.out.println("players lose : " + chercher(list, "wara9a").toString());
			}

		}

		afficher(list);
		System.out.println("=======================================================================");
		return true;

	}

	private List<Player> chercher(List<Player> list, String choise) {
		return list.stream().filter(x -> x.choise == choise).collect(Collectors.toList());

	}

	public static String clavier() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

	private void afficher(List<Player> list) {
		List<Player> SortedList = new ArrayList<>();
		SortedList.addAll(chercher(list, "mi9as"));
		SortedList.addAll(chercher(list, "wara9a"));
		SortedList.addAll(chercher(list, "hajara"));
		SortedList.forEach(System.out::println);
	}
}
