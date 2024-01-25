package main.madman.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameClass {
	List<String> choices = Stream.of("hajara", "wara9a", "mi9as", "hajara", "wara9a", "mi9as", "hajara", "wara9a",
			"mi9as", "hajara", "wara9a", "mi9as").collect(Collectors.toList());
	List<Player> ansers = new ArrayList<>();
	int nbr =20;
	// nbr must less that 20

	String getrandom() {
		Random random = new Random();

		return choices.get(random.nextInt(choices.size()));

	}

	private List<Player> CreateOrupdatePlayersChoise(int nbr) {
		List<Player> rtn = new ArrayList<>();
		for (int i = 0; i < nbr; i++) {
			rtn.add(new Player(getrandom()));
		}
		this.ansers = rtn;
		return rtn;

	}

	private List<Player> CreateOrupdatePlayersChoise(List<Player> players) {
		List<Player> rtn = players;
		for (Player player : rtn) {
			player.choise = getrandom();
		}
		return rtn;
	}

	public void play() {
		long startTime = System.nanoTime();

		int i = 1;
		List<Player> list = CreateOrupdatePlayersChoise(nbr);

		while (!CheckPlayers(list)) {
			list = CreateOrupdatePlayersChoise(list);
			i++;
		}
		System.out.println("number of try : " + i);

		long endTime = System.nanoTime();

		long duration = (endTime - startTime); // divide by 1000000 to get milliseconds
		System.out.println("duration  of exucution   : " + duration / 1000000 + " milliseconds ");
		System.out.println("duration  of exucution   : " + (float) duration / 1000000000 + " seconds ");
	}

	// m9as => wr9a => 7ajra =>
	private boolean checkIfIsNull(List<Player> list) {
		if (list.size() == 0) {
			return true;
		}
		return false;
	}

	private boolean CheckPlayers(List<Player> list) {
		List<Player> hajaraPlayers = chercher(list, "hajara");
		List<Player> wara9aPlayers = chercher(list, "wara9a");
		List<Player> mi9asPlayers = chercher(list, "mi9as");

		// "hajara", "wara9a", "mi9as"
		System.out.println("=======================================================================");
		if ((hajaraPlayers.size() > 0 && wara9aPlayers.size() > 0 && mi9asPlayers.size() > 0)
				|| (hajaraPlayers.size() == ansers.size() || wara9aPlayers.size() == ansers.size()
						|| mi9asPlayers.size() == ansers.size())) {
			System.out.println("No win No lose ! try again.");
			afficher(list);
			System.out.println("=======================================================================");
			return false;
		} else {
			// group plyers
			if (checkIfIsNull(mi9asPlayers)) {
				System.out.println("player win : " + wara9aPlayers.toString());
				System.out.println("players lose : " + hajaraPlayers.toString());

			}
			if (checkIfIsNull(wara9aPlayers)) {
				System.out.println("player win : " + hajaraPlayers.toString());
				System.out.println("players lose : " + mi9asPlayers.toString());
			}
			if (checkIfIsNull(hajaraPlayers)) {
				System.out.println("player win : " + mi9asPlayers.toString());
				System.out.println("players lose : " + wara9aPlayers.toString());
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
