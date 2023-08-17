package com.example.cardapio.controler;

import com.example.cardapio.food.Food;
import com.example.cardapio.repository.FoodRepository;
import com.example.cardapio.dto.FoodRequestDTO;
import com.example.cardapio.dto.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodControler {

    @Autowired  /*  IC, CD ou CDI = Injeção de dependência */
    private FoodRepository foodRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        foodRepository.save(foodData);
        return;
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<Food> atualizar(@PathVariable Long foodId,
                                          @RequestBody Food food) {
        if (!foodRepository.existsById(foodId)) {
            return ResponseEntity.notFound().build();
        }

        food.setId(foodId);
        Food foodAtualizado = foodRepository.save(food);

        return ResponseEntity.ok(foodAtualizado);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll(){

        /*List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList; = A linha posterior tem o mesmo resultado destas duas linhas. */
        return foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
    }
}

// TODO -  Vídeo assistido e aula concluida = https://www.youtube.com/watch?v=lUVureR5GqI&t=735s
// TODO - Assistir ao próximo vídeo = https://www.youtube.com/watch?v=WHruc3_2z68