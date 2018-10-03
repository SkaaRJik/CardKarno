package cardKarno;

public class CardKarno {

    int[][] zippedTruthTable;

    public void karnoTableBuilder(int[][] truthTable){
        int rows = truthTable.length;
        int columns = truthTable[0].length;
        int temp = (int) Math.floor((columns-1)/2);
        zippedTruthTable = new int[temp*2][(columns-1-temp)*2];
        int l = 0;
        for (int i = 0; i < zippedTruthTable.length; i++) {
            for (int j = 0; j < zippedTruthTable[i].length; j++) {
                zippedTruthTable[i][j] = truthTable[l++][columns-1];
            }
        }
    }

    public int[][] getZippedTruthTable() {
        return zippedTruthTable;
    }
}
