import java.util.Scanner;

public class KantinBudiLuhur {
    public String nama;
    public String nipNim;
    public String angkatan;
    public Character lanjut;
    public String status;

    private static final int[] HARGA_MAKANAN = { 15000, 13000, 10000, 12000, 15000 };
    private static final int[] HARGA_CAMILAN = { 2000, 1000, 1000 };
    private static final int[] HARGA_MINUMAN_MHS = { 5000, 5000, 5000, 5000, 4000 };
    private static final int[] HARGA_MINUMAN_KARYAWAN = { 5000, 5000, 5000, 5000, 3000 };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double totalHarga = 0;
        int totalPorsiMakanan = 0;
        int totalPorsiCemilan = 0;
        int totalPorsiMinuman = 0;

        KantinBudiLuhur obj = new KantinBudiLuhur();

        do {
            System.out.print("Nama\t: ");
            obj.nama = input.nextLine();

            System.out.print("Nip/Nim\t: ");
            obj.nipNim = input.nextLine();

            obj.angkatan =obj.nipNim.substring(0, 2);

            System.out.println("Angkatan  : " + obj.angkatan);
            System.out.println("Pilih Status : ");
            System.out.println("a. Mahasiswa");
            System.out.println("b. Karyawan");
            System.out.print("Masukkan Pilihan (a/b): ");

            String status = input.nextLine();
            if (status.equals("a")) {
                status = "mahasiswa";
            } else if (status.equals("b")) {
                status = "karyawan";
            } else {
                System.out.println("Status tidak valid");
                System.exit(0);
            }

            System.out.println("Status Pembeli: " + status);
            obj.status = status;

            System.out.println("\nMakanan Berat: ");
            System.out.println("1. Nasi goreng  : 15.000");
            System.out.println("2. Mie goreng   : 13.000");
            System.out.println("3. Mie rebus    : 10.000");
            System.out.println("4. Soto ayam    : 12.000");
            System.out.println("5. Soto daging  : 15.000");
            System.out.println("6. Tidak makan");

            System.out.print("Masukkan jumlah makanan yang ingin dibeli: ");
            int jumlahMakanan = input.nextInt();
            input.nextLine();

            for (int i = 0; i < jumlahMakanan; i++) {
                System.out.print("Pilih makanan berat (1-6): ");
                int pilihanMakanan = input.nextInt();
                input.nextLine();

                if (pilihanMakanan != 6) {
                    totalHarga += HARGA_MAKANAN[pilihanMakanan - 1];
                    totalPorsiMakanan++;
                }
            }

            System.out.println("\nCemilan:");
            System.out.println("1. Chiki        : 2.000");
            System.out.println("2. Lidi-lidian  : 1.000");
            System.out.println("3. Kerupuk      : 1.000");
            System.out.println("4. Tidak Ngemil");

            System.out.print("Masukkan jumlah camilan yang ingin dibeli: ");
            int jumlahCamilan = input.nextInt();
            input.nextLine();

            for (int i = 0; i < jumlahCamilan; i++) {
                System.out.print("Pilih cemilan (1-4): ");
                int PilihCemilan = input.nextInt();
                input.nextLine();

                if (PilihCemilan != 4) {
                    totalHarga += jumlahCamilan * HARGA_CAMILAN[PilihCemilan - 1];
                    totalPorsiCemilan++;
                }
            }

            System.out.println("\nMinuman:");
            System.out.println("1. Es the manis         : 5.000");
            System.out.println("2. Minuman saset seduh  : 5.000");
            System.out.println("3. Kopi panas           : 5.000");
            System.out.println("4. The panas            : 5.000");

            if (status.equals("mahasiswa")) {
                System.out.println("5. Aqua                 : 4.000");
            } else {
                System.out.println("5. Aqua                 : 3.000");
            }
            System.out.println("6. Tidak minum");

            System.out.print("Masukkan jumlah minuman yang ingin dibeli: ");
            int jumlahMinuman = input.nextInt();
            input.nextLine();

            for (int i = 0; i < jumlahMinuman; i++) {
                System.out.print("Pilih minuman (1-6): ");
                int pilihanMinuman = input.nextInt();
                input.nextLine();

                if (pilihanMinuman != 6) {
                    totalHarga += status.equals("mahasiswa") ? HARGA_MINUMAN_MHS[pilihanMinuman - 1]
                            : HARGA_MINUMAN_KARYAWAN[pilihanMinuman - 1];
                    totalPorsiMinuman++;
                }
            }

            double diskonMahasiswa = 0;
            if (status.equals("mahasiswa")) {
                if (Integer.parseInt(obj.angkatan) < 20) {
                    diskonMahasiswa = totalHarga * 2 / 100;
                }
            }

            double diskonTotal = totalHarga >= 100000 ? 2000 : 0;

            int voucher = 0;
            if (totalPorsiMakanan >= 10) {
                totalHarga -= totalHarga / totalPorsiMakanan;
                voucher = (int) (totalHarga / totalPorsiMakanan);
            }

            int HargaAwal = (int) totalHarga;

            totalHarga -= diskonMahasiswa;
            totalHarga -= diskonTotal;

            int HargaDiskon = (int) totalHarga;

            System.out.print("\nProses Gaji Karyawan (y/t) : ");
            Character proses = input.next().charAt(0);

            if (proses == 'y' || proses == 'Y') {
                System.out.println("\nTotal Pembelian:");
                System.out.println("Jumlah Pesanan\t: " + (totalPorsiMakanan + totalPorsiCemilan + totalPorsiMinuman));
                System.out.println("Total Harga\t: Rp " + HargaAwal);
                System.out.println("Diskon \t\t: Rp " + (int) diskonMahasiswa);
                System.out.println("Voucher\t\t: Rp " + voucher);
                System.out.println("Potongan harga\t: Rp " + (int) diskonTotal);

                System.out.println("Total Bayar\t: Rp " + HargaDiskon);

                System.out.print("\nMasukan uang pembayaran: ");
                int uang = input.nextInt();

                if (uang < totalHarga) {
                    System.out.println("Uang tidak cukup");
                } else {
                    int kembalian = uang - (int) totalHarga;
                    System.out.println("Kembali\t: Rp " + kembalian);
                }
            } else {
                System.out.println("\nGaji Karyawan Gagal Diproses");
            }

            System.out.println("\nProgrammer");

            System.out.print("NIM\t: ");
            String programmer = input.next();
            input.nextLine();

            if (programmer.equals("2311500942")) {
                System.out.println("NAMA\t: Miftah Husnita");
            }

            System.out.print("\nApakah Anda Ingin Mengulang menggunakan program ini? (Y/T): ");
            obj.lanjut = input.next().charAt(0);
            input.nextLine();

        } while (obj.lanjut.equals('y'));

        input.close();
        System.out.println("Terima kasih telah berbelanja di Kantin Budi Luhur 💖.");
    }
}