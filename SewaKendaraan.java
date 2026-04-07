package PraktikumPemlan;

import java.util.HashMap;
import java.util.Scanner;

class Kendaraan {
    protected String kode;
    protected String nama;
    protected int harga;
    protected boolean tersedia;

    public Kendaraan(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.tersedia = true;
    }

    public int rent(int hari, boolean promo) {
        int total = harga * hari;
        return total;
    }

    public String getTipe() {
        return "Vehicle";
    }

    public String getStatus() {
        return tersedia ? "TERSEDIA" : "DISEWA";
    }
}

class Mobil2 extends Kendaraan {
    
    public Mobil2(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    @Override
    public int rent(int hari, boolean promo) {
        int total = harga * hari;
        if (promo) {
            total -= 20000;
        }
        return Math.max(total, 0);
    }

    @Override
    public String getTipe() {
        return "CAR";
    }
}

class Motor extends Kendaraan {

     public Motor(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    @Override
    public int rent(int hari, boolean promo) {
        int total = harga * hari;
        if (promo) {
            total -= 10000;
        }
        return Math.max(total, 0);
    }

    @Override
    public String getTipe() {
        return "BIKE";
    }
}

public class SewaKendaraan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        HashMap<String, Kendaraan> data = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String input = sc.nextLine();
            String[] cmd = input.split(" ");

            switch (cmd[0]) {

                case "ADD":
                    String tipe = cmd[1];
                    String kode = cmd[2];
                    String nama = cmd[3];
                    int harga = Integer.parseInt(cmd[4]);

                    if (data.containsKey(kode)) {
                        System.out.println("Kendaraan sudah terdaftar");
                    } else {
                        if (tipe.equals("CAR")) {
                            data.put(kode, new Mobil2(kode, nama, harga));
                        } else {
                            data.put(kode, new Motor(kode, nama, harga));
                        }
                        System.out.println(tipe + " " + kode + " berhasil ditambahkan");
                    }
                    break;

                case "RENT":
                    kode = cmd[1];
                    int hari = Integer.parseInt(cmd[2]);
                    boolean promo = (cmd.length == 4);

                    if (!data.containsKey(kode)) {
                        System.out.println("Kendaraan tidak ditemukan");
                    } else {
                        Kendaraan v = data.get(kode);

                        if (!v.tersedia) {
                            System.out.println("Kendaraan sedang disewa");
                        } else {
                            int total = v.rent(hari, promo);
                            v.tersedia = false;
                            System.out.println("Total sewa " + kode + ": " + total);
                        }
                    }
                    break;

                case "RETURN":
                    kode = cmd[1];

                    if (!data.containsKey(kode)) {
                        System.out.println("Kendaraan tidak ditemukan");
                    } else {
                        Kendaraan v = data.get(kode);

                        if (v.tersedia) {
                            System.out.println("Kendaraan belum disewa");
                        } else {
                            v.tersedia = true;
                            System.out.println(kode + " berhasil dikembalikan");
                        }
                    }
                    break;

                case "DETAIL":
                    kode = cmd[1];

                    if (!data.containsKey(kode)) {
                        System.out.println("Kendaraan tidak ditemukan");
                    } else {
                        Kendaraan v = data.get(kode);
                        System.out.println(
                            v.kode + " | " +
                            v.getTipe() + " | " +
                            v.nama + " | harga: " +
                            v.harga + " | status: " +
                            v.getStatus()
                        );
                    }
                    break;

                case "COUNT":
                    System.out.println("Total kendaraan: " + data.size());
                    break;
            }
        }
    }
}
