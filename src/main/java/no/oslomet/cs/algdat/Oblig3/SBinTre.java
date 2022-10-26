package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;


public class SBinTre<T> {

    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<> (verdi,null,null,q);                   // oppretter en ny node

        if (q == null) {
            rot = p;                  // p blir rotnode
        }
        else if (cmp < 0) {
            q.venstre = p;         // venstre barn til q
        }
        else {
            q.høyre = p;                        // høyre barn til q
        }
        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        if(tom() == true || inneholder(verdi) == false ) // sjekker om treet er tomt eller ikke inneholder verdien
            return 0;

            Node <T> p = rot;
            int antallVerdi = 0;
            while (p != null) { // Itererer treet
                int cmp = comp.compare(verdi, p.verdi);
                if (cmp < 0) p = p.venstre;          // verdi er mindre enn p, går til venstre
                else if (cmp > 0) p = p.høyre;       // verdi er større enn p, går til høyre
                else                                // om verdien er funnet øker vi antallVerdi og går til høyre i treet
                {
                    antallVerdi++;
                    p = p.høyre;
                }
        }
            return antallVerdi;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        while(true) // Vil finne første uten venstre eller høyre barn
        {
            if(p.venstre != null) // Sjekker for venstre barn
                p = p.venstre;
            else if (p.høyre != null) // sjekker for høyre barn
                p = p.høyre;
            else return p; // P er førstePostorden da den verken har høyre eller venstre barn.
        }

    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        if(p.forelder == null) // p er rotnode og da sist i postorden
            return null;

        Node <T> f = p.forelder; // Testnode for forelder
        if(f.høyre == p) // P er høyre barn til forelder, forelder er neste
            return f;
        else if(f.høyre == null)// P er enebarn, forelder er neste
            return f;
        else // P er ikke enebarn, første i postorden med forelder som rot er neste
            return førstePostorden(f.høyre);



    }

    public void postorden(Oppgave<? super T> oppgave) {

        Node <T> p = førstePostorden(rot);
        oppgave.utførOppgave(p.verdi);
        while(p!= null) {
            p = nestePostorden(p);
            oppgave.utførOppgave(p.verdi);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        while(p != null)
        {
            oppgave.utførOppgave(p.verdi);
            postordenRecursive(nestePostorden(p), oppgave);
        }
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

} // ObligSBinTre
