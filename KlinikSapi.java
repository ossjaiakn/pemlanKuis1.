package PraktikumPemlan;

import java.util.Scanner;

public class KlinikSapi {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String nama;
        int berat = 0;
        String layanan;
        String kelas;

        while (true) {
            nama = sc.nextLine();
            if (!nama.matches("[a-zA-Z]+")) {
                System.out.println("Mooo! Nama sapi harus pakai huruf, bukan angka atau simbol!");
            } else {
                break;
            }
        }

        while (true) {
            try {
                berat = Integer.parseInt(sc.nextLine());
                if (berat < 1) {
                    System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
            }
        }

        while (true) {
            layanan = sc.nextLine();
            if (layanan.equals("spa") || layanan.equals("potong_kuku") || layanan.equals("grooming")) {
                break;
            } else {
                System.out.println("Pilih spa, potong_kuku, atau grooming! Sapi kamu mau dirawat apa, sih?");
            }
        }

        while (true) {
            kelas = sc.nextLine();
            if (kelas.equals("reguler") || kelas.equals("vip")) {
                break;
            } else {
                System.out.println("Pilih reguler atau vip! Sapi kamu mau treatment sultan atau biasa aja?");
            }
        }

        int hargaPerKg = 0;
        switch (layanan) {
            case "spa": hargaPerKg = 8000; break;
            case "potong_kuku": hargaPerKg = 6000; break;
            case "grooming": hargaPerKg = 10000; break;
        }

        double biayaDasar = berat * hargaPerKg;

        double diskon = 0;
        if (berat > 30) {
            diskon = 0.1 * biayaDasar;
        }

        double tambahanVIP = 0;
        if (kelas.equals("vip")) {
            tambahanVIP = 0.2 * biayaDasar;
        }

        double subtotal = biayaDasar - diskon + tambahanVIP;

        double pajak = 0.08 * subtotal;

        double total = subtotal + pajak;

        if (nama.equals("Moo") || nama.equals("Mooo") || nama.equals("Moooo")) {
            total = 0;
        }

        System.out.println("============= NOTA KLINIK SAPI =============");
        System.out.println("Nama Sapi: " + nama);
        System.out.println("Berat: " + berat + " kg");
        System.out.println("Jenis Layanan: " + layanan);
        System.out.println("Kelas: " + kelas);
        System.out.println("Biaya Dasar: Rp " + biayaDasar);
        System.out.println("Diskon: Rp " + diskon);
        System.out.println("Biaya Tambahan VIP: Rp " + tambahanVIP);
        System.out.println("Subtotal: Rp " + subtotal);
        System.out.println("Pajak: Rp " + pajak);
        System.out.println("Total Biaya: Rp " + total);
        System.out.println("============================================");
        if (nama.equals("Moo") || nama.equals("Mooo") || nama.equals("Moooo")) {
            System.out.println("Terima kasih, " + nama + " ! Sapi spesial memang beda perlakuan~");
        } else {
            System.out.println("Terima kasih, " + nama + " ! Semoga sapinya makin glow up.");
        }
    }
}
