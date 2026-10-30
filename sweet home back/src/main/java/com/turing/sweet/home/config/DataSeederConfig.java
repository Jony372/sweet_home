package com.turing.sweet.home.config;

import com.turing.sweet.home.modules.cateogories.entity.CategoryEntity;
import com.turing.sweet.home.modules.cateogories.repository.CategoryRepository;
import com.turing.sweet.home.modules.houses.entity.HouseEntity;
import com.turing.sweet.home.modules.houses.repository.HouseRepository;
import com.turing.sweet.home.modules.ratings.entity.RatingEntity;
import com.turing.sweet.home.modules.ratings.repository.RatingRepository;
import com.turing.sweet.home.modules.users.entity.UserEntity;
import com.turing.sweet.home.modules.users.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSeederConfig {
    private final PasswordEncoder passwordEncoder;

    public DataSeederConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Order(1)
    public CommandLineRunner initUserAdminAndUsers(UserRepository userRepository) {
        String username = "Admin";
        String pasword = this.passwordEncoder.encode("Admin123");
        return args -> {
            if(!userRepository.existsByUsername(username)) {
                UserEntity admin = new UserEntity(
                        "Admin",
                        "Admin",
                        username,
                        null,
                        null
                );
                admin.setPassword(pasword);
                admin.setAdmin(true);
                userRepository.save(admin);
            }

            if(userRepository.count() > 10) return;

            Faker faker = new Faker();
            for (int i = 1; i <= 15; i++) {
                String userUsername = faker.name().lastName();
                String userPassword = this.passwordEncoder.encode("pass1234");
                if(!userRepository.existsByUsername(userUsername)) {
                    UserEntity user = new UserEntity(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            userUsername,
                            null,
                            null
                    );
                    user.setPassword(userPassword);
                    user.setAdmin(false);
                    userRepository.save(user);
                }
            }
        };
    }

    @Bean
    @Order(2)
    public CommandLineRunner initCategoriesAndHouse(
            CategoryRepository categoryRepository,
            HouseRepository houseRepository
    ) {
        return args -> {
            Faker faker = new Faker();
            List<CategoryEntity> categories = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                String name = faker.construction().subcontractCategories();
                if(categoryRepository.existsByName(name)) continue;
                CategoryEntity category = new CategoryEntity();
                category.setName(name);
                category.setDescription(faker.lorem().sentence());
                categoryRepository.save(category);
                categories.add(category);
            }

            if(houseRepository.count() > 15) return;
            for(int i = 0; i < 15; i++) {
                HouseEntity house = new HouseEntity(
                        categories.get(faker.random().nextInt(categories.size())),
                        faker.lorem().sentence(),
                        faker.internet().image(),
                        faker.company().name(),
                        BigDecimal.valueOf(faker.number().randomDouble(2, 500000, 5000000))
                );
                houseRepository.save(house);
            }
        };
    };

    // Seeder de calificaciones
    @Bean
    @Order(3)
    public CommandLineRunner initRatings(UserRepository userRepository, RatingRepository ratingRepository, HouseRepository houseRepository) {
        return args -> {
            if(ratingRepository.count() > 3) return;
            List<UserEntity> users = userRepository.findAll();
            List<HouseEntity> houses = houseRepository.findAll();

            Faker faker = new Faker();

            for(int i = 0; i < 5; i++){
                RatingEntity rating = new RatingEntity(
                        faker.lorem().sentence(),
                        houses.get(faker.random().nextInt(houses.size())),
                        faker.number().numberBetween(1, 5),
                        users.get(faker.random().nextInt(users.size()))
                );
                ratingRepository.save(rating);
            }
        };
    }
}
