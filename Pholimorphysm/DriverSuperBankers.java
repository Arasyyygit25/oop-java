import java.util.ArrayList;

class Rekening {
    private int NoRekening;
    private double saldo = 0.0;
    private String Nama;
    private String kartuidentitas;
    private String NamaPerusahaan;
    private int noIjin;
    private String namaPerwakilan;

    Rekening(int NoRekening, Double saldo, String Nama, String kartuidentitas) {
        this.NoRekening = NoRekening;
        this.saldo = saldo;
        this.Nama = Nama;
        this.kartuidentitas = kartuidentitas;
    }

    Rekening(int NoRekening, Double saldo, String NamaPerusahaan, int noIjin, String namaPerwakilan, String kartuidentitas){
        this.NoRekening = NoRekening;
        this.saldo = saldo;
        this.NamaPerusahaan = NamaPerusahaan;
        this.noIjin = noIjin;
        this.namaPerwakilan = namaPerwakilan;
        this.kartuidentitas = kartuidentitas;
    }

    public int getNoRekening(){ 
        return this.NoRekening;
    }

    public Double getsaldo(){
        return this.saldo;
    }

    public String getNama() {
        return this.Nama;
    }

    public String kartuidentitas() {
        return this.kartuidentitas;
    }

    public String NamaPerusahaan() {
        return this.NamaPerusahaan;
    }

    public int noIjin() {
        return this.noIjin;
    }

    public String namaPerwakilan(){
        return this.namaPerwakilan;
    }

    public void infoRekeningPerorangan() {
        System.out.println("No Rekening        : " + NoRekening);
        System.out.println("Saldo              : " + saldo);
        System.out.println("Nama               : " + Nama);
        System.out.println("Kartu Identitas    : " + kartuidentitas);
    }

    public void infoRekeningKorporat() {
        System.out.println("No Rekening     : " + NoRekening);
        System.out.println("Saldo           : " + saldo);
        System.out.println("Nama Perusahaan : " + NamaPerusahaan);
        System.out.println("No Ijin         : " + noIjin);
        System.out.println("Nama Perwakilan : " + namaPerwakilan);
        System.out.println("Kartu Identitas : " + kartuidentitas);
    }

    public void transfer(double jumlah) {
        this.saldo -= jumlah;
    }

    public void terimaTransfer(double jumlah) {
        this.saldo += jumlah;
    }
}

class SuperBankers{
    private ArrayList<Rekening> listRekeningP = new ArrayList<>();
    private ArrayList<Rekening> listRekeningK = new ArrayList<>();
    private int saldoP;
    private int saldoK;

    public void tambahRekeningPerorangan(int NoRekening, Double saldo, String nama, String kartuidentitas){
        System.out.println("     =========================================");
        System.out.println("\tMenambahkan Rekening Perorangan");
        System.out.println("     =========================================");
        listRekeningP.add(new Rekening(NoRekening, saldo, nama, kartuidentitas));
        listRekeningP.get(saldoP).infoRekeningPerorangan();
        saldoP++;
    }

    public void tambahRekeningKorporat(int NoRekening, Double saldo, String NamaPerusahaan, int noIjin, String namaPerwakilan, String kartuidentitas){
        System.out.println("     =========================================");
        System.out.println("\tMenambahkan Rekening Korporat");
        System.out.println("     =========================================");
        listRekeningK.add(new Rekening(NoRekening, saldo, NamaPerusahaan, noIjin, namaPerwakilan, kartuidentitas));
        listRekeningK.get(saldoK).infoRekeningKorporat();
        saldoK++;
    }

    public void infoallRekeningPerorangan(){
        System.out.println("     =================================");
        System.out.println("\tList Rekening Perorangan");
        System.out.println("     =================================");

        if(!listRekeningP.isEmpty()) {
            for(Rekening rekening : listRekeningP) {
                rekening.infoRekeningPerorangan();
                System.out.println();
            }
        } else {
            System.out.println("Belum ada Rekening per-orangan");
            System.out.println("===============================");
        }
    }

