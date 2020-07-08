package sorting;

import java.io.*;
import java.util.*;

public class SortingTool {
    public static void main(final String[] args) throws IOException {
        String method = "natural";
        String datatype = "word";
        String inputtype = "stdin";
        String outputtype = "stdout";
        //System.out.println(args);
        for (int i = 0; i < args.length; ++i) {
            String s = args[i];
            if(s.startsWith("-")) {
                if (s.equals("-sortingType")) {
                    try {
                        method = args[i + 1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("No sorting type defined!");
                        System.exit(0);
                    }
                } else if (s.equals("-dataType")) {
                    try {
                        datatype = args[i + 1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("No data type defined!");
                        System.exit(0);
                    }
                } else if (s.equals("-inputFile")) {
                    try {
                        inputtype = args[i + 1];
                    } catch (Exception e) {

                    }
                } else if (s.equals("-outputFile")) {
                    try {
                        outputtype = args[i + 1];
                    } catch (Exception e) {

                    }
                } else {
                    System.out.println("\"" + s + "\" isn't a valid parameter. It's skipped.");
                }
            }
        }
        PrintWriter out =null;
        if (!outputtype.equals("stdout")) {
            try {
                out = new PrintWriter(outputtype);
            } catch (Exception e) {

            }
        }
        Scanner scanner = null;
        if (inputtype.equals("stdin")) {
            scanner = new Scanner(System.in);
        } else {
            try {
                File file = new File(inputtype);
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        if (datatype.equals("long")) {
            ArrayList<Long> arr = new ArrayList<>();
            while (scanner.hasNext()) {
                String x = scanner.next();
                try {
                    long y = Long.parseLong(x);
                    arr.add(y);
                } catch (Exception e) {
                    System.out.println("\"" + x + "\" isn't a long. It's skipped.");
                }
            }
            scanner.close();
            if (method.equals("natural")) {
                if (outputtype.equals("stdout")) {
                    System.out.println("Total numbers: " + arr.size());
                } else {
                    out.println("Total numbers: " + arr.size());
                }
                Collections.sort(arr);
                if (outputtype.equals("stdout")) {
                    System.out.print("Sorted data:");
                } else {
                    out.print("Sorted data:");
                }
                for (long i : arr) {
                    if (outputtype.equals("stdout")) {
                        System.out.print(" " + i);
                    } else {
                        out.print(" " + i);
                    }
                }
            } else if(method.equals("byCount")){
                System.out.println("Total numbers: " + arr.size() + ".");
                TreeMap<Long,Integer> dataEntryToCount = new TreeMap<>();
                Collections.sort(arr);
                for (Long i : arr) {
                    if(dataEntryToCount.get(i) == null) {
                        dataEntryToCount.put(i,1);
                    } else {
                        dataEntryToCount.put(i, dataEntryToCount.get(i) + 1);
                    }
                }
                //System.out.println("dataEntryToCount : ");
                //System.out.println(dataEntryToCount);
                TreeSet<Integer> sortedCounts = new TreeSet<>(dataEntryToCount.values());
                TreeMap<Integer, Set<Long>> countToDataEntries = new TreeMap<>();
                for (Long k : dataEntryToCount.keySet()) {
                    int v = dataEntryToCount.get(k);
                    TreeSet<Long> tempSet= (TreeSet) countToDataEntries.get(v);
                    //System.out.println("tempset : " + tempSet);
                    if (tempSet == null) {
                        tempSet = new TreeSet<>(Set.of(k));
                    } else {
                        tempSet.add(k);
                    }
                    countToDataEntries.put(v,tempSet);
                }
                for (int i : sortedCounts) {
                    for(long j : countToDataEntries.get(i)) {
                        if (outputtype.equals("stdout")) {
                            System.out.println(j + ": " + i + " time(s), " + (int) ((double) i / arr.size() * 100));
                        } else {
                            out.println(j + ": " + i + " time(s), " + (int) ((double) i / arr.size() * 100));
                        }
                    }
                }
            }
            if (out != null) {
                out.close();
            }
        } else if (datatype.equals("word")) {
            ArrayList<String> arr = new ArrayList<>();
            while (scanner.hasNext()) {
                arr.add(scanner.next());
            }
            scanner.close();
            if (method.equals("natural")) {
                if (outputtype.equals("stdout")) {
                    System.out.println("Total words: " + arr.size());
                    System.out.print("Sorted data:");
                } else {
                    out.println("Total words: " + arr.size());
                    out.print("Sorted data:");
                }
                Collections.sort(arr);
                for (String i : arr) {
                    if (outputtype.equals("stdout")) {
                        System.out.print(" " + i);
                    } else {
                        out.print(" " + i);
                    }
                }
            } else if(method.equals("byCount")){
                if (outputtype.equals("stdout")) {
                    System.out.println("Total words: " + arr.size() + ".");
                } else {
                    out.println("Total words: " + arr.size() + ".");
                }

                TreeMap<String,Integer> dataEntryToCount = new TreeMap<>();
                Collections.sort(arr);
                for (String i : arr) {
                    if(dataEntryToCount.get(i) == null) {
                        dataEntryToCount.put(i,1);
                    } else {
                        dataEntryToCount.put(i, dataEntryToCount.get(i) + 1);
                    }
                }
                TreeSet<Integer> sortedCounts = new TreeSet<>(dataEntryToCount.values());
                TreeMap<Integer, Set<String>> countToDataEntries = new TreeMap<>();
                for (String k : dataEntryToCount.keySet()) {
                    int v = dataEntryToCount.get(k);
                    TreeSet<String> tempSet= (TreeSet) countToDataEntries.get(v);
                    //System.out.println("tempset : " + tempSet);
                    if (tempSet == null) {
                        tempSet = new TreeSet<>(Set.of(k));
                    } else {
                        tempSet.add(k);
                    }
                    countToDataEntries.put(v,tempSet);
                }
                for (int i : sortedCounts) {
                    for(String j : countToDataEntries.get(i)) {
                        if (outputtype.equals("stdout")) {
                            System.out.println(j + ": " + i + " time(s), " + (int) ((double) i / arr.size() * 100 ) + "%");
                        } else {
                            out.println(j + ": " + i + " time(s), " + (int) ((double) i / arr.size() * 100) + "%");
                        }
                    }
                }
            }
            if (out != null) {
                out.close();
            }
        } else if (datatype.equals("line")) {
            ArrayList<String> arr = new ArrayList<>();
            while (scanner.hasNextLine()) {
                arr.add(scanner.nextLine());
            }
            scanner.close();
            if (method.equals("natural")) {
                if (outputtype.equals("stdout")) {
                    System.out.println("Total lines: " + arr.size());
                    System.out.print("Sorted data:");
                } else {
                    out.println("Total lines: " + arr.size());
                    out.print("Sorted data:");
                }
                Collections.sort(arr);

                for (String i : arr) {
                    if (outputtype.equals("stdout")) {
                        System.out.print(" " + i);
                    } else {
                        out.print(" " + i);
                    }
                }
            } else if(method.equals("byCount")){
                if (outputtype.equals("stdout")) {
                    System.out.println("Total lines: " + arr.size() + ".");
                } else {
                    out.println("Total lines: " + arr.size() + ".");
                }

                TreeMap<String,Integer> dataEntryToCount = new TreeMap<>();
                Collections.sort(arr);
                for (String i : arr) {
                    if(dataEntryToCount.get(i) == null) {
                        dataEntryToCount.put(i,1);
                    } else {
                        dataEntryToCount.put(i, dataEntryToCount.get(i) + 1);
                    }
                }
                TreeSet<Integer> sortedCounts = new TreeSet<>(dataEntryToCount.values());
                TreeMap<Integer, Set<String>> countToDataEntries = new TreeMap<>();
                for (String k : dataEntryToCount.keySet()) {
                    int v = dataEntryToCount.get(k);
                    TreeSet<String> tempSet= (TreeSet) countToDataEntries.get(v);
                    //System.out.println("tempset : " + tempSet);
                    if (tempSet == null) {
                        tempSet = new TreeSet<>(Set.of(k));
                    } else {
                        tempSet.add(k);
                    }
                    countToDataEntries.put(v,tempSet);
                }
                for (int i : sortedCounts) {
                    for(String j : countToDataEntries.get(i)) {
                        if (outputtype.equals("stdout")) {
                            System.out.println(j + ": " + i + " time(s), " + (int) ((double) i / arr.size() * 100) + "%");
                        } else {
                            out.println(j + ": " + i + " time(s), " + (int) ((double) i / arr.size() * 100) + "%");
                        }
                    }
                }
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
