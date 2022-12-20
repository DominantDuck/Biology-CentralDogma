import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.RNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.CompoundSet;
import org.biojava.nbio.core.sequence.template.SequenceView;
import org.biojava.nbio.core.sequence.transcription.RNAToAminoAcidTranslator;

import java.util.List;
import java.util.Scanner;

import javax.sound.midi.Sequence;
//import org.biojava.bio.symbol.*;
//import org.biojava.bio.seq.*;



public class testOpMode {


    public static void main(String[] args) throws CompoundNotFoundException {
        RNASequence rnaSeqFromInput;
        ProteinSequence proSeq;


        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter DNA sequence: ");
        String userInput = myObj.nextLine();
        userInput = userInput.replaceAll("\\s", "");

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

        DNASequence seq = new DNASequence(userInput.toUpperCase());

        RNASequence antisenseStrand = seq.getRNASequence();

        String mRNA = antisenseStrand.toString();

        String printmRNA = mRNA;
        printmRNA = printmRNA.toUpperCase();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < printmRNA.length(); i++) {
            sb.append(printmRNA.charAt(i));
            if (i % 3 == 2 && i < printmRNA.length() - 1) {
                sb.append(" ");
            }
        }

        printmRNA = sb.toString();

        System.out.println("mRNA: " + printmRNA);

        SequenceView tRNA = antisenseStrand.getComplement();
        String tRNAAsString = tRNA.getSequenceAsString();
        System.out.println("tRNA: " + tRNAAsString);


        proSeq = antisenseStrand.getProteinSequence();
        String printPro = proSeq.getSequenceAsString();
        printPro = printPro.toUpperCase();


        String[][] aminoAcids = {
                {"A", "Ala"},
                {"R", "Arg"},
                {"N", "Asn"},
                {"D", "Asp"},
                {"C", "Cys"},
                {"E", "Glu"},
                {"Q", "Gln"},
                {"G", "Gly"},
                {"H", "His"},
                {"I", "Ile"},
                {"L", "Leu"},
                {"K", "Lys"},
                {"M", "Met"},
                {"F", "Phe"},
                {"P", "Pro"},
                {"S", "Ser"},
                {"T", "Thr"},
                {"W", "Trp"},
                {"Y", "Tyr"},
                {"V", "Val"}
        };

        StringBuilder proteinBuilder = new StringBuilder();
        boolean stopFound = false;

        for (int i = 0; i < printPro.length(); i++) {
            char singleLetterName = printPro.charAt(i);
            for (int j = 0; j < aminoAcids.length; j++) {
                if (aminoAcids[j][0].charAt(0) == singleLetterName) {
                    proteinBuilder.append(aminoAcids[j][1]);
                    proteinBuilder.append(" ");
                    break;
                }
                if (printPro.charAt(i) == '*') {
                    proteinBuilder.append("STOP");
                    proteinBuilder.append(" ");
                    stopFound = true;
                    break;
                }
            }
            if (stopFound == true) {
                break;
            }
        }

        String result = proteinBuilder.toString();

        System.out.println("Amino Acid: " + result);



    }
}