    public void infoallRekeningKorporat(){
        System.out.println("     =================================");
        System.out.println("\tList Rekening Korporat");
        System.out.println("     =================================");

        if(!listRekeningK.isEmpty()) {
            for(Rekening rekening : listRekeningK) {
                rekening.infoRekeningKorporat();
                System.out.println();
            }
        } else {
            System.out.println("Belum ada Rekening Korporat");
            System.out.println("===============================");
        }
    }

    public void transferDariSatuRekeningKeBanyak(int noRekeningAsal, int[] noRekeningTujuan, double jumlah) {
        Rekening rekeningAsal = findRekeningByNo(noRekeningAsal);

        if (rekeningAsal != null && rekeningAsal.getsaldo() >= jumlah && noRekeningTujuan.length > 0 && noRekeningTujuan.length <= 3) {
            double jumlahPerRekening = jumlah / noRekeningTujuan.length;

            for (int i = 0; i < noRekeningTujuan.length; i++) {
                Rekening rekeningTujuan = findRekeningByNo(noRekeningTujuan[i]);
                if (rekeningTujuan != null) {
                    rekeningAsal.transfer(jumlahPerRekening);
                    rekeningTujuan.terimaTransfer(jumlahPerRekening);
                    System.out.println("==============================");
                    System.out.println("Transfer dari Rekening " + noRekeningAsal + " ke Rekening " + noRekeningTujuan[i] + " sebesar " + jumlahPerRekening + " berhasil.");
                    updateSaldoDiDaftarRekening(rekeningAsal);
                    updateSaldoDiDaftarRekening(rekeningTujuan);
                } else {
                    System.out.println("Rekening dengan nomor " + noRekeningTujuan[i] + " tidak ditemukan.");
                }
            }
        } else {
            System.out.println("Transfer gagal. Pastikan saldo mencukupi dan jumlah rekening tujuan tidak melebihi 3.");
        }
    }

    private void updateSaldoDiDaftarRekening(Rekening rekening) {
        for (int i = 0; i < listRekeningP.size(); i++) {
            if (listRekeningP.get(i).getNoRekening() == rekening.getNoRekening()) {
                listRekeningP.set(i, rekening);
                break;
            }
        }
        for (int i = 0; i < listRekeningK.size(); i++) {
            if (listRekeningK.get(i).getNoRekening() == rekening.getNoRekening()) {
                listRekeningK.set(i, rekening);
                break;
            }
        }
    }

    private Rekening findRekeningByNo(int noRekening) {
        for (Rekening rekening : listRekeningP) {
            if (rekening.getNoRekening() == noRekening) {
                return rekening;
            }
        }
        for (Rekening rekening : listRekeningK) {
            if (rekening.getNoRekening() == noRekening) {
                return rekening;
            }
        }
        return null;
    }
}

class DriverSuperBankers {
    public static void main(String[] args){
        SuperBankers sb = new SuperBankers();
        sb.tambahRekeningPerorangan(1201230038, 50000.0, "Arasy", "SIM");
        sb.tambahRekeningPerorangan(1201230040, 100000.0, "Rifqi", "KTP");
        sb.infoallRekeningPerorangan();

        sb.tambahRekeningKorporat(1223334455, 100000.0, "ArasyCompany", 1, "Joko", "SIM");
        sb.tambahRekeningKorporat(1223334450, 200000.0, "PoCompany", 2, "Arasy", "KTP");
        sb.infoallRekeningKorporat();

        // Transfer dari satu rekening ke beberapa rekening
        sb.transferDariSatuRekeningKeBanyak(1201230040, new int[]{1201230038, 1223334455, }, 30000.0);

        System.out.println("==============================");
        System.out.println("Setelah di Transfer = ");
        sb.infoallRekeningPerorangan();
        sb.infoallRekeningKorporat();

    }
}
