package services;

import disc.Disc;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import song.Song;

public class Menu {
    /*Menu interface creation class*/
    Disc disc = new Disc();
    DiscTools discTools;

    public Menu() throws IOException {
        this.discTools = new DiscTools(disc, "src/main/resources/songsList.txt");
        this.printMenu();
        this.initDisc(discTools);
    }

    public void printMenu() throws IOException {
        /*Menu interface*/
        Boolean f = true;
        System.out.println("Welcome in my studio!\n" +
                "Here you can:\n" +
                "Record songs on cd - 1\n" +
                "Calculate general songs duration - 2\n" +
                "Sort contains on disc - 3\n" +
                "Find composition on your disc - 4\n" +
                "Remove composition from disc - 5\n" +
                "Look at disc contains - 6\n" +
                "Fill disc with existing compositions - 7\n" +
                "Exit from program - 0\n");

        for(; f; System.out.println()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("So what is your choice: ");
            Integer userChoice = scanner.nextInt();
            switch(userChoice) {
                case 0: f = false; break;
                case 1: addSongOnDisc(discTools); break;
                case 2: getFullDuration(discTools); break;
                case 3: sortContainsOnDisc(discTools); break;
                case 4: findCompositionOnDisc(discTools); break;
                case 5: removeCompositionFromDisc(discTools); break;
                case 6: showDiscContains(discTools); break;
                case 7: initDisc(discTools);
            }
        }

    }

    private void addSongOnDisc(DiscTools discTools) throws IOException {
        /*Records users song on disc*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write: <Name> <Author> <Duration> <Genre>");
        discTools.addSongOnDisc(scanner.next(), scanner.next(), scanner.nextFloat(), scanner.next());
        System.out.println("Successfully added");
    }

    private void getFullDuration(DiscTools discTools) {
        /*Prints general songs duration*/
        System.out.println("Full songs duration is: " + discTools.getFullDuration());
    }

    private void sortContainsOnDisc(DiscTools discTools) throws IOException {
        /*Sorts disc contains recording to users sorting option*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sort contains by name, author, duration or genre? :");
        FileFunctions fileFunctions = FileFunctions.getInstance();
        List<Song> sortedSongs = discTools.sortSongs(scanner.next());
        fileFunctions.writeInFile(discTools.getFilePath(), sortedSongs);
        discTools.printSongs(sortedSongs);
        System.out.println("Successfully sorted! Init the disc");
    }

    private void findCompositionOnDisc(DiscTools discTools) throws IOException {
        /*Finds composition from entered by user range*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write duration of your song: <from> <to>");
        List<Song> result = discTools.songsByDuration(scanner.nextInt(), scanner.nextInt());
        discTools.printSongs(result);
    }

    private void removeCompositionFromDisc(DiscTools discTools) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write name of composition you want to remove: ");
        discTools.removeSongFromDisc(scanner.next());
        System.out.println("Successful removed!");
    }

    private void showDiscContains(DiscTools discTools) throws IOException {
        discTools.printSongs(this.disc.getSongs());
    }

    private void initDisc(DiscTools discTools) throws IOException {
        /*Writes information from file on disc*/
        discTools.writeSongsOnDisc();
        System.out.println("Successfully inited!");
    }
}
