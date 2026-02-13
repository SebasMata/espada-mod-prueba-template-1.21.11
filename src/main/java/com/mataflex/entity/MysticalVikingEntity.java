package com.mataflex.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.level.Level;

public class MysticalVikingEntity extends ZombifiedPiglin {

    public MysticalVikingEntity(EntityType<? extends ZombifiedPiglin> entityType, Level level) {
        super(entityType, level);
    }

}