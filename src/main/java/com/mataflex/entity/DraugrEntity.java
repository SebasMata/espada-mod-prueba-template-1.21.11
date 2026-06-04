package com.mataflex.entity;

import com.mataflex.item.ModItems;
import com.mataflex.sound.CustomSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.witherskeleton.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class DraugrEntity extends WitherSkeleton {

    public DraugrEntity(EntityType<? extends WitherSkeleton> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new com.mataflex.entity.ai.DraugrAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(2, new net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(3, new net.minecraft.world.entity.ai.goal.LookAtPlayerGoal(this, net.minecraft.world.entity.player.Player.class, 12.0F));
        this.goalSelector.addGoal(4, new net.minecraft.world.entity.ai.goal.RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<>(this, net.minecraft.world.entity.player.Player.class, true));
    }

    @Override
    protected void dropCustomDeathLoot(@NonNull ServerLevel serverLevel, @NonNull DamageSource damageSource, boolean bl) {
        super.dropCustomDeathLoot(serverLevel, damageSource, bl);

        if (this.random.nextFloat() < 0.65f) {
            this.spawnAtLocation(serverLevel, new ItemStack(Items.BONE, this.random.nextIntBetweenInclusive(1, 2)));
        }

        if (this.random.nextFloat() < 0.33f) {
            this.spawnAtLocation(serverLevel, new ItemStack(Items.COAL, 1));
        }

        if (this.random.nextFloat() < 0.05f) {
            this.spawnAtLocation(serverLevel, new ItemStack(ModItems.RUNIC_GEM, 1));
        }
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return CustomSounds.DRAUGR_AMBIENT;
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource damageSource) {
        return CustomSounds.DRAUGR_HURT;
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return CustomSounds.DRAUGR_DEATH;
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState state) {
        this.playSound(SoundEvents.WITHER_SKELETON_STEP, 0.3F, 0.7F);
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
        return world.isUnobstructed(this);
    }

    public static boolean checkDraugrSpawnRules(EntityType<? extends Monster> type, LevelAccessor world, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return Monster.checkAnyLightMonsterSpawnRules(type, world, spawnReason, pos, random);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, @NonNull net.minecraft.world.DifficultyInstance difficultyInstance, @NonNull EntitySpawnReason entitySpawnReason, @Nullable SpawnGroupData spawnGroupData) {
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, entitySpawnReason, spawnGroupData);
    }
}
