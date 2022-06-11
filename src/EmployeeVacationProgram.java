import java.io.*;import java.text.DateFormat;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.Locale;import java.util.Objects;import java.util.Scanner;import java.util.regex.Matcher;import java.util.regex.Pattern;public class EmployeeVacationProgram {    public static void printTableFormat() {        System.out.print("|N:| ");        System.out.print("  ���:   | ");        System.out.print(" ����� | ");        System.out.print("    ID:  |");        System.out.print("������|");        System.out.print("����|");        System.out.print("  ��� |");        System.out.print("������| ");        System.out.println();    }    public static boolean isValid(String email) {        String emailRegex = "^[A-Z\\d._%+-]+@[A-Z\\d.-]+\\.[A-Z]{2,6}$";        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);        Matcher matcher = emailPat.matcher(email);        return matcher.find();    }    public static boolean isDateFormatValid(String date) {        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");        df.setLenient(false);        try {            df.parse(date.trim());        } catch (ParseException pe) {            return false;        }        return true;    }    public static boolean isIdNumberTenDigits(String num) {        return num.length() == 10;    }    public static boolean checkHolidayType(String text) {        if (Objects.equals(text, "�������")) {            return true;        } else {            return Objects.equals(text, "���������");        }    }    public static String checkName(String[][] tableNames, String enteredName){        for (int i = 1; i <= readCsvFile(); i++) {            String temp = tableNames[i][1];            if (temp.equals(enteredName.toLowerCase())) {                System.out.println();                break;            }        }        return "���� �������� � ���� ���!";    }    public static int readCsvFile() {        int lnCounter = 0;        String path = "database.csv";        String rowNum = "";        try {            BufferedReader br = new BufferedReader(new FileReader(path));            while ((rowNum = br.readLine()) != null) {                lnCounter++;                if (rowNum.trim().isEmpty()) {                    break;                }            }            lnCounter--;        } catch (IOException e) {            System.out.print("CSV data file read error!");            e.printStackTrace();        }        return lnCounter;    }    public static void copyCsvFileToArray(String[][] dataFile) throws IOException {        int lnCount = 0;        String line = "";        BufferedReader br = new BufferedReader(new FileReader("database.csv"));        while ((line = br.readLine()) != null) {            lnCount++;            if (line.trim().isEmpty()) {                break;            }            String[] data = (line.split(","));            for (int comaCounter = 0; comaCounter < dataFile[0].length; comaCounter++) {                dataFile[lnCount][comaCounter] = data[comaCounter] + "";            }        }        //lnCount--;        br.close();    }    public static void saveDataToCSVFile(String[] data) {        try {            FileWriter fw = new FileWriter("database.csv", true);            BufferedWriter bw = new BufferedWriter(fw);            bw.newLine();            for (String tempData : data) {                bw.write(tempData + ",");            }            bw.close();        } catch (IOException e) {            System.out.println("Error!");            e.printStackTrace();        }    }    public static void saveChangesToCSVFile(String[][] table) {        //String line = "";        try {            FileWriter fw = new FileWriter("database.csv", false);            BufferedWriter bw = new BufferedWriter(fw);            for (int i = 1; i < table.length; i++) {                for (int j = 0; j < table[0].length; j++) {                    if (table[i][j] != null) {                        bw.write(table[i][j] + ",");                    }                }                bw.newLine();            }            bw.close();        } catch (IOException e) {            System.out.println("File saving error!");            e.printStackTrace();        }    }    public static void main(String[] args) throws IOException {        Scanner sc = new Scanner(System.in);        int count = readCsvFile();        String[][] table = new String[20][8];        int ch;        copyCsvFileToArray(table);        do {            System.out.println("---------------------------------------------");            System.out.println("    1. ����� �������");            System.out.println("    2. ��� ������ �������");            System.out.println("    3. ��� ������� �� ��������");            System.out.println("    4. ������� ������ �� �������");            System.out.println("    5. ����");            System.out.println("---------------------------------------------");            System.out.println("count is ->" + count);            System.out.println("������ �����:");            ch = sc.nextInt();            switch (ch) {                case 1 -> {                    String loopEnd;                    do {                        count++;                        table[count][0] = String.valueOf(count);                        System.out.println("������ ���: ");                        table[count][1] = sc.next();                        do {                            System.out.println("������ �����: ");                            table[count][2] = sc.next();                            System.out.println(isValid(table[count][2]) ? "" : " ������ ��� ������ ����. �������� ������!");                        } while (!isValid(table[count][2]));                        do {                            System.out.println("������ ��� (����� �����):");                            table[count][3] = sc.next();                            System.out.println(isIdNumberTenDigits(table[count][3]) ? "" : " ������ ��� ������ ���. �������� ������!");                        } while (table[count][3].length() != 10);                        do {                            System.out.println("������ ������� ���� �� ��������� (������:��.��.����)");                            table[count][4] = sc.next();                            System.out.println(isDateFormatValid(table[count][4]) ? "" : "������ ��� ������ ������ �� ����. �������� ������!");                        } while (!isDateFormatValid(table[count][4]));                        do {                            System.out.println("������ ������ ���� �� ��������� (������:��.��.����)");                            table[count][5] = sc.next();                            System.out.println(isDateFormatValid(table[count][5]) ? "" : "������ ��� ������ ������ �� ����. �������� ������!");                        } while (!isDateFormatValid(table[count][5]));                        do {                            System.out.println("������ ��� �� ������� ������� ��� ��������� ):");                            table[count][6] = sc.next();                            System.out.println(checkHolidayType(table[count][6]) ? "" : "������� � ������ ���. �������� ������!");                        } while (!checkHolidayType(table[count][6]));                        System.out.println("������:");                        table[count][7] = sc.next();                        System.out.println("������ �� �� �������� ���� ������? ��/��");                        loopEnd = sc.next();                        saveDataToCSVFile(table[count]);                    } while (loopEnd.equals("��"));                }                case 2 -> {                    printTableFormat();                    for (int i = 1; i <= count; i++) {                        for (int j = 0; j < table[0].length; j++) {                            System.out.print("|" + table[i][j]);                        }                        System.out.println("|");                    }                }                // checkEmployeeHoliday list                case 3 -> {                    String[][] buffer = new String[table.length][table[0].length];                    sc.nextLine();                    System.out.println("������ ��� �� ��������:");                    String enteredname = sc.nextLine();                    //System.out.println();                   checkName(table, enteredname);                    printTableFormat();                    for (int i = 1; i <= count; i++) {                        if (enteredname.equalsIgnoreCase(table[i][1])) {                            for (int j = 0; j < table[0].length; j++) {                                buffer[i][j] = table[i][j];                                System.out.print("|" + buffer[i][j]);                            }                            System.out.println("|");                        }                    }                }                case 4 -> {                    System.out.println("�������� ����� �� ������: ");                    int numRequest = sc.nextInt();                    printTableFormat();                    for (int j = 0; j < table[0].length; j++) {                        System.out.print("|" + table[numRequest][j]);                    }                    System.out.println("|");                    System.out.println("������ ��� ������ �� ��������� ������:");                    String newStat = sc.next();                    table[numRequest][7] = newStat;                    for (int j = 0; j < table[0].length; j++) {                        // toSave[j] = table[numRequest][j];                        System.out.print("|" + table[numRequest][j]);                    }                    System.out.println("|");                    saveChangesToCSVFile(table);                }                case 5 -> System.out.println("���� �� ����������!");                default -> System.out.println("������ ��� ������ �����!!!");            }        } while (ch != 5);    }}