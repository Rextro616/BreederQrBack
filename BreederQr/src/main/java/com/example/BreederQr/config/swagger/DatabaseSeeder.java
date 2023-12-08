package com.example.BreederQr.config.swagger;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.breeder.Breeder;
import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.example.BreederQr.models.laying.Laying;
import com.example.BreederQr.models.photo.Photo;
import com.example.BreederQr.models.specie.Specie;
import com.example.BreederQr.repository.AnimalRepository;
import com.example.BreederQr.repository.BreederRepository;
import com.example.BreederQr.repository.BreedingPlaceRepository;
import com.example.BreederQr.repository.LayingRepository;
import com.example.BreederQr.repository.PhotoRepository;
import com.example.BreederQr.repository.SpecieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class DatabaseSeeder {


    private JdbcTemplate jdbcTemplate;
    private BreederRepository breederRepository;
    private BreedingPlaceRepository breedingPlaceRepository;
    private SpecieRepository specieRepository;
    private AnimalRepository animalRepository;
    private LayingRepository layingRepository;
    private PhotoRepository photoRepository;



    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedBreederTable();
        seedBreedingPlaceTable();
        seedSpecieTable();
        seedAnimalTable();
        seedLayingTable();
        seedPhotoTable();

    }

    private void seedBreederTable() {

        String sql = "SELECT * FROM breeder b WHERE b.mail= 'test@mail.com'";
        List<Breeder> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("12345678");

        if(rs == null || rs.size() <= 0) {
            Breeder breeder = new Breeder();
            breeder.setMail("test@mail.com");
            breeder.setName("Test");
            breeder.setLast_name("Test");
            breeder.setSecond_last_name("Test");
            breeder.setCreatedAt(LocalDateTime.now());
            breeder.setPassword(password);
            breeder.setUsername("test");
            Breeder result = breederRepository.save(breeder);
            log.info("Seeding Breeder Table");

        }else {
            log.trace("Breeder Seeding Not Required");
        }
    }

    private void seedBreedingPlaceTable() {

        String sql = "SELECT * FROM breeding_place b WHERE b.register_number= '12345678'";
        List<BreedingPlace> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        String sql1 = "SELECT * FROM breeder b WHERE b.mail= 'test@mail.com'";
        Breeder breederId = (Breeder) jdbcTemplate.queryForObject(
                sql1,
                new Object[]{},
                new BeanPropertyRowMapper(Breeder.class));


        if(rs == null || rs.size() <= 0) {
            BreedingPlace breedingPlace = new BreedingPlace();
            breedingPlace.setName("TestBreedingPlace");
            breedingPlace.setCreatedAt(LocalDateTime.now());
            breedingPlace.setAddress("TestDireccion");
            breedingPlace.setDescription("TestDescription");
            breedingPlace.setBreeder(breederId);
            breedingPlace.setLogo("logo");
            breedingPlace.setRegister_number("12345678");
            breedingPlaceRepository.save(breedingPlace);
            log.info("Seeding Breeding Place Table");

        }else {
            log.trace("Breeding Place Seeding Not Required");
        }
    }

    private void seedSpecieTable() {

        String sql = "SELECT * FROM specie s WHERE s.name= 'gecko'";
        List<Specie> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if(rs == null || rs.size() <= 0) {
            Specie specie = new Specie();
            specie.setName("gecko");
            specieRepository.save(specie);
            log.info("Seeding Specie Table");

        }else {
            log.trace("Specie Seeding Not Required");
        }
    }

    private void seedAnimalTable() {

        String sql = "SELECT * FROM animal a WHERE a.name= 'gecko'";
        List<Animal> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);


        String sql1 = "SELECT * FROM specie s WHERE s.name= 'gecko'";
        Specie specie = (Specie) jdbcTemplate.queryForObject(
                sql1,
                new Object[]{},
                new BeanPropertyRowMapper(Specie.class));


        String sql2 = "SELECT * FROM breeding_place b WHERE b.register_number= '12345678'";
        BreedingPlace breedingPlace = (BreedingPlace) jdbcTemplate.queryForObject(
                sql2,
                new Object[]{},
                new BeanPropertyRowMapper(BreedingPlace.class));

        if(rs == null || rs.size() <= 0) {
            Animal animal = new Animal();
            animal.setName("gecko");
            animal.setDescription("gecko");
            animal.setCreatedAt(LocalDateTime.now());
            animal.setRegister_number("12345678");
            animal.setBirthday("24/10/05");
            animal.setQr("ass");
            animal.setSpecie(specie);
            animal.setBreedingPlace(breedingPlace);
            animal.setGender("M");
            animalRepository.save(animal);
            log.info("Seeding Animal Table");

        }else {
            log.trace("Animal Seeding Not Required");
        }
    }

    private void seedLayingTable() {

        String sql1 = "SELECT * FROM animal a WHERE a.name= 'gecko'";
        Animal animal = (Animal) jdbcTemplate.queryForObject(
                sql1,
                new Object[]{},
                new BeanPropertyRowMapper(Animal.class));

        String sql = "SELECT * FROM laying l WHERE l.id_animal="+animal.getId();
        List<Laying> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if(rs == null || rs.size() <= 0) {
            Laying laying = new Laying();
            laying.setCreatedAt(LocalDateTime.now());
            laying.setAmount(10);
            laying.setDeads(1);
            laying.setAnimal(animal);
            layingRepository.save(laying);
            log.info("Seeding Laying Table");

        }else {
            log.trace("Laying Seeding Not Required");
        }
    }

    private void seedPhotoTable() {

        String sql1 = "SELECT * FROM animal a WHERE a.name= 'gecko'";
        Animal animal = (Animal) jdbcTemplate.queryForObject(
                sql1,
                new Object[]{},
                new BeanPropertyRowMapper(Animal.class));

        String sql = "SELECT * FROM photo p WHERE p.id_animal="+animal.getId();
        List<Photo> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if(rs == null || rs.size() <= 0) {
            Photo photo = new Photo();
            photo.setPhoto("photo");
            photo.setAnimal(animal);
            photo.setCreatedAt(LocalDateTime.now());
            photoRepository.save(photo);
            log.info("Seeding Photo Table");

        }else {
            log.trace("Photo Seeding Not Required");
        }
    }

}
