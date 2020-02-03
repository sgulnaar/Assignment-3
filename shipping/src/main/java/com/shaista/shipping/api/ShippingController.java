package com.shaista.shipping.api;

import com.shaista.shipping.domain.ShippingRates;
import com.shaista.shipping.services.ShippingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shipping")
public class ShippingController {
    private ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping
    public ResponseEntity<List<ShippingModel>> get(@RequestParam(required = false) Optional<Integer> offset, @RequestParam(required = false) Optional<Integer> limit) {
        int skip = offset.orElse(0); //default offset to 0 if not specified
        int take = limit.orElse(50); //default limit to 50 if not sepcified
        if (take > 50) {
            throw new IllegalArgumentException("Limit cannot be more than 50.");
        }

        List<ShippingModel> shipping = shippingService.getAll(skip, take)
                .stream().map(c->ShippingMapper.toModel(c))
                .collect(Collectors.toList());

        return new ResponseEntity<>(shipping, HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<ShippingModel> get(@PathVariable long id) {
        ShippingRates shippingRates = shippingService.getById(id);
        if (shippingRates == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ShippingMapper.toModel(shippingRates), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ShippingModel> create(@RequestBody ShippingModel model) {
        ShippingRates shippingRates = ShippingMapper.toEntity(model);
        ShippingRates savedShipping = shippingService.save(shippingRates);
        return ResponseEntity.created(URI.create("/shipping/" + savedShipping.getShippingRateID()))
                .body(ShippingMapper.toModel(savedShipping));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingModel> update(@PathVariable long id,
                                                @RequestBody ShippingModel model) {
        ShippingRates existingShipping = shippingService.getById(id);
        if (existingShipping == null) {
            return ResponseEntity.notFound().build();
        }

        if (existingShipping.getVersion() != model.getVersion()) {
            return new ResponseEntity<>(ShippingMapper.toModel(existingShipping), HttpStatus.CONFLICT);
        }
        ShippingRates mergedShipping = ShippingMapper.toEntity(model, existingShipping);

        ShippingRates savedShipping = shippingService.save(mergedShipping);

        return new ResponseEntity<>(ShippingMapper.toModel(savedShipping), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        ShippingRates existingShipping = shippingService.getById(id);
        if (existingShipping ==  null)
            return;

        shippingService.delete(existingShipping);
    }
    @GetMapping(params = "country")
    public ResponseEntity<List<ShippingModel>> findByCountry(@RequestParam String country) {
        return ResponseEntity
                .ok()
                .body(shippingService.findByCountry(country).stream()
                        .map(c->ShippingMapper.toModel(c))
                        .collect(Collectors.toList()));
    }

}
