package com.sakha.sakhatranslator.controller;

import com.sakha.sakhatranslator.model.CyrillicToLatinTranslator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/cyrillic_to_latin")
    String cyrillicToLatin(@RequestBody String cyrillicText) {
        CyrillicToLatinTranslator translator = new CyrillicToLatinTranslator(cyrillicText);
        return translator.translate();
    }
}