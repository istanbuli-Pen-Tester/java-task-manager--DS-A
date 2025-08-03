import java.util.Random;

class Test {

    public static class MergeSort {

        public static void mergeSort(int[] array) {
            if (array.length <= 1) {
                return;
            }

            int middle = array.length / 2;
            int[] left = new int[middle];
            int[] right = new int[array.length - middle];

            for (int i = 0; i < middle; i++) {
                left[i] = array[i];
            }
            for (int i = middle; i < array.length; i++) {
                right[i - middle] = array[i];
            }

            mergeSort(left);
            mergeSort(right);

            merge(array, left, right);
        }

        private static void merge(int[] array, int[] left, int[] right) {
            int i = 0, j = 0, k = 0;

            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }

            while (i < left.length) {
                array[k++] = left[i++];
            }

            while (j < right.length) {
                array[k++] = right[j++];
            }
        }
    }

    public static void enhancedSelectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            int minIndex = i;
            int maxIndex = i;
            for (int j = i; j < n - i; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            if (maxIndex == i) {
                maxIndex = minIndex;
            }
            temp = arr[n - i - 1];
            arr[n - i - 1] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr1=new int[10000000];
        int[] arr2=new int[10000000];
        Random rand = new Random();
        
        for(int i=0;i<arr1.length-1;i++){
            arr1[i] = rand.nextInt(arr1.length);
            //arr2[i] = arr1[i];
            
            arr1[i] = i;
           // arr1[i] = arr2[i];
        }
        
        
        for (int i = arr1.length - 1; i >= 0; i--) {
            //arr1[i] = i;
            //arr1[i] = arr2[i];
            arr2[i] = i;
        }
        
        
        long start = System.currentTimeMillis();
        MergeSort.mergeSort(arr1);
        long end = System.currentTimeMillis();

        long time =end - start;
        System.out.println("merg sort "+time);
        long start3 = System.currentTimeMillis();
        MergeSort.mergeSort(arr2);
        long end3 = System.currentTimeMillis();
        long time3 =end3 - start3;
        System.out.println("merg sort "+time3);
        //long start2 = System.currentTimeMillis();
       // enhancedSelectionSort(arr2);
       // long end2 = System.currentTimeMillis();
        //long time2 =end2 - start2;
        //System.out.println("enhanced selection sort "+time2);

    }
}
