
import java.util.ArrayList;

class Barang { 
    private String kodeBarang; 
    private String namaBarang;
    private int harga = 0; 
    private int jumlah = 0; 
    private double diskon = 0.0; 

    // Konstruktor Barang untuk membuat objek barang dengan kode, nama, harga, dan jumlah tertentu.
    Barang(String kodeBarang, String namaBarang, int harga, int jumlah) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    // Konstruktor Barang untuk membuat objek barang dengan kode, nama, dan harga tertentu.
    Barang(String kodeBarang, String namaBarang, int harga) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
    }

    
    public String getKodeBarang() {
        return this.kodeBarang;
    }

    // Getter untuk mengembalikan nilai nama barang.
    public String getNamaBarang() {
        return this.namaBarang;
    }

    // Getter untuk mengembalikan nilai harga barang.
    public int getHarga() {
        return this.harga;
    }

    // Getter untuk mengembalikan nilai jumlah barang.
    public int getJumlah() {
        return this.jumlah;
    }

    // Getter untuk mengembalikan nilai diskon barang.
    public double getDiskon() {
        return this.diskon;
    }

    // Setter untuk mengatur nilai harga barang.
    public void setHarga(int harga) {
        this.harga = harga;
    }

    // Setter untuk mengatur nilai jumlah barang.
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    // Setter untuk mengatur nilai harga dan jumlah barang.
    public void setHargaJumlah(int harga, int jumlah) {
        this.harga = harga;
        this.jumlah = jumlah;
    }

    // Setter untuk mengatur nilai diskon barang.
    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }

    // OVERRIDING (menampilkan informasi barang(nama, kode, harga, jumlah dan diskon). yang sama di superclassnya.)
    public void infoBarang() {
        System.out.println("Kode    : " + kodeBarang);
        System.out.println("Nama    : " + namaBarang);
        System.out.println("Harga   : " + harga);
        System.out.println("Jumlah  : " + jumlah);
        System.out.println("Diskon  : " + (diskon * 100) + "%");
    }
}

// Kelas Superstore merepresentasikan sebuah toko yang memiliki daftar barang-barang yang dijual.
class Superstore {
    private ArrayList<Barang> listBarang = new ArrayList<>(); // ArrayList untuk menyimpan daftar barang
    private int totalHarga; // Variabel untuk menyimpan total harga barang
    private int noItem = 0; // Variabel untuk menghitung jumlah barang dalam daftar

    // Method untuk menambahkan barang baru ke dalam daftar. OVERLOADING (menambahkan barang baru tanpa jumlah. karena di DriverSuperstore ada barang yang memiliki jumlah 0)
    public void tambahBarang(String kodeBarang, String namaBarang, int harga) {
        System.out.println("     =====================================");
        System.out.println("\tMenambahkan Daftar Barang Baru");
        System.out.println("     =====================================");
        listBarang.add(new Barang(kodeBarang, namaBarang, harga)); // Membuat objek Barang baru dan menambahkannya ke dalam ArrayList listBarang
        listBarang.get(noItem).infoBarang(); // Menampilkan informasi tentang barang yang baru ditambahkan
        System.out.println("Jumlah barang 0, lakukan penambahan stock sebelum melakukan penjualan");
        noItem++;
    }

    // Method untuk menambahkan barang baru dengan jumlah tertentu ke dalam daftar. OVERLOADING (menambahkan barang baru dengan jumlah tertentu.)
    public void tambahBarang(String kodeBarang, String namaBarang, int harga, int jumlah) {
        System.out.println("     =====================================");
        System.out.println("\tMenambahkan Daftar Barang Baru");
        System.out.println("     =====================================");
        listBarang.add(new Barang(kodeBarang, namaBarang, harga, jumlah)); // Membuat objek Barang baru dengan jumlah tertentu dan menambahkannya ke dalam ArrayList listBarang
        listBarang.get(noItem).infoBarang(); // Menampilkan informasi tentang barang yang baru ditambahkan
        noItem++;
    }

    // Method untuk mengupdate harga barang dengan kode tertentu.
    public void updateHarga(int newharga, String kodeBarang){
        for(Barang barang : listBarang){
            System.out.println("     =====================================");
            System.out.println("\tUpdate Harga Barang");
            System.out.println("     =====================================");
            if (barang.getKodeBarang().equals(kodeBarang)) {
                barang.setHarga(newharga); // Mengatur harga baru untuk barang dengan kode tertentu
                System.out.println("Harga barang dengan kode " + kodeBarang + " berhasil diperbarui");
                return;
            }
        }
    }

