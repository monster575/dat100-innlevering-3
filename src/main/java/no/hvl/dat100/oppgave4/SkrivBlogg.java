package no.hvl.dat100.oppgave4;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import no.hvl.dat100.oppgave3.Blogg;

public class SkrivBlogg {

    public static boolean skriv(Blogg samling, String mappe, String filnavn) {
        try {
            Path dir = (mappe == null || mappe.isEmpty()) ? Path.of(".") : Path.of(mappe);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Path fil = dir.resolve(filnavn);
            try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(fil))) {
                out.print(samling.toString());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
