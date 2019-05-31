package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import song.Song;

public class FileServices {
    public BufferedReader buffReader;

    public void openFile (String filePath) throws IOException {
        buffReader = new BufferedReader(new FileReader(filePath));
    }

    public String readFromFile (String filePath) throws IOException {
        if (!buffReader.ready()) openFile(filePath);
        String buff = this.buffReader.readLine();
        return buff != null ? buff : "";
    }

    public void closeFile () throws IOException {
        buffReader.close();
    }

    /*Write in file method*/
    public void writeInFile (String filePath, List < Song > songs) throws IOException {
        FileWriter fr = new FileWriter(new File(filePath), false);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);
        for (Song song : songs) {
            pr.println(song.getName() + "-" + song.getAuthor() + "-" +
                    song.getDuration() + "-" + song.getGenre() + "-");
        }
        pr.close();
        br.close();
        fr.close();
    }
}
