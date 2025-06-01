package PetClinic.PetClinic.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pembelian")
public class Pembelian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer produkId;
    private String namaPembeli;
    private Integer jumlah;
    private Double total;
    private LocalDateTime tanggal;
}
