package com.ironhack.security.services.interfaces;

import com.ironhack.security.models.Spell;

public interface SpellService {
    Spell saveSpell(Spell spell);

    void updateSpellLevel(Integer id, String level);
}
