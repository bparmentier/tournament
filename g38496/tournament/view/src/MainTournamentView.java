package g38496.tournament.view;

import g38496.tournament.business.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * User interface of the application.
 * 
 * @author g38496
 */
public class MainTournamentView {

    /**
     * Application entry point.
     * @param args array of command-line arguments. Not used here.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainTournament mainTournament = new MainTournament();
        String command;

        System.out.println("Project Tournament v2");
        System.out.println("Type \"help\" for a list of commands.\n");

        System.out.print("> ");
        command = scanner.nextLine();

        while (!command.equals("exit") && !command.equals("quit")) {

            /* Show help */
            if (command.equals("help")) {
                showHelp();

            /* Open inscriptions */
            } else if (command.equals("open")) { // XXX
                if (!mainTournament.isInscriptionsOpen()) {
                    mainTournament.openInscription();
                } //else if (
                if (mainTournament.isInscriptionsOpen()) {
                    System.out.println("Registrations are now open.");
                } else {
                    System.out.println("Registrations were not opened.");
                }

            /* Close inscriptions */
            } else if (command.equals("close")) {
                mainTournament.closeInscription();
                if (!mainTournament.isInscriptionsOpen()) {
                    System.out.println("Registrations are now closed.");
                } else {
                    System.out.println("Registrations were not closed.");
                }

            /* Show inscriptions status */
            } else if (command.equals("status")) {
                if (mainTournament.isInscriptionsOpen()) {
                    System.out.println("Registrations are open.");
                } else {
                    System.out.println("Registrations are closed.");
                }

            /* Add player */
            } else if (command.equals("add")) {
                boolean isAdded;
                Player player;

                System.out.print("Type the name of the player: ");
                player = new Player(scanner.nextLine());
                isAdded = mainTournament.addPlayer(player);

                if (isAdded) {
                    System.out.println("Player was successfully added.");
                } else {
                    System.out.println("Player could not have been added, "
                            + "make sure registrations are open.");
                }

            /* Remove player */
            } else if (command.equals("remove")) {
                boolean isRemoved = false;
                Player player;
                int playerId;
                ArrayList<Player> playersList = mainTournament.getPlayers();

                try {
                    System.out.print("Type the id of the player: ");
                    playerId = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < playersList.size(); i++) {
                        player = playersList.get(i);
                        if (player.getId() == playerId) {
                            isRemoved = mainTournament.removePlayer(player);
                        }
                    }

                    if (isRemoved) {
                        System.out.println("Player was successfully removed.");
                    } else {
                        System.out.println("Player could not have been removed, "
                                + "make sure registrations are open.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please enter a valid id.");
                }

            /* Show free places number */
            } else if (command.equals("freeplaces")) {
                System.out.println("Free places: "
                        + mainTournament.getFreePlacesNumber());

            /* Show players list */
            } else if (command.equals("players")) {
                System.out.println(mainTournament.getPlayers());

            /* Show list of all matchs */
            } else if (command.equals("matches")) {
                if (mainTournament.isInscriptionsOpen()) {
                    System.out.println("Matches are not generated yet.");
                } else {
                    System.out.println(mainTournament.getMatchs());
                }

            /* Show list of unplayed matchs */
            } else if (command.equals("toplay")) {
                if (mainTournament.isInscriptionsOpen()) {
                    System.out.println("Matches are not generated yet.");
                } else {
                    System.out.println(mainTournament.getMatchsToPlay());
                }

            /* Show list of already played matchs */
            } else if (command.equals("done")) {
                if (mainTournament.isInscriptionsOpen()) {
                    System.out.println("Matches are not generated yet.");
                } else {
                    System.out.println(mainTournament.getMatchsDone());
                }

            /* Set match result */
            } else if (command.equals("setresult")) {
                int id;
                String result;
                ArrayList<Player> playersList = mainTournament.getPlayers();
                Player player;
                String playerName;

                try {
                    System.out.print("Enter match id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter result (\"PLAYER1\" or"
                            + " \"PLAYER2\"): ");
                    result = scanner.nextLine();
                    if (result.equalsIgnoreCase("DRAW")) {
                        mainTournament.setTurnResult(id, ResultEnum.DRAW);
                        System.out.println("Result set to \"DRAW\".");
                    } else if (result.equalsIgnoreCase("NOT_PLAYED")) {
                        mainTournament.setTurnResult(id, ResultEnum.NOT_PLAYED);
                        System.out.println("Result set to \"NOT_PLAYED\".");
                    } else if (result.equalsIgnoreCase("PLAYER1")) {
                        mainTournament.setTurnResult(id, ResultEnum.PLAYER1);
                        System.out.println("Winner set to \"PLAYER1\".");
                    } else if (result.equalsIgnoreCase("PLAYER2")) {
                        mainTournament.setTurnResult(id, ResultEnum.PLAYER2);
                        System.out.println("Winner set to \"PLAYER2\".");
                    } else {
                        System.out.println("Please enter a valid result");
                    }
                } catch (TournamentException e) {
                    System.out.println(e.getMessage());
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please enter a valid id.");
                } catch (java.lang.NullPointerException e) {
                    System.out.println("Please enter an existing id.");
                }

            /* show ranking */
            } else if (command.equals("ranking")) {
                System.out.println(mainTournament.getRanking());

            /* show winner */
            } else if (command.equals("winner")) {
                try {
                    System.out.println(mainTournament.getWinner());
                } catch (TournamentException e) {
                    System.out.println(e.getMessage());
                }

            /* Invalid command */
            } else {
                System.out.println("Invalid command");
            }

            System.out.print("> ");
            command = scanner.nextLine();
        }
    }

    private static void showHelp() {
        System.out.println("List of commands:");
        System.out.println("help\t\tshow this help\n"
                + "open\t\topen registrations\n"
                + "close\t\tclose registrations\n"
                + "status\t\tshow registrations status\n"
                + "add\t\tadd a player\n"
                + "remove\t\tremove a player\n"
                + "players\t\tshow players list\n"
                + "freeplaces\tshow free places number\n"
                + "matches\t\tshow list of all matches\n"
                + "toplay\t\tshow list of unplayed matches\n"
                + "done\t\tshow list of already played matches\n"
                + "setresult\tset match result\n"
                + "ranking\t\tshow ranking\n"
                + "winner\t\tshow winner\n"
                + "exit\t\texit the program");
    }
}
