package com.ironhack.security.controllers.interfaces;

import com.ironhack.security.models.Spell;

public interface SpellController {
    Spell saveSpell(Spell spell);
    void updateSpellLevel(Integer id, String level);
}
