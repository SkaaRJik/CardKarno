package cardKarno;

public class CardKarno {

    public void karnoTableBuilder(int[][] truthTable){
        int rows = truthTable.length;
        int columns = truthTable[0].length;
        int temp = (int) Math.floor((columns-1)/2);
        int[][] zippedTruthTable = new int[temp*2][(columns-1-temp)*2];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

            }
        }
    }

}
