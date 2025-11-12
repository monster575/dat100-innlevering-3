package no.hvl.dat100.oppgave3;

import no.hvl.dat100.oppgave1.Innlegg;
import no.hvl.dat100.oppgave2.Tekst;

public class Blogg {

    private Innlegg[] innleggtabell;
    private int nesteledig;

    public Blogg() {
        this(20);
    }

    public Blogg(int lengde) {
        innleggtabell = new Innlegg[Math.max(0, lengde)];
        nesteledig = 0;
    }

    public int getAntall() {
        return nesteledig;
    }

    public Innlegg[] getSamling() {
        return innleggtabell;
    }

    public int finnInnlegg(Innlegg innlegg) {
        for (int i = 0; i < nesteledig; i++) {
            if (innleggtabell[i].erLik(innlegg)) {
                return i;
            }
        }
        return -1;
    }

    public boolean finnes(Innlegg innlegg) {
        return finnInnlegg(innlegg) != -1;
    }

    public boolean ledigPlass() {
        return nesteledig < innleggtabell.length;
    }

    public boolean leggTil(Innlegg innlegg) {
        if (finnes(innlegg) || !ledigPlass()) return false;
        innleggtabell[nesteledig++] = innlegg;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nesteledig).append("\n");
        for (int i = 0; i < nesteledig; i++) {
            sb.append(innleggtabell[i].toString());
        }
        return sb.toString();
    }

    public void utvid() {
        Innlegg[] ny = new Innlegg[Math.max(1, innleggtabell.length * 2)];
        System.arraycopy(innleggtabell, 0, ny, 0, nesteledig);
        innleggtabell = ny;
    }

    public boolean leggTilUtvid(Innlegg innlegg) {
        if (finnes(innlegg)) return false;
        if (!ledigPlass()) utvid();
        innleggtabell[nesteledig++] = innlegg;
        return true;
    }

    public boolean slett(Innlegg innlegg) {
        int i = finnInnlegg(innlegg);
        if (i == -1) return false;
        innleggtabell[i] = innleggtabell[nesteledig - 1];
        innleggtabell[nesteledig - 1] = null;
        nesteledig--;
        return true;
    }

    public int[] search(String user, String ord) {
        int count = 0;
        for (int i = 0; i < nesteledig; i++) {
            if (match(innleggtabell[i], user, ord)) count++;
        }
        int[] ids = new int[count];
        int k = 0;
        for (int i = 0; i < nesteledig; i++) {
            if (match(innleggtabell[i], user, ord)) ids[k++] = innleggtabell[i].getId();
        }
        return ids;
    }

    private boolean match(Innlegg in, String user, String ord) {
        if (user != null && !user.isEmpty() && !user.equals(in.getBruker())) return false;
        if (ord == null || ord.isEmpty()) return true;
        if (in instanceof Tekst) {
            String t = ((Tekst) in).getTekst();
            return t != null && t.contains(ord);
        }
        return false;
    }
}
