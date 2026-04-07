package PraktikumPemlan;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    protected String nama;
    protected double saldo;

    public Student(String nama) {
        this.nama = nama;
        saldo = 0;
    }

    public void simpan(int jumlah) {
        saldo += jumlah;
        System.out.println("Saldo " + nama + ": " + saldo);
    }

    public boolean ambil(int jumlah) {
        if (saldo < jumlah) {
            System.out.println("Saldo " + nama + " tidak cukup");
            return false;
        }
        saldo -= jumlah;
        return true;
    }

    public String getTipe() {
        return "Student";
    }

    public void check() {
        System.out.println(nama + " | " + getTipe() + " | saldo: " + saldo);
    }
}

class Reguler extends Student{

    public Reguler(String nama) {
        super(nama);
    }

    @Override
    public String getTipe() {
        return "REGULER";
    }
}

class Beasiswa extends Student{
    
    public Beasiswa(String nama) {
        super(nama);
    }

    @Override
    public boolean ambil(int jumlah) {
        int bayar = jumlah - 1000;
        if (bayar < 0) bayar = 0;

        if (saldo < bayar) {
            System.out.println("Saldo" + nama + " tidak cukup");
            return false;
        }

        saldo -= bayar;
        return true;
    }

     @Override
    public String getTipe() {
        return "BEASISWA";
    }
}

public class Tabungan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        HashMap<String, Student> data = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String input = sc.nextLine();
            String[] cmd = input.split(" ");

            switch (cmd[0]) {
                case "CREATE":
                    String tipe = cmd[1];
                    String nama = cmd[2];

                    if (data.containsKey(nama)) {
                        System.out.println("Akun sudah terdaftar");
                    } else {
                        if (tipe.equals("REGULER")) {
                            data.put(nama, new Reguler(nama));
                        } else if (tipe.equals("BEASISWA")) {
                            data.put(nama, new Beasiswa(nama));
                        }
                        System.out.println(tipe + " " + nama + " berhasil dibuat");
                    }
                    break;

                case "SAVE":
                    nama = cmd[1];
                    int jumlah = Integer.parseInt(cmd[2]);

                    if (!data.containsKey(nama)) {
                        System.out.println("Akun tidak ditemukan");
                    } else {
                        data.get(nama).simpan(jumlah);
                    }
                    break;

                case "TAKE":
                    nama = cmd[1];
                    jumlah = Integer.parseInt(cmd[2]);

                    if (!data.containsKey(nama)) {
                        System.out.println("Akun tidak ditemukan");
                    } else {
                        Student s = data.get(nama);
                        if (s.ambil(jumlah)) {
                            System.out.println("Saldo " + nama + ": " + s.saldo);
                        }
                    }
                    break;

                case "CHECK":
                    nama = cmd[1];

                    if (!data.containsKey(nama)) {
                        System.out.println("Akun tidak ditemukan");
                    } else {
                        data.get(nama).check();
                    }
                    break;
            }
        }
    }
}

