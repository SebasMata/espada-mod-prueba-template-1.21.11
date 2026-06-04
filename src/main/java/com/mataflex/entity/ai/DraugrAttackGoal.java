package com.mataflex.entity.ai;

import com.mataflex.entity.DraugrEntity;
import com.mataflex.sound.CustomSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class DraugrAttackGoal extends MeleeAttackGoal {

    private final DraugrEntity draugr;

    public DraugrAttackGoal(DraugrEntity draugr, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(draugr, speedModifier, followingTargetEvenIfNotSeen);
        this.draugr = draugr;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
        if (this.canPerformAttack(target)) {
            this.resetAttackCooldown();
            if (this.mob.level() instanceof ServerLevel serverLevel) {
                this.mob.doHurtTarget(serverLevel, target);
            }
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));
            this.mob.playSound(CustomSounds.DRAUGR_ATTACK, 1.0F, 1.0F);
        }
    }
}
