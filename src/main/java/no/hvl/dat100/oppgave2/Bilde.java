package no.hvl.dat100.oppgave2;

public class Bilde extends Tekst {

    private String url;

    public Bilde() {
    }

    public Bilde(int id, String bruker, String dato, String tekst, String url) {
        super(id, bruker, dato, tekst);
        this.url = url;
    }

    public Bilde(int id, String bruker, String dato, int likes, String tekst, String url) {
        super(id, bruker, dato, likes, tekst);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        // super.toString() starter med "TEKST\n..."
        String t = super.toString();
        return "BILDE\n" + t.substring("TEKST\n".length()) + url + "\n";
    }

    @Override
    public String toHTML() {
        return super.toHTML()
                + "<iframe src=\"" + (url == null ? "" : url)
                + "\" height=600 width=800></iframe>";
    }
}
