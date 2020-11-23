package ma.enset;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class DemoMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMicroServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(ProduitRepository produitRepository) {
		return args->{
			produitRepository.save(new Produit(null,"Ord HP 54",6000,3));
			produitRepository.save(new Produit(null,"Imprimante",7000,13));
			produitRepository.save(new Produit(null,"smartPHone",300,23));
			
			produitRepository.findAll().forEach(p->{
				System.out.println(p.getName());
			});
			
			
		
	};

}
}


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
class Produit{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private double quantity;
	
}

interface ProduitRepository extends JpaRepository<Produit, Long>{
	
}

@RestController
class ProduitRestController{
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@GetMapping(path = "/produits")
	public List<Produit> list(){
		return produitRepository.findAll();
	}
	
	@GetMapping(path = "/produits/{id}")
	public Produit getOne(@PathVariable Long id){
		return produitRepository.findById(id).get();
	}
	
	@PostMapping(path = "/produits}")
	public Produit save (@RequestBody Produit produit){
		return produitRepository.save(produit);
	}
	
	@PutMapping(path = "/produits/{id}")
	public Produit update (@PathVariable Long id,@RequestBody Produit produit){
		produit.setId(id);
		return produitRepository.save(produit);
	}
	
	@DeleteMapping(path = "/produits/{id}")
	public void delete (@PathVariable Long id){
		produitRepository.deleteById(id);
	}
	
	
}










