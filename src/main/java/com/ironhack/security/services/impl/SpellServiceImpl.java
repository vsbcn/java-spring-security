package com.ironhack.security.services.impl;

import com.ironhack.security.enums.Level;
import com.ironhack.security.models.Spell;
import com.ironhack.security.repositories.SpellRepository;
import com.ironhack.security.services.interfaces.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SpellServiceImpl implements SpellService {

    @Autowired
    private SpellRepository spellRepository;

    public Spell saveSpell(Spell spell) {
        spell.setId(null);
        return spellRepository.save(spell);
    }

    public void updateSpellLevel(Integer id, String level) {
        Optional<Spell> optionalSpell = spellRepository.findById(id);
        if (optionalSpell.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Spell not found");
        }
        optionalSpell.get().setLevel(Level.valueOf(level.toUpperCase()));
        spellRepository.save(optionalSpell.get());
    }
}
