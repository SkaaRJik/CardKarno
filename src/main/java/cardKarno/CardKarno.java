package cardKarno;

import java.util.ArrayList;
import java.util.List;

public class CardKarno {

    public static int[][] getKarnoTable(int[][] truthTable){

        int rows = 2;
        int columns = 2;
        for (int i = 2; i < truthTable[0].length-1; i++) {
            if(i % 2 == 0) columns*=2;
            else rows*=2;
        }
        int[][] zippedTruthTable = new int[rows][columns];

        int l = 0;
        for (int i = 0; i < zippedTruthTable.length; i++) {
            for (int j = 0; j < zippedTruthTable[i].length; j++) {
                zippedTruthTable[i][j] = truthTable[l++][columns-1];
            }
        }
        return zippedTruthTable;
    }

    public List<List<Integer>> getMinimiseExpression(int[][] truthTable, int mode){

        int numOfVars = truthTable[0].length-1;//Количество переменных
        int rows = truthTable.length;

        List<List<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            if(truthTable[i][numOfVars] == mode) {
                int finalI = i;
                groups.add(new ArrayList<Integer>(2){{add(finalI);}});
            }
        }
        //Вывод исключительных выражений
        if(groups.size() == 0) return groups;

        for(int i = 1; i < Math.pow(2, numOfVars); i <<= 1)//Объединение ячеек в группы
            groups = makeGroups(groups, numOfVars,i);

        //groups.sort();

        var list_of_groups=[];
        var canon_groups = groups.slice(0);
        //Исключение повторяющихся и ненужных групп
        for(var i = 0; i < canon_groups.length; i++)
        {
            var buf = canon_groups.shift();
            canon_groups.push(buf);
            for(var iter = 0; iter < numOfVars + 2; iter++)
            {
                groups = CheckGroups(groups);
                buf = groups.shift();
                groups.push(buf);
            }
            list_of_groups.push(groups.sort());
            groups = canon_groups;
        }
        return groups;
    }

    static List<List<Integer>> makeGroups(List<List<Integer>> groups, int numOfVars, int length) {
        List<List<Integer>> newGroups = new ArrayList<>();
        for(int group = 0;group < groups.size(); group++) {
            List<Integer> potentialGroups = new ArrayList<>(2);
            if(groups.get(group).size()==length) {
                for(int BinPow = 1; BinPow<=Math.pow(2,numOfVars-1); BinPow*=2) {
                    List<Integer> potNeibGroup = new ArrayList<>(groups.get(group));//Создание потенциальной соседней группы
                    for(int i = 0; i < potNeibGroup.size(); i++) {
                        potNeibGroup.set(i, potNeibGroup.get(i) + BinPow);
                        if(potNeibGroup.get(i) % (BinPow << 1) < BinPow) {
                            potNeibGroup = null;
                            break;
                        }
                    }

                    if(potNeibGroup != null && groups.ArrayInArray(potNeibGroup))
                        potentialGroups.add(potNeibGroup.concat(groups[group]));
                }
            }

            if(potentialGroups.size() != 0)
                newGroups = newGroups.concat(potentialGroups)/*.sort(function(a,b){return a[0]&2})*/;//Почему-то работает
            else
                newGroups.push(groups[group].sort());
        }
        return newGroups;
    }
}
