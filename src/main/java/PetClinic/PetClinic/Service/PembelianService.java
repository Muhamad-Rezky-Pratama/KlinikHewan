package PetClinic.PetClinic.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import PetClinic.PetClinic.Model.Pembelian;
import PetClinic.PetClinic.Model.Produk;
import PetClinic.PetClinic.Model.User;
import PetClinic.PetClinic.Repository.PembelianRepository;
import PetClinic.PetClinic.Repository.ProdukRepository;

@Service
public class PembelianService {

    @Autowired
    private PembelianRepository pembelianRepository;

    @Autowired
    private ProdukRepository produkRepository;

    @Transactional
    public Pembelian createPembelian(User user, Produk produk, Integer jumlah) {
        // Validasi stok
        if (produk.getStok() < jumlah) {
            throw new RuntimeException("Stok produk " + produk.getNama() + " tidak mencukupi");
        }

        Pembelian pembelian = new Pembelian();
        pembelian.setUser(user);
        pembelian.setProduk(produk);
        pembelian.setJumlah(jumlah);
        pembelian.setTotal(produk.getHarga() * jumlah);
        pembelian.setTanggal(LocalDateTime.now());
        pembelian.setStatus("PENDING");

        // Update stok produk        produk.setStok(produk.getStok() - jumlah);
        produkRepository.save(produk);

        return pembelianRepository.save(pembelian);
    }
    
    @Transactional
    public List<Pembelian> processCheckout(User user, List<Map<String, Object>> cartItems) {
        List<Pembelian> pembelianList = new ArrayList<>();
        
        for (Map<String, Object> item : cartItems) {
            Integer produkId = ((Number) item.get("productId")).intValue();
            Integer quantity = ((Number) item.get("quantity")).intValue();
            Double price = ((Number) item.get("price")).doubleValue();
            
            // Get product and validate stock
            Produk produk = produkRepository.findById(produkId)
                .orElseThrow(() -> new RuntimeException("Produk tidak ditemukan: " + produkId));
            
            if (produk.getStok() < quantity) {
                throw new RuntimeException("Stok tidak mencukupi untuk produk: " + produk.getNama());
            }

            // Create pembelian record
            Pembelian pembelian = new Pembelian();
            pembelian.setUser(user);
            pembelian.setProduk(produk);
            pembelian.setJumlah(quantity);
            pembelian.setTotal(price * quantity);
            pembelian.setTanggal(LocalDateTime.now());
            pembelian.setStatus("PENDING"); // Initial status
            
            // Update stock
            produk.setStok(produk.getStok() - quantity);
            produkRepository.save(produk);
            
            // Save pembelian
            pembelianRepository.save(pembelian);
            pembelianList.add(pembelian);
        }
        
        return pembelianList;
    }

    public List<Pembelian> getPembelianByUser(User user) {
        return pembelianRepository.findByUser(user);
    }

    public Pembelian updateStatus(Integer pembelianId, String status) {
        Pembelian pembelian = pembelianRepository.findById(pembelianId)
            .orElseThrow(() -> new RuntimeException("Pembelian tidak ditemukan"));
        pembelian.setStatus(status);
        return pembelianRepository.save(pembelian);
    }
}
