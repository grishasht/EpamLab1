package services;

import disc.Disc;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import song.Song;

/*Menu interface creation class*/
public class Menu {
    Disc disc = new Disc();
    DiscTools discTools;

    /*Menu interface*/
    public void printMenu() throws IOException {
        System.out.println(new StringBuilder()
                .append("Welcome in my studio!\n")
                .append("Here you can:\n")
                .append("Record songs on cd - 1\n")
                .append("Calculate general songs duration - 2\n")
                .append("Sort contains on disc - 3\n")
                .append("Find composition on your disc - 4\n")
                .append("Remove composition from disc - 5\n")
                .append("Look at disc contains - 6\n")
                .append("Fill disc with existing compositions - 7\n")
                .append("Exit from program - 0\n"));

        Boolean runProgram = true;
        for (; runProgram; System.out.println()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("So what is your choice: ");
            Integer userChoice = scanner.nextInt();
            switch (userChoice) {
                case 0:
                    runProgram = false;
                    break;
                case 1:
                    addSongOnDisc(discTools);
                    break;
                case 2:
                    getFullDuration(discTools);
                    break;
                case 3:
                    sortContainsOnDisc(discTools);
                    break;
                case 4:
                    findCompositionOnDisc(discTools);
                    break;
                case 5:
                    removeCompositionFromDisc(discTools);
                    break;
                case 6:
                    showDiscContains(discTools);
                    break;
                case 7:
                    initDisc(discTools);
            }
        }

    }

    /*Records users song on disc*/
    private void addSongOnDisc(DiscTools discTools) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write: <Name> <Author> <Duration> <Genre>");
        discTools.addSongOnDisc(scanner.next(), scanner.next(), scanner.nextFloat(), scanner.next());
        System.out.println("Successfully added");
    }

    /*Prints general songs duration*/
    private void getFullDuration(DiscTools discTools) {
        System.out.println("Full songs duration is: " + discTools.getFullDuration());
    }

    /*Sorts disc contains recording to users sorting option*/
    private void sortContainsOnDisc(DiscTools discTools) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sort contains by name, author, duration or genre? :");
        FileFunctions fileFunctions = FileFunctions.getInstance();
        List<Song> sortedSongs = discTools.sortSongs(scanner.next());
        fileFunctions.writeInFile(discTools.getFilePath(), sortedSongs);
        discTools.printSongs(sortedSongs);
        System.out.println("Successfully sorted! Init the disc");
    }

    /*Finds composition from entered by user range*/
    private void findCompositionOnDisc(DiscTools discTools) throws IOException {
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

    /*Writes information from file on disc*/
    private void initDisc(DiscTools discTools) throws IOException {
        discTools.writeSongsOnDisc();
        System.out.println("Successfully inited!");
    }
}
