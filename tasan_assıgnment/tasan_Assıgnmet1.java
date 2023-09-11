import org.jetbrains.annotations.NotNull;

public class tasan_Assıgnmet1 {
    public static void main(String[] args){
    }
    public boolean boundsAreValid(int lowerBound, int upperBound, int length){
        boolean isValid = true;

        if(length == 0) {
            System.out.print("length zero");
        }

        if(lowerBound < 0) {
            System.out.print("cannot be smaller than zero");
        }

        if (lowerBound > upperBound) {
            System.out.print("cannot be upper than upperbound");
        }
        if (lowerBound > length - 1 ) {
            System.out.print("lowerbound must be smaller");
        }

        if (upperBound > length - 1 )
            System.out.print("upperbound must be smaller");

        return isValid;
    }

    public String[] selectionSort(String[] values, int lowerBound, int upperBound) {
        if(boundsAreValid(lowerBound, upperBound, values.length)){
            String values2[] = new String[(upperBound - lowerBound) + 1];
            values2[0] = values[lowerBound];
            for(int s = 0 ; s<upperBound-lowerBound; s++){
                for(int i = lowerBound; i< upperBound; i++){
                    String currentValue = values[i];
                    values2[i - lowerBound] = currentValue;
                    if(i+1 <= upperBound){
                        String nextValue = values[i+1];
                        values2[i+1 -lowerBound] = nextValue;
                        if(currentValue.compareTo(nextValue) > 0){
                            values[i] = nextValue;
                            values[i+1] = currentValue;
                            values2[i - lowerBound] = nextValue;
                            values2[i+1 - lowerBound] = currentValue;
                        }
                    }
                }
            }
            return values2;
        }
        return null;
    }

    public int LoopTest(int lowerBound, int upperBound, int testValue, int @NotNull [] values) {
        int count = 0;
        if(boundsAreValid(lowerBound, upperBound, values.length)){
            for (int i = lowerBound; i <= upperBound; ++i) {
                if (values[i] <= testValue) {
                    count++;
                }
                else{
                    System.out.print("test value smaller than value");
                }
            }
            System.out.print("count:"+count);
        }
        return count;
    }

    public int whileLoopTest(int lowerBound, int upperBound, int testValue, int[] values) {
        int i = lowerBound;
        int count = 0;
        if(boundsAreValid(lowerBound, upperBound, values.length)){
            while (i >= lowerBound && i <= upperBound && i < values.length) {
                if (i < values.length && values[i] >= testValue) {
                    count++;
                }
                i++;
            }
        }
        return count;
    }

    public int doWhileTest(int lowerBound, int upperBound, int testValue1, int testValue2, int[] values) {
        int i = lowerBound;
        int count = 0;
        if(boundsAreValid(lowerBound, upperBound, values.length)){
            do {
                if (i < values.length && (values[i] >= testValue1 && values[i] <= testValue2 || values[i] >= testValue2 && values[i] <= testValue1) ) {
                    count++;
                }
                i++;
            } while ((i >= lowerBound )&& (i <= upperBound )&& (i < values.length));
        }
        return count;
    }

    public int[] switchTest(int lowerBound, int upperBound, int[] values) {
        int val[] = new int[11];
        if(boundsAreValid(lowerBound, upperBound, values.length)){
            for (int i = lowerBound; i <= upperBound; i++) {
                switch (values[i]) {
                    case 0:
                        val[0]++;
                        break;
                    case 2:
                        val[1]++;
                        break;
                    case 4:
                        val[2]++;
                        break;
                    case 6:
                        val[3]++;
                        break;
                    case 8:
                        val[4]++;
                        break;
                    case 10:
                        val[5]++;
                        break;
                    case 12:
                        val[6]++;
                        break;
                    case 14:
                        val[7]++;
                        break;
                    case 16:
                        val[8]++;
                        break;
                    case 18:
                        val[9]++;
                        break;
                    default:
                        val[10]++;
                }
            }
        }
        return val;
    }
}


