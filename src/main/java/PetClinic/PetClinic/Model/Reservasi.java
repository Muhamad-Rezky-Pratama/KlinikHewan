package PetClinic.PetClinic.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservasi")
public class Reservasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String namaHewan;
    private String jenisLayanan;
    private LocalDate tanggal;
    private String catatan;
    private String dokter;
    private Long userId;

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNamaHewan() { return namaHewan; }
    public void setNamaHewan(String namaHewan) { this.namaHewan = namaHewan; }

    public String getJenisLayanan() { return jenisLayanan; }
    public void setJenisLayanan(String jenisLayanan) { this.jenisLayanan = jenisLayanan; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public String getCatatan() { return catatan; }
    public void setCatatan(String catatan) { this.catatan = catatan; }

    public String getDokter() { return dokter; }
    public void setDokter(String dokter) { this.dokter = dokter; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}