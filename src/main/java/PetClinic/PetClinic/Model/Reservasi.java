package PetClinic.PetClinic.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "reservasi")
public class Reservasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "nama_hewan")
    private String namaHewan;

    @Column(name = "jenis_layanan")
    private String jenisLayanan;

    private LocalDate tanggal;

    private String catatan;

    private String dokter;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
