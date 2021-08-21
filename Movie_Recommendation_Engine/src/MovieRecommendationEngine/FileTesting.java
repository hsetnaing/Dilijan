package MovieRecommendationEngine;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTesting {

    Path Movie;

    public void GetFile()  {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("moviedata"))) {
            for (Path file : stream) {
                Movie = file.getFileName();
                System.out.println(Movie);

            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ex);
        }

    }



    public static void main(String[] args) throws IOException {

        FileTesting FileTester = new FileTesting(); //as soon as we start the engine, we go through the file and record everyone's ID and ratings
        FileTester.GetFile();




    }
}

