package com.example.restservice.controller;

import com.example.restservice.cache.InclusionCache;
import com.example.restservice.entity.*;
import com.example.restservice.requestCounter.MySemaphore;
import com.example.restservice.requestCounter.RequestCounterThread;
import com.example.restservice.service.CharacterInclusionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Validated
@RestController
public class CharacterInclusionController {

    @Autowired
    private CharacterInclusionService characterInclusionService;

    @Autowired
    private InclusionCache cache;


    @PostMapping("/inclusion")
    public ResponseEntity<?> bulkInclusion(@Valid @RequestBody WrapperObject wrapperObject) {
        List<Inclusion> numberOfInclusionsList = new LinkedList<>();
        List<RequestParameters> bodyList = wrapperObject.getObjects();
        bodyList.forEach((element) -> {
            try {
                numberOfInclusionsList.add(characterInclusionService.calculateCharacterInclusion(element));
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                System.exit(2);
            } catch (IllegalArgumentException iae) {
                iae.printStackTrace();
                System.exit(3);
            } catch (IllegalStateException ise) {
                ise.printStackTrace();
                System.exit(4);
            }
        });
        int max = characterInclusionService.calculateMaxResult(numberOfInclusionsList);
        int min = characterInclusionService.calculateMinResult(numberOfInclusionsList);
        int sum = characterInclusionService.calculateSumResult(numberOfInclusionsList);
        Dto responceObject = new Dto(numberOfInclusionsList, max, min, sum);
        return new ResponseEntity<>(responceObject, HttpStatus.OK);
    }

    @GetMapping("/inclusion")
    public Inclusion inclusion(@RequestParam(value = "string", defaultValue = "ABCDEFGHIJKLMOP") @Size(min = 2, max = 50, message = "String length must be between 2 and 50") String name,
                               @RequestParam(value = "symbol", defaultValue = "A") char symbol) {
        RequestParameters parameters = new RequestParameters(name, symbol);
        new RequestCounterThread(Thread.currentThread().getName()).start();
        if (cache.contains(parameters)) {
            return cache.getResult(parameters);
        }
        Inclusion result = characterInclusionService.calculateCharacterInclusion(parameters);
        MySemaphore.semaphore.release();
        cache.addCache(parameters, result);
        return result;
    }

    @GetMapping("/cache")
    public HashMap<RequestParameters, Inclusion> getCache()
    {
        return cache.getCache();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleException(ConstraintViolationException ex) {
        ApiError newError = new ApiError("Entity not found exception", ex.getMessage());
        return new ResponseEntity<>(newError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleException(MethodArgumentTypeMismatchException ex) {
        ApiError newError = new ApiError("Wrong symbol argument exception", ex.getMessage());
        return new ResponseEntity<>(newError, HttpStatus.BAD_REQUEST);
    }
}