package PetClinic.PetClinic.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "produk")
public class Produk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Menggunakan Integer untuk konsistensi

    private String nama;
    private String deskripsi;
    private double harga;
    private String gambar;
    private String lokasi;
    private String kategori;
    private int stok;
}
