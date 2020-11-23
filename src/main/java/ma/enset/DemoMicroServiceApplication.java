package ma.enset;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

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


