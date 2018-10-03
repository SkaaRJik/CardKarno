package cardKarno;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardKarnoTest {
    public CardKarno cardKarno;
    @Test
    public void karnoTableBuilder() {
        this.cardKarno = new CardKarno();

        int size = 2;

        int[][] truthTable = new int[(int) Math.pow(2,size)][size+1];
        for (int i = 0; i < truthTable.length; i++) {
            int tmp = i;
            for (int j = truthTable[i].length-2; j >=0 ; j--) {
                truthTable[i][j] = tmp & 1;
                tmp >>= 1;
            }
        }

        for (int i = 0; i < truthTable.length; i++) {
            for (int j = 0; j < truthTable[i].length; j++) {
                if(j == truthTable[i].length) {
                    truthTable[i][j] = i % 2;
                }

                System.out.printf("%d | ", truthTable[i][j]);
            }
            System.out.println("\n");
        }
        cardKarno.karnoTableBuilder(truthTable);


    }
}