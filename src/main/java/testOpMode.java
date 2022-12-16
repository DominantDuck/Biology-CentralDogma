import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.RNASequence;
import org.biojava.nbio.core.sequence.template.SequenceView;
import java.util.Scanner;

import javax.sound.midi.Sequence;
//import org.biojava.bio.symbol.*;
//import org.biojava.bio.seq.*;



public class testOpMode {


    public static void main(String[] args) throws CompoundNotFoundException {
        RNASequence rnaSeq;
        ProteinSequence proSeq;


        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter DNA sequence: ");
        String userInput = myObj.nextLine();

        boolean errorPresent = false;

        for (int i = 0; i < userInput.length(); i++) {
            char x = ' ';

            x = userInput.charAt(i);
            x = Character.toLowerCase(x);

            if (x != 'g' && x != 't'&& x != 'a' && x != 'c') {
               errorPresent = true;
            }
        }

        if (errorPresent) {
            System.out.println("Error"); //add more bio vocab describing error
        }

        DNASequence seq = new DNASequence(userInput);

        rnaSeq = seq.getRNASequence();
        String printmRNA = rnaSeq.getSequenceAsString();
        printmRNA = printmRNA.toUpperCase();

        System.out.println("mRNA: " + printmRNA);

        proSeq = rnaSeq.getProteinSequence();
        String printPro = proSeq.getSequenceAsString();
        printPro = printPro.toUpperCase();


        System.out.println("Protein: " + printPro);


    }
}