    // Method untuk mengupdate harga dan jumlah barang dengan kode tertentu.
    public void updatehargaJumlah(int newJumlah, String kodeBarang, int newharga){
        for(Barang barang : listBarang){
            System.out.println("     =====================================");
            System.out.println("\tUpdate Harga dan Jumlah Barang");
            System.out.println("     =====================================");
            if (barang.getKodeBarang().equals(kodeBarang)) {
                barang.setHargaJumlah(newharga, newJumlah); // Mengatur harga dan jumlah baru untuk barang dengan kode tertentu
                System.out.println("Harga dan Jumlah barang dengan kode " + kodeBarang + " berhasil diperbarui");
                return;
            }
        }
    }

    // Method untuk proses penjualan barang dengan jumlah tertentu.
    public void prosesPenjualan(String kodeBarang, int jumlahTerjual) {
        for (Barang barang : listBarang) {
            if (barang.getKodeBarang().equals(kodeBarang)) {
                if (barang.getJumlah() >= jumlahTerjual) {
                    int totalHarga = barang.getHarga() * jumlahTerjual;
                    System.out.println("Penjualan barang dengan kode " + kodeBarang + " sejumlah " + jumlahTerjual + " berhasil. Total harga: " + totalHarga);
                    barang.setJumlah(barang.getJumlah() - jumlahTerjual); // Mengurangi jumlah barang yang terjual dari stok barang
                } else {
                    System.out.println("Stok barang tidak mencukupi untuk penjualan");
                }
                return;
            }
        }
        System.out.println("Barang dengan kode " + kodeBarang + " tidak ditemukan");
    }

    // Method untuk mengatur jumlah barang dan diskon untuk barang dengan kode tertentu.
    public void jumlahBarangDanDiskon(String kodeBarang, int jumlah, double diskon) {
        for (Barang barang : listBarang ) {
            if (barang.getKodeBarang().equals(kodeBarang)) {
                barang.setJumlah(jumlah); // Mengatur jumlah barang baru
                barang.setDiskon(diskon); // Mengatur diskon baru untuk barang
                System.out.println("Jumlah barang dengan kode " + kodeBarang + " berhasil diperbarui menjadi " + jumlah + " dengan diskon " + (diskon * 100) + "%");
                return;
            }
        }
        System.out.println("Barang dengan kode " + kodeBarang + " tidak ditemukan");
    }

    // Method untuk menampilkan informasi semua barang dalam daftar.
    public void infoStockAllBarang() {
        System.out.println("     ===================");
        System.out.println("\tDaftar Barang");
        System.out.println("     ===================");

        if (!listBarang.isEmpty()) {
            for (Barang barang : listBarang) {
                barang.infoBarang(); // Menampilkan informasi tentang setiap barang dalam daftar
                System.out.println();
            }
        } else {
            System.out.println("Belum ada barang dalam list data");
        }
    }
}

// Kelas DriverSuperStore untuk menjalankan program.
class DriverSuperStore {
    public static void main(String[] args) {
        Superstore st = new Superstore();
        st.tambahBarang("AO1000", "Susu Bendera", 50000); // Menambahkan barang baru
        st.tambahBarang("BO1000", "Kertas HVS", 28000, 3); // Menambahkan barang baru dengan jumlah tertentu
        st.tambahBarang("CO1000", "Kacang Kedelai", 4700, 5); // Menambahkan barang baru dengan jumlah tertentu
        st.infoStockAllBarang(); // Menampilkan informasi semua barang dalam daftar

        st.updateHarga(30000,"AO1000"); // Mengupdate harga barang
        st.infoStockAllBarang(); // Menampilkan informasi semua barang dalam daftar setelah diupdate

        st.updatehargaJumlah(5,"BO1000",10000); // Mengupdate harga dan jumlah barang
        st.infoStockAllBarang(); // Menampilkan informasi semua barang dalam daftar setelah diupdate

        st.jumlahBarangDanDiskon("CO1000", 8, 0.2); // Mengubah jumlah barang dan diskon
        st.infoStockAllBarang(); // Menampilkan informasi semua barang dalam daftar setelah diubah

        st.prosesPenjualan("DO1000", 3); // Menjalankan proses penjualan
        st.infoStockAllBarang(); // Menampilkan informasi semua barang dalam daftar setelah proses penjualan

        //st.jumlahbarangterjualDenganDiskon("CO1000", 3, 1.0); // Menjual 3 barang dengan diskon 10%
    // st.infoStockAllBarang();
    }
}
