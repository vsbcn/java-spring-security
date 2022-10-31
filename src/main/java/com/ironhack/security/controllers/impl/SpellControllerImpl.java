package com.ironhack.security.controllers.impl;

import com.ironhack.security.controllers.interfaces.SpellController;
import com.ironhack.security.models.Spell;
import com.ironhack.security.services.interfaces.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SpellControllerImpl implements SpellController {

    @Autowired
    private SpellService spellService;

    @PostMapping("/spell")
    @ResponseStatus(HttpStatus.CREATED)
    public Spell saveSpell(@RequestBody @Valid Spell spell) {
        return spellService.saveSpell(spell);
    }

    @PatchMapping("/spell/level/{spellId}")
    public void updateSpellLevel(@PathVariable(name = "spellId") Integer id, @RequestParam String level) {
        spellService.updateSpellLevel(id, level);
    }
}
